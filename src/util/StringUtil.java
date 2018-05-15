package util;

import java.util.HashSet;
import java.util.Set;

public class StringUtil {
	/**
	 * 将一个字符串首字母改为大写，其余部分不变
	 * @param str 被操作的字符串
	 * @return 修改完的字符串
	 */
	public static String initcap(String str) {
		if (str==null || "".equals(str)) {	// 保证操作的数据有内容
			return str ;
		}
		if (str.length()==1) {	// 只有一位数据
			return str.toUpperCase() ;	// 全部变为大写
		} else {
			return str.substring(0,1).toUpperCase() + str.substring(1,str.length()) ;
		}
	}
	
	public static Set<Integer> splitInteger(String str) {
		Set<Integer> set = new HashSet<Integer>() ;
		if (str == null || "".equals(str)) {
			return set ;
		}
		String result [] = str.split("\\|") ;
		for (int i=0 ; i<result.length ; i++) {
			set.add(Integer.parseInt(result[i])) ;
		}
		return set ;
	}
	
	public static Set<String> splitString(String str) {
		Set<String> set = new HashSet<String>() ;
		if (str == null || "".equals(str)) {
			return set ;
		}
		String result [] = str.split("\\|") ;
		for (int i=0 ; i<result.length ; i++) {
			set.add(result[i]) ;
		}
		return set ;
	}
}
