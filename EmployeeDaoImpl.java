package com.avega.training.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.avega.training.dao.EmployeeDao;
import com.avega.training.pojo.Employee;

public class EmployeeDaoImpl implements EmployeeDao {
	Logger logger = Logger.getLogger(EmployeeDaoImpl.class.getName());
	PreparedStatement ps = null;

	@Override
	public List<Employee> getAllEmployees(Connection connection) throws SQLException {
		String query = "SELECT * FROM employee";
		ps = connection.prepareStatement(query);
		List<Employee> employees = new ArrayList<>();
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Employee employee = new Employee(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getString(4),
					rs.getString(5), rs.getInt(6));
			employees.add(employee);
		}
		return employees;

	}

	@Override
	public Employee getEmployeeById(Connection connection, String employeeId) throws SQLException {
		String query = "SELECT * FROM employee WHERE employee_id = ?";
		ps = connection.prepareStatement(query);
		ps.setString(1, employeeId);
		ResultSet rs = ps.executeQuery();
		Employee employee = null;
		while (rs.next()) {
			employee = new Employee(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getString(4), rs.getString(5),
					rs.getInt(6));
		}
		return employee;
	}

	@Override
	public boolean addEmployee(Connection connection, Employee employee) throws SQLException {
		String query = "INSERT INTO employee VALUES(?, ?, ?, ?, ?, ?)";
		boolean isProduct = false;
		ps = connection.prepareStatement(query);
		ps.setString(1, employee.getEmployeeId());
		ps.setString(2, employee.getEmployeeName());
		ps.setDate(3, employee.getDoj());
		ps.setString(4, employee.getDesignation());
		ps.setString(5, employee.getDepartment());
		ps.setInt(6, employee.getSkills());
		int count = ps.executeUpdate();
		if (count > 0) {
			isProduct = true;
			logger.info("Employee added successfully");
		}

		return isProduct;
	}

	@Override
	public boolean deleteEmployee(Connection connection, String employeeId) throws SQLException {
		String query = "DELETE FROM employee WHERE employee_id = ?";
		boolean isDelete = false;
		ps = connection.prepareStatement(query);
		ps.setString(1, employeeId);
		int count = ps.executeUpdate();
		if (count > 0) {
			logger.info("Employee deleted successfully");
			isDelete = true;
		}
		return isDelete;

	}

	@Override
	public boolean updateEmployee(Connection connection, Employee employee) throws SQLException {
		String query = "UPDATE employee SET employee_name = ?, doj = ?, "
				+ "designation = ?, department = ?, skills = ? WHERE employee_id = ?";
		ps = connection.prepareStatement(query);
		boolean isUpdate = false;
		ps.setString(1, employee.getEmployeeName());
		ps.setDate(2, employee.getDoj());
		ps.setString(3, employee.getDesignation());
		ps.setString(4, employee.getDepartment());
		ps.setInt(5, employee.getSkills());
		ps.setString(6, employee.getEmployeeId());
		int count = ps.executeUpdate();
		if (count > 0) {
			isUpdate = true;
			logger.info("Employee updated successfully");
		}
		return isUpdate;
	}

}
