package service.back;

import java.util.List;
import java.util.Set;

import vo.Jobs;

public interface IJobsServiceBack {
	public boolean insert(Jobs jobs) throws Exception ;
	public boolean delete(Set<Integer> jids) throws Exception ;
	public Jobs updatePre(int jid) throws Exception ;
	public boolean update(Jobs jobs) throws Exception ;
	public List<Jobs> list() throws Exception ;
}
