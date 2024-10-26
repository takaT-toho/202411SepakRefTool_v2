// 2300
package logic;

import java.sql.Connection;
import java.sql.SQLException;

import common.JudgeBusinessException;
import common.JudgeSystemException;
import dao.ConnectionManager;
import dao.GameDetailDAO;
import entity.GameDetail;

public class UpdateGameDetailLogic {

    public boolean updateGameDetail(GameDetail gameDetail) throws JudgeBusinessException, JudgeSystemException {
        Connection con = null;
        boolean res = false;

        try {
			con = ConnectionManager.getConnectionManager().getConnection();
			GameDetailDAO dao = new GameDetailDAO(con);
			boolean result = dao.updateGameDetail(gameDetail);
			
			if (result == false) {
				throw new JudgeBusinessException("データベースの更新に失敗しました。");
			}

            res = true;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new JudgeSystemException("データベースシステムエラーが発生しました。(23001)");
		} finally {
			try {
				if (con != null) {
					con.close();
				} 
			} catch (SQLException e) {
				e.printStackTrace();
				throw new JudgeSystemException("データベースシステムエラーが発生しました。(23002)");
			}
		}

        return res;
    }

	public boolean addPoints(int gameId, int setNow, boolean isAreguGot, int serialNumber) throws JudgeBusinessException, JudgeSystemException {
        Connection con = null;
        boolean res = false;

        try {
			con = ConnectionManager.getConnectionManager().getConnection();
			GameDetailDAO dao = new GameDetailDAO(con);
			boolean result = false;
			if (isAreguGot) {
				if (setNow == 1) {
					result = dao.addPoints1setA(gameId, serialNumber);
				} else if (setNow == 2) {
					result = dao.addPoints2setA(gameId, serialNumber);
				} else if (setNow == 3) {
					result = dao.addPoints3setA(gameId, serialNumber);
				}
			} else {
				if (setNow == 1) {
					result = dao.addPoints1setB(gameId, serialNumber);
				} else if (setNow == 2) {
					result = dao.addPoints2setB(gameId, serialNumber);
				} else if (setNow == 3) {
					result = dao.addPoints3setB(gameId, serialNumber);
				}
			}
			
			if (result == false) {
				throw new JudgeBusinessException("データベースの更新に失敗しました。");
			}

            res = true;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new JudgeSystemException("データベースシステムエラーが発生しました。(23003)");
		} finally {
			try {
				if (con != null) {
					con.close();
				} 
			} catch (SQLException e) {
				e.printStackTrace();
				throw new JudgeSystemException("データベースシステムエラーが発生しました。(23004)");
			}
		}

        return res;
    }

	

	public boolean subtractPoints(int gameId, int setNow, boolean isAreguGot) throws JudgeBusinessException, JudgeSystemException {
        Connection con = null;
        boolean res = false;

        try {
			con = ConnectionManager.getConnectionManager().getConnection();
			GameDetailDAO dao = new GameDetailDAO(con);
			boolean result = false;
			if (isAreguGot) {
				if (setNow == 1) {
					result = dao.subtractPoints1setA(gameId);
				} else if (setNow == 2) {
					result = dao.subtractPoints2setA(gameId);
				} else if (setNow == 3) {
					result = dao.subtractPoints3setA(gameId);
				}
			} else {
				if (setNow == 1) {
					result = dao.subtractPoints1setB(gameId);
				} else if (setNow == 2) {
					result = dao.subtractPoints2setB(gameId);
				} else if (setNow == 3) {
					result = dao.subtractPoints3setB(gameId);
				}
			}
			
			if (result == false) {
				throw new JudgeBusinessException("データベースの更新に失敗しました。");
			}

            res = true;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new JudgeSystemException("データベースシステムエラーが発生しました。(23005)");
		} finally {
			try {
				if (con != null) {
					con.close();
				} 
			} catch (SQLException e) {
				e.printStackTrace();
				throw new JudgeSystemException("データベースシステムエラーが発生しました。(23006)");
			}
		}

        return res;
    }

	public boolean updateTimeout(int gameId, String firstDetail, boolean isAreguGot) throws JudgeBusinessException, JudgeSystemException {
        Connection con = null;
        boolean res = false;

        try {
			con = ConnectionManager.getConnectionManager().getConnection();
			GameDetailDAO dao = new GameDetailDAO(con);
			boolean result = false;
			if (isAreguGot) {
				if (firstDetail.equals("1")) {
					result = dao.updateNormalTimeoutA(gameId);
				} else if (firstDetail.equals("5")) {
					result = dao.updateTechnicalTimeoutA(gameId);
				}
			} else {
				if (firstDetail.equals("1")) {
					result = dao.updateNormalTimeoutB(gameId);
				} else if (firstDetail.equals("5")) {
					result = dao.updateTechnicalTimeoutB(gameId);
				}
			}
			
			if (result == false) {
				throw new JudgeBusinessException("データベースの更新に失敗しました。");
			}

            res = true;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new JudgeSystemException("データベースシステムエラーが発生しました。(23007)");
		} finally {
			try {
				if (con != null) {
					con.close();
				} 
			} catch (SQLException e) {
				e.printStackTrace();
				throw new JudgeSystemException("データベースシステムエラーが発生しました。(23008)");
			}
		}

        return res;
    }

	public boolean updatePlayerSubstitutionRemain(int gameId, boolean isAreguGot) throws JudgeBusinessException, JudgeSystemException {
        Connection con = null;
        boolean res = false;

        try {
			con = ConnectionManager.getConnectionManager().getConnection();
			GameDetailDAO dao = new GameDetailDAO(con);
			boolean result = false;
			if (isAreguGot) {
				result = dao.updatePlayerSubstitutionA(gameId);
			} else {
				result = dao.updatePlayerSubstitutionB(gameId);
			}
			
			if (result == false) {
				throw new JudgeBusinessException("データベースの更新に失敗しました。");
			}

            res = true;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new JudgeSystemException("データベースシステムエラーが発生しました。(23009)");
		} finally {
			try {
				if (con != null) {
					con.close();
				} 
			} catch (SQLException e) {
				e.printStackTrace();
				throw new JudgeSystemException("データベースシステムエラーが発生しました。(23010)");
			}
		}

        return res;
    }

	public boolean updateGameDetailWhenSetFinished(int gameId, int setNow, boolean isAreguGot) throws JudgeBusinessException, JudgeSystemException {
        Connection con = null;
        boolean res = false;

        try {
			con = ConnectionManager.getConnectionManager().getConnection();
			GameDetailDAO dao = new GameDetailDAO(con);
			boolean result = false;
			if (setNow == 1) {
				result = dao.update1setDetail(gameId, isAreguGot);
			} else if (setNow == 2) {
				result = dao.update2setDetail(gameId, isAreguGot);
			} else if (setNow == 3) {
				result = dao.update3setDetail(gameId, isAreguGot);
			}
			
			if (result == false) {
				throw new JudgeBusinessException("データベースの更新に失敗しました。");
			}

            res = true;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new JudgeSystemException("データベースシステムエラーが発生しました。(23011)");
		} finally {
			try {
				if (con != null) {
					con.close();
				} 
			} catch (SQLException e) {
				e.printStackTrace();
				throw new JudgeSystemException("データベースシステムエラーが発生しました。(23012)");
			}
		}

        return res;
    }	

	public boolean UpdateCourtChange(int gameId, boolean is3setCourtChanged) throws JudgeBusinessException, JudgeSystemException {
        Connection con = null;
        boolean res = false;

        try {
			con = ConnectionManager.getConnectionManager().getConnection();
			GameDetailDAO dao = new GameDetailDAO(con);
			boolean result = false;
			result = dao.updateCourtChange(gameId, is3setCourtChanged);
			
			if (result == false) {
				throw new JudgeBusinessException("データベースの更新に失敗しました。");
			}

            res = true;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new JudgeSystemException("データベースシステムエラーが発生しました。(23013)");
		} finally {
			try {
				if (con != null) {
					con.close();
				} 
			} catch (SQLException e) {
				e.printStackTrace();
				throw new JudgeSystemException("データベースシステムエラーが発生しました。(23014)");
			}
		}

        return res;
    }
}
