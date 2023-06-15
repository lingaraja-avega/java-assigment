package com.avega.training.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.avega.training.pojo.Allocation;

public interface AllocationDao {

	List<Allocation> getAllAllocations(Connection connection) throws SQLException;

	Allocation getAllocationById(Connection connection, String allocationId) throws SQLException;

	boolean addAllocation(Connection connection, Allocation allocation) throws SQLException;

	boolean deleteAllocation(Connection connection, String allocationId) throws SQLException;

	boolean updateAllocation(Connection connection, Allocation allocation) throws SQLException;
}
