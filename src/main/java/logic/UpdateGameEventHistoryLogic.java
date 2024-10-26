// 2400
package logic;

import java.sql.Connection;
import java.sql.SQLException;

import common.JudgeBusinessException;
import common.JudgeSystemException;
import dao.ConnectionManager;
import dao.GameEventHistoryDAO;
import entity.GameEventHistory;

public class UpdateGameEventHistoryLogic  {

    public boolean insertGameEventHistory(GameEventHistory geh) throws JudgeBusinessException, JudgeSystemException {
		Connection con = null;
		boolean result = false;
		try {
			con = ConnectionManager.getConnectionManager().getConnection();
			GameEventHistoryDAO dao = new GameEventHistoryDAO(con);
			boolean res = dao.insertGameEventHistory(geh);		
			if (res == false) {
				throw new JudgeBusinessException("データの更新に失敗しました");
			}
            result = true;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new JudgeSystemException("データベースシステムエラーが発生しました。(24001)");
		} finally {
			try {
				if (con != null) {
					con.close();
				} 
			} catch (SQLException e) {
				e.printStackTrace();
				throw new JudgeSystemException("データベースシステムエラーが発生しました。(24002)");
			}
		}
		
		return result;
	}

    public GameEventHistory deleteGameEventHistory(int gameId) throws JudgeBusinessException, JudgeSystemException {
		Connection con = null;
		GameEventHistory geh = null;
		try {
			con = ConnectionManager.getConnectionManager().getConnection();
			GameEventHistoryDAO dao = new GameEventHistoryDAO(con);
			geh = dao.selectLatestGameEventHistory(gameId);
			if (geh == null) {
				throw new JudgeBusinessException("データが見つかりませんでした");
			}
			boolean res = dao.deleteGameEventHistory(geh);
			if (res == false) {
				throw new JudgeBusinessException("データの更新に失敗しました");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new JudgeSystemException("データベースシステムエラーが発生しました。(24003)");
		} finally {
			try {
				if (con != null) {
					con.close();
				} 
			} catch (SQLException e) {
				e.printStackTrace();
				throw new JudgeSystemException("データベースシステムエラーが発生しました。(24004)");
			}
		}
		
		return geh;
	}
}
