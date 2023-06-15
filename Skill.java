package com.avega.training.pojo;

import java.util.Objects;

public class Skill {

	private String skillId;
	private String skillDescription;

	public Skill() {
	}

	public Skill(String skillId, String skillDescription) {
		super();
		this.skillId = skillId;
		this.skillDescription = skillDescription;
	}

	public String getSkillId() {
		return skillId;
	}

	public void setSkillId(String skillId) {
		this.skillId = skillId;
	}

	public String getSkillDescription() {
		return skillDescription;
	}

	public void setSkillDescription(String skillDescription) {
		this.skillDescription = skillDescription;
	}

	@Override
	public String toString() {
		return "Skill [skillId=" + skillId + ", skillDescription=" + skillDescription + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(skillId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Skill other = (Skill) obj;
		return Objects.equals(skillId, other.skillId);
	}

}
