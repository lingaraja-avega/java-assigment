package com.avega.training.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.avega.training.dao.TrainingDao;
import com.avega.training.pojo.Training;

public class TrainingDaoImpl implements TrainingDao {
	Logger logger = Logger.getLogger(TrainingDaoImpl.class.getName());
	PreparedStatement ps = null;

	@Override
	public List<Training> getAllTrainings(Connection connection) throws SQLException {
		String query = "SELECT * FROM training";
		ps = connection.prepareStatement(query);
		List<Training> trainings = new ArrayList<>();
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Training training = new Training(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getDate(4),
					rs.getString(5), rs.getString(6), rs.getString(7));
			trainings.add(training);
		}
		return trainings;
	}

	@Override
	public Training getTrainingById(Connection connection, String trainingId) throws SQLException {
		String query = "SELECT * FROM training WHERE training_id = ?";
		ps = connection.prepareStatement(query);
		ps.setString(1, trainingId);
		ResultSet rs = ps.executeQuery();
		Training training = null;
		while (rs.next()) {
			training = new Training(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getDate(4), rs.getString(5),
					rs.getString(6), rs.getString(7));
		}
		return training;
	}

	@Override
	public boolean addTraining(Connection connection, Training training) throws SQLException {

		String query = "INSERT INTO training VALUES(?, ?, ?, ?, ?, ?, ?)";
		boolean isProduct = false;
		ps = connection.prepareStatement(query);
		ps.setString(1, training.getTrainingId());
		ps.setString(2, training.getTrainingDescription());
		ps.setDate(3, training.getStartDate());
		ps.setDate(4, training.getEndDate());
		ps.setString(5, training.getRequestorId());
		ps.setString(6, training.getTrainnerId());
		ps.setString(7, training.getSkillId());
		int count = ps.executeUpdate();
		if (count > 0) {
			isProduct = true;
			logger.info("Training added successfully");
		}

		return isProduct;
	
	}

	@Override
	public boolean deleteTraining(Connection connection, String trainingId) throws SQLException {
		String query = "DELETE FROM training WHERE training_id = ?";
		boolean isDelete = false;
		ps = connection.prepareStatement(query);
		ps.setString(1, trainingId);
		int count = ps.executeUpdate();
		if (count > 0) {
			logger.info("Training deleted successfully");
			isDelete = true;
		}
		return isDelete;

	
		
	}

	@Override
	public boolean updateTraining(Connection connection, Training training) throws SQLException {
		String query = "UPDATE training SET training_description = ?, start_date = ?, "
				+ "end_date = ?, requestor_id = ?, trainner_id = ?, skill_id = ? WHERE training_id = ?";
		ps = connection.prepareStatement(query);
		boolean isUpdate = false;
		ps.setString(1, training.getTrainingDescription());
		ps.setDate(2, training.getStartDate());
		ps.setDate(3, training.getEndDate());
		ps.setString(4, training.getRequestorId());
		ps.setString(5, training.getTrainnerId());
		ps.setString(6, training.getSkillId());
		ps.setString(7, training.getTrainingId());
		int count = ps.executeUpdate();
		if (count > 0) {
			isUpdate = true;
			logger.info("Training updated successfully");
		}
		return isUpdate;
	
	}
}
