package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import entity.GameConfig;

public class GameConfigDAO {
	private Connection con;
	
	public GameConfigDAO(Connection con) {
		this.con = con;
	}

    public boolean updateGameConfig(GameConfig gameConfig) throws SQLException {
		String sql = "UPDATE gameConfig SET isAreguTossWin = ?, " +
            "isAreguFirstServe = ?, isAreguLeft = ? WHERE gameId = ?";
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setBoolean(1, gameConfig.getIsAreguTossWin());
			stmt.setBoolean(2, gameConfig.getIsAreguFirstServe());
			stmt.setBoolean(3, gameConfig.getIsAreguLeft());
            stmt.setInt(4, gameConfig.getGameId());
			
			int row = stmt.executeUpdate();
			if (row != 1) {
				return false;
			}
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return true;
	}

}
