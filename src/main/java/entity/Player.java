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
// 選手ID命名規則
// TEAMID + 連番
//
// CREATE TABLE PLAYER (
// PLAYERID INT PRIMARY KEY,
// REGUID INT NOT NULL,
// FOREIGN KEY (REGUID) REFERENCES REGU(REGUID) ON DELETE CASCADE,
// PLAYERMASTERID INT NOT NULL,
// FOREIGN KEY (PLAYERMASTERID) REFERENCES PLAYERMASTER(PLAYERMASTERID) ON DELETE CASCADE,
// POSITION VARCHAR (10) NOT NULL,
// ORDERS INT NOT NULL
// );
//
// INSERT INTO PLAYER VALUES (100011, 10000, 1000001, 'サーバー', 1);
// INSERT INTO PLAYER VALUES (100012, 10000, 1000002, 'アタッカー', 2);
// INSERT INTO PLAYER VALUES (100013, 10000, 1000003, 'トサー', 3);
// INSERT INTO PLAYER VALUES (100021, 10001, 1000004, 'サーバー', 1);
// INSERT INTO PLAYER VALUES (100022, 10001, 1000005, 'アタッカー', 2);
// INSERT INTO PLAYER VALUES (100023, 10001, 1000006, 'トサー', 3);
// INSERT INTO PLAYER VALUES (100031, 10002, 1000007, 'サーバー', 1);
// INSERT INTO PLAYER VALUES (100032, 10002, 1000008, 'アタッカー', 2);
// INSERT INTO PLAYER VALUES (100033, 10002, 1000009, 'トサー', 3);
// INSERT INTO PLAYER VALUES (200001, 20000, 2000001, 'サーバー', 1);
// INSERT INTO PLAYER VALUES (200002, 20000, 2000002, 'アタッカー', 2);
// INSERT INTO PLAYER VALUES (200003, 20000, 2000003, 'トサー', 3);
// INSERT INTO PLAYER VALUES (200011, 20001, 2000004, 'サーバー', 1);
// INSERT INTO PLAYER VALUES (200012, 20001, 2000005, 'アタッカー', 2);
// INSERT INTO PLAYER VALUES (200013, 20001, 2000006, 'トサー', 3);
// INSERT INTO PLAYER VALUES (300001, 30000, 3000001, 'サーバー', 1);
// INSERT INTO PLAYER VALUES (300002, 30000, 3000002, 'アタッカー', 2);
// INSERT INTO PLAYER VALUES (300003, 30000, 3000003, 'トサー', 3);
// INSERT INTO PLAYER VALUES (400001, 40000, 4000001, 'サーバー', 1);
// INSERT INTO PLAYER VALUES (400002, 40000, 4000002, 'アタッカー', 2);
// INSERT INTO PLAYER VALUES (400003, 40000, 4000003, 'トサー', 3);
// INSERT INTO PLAYER VALUES (500001, 50000, 5000001, 'サーバー', 1);
// INSERT INTO PLAYER VALUES (500002, 50000, 5000002, 'アタッカー', 2);
// INSERT INTO PLAYER VALUES (500003, 50000, 5000003, 'トサー', 3);
// INSERT INTO PLAYER VALUES (600001, 60000, 6000001, 'サーバー', 1);
// INSERT INTO PLAYER VALUES (600002, 60000, 6000002, 'アタッカー', 2);
// INSERT INTO PLAYER VALUES (600003, 60000, 6000003, 'トサー', 3);
// INSERT INTO PLAYER VALUES (600004, 60000, 6000004, 'サーバー', 4);
// INSERT INTO PLAYER VALUES (600005, 60000, 6000005, 'アタッカー', 5);