package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Game;

public class GameDAO {
	private Connection con;
	
	public GameDAO(Connection con) {
		this.con = con;
	}
	
	public Game selectLatestGameByCourtName(String courtName) throws SQLException {
		String sql = "SELECT game.gameId, game.isFinished, game.isStarted FROM game "
				+ "INNER JOIN court ON game.courtId = court.courtId "
				+ "WHERE game.isFinished = false and game.courtId = "
				+ "(SELECT court.courtId FROM court WHERE court.name = ?) "
				+ "ORDER BY ROUND ASC LIMIT 1";
		PreparedStatement stmt = null;
		ResultSet res = null;
		Game game = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, courtName);
			res = stmt.executeQuery();
			
			if (res.next()) {
				game = new Game();
				game.setGameId(res.getInt("gameId"));
				game.setIsFinished(res.getBoolean("isFinished"));
				game.setIsStarted(res.getBoolean("isStarted"));
			}
			
		} finally {
			if (res != null) {
				res.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
		return game;
	}
	
	public Game selectGameByGameId(int gameId) throws SQLException {
		String sql = "SELECT * FROM game WHERE gameId = ?";
		PreparedStatement stmt = null;
		ResultSet res = null;
		Game game = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, gameId);
			res = stmt.executeQuery();
			
			if (res.next()) {
				game = new Game(
						res.getInt("gameId"),
						res.getString("name"),
						res.getString("pass"),
						res.getString("round"),
						res.getString("time"),
						res.getInt("courtId"),
						res.getInt("areguId"),
						res.getInt("breguId"),
						res.getInt("mainJudgeReguId"),
						res.getInt("subJudgeReguId"),
						res.getInt("setNow"),
						res.getInt("nextGameIdWinner"),
						res.getInt("nextGameIdLoser"),
						res.getBoolean("isStarted"),
						res.getBoolean("isFinished"),
						res.getBoolean("isFin1Set"),
						res.getBoolean("isFin2Set"),
						res.getBoolean("isFin3Set"),
						res.getInt("setNumGotByA"),
						res.getInt("setNumGotByB"),
						res.getString("mainJudgeSign")
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
		return game;
	}
	
	public boolean updateGameIsStarted(int gameId) throws SQLException {
		String sql = "UPDATE game SET isStarted = true WHERE gameId = ?";
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, gameId);
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

	public boolean updateSignInfoInGame(int gameId, String sign) throws SQLException {
		String sql = "UPDATE game SET mainJudgeSign = ? WHERE gameId = ?";
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, sign);
			stmt.setInt(2, gameId);
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

	public boolean update1SetInfoA(int gameId) throws SQLException {
		String sql = "UPDATE game SET setNow = setNow + 1, isFin1Set = true, setNumGotByA = setNumGotByA + 1 WHERE gameId = ?";
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, gameId);
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

	public boolean update2SetInfoA(int gameId) throws SQLException {
		String sql = "UPDATE game SET setNow = setNow + 1, isFin2Set = true, setNumGotByA = setNumGotByA + 1 WHERE gameId = ?";
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, gameId);
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

	public boolean update3SetInfoA(int gameId) throws SQLException {
		String sql = "UPDATE game SET setNow = setNow + 1, isFin3Set = true, setNumGotByA = setNumGotByA + 1 WHERE gameId = ?";
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, gameId);
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

	public boolean update1SetInfoB(int gameId) throws SQLException {
		String sql = "UPDATE game SET setNow = setNow + 1, isFin1Set = true, setNumGotByB = setNumGotByB + 1 WHERE gameId = ?";
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, gameId);
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

	public boolean update2SetInfoB(int gameId) throws SQLException {
		String sql = "UPDATE game SET setNow = setNow + 1, isFin2Set = true, setNumGotByB = setNumGotByB + 1 WHERE gameId = ?";
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, gameId);
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

	public boolean update3SetInfoB(int gameId) throws SQLException {
		String sql = "UPDATE game SET setNow = setNow + 1, isFin3Set = true, setNumGotByB = setNumGotByB + 1 WHERE gameId = ?";
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, gameId);
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

	public boolean updateIsGameFinished(int gameId, boolean isGameFinished) throws SQLException {
        String sql = "UPDATE game SET isFinished = ? WHERE gameId = ?";
		PreparedStatement stmt = null;
		boolean result = false;
		
		try {
			stmt = con.prepareStatement(sql);
            stmt.setBoolean(1, isGameFinished);
            stmt.setInt(2, gameId);
			int row = stmt.executeUpdate();
			
			if (row != 1) {
				throw new SQLException("更新対象のデータが存在しません");
			}
			result = true;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return result;
    }

	public boolean updateWinnerLoser(int gameId, int winner, int loser) throws SQLException {
        String sql = "UPDATE game SET winner = ?, loser = ? WHERE gameId = ?";
		PreparedStatement stmt = null;
		boolean result = false;
		
		try {
			stmt = con.prepareStatement(sql);
            stmt.setInt(1, winner);
            stmt.setInt(2, loser);
            stmt.setInt(3, gameId);
			int row = stmt.executeUpdate();
			
			if (row != 1) {
				throw new SQLException("更新対象のデータが存在しません");
			}
			result = true;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return result;
    }
	
	public boolean updateARegu(int gameId, int regu) throws SQLException {
        String sql = "UPDATE game SET areguid = ? WHERE gameId = ?";
		PreparedStatement stmt = null;
		boolean result = false;
		
		try {
			stmt = con.prepareStatement(sql);
            stmt.setInt(1, regu);
            stmt.setInt(2, gameId);
			int row = stmt.executeUpdate();
			
			if (row != 1) {
				throw new SQLException("更新対象のデータが存在しません");
			}
			result = true;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return result;
    }
	
	public boolean updateBRegu(int gameId, int regu) throws SQLException {
        String sql = "UPDATE game SET breguid = ? WHERE gameId = ?";
		PreparedStatement stmt = null;
		boolean result = false;
		
		try {
			stmt = con.prepareStatement(sql);
            stmt.setInt(1, regu);
            stmt.setInt(2, gameId);
			int row = stmt.executeUpdate();
			
			if (row != 1) {
				throw new SQLException("更新対象のデータが存在しません");
			}
			result = true;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return result;
    }
}
