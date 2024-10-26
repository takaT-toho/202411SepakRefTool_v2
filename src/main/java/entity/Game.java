package entity;
import java.io.Serializable;

public class Game implements Serializable {
	private int gameId;
	private String name;
	private String pass;
	private String round;
	private String time;
	private int courtId; // FK
	private int areguId; // FK
	private int breguId; // FK
	private int mainJudgeReguId; // FK
	private int subJudgeReguId; // FK
	private int setNow;
	private int nextGameIdWinner;
	private int nextGameIdLoser;
	private boolean isStarted;
	private boolean isFinished;
	private boolean isFin1Set;
	private boolean isFin2Set;
	private boolean isFin3Set;
	private int setNumGotByA;
	private int setNumGotByB;
	private String mainJudgeSign;
	private int winner; // FK
	private int loser; // FK
	
	public Game() {}	
	// 変数ありのコンストラクト
	public Game(int gameId, String name, String pass, String round, String time,
			int courtId, int areguId, int breguId, int mainJudgeReguId, int subJudgeReguId,
			int setNow, int nextGameIdWinner, int nextGameIdLoser,
			boolean isStarted, boolean isFinished, boolean isFin1Set, boolean isFin2Set, boolean isFin3Set,
			int setNumGotByA, int setNumGotByB, String mainJudgeSign) {
		this.gameId = gameId;
		this.name = name;
		this.pass = pass;
		this.round = round;
		this.time = time;
		this.courtId = courtId;
		this.areguId = areguId;
		this.breguId = breguId;
		this.mainJudgeReguId = mainJudgeReguId;
		this.subJudgeReguId = subJudgeReguId;
		this.setNow = setNow;
		this.nextGameIdWinner = nextGameIdWinner;
		this.nextGameIdLoser = nextGameIdLoser;
		this.isStarted = isStarted;
		this.isFinished = isFinished;
		this.isFin1Set = isFin1Set;
		this.isFin2Set = isFin2Set;
		this.isFin3Set = isFin3Set;
		this.setNumGotByA = setNumGotByA;
		this.setNumGotByB = setNumGotByB;
		this.mainJudgeSign = mainJudgeSign;
	}
	
	public Game(int gameId, String name, String pass, String round, String time,
			int courtId, int areguId, int breguId, int mainJudgeReguId, int subJudgeReguId,
			int setNow, int nextGameIdWinner, int nextGameIdLoser,
			boolean isStarted, boolean isFinished, boolean isFin1Set, boolean isFin2Set, boolean isFin3Set,
			int setNumGotByA, int setNumGotByB, String mainJudgeSign, int winner, int loser) {
		this.gameId = gameId;
		this.name = name;
		this.pass = pass;
		this.round = round;
		this.time = time;
		this.courtId = courtId;
		this.areguId = areguId;
		this.breguId = breguId;
		this.mainJudgeReguId = mainJudgeReguId;
		this.subJudgeReguId = subJudgeReguId;
		this.setNow = setNow;
		this.nextGameIdWinner = nextGameIdWinner;
		this.nextGameIdLoser = nextGameIdLoser;
		this.isStarted = isStarted;
		this.isFinished = isFinished;
		this.isFin1Set = isFin1Set;
		this.isFin2Set = isFin2Set;
		this.isFin3Set = isFin3Set;
		this.setNumGotByA = setNumGotByA;
		this.setNumGotByB = setNumGotByB;
		this.mainJudgeSign = mainJudgeSign;
		this.winner = winner;
		this.loser = loser;
	}
	
	// setter getter
	public int getGameId() {
		return gameId;
	}
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getRound() {
		return round;
	}
	public void setRound(String round) {
		this.round = round;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getCourtId() {
		return courtId;
	}
	public void setCourtId(int courtId) {
		this.courtId = courtId;
	}
	public int getAreguId() {
		return areguId;
	}
	public void setAreguId(int areguId) {
		this.areguId = areguId;
	}
	public int getBreguId() {
		return breguId;
	}
	public void setBreguId(int breguId) {
		this.breguId = breguId;
	}
	public int getMainJudgeReguId() {
		return mainJudgeReguId;
	}
	public void setMainJudgeReguId(int mainJudgeReguId) {
		this.mainJudgeReguId = mainJudgeReguId;
	}
	public int getSubJudgeReguId() {
		return subJudgeReguId;
	}
	public void setSubJudgeReguId(int subJudgeReguId) {
		this.subJudgeReguId = subJudgeReguId;
	}
	public int getSetNow() {
		return setNow;
	}
	public void setSetNow(int setNow) {
		this.setNow = setNow;
	}
	public int getNextGameIdWinner() {
		return nextGameIdWinner;
	}
	public void setNextGameIdWinner(int nextGameIdWinner) {
		this.nextGameIdWinner = nextGameIdWinner;
	}
	public int getNextGameIdLoser() {
		return nextGameIdLoser;
	}
	public void setNextGameIdLoser(int nextGameIdLoser) {
		this.nextGameIdLoser = nextGameIdLoser;
	}
	public boolean getIsStarted() {
		return isStarted;
	}
	public void setIsStarted(boolean isStarted) {
		this.isStarted = isStarted;
	}
	public boolean getIsFinished() {
		return isFinished;
	}
	public void setIsFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}
	public boolean getIsFin1Set() {
		return isFin1Set;
	}
	public void setIsFin1Set(boolean isFin1Set) {
		this.isFin1Set = isFin1Set;
	}
	public boolean getIsFin2Set() {
		return isFin2Set;
	}
	public void setIsFin2Set(boolean isFin2Set) {
		this.isFin2Set = isFin2Set;
	}
	public boolean getIsFin3Set() {
		return isFin3Set;
	}
	public void setIsFin3Set(boolean isFin3Set) {
		this.isFin3Set = isFin3Set;
	}
	public int getSetNumGotByA() {
		return setNumGotByA;
	}
	public void setSetNumGotByA(int setNumGotByA) {
		this.setNumGotByA = setNumGotByA;
	}
	public int getSetNumGotByB() {
		return setNumGotByB;
	}
	public void setSetNumGotByB(int setNumGotByB) {
		this.setNumGotByB = setNumGotByB;
	}
	public String getMainJudgeSign() {
		return mainJudgeSign;
	}
	public void setMainJudgeSign(String mainJudgeSign) {
		this.mainJudgeSign = mainJudgeSign;
	}
	public int getWinner() {
		return winner;
	}
	public void setWinner(int winner) {
		this.winner = winner;
	}
	public int getLoser() {
		return loser;
	}
	public void setLoser(int loser) {
		this.loser = loser;
	}
	
	// その他のメソッド
	public String getStatus() {
		if (this.isFinished == true) {
			return "isFinished";
		} else if (this.isStarted == true) {
			return "isStarted";
		} else {
			return "canLogin";
		}
	}

}


