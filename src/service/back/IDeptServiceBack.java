package service.back;

import java.util.List;
import java.util.Set;

import vo.Dept;

public interface IDeptServiceBack {
	public boolean insert(Dept dept) throws Exception ;
	public boolean delete(Set<Integer> dids) throws Exception ;
	public boolean update(Dept dept) throws Exception ;
	public List<Dept> list() throws Exception ;
}
