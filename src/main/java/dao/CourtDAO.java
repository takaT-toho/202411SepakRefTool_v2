package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Court;
import entity.LoginMap;

public class CourtDAO {
	private Connection con;
	
	public CourtDAO(Connection con) {
		this.con = con;
	}
	
	public Court selectCourtByGameId(int gameId) throws SQLException {
		String sql = "SELECT * FROM court WHERE courtId = "
				+ "(SELECT courtId FROM game where gameId = ?)";
		PreparedStatement stmt = null;
		ResultSet res = null;
		Court court = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, gameId);
			res = stmt.executeQuery();
			
			if (res.next()) {
				court = new Court(
						res.getInt("courtId"),
						res.getString("name"),
						res.getString("pass"),
						res.getString("sessionId"),
						res.getLong("lastUpdate")
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
		return court;
	}
	
	public Court selectCourtByCourtName(String courtName) throws SQLException {
		String sql = "SELECT * FROM court WHERE name = ?";
		PreparedStatement stmt = null;
		ResultSet res = null;
		Court court = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, courtName);
			res = stmt.executeQuery();
			
			if (res.next()) {
				court = new Court(
						res.getInt("courtId"),
						res.getString("name"),
						res.getString("pass"),
						res.getString("sessionId"),
						res.getLong("lastUpdate")
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
		return court;
	}

	public Court selectCourtByCourtId(int courtId) throws SQLException {
		String sql = "SELECT * FROM court WHERE courtId = ?";
		PreparedStatement stmt = null;
		ResultSet res = null;
		Court court = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, courtId);
			res = stmt.executeQuery();
			
			if (res.next()) {
				court = new Court(
						res.getInt("courtId"),
						res.getString("name"),
						res.getString("pass"),
						res.getString("sessionId"),
						res.getLong("lastUpdate")
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
		return court;
	}
	
	public boolean updateCourtLoginInfo(LoginMap loginMap) throws SQLException {
		String sql = "UPDATE court SET sessionId = ?, lastUpdate = ? WHERE courtId = ?";
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, loginMap.getSessionId());
			stmt.setLong(2, loginMap.getLastUpdate());
			stmt.setInt(3, loginMap.getCourtId());
			
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

	public boolean initCourtSessionId(int courtId) throws SQLException {
		String sql = "UPDATE court SET sessionId = null, lastUpdate = 0 WHERE courtId = ?";
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, courtId);
			
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
