package util;

public class ValidateUtil {
	/**
	 * 验证输入的数据是否为空
	 * @param data 输入数据
	 * @return 如果不是null或者""，返回true，否则返回false
	 */
	public static boolean validateEmpty(String data) {
		if (data==null || "".equals(data)) {
			return false ;
		}
		return true ;
	}
	
	/**
	 * 进行数据的正则操作验证
	 * @param data 要验证的数据
	 * @param regex 要执行验证的正则表达式
	 * @return 验证通过返回true，否则false
	 */
	public static boolean validateRegex(String data, String regex) {
		if (!validateEmpty(data)) {
			return false ;
		}
		return data.matches(regex) ;		
	}

	/**
	 * 验证两个字符串是否相同，不区分大小写
	 * @param dataA 数据A
	 * @param dataB 数据B
	 * @return 如果下你沟通放回true，否则返回false
	 */
	public static boolean validateSame(String dataA, String dataB) {
		if (dataA==null || dataB==null) {
			return false ;
		}
		return dataA.equalsIgnoreCase(dataB) ;
	}
}
