package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.GameConfig;

public class GameConfigDAO {
	private Connection con;
	
	public GameConfigDAO(Connection con) {
		this.con = con;
	}

	public boolean updateGameConfig(GameConfig gameConfig) throws SQLException {
		String sql = "UPDATE gameConfig SET isAreguTossWin = ?, " +
			"isAreguFirstServe = ?, isAreguLeft = ?, " +
			"language = ?, maxScore1set = ?, maxScore2set = ?, " +
			"maxScore3set = ?, deuceStartScore1set = ?, " +
			"deuceStartScore2set = ?, deuceStartScore3set = ?, " +
			"deuceDifference1set = ?, deuceDifference2set = ?, " +
			"deuceDifference3set = ?, courtChangeScore = ?, " +
			"maxSet = ? WHERE gameId = ?";
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(sql);
			stmt.setBoolean(1, gameConfig.getIsAreguTossWin());
			stmt.setBoolean(2, gameConfig.getIsAreguFirstServe());
			stmt.setBoolean(3, gameConfig.getIsAreguLeft());
			stmt.setInt(4, gameConfig.getLanguage());
			stmt.setInt(5, gameConfig.getMaxScore1set());
			stmt.setInt(6, gameConfig.getMaxScore2set());
			stmt.setInt(7, gameConfig.getMaxScore3set());
			stmt.setInt(8, gameConfig.getDeuceStartScore1set());
			stmt.setInt(9, gameConfig.getDeuceStartScore2set());
			stmt.setInt(10, gameConfig.getDeuceStartScore3set());
			stmt.setInt(11, gameConfig.getDeuceDifference1set());
			stmt.setInt(12, gameConfig.getDeuceDifference2set());
			stmt.setInt(13, gameConfig.getDeuceDifference3set());
			stmt.setInt(14, gameConfig.getCourtChangeScore());
			stmt.setInt(15, gameConfig.getMaxSet());
			stmt.setInt(16, gameConfig.getGameId());

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

	public boolean updateBasicGameConfig(GameConfig gameConfig) throws SQLException {
		String sql = "UPDATE gameConfig SET isAreguTossWin = ?, " +
			"isAreguFirstServe = ?, isAreguLeft = ? " +
			"WHERE gameId = ?";
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

	public GameConfig selectGameConfigByGameId(int gameId) throws SQLException {
		String sql = "SELECT * FROM gameConfig WHERE gameId = ?";
		PreparedStatement stmt = null;
		ResultSet res = null;
		GameConfig gameConfig = null;

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, gameId);
			res = stmt.executeQuery();

			if (res.next()) {
				gameConfig = new GameConfig(
						res.getInt("gameId"),
						res.getString("gameType"),
						res.getString("gameRule"),
						res.getBoolean("isAreguTossWin"),
						res.getBoolean("isAreguFirstServe"),
						res.getBoolean("isAreguLeft"),
						res.getInt("language"),
						res.getInt("maxScore1set"),
						res.getInt("maxScore2set"),
						res.getInt("maxScore3set"),
						res.getInt("deuceStartScore1set"),
						res.getInt("deuceStartScore2set"),
						res.getInt("deuceStartScore3set"),
						res.getInt("deuceDifference1set"),
						res.getInt("deuceDifference2set"),
						res.getInt("deuceDifference3set"),
						res.getInt("courtChangeScore"),
						res.getInt("maxSet")
						);
			}

		} finally {
			if (res != null) {
				res.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
		return gameConfig;
	}
}
