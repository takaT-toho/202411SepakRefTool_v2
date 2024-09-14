package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import common.JudgeBusinessException;
import common.JudgeSystemException;
import entity.Game;
import entity.GameDetail;
import entity.PointJSON;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logic.SelectGameDetailLogic;
import logic.SelectGameLogic;

/**
 * Servlet implementation class FrontController
 */
@WebServlet( urlPatterns = {"/async"} )
public class FrontControllerAsync extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontControllerAsync() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // レスポンス結果格納用
        boolean result = false;

		try {            
            // 前処理 ===========================================================
            // リクエストボディを読み取る
            StringBuilder buffer = new StringBuilder();
            String line;
            try (BufferedReader reader = request.getReader()) {
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
            }
            String data = buffer.toString();
            
            // JSONをJavaオブジェクトに変換
            Gson gson = new Gson();
            PointJSON pointJson = gson.fromJson(data, PointJSON.class);
            // ====================================================================

			//パラメータの取得
			String buttonId = pointJson.getButtonId();
			System.out.println("buttonId:async" + buttonId);	
			if (buttonId == null || buttonId.equals("")) {
				throw new Exception("buttonId is null or empty.");
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
			boolean isOK = SessionCheck.checkSession(request, game.getCourtId());
			if (!isOK) {
				throw new JudgeBusinessException("セッションが切れました。再度ログインしてください。");
			}

			// リクエスト種別の判定
			switch (buttonId) {
			case "p0150":
				// メイン画面：得点加算
				result = new PointAddAction().execute(pointJson);
				break;
            case "p0151":
                // メイン画面：タイムアウト
                result = new UpdateTimeoutAction().execute(pointJson);
                break;
            case "p0152":
                // メイン画面：選手交代
                result = new UpdatePlayerSubstituteAction().execute(pointJson);
                if (result == false) {
                    throw new Exception("UpdatePlayerSubstituteAction failed.");
                } 
                // 選手情報の更新
                String res = new UpdateSessionPlayerList().execute(request);
                if (!res.equals("ok")) {
                    throw new Exception("UpdateSessionPlayerList failed.");
                }
                result = true;
                break;
            case "p0160":
                // メイン画面：得点減算
                result = new PointSubtractAction().execute(pointJson);
                break;
            case "p0200":
                // 3セット目コートチェンジ
                result = new UpdateCourtChangeAction().execute(pointJson);   
            
                // 最新の試合詳細情報を取得する
                SelectGameDetailLogic selectGameDetailLogic = new SelectGameDetailLogic();
                GameDetail gameDetail = selectGameDetailLogic.findGameDetailByGameId(game.getGameId());             
                session.setAttribute("gameDetail", gameDetail);
                break;
            case "p0210":
                // 試合終了
                result = new UpdateIsGameFinishedAction().execute(pointJson);   
            
                // 最新の試合情報を取得する
                SelectGameLogic selectGameLogic = new SelectGameLogic();
                game = selectGameLogic.selectGameByGameId(game.getGameId());             
                session.setAttribute("game", game);
                break;
            }
            
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
            // 後処理 =============================================================
            // result == falseの場合、エラー画面に遷移
            if (result == false) {
                String page = "WEB-INF/jsp/systemError.jsp";
                // 結果画面に転送
                RequestDispatcher rd = request.getRequestDispatcher(page);
                rd.forward(request, response);
            } else {
                // レスポンスの設定
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");

                JsonObject jsonResponse = new JsonObject();
                jsonResponse.addProperty("success", result);

                try {
                    PrintWriter out = response.getWriter();
                    out.print(jsonResponse.toString());
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // ====================================================================
        }
		
		
	}

}
