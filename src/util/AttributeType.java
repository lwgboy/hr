package util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AttributeType {
	private Object currentObj ;	// 当前的操作对象
	private String attribute ;	// 属性的字符串描述
	private Field field ;		// 属性的成员
	
	public AttributeType(Object currentObj, String attribute) {
		this.currentObj = currentObj ;
		this.attribute = attribute ;
		this.handleParameter() ;	// 取得属性成员
	}
	
	private void handleParameter() {	// 针对传入数据进行处理
		try {
			String result [] = this.attribute.split("\\.") ;
			for (int i=0 ; i<result.length-1 ; ++i) {
				// 先判断是否存在该成员，避免报异常
				if (this.currentObj.getClass().getDeclaredField(result[i])!=null) {
					Method getMet = this.currentObj.getClass().getMethod("get" + 
							StringUtil.initcap(result[i])) ;
					this.currentObj = getMet.invoke(this.currentObj) ;
				}
			}
			this.field = this.currentObj.getClass().getDeclaredField(result[result.length-1]) ;
		} catch (Exception e) {
			e.printStackTrace() ;
		}
	}
	
	public Field getField() {
		return this.field ;
	}
	
	public String getFieldType() {
		return this.field.getType().getSimpleName() ;
	}
}
