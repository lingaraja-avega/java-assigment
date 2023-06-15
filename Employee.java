package com.avega.training.pojo;

import java.sql.Date;
import java.util.Objects;

public class Employee {

	private String employeeId;
	private String employeeName;
	private Date doj;
	private String designation;
	private String department;
	private int skills;

	public Employee() {
	}

	public Employee(String employeeId, String employeeName, Date doj, String designation, String department,
			int skills) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.doj = doj;
		this.designation = designation;
		this.department = department;
		this.skills = skills;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getSkills() {
		return skills;
	}

	public void setSkills(int skills) {
		this.skills = skills;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", doj=" + doj
				+ ", designation=" + designation + ", department=" + department + ", skills=" + skills + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(employeeId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(employeeId, other.employeeId);
	}

}
