// 2100
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
			e.printStackTrace();
			throw new JudgeSystemException("データベースシステムエラーが発生しました。(21001)");
		} finally {
			try {
				if (con != null) {
					con.close();
				} 
			} catch (SQLException e) {
				e.printStackTrace();
				throw new JudgeSystemException("データベースシステムエラーが発生しました。(21002)");
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
			e.printStackTrace();
			throw new JudgeSystemException("データベースシステムエラーが発生しました。(21003)");
		} finally {
			try {
				if (con != null) {
					con.close();
				} 
			} catch (SQLException e) {
				e.printStackTrace();
				throw new JudgeSystemException("データベースシステムエラーが発生しました。(21004)");
			}
		}

		return res;
	}
}
