package com.avega.training.service;

import java.sql.SQLException;
import java.util.List;

import com.avega.training.exception.TrainingNotFoundException;
import com.avega.training.pojo.Employee;
import com.avega.training.pojo.Training;

public interface EmployeeService {

	List<Employee> findAllEmployees() throws SQLException;

	Employee findEmployeeById(String employeeId) throws SQLException;

	boolean addEmployee(Employee employee) throws SQLException;

	boolean deleteEmployee(String employeeId) throws SQLException;

	boolean updateEmployee(Employee employee) throws SQLException;

	List<Employee> getAllEmployeesByTraining(String trainingId) throws SQLException, TrainingNotFoundException;

	public List<Employee> detailsEmployeesByTrainingId(String trainingId) throws SQLException;

}
