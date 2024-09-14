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
// レグ名命名規則
// TEAMID+連番

//CREATE TABLE REGU (
//REGUID INT PRIMARY KEY,
//TEAMID INT NOT NULL,
//FOREIGN KEY (TEAMID) REFERENCES TEAM(TEAMID) ON DELETE CASCADE,
//NAME VARCHAR (10) NOT NULL,
//ABBREVIATION VARCHAR (10) NOT NULL,
//DEPARTMENT VARCHAR (10) NOT NULL
//)
//
//INSERT INTO REGU VALUES (10000, 100, '北北Aチーム', '北北A', 'アスリート')
//INSERT INTO REGU VALUES (10001, 100, '北北Bチーム', '北北B', 'アスリート')
//INSERT INTO REGU VALUES (10002, 100, '北北Cチーム', '北北C', 'エンジョイ')
//INSERT INTO REGU VALUES (20000, 200, '東東Aチーム', '東東A', 'アスリート')
//INSERT INTO REGU VALUES (20001, 200, '東東Bチーム', '東東B', 'アスリート')
//INSERT INTO REGU VALUES (30000, 300, '関関Aチーム', '関関A', 'アスリート')
//INSERT INTO REGU VALUES (40000, 400, '陸陸Aチーム', '陸陸A', 'アスリート')
//INSERT INTO REGU VALUES (50000, 500, '海海Aチーム', '海海A', 'エンジョイ')
// INSERT INTO REGU VALUES (60000, 600, '近々Aチーム', '近々A', 'エンジョイ')




