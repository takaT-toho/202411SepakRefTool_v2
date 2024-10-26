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

	public boolean updateNextGameReguId(int gameId, int nextGameId, int reguId) throws JudgeBusinessException, JudgeSystemException {
        Connection con = null;
        boolean res = false;

        try {
			con = ConnectionManager.getConnectionManager().getConnection();
			GameDAO dao = new GameDAO(con);
			ReguDAO rDao = new ReguDAO(con);
			boolean result = false;

			Game game = dao.selectGameByGameId(gameId);
			Game nextGame = dao.selectGameByGameId(nextGameId);
			String areguAbb = rDao.findReguByReguId(nextGame.getAreguId()).getAbbreviation();
			System.out.println("areguAbb=" + areguAbb);
			System.out.println("game.getName()=" + game.getName());
			if (areguAbb.contains("::") && areguAbb.contains(game.getName())) {
				result = dao.updateARegu(nextGameId, reguId);
			}
			if (result) {
				return true;
			}
			String breguAbb = rDao.findReguByReguId(nextGame.getBreguId()).getAbbreviation();
			System.out.println("breguAbb=" + breguAbb);
			if (breguAbb.contains("::") && breguAbb.contains(game.getName())) {
				result = dao.updateBRegu(nextGameId, reguId);
			}
			if (result) {
				return true;
			}
			if (nextGameId == 9999) {
				return true;
			}

			if (result == false) {
				System.out.println("updateNextGameReguId: gameId=" + nextGameId + ", reguId=" + reguId);
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

	public boolean updateNextGameJudgeReguId(int gameId, int nextGameId, int reguId) throws JudgeBusinessException, JudgeSystemException {
		System.out.println("updateNextGameJudgeReguId: gameId=" + gameId + ", nextGameId=" + nextGameId + ", reguId=" + reguId);
        Connection con = null;
        boolean res = false;

        try {
			con = ConnectionManager.getConnectionManager().getConnection();
			GameDAO dao = new GameDAO(con);
			ReguDAO rDao = new ReguDAO(con);
			boolean result = false;

			Game game = dao.selectGameByGameId(gameId);
			Game nextGame = dao.selectGameByGameId(nextGameId);
			String mainJudgeReguAbb = rDao.findReguByReguId(nextGame.getMainJudgeReguId()).getAbbreviation();
			System.out.println("mainJudgeReguAbb=" + mainJudgeReguAbb);
			System.out.println("game.getName()=" + game.getName());
			if (mainJudgeReguAbb.contains("::") && mainJudgeReguAbb.contains(game.getName())) {
				result = dao.updateMainJudgeRegu(nextGameId, reguId);
			}
			if (result) {
				return true;
			}
			String subJudgeReguAbb = rDao.findReguByReguId(nextGame.getSubJudgeReguId()).getAbbreviation();
			System.out.println("subJudgeReguAbb=" + subJudgeReguAbb);
			if (subJudgeReguAbb.contains("::") && subJudgeReguAbb.contains(game.getName())) {
				result = dao.updateSubJudgeRegu(nextGameId, reguId);
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
