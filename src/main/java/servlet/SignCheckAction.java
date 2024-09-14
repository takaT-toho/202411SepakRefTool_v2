package servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import common.JudgeBusinessException;
import common.JudgeSystemException;
import entity.Game;
import logic.UpdateSignInfoInGameLogic;

public class SignCheckAction implements ActionIF {

	public String execute(HttpServletRequest request) {
		String page = "tossForServeAndSide.jsp";
		try {
			// パラメータの取得
			String sign = request.getParameter("sign");			
			// パラメータが未入力の場合
			if (sign == null || sign.equals("")) {
				throw new JudgeBusinessException("署名が未入力です。");
			}
			
			// セッションから値を取得
			HttpSession session = request.getSession(false);
			if (session == null) {
				throw new JudgeBusinessException("セッションが切れました。再度ログインしてください。");
			}
			Game game = (Game) session.getAttribute("game");
			if (game == null) {
				throw new JudgeBusinessException("セッションが切れました。再度ログインしてください。");
			}
			int gameId = game.getGameId();

			// // セッションチェック
			boolean result = SessionCheck.checkSession(request, game.getCourtId());
			if (!result) {
				throw new JudgeBusinessException("セッションが切れました。再度ログインしてください。");
			}
			
			// 署名をデータベースに書き込む処理
			UpdateSignInfoInGameLogic logic = new UpdateSignInfoInGameLogic();
			logic.updateSignInfoInGame(gameId, sign);
			
		} catch (JudgeBusinessException e) {
			request.setAttribute("errorMsg", e.getMessage());
			page = "index.jsp";
		} catch (JudgeSystemException e) {
			request.setAttribute("errorMsg", e.getMessage());
			page = "systemError.jsp";
		}
		
		return page;
	}

}
