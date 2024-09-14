package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.GameDetail;

public class GameDetailDAO {
	private Connection con;
	
	public GameDetailDAO(Connection con) {
		this.con = con;
	}

    public GameDetail findGameDetailByGameId(int gameId) throws SQLException {
        String sql = "SELECT gameId, is1setGotByA, is2setGotByA, is3setGotByA, "
            + "points1setA, points2setA, points3setA, points1setB, points2setB, points3setB, "
            + "normalTimeoutRemainA, technicalTimeoutRemainA, normalTimeoutRemainB, technicalTimeoutRemainB, "
            + "playerSubstitutionRemainA, playerSubstitutionRemainB, is3setCourtChanged, sumPoints FROM gameDetail WHERE gameId = ?";
		PreparedStatement stmt = null;
		ResultSet res = null;
		GameDetail gameDetail = null;
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, gameId);
			res = stmt.executeQuery();
			
			if (res.next()) {
				gameDetail = new GameDetail();
                gameDetail.setGameId(res.getInt("gameId"));
                gameDetail.setIs1setGotByA(res.getBoolean("is1setGotByA"));
                gameDetail.setIs2setGotByA(res.getBoolean("is2setGotByA"));
                gameDetail.setIs3setGotByA(res.getBoolean("is3setGotByA"));
                gameDetail.setPoints1setA(res.getInt("points1setA"));
                gameDetail.setPoints2setA(res.getInt("points2setA"));
                gameDetail.setPoints3setA(res.getInt("points3setA"));
                gameDetail.setPoints1setB(res.getInt("points1setB"));
                gameDetail.setPoints2setB(res.getInt("points2setB"));
                gameDetail.setPoints3setB(res.getInt("points3setB"));
                gameDetail.setNormalTimeoutRemainA(res.getInt("normalTimeoutRemainA"));
                gameDetail.setTechnicalTimeoutRemainA(res.getInt("technicalTimeoutRemainA"));
                gameDetail.setNormalTimeoutRemainB(res.getInt("normalTimeoutRemainB"));
                gameDetail.setTechnicalTimeoutRemainB(res.getInt("technicalTimeoutRemainB"));
                gameDetail.setPlayerSubstitutionRemainA(res.getInt("playerSubstitutionRemainA"));
				gameDetail.setPlayerSubstitutionRemainB(res.getInt("playerSubstitutionRemainB"));
                gameDetail.setIs3setCourtChanged(res.getBoolean("is3setCourtChanged"));
				gameDetail.setSumPoints(res.getInt("sumPoints"));
			}
			
		} finally {
			if (res != null) {
				res.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
		return gameDetail;
    }

    public boolean updateGameDetail(GameDetail gameDetail) throws SQLException {
        String sql = "UPDATE gameDetail SET is1setGotByA = ?, is2setGotByA = ?, is3setGotByA = ?, "
            + "points1setA = ?, points2setA = ?, points3setA = ?, points1setB = ?, points2setB = ?, points3setB = ?, "
            + "normalTimeoutRemainA = ?, technicalTimeoutRemainA = ?, normalTimeoutRemainB = ?, technicalTimeoutRemainB = ? "
            + "playerSubstitutionRemainA = ?, playerSubstitutionRemainB = ?, is3setCourtChanged = ?, sumPoints = ? WHERE gameId = ?";
		PreparedStatement stmt = null;
		boolean result = false;
		
		try {
			stmt = con.prepareStatement(sql);
            stmt.setBoolean(1, gameDetail.getIs1setGotByA());
            stmt.setBoolean(2, gameDetail.getIs2setGotByA());
            stmt.setBoolean(3, gameDetail.getIs3setGotByA());
            stmt.setInt(4, gameDetail.getPoints1setA());
            stmt.setInt(5, gameDetail.getPoints2setA());
            stmt.setInt(6, gameDetail.getPoints3setA());
            stmt.setInt(7, gameDetail.getPoints1setB());
            stmt.setInt(8, gameDetail.getPoints2setB());
            stmt.setInt(9, gameDetail.getPoints3setB());
            stmt.setInt(10, gameDetail.getNormalTimeoutRemainA());
            stmt.setInt(11, gameDetail.getTechnicalTimeoutRemainA());
            stmt.setInt(12, gameDetail.getNormalTimeoutRemainB());
            stmt.setInt(13, gameDetail.getTechnicalTimeoutRemainB());
            stmt.setInt(14, gameDetail.getPlayerSubstitutionRemainA());
            stmt.setInt(15, gameDetail.getPlayerSubstitutionRemainB());
            stmt.setBoolean(16, gameDetail.getIs3setCourtChanged());
			stmt.setInt(17, gameDetail.getSumPoints());
            stmt.setInt(17, gameDetail.getGameId());
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

    public boolean addPoints1setA(int gameId, int serialNumber) throws SQLException {
        String sql = "UPDATE gameDetail SET points1setA = points1setA + 1, sumPoints = ? WHERE gameId = ?";
		PreparedStatement stmt = null;
		boolean result = false;
		
		try {
			stmt = con.prepareStatement(sql);
            stmt.setInt(1, serialNumber);
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

    public boolean addPoints2setA(int gameId, int serialNumber) throws SQLException {
        String sql = "UPDATE gameDetail SET points2setA = points2setA + 1, sumPoints = ? WHERE gameId = ?";
		PreparedStatement stmt = null;
		boolean result = false;
		
		try {
			stmt = con.prepareStatement(sql);
            stmt.setInt(1, serialNumber);
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

    public boolean addPoints3setA(int gameId, int serialNumber) throws SQLException {
        String sql = "UPDATE gameDetail SET points3setA = points3setA + 1, sumPoints = ? WHERE gameId = ?";
		PreparedStatement stmt = null;
		boolean result = false;
		
		try {
			stmt = con.prepareStatement(sql);
            stmt.setInt(1, serialNumber);
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

    public boolean addPoints1setB(int gameId, int serialNumber) throws SQLException {
        String sql = "UPDATE gameDetail SET points1setB = points1setB + 1, sumPoints = ? WHERE gameId = ?";
		PreparedStatement stmt = null;
		boolean result = false;
		
		try {
			stmt = con.prepareStatement(sql);
            stmt.setInt(1, serialNumber);
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

    public boolean addPoints2setB(int gameId, int serialNumber) throws SQLException {
        String sql = "UPDATE gameDetail SET points2setB = points2setB + 1, sumPoints = ? WHERE gameId = ?";
		PreparedStatement stmt = null;
		boolean result = false;
		
		try {
			stmt = con.prepareStatement(sql);
            stmt.setInt(1, serialNumber);
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

    public boolean addPoints3setB(int gameId, int serialNumber) throws SQLException {
        String sql = "UPDATE gameDetail SET points3setB = points3setB + 1, sumPoints = ? WHERE gameId = ?";
		PreparedStatement stmt = null;
		boolean result = false;
		
		try {
			stmt = con.prepareStatement(sql);
            stmt.setInt(1, serialNumber);
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

    public boolean subtractPoints1setA(int gameId) throws SQLException {
        String sql = "UPDATE gameDetail SET points1setA = points1setA - 1, sumPoints = sumPoints - 1 WHERE gameId = ?";
		PreparedStatement stmt = null;
		boolean result = false;
		
		try {
			stmt = con.prepareStatement(sql);
            stmt.setInt(1, gameId);
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

    public boolean subtractPoints2setA(int gameId) throws SQLException {
        String sql = "UPDATE gameDetail SET points2setA = points2setA - 1, sumPoints = sumPoints - 1 WHERE gameId = ?";
		PreparedStatement stmt = null;
		boolean result = false;
		
		try {
			stmt = con.prepareStatement(sql);
            stmt.setInt(1, gameId);
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

    public boolean subtractPoints3setA(int gameId) throws SQLException {
        String sql = "UPDATE gameDetail SET points3setA = points3setA - 1, sumPoints = sumPoints - 1 WHERE gameId = ?";
		PreparedStatement stmt = null;
		boolean result = false;
		
		try {
			stmt = con.prepareStatement(sql);
            stmt.setInt(1, gameId);
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

    public boolean subtractPoints1setB(int gameId) throws SQLException {
        String sql = "UPDATE gameDetail SET points1setB = points1setB - 1, sumPoints = sumPoints - 1 WHERE gameId = ?";
		PreparedStatement stmt = null;
		boolean result = false;
		
		try {
			stmt = con.prepareStatement(sql);
            stmt.setInt(1, gameId);
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

    public boolean subtractPoints2setB(int gameId) throws SQLException {
        String sql = "UPDATE gameDetail SET points2setB = points2setB - 1, sumPoints = sumPoints - 1 WHERE gameId = ?";
		PreparedStatement stmt = null;
		boolean result = false;
		
		try {
			stmt = con.prepareStatement(sql);
            stmt.setInt(1, gameId);
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

    public boolean subtractPoints3setB(int gameId) throws SQLException {
        String sql = "UPDATE gameDetail SET points3setB = points3setB - 1, sumPoints = sumPoints - 1 WHERE gameId = ?";
		PreparedStatement stmt = null;
		boolean result = false;
		
		try {
			stmt = con.prepareStatement(sql);
            stmt.setInt(1, gameId);
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

	public boolean updateNormalTimeoutA(int gameId) throws SQLException {
        String sql = "UPDATE gameDetail SET normalTimeoutRemainA = normalTimeoutRemainA - 1 WHERE gameId = ?";
		PreparedStatement stmt = null;
		boolean result = false;
		
		try {
			stmt = con.prepareStatement(sql);
            stmt.setInt(1, gameId);
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

	public boolean updateTechnicalTimeoutA(int gameId) throws SQLException {
        String sql = "UPDATE gameDetail SET technicalTimeoutRemainA = technicalTimeoutRemainA - 1 WHERE gameId = ?";
		PreparedStatement stmt = null;
		boolean result = false;
		
		try {
			stmt = con.prepareStatement(sql);
            stmt.setInt(1, gameId);
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

	public boolean updateNormalTimeoutB(int gameId) throws SQLException {
        String sql = "UPDATE gameDetail SET normalTimeoutRemainB = normalTimeoutRemainB - 1 WHERE gameId = ?";
		PreparedStatement stmt = null;
		boolean result = false;
		
		try {
			stmt = con.prepareStatement(sql);
            stmt.setInt(1, gameId);
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

	public boolean updateTechnicalTimeoutB(int gameId) throws SQLException {
        String sql = "UPDATE gameDetail SET technicalTimeoutRemainB = technicalTimeoutRemainB - 1 WHERE gameId = ?";
		PreparedStatement stmt = null;
		boolean result = false;
		
		try {
			stmt = con.prepareStatement(sql);
            stmt.setInt(1, gameId);
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

	public boolean updatePlayerSubstitutionA(int gameId) throws SQLException {
        String sql = "UPDATE gameDetail SET playerSubstitutionRemainA = playerSubstitutionRemainA - 1 WHERE gameId = ?";
		PreparedStatement stmt = null;
		boolean result = false;
		
		try {
			stmt = con.prepareStatement(sql);
            stmt.setInt(1, gameId);
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

	public boolean updatePlayerSubstitutionB(int gameId) throws SQLException {
        String sql = "UPDATE gameDetail SET playerSubstitutionRemainB = playerSubstitutionRemainB - 1 WHERE gameId = ?";
		PreparedStatement stmt = null;
		boolean result = false;
		
		try {
			stmt = con.prepareStatement(sql);
            stmt.setInt(1, gameId);
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

	public boolean update1setDetail(int gameId, boolean isAreguGot) throws SQLException {
        String sql = "UPDATE gameDetail SET is1SetGotByA = ?, normalTimeoutRemainA = 1, technicalTimeoutRemainA = 5, "
			+ "normalTimeoutRemainB = 1, technicalTimeoutRemainB = 5, playerSubstitutionRemainA = 2, playerSubstitutionRemainB = 2, sumPoints = 0 WHERE gameId = ?";
		PreparedStatement stmt = null;
		boolean result = false;
		
		try {
			stmt = con.prepareStatement(sql);
            stmt.setBoolean(1, isAreguGot);
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

	public boolean update2setDetail(int gameId, boolean isAreguGot) throws SQLException {
        String sql = "UPDATE gameDetail SET is2SetGotByA = ?, normalTimeoutRemainA = 1, technicalTimeoutRemainA = 5, "
			+ "normalTimeoutRemainB = 1, technicalTimeoutRemainB = 5, playerSubstitutionRemainA = 2, playerSubstitutionRemainB = 2, sumPoints = 0 WHERE gameId = ?";
		PreparedStatement stmt = null;
		boolean result = false;
		
		try {
			stmt = con.prepareStatement(sql);
            stmt.setBoolean(1, isAreguGot);
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

	public boolean update3setDetail(int gameId, boolean isAreguGot) throws SQLException {
        String sql = "UPDATE gameDetail SET is3SetGotByA = ?, normalTimeoutRemainA = 1, technicalTimeoutRemainA = 5, "
			+ "normalTimeoutRemainB = 1, technicalTimeoutRemainB = 5, playerSubstitutionRemainA = 2, playerSubstitutionRemainB = 2, sumPoints = 0 WHERE gameId = ?";
		PreparedStatement stmt = null;
		boolean result = false;
		
		try {
			stmt = con.prepareStatement(sql);
            stmt.setBoolean(1, isAreguGot);
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

	public boolean updateCourtChange(int gameId, boolean is3setCourtChanged) throws SQLException {
        String sql = "UPDATE gameDetail SET is3setCourtChanged = ? WHERE gameId = ?";
		PreparedStatement stmt = null;
		boolean result = false;
		
		try {
			stmt = con.prepareStatement(sql);
            stmt.setBoolean(1, is3setCourtChanged);
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
