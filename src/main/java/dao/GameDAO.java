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
		String sql = "SELECT g.gameId, g.isFinished, g.isStarted FROM GAME g "
				+ "INNER JOIN COURT c ON g.courtId = c.courtId "
				+ "WHERE g.isFinished = false and g.courtId = "
				+ "(SELECT c.courtId FROM COURT c WHERE c.name = ?) "
				+ "ORDER BY gameId ASC LIMIT 1";
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
		String sql = "SELECT * FROM GAME WHERE gameId = ?";
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
		String sql = "UPDATE GAME SET isStarted = true WHERE gameId = ?";
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
		String sql = "UPDATE GAME SET mainJudgeSign = ? WHERE gameId = ?";
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
		String sql = "UPDATE GAME SET setNow = setNow + 1, isFin1Set = true, setNumGotByA = setNumGotByA + 1 WHERE gameId = ?";
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
		String sql = "UPDATE GAME SET setNow = setNow + 1, isFin2Set = true, setNumGotByA = setNumGotByA + 1 WHERE gameId = ?";
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
		String sql = "UPDATE GAME SET setNow = setNow + 1, isFin3Set = true, setNumGotByA = setNumGotByA + 1 WHERE gameId = ?";
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
		String sql = "UPDATE GAME SET setNow = setNow + 1, isFin1Set = true, setNumGotByB = setNumGotByB + 1 WHERE gameId = ?";
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
		String sql = "UPDATE GAME SET setNow = setNow + 1, isFin2Set = true, setNumGotByB = setNumGotByB + 1 WHERE gameId = ?";
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
		String sql = "UPDATE GAME SET setNow = setNow + 1, isFin3Set = true, setNumGotByB = setNumGotByB + 1 WHERE gameId = ?";
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

	public boolean update1SetInfoTemp(int gameId, boolean isSetFinished) throws SQLException {
		String sql = "UPDATE GAME SET isFin1Set = ? WHERE gameId = ?";
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setBoolean(1, isSetFinished);
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

	public boolean update2SetInfoTemp(int gameId, boolean isSetFinished) throws SQLException {
		String sql = "UPDATE GAME SET isFin2Set = ? WHERE gameId = ?";
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setBoolean(1, isSetFinished);
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

	public boolean update3SetInfoTemp(int gameId, boolean isSetFinished) throws SQLException {
		String sql = "UPDATE GAME SET isFin3Set = ? WHERE gameId = ?";
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setBoolean(1, isSetFinished);
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

	public boolean updateIsGameFinished(int gameId, boolean isGameFinished) throws SQLException {
        String sql = "UPDATE GAME SET isFinished = ? WHERE gameId = ?";
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
        String sql = "UPDATE GAME SET winner = ?, loser = ? WHERE gameId = ?";
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
        String sql = "UPDATE GAME SET areguid = ? WHERE gameId = ?";
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
        String sql = "UPDATE GAME SET breguid = ? WHERE gameId = ?";
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
	
	public boolean updateMainJudgeRegu(int gameId, int regu) throws SQLException {
        String sql = "UPDATE GAME SET mainJudgeReguId = ? WHERE gameId = ?";
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

	
	
	public boolean updateSubJudgeRegu(int gameId, int regu) throws SQLException {
        String sql = "UPDATE GAME SET subJudgeReguId = ? WHERE gameId = ?";
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
