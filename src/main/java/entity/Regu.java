package entity;

import java.io.Serializable;

public class Regu implements Serializable {
	private int reguId;
	private int teamId;
	private String name;
	private String abbreviation;
	private String department;
	
	public Regu() {}

	public Regu(int reguId, int teamId, String name, String abbreviation, String department) {
		this.reguId = reguId;
		this.teamId = teamId;
		this.name = name;
		this.abbreviation = abbreviation;
		this.department = department;
	}

	public int getReguId() {
		return reguId;
	}
	public void setReguId(int reguId) {
		this.reguId = reguId;
	}

	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	
}





