package factory;

import service.back.IDeptServiceBack;
import service.back.IEmployeeServiceBack;
import service.back.IJobsServiceBack;
import service.back.ILevelServiceBack;
import service.back.ISalaryServiceBack;
import service.back.IWorkServiceBack;
import service.back.impl.DeptServiceBackImpl;
import service.back.impl.EmployeeServiceBackImpl;
import service.back.impl.JobsServiceBackImpl;
import service.back.impl.LevelServiceBackImpl;
import service.back.impl.SalaryServiceBackImpl;
import service.back.impl.WorkServiceBackImpl;

public class ServiceBackFactory {
	public static IDeptServiceBack getIDeptServiceBackInstance() {
		return new DeptServiceBackImpl() ;
	}
	public static IJobsServiceBack getIJobsServiceBackInstance() {
		return new JobsServiceBackImpl() ;
	}
	public static ILevelServiceBack getILevelServiceBackInstance() {
		return new LevelServiceBackImpl() ;
	}
	public static IEmployeeServiceBack getIEmployeeServiceBackInstance() {
		return new EmployeeServiceBackImpl() ;
	}
	public static IWorkServiceBack getIWorkServiceBackInstance() {
		return new WorkServiceBackImpl() ;
	}
	public static ISalaryServiceBack getISalaryServiceBackInstance() {
		return new SalaryServiceBackImpl() ;
	}
}
