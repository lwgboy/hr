package factory;

import service.front.IAdminServiceFront;
import service.front.IEmployeeServiceFront;
import service.front.ISalaryServiceFront;
import service.front.IWorkServiceFront;
import service.front.impl.AdminServiceFrontImpl;
import service.front.impl.EmployeeServiceFrontImpl;
import service.front.impl.SalaryServiceFrontImpl;
import service.front.impl.WorkServiceFrontImpl;

public class ServiceFrontFactory {
	public static IAdminServiceFront getIAdminServiceFrontInstance() {
		return new AdminServiceFrontImpl() ;
	}
	public static IEmployeeServiceFront getIEmployeeServiceFrontInstance() {
		return new EmployeeServiceFrontImpl() ;
	}
	public static IWorkServiceFront getIWorkServiceFrontInstance() {
		return new WorkServiceFrontImpl() ;
	}
	public static ISalaryServiceFront getISalaryServiceFrontInstance() {
		return new SalaryServiceFrontImpl() ;
	}
}
