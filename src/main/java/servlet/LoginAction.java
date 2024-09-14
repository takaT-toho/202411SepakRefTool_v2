package servlet;

import java.util.ArrayList;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import common.JudgeBusinessException;
import common.JudgeSystemException;
import entity.Game;
import entity.LoginMap;
import entity.Regu;
import logic.SelectCourtLogic;
import logic.SelectGameLogic;
import logic.SelectReguLogic;
import logic.UpdateCourtLogic;

public class LoginAction implements ActionIF {
	
	public String execute(HttpServletRequest request) {
		String page = "sign.jsp";
		try {
			// パラメータの取得
			String gameId = request.getParameter("gameId");
			String pass = request.getParameter("pass");
			
			ArrayList<String> errorMsgList = new ArrayList<>();
			// パラメータが未入力の場合
			if (gameId == null || gameId.equals("")) {
				errorMsgList.add("試合番号が未入力です。");
			}
			if (pass == null || pass.equals("")) {
				errorMsgList.add("パスワードが未入力です。");
			}
			if (!errorMsgList.isEmpty()) {
				throw new JudgeBusinessException(errorMsgList);
			}
			
			// 業務ロジックの呼び出し
			SelectGameLogic logic = new SelectGameLogic();
			Game game = logic.selectGameByGameId(Integer.parseInt(gameId));
			if (game == null) {
				throw new JudgeBusinessException("試合番号またはパスワードが違います。");
			}
			// パスワードのチェック
			if (!pass.equals(game.getPass())) {
				throw new JudgeBusinessException("試合番号またはパスワードが違います。");
			}
			// ここで第一関門を突破している

			// 試合が終了している場合			
			if (game.getIsFinished()) {
				throw new JudgeBusinessException("この試合は終了しています。");
			}

			// セッションチェック
			boolean result = SessionCheck.checkSession(request, game.getCourtId());
			if (!result) {
				throw new JudgeBusinessException("既に他のユーザーがログインしています。");
			}
			// ここで第二関門を突破している

			// セッションの生成
			HttpSession session = request.getSession(true);

			// コートログイン情報をDBに登録
			LoginMap loginMap = new LoginMap();
			loginMap.setCourtId(game.getCourtId());
			loginMap.setSessionId(session.getId());
			loginMap.setLastUpdate(System.currentTimeMillis());			
			UpdateCourtLogic cLogic = new UpdateCourtLogic();
			cLogic.updateCourtLoginInfo(loginMap);

			// AレグとBレグの名前情報を取得する
			SelectReguLogic sLogic = new SelectReguLogic();
			Regu reguA = sLogic.findReguByReguId(game.getAreguId());
			Regu reguB = sLogic.findReguByReguId(game.getBreguId());
			Regu mainRef = sLogic.findReguByReguId(game.getMainJudgeReguId());
			Regu subRef = sLogic.findReguByReguId(game.getSubJudgeReguId());

			// コート情報を取得する
			SelectCourtLogic cLogic2 = new SelectCourtLogic();
			String courtName = cLogic2.selectCourtByCourtId(game.getCourtId()).getName();
			
			// 情報の格納
			session.setAttribute("game", game);
			session.setAttribute("reguA", reguA);
			session.setAttribute("reguB", reguB);
			session.setAttribute("mainRef", mainRef);
			session.setAttribute("subRef", subRef);
			session.setAttribute("court", courtName);
			
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
