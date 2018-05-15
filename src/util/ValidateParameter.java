package util;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import com.jspsmart.upload.SmartUpload;

public class ValidateParameter {
	private Object currentObj ;
	private HttpServletRequest request ;
	private SmartUpload smart ;
	private String rule ;
	// 保存所有的错误信息，其中key保存参数名称，value保存内容信息
	private Map<String,String> map = new HashMap<String,String>() ;
	private ResourceBundle msg = null ;
	
	/**
	 * 进行数据验证的设置操作
	 * @param currentObj 当前对象
	 * @param request 需要接收所有的请求参数，所以需要request
	 * @param smart 考虑到表单封装
	 * @param rule 验证规则
	 * @param msg 考虑到要传出错误信息
	 */
	public ValidateParameter(Object currentObj, HttpServletRequest request, 
			SmartUpload smart, String rule, ResourceBundle msg) {
		this.currentObj = currentObj ;
		this.request = request ;
		this.smart = smart ;
		this.rule = rule ;
		this.msg = msg ;
	}
	
	public boolean validate() {
		String result [] = this.rule.split("\\|") ;
		for (int i=0 ; i<result.length ; i++) {
			AttributeType at = new AttributeType(this.currentObj, result[i]) ;
			String type = at.getFieldType().toLowerCase() ;
			if (!type.contains("[]")) {
				String value = null ;
				if (this.request.getContentType() != null) {	// 通过表单传递数据
					if (this.request.getContentType().contains("multipart/form-data")) {	// 表单进行了分装
						value = this.smart.getRequest().getParameter(result[i]) ;
					} else {
						value = this.request.getParameter(result[i]) ;
					}
				} else {	// 没有通过表单传递数据
					value = this.request.getParameter(result[i]) ;
				}
				
				if ("string".equals(type)) {
					if (!ValidateUtil.validateEmpty(value)) {
						this.map.put(result[i], this.msg.getString("validate.string.failure.msg")) ;
					}
				} else if ("int".equals(type) || "integer".equals(type)) {
					if (!ValidateUtil.validateRegex(value, "\\d+")) {
						this.map.put(result[i], this.msg.getString("validate.int.failure.msg")) ;
					}
				} else if ("double".equals(type)) {
					if (!ValidateUtil.validateRegex(value, "\\d+(\\.\\d+)?")) {
						this.map.put(result[i], this.msg.getString("validate.double.failure.msg")) ;
					}
				} else if ("date".equals(type)) {
					if (!ValidateUtil.validateRegex(value, "\\d{4}-\\d{2}-\\d{2}")
						&& !ValidateUtil.validateRegex(value, "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")) {
						this.map.put(result[i], this.msg.getString("validate.date.failure.msg")) ;
					}
				}
			} else {
				String value [] = null ;
				if (this.request.getContentType() != null) {	// 通过表单传递数据
					if (this.request.getContentType().contains("multipart/form-data")) {
						value = this.smart.getRequest().getParameterValues(result[i]) ;
					} else {
						value = request.getParameterValues(result[i]) ;
					}
				} else {
					value = request.getParameterValues(result[i]) ;
				}
				if (value == null) {
					this.map.put(result[i], this.msg.getString("validate.string.failure.msg")) ;
				} else {
					if ("int[]".equals(type) || "integer[]".equals(type)) {
						for (int j=0 ; j<value.length ; j++) {
							if (!ValidateUtil.validateRegex(value[j], "\\d+")) {
								this.map.put(result[i], this.msg.getString("validate.int.failure.msg")) ;
								break ;
							}
						}
					} else if ("double[]".equals(type)) {
						for (int j=0 ; j<value.length ; j++) {
							if (!ValidateUtil.validateRegex(value[j], "\\d+(\\.\\d+)?")) {
								this.map.put(result[i], this.msg.getString("validate.double.failure.msg")) ;
								break ;
							}
						}
					}
				}
			}
		}
		return this.map.size() == 0 ;
	}
	
	public Map<String,String> getErrors() {
		return this.map ;
	}
}
