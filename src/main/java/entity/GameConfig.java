package entity;

import java.io.Serializable;

public class GameConfig implements Serializable {
    private int gameId;
    private String gameType; // ダブルス、レグ、クワッド
    private String gameRule; // 2セット先取、2セット引き分け有り、2セット先取15点、2セット引き分け有り15点
    private boolean isAreguTossWin;
    private boolean isAreguFirstServe;
    private boolean isAreguLeft;

    public GameConfig() {}

    public GameConfig(int gameId, String gameType, String gameRule, boolean isAreguTossWin, boolean isAreguFirstServe, boolean isAreguLeft) {
        this.gameId = gameId;
        this.gameType = gameType;
        this.gameRule = gameRule;
        this.isAreguTossWin = isAreguTossWin;
        this.isAreguFirstServe = isAreguFirstServe;
        this.isAreguLeft = isAreguLeft;
    }

    public int getGameId() {
        return gameId;
    }    
    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getGameType() {
        return gameType;
    }
    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public String getGameRule() {
        return gameRule;
    }
    public void setGameRule(String gameRule) {
        this.gameRule = gameRule;
    }

    public boolean getIsAreguTossWin() {
        return isAreguTossWin;
    }
    public void setIsAreguTossWin(boolean isAreguTossWin) {
        this.isAreguTossWin = isAreguTossWin;
    }

    public boolean getIsAreguFirstServe() {
        return isAreguFirstServe;
    }
    public void setIsAreguFirstServe(boolean isAreguFirstServe) {
        this.isAreguFirstServe = isAreguFirstServe;
    }

    public boolean getIsAreguLeft() {
        return isAreguLeft;
    }

    public void setIsAreguLeft(boolean isAreguLeft) {
        this.isAreguLeft = isAreguLeft;
    }

}
// コメントでよいので、上のクラスのテーブルを作成するSQL文を記述してください。
// CREATE TABLE gameConfig (
//     gameId INT PRIMARY KEY,
//     gameType VARCHAR(10),
//     gameRule VARCHAR(40),
//     isAreguTossWin BOOLEAN,
//     isAreguFirstServe BOOLEAN,
//     isAreguLeft BOOLEAN
// );

// コメントでよいので、上のクラスのテーブルにサンプルデータを挿入するSQL文を記述してください
// INSERT INTO gameConfig VALUES (101, 'レグ', '2セット先取', true, true, true);
// INSERT INTO gameConfig VALUES (102, 'レグ', '2セット先取', true, true, true);