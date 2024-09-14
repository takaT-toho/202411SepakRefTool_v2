package logic;

import java.sql.Connection;
import java.sql.SQLException;

import common.JudgeBusinessException;
import common.JudgeSystemException;
import dao.ConnectionManager;
import dao.CourtDAO;
import entity.LoginMap;

public class UpdateCourtLogic {

    public boolean updateCourtLoginInfo(LoginMap loginMap) throws JudgeBusinessException, JudgeSystemException {
        Connection con = null;
        boolean res = false;

        try {
			con = ConnectionManager.getConnectionManager().getConnection();
			CourtDAO dao = new CourtDAO(con);
			boolean result = dao.updateCourtLoginInfo(loginMap);
			
			if (result == false) {
				throw new JudgeBusinessException("データベースの更新に失敗しました。");
			}

            res = true;
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

        return res;
    }

	public boolean initCourtSessionId(int courtId) throws JudgeBusinessException, JudgeSystemException {
		Connection con = null;
		boolean res = false;

		try {
			con = ConnectionManager.getConnectionManager().getConnection();
			CourtDAO dao = new CourtDAO(con);
			boolean result = dao.initCourtSessionId(courtId);
			
			if (result == false) {
				throw new JudgeBusinessException("データベースの更新に失敗しました。");
			}

			res = true;
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

		return res;
	}
}
