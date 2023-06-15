package com.avega.training.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.avega.training.pojo.Skill;

public interface SkillDao {
	
	List<Skill> getAllSkills(Connection connection) throws SQLException;

	Skill getSkillById(Connection connection, String skillId) throws SQLException;

	boolean addSkill(Connection connection, Skill skill) throws SQLException;

	boolean deleteSkill(Connection connection, String skillId) throws SQLException;

	boolean updateSkill(Connection connection, Skill skill) throws SQLException;

}
