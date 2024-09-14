package entity;
import java.io.Serializable;

public class Court implements Serializable {
	private int courtId;
	private String name;
	private String pass;
	private String sessionId;
	private long lastUpdate;
	
	public Court() {}
	// Court() 変数ありのコンストラクタ
	public Court(int courtId, String name, String pass, String sessionId, long lastUpdate) {
		this.courtId = courtId;
		this.name = name;
		this.pass = pass;
		this.sessionId = sessionId;
		this.lastUpdate = lastUpdate;
	}
	public Court(int courtId, String name, String pass, String sessionId, boolean isControlled) {
		this.courtId = courtId;
		this.name = name;
		this.pass = pass;
		this.sessionId = sessionId;
		this.lastUpdate = 0;
	}
	
	// getter setter
	public int getCourtId() { return courtId; }
	public void setCourtId(int courtId) { this.courtId = courtId; }
	
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	public String getPass() { return pass; }
	public void setPass(String pass) { this.pass = pass; }
	
	public String getSessionId() { return sessionId; }
	public void setSessionId(String sessionId) { this.sessionId = sessionId; }

	public long getLastUpdate() { return lastUpdate; }
	public void setLastUpdate(long lastUpdate) { this.lastUpdate = lastUpdate; }

}
// CREATE TABLE COURT (
// COURTID INT PRIMARY KEY,
// NAME VARCHAR (10) NOT NULL,
// PASS VARCHAR (10) NOT NULL,
// SESSIONID VARCHAR (256),
// LASTUPDATE BIGINT
// );

// INSERT INTO COURT VALUES (1, 'A', '12341', NULL, 0);
// INSERT INTO COURT VALUES (2, 'B', '12342', NULL, 0);