package org.sf.chapter5.jdbc.main;

import org.sf.chapter5.jdbc.dao.EmployeeDao;
import org.sf.chapter5.jdbc.dao.EmployeeDaoImpl;
import org.sf.chapter5.jdbc.model.Employee;

public class HrPayrollSystem {

	public static void main(String[] args) {

		EmployeeDao employeeDao = new EmployeeDaoImpl();
		
		employeeDao.createEmployee();
		
		employeeDao.insertEmployee(new Employee(1, "Juan Perez"));
		
		Employee employee = employeeDao.getEmployeeById(1);
		
		System.out.println("Employee name: "+employee.getName());

	}

}
