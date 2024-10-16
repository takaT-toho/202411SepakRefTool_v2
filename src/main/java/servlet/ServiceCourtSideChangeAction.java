package servlet;

import java.util.ArrayList;

import common.JudgeBusinessException;
import common.JudgeSystemException;
import entity.Game;
import entity.GameConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import logic.SelectGameConfigLogic;
import logic.UpdateGameConfigLogic;

public class ServiceCourtSideChangeAction implements ActionIF {
    public String execute(HttpServletRequest request) {
        String page = "/judgeFC?buttonId=p0006";

		try {            
			// パラメータの取得
			String serve = request.getParameter("serve");
			String court = request.getParameter("court");

            ArrayList<String> errorMsgList = new ArrayList<>();
			// パラメータが未入力の場合
			if (serve == null || serve.equals("")) {
				errorMsgList.add("サーブ権結果が未入力です。");
			}
			if (court == null || court.equals("")) {
				errorMsgList.add("コート権結果が未入力です。");
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
			GameConfig gameConfig = new GameConfig();
			gameConfig.setGameId(game.getGameId());
			gameConfig.setIsAreguTossWin(gameConfig.getIsAreguTossWin());
			gameConfig.setIsAreguFirstServe(serve.equals("1"));
			gameConfig.setIsAreguLeft(court.equals("1"));
			UpdateGameConfigLogic gmLogic = new UpdateGameConfigLogic();
			boolean configResult = gmLogic.updateBasicGameConfig(gameConfig);
			if (!configResult) {
				throw new JudgeBusinessException("データベースの更新に失敗しました。");
			}
			SelectGameConfigLogic selectGameConfigLogic = new SelectGameConfigLogic();
			gameConfig = selectGameConfigLogic.selectGameConfig(game.getGameId());
			if (gameConfig == null) {
				throw new JudgeBusinessException("試合が見つかりません。");
			}
			session.setAttribute("gameConfig", gameConfig);

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