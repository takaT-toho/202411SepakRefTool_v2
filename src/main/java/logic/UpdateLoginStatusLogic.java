// 2600
package logic;

import java.sql.Connection;
import java.sql.SQLException;

import common.JudgeBusinessException;
import common.JudgeSystemException;
import dao.ConnectionManager;
import dao.GameDAO;

public class UpdateLoginStatusLogic {
	public boolean execute(int gameId) throws JudgeBusinessException, JudgeSystemException {
		Connection con = null;
		boolean gRes = false;
		try {
			con = ConnectionManager.getConnectionManager().getConnection();
			GameDAO gDao = new GameDAO(con);
			gRes = gDao.updateGameIsStarted(gameId);		
			if (gRes == false) {
				throw new JudgeBusinessException("「" + gameId + "」というコートが見つかりません。");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new JudgeSystemException("データベースシステムエラーが発生しました。(26001)");
		} finally {
			try {
				if (con != null) {
					con.close();
				} 
			} catch (SQLException e) {
				e.printStackTrace();
				throw new JudgeSystemException("データベースシステムエラーが発生しました。(26002)");
			}
		}
		
		return gRes;
	}

}
