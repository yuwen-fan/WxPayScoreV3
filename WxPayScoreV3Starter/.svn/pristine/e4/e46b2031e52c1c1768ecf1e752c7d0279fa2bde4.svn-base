package com.evchong.wxpayscore.filter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import lombok.Getter;
import lombok.Setter;

/**
 * 可重复读取IO流中body的HttpServletRequestWrapper
 * 
 * <p>
 * 用于解决拦截器读取IO流后造成@RequestBody读取不到内容而报错的问题
 * <p>
 * IO流被读取过一次之后，pos位置有变化，又不能重置，再次读取body时会返回null，从而报错
 * 
 * @see HttpServletRequestWrapperReplaceFilter
 * 
 * @author fanyuwen
 *
 */
public class RepeatableReadHttpServletRequestWrapper extends HttpServletRequestWrapper {
	private static final Log log = LogFactory.getLog(RepeatableReadHttpServletRequestWrapper.class);
	@Getter
	@Setter
	private String body;

	private RepeatableReadHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
	}

	public static RepeatableReadHttpServletRequestWrapper of(HttpServletRequest request) {
		RepeatableReadHttpServletRequestWrapper wrapper = new RepeatableReadHttpServletRequestWrapper(request);
		StringBuilder stringBuilder = new StringBuilder();
		try (InputStream inputStream = request.getInputStream();
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));) {
			char[] charBuffer = new char[1024];
			int bytesRead;
			while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
				stringBuilder.append(charBuffer, 0, bytesRead);
			}
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		wrapper.setBody(stringBuilder.toString());
		return wrapper;
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes());
		return new ServletInputStream() {
			@Override
			public boolean isFinished() {
				return false;
			}

			@Override
			public boolean isReady() {
				return false;
			}

			@Override
			public void setReadListener(ReadListener readListener) {
				// NO-OP
			}

			@Override
			public int read() throws IOException {
				return byteArrayInputStream.read();
			}
		};
	}

	@Override
	public BufferedReader getReader() throws IOException {
		return new BufferedReader(new InputStreamReader(this.getInputStream()));
	}
}