package entity;

import java.io.Serializable;

public class PointJSON implements Serializable {
    private String gameId;
    private int setNum;
    private String type;
    private String firstDetail;
    private String secondDetail;
    private boolean isSequential;
    private boolean isAreguGot;

    private String buttonId;
    private int serialNumber;
    private boolean is3setCourtChanged;
    private boolean isSetFinished;
    private boolean isGameFinished;

    public PointJSON() {
    }

    public PointJSON(String gameId, int setNum, String type, String firstDetail, String secondDetail, boolean isSequential, boolean isAreguGot, 
        String buttonId, int serialNumber, boolean is3setCourtChanged, boolean isSetFinished, boolean isGameFinished) {
        this.gameId = gameId;
        this.setNum = setNum;
        this.type = type;
        this.firstDetail = firstDetail;
        this.secondDetail = secondDetail;
        this.isSequential = isSequential;
        this.isAreguGot = isAreguGot;
        this.buttonId = buttonId;
        this.serialNumber = serialNumber;
        this.is3setCourtChanged = is3setCourtChanged;
        this.isSetFinished = isSetFinished;
        this.isGameFinished = isGameFinished;
    }

    public String getGameId() { return gameId; }
    public void setGameId(String gameId) { this.gameId = gameId; }

    public int getSetNum() { return setNum; }
    public void setSetNum(int setNum) { this.setNum = setNum; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getFirstDetail() { return firstDetail; }
    public void setFirstDetail(String firstDetail) { this.firstDetail = firstDetail; }

    public String getSecondDetail() { return secondDetail; }
    public void setSecondDetail(String secondDetail) { this.secondDetail = secondDetail; }

    public boolean getIsSequential() { return isSequential; }
    public void setIsSequential(boolean isSequential) { this.isSequential = isSequential; }

    public boolean getIsAreguGot() { return isAreguGot; }
    public void setIsAreguGot(boolean isAreguGot) { this.isAreguGot = isAreguGot; }

    public String getButtonId() { return buttonId; }
    public void setButtonId(String buttonId) { this.buttonId = buttonId; }

    public int getSerialNumber() { return serialNumber; }
    public void setSerialNumber(int serialNumber) { this.serialNumber = serialNumber; }

    public boolean getIs3setCourtChanged() { return is3setCourtChanged; }
    public void setIs3setCourtChanged(boolean is3setCourtChanged) { this.is3setCourtChanged = is3setCourtChanged; }

    public boolean getIsSetFinished() { return isSetFinished; }
    public void setIsSetFinished(boolean isSetFinished) { this.isSetFinished = isSetFinished; }

    public boolean getIsGameFinished() { return isGameFinished; }
    public void setIsGameFinished(boolean isGameFinished) { this.isGameFinished = isGameFinished; }
}
