package servlet;

import common.JudgeBusinessException;
import common.JudgeSystemException;
import entity.Game;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import logic.UpdateCourtLogic;

public class GameEndAction implements ActionIF {
    public String execute(HttpServletRequest request) {
		String page = "gameEnd.jsp";
		
		try {
			// セッションを取得する
			HttpSession session = request.getSession(false);
			if (session == null) {
				throw new JudgeSystemException("セッションが切れました。再度ログインしてください。");
			}
			// 試合情報を取得する
			Game game = (Game) session.getAttribute("game");
			if (game == null) {
				throw new JudgeSystemException("セッションが切れました。再度ログインしてください。");
			}
			// コートDBのセッション情報を初期化する
			UpdateCourtLogic updateCourtLogic = new UpdateCourtLogic();
			updateCourtLogic.initCourtSessionId(game.getCourtId());

			// セッションを破棄する
			request.getSession().invalidate();


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
