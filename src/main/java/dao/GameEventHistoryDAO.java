package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.GameEventHistory;

public class GameEventHistoryDAO {
private Connection con;
	
	public GameEventHistoryDAO(Connection con) {
		this.con = con;
	}

    public ArrayList<GameEventHistory> findGameEventHistoriesByGameId(int gameId) throws SQLException {
        String sql = "SELECT id, gameId, setNum, type, firstDetail, secondDetail, "
            + "isSequential, isAreguGot, createdAt FROM gameEventHistory WHERE gameId = ? "
			+ "ORDER BY createdAt";
		PreparedStatement stmt = null;
		ResultSet res = null;
		ArrayList<GameEventHistory> array_ = new ArrayList<>();
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, gameId);
			res = stmt.executeQuery();
			
			while (res.next()) {
				array_.add(new GameEventHistory(
					res.getInt("id"),
					res.getInt("gameId"),
					res.getInt("setNum"),
					res.getString("type"),
					res.getString("firstDetail"),
					res.getString("secondDetail"),
					res.getBoolean("isSequential"),
					res.getBoolean("isAreguGot"),
					res.getDate("createdAt")
				));
			}
			
		} finally {
			if (res != null) {
				res.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
		return array_;
    }

    public boolean insertGameEventHistory(GameEventHistory gameEventHistory) throws SQLException {
        String sql = "INSERT INTO gameEventHistory (gameId, setNum, type, firstDetail, secondDetail, "
            + "isSequential, isAreguGot, createdAt) VALUES (?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";
		PreparedStatement stmt = null;
		boolean result = false;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, gameEventHistory.getGameId());
            stmt.setInt(2, gameEventHistory.getSetNum());
            stmt.setString(3, gameEventHistory.getType());
            stmt.setString(4, gameEventHistory.getFirstDetail());
            stmt.setString(5, gameEventHistory.getSecondDetail());
            stmt.setBoolean(6, gameEventHistory.getIsSequential());
            stmt.setBoolean(7, gameEventHistory.getIsAreguGot());
            
            int row = stmt.executeUpdate();
			
			if (row != 0) {
                result = true;				
			}
			
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return result;
    }

    public GameEventHistory selectLatestGameEventHistory(int gameId) throws SQLException {
        String sql = "SELECT * FROM gameeventhistory WHERE ID = (SELECT MAX(ID) FROM gameeventhistory WHERE GAMEID = ?)";
		PreparedStatement stmt = null;
		ResultSet res = null;
		GameEventHistory geh = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, gameId);            
            res = stmt.executeQuery();
			
			if (res.next()) {
				geh = new GameEventHistory(
					res.getInt("id"),
					res.getInt("gameId"),
					res.getInt("setNum"),
					res.getString("type"),
					res.getString("firstDetail"),
					res.getString("secondDetail"),
					res.getBoolean("isSequential"),
					res.getBoolean("isAreguGot"),
					res.getDate("createdAt")
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
		return geh;
    }

    public boolean deleteGameEventHistory(GameEventHistory gameEventHistory) throws SQLException {
        String sql = "DELETE FROM gameeventhistory WHERE ID = ?;";
		PreparedStatement stmt = null;
		boolean result = false;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, gameEventHistory.getId());
            
            int row = stmt.executeUpdate();
			
			if (row != 0) {
                result = true;				
			}
			
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return result;
    }
}
