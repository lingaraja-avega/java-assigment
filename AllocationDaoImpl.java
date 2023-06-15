package com.avega.training.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.avega.training.dao.AllocationDao;
import com.avega.training.pojo.Allocation;

public class AllocationDaoImpl implements AllocationDao {
	Logger logger = Logger.getLogger(AllocationDaoImpl.class.getName());
	PreparedStatement ps = null;

	@Override
	public List<Allocation> getAllAllocations(Connection connection) throws SQLException {
		String query = "SELECT * FROM allocation";
		ps = connection.prepareStatement(query);
		List<Allocation> allocations = new ArrayList<>();
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Allocation allocation = new Allocation(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
					rs.getInt(5), rs.getString(6));
			allocations.add(allocation);
		}
		return allocations;
	}

	@Override
	public Allocation getAllocationById(Connection connection, String allocationId) throws SQLException {
		String query = "SELECT * FROM allocation WHERE allocation_id = ?";
		ps = connection.prepareStatement(query);
		ps.setString(1, allocationId);
		ResultSet rs = ps.executeQuery();
		Allocation allocation = null;
		while (rs.next()) {
			allocation = new Allocation(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
					rs.getInt(5), rs.getString(6));
		}
		return allocation;
	}

	@Override
	public boolean addAllocation(Connection connection, Allocation allocation) throws SQLException {

		String query = "INSERT INTO allocation VALUES(?, ?, ?, ?, ?, ?)";
		boolean isProduct = false;
		ps = connection.prepareStatement(query);
		ps.setString(1, allocation.getAllocationId());
		ps.setString(2, allocation.getTrainingId());
		ps.setString(3, allocation.getEmployeeId());
		ps.setString(4, allocation.getStatus());
		ps.setInt(5, allocation.getScore());
		ps.setString(6, allocation.getRemarks());
		int count = ps.executeUpdate();
		if (count > 0) {
			isProduct = true;
			logger.info("Allocation added successfully");
		}

		return isProduct;

	}

	@Override
	public boolean deleteAllocation(Connection connection, String allocationId) throws SQLException {
		String query = "DELETE FROM allocation WHERE allocation_id = ?";
		boolean isDelete = false;
		ps = connection.prepareStatement(query);
		ps.setString(1, allocationId);
		int count = ps.executeUpdate();
		if (count > 0) {
			logger.info("Allocation deleted successfully");
			isDelete = true;
		}
		return isDelete;

	}

	@Override
	public boolean updateAllocation(Connection connection, Allocation allocation) throws SQLException {
		String query = "UPDATE allocation SET training_id = ?, employee_id = ?, "
				+ "status = ?, score = ?, remarks = ? WHERE allocation_id = ?";
		ps = connection.prepareStatement(query);
		boolean isUpdate = false;
		ps.setString(1, allocation.getTrainingId());
		ps.setString(2, allocation.getEmployeeId());
		ps.setString(3, allocation.getStatus());
		ps.setInt(4, allocation.getScore());
		ps.setString(5, allocation.getRemarks());
		ps.setString(6, allocation.getAllocationId());
		int count = ps.executeUpdate();
		if (count > 0) {
			isUpdate = true;
			logger.info("Allocation updated successfully");
		}
		return isUpdate;

	}

}
