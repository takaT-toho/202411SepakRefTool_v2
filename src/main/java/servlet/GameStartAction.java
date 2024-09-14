package servlet;

import common.JudgeBusinessException;
import common.JudgeSystemException;
import entity.Game;
import entity.GameDetail;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import logic.SelectGameDetailLogic;
import logic.UpdateGameLogic;

public class GameStartAction implements ActionIF {
	public String execute(HttpServletRequest request) {
		String page = "main.jsp";
		try {
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

			// isStartedの更新
			UpdateGameLogic updateGameLogic = new UpdateGameLogic();
			updateGameLogic.updateIsStarted(game.getGameId());
			
			// GameDetailの取得
			SelectGameDetailLogic logic = new SelectGameDetailLogic();
			GameDetail gameDetail = logic.findGameDetailByGameId(game.getGameId());
			if (gameDetail == null) {
				throw new JudgeBusinessException("試合が見つかりませんでした。");
			}

			// GameDetailをセッションスコープに保存
			session.setAttribute("gameDetail", gameDetail);

		} catch (JudgeBusinessException e) {
			request.setAttribute("errorMsg", e.getMessage());
			request.setAttribute("errorMsgList", e.getMsgList());
			page = "index.jsp";
		}
		catch (JudgeSystemException e) {
			request.setAttribute("errorMsg", e.getMessage());
			page = "systemError.jsp";
		}
		
		return page;
	}
}
