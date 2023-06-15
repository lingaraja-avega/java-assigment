package com.avega.training.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.avega.training.pojo.Training;

public interface TrainingDao {
	
	List<Training> getAllTrainings(Connection connection) throws SQLException;

	Training getTrainingById(Connection connection, String trainingId) throws SQLException;

	boolean addTraining(Connection connection, Training training) throws SQLException;

	boolean deleteTraining(Connection connection, String trainingId) throws SQLException;

	boolean updateTraining(Connection connection, Training training) throws SQLException;

}
