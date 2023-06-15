package com.avega.training.serviceImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.avega.training.dao.AllocationDao;
import com.avega.training.dao.EmployeeDao;
import com.avega.training.daoImpl.AllocationDaoImpl;
import com.avega.training.daoImpl.EmployeeDaoImpl;
import com.avega.training.exception.TrainingNotFoundException;
import com.avega.training.pojo.Employee;
import com.avega.training.service.EmployeeService;
import com.avega.training.util.DBUtil;

public class EmployeeServiceImpl implements EmployeeService {

	Connection connection = DBUtil.getConnection();
	private EmployeeDao employeeDao = null;
	private AllocationDao allocationDao = null;

	public EmployeeServiceImpl() {
		this.employeeDao = new EmployeeDaoImpl();
		this.allocationDao = new AllocationDaoImpl();
	}

	@Override
	public List<Employee> findAllEmployees() throws SQLException {
		return employeeDao.getAllEmployees(connection);
	}

	@Override
	public Employee findEmployeeById(String employeeId) throws SQLException {
		return employeeDao.getEmployeeById(connection, employeeId);
	}

	@Override
	public boolean addEmployee(Employee employee) throws SQLException {
		return employeeDao.addEmployee(connection, employee);
	}

	@Override
	public boolean deleteEmployee(String employeeId) throws SQLException {
		return employeeDao.deleteEmployee(connection, employeeId);
	}

	@Override
	public boolean updateEmployee(Employee employee) throws SQLException {
		return employeeDao.updateEmployee(connection, employee);
	}

	@Override
	public List<Employee> getAllEmployeesByTraining(String trainingId) throws SQLException, TrainingNotFoundException {
		List<Employee> employees = new ArrayList<>();
		allocationDao.getAllAllocations(connection).forEach(allocation -> {
			if (allocation.getTrainingId().equalsIgnoreCase(trainingId)) {
				try {
					employees.add(employeeDao.getEmployeeById(connection, allocation.getEmployeeId()));
				} catch (SQLException e) {
					e.printStackTrace();
				}

			} 

		});
		
		if(employees.isEmpty()) {
			throw new TrainingNotFoundException("Trainner not found in the database");
		}
		
		return employees;
	}

	@Override
	public List<Employee> detailsEmployeesByTrainingId(String trainingId) throws SQLException {
		List<Employee> employees = new ArrayList<>();
		allocationDao.getAllAllocations(connection).forEach(allocation -> {
			if (allocation.getTrainingId().equalsIgnoreCase(trainingId)) {
				try {
					employees.add(employeeDao.getEmployeeById(connection, allocation.getEmployeeId()));
				} catch (SQLException e) {
					e.printStackTrace();
				}

			} else
				try {
					throw new TrainingNotFoundException("Training not found in the database");
				} catch (Exception e2) {
					e2.printStackTrace();
				}
		});
		return employees;
	}

}
