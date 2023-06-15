package com.avega.training.serviceImpl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.avega.training.dao.EmployeeDao;
import com.avega.training.dao.SkillDao;
import com.avega.training.dao.TrainingDao;
import com.avega.training.daoImpl.EmployeeDaoImpl;
import com.avega.training.daoImpl.SkillDaoImpl;
import com.avega.training.daoImpl.TrainingDaoImpl;
import com.avega.training.exception.SkillNotFountException;
import com.avega.training.exception.TrainingNotFoundException;
import com.avega.training.pojo.Employee;
import com.avega.training.pojo.Skill;
import com.avega.training.pojo.Training;
import com.avega.training.service.TrainingService;
import com.avega.training.util.DBUtil;

public class TrainingServiceImpl implements TrainingService {

	Logger logger = Logger.getLogger(TrainingServiceImpl.class.getName());
	Connection connection = DBUtil.getConnection();
	private TrainingDao trainingDao = null;
	private SkillDao skillDao = null;
	private EmployeeDao employeeDao = null;

	public TrainingServiceImpl() {
		this.trainingDao = new TrainingDaoImpl();
		this.skillDao = new SkillDaoImpl();
		this.employeeDao = new EmployeeDaoImpl();
	}

	@Override
	public List<Training> findAllTrainings() throws SQLException {
		return trainingDao.getAllTrainings(connection);
	}

	@Override
	public Training findTrainingById(String trainingId) throws SQLException {
		return trainingDao.getTrainingById(connection, trainingId);
	}

	@Override
	public boolean addTraining(Training training) throws SQLException {
		return trainingDao.addTraining(connection, training);
	}

	@Override
	public boolean deleteTraining(String trainingId) throws SQLException {
		return trainingDao.deleteTraining(connection, trainingId);
	}

	@Override
	public boolean updateTraining(Training training) throws SQLException {
		return trainingDao.updateTraining(connection, training);
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Training> findTrainingBySkillInFeb2023(String skillId) throws SQLException, SkillNotFountException {
		List<Training> trainings = new ArrayList<>();
		trainingDao.getAllTrainings(connection).forEach(training -> {
			if (training.getSkillId().equalsIgnoreCase(skillId))
				if (training.getStartDate().getMonth() == 1 && training.getStartDate().getYear() == 123)
					trainings.add(training);
		});
		
		if(trainings.isEmpty()) {
			throw new SkillNotFountException("Skill not found in the database");
		}
		
		return trainings;
	}

	@Override
	public List<Training> findTrainingByTrainnerId(String trainnerId) throws SQLException, TrainingNotFoundException {
		List<Training> trainings = new ArrayList<>();
		trainingDao.getAllTrainings(connection).forEach(training -> {
			if (training.getTrainnerId().equalsIgnoreCase(trainnerId))
				trainings.add(training);
		});
		
		if(trainings.isEmpty())
			throw new TrainingNotFoundException("Trainner not found in the database");
		
		return trainings;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void writeToExcelCompletedTrainingThatMonth() throws SQLException {
		LocalDate currentdate = LocalDate.now();
		trainingDao.getAllTrainings(connection).forEach(training -> {
			if ((training.getEndDate().getMonth() + 1) == currentdate.getMonthValue()) {
				try {
					uploadDatabaseToExcel("D:\\Workspace\\Test\\assignment.xlsx", training);
				} catch (IOException | SQLException e) {
					e.printStackTrace();
				}
			}
		});

	}

	public void uploadDatabaseToExcel(String filePath, Training training) throws IOException, SQLException {
		FileInputStream fn = new FileInputStream(filePath);
		Workbook workbook = new XSSFWorkbook(fn);
		Sheet sheet = workbook.getSheetAt(0);
		int count = sheet.getLastRowNum();
		Row row = sheet.createRow(count + 1);
		row.createCell(0).setCellValue(training.getTrainingId());
		row.createCell(1).setCellValue(training.getTrainingDescription());
		row.createCell(2).setCellValue(training.getStartDate());
		row.createCell(3).setCellValue(training.getEndDate());
		Employee emp = employeeDao.getEmployeeById(connection, training.getTrainnerId());
		row.createCell(4).setCellValue(emp.getEmployeeName());
		Skill skill = skillDao.getSkillById(connection, training.getSkillId());
		row.createCell(5).setCellValue(skill.getSkillDescription());
		fn.close();
		try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
			workbook.write(fileOut);
			fileOut.flush();
			logger.info("Uploaded successfully in the excel sheet");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
