package entity;

import java.io.Serializable;

public class LoginMap implements Serializable {
    private int courtId;
    private String sessionId;
    private long lastUpdate;

    public LoginMap() {}

    public LoginMap(int courtId, String sessionId, long lastUpdate) {
        this.courtId = courtId;
        this.sessionId = sessionId;
        this.lastUpdate = lastUpdate;
    }

    public int getCourtId() {
        return courtId;
    }
    public void setCourtId(int courtId) {
        this.courtId = courtId;
    }

    public String getSessionId() {
        return sessionId;
    }
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public long getLastUpdate() {
        return lastUpdate;
    }
    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

}
