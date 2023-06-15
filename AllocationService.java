package com.avega.training.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.avega.training.exception.EmployeeNotFountException;
import com.avega.training.pojo.Allocation;

public interface AllocationService {

	List<Allocation> findAllAllocations() throws SQLException;

	Allocation findAllocationById(String allocationId) throws SQLException;

	boolean addAllocation(Allocation allocation) throws SQLException;

	boolean deleteAllocation(String allocationId) throws SQLException;

	boolean updateAllocation(Allocation allocation) throws SQLException;

	Map<String, List<String>> findAllScoreAndStatusByEmployee(String empId) throws SQLException, EmployeeNotFountException;
	
	

}
