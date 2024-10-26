// 1600
package logic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JudgeBusinessException;
import common.JudgeSystemException;
import dao.ConnectionManager;
import dao.PlayerDAO;
import entity.Player;

public class SelectPlayerLogic {
	public ArrayList<Player> findAllPlayersByReguId(int reguId) throws JudgeBusinessException, JudgeSystemException {
        Connection con = null;
		ArrayList<Player> playerList = null;
		
        try {
            con = ConnectionManager.getConnectionManager().getConnection();

            PlayerDAO dao = new PlayerDAO(con);
            playerList = dao.findAllPlayersByReguId(reguId);

            // サイズチェック
            if (playerList.size() == 0) {
                throw new JudgeBusinessException("選手情報がありません");
            }


        } catch (SQLException e) {
            e.printStackTrace();
            throw new JudgeSystemException("データベースシステムエラーが発生しました。(16001)");
        }finally {
            try {
                if (con != null) {
                    con.close();
                } 
            } catch (SQLException e) {
                e.printStackTrace();
                throw new JudgeSystemException("データベースシステムエラーが発生しました。(16002)");
            }
        }

		return playerList;
	}

    public Player findPlayerByName(String name) throws JudgeBusinessException, JudgeSystemException {
        Connection con = null;
		Player player = null;
		
        try {
            con = ConnectionManager.getConnectionManager().getConnection();

            PlayerDAO dao = new PlayerDAO(con);
            player = dao.findPlayerByName(name);

            // サイズチェック
            if (player == null) {
                throw new JudgeBusinessException("選手情報がありません");
            }


        } catch (SQLException e) {
            e.printStackTrace();
            throw new JudgeSystemException("データベースシステムエラーが発生しました。(16003)");
        }finally {
            try {
                if (con != null) {
                    con.close();
                } 
            } catch (SQLException e) {
                e.printStackTrace();
                throw new JudgeSystemException("データベースシステムエラーが発生しました。(16004)");
            }
        }

		return player;
	}

}