package service.back;

import java.util.Map;

public interface IWorkServiceBack {
	public Map<String,Object> listSplit(int currentPage, int lineSize, String column, String keyWord) throws Exception ;
}
