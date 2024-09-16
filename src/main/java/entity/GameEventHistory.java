package entity;

import java.io.Serializable;
import java.util.Date;

public class GameEventHistory implements Serializable {
	private int id;
	private int gameId;
	private int setNum;
	private String type;
	private String firstDetail;
	private String secondDetail;
	private boolean isSequential;
	private boolean isAreguGot;
	private Date createAt;
	
	// コンストラクタ
	public GameEventHistory() {}
	public GameEventHistory(int id, int gameId, int setNum, String type, String firstDetail, String secondDetail,
			boolean isSequential, boolean isAreguGot, Date createAt) {
		this.id = id;
		this.gameId = gameId;
		this.setNum = setNum;
		this.type = type;
		this.firstDetail = firstDetail;
		this.secondDetail = secondDetail;
		this.isSequential = isSequential;
		this.isAreguGot = isAreguGot;
		this.createAt = createAt;
	}
	public GameEventHistory(int gameId, int setNum, String type, String firstDetail, String secondDetail,
			boolean isSequential, boolean isAreguGot) {
		this.gameId = gameId;
		this.setNum = setNum;
		this.type = type;
		this.firstDetail = firstDetail;
		this.secondDetail = secondDetail;
		this.isSequential = isSequential;
		this.isAreguGot = isAreguGot;
	}

	
	// setter getter
	public int getId() { return id;}
	public void setId(int id) {	this.id = id; }
	
	public int getGameId() { return gameId; }
	public void setGameId(int gameId) { this.gameId = gameId; }

	public int getSetNum() { return setNum; }
	public void setSetNum(int setNum) { this.setNum = setNum; }

	public String getType() { return type; }
	public void setType(String type) { this.type = type; }

	public String getFirstDetail() { return firstDetail; }
	public void setFirstDetail(String firstDetail) { this.firstDetail = firstDetail; }

	public String getSecondDetail() { return secondDetail; }
	public void setSecondDetail(String secondDetail) { this.secondDetail = secondDetail; }

	public boolean getIsSequential() {return isSequential; }
	public void setIsSequential(boolean isSequential) { this.isSequential = isSequential;}

	public boolean getIsAreguGot() {return isAreguGot; }
	public void setIsAreguGot(boolean isAreguGot) { this.isAreguGot = isAreguGot;}

	public Date getCreateAt() { return createAt; }
	public void setCreateAt(Date createAt) { this.createAt = createAt; }
}
