package entity;

import java.io.Serializable;

public class GameDetail implements Serializable {
    private int gameId;
    private boolean is1setGotByA;
    private boolean is2setGotByA;
    private boolean is3setGotByA;
    private int points1setA;
    private int points2setA;
    private int points3setA;
    private int points1setB;
    private int points2setB;
    private int points3setB;
    private int normalTimeoutRemainA;
    private int technicalTimeoutRemainA;
    private int normalTimeoutRemainB;
    private int technicalTimeoutRemainB;
    private int playerSubstitutionRemainA;
    private int playerSubstitutionRemainB;
    private boolean is3setCourtChanged;
    private int sumPoints;

    public GameDetail() {
    }

    public GameDetail(int gameId, boolean is1setGotByA, boolean is2setGotByA, boolean is3setGotByA, int points1setA, int points2setA, int points3setA, int points1setB, int points2setB, int points3setB, int normalTimeoutRemainA, int technicalTimeoutRemainA, int normalTimeoutRemainB, int technicalTimeoutRemainB, int playerSubstitutionRemainA, int playerSubstitutionRemainB, boolean is3setCourtChanged, int sumPoints) {
        this.gameId = gameId;
        this.is1setGotByA = is1setGotByA;
        this.is2setGotByA = is2setGotByA;
        this.is3setGotByA = is3setGotByA;
        this.points1setA = points1setA;
        this.points2setA = points2setA;
        this.points3setA = points3setA;
        this.points1setB = points1setB;
        this.points2setB = points2setB;
        this.points3setB = points3setB;
        this.normalTimeoutRemainA = normalTimeoutRemainA;
        this.technicalTimeoutRemainA = technicalTimeoutRemainA;
        this.normalTimeoutRemainB = normalTimeoutRemainB;
        this.technicalTimeoutRemainB = technicalTimeoutRemainB;
        this.playerSubstitutionRemainA = playerSubstitutionRemainA;
        this.playerSubstitutionRemainB = playerSubstitutionRemainB;
        this.is3setCourtChanged = is3setCourtChanged;
        this.sumPoints = sumPoints;
    }

    public int getGameId() { return gameId; }
    public void setGameId(int gameId) { this.gameId = gameId; }

    public boolean getIs1setGotByA() { return is1setGotByA; }
    public void setIs1setGotByA(boolean is1setGotByA) { this.is1setGotByA = is1setGotByA; }

    public boolean getIs2setGotByA() { return is2setGotByA; }
    public void setIs2setGotByA(boolean is2setGotByA) { this.is2setGotByA = is2setGotByA; }

    public boolean getIs3setGotByA() { return is3setGotByA; }
    public void setIs3setGotByA(boolean is3setGotByA) { this.is3setGotByA = is3setGotByA; }

    public int getPoints1setA() { return points1setA; }
    public void setPoints1setA(int points1setA) { this.points1setA = points1setA; }

    public int getPoints2setA() { return points2setA; }
    public void setPoints2setA(int points2setA) { this.points2setA = points2setA; }

    public int getPoints3setA() { return points3setA; }
    public void setPoints3setA(int points3setA) { this.points3setA = points3setA; }

    public int getPoints1setB() { return points1setB; }
    public void setPoints1setB(int points1setB) { this.points1setB = points1setB; }

    public int getPoints2setB() { return points2setB; }
    public void setPoints2setB(int points2setB) { this.points2setB = points2setB; }

    public int getPoints3setB() { return points3setB; }
    public void setPoints3setB(int points3setB) { this.points3setB = points3setB; }

    public int getNormalTimeoutRemainA() { return normalTimeoutRemainA; }
    public void setNormalTimeoutRemainA(int normalTimeoutRemainA) { this.normalTimeoutRemainA = normalTimeoutRemainA; }

    public int getTechnicalTimeoutRemainA() { return technicalTimeoutRemainA; }
    public void setTechnicalTimeoutRemainA(int technicalTimeoutRemainA) { this.technicalTimeoutRemainA = technicalTimeoutRemainA; }

    public int getNormalTimeoutRemainB() { return normalTimeoutRemainB; }
    public void setNormalTimeoutRemainB(int normalTimeoutRemainB) { this.normalTimeoutRemainB = normalTimeoutRemainB; }

    public int getTechnicalTimeoutRemainB() { return technicalTimeoutRemainB; }
    public void setTechnicalTimeoutRemainB(int technicalTimeoutRemainB) { this.technicalTimeoutRemainB = technicalTimeoutRemainB; }

    public int getPlayerSubstitutionRemainA() { return playerSubstitutionRemainA; }
    public void setPlayerSubstitutionRemainA(int playerSubstitutionRemainA) { this.playerSubstitutionRemainA = playerSubstitutionRemainA; }

    public int getPlayerSubstitutionRemainB() { return playerSubstitutionRemainB; }
    public void setPlayerSubstitutionRemainB(int playerSubstitutionRemainB) { this.playerSubstitutionRemainB = playerSubstitutionRemainB; }

    public boolean getIs3setCourtChanged() { return is3setCourtChanged; }
    public void setIs3setCourtChanged(boolean is3setCourtChanged) { this.is3setCourtChanged = is3setCourtChanged; }

	public int getSumPoints() { return sumPoints; }
	public void setSumPoints(int sumPoints) { this.sumPoints = sumPoints; }

    // 追加のgetter/setter
    // Aレグのポイントを配列にして返す
    public int[] getPointsA() {
        return new int[] { points1setA, points2setA, points3setA };
    }
    // Bレグのポイントを配列にして返す
    public int[] getPointsB() {
        return new int[] { points1setB, points2setB, points3setB };
    }
}