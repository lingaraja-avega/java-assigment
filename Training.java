package com.avega.training.pojo;

import java.sql.Date;
import java.util.Objects;

public class Training {

	private String trainingId;
	private String trainingDescription;
	private Date startDate;
	private Date endDate;
	private String requestorId;
	private String trainnerId;
	private String skillId;

	public Training() {
	}

	public Training(String trainingId, String trainingDescription, Date startDate, Date endDate, String requestorId,
			String trainnerId, String skillId) {
		super();
		this.trainingId = trainingId;
		this.trainingDescription = trainingDescription;
		this.startDate = startDate;
		this.endDate = endDate;
		this.requestorId = requestorId;
		this.trainnerId = trainnerId;
		this.skillId = skillId;
	}

	public String getTrainingId() {
		return trainingId;
	}

	public void setTrainingId(String trainingId) {
		this.trainingId = trainingId;
	}

	public String getTrainingDescription() {
		return trainingDescription;
	}

	public void setTrainingDescription(String trainingDescription) {
		this.trainingDescription = trainingDescription;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getRequestorId() {
		return requestorId;
	}

	public void setRequestorId(String requestorId) {
		this.requestorId = requestorId;
	}

	public String getTrainnerId() {
		return trainnerId;
	}

	public void setTrainnerId(String trainnerId) {
		this.trainnerId = trainnerId;
	}

	public String getSkillId() {
		return skillId;
	}

	public void setSkillId(String skillId) {
		this.skillId = skillId;
	}

	@Override
	public String toString() {
		return "Training [trainingId=" + trainingId + ", trainingDescription=" + trainingDescription + ", startDate="
				+ startDate + ", endDate=" + endDate + ", requestorId=" + requestorId + ", trainnerId=" + trainnerId
				+ ", skillId=" + skillId + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(trainingId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Training other = (Training) obj;
		return Objects.equals(trainingId, other.trainingId);
	}
}
