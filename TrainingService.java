package com.avega.training.service;

import java.sql.SQLException;
import java.util.List;

import com.avega.training.exception.SkillNotFountException;
import com.avega.training.exception.TrainingNotFoundException;
import com.avega.training.pojo.Training;

public interface TrainingService {

	List<Training> findAllTrainings() throws SQLException;

	Training findTrainingById(String trainingId) throws SQLException;

	boolean addTraining(Training training) throws SQLException;

	boolean deleteTraining(String trainingId) throws SQLException;

	boolean updateTraining(Training training) throws SQLException;

	List<Training> findTrainingBySkillInFeb2023(String skillId) throws SQLException, SkillNotFountException;

	List<Training> findTrainingByTrainnerId(String trainnerId) throws SQLException, TrainingNotFoundException;

	void writeToExcelCompletedTrainingThatMonth() throws SQLException;

}
