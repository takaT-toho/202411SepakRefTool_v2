package servlet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
		String page = "/judgeFC?buttonId=p0005";

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
			
				int areguId = game.getAreguId();
				int beguId = game.getBreguId();
				int winner = isAreguSetWin.equals("true") ? areguId : beguId;
				int loser = isAreguSetWin.equals("true") ? beguId : areguId;
				gameLogic.updateWinnerLoser(game.getGameId(), winner, loser);
				
				int nextGameIdWinner = game.getNextGameIdWinner();
				int nextGameIdLoser = game.getNextGameIdLoser();
				gameLogic.updateNextGameReguId(game.getGameId(), nextGameIdWinner, winner);
				gameLogic.updateNextGameReguId(game.getGameId(), nextGameIdLoser, loser);

				Map<Integer, Integer> gameIdMap = new HashMap<>();
				gameIdMap.put(708, 1004);
				gameIdMap.put(801, 1004);
				gameIdMap.put(601, 1005);
				gameIdMap.put(605, 1007);
				gameIdMap.put(905, 1102);
				gameIdMap.put(906, 1102);
				gameIdMap.put(903, 1103);
				gameIdMap.put(904, 1103);
				gameIdMap.put(607, 1104);
				gameIdMap.put(608, 1104);
				gameIdMap.put(507, 1106);
				gameIdMap.put(907, 1106);
				gameIdMap.put(703, 1108);
				gameIdMap.put(704, 1108);
				gameIdMap.put(805, 1203);
				gameIdMap.put(806, 1203);
				gameIdMap.put(901, 1205);
				gameIdMap.put(902, 1205);
				gameIdMap.put(1004, 1207);
				gameIdMap.put(707, 1207);

				if (gameIdMap.containsKey(game.getGameId())) {
					int nextGameId = gameIdMap.get(game.getGameId());
					gameLogic.updateNextGameJudgeReguId(game.getGameId(), nextGameId, loser);
				}
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
