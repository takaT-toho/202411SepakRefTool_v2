package logic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JudgeBusinessException;
import common.JudgeSystemException;
import dao.ConnectionManager;
import dao.GameEventHistoryDAO;
import entity.GameEventHistory;

public class SelectGameEventHistoryLogic {
    public ArrayList<GameEventHistory> findGameEventHistoriesByGameId(int gameId) throws JudgeBusinessException, JudgeSystemException {
        Connection con = null;
		ArrayList<GameEventHistory> array_ = new ArrayList<>();
		
		try {
			con = ConnectionManager.getConnectionManager().getConnection();
			
			GameEventHistoryDAO dao = new GameEventHistoryDAO(con);
			array_ = dao.findGameEventHistoriesByGameId(gameId);			
			if (array_.isEmpty()) {
				throw new JudgeBusinessException("試合履歴が見つかりませんでした。");
			}

		} catch (SQLException e) {
			throw new JudgeSystemException("データベースシステムエラーが発生しました。");
		} finally {
			try {
				if (con != null) {
					con.close();
				} 
			} catch (SQLException e) {
				throw new JudgeSystemException("データベースシステムエラーが発生しました。");
			}
		}
		
		return array_;
    }
    
}
