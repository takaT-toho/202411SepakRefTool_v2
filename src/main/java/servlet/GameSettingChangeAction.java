package servlet;

import java.util.ArrayList;

import common.JudgeBusinessException;
import common.JudgeSystemException;
import entity.Game;
import entity.GameConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import logic.UpdateGameConfigLogic;

public class GameSettingChangeAction implements ActionIF {
    public String execute(HttpServletRequest request) {
		String page = "/judgeFC?buttonId=p0006";

		try {
			// パラメータの取得
			String language = request.getParameter("refree-call-language");
			String gameType = request.getParameter("match-type");
			String gameRule = request.getParameter("game-resolution");
			String maxSet = request.getParameter("match-end-condition");
			String deuceStartScore1setPlusOne = request.getParameter("1set-normal-end-point");
			String deuceStartScore2setPlusOne = request.getParameter("2set-normal-end-point");
			String deuceStartScore3setPlusOne = request.getParameter("3set-normal-end-point");
			String maxScore1set = request.getParameter("1set-deuce-end-point");
			String maxScore2set = request.getParameter("2set-deuce-end-point");
			String maxScore3set = request.getParameter("3set-deuce-end-point");
			String courtChangeScore = request.getParameter("3set-court-side-change-end-point");

            ArrayList<String> errorMsgList = new ArrayList<>();
			// パラメータが未入力の場合
			if (language == null || language.equals("")) {
				errorMsgList.add("言語が取得できませんでした。");
			}
			if (gameType == null || gameType.equals("")) {
				errorMsgList.add("試合タイプが取得できませんでした。");
			}
			if (gameRule == null || gameRule.equals("")) {
				errorMsgList.add("試合ルールが取得できませんでした。");
			}
			if (maxSet == null || maxSet.equals("")) {
				errorMsgList.add("最大セット数が取得できませんでした。");
			}
			if (deuceStartScore1setPlusOne == null || deuceStartScore1setPlusOne.equals("")) {
				errorMsgList.add("デュース開始得点+1(1SET)が取得できませんでした。");
			}
			if (deuceStartScore2setPlusOne == null || deuceStartScore2setPlusOne.equals("")) {
				errorMsgList.add("デュース開始得点+1(2SET)が取得できませんでした。");
			}
			if (deuceStartScore3setPlusOne == null || deuceStartScore3setPlusOne.equals("")) {
				errorMsgList.add("デュース開始得点+1(3SET)が取得できませんでした。");
			}
			if (maxScore1set == null || maxScore1set.equals("")) {
				errorMsgList.add("最大得点(1SET)が取得できませんでした。");
			}
			if (maxScore2set == null || maxScore2set.equals("")) {
				errorMsgList.add("最大得点(2SET)が取得できませんでした。");
			}
			if (maxScore3set == null || maxScore3set.equals("")) {
				errorMsgList.add("最大得点(3SET)が取得できませんでした。");
			}
			if (courtChangeScore == null || courtChangeScore.equals("")) {
				errorMsgList.add("コードチェンジ得点が取得できませんでした。");
			}
			if (!errorMsgList.isEmpty()) {
				throw new JudgeBusinessException(errorMsgList);
			}

			// セッションから情報取得
			HttpSession session = request.getSession(false);
			if (session == null) {
				throw new JudgeSystemException("セッションが切れました。再度ログインしてください。1");
			}
			Game game = (Game) session.getAttribute("game");
			if (game == null) {
				throw new JudgeBusinessException("セッションが切れました。再度ログインしてください。2");
			}

			// セッションチェック
			boolean result = SessionCheck.checkSession(request, game.getCourtId());
			if (!result) {
				throw new JudgeBusinessException("セッションが切れました。再度ログインしてください。3");
			}

            // 試合の設定を更新する
            GameConfig gameConfig = (GameConfig) session.getAttribute("gameConfig");
            gameConfig.setLanguage(Integer.parseInt(language));
            gameConfig.setGameType(gameType);
            gameConfig.setGameRule(gameRule);
            gameConfig.setMaxSet(Integer.parseInt(maxSet));
            gameConfig.setDeuceStartScore1set(Integer.parseInt(deuceStartScore1setPlusOne) - 1);
            gameConfig.setDeuceStartScore2set(Integer.parseInt(deuceStartScore2setPlusOne) - 1);
            gameConfig.setDeuceStartScore3set(Integer.parseInt(deuceStartScore3setPlusOne) - 1);
            gameConfig.setMaxScore1set(Integer.parseInt(maxScore1set));
            gameConfig.setMaxScore2set(Integer.parseInt(maxScore2set));
            gameConfig.setMaxScore3set(Integer.parseInt(maxScore3set));
            gameConfig.setCourtChangeScore(Integer.parseInt(courtChangeScore));
            UpdateGameConfigLogic gmLogic = new UpdateGameConfigLogic();
            boolean configResult = gmLogic.updateGameConfig(gameConfig);
            if (!configResult) {
                throw new JudgeBusinessException("データベースの更新に失敗しました。");
            }


		} catch (JudgeBusinessException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println(e.getMsgList());
			request.setAttribute("errorMsg", e.getMessage());
			request.setAttribute("errorMsgList", e.getMsgList());
			page = "/judgeFC?buttonId=p0000";
		}
		catch (JudgeSystemException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
			request.setAttribute("errorMsg", e.getMessage());
			page = "/judgeFC?buttonId=p9000";
		}
		
		return page;
    }
}
