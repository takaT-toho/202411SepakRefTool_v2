package servlet;

import java.util.ArrayList;

import common.JudgeBusinessException;
import common.JudgeSystemException;
import entity.Game;
import entity.Player;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import logic.SelectPlayerLogic;

public class TwoMinutesWarmUpAction implements ActionIF {
    public String execute(HttpServletRequest request) {
		String page = "gameReady.jsp";
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

            // AレグとBレグの選手情報を取得
            SelectPlayerLogic logic = new SelectPlayerLogic();
            ArrayList<Player> playerListA = logic.findAllPlayersByReguId(game.getAreguId());
            ArrayList<Player> playerListB = logic.findAllPlayersByReguId(game.getBreguId());
			
			ArrayList<String> errorMsgList = new ArrayList<>();
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
			request.setAttribute("errorMsgList", e.getMessage());
			page = "index.jsp";
		} catch (JudgeSystemException e) {
			request.setAttribute("errorMsg", e.getMessage());
			page = "systemError.jsp";
		}
		
		return page;
	}

}
