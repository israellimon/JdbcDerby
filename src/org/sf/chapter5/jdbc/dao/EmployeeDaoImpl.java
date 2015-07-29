package org.sf.chapter5.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.sf.chapter5.jdbc.model.Employee;

public class EmployeeDaoImpl implements EmployeeDao {
	
	static final String JDBC_DRIVER = "org.apache.derby.jdbc.ClientDriver";
	static final String DB_URL = "jdbc:derby://localhost:1527/db";
	
	private void registerDriver(){
		try {
			Class.forName(JDBC_DRIVER).newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Employee getEmployeeById(int id) {
		Connection con= null;
		Employee employee = null;
		
		try{
			registerDriver();
			con = DriverManager.getConnection(DB_URL);
			
			PreparedStatement ps = con.prepareStatement("select * from employee where id = ?");
			ps.setInt(1,id);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				employee = new Employee(id, rs.getString("name"));
			}
			
			rs.close();
			ps.close();
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}finally{
			if(con != null){
				try{
					con.close();
				}catch(SQLException e){
					
				}
			}
		}
		
		return employee;
	}

	@Override
	public void createEmployee() {
		Connection con = null;
		
		try{
			registerDriver();
			con = DriverManager.getConnection(DB_URL);
			Statement stmt = con.createStatement();
			stmt.executeUpdate("create table employee(id integer, name char(30))");
			stmt.close();
			
		}catch(SQLException e){
			throw new RuntimeException(e);
		}finally{
			if(con != null){
				try{
					con.close();
				}catch(SQLException e){
					
				}
			}
		}
		
	}

	@Override
	public void insertEmployee(Employee employee) {
		Connection con = null;
		
		try {
			registerDriver();
			con = DriverManager.getConnection(DB_URL);
			Statement stmt = con.createStatement();
			stmt.executeQuery("insert into employee values("+employee.getId()+","+employee.getName()+")");
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally{
			if(con != null){
				try{
					con.close();
				}catch(SQLException e){
					
				}
			}
		}
		
	}

	
}
