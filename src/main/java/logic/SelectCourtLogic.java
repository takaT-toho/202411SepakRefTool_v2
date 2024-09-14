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
			throw new JudgeSystemException("データベースシステムエラーが発生しました。" + e.getMessage());
		} finally {
			try {
				if (con != null) {
					con.close();
				} 
			} catch (SQLException e) {
				throw new JudgeSystemException("データベースシステムエラーが発生しました。");
			}
		}
		
		return court;
	}

}
