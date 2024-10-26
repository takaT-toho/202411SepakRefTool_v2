package servlet;

import common.JudgeBusinessException;
import common.JudgeSystemException;
import entity.Game;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class TwoMinutesRestAction implements ActionIF {
    public String execute(HttpServletRequest request) {
		String page = "twoMinutesRest.jsp";

		try {
            // セッション情報の取得
            HttpSession session = request.getSession(false);
			if (session == null) {
				throw new JudgeBusinessException("セッションが切れました。再度ログインしてください。");
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
			
		} catch (JudgeBusinessException e) {
			request.setAttribute("errorMsgList", e.getMessage());
			page = "index.jsp";
		} 
		// catch (JudgeSystemException e) {
		// 	request.setAttribute("errorMsg", e.getMessage());
		// 	page = "systemError.jsp";
		// }
		
		return page;
	}

}
