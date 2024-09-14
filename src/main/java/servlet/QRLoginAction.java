package servlet;

import java.util.ArrayList;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import common.JudgeBusinessException;
import common.JudgeSystemException;
import entity.Court;
import entity.Game;
import entity.LoginMap;
import entity.Regu;
import logic.SelectCourtLogic;
import logic.SelectGameLogic;
import logic.SelectReguLogic;
import logic.UpdateCourtLogic;
// import logic.UpdateLoginStatusLogic;

public class QRLoginAction implements ActionIF {
	
	public String execute(HttpServletRequest request) {
		String page = "sign.jsp";
		try {
			// パラメータの取得
			String courtName = request.getParameter("courtName");
			String pass = request.getParameter("pass");
			
			ArrayList<String> errorMsgList = new ArrayList<>();
			// パラメータが未入力の場合
			if (courtName == null || courtName.equals("")) {
				errorMsgList.add("コート名が未入力です。");
			}
			if (pass == null || pass.equals("")) {
				errorMsgList.add("パスワードが未入力です。");
			}
			if (!errorMsgList.isEmpty()) {
				throw new JudgeBusinessException(errorMsgList);
			}
			
			// 業務ロジックの呼び出し
			SelectCourtLogic sLogic = new SelectCourtLogic();
			Court court = sLogic.selectCourtByCourtName(courtName);		
			// パスワードの確認
			if (!court.getPass().equals(pass)) {
				throw new JudgeBusinessException("パスワードが合っていません。");
			}

			// セッションチェック
			boolean result = SessionCheck.checkSession(request, court.getCourtId());
			if (!result) {
				throw new JudgeBusinessException("既に他のユーザーがログインしています。");
			}
			
			// セッションの生成
			HttpSession session = request.getSession(true);
			
			// 業務ロジックの呼び出し
			SelectGameLogic logic = new SelectGameLogic();
			Game game = logic.selectLatestGameByCourtName(courtName);
			if (game == null) {
				throw new JudgeBusinessException("「" + courtName + "」というコートが見つかりません。");
			}
			// 試合が終了していたらエラー
			boolean isFinished = game.getIsFinished();
			if (isFinished) {
				throw new JudgeBusinessException("既にこの試合は終了しています");
			}

			// 試合情報を取得する
			game = logic.selectGameByGameId(game.getGameId());			

			// AレグとBレグの名前情報を取得する
			SelectReguLogic srLogic = new SelectReguLogic();
			Regu reguA = srLogic.findReguByReguId(game.getAreguId());
			Regu reguB = srLogic.findReguByReguId(game.getBreguId());
			Regu mainRef = srLogic.findReguByReguId(game.getMainJudgeReguId());
			Regu subRef = srLogic.findReguByReguId(game.getSubJudgeReguId());
			
			// 情報をセッションに保存
			session.setAttribute("game", game);
			session.setAttribute("reguA", reguA);
			session.setAttribute("reguB", reguB);
			session.setAttribute("mainRef", mainRef);
			session.setAttribute("subRef", subRef);
			session.setAttribute("court", courtName);
			session.setAttribute("game", game);

			// コートログイン情報をDBに登録
			LoginMap loginMap = new LoginMap();
			loginMap.setCourtId(court.getCourtId());
			loginMap.setSessionId(session.getId());
			loginMap.setLastUpdate(System.currentTimeMillis());
			UpdateCourtLogic cLogic = new UpdateCourtLogic();
			cLogic.updateCourtLoginInfo(loginMap);

		} catch (JudgeBusinessException e) {
			request.setAttribute("errorMsg", e.getMessage());
			page = "index.jsp";
		} catch (JudgeSystemException e) {
			request.setAttribute("errorMsg", e.getMessage());
			page = "systemError.jsp";
		}
		
		return page;
	}

}
