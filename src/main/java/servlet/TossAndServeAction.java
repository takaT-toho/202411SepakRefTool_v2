package servlet;

import java.util.ArrayList;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import logic.SelectGameConfigLogic;
import logic.SelectPlayerLogic;
import logic.UpdateGameConfigLogic;
import common.JudgeBusinessException;
import common.JudgeSystemException;
import entity.Game;
import entity.GameConfig;
import entity.Player;
import entity.Regu;

public class TossAndServeAction implements ActionIF {

	public String execute(HttpServletRequest request) {
		String page = "gameReady.jsp";
		try {
			// パラメータの取得
			String isAreguTossWin = request.getParameter("isAreguTossWin");
			String serve = request.getParameter("serve");
			String court = request.getParameter("court");
			
			ArrayList<String> errorMsgList = new ArrayList<>();
			// パラメータが未入力の場合
			if (isAreguTossWin == null || isAreguTossWin.equals("")) {
				errorMsgList.add("コイントス勝者が未入力です。");
			}
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
				throw new JudgeBusinessException("セッションが切れました。再度ログインしてください。");
			}
			Game game = (Game) session.getAttribute("game");
			if (game == null) {
				throw new JudgeBusinessException("セッションが切れました。再度ログインしてください。");
			}

			// // セッションチェック
			boolean result = SessionCheck.checkSession(request, game.getCourtId());
			if (!result) {
				throw new JudgeBusinessException("セッションが切れました。再度ログインしてください。");
			}
			
			// 試合の設定を更新する
			GameConfig gameConfig = new GameConfig();
			gameConfig.setGameId(game.getGameId());
			gameConfig.setIsAreguTossWin(isAreguTossWin.equals("1"));
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

			// セッションからAレグとBレグの名前情報を取得する
			Regu reguA = (Regu) session.getAttribute("reguA");
			Regu reguB = (Regu) session.getAttribute("reguB");

			// トス勝者とトス敗者を変数に格納する
			session.setAttribute("tossWinner", isAreguTossWin.equals("1") ? reguA.getName() : reguB.getName());
			session.setAttribute("tossLoser", isAreguTossWin.equals("1") ? reguB.getName() : reguA.getName());

			

            // AレグとBレグの選手情報を取得
            SelectPlayerLogic logic = new SelectPlayerLogic();
            ArrayList<Player> playerListA = logic.findAllPlayersByReguId(game.getAreguId());
            ArrayList<Player> playerListB = logic.findAllPlayersByReguId(game.getBreguId());
			
			if (playerListA.size() == 0) {
                errorMsgList.add("Aレグの選手情報がありません");
            }	
            if (playerListB.size() == 0) {
                errorMsgList.add("Bレグの選手情報がありません");
            }
            // エラーメッセージがある場合
            // 業務例外をスローする
            if (!errorMsgList.isEmpty()) {
                throw new JudgeBusinessException(errorMsgList);
            }
			
			// 情報の格納
            session.setAttribute("playerListA", playerListA);
            session.setAttribute("playerListB", playerListB);
			
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
