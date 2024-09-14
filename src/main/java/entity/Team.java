package entity;

import java.io.Serializable;

public class Team implements Serializable {
	private int teamId;
	private String name;
	private String representative;
	private String email;
	
	public Team() {}
	
	public Team(int teamId, String name, String representative, String email) {
		this.teamId = teamId;
		this.name = name;
		this.representative = representative;
		this.email = email;
	}
	
	public int getTeamId() { return teamId; }
	public void setTeamId(int teamId) { this.teamId = teamId; }


	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public String getRepresentative() { return representative; }
	public void setRepresentative(String representative) { this.representative = representative; }
	
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }

}
// TEAMIDの命名規則
// 北海道：100番台
// 東北：200番台
// 関東：300番台
// 北陸・甲信越：400番台
// 東海：500番台
// 近畿：600番台
// 中国・四国：700番台
// 九州・沖縄：800番台

//CREATE TABLE TEAM (
//TEAMID INT PRIMARY KEY,
//NAME VARCHAR (20) NOT NULL,
//REPRESENTATIVE VARCHAR (20),
//EMAIL VARCHAR (256)
//)
//
//INSERT INTO TEAM VALUES (100, '北海道セパタクロー', '北海道太郎', 'xxxx@xxxxxx.com')
//INSERT INTO TEAM VALUES (200, '東北セパタクロー', '東北太郎', 'xxxx@xxxxxx.com')
//INSERT INTO TEAM VALUES (300, '関東セパタクロー', '関東太郎', 'xxxx@xxxxxx.com')
//INSERT INTO TEAM VALUES (400, '北陸甲信越セパタクロー', '北陸甲信', 'xxxx@xxxxxx.com')
//INSERT INTO TEAM VALUES (500, '東海セパタクロー', '東海太郎', 'xxxx@xxxxxx.com')
//INSERT INTO TEAM VALUES (600, '近畿セパタクロー', '近畿太郎', 'xxxx@xxxxxx.com')
//INSERT INTO TEAM VALUES (700, '中国四国セパタクロー', '中国四国', 'xxxx@xxxxxx.com')
//INSERT INTO TEAM VALUES (800, '九州沖縄セパタクロー', '九州沖縄', 'xxxx@xxxxxx.com')