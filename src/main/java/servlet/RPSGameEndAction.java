package servlet;

import common.JudgeBusinessException;
import common.JudgeSystemException;
import entity.Game;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

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
