package com.avega.training.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.avega.training.pojo.Skill;

public interface SkillService {

	List<Skill> findAllSkills() throws SQLException;

	Skill findSkillById(String skillId) throws SQLException;

	boolean addSkill(Skill skill) throws SQLException;

	boolean deleteSkill(String skillId) throws SQLException;

	boolean updateSkill(Skill skill) throws SQLException;
	
	void uploadExcelDataToDatabase() throws FileNotFoundException, IOException, SQLException;

}
