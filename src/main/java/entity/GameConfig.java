package entity;

import java.io.Serializable;

public class GameConfig implements Serializable {
    private int gameId;
    private String gameType; // ダブルス、レグ、クワッド
    private String gameRule; // firstToGet, draw, drawGD, fullGame
    private boolean isAreguTossWin;
    private boolean isAreguFirstServe;
    private boolean isAreguLeft;

    private int language;
    private int maxScore1set;
    private int maxScore2set;
    private int maxScore3set;
    private int deuceStartScore1set;
    private int deuceStartScore2set;
    private int deuceStartScore3set;
    private int deuceDifference1set;
    private int deuceDifference2set;
    private int deuceDifference3set;
    private int courtChangeScore;
    private int maxSet;


    public GameConfig() {}

    public GameConfig(int gameId, String gameType, String gameRule, boolean isAreguTossWin, boolean isAreguFirstServe, boolean isAreguLeft, int language, int maxScore1set, int maxScore2set, int maxScore3set, int deuceStartScore1set, int deuceStartScore2set, int deuceStartScore3set, int deuceDifference1set, int deuceDifference2set, int deuceDifference3set, int courtChangeScore, int maxSet) {
        this.gameId = gameId;
        this.gameType = gameType;
        this.gameRule = gameRule;
        this.isAreguTossWin = isAreguTossWin;
        this.isAreguFirstServe = isAreguFirstServe;
        this.isAreguLeft = isAreguLeft;
        this.language = language;
        this.maxScore1set = maxScore1set;
        this.maxScore2set = maxScore2set;
        this.maxScore3set = maxScore3set;
        this.deuceStartScore1set = deuceStartScore1set;
        this.deuceStartScore2set = deuceStartScore2set;
        this.deuceStartScore3set = deuceStartScore3set;
        this.deuceDifference1set = deuceDifference1set;
        this.deuceDifference2set = deuceDifference2set;
        this.deuceDifference3set = deuceDifference3set;
        this.courtChangeScore = courtChangeScore;
        this.maxSet = maxSet;
    }

    public int getGameId() { return gameId; }    
    public void setGameId(int gameId) { this.gameId = gameId; }

    public String getGameType() { return gameType; }
    public void setGameType(String gameType) { this.gameType = gameType; }

    public String getGameRule() { return gameRule; }
    public void setGameRule(String gameRule) { this.gameRule = gameRule; }

    public boolean getIsAreguTossWin() { return isAreguTossWin; }
    public void setIsAreguTossWin(boolean isAreguTossWin) { this.isAreguTossWin = isAreguTossWin; }

    public boolean getIsAreguFirstServe() { return isAreguFirstServe; }
    public void setIsAreguFirstServe(boolean isAreguFirstServe) { this.isAreguFirstServe = isAreguFirstServe; }

    public boolean getIsAreguLeft() { return isAreguLeft; }
    public void setIsAreguLeft(boolean isAreguLeft) { this.isAreguLeft = isAreguLeft; }

    public int getLanguage() { return language; }
    public void setLanguage(int language) { this.language = language; }

    public int getMaxScore1set() { return maxScore1set; }
    public void setMaxScore1set(int maxScore1set) { this.maxScore1set = maxScore1set; }

    public int getMaxScore2set() { return maxScore2set; }
    public void setMaxScore2set(int maxScore2set) { this.maxScore2set = maxScore2set; }

    public int getMaxScore3set() { return maxScore3set; }
    public void setMaxScore3set(int maxScore3set) { this.maxScore3set = maxScore3set; }

    public int getDeuceStartScore1set() { return deuceStartScore1set; }
    public void setDeuceStartScore1set(int deuceStartScore1set) { this.deuceStartScore1set = deuceStartScore1set; }

    public int getDeuceStartScore2set() { return deuceStartScore2set; }
    public void setDeuceStartScore2set(int deuceStartScore2set) { this.deuceStartScore2set = deuceStartScore2set; }

    public int getDeuceStartScore3set() { return deuceStartScore3set; }
    public void setDeuceStartScore3set(int deuceStartScore3set) { this.deuceStartScore3set = deuceStartScore3set; }

    public int getDeuceDifference1set() { return deuceDifference1set; }
    public void setDeuceDifference1set(int deuceDifference1set) { this.deuceDifference1set = deuceDifference1set; }

    public int getDeuceDifference2set() { return deuceDifference2set; }
    public void setDeuceDifference2set(int deuceDifference2set) { this.deuceDifference2set = deuceDifference2set; }

    public int getDeuceDifference3set() { return deuceDifference3set; }
    public void setDeuceDifference3set(int deuceDifference3set) { this.deuceDifference3set = deuceDifference3set; }

    public int getCourtChangeScore() { return courtChangeScore; }
    public void setCourtChangeScore(int courtChangeScore) { this.courtChangeScore = courtChangeScore; }

    public int getMaxSet() { return maxSet; }
    public void setMaxSet(int maxSet) { this.maxSet = maxSet; }
}