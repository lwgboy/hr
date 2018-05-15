package util;

import javax.servlet.http.HttpServletRequest;

public class BasePath {
	public static String getBasePath(HttpServletRequest request) {
		return request.getScheme() + "://" + request.getServerName() 
			+ ":" + request.getServerPort() + request.getContextPath() + "/" ;
	}
}
