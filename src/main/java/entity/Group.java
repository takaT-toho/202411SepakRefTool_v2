package entity;

import java.io.Serializable;

public class Group implements Serializable {
	private int groupId;
	private String department;
	private String symbol;
	private boolean isFinished;
	
	public Group() {}
	
	// 変数ありのコンストラクタ
    public Group(int groupId, String department, String symbol, boolean isFinished) {
        this.groupId = groupId;
        this.department = department;
        this.symbol = symbol;
        this.isFinished = isFinished;
    }
	
	// getter setter
    public int getGroupId() { return groupId; }
    public void setGroupId(int groupId) { this.groupId = groupId; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getSymbol() { return symbol; }
    public void setSymbol(String symbol) { this.symbol = symbol; }

    public boolean getIsFinished() { return isFinished; }
    public void setIsFinished(boolean isFinished) { this.isFinished = isFinished; }
    

}
