// 1300
package logic;

import java.sql.Connection;
import java.sql.SQLException;

import common.JudgeBusinessException;
import common.JudgeSystemException;
import dao.ConnectionManager;
import dao.GameDetailDAO;
import entity.GameDetail;

public class SelectGameDetailLogic {
    public GameDetail findGameDetailByGameId(int gameId) throws JudgeBusinessException, JudgeSystemException {
		Connection con = null;
		GameDetail gameDetail = null;
		
		try {
			con = ConnectionManager.getConnectionManager().getConnection();
			
			GameDetailDAO dao = new GameDetailDAO(con);
			gameDetail = dao.findGameDetailByGameId(gameId);			
			if (gameDetail == null) {
				throw new JudgeBusinessException("試合が見つかりませんでした。");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new JudgeSystemException("データベースシステムエラーが発生しました。(13001)");
		} finally {
			try {
				if (con != null) {
					con.close();
				} 
			} catch (SQLException e) {
				e.printStackTrace();
				throw new JudgeSystemException("データベースシステムエラーが発生しました。(13002)");
			}
		}
		
		return gameDetail;
	}
}
