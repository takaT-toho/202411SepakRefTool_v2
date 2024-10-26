// 1200
package logic;

import java.sql.Connection;
import java.sql.SQLException;

import common.JudgeBusinessException;
import common.JudgeSystemException;
import dao.ConnectionManager;
import dao.GameConfigDAO;
import entity.GameConfig;

public class SelectGameConfigLogic {
    public GameConfig selectGameConfig(int gameId) throws JudgeBusinessException, JudgeSystemException {
		Connection con = null;
		GameConfig gameConfig = null;
		
		try {
			con = ConnectionManager.getConnectionManager().getConnection();
			
			GameConfigDAO dao = new GameConfigDAO(con);
			gameConfig = dao.selectGameConfigByGameId(gameId);
			
			if (gameConfig == null) {
				throw new JudgeBusinessException("試合が見つかりません。");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new JudgeSystemException("データベースシステムエラーが発生しました。(12001)");
		} finally {
			try {
				if (con != null) {
					con.close();
				} 
			} catch (SQLException e) {
				e.printStackTrace();
				throw new JudgeSystemException("データベースシステムエラーが発生しました。(12002");
			}
		}
		
		return gameConfig;
    }
}
