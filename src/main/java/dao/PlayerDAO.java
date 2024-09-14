package dao;

import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Player;

public class PlayerDAO {
	private Connection con;
	
	public PlayerDAO(Connection con) {
		this.con = con;
	}
	
	public ArrayList<Player> findAllPlayersByReguId(int reguId) throws SQLException {
		String sql = "SELECT p.playerId, pm.name, pm.katakana, p.position, p.orders FROM player AS p "
            + "INNER JOIN playermaster as pm "
            + "ON p.playerMasterId = pm.playerMasterId "
            + "WHERE p.reguId = ? "
			+ "ORDER BY p.orders";
		PreparedStatement stmt = null;
		ResultSet res = null;
		ArrayList<Player> playerList = new ArrayList<>();
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, reguId);
			res = stmt.executeQuery();
			
			while (res.next()) {
				Player player = new Player();
                player.setPlayerId(res.getInt("playerId"));
                player.setName(res.getString("name"));
                player.setKatakana(res.getString("katakana"));
                player.setPosition(res.getString("position"));
                player.setOrders(res.getInt("orders"));
                playerList.add(player);
			}
			
		} finally {
			if (res != null) {
				res.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
		return playerList;
	}

	
	
	public Player findPlayerByName(String name) throws SQLException {
		String sql = "SELECT p.playerId, pm.name, pm.katakana, p.position, p.orders FROM player AS p "
            + "INNER JOIN playermaster as pm "
            + "ON p.playerMasterId = pm.playerMasterId "
            + "WHERE pm.name = ?";
		PreparedStatement stmt = null;
		ResultSet res = null;
		Player player = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, name);
			res = stmt.executeQuery();
			
			if (res.next()) {
				player = new Player();
                player.setPlayerId(res.getInt("playerId"));
                player.setName(res.getString("name"));
                player.setKatakana(res.getString("katakana"));
                player.setPosition(res.getString("position"));
                player.setOrders(res.getInt("orders"));
			}
			
		} finally {
			if (res != null) {
				res.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
		return player;
	}

	public boolean updatePlayersOrder(int playerId, int orders) throws SQLException {
		System.out.println("playerId" + playerId);
		System.out.println("orders" + orders);
		String sql = "UPDATE player SET orders = ? WHERE playerId = ?";
		PreparedStatement stmt = null;
		boolean result = false;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, orders);
			stmt.setInt(2, playerId);
			int row = stmt.executeUpdate();
			
			if (row != 1) {
				System.out.println("row:"+row);
				throw new SQLException("更新失敗");
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