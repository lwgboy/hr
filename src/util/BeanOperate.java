package util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;

public class BeanOperate {
	private Object currentObj ;	// 表示当前程序的保存对象
	private String attribute ;	// 要操作的属性
	private String value ;		// 要操作的内容
	private String arrayValue [] ;	// 要操作的数组内容
	private Field field ;		// 属性
	
	/**
	 * 进行操作数据的接收，接收后才可以进行数据的设置操作
	 * @param obj 表示当前要操作此功能的类对象
	 * @param attribute 包含了“对象.属性.属性...”字符串
	 * @param value 表示属性的内容
	 */
	public BeanOperate(Object obj, String attribute, String value) {
		this.currentObj = obj ;		// 保存当前的操作对象
		this.attribute = attribute ;
		this.value = value ;
		this.handleParameter() ;
	}
	
	/**
	 * 进行数组数据类型的操作
	 * @param obj
	 * @param attribute
	 * @param value
	 */
	public BeanOperate(Object obj, String attribute, String arrayValue[]) {
		this.currentObj = obj ;		// 保存当前的操作对象
		this.attribute = attribute ;
		this.arrayValue = arrayValue ;
		this.handleParameter() ;
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
			if (this.field!=null) {
				Method setMet = this.currentObj.getClass().getMethod("set" + 
						StringUtil.initcap(this.field.getName()), 
						this.field.getType()) ;
				// 取得数据类型
				String type = this.field.getType().getSimpleName()	;
				if ("string".equalsIgnoreCase(type)) {
					setMet.invoke(this.currentObj, this.value) ;
				} else if ("int".equalsIgnoreCase(type)
						|| "integer".equalsIgnoreCase(type)) {
					if (this.value.matches("\\d+")) {
						setMet.invoke(this.currentObj, Integer.parseInt(this.value)) ;
					}
				} else if ("double".equalsIgnoreCase(type)) {
					if (this.value.matches("\\d+(\\.\\d+)?")) {
						setMet.invoke(this.currentObj, Double.parseDouble(this.value)) ;
					}
				} else if ("date".equalsIgnoreCase(type)) {
					if (this.value.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")) {
						setMet.invoke(this.currentObj, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
								.parse(this.value)) ;
					} else if (this.value.matches("\\d{4}-\\d{2}-\\d{2}")) {
						setMet.invoke(this.currentObj, new SimpleDateFormat("yyyy-MM-dd")
							.parse(this.value)) ;
					}
				} else if ("string[]".equalsIgnoreCase(type)) {	// 字符串数组
					setMet.invoke(this.currentObj, new Object[]{this.arrayValue}) ;
				} else if ("int[]".equalsIgnoreCase(type)) {
					int temp [] = new int [this.arrayValue.length] ;
					for (int i=0 ; i<temp.length ; ++i) {
						if (this.arrayValue[i].matches("\\d+")) {
							temp[i] = Integer.parseInt(this.arrayValue[i]) ;
						}
					}
					setMet.invoke(this.currentObj, new Object[]{temp}) ;
				} else if ("integer[]".equalsIgnoreCase(type)) {
					Integer temp [] = new Integer [this.arrayValue.length] ;
					for (int i=0 ; i<temp.length ; ++i) {
						if (this.arrayValue[i].matches("\\d+")) {
							temp[i] = Integer.parseInt(this.arrayValue[i]) ;
						}
					}
					setMet.invoke(this.currentObj, new Object[]{temp}) ;
				} else if ("double[]".equals(type)) {
					double temp [] = new double [this.arrayValue.length] ;
					for (int i=0 ; i<temp.length ; ++i) {
						if (this.arrayValue[i].matches("\\d+(\\.\\d+)?")) {
							temp[i] = Double.parseDouble(this.arrayValue[i]) ;
						}
					}
					setMet.invoke(this.currentObj, new Object[]{temp}) ;
				} else if ("Double[]".equals(type)) {
					Double temp [] = new Double [this.arrayValue.length] ;
					for (int i=0 ; i<temp.length ; ++i) {
						if (this.arrayValue[i].matches("\\d+(\\.\\d+)?")) {
							temp[i] = Double.parseDouble(this.arrayValue[i]) ;
						}
					}
					setMet.invoke(this.currentObj, new Object[]{temp}) ;
				}
			}
		} catch (Exception e) {
			e.printStackTrace() ;
		}
	}
}
