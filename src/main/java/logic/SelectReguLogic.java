package logic;

import java.sql.Connection;
import java.sql.SQLException;

import common.JudgeBusinessException;
import common.JudgeSystemException;
import dao.ConnectionManager;
import dao.ReguDAO;
import entity.Regu;

public class SelectReguLogic {
	public Regu findReguByReguId(int reguId) throws JudgeBusinessException, JudgeSystemException {
        Connection con = null;
		Regu regu = null;
		
        try {
            // データベースへの接続を取得
            con = ConnectionManager.getConnectionManager().getConnection();

            // ReguDAOの生成
            ReguDAO dao = new ReguDAO(con);
            // ReguDAOのメソッドを実行
            regu = dao.findReguByReguId(reguId);
            // null チェック
            if (regu == null) {
                throw new JudgeBusinessException("レグ情報が取得できませんでした");
            }

        } catch (SQLException e) {
            throw new JudgeSystemException("システムエラーが発生しました");
        }
         finally {
            try {
                // データベースへの接続をクローズ
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                throw new JudgeSystemException("システムエラーが発生しました");
            }
        }

		return regu;
	}

}
