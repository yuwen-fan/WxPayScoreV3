package com.evchong.wxpayscore.allocation.api.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.Data;

/**
 * 微信支付分普通直连分账 - 公共响应体
 */
@Data
public class WxPayAllocationCommonResponse implements WxPayAllocationResponse {

	private static final long serialVersionUID = 1L;
	private static final String RETURN_CODE_SUCCESS = "SUCCESS";
	private static final String RESULT_CODE_SUCCESS = "SUCCESS";

	/**
	 * 返回状态码
	 * 
	 * <p>
	 * SUCCESS/FAIL 此字段是通信标识，非交易标识
	 * <p>
	 * 示例值: SUCCESS
	 */
	@Size(max = 32)
	@NotBlank
	@JacksonXmlProperty(localName = "return_code")
	private String returnCode;
	/**
	 * 返回信息
	 * <p>
	 * 如非空，为错误原因
	 * <p>
	 * 示例值: 参数格式校验错误。
	 */
	@JacksonXmlProperty(localName = "return_msg")
	private String returnMsg;

	/**
	 * 业务结果
	 * <li>SUCCESS：添加分账接收方成功
	 * <li>FAIL ：提交业务失败
	 * 
	 * <p>
	 * 该字段及以下字段在return_code为SUCCESS的时候有返回
	 * <p>
	 * 必填
	 * <p>
	 * 示例值：SUCCESS
	 */
	@Size(max = 32)
	@JacksonXmlProperty(localName = "result_code")
	private String resultCode;

	/**
	 * 错误代码
	 * <p>
	 * 列表详见错误码列表
	 * <p>
	 * 非必填
	 * <p>
	 * 示例值：SYSTEMERROR
	 */
	@Size(max = 32)
	@JacksonXmlProperty(localName = "err_code")
	private String errCode;

	/**
	 * 错误代码描述
	 * <p>
	 * 结果信息描述
	 * <p>
	 * 非必填
	 * <p>
	 * 示例值：系统超时
	 */
	@Size(max = 128)
	@JacksonXmlProperty(localName = "err_code_des")
	private String errCodeDes;

	public boolean hasError() {
		return !RETURN_CODE_SUCCESS.equals(returnCode) || !RESULT_CODE_SUCCESS.equals(resultCode);
	}
}
