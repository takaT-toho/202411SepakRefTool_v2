// 1500
package logic;

import java.sql.Connection;
import java.sql.SQLException;

import common.JudgeBusinessException;
import common.JudgeSystemException;
import dao.ConnectionManager;
import dao.GameDAO;
import entity.Game;

public class SelectGameLogic {
    public Game selectGameByGameId(int gameId) throws JudgeBusinessException, JudgeSystemException {
		Connection con = null;
		Game game = null;
		
		try {
			con = ConnectionManager.getConnectionManager().getConnection();
			
			GameDAO dao = new GameDAO(con);
			game = dao.selectGameByGameId(gameId);
			
			if (game == null) {
				throw new JudgeBusinessException("「" + gameId + "」という試合が見つかりません。");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new JudgeSystemException("データベースシステムエラーが発生しました。(15001)");
		} finally {
			try {
				if (con != null) {
					con.close();
				} 
			} catch (SQLException e) {
				e.printStackTrace();
				throw new JudgeSystemException("データベースシステムエラーが発生しました。(15002)");
			}
		}
		
		return game;
	}

    public Game selectLatestGameByCourtName(String courtName) throws JudgeBusinessException, JudgeSystemException {
		Connection con = null;
		Game game = null;
		
		try {
			con = ConnectionManager.getConnectionManager().getConnection();
			
			GameDAO dao = new GameDAO(con);
			game = dao.selectLatestGameByCourtName(courtName);
			
			if (game == null) {
				throw new JudgeBusinessException("「" + courtName + "」というコートが見つかりません。");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new JudgeSystemException("データベースシステムエラーが発生しました。(15003)");
		} finally {
			try {
				if (con != null) {
					con.close();
				} 
			} catch (SQLException e) {
				e.printStackTrace();
				throw new JudgeSystemException("データベースシステムエラーが発生しました。(15004)");
			}
		}
		
		return game;
	}
}
