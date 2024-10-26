// 2800
package logic;

import java.sql.Connection;
import java.sql.SQLException;

import common.JudgeBusinessException;
import common.JudgeSystemException;
import dao.ConnectionManager;
import dao.GameDAO;

public class UpdateSignInfoInGameLogic {
	public boolean updateSignInfoInGame(int gameId, String sign) throws JudgeBusinessException, JudgeSystemException {
		Connection con = null;
		
		try {
			con = ConnectionManager.getConnectionManager().getConnection();
			
			GameDAO dao = new GameDAO(con);
			boolean isSucceeded = dao.updateSignInfoInGame(gameId, sign);
			
			if (isSucceeded == false) {
				throw new JudgeBusinessException("署名が未入力です。");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new JudgeSystemException("データベースシステムエラーが発生しました。(28001)");
		} finally {
			try {
				if (con != null) {
					con.close();
				} 
			} catch (SQLException e) {
				e.printStackTrace();
				throw new JudgeSystemException("データベースシステムエラーが発生しました。(28002)");
			}
		}
		
		return true;
	}
}
