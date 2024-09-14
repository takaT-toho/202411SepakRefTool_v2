package common;

import java.util.ArrayList;

// 業務例外クラス
public class JudgeBusinessException extends Exception {
	private ArrayList<String> msgList = new ArrayList<>();
	
	public JudgeBusinessException(String msg) {
		super(msg);
	}
	
	public JudgeBusinessException(ArrayList<String> msgList) {
		super();
		this.msgList = msgList;
	}
	
	public ArrayList<String> getMsgList() {
		return msgList;
	}
	
	public void setMsgList(ArrayList<String> msgList) {
		this.msgList = msgList;
	}
}
