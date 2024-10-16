package servlet;

import java.util.ArrayList;

import common.JudgeBusinessException;
import common.JudgeSystemException;
import entity.Game;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import logic.UpdateGameDetailLogic;
import logic.UpdateGameLogic;

public class RPSAction implements ActionIF {
    public String execute(HttpServletRequest request) {
		// リダイレクトURLの設定
		String page = "/judgeFC?buttonId=p0204";

		try {            
			// パラメータの取得
			String setNow = request.getParameter("setNow");
			String isAreguSetWin = request.getParameter("isAreguSetWin");
			
			ArrayList<String> errorMsgList = new ArrayList<>();
			// パラメータが未入力の場合
			if (setNow == null || setNow.equals("")) {
				errorMsgList.add("セット情報が取得できませんでした。");
			}
			if (isAreguSetWin == null || isAreguSetWin.equals("")) {
				errorMsgList.add("勝者レグ情報が取得できませんでした。");
			}
			if (!errorMsgList.isEmpty()) {
				throw new JudgeBusinessException(errorMsgList);
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
            
            // 試合情報を更新する
            UpdateGameLogic gameLogic = new UpdateGameLogic();
            gameLogic.updateGameWhenSetFinished(game.getGameId() , Integer.parseInt(setNow), isAreguSetWin.equals("true") ? true : false);

            // 試合詳細情報を更新する
            UpdateGameDetailLogic gameDetailLogic = new UpdateGameDetailLogic();
            gameDetailLogic.updateGameDetailWhenSetFinished(game.getGameId(), Integer.parseInt(setNow), isAreguSetWin.equals("true") ? true : false);

		} catch (JudgeBusinessException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getMsgList());
			request.setAttribute("errorMsg", e.getMessage());
			request.setAttribute("errorMsgList", e.getMsgList());
			page = "/judgeFC?buttonId=p0000";
		}
		catch (JudgeSystemException e) {
            System.out.println(e.getMessage());
			request.setAttribute("errorMsg", e.getMessage());
			page = "/judgeFC?buttonId=p9000";
		}
		
		return page;
	}
}
