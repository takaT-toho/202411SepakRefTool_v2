package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Regu;


public class ReguDAO {
	private Connection con;
	
	public ReguDAO(Connection con) {
		this.con = con;
	}

    public Regu findReguByReguId(int reguId) throws SQLException {
		String sql = "SELECT * FROM regu WHERE reguId = ?";
		PreparedStatement stmt = null;
		ResultSet res = null;
		Regu regu = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, reguId);
			res = stmt.executeQuery();
			
			if (res.next()) {
				regu = new Regu(
						res.getInt("reguId"),
						res.getInt("teamId"),
						res.getString("name"),
						res.getString("abbreviation"),
						res.getString("department")
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
		return regu;
	}
}