package entity;

import java.io.Serializable;

public class Player implements Serializable {
	private int playerId;
	private int reguId;
	private int playerMasterId;
	private String position;
	private int orders;
	private String name; // FK
	private String katakana; // FK
	
	public Player() {}

	public Player(int playerId, int reguId, int playerMasterId, String position, int orders, String name, String katakana) {
		this.playerId = playerId;
		this.reguId = reguId;
		this.playerMasterId = playerMasterId;
		this.position = position;
		this.orders = orders;
		this.name = name;
		this.katakana = katakana;
	}

	public Player(int playerId, int reguId, int playerMasterId, String position, int orders) {
		this.playerId = playerId;
		this.reguId = reguId;
		this.playerMasterId = playerMasterId;
		this.position = position;
		this.orders = orders;
	}

	public int getPlayerId() { return playerId; }
	public void setPlayerId(int playerId) { this.playerId = playerId; }
	
	public int getReguId() { return reguId; }
	public void setReguId(int reguId) { this.reguId = reguId; }

	public int getPlayerMasterId() { return playerMasterId; }
	public void setPlayerMasterId(int playerMasterId) { this.playerMasterId = playerMasterId; }

	public String getPosition() { return position; }
	public void setPosition(String position) { this.position = position; }

	public int getOrders() { return orders; }
	public void setOrders(int orders) { this.orders = orders; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public String getKatakana() { return katakana; }
	public void setKatakana(String katakana) { this.katakana = katakana; }
	
	
	

}