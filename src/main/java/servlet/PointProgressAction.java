package servlet;

import java.util.ArrayList;

import common.JudgeBusinessException;
import common.JudgeSystemException;
import entity.Game;
import entity.GameEventHistory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import logic.SelectGameEventHistoryLogic;

public class PointProgressAction implements ActionIF {
    public String execute(HttpServletRequest request) {
		String page = "pointProgressMenu.jsp";
		try {			
			// セッションを取得
			HttpSession session = request.getSession(false);
			if (session == null) {
				throw new JudgeSystemException("セッションが切れました。");
			}
			Game game = (Game) session.getAttribute("game");
			if (game == null) {
				throw new JudgeBusinessException("試合情報が取得できませんでした。");
			}

			// セッションチェック
			boolean result = SessionCheck.checkSession(request, game.getCourtId());
			if (!result) {
				throw new JudgeBusinessException("セッションが切れました。再度ログインしてください。");
			}

			// GameEventHistoryの取得
			SelectGameEventHistoryLogic logic = new SelectGameEventHistoryLogic();
			ArrayList<GameEventHistory> gameEventHistoryList = logic.findGameEventHistoriesByGameId(game.getGameId());
			request.setAttribute("gameEventHistoryList", gameEventHistoryList);

		} catch (JudgeBusinessException e) {
			request.setAttribute("errorMsg", e.getMessage());
			request.setAttribute("errorMsgList", e.getMsgList());
		}
		catch (JudgeSystemException e) {
			request.setAttribute("errorMsg", e.getMessage());
			page = "systemError.jsp";
		}
		
		return page;
	}
}