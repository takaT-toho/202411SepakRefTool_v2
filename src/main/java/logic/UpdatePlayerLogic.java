package logic;

import java.sql.Connection;
import java.sql.SQLException;

import common.JudgeBusinessException;
import common.JudgeSystemException;
import dao.ConnectionManager;
import dao.PlayerDAO;

public class UpdatePlayerLogic {

    public boolean updatePlayerOrder(int playerId1, int order1, int playerId2, int order2) throws JudgeBusinessException, JudgeSystemException {
		Connection con = null;
		boolean res = false;
		try {
			con = ConnectionManager.getConnectionManager().getConnection();
            con.setAutoCommit(false);

			PlayerDAO dao = new PlayerDAO(con);
			boolean res1 = dao.updatePlayersOrder(playerId1, order2);
			boolean res2 = dao.updatePlayersOrder(playerId2, order1);
			if (res1 == false || res2 == false) {
				throw new JudgeBusinessException("更新に失敗しました");
			}

            con.commit();
            res = true;
		} catch (SQLException e) {
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException e1) {
                throw new JudgeSystemException("データベースシステムエラーが発生しました。");
            }
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
