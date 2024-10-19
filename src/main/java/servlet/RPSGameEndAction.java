package servlet;

import common.JudgeBusinessException;
import common.JudgeSystemException;
import entity.Game;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import logic.UpdateGameLogic;

public class RPSGameEndAction implements ActionIF {
    public String execute(HttpServletRequest request) {
		String page = "/judgeFC?buttonId=p0210";

		try {
            // パラメータを取得
            String isAreguWin = request.getParameter("isAreguWin");
            if (isAreguWin == null || isAreguWin.isEmpty()) {
                throw new JudgeBusinessException("正しい試合情報が取得できませんでした。");
            }

			// セッションを取得
			HttpSession session = request.getSession(false);
			if (session == null) {
				throw new JudgeSystemException("セッションが切れました。");
			}
			Game game = (Game) session.getAttribute("game");
			if (game == null) {
				throw new JudgeBusinessException("セッションが切れました。再度ログインしてください。");
			}

			// セッションチェック
			boolean result = SessionCheck.checkSession(request, game.getCourtId());
			if (!result) {
				throw new JudgeBusinessException("セッションが切れました。再度ログインしてください。");
			}

            // 試合情報を更新する(SetEndActionと同じ処理)
            int areguId = game.getAreguId();
            int beguId = game.getBreguId();
            int winner = isAreguWin.equals("true") ? areguId : beguId;
            int loser = isAreguWin.equals("true") ? beguId : areguId;
            UpdateGameLogic gameLogic = new UpdateGameLogic();
            gameLogic.updateWinnerLoser(game.getGameId(), winner, loser);        

            // 次の試合情報を更新する
            int nextGameIdWinner = game.getNextGameIdWinner();
            int nextGameIdLoser = game.getNextGameIdLoser();
            gameLogic.updateNextGameReguId(nextGameIdWinner, winner);
            gameLogic.updateNextGameReguId(nextGameIdLoser, loser);

		} catch (JudgeBusinessException e) {
			request.setAttribute("errorMsg", e.getMessage());
			request.setAttribute("errorMsgList", e.getMsgList());
			page = "/judgeFC?buttonId=p0000";
		}
		catch (JudgeSystemException e) {
			request.setAttribute("errorMsg", e.getMessage());
			page = "/judgeFC?buttonId=p9000";
		}
		
		return page;
	}
}
