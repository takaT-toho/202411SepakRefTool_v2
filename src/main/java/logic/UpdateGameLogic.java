package logic;

import java.sql.Connection;
import java.sql.SQLException;

import common.JudgeBusinessException;
import common.JudgeSystemException;
import dao.ConnectionManager;
import dao.GameDAO;
import dao.ReguDAO;
import entity.Game;

public class UpdateGameLogic {

    public boolean updateGameWhenSetFinished(int gameId, int setNow, boolean isAreguWin) throws JudgeBusinessException, JudgeSystemException {
        Connection con = null;
        boolean res = false;

        try {
			con = ConnectionManager.getConnectionManager().getConnection();
			GameDAO dao = new GameDAO(con);
            boolean result = false;
            if (setNow == 1) {
                if (isAreguWin) {
                    result = dao.update1SetInfoA(gameId);
                } else {
                    result = dao.update1SetInfoB(gameId);
                }
            } else if (setNow == 2) {
                if (isAreguWin) {
                    result = dao.update2SetInfoA(gameId);
                } else {
                    result = dao.update2SetInfoB(gameId);
                }
            } else if (setNow == 3) {
                if (isAreguWin) {
                    result = dao.update3SetInfoA(gameId);
                } else {
                    result = dao.update3SetInfoB(gameId);
                }
            } else {
                throw new JudgeBusinessException("セット情報が取得できませんでした。");
            }
			
			if (result == false) {
				throw new JudgeBusinessException("データベースの更新に失敗しました。");
			}

            res = true;
		} catch (SQLException e) {
            e.printStackTrace();
			throw new JudgeSystemException("データベースシステムエラーが発生しました。11");
		} finally {
			try {
				if (con != null) {
					con.close();
				} 
			} catch (SQLException e) {
				throw new JudgeSystemException("データベースシステムエラーが発生しました。12");
			}
		}

        return res;
    }

	public boolean updateIsStarted(int gameId) throws JudgeBusinessException, JudgeSystemException {
        Connection con = null;
        boolean res = false;

        try {
			con = ConnectionManager.getConnectionManager().getConnection();
			GameDAO dao = new GameDAO(con);
			boolean result = false;
			result = dao.updateGameIsStarted(gameId);
			
			if (result == false) {
				throw new JudgeBusinessException("データベースの更新に失敗しました。");
			}

            res = true;
		} catch (SQLException e) {
			throw new JudgeSystemException("データベースシステムエラーが発生しました。21");
		} finally {
			try {
				if (con != null) {
					con.close();
				} 
			} catch (SQLException e) {
				throw new JudgeSystemException("データベースシステムエラーが発生しました。22");
			}
		}

        return res;
    }

	public boolean UpdateIsGameFinished(int gameId, boolean isGameFinished) throws JudgeBusinessException, JudgeSystemException {
        Connection con = null;
        boolean res = false;

        try {
			con = ConnectionManager.getConnectionManager().getConnection();
			GameDAO dao = new GameDAO(con);
			boolean result = false;
			result = dao.updateIsGameFinished(gameId, isGameFinished);
			
			if (result == false) {
				throw new JudgeBusinessException("データベースの更新に失敗しました。");
			}

            res = true;
		} catch (SQLException e) {
			throw new JudgeSystemException("データベースシステムエラーが発生しました。21");
		} finally {
			try {
				if (con != null) {
					con.close();
				} 
			} catch (SQLException e) {
				throw new JudgeSystemException("データベースシステムエラーが発生しました。22");
			}
		}

        return res;
    }

	public boolean updateWinnerLoser(int gameId, int winner, int loser) throws JudgeBusinessException, JudgeSystemException {
        Connection con = null;
        boolean res = false;

        try {
			con = ConnectionManager.getConnectionManager().getConnection();
			GameDAO dao = new GameDAO(con);
			boolean result = false;
			result = dao.updateWinnerLoser(gameId, winner, loser);
			
			if (result == false) {
				throw new JudgeBusinessException("データベースの更新に失敗しました。");
			}

            res = true;
		} catch (SQLException e) {
			throw new JudgeSystemException("データベースシステムエラーが発生しました。21");
		} finally {
			try {
				if (con != null) {
					con.close();
				} 
			} catch (SQLException e) {
				throw new JudgeSystemException("データベースシステムエラーが発生しました。22");
			}
		}

        return res;
    }

	public boolean updateNextGameReguId(int gameId, int reguId) throws JudgeBusinessException, JudgeSystemException {
        Connection con = null;
        boolean res = false;

        try {
			con = ConnectionManager.getConnectionManager().getConnection();
			GameDAO dao = new GameDAO(con);
			ReguDAO rDao = new ReguDAO(con);
			boolean result = false;

			Game game = dao.selectGameByGameId(gameId);
			System.out.println("gameId: " + game.getGameId());
			String areguAbb = rDao.findReguByReguId(game.getAreguId()).getAbbreviation();
			System.out.println("areguAbb: " + areguAbb);
			if (areguAbb.contains("::")) {
				System.out.println("inside: 1");
				result = dao.updateARegu(gameId, reguId);
			}
			if (result) {
				return true;
			}
			String breguAbb = rDao.findReguByReguId(game.getBreguId()).getAbbreviation();
			System.out.println("breguAbb: " + breguAbb);
			if (breguAbb.contains("::")) {
				System.out.println("inside: 2");
				result = dao.updateBRegu(gameId, reguId);
			}
			if (result) {
				return true;
			}

			if (result == false) {
				throw new JudgeBusinessException("データベースの更新に失敗しました。");
			}
		} catch (SQLException e) {
			throw new JudgeSystemException("データベースシステムエラーが発生しました。21");
		} finally {
			try {
				if (con != null) {
					con.close();
				} 
			} catch (SQLException e) {
				throw new JudgeSystemException("データベースシステムエラーが発生しました。22");
			}
		}

        return res;
    }

	
}
