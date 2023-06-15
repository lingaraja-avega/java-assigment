package com.avega.training.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.avega.training.dao.SkillDao;
import com.avega.training.pojo.Skill;

public class SkillDaoImpl implements SkillDao {
	PreparedStatement ps = null;
	Logger logger = Logger.getLogger(SkillDaoImpl.class.getName());

	@Override
	public List<Skill> getAllSkills(Connection connection) throws SQLException {
		String query = "SELECT * FROM skill";
		ps = connection.prepareStatement(query);
		List<Skill> skills = new ArrayList<>();
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Skill skill = new Skill(rs.getString(1), rs.getString(2));
			skills.add(skill);
		}
		return skills;
	}

	@Override
	public Skill getSkillById(Connection connection, String skillId) throws SQLException {
		String query = "SELECT * FROM skill WHERE skill_id = ?";
		ps = connection.prepareStatement(query);
		ps.setString(1, skillId);
		ResultSet rs = ps.executeQuery();
		Skill skill = null;
		while (rs.next()) {
			skill = new Skill(rs.getString(1), rs.getString(2));
		}
		return skill;

	}

	@Override
	public boolean addSkill(Connection connection, Skill skill) throws SQLException {
		String query = "INSERT INTO skill VALUES(?, ?)";
		boolean isProduct = false;
		ps = connection.prepareStatement(query);
		ps.setString(1, skill.getSkillId());
		ps.setString(2, skill.getSkillDescription());
		int count = ps.executeUpdate();
		if (count > 0) {
			isProduct = true;
			logger.info("Skill added successfully");
		}
		return isProduct;

	}

	@Override
	public boolean deleteSkill(Connection connection, String skillId) throws SQLException {
		String query = "DELETE FROM skill WHERE skill_id = ?";
		boolean isDelete = false;
		ps = connection.prepareStatement(query);
		ps.setString(1, skillId);
		int count = ps.executeUpdate();
		if (count > 0) {
			logger.info("Skill deleted successfully");
			isDelete = true;
		}
		return isDelete;
	}

	@Override
	public boolean updateSkill(Connection connection, Skill skill) throws SQLException {

		String query = "UPDATE skill SET skill_description = ? WHERE skill_id = ?";
		ps = connection.prepareStatement(query);
		boolean isUpdate = false;
		ps.setString(1, skill.getSkillDescription());
		ps.setString(2, skill.getSkillId());
		int count = ps.executeUpdate();
		if (count > 0) {
			isUpdate = true;
			logger.info("Skill updated successfully");
		}
		return isUpdate;

	}

}
