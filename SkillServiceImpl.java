package com.avega.training.serviceImpl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.avega.training.dao.SkillDao;
import com.avega.training.daoImpl.SkillDaoImpl;
import com.avega.training.pojo.Skill;
import com.avega.training.service.SkillService;
import com.avega.training.util.DBUtil;

public class SkillServiceImpl implements SkillService {
	PreparedStatement ps = null;
	Connection connection = DBUtil.getConnection();
	private SkillDao skillDao = null;
	Logger logger = Logger.getLogger(SkillServiceImpl.class.getName());

	public SkillServiceImpl() {
		this.skillDao = new SkillDaoImpl();
	}

	@Override
	public List<Skill> findAllSkills() throws SQLException {
		return skillDao.getAllSkills(connection);

	}

	@Override
	public Skill findSkillById(String skillId) throws SQLException {
		return skillDao.getSkillById(connection, skillId);
	}

	@Override
	public boolean addSkill(Skill skill) throws SQLException {
		return skillDao.addSkill(connection, skill);
	}

	@Override
	public boolean deleteSkill(String skillId) throws SQLException {
		return skillDao.deleteSkill(connection, skillId);
	}

	@Override
	public boolean updateSkill(Skill skill) throws SQLException {
		return skillDao.updateSkill(connection, skill);
	}

	@Override
	public void uploadExcelDataToDatabase() throws FileNotFoundException, IOException, SQLException {
		String updateQuery = "INSERT INTO skill VALUES(?, ?)";
		ps = connection.prepareStatement(updateQuery);
		List<Skill> skills = new ArrayList<>();
		String filePath = "D:\\Workspace\\Test\\data.xlsx";
		try (FileInputStream fileInput = new FileInputStream(filePath)) {
			Workbook workbook = new XSSFWorkbook(filePath);
			Sheet sheet = workbook.getSheet("skill");
			for (int i = 0; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				if (row != null) {
					Skill skill = new Skill();
					skill.setSkillId(row.getCell(0).getStringCellValue());
					skill.setSkillDescription(row.getCell(1).getStringCellValue());
					skills.add(skill);
				}
			}
			connection.setAutoCommit(false);
			skills.forEach(skill -> {
				try {
					ps.setString(1,skill.getSkillId());
					ps.setString(2, skill.getSkillDescription());
					ps.addBatch();

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			});
			int count = Arrays.asList(ps.executeBatch()).size();
			connection.commit();
			if (count > 0)
				logger.info("Excel Sheet Data uploaded successfully in Database");
			else
				logger.info("Uploading Excel Sheet to Databass is Failed");
		}

	}

}
