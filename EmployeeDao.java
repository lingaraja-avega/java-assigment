package com.avega.training.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.avega.training.pojo.Employee;

public interface EmployeeDao {
	
	List<Employee> getAllEmployees(Connection connection) throws SQLException;

	Employee getEmployeeById(Connection connection, String employeeId) throws SQLException;

	boolean addEmployee(Connection connection, Employee employee) throws SQLException;

	boolean deleteEmployee(Connection connection, String employeeId) throws SQLException;

	boolean updateEmployee(Connection connection, Employee employee) throws SQLException;

}
