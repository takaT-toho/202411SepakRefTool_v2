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
			throw new JudgeSystemException("データベースシステムエラーが発生しました。2");
		} finally {
			try {
				if (con != null) {
					con.close();
				} 
			} catch (SQLException e) {
				throw new JudgeSystemException("データベースシステムエラーが発生しました。3");
			}
		}
		
		return gameConfig;
    }
}
