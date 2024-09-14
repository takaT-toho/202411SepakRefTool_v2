package servlet;

import java.util.ArrayList;

import common.JudgeBusinessException;
import common.JudgeSystemException;
import entity.Game;
import entity.GameDetail;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import logic.SelectGameDetailLogic;
import logic.SelectGameLogic;
import logic.UpdateGameDetailLogic;
import logic.UpdateGameLogic;

public class SetEndAction implements ActionIF {

	public String execute(HttpServletRequest request) {
		// リダイレクトURLの設定
		String page = "/judgeFC?buttonId=p0202";

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

			// セッションから情報取得
			HttpSession session = request.getSession(false);
			if (session == null) {
				throw new JudgeSystemException("セッションが切れました。再度ログインしてください。");
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

            // 最新の試合情報を取得する
            SelectGameLogic selectGameLogic = new SelectGameLogic();
            game = selectGameLogic.selectGameByGameId(game.getGameId());

            // 最新の試合詳細情報を取得する
            SelectGameDetailLogic selectGameDetailLogic = new SelectGameDetailLogic();
            GameDetail gameDetail = selectGameDetailLogic.findGameDetailByGameId(game.getGameId());

			// 試合終了かどうかを判定する
			if (game.getIsFinished()) {
				page = "/judgeFC?buttonId=p0210";
			}

            // セッションのgame・gamedetailを更新する
            session.setAttribute("game", game);
            session.setAttribute("gameDetail", gameDetail);
			
		} catch (JudgeBusinessException e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
			request.setAttribute("errorMsgList", e.getMsgList());
			page = "/judgeFC?buttonId=p0000";
		}
		catch (JudgeSystemException e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
			page = "/judgeFC?buttonId=p9000";
		}
		
		return page;
	}
}
