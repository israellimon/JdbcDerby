package org.sf.chapter5.jdbc.dao;

import org.sf.chapter5.jdbc.model.Employee;

public interface EmployeeDao {
	Employee getEmployeeById(int id);
	
	void createEmployee();
	
	void insertEmployee(Employee employee);

}
