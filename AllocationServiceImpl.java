package com.avega.training.serviceImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.avega.training.dao.AllocationDao;
import com.avega.training.daoImpl.AllocationDaoImpl;
import com.avega.training.exception.EmployeeNotFountException;
import com.avega.training.pojo.Allocation;
import com.avega.training.service.AllocationService;
import com.avega.training.util.DBUtil;

public class AllocationServiceImpl implements AllocationService {

	Connection connection = DBUtil.getConnection();
	private AllocationDao allocationDao = null;

	public AllocationServiceImpl() {
		this.allocationDao = new AllocationDaoImpl();
	}

	@Override
	public List<Allocation> findAllAllocations() throws SQLException {
		return allocationDao.getAllAllocations(connection);
	}

	@Override
	public Allocation findAllocationById(String allocationId) throws SQLException {
		return allocationDao.getAllocationById(connection, allocationId);
	}

	@Override
	public boolean addAllocation(Allocation allocation) throws SQLException {
		return allocationDao.addAllocation(connection, allocation);
	}

	@Override
	public boolean deleteAllocation(String allocationId) throws SQLException {
		return allocationDao.deleteAllocation(connection, allocationId);
	}

	@Override
	public boolean updateAllocation(Allocation allocation) throws SQLException {
		return allocationDao.updateAllocation(connection, allocation);
	}

	@Override
	public Map<String, List<String>> findAllScoreAndStatusByEmployee(String empId) throws SQLException, EmployeeNotFountException {
		Map<String, List<String>> details = new HashMap<>();
		allocationDao.getAllAllocations(connection).forEach(allocation -> {
			List<String> scoreAndStatus = new ArrayList<>();
			if (allocation.getEmployeeId().equalsIgnoreCase(empId)) {
				scoreAndStatus.add(Integer.toString(allocation.getScore()));
				scoreAndStatus.add(allocation.getStatus());
				details.put(allocation.getTrainingId(), scoreAndStatus);

			} 

		});
		
		if(details.isEmpty()) {
			throw new EmployeeNotFountException("Employee not found in the database!");
		}
		
		return details;
	}

}
