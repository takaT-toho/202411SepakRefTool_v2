// 1100
package logic;

import java.sql.Connection;
import java.sql.SQLException;

import common.JudgeBusinessException;
import common.JudgeSystemException;
import dao.ConnectionManager;
import dao.CourtDAO;
import entity.Court;

public class SelectCourtLogic {
    public Court selectCourtByCourtName(String courtName) throws JudgeBusinessException, JudgeSystemException {
		Connection con = null;
		Court court = null;
		
		try {
			con = ConnectionManager.getConnectionManager().getConnection();
			
			CourtDAO dao = new CourtDAO(con);
			court = dao.selectCourtByCourtName(courtName);
			
			if (court == null) {
				throw new JudgeBusinessException("「" + courtName + "」というコートが見つかりません。");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new JudgeSystemException("データベースシステムエラーが発生しました。(11001)");
		} finally {
			try {
				if (con != null) {
					con.close();
				} 
			} catch (SQLException e) {
				e.printStackTrace();
				throw new JudgeSystemException("データベースシステムエラーが発生しました。(11002)");
			}
		}
		
		return court;
	}

	public Court selectCourtByCourtId(int courtId) throws JudgeBusinessException, JudgeSystemException {
		Connection con = null;
		Court court = null;
		
		try {
			con = ConnectionManager.getConnectionManager().getConnection();
			
			CourtDAO dao = new CourtDAO(con);
			court = dao.selectCourtByCourtId(courtId);
			
			if (court == null) {
				throw new JudgeBusinessException("「" + courtId + "」というコートが見つかりません。");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new JudgeSystemException("データベースシステムエラーが発生しました。(11003)");
		} finally {
			try {
				if (con != null) {
					con.close();
				} 
			} catch (SQLException e) {
				e.printStackTrace();
				throw new JudgeSystemException("データベースシステムエラーが発生しました。(11004)");
			}
		}
		
		return court;
	}



    public Court selectCourtByGameId(int gameId) throws JudgeBusinessException, JudgeSystemException {
		Connection con = null;
		Court court = null;
		
		try {
			con = ConnectionManager.getConnectionManager().getConnection();
			
			CourtDAO dao = new CourtDAO(con);
			court = dao.selectCourtByGameId(gameId);
			
			if (court == null) {
				throw new JudgeBusinessException("「" + gameId + "」に対応するコートが見つかりません。");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new JudgeSystemException("データベースシステムエラーが発生しました。(11005)");
		} finally {
			try {
				if (con != null) {
					con.close();
				} 
			} catch (SQLException e) {
				throw new JudgeSystemException("データベースシステムエラーが発生しました。(11006)");
			}
		}
		
		return court;
	}

}
