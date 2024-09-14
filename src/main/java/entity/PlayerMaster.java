package entity;

import java.io.Serializable;

public class PlayerMaster implements Serializable {
	private int playerMasterId;
	private int teamId;
	private String name;
	private String katakana;
	private String experience;
	
	public PlayerMaster() {}

	public PlayerMaster(int playerMasterId, int teamId, String name, String katakana, String experience) {
		this.playerMasterId = playerMasterId;
		this.teamId = teamId;
		this.name = name;
		this.katakana = katakana;
		this.experience = experience;
	}

	public int getPlayerMasterId() {
		return playerMasterId;
	}
	public void setPlayerMasterId(int playerMasterId) {
		this.playerMasterId = playerMasterId;
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

	public String getKatakana() {
		return katakana;
	}
	public void setKatakana(String katakana) {
		this.katakana = katakana;
	}

	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	
	

}

//CREATE TABLE PLAYERMASTER (
//PLAYERMASTERID INT PRIMARY KEY,
//TEAMID INT NOT NULL,
//FOREIGN KEY (TEAMID) REFERENCES TEAM(TEAMID) ON DELETE CASCADE,
//NAME VARCHAR (20) NOT NULL,
//KATAKANA VARCHAR (20) NOT NULL,
//EXPERIENCE VARCHAR (20) NOT NULL
//)
//
//INSERT INTO PLAYERMASTER VALUES (1000001, 100, '北海 太郎', 'ホッカイ タロウ', 'セパ歴10年');
//INSERT INTO PLAYERMASTER VALUES (1000002, 100, '北海 次郎', 'ホッカイ ジロウ', 'セパ歴8年');
//INSERT INTO PLAYERMASTER VALUES (1000003, 100, '北海 三郎', 'ホッカイ サブロウ', 'セパ歴6年');
//INSERT INTO PLAYERMASTER VALUES (1000004, 100, '北海 四郎', 'ホッカイ シロウ', 'セパ歴4年');
//INSERT INTO PLAYERMASTER VALUES (1000005, 100, '北海 五郎', 'ホッカイ ゴロウ', 'セパ歴2年');
//INSERT INTO PLAYERMASTER VALUES (1000006, 100, '北海 六郎', 'ホッカイ ロクロウ', 'セパ歴1年');
//INSERT INTO PLAYERMASTER VALUES (1000007, 100, '北海 七郎', 'ホッカイ シチロウ', 'セパ歴1年');
//INSERT INTO PLAYERMASTER VALUES (1000008, 100, '北海 八郎', 'ホッカイ ハチロウ', 'セパ歴1年');
//INSERT INTO PLAYERMASTER VALUES (1000009, 100, '北海 九郎', 'ホッカイ クロウ', 'セパ歴1年');
//INSERT INTO PLAYERMASTER VALUES (2000001, 200, '東北 太郎', 'トウホク タロウ', 'セパ歴10年');
//INSERT INTO PLAYERMASTER VALUES (2000002, 200, '東北 次郎', 'トウホク ジロウ', 'セパ歴7年');
//INSERT INTO PLAYERMASTER VALUES (2000003, 200, '東北 三郎', 'トウホク サブロウ', 'セパ歴5年');
//INSERT INTO PLAYERMASTER VALUES (2000004, 200, '東北 四郎', 'トウホク シロウ', 'セパ歴3年');
//INSERT INTO PLAYERMASTER VALUES (2000005, 200, '東北 五郎', 'トウホク ゴロウ', 'セパ歴2年');
//INSERT INTO PLAYERMASTER VALUES (2000006, 200, '東北 六郎', 'トウホク ロクロウ', 'セパ歴1年');
//INSERT INTO PLAYERMASTER VALUES (3000001, 300, '関東 太郎', 'カントウ タロウ', 'セパ歴10年');
//INSERT INTO PLAYERMASTER VALUES (3000002, 300, '関東 次郎', 'カントウ ジロウ', 'セパ歴7年');
//INSERT INTO PLAYERMASTER VALUES (3000003, 300, '関東 三郎', 'カントウ サブロウ', 'セパ歴4年');
//INSERT INTO PLAYERMASTER VALUES (4000001, 400, '北陸 甲信', 'ホクリク コウシン', 'セパ歴10年');
//INSERT INTO PLAYERMASTER VALUES (4000002, 400, '北陸 次郎', 'ホクリク ジロウ', 'セパ歴7年');
//INSERT INTO PLAYERMASTER VALUES (4000003, 400, '北陸 三郎', 'ホクリク サブロウ', 'セパ歴4年');
//INSERT INTO PLAYERMASTER VALUES (5000001, 500, '東海 太郎', 'トウカイ タロウ', 'セパ歴10年');
//INSERT INTO PLAYERMASTER VALUES (5000002, 500, '東海 次郎', 'トウカイ ジロウ', 'セパ歴7年');
//INSERT INTO PLAYERMASTER VALUES (5000003, 500, '東海 三郎', 'トウカイ サブロウ', 'セパ歴4年');
// INSERT INTO PLAYERMASTER VALUES (6000001, 600, '近畿 一郎', 'キンキ イチロウ', 'セパ歴1年');
// INSERT INTO PLAYERMASTER VALUES (6000002, 600, '近畿 二郎', 'キンキ ジロウ', 'セパ歴2年');
// INSERT INTO PLAYERMASTER VALUES (6000003, 600, '近畿 三郎', 'キンキ サブロウ', 'セパ歴3年');
// INSERT INTO PLAYERMASTER VALUES (6000004, 600, '近畿 四郎', 'キンキ シロウ', 'セパ歴4年');
// INSERT INTO PLAYERMASTER VALUES (6000005, 600, '近畿 五郎', 'キンキ シロウ', 'セパ歴5年');