package com.avega.training.pojo;

import java.util.Objects;

public class Allocation {
	private String allocationId;
	private String trainingId;
	private String employeeId;
	private String status;
	private int score;
	private String remarks;

	public Allocation() {
	}

	public Allocation(String allocationId, String trainingId, String employeeId, String status, int score,
			String remarks) {
		super();
		this.allocationId = allocationId;
		this.trainingId = trainingId;
		this.employeeId = employeeId;
		this.status = status;
		this.score = score;
		this.remarks = remarks;
	}

	public String getAllocationId() {
		return allocationId;
	}

	public void setAllocationId(String allocationId) {
		this.allocationId = allocationId;
	}

	public String getTrainingId() {
		return trainingId;
	}

	public void setTrainingId(String trainingId) {
		this.trainingId = trainingId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "Allocation [allocationId=" + allocationId + ", trainingId=" + trainingId + ", employeeId=" + employeeId
				+ ", status=" + status + ", score=" + score + ", remarks=" + remarks + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(allocationId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Allocation other = (Allocation) obj;
		return Objects.equals(allocationId, other.allocationId);
	}

}
