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