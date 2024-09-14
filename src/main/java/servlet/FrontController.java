package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontController
 */
@WebServlet( urlPatterns = {"", "/judgeFC"} )
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 遷移先ページの設定
		String path = "WEB-INF/jsp/";
		String page = null;
		boolean shouldRequestDispatcher = false;
		boolean shouldDoPost = false;

		try {
			//パラメータの取得
			request.setCharacterEncoding("UTF-8");
			String buttonId = request.getParameter("buttonId");
			System.out.println("GET:buttonId:" + buttonId);
			
			// パラメータがない場合
			if (buttonId == null || buttonId.equals("")) {
				// デフォルト値
				buttonId = "p0000";
			}

			// リクエスト種別の判定
			switch (buttonId) {
			case "p0000":
				// デフォルト画面：ログイン画面
				page = "index.jsp";
				shouldRequestDispatcher = true;
				break;
			case "p0002":
				// QRログイン処理
				shouldDoPost = true;
				break;
			case "p0101":
				// メイン画面：メニュー：試合基本情報
				shouldDoPost = true;
				break;
			case "p0102":
				// メイン画面：メニュー：タイムアウト
				shouldDoPost = true;
				break;
			case "p0103":
				// メイン画面：メニュー：選手交代
				shouldDoPost = true;
				break;
			case "p0104":
				// メイン画面：メニュー：サービス/コートサイド選択
				shouldDoPost = true;
				break;
			case "p0105":
				// メイン画面：メニュー：得点経過表
				shouldDoPost = true;
				break;
			case "p0106":
				// メイン画面：メニュー：試合設定
				shouldDoPost = true;
				break;
			case "p0202":			
				// セット終了処理
				shouldDoPost = true;
				break;
			case "p0210":			
				// 試合終了処理
				shouldDoPost = true;
				break;
			// デフォルト画面
			default:
				// buttonIdが存在しない場合
				page = "index.jsp";
				shouldRequestDispatcher = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			page = "index.jsp";
			shouldRequestDispatcher = true;
		}

		if (shouldRequestDispatcher) {
			// 結果画面に転送
			RequestDispatcher rd = request.getRequestDispatcher(path + page);
			rd.forward(request, response);
		}

		if (shouldDoPost) {
			doPost(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 遷移先ページの設定
		String path = "WEB-INF/jsp/";
		String page = null;
		// Actionクラス
		ActionIF action = null;
		boolean shouldRedirect = false;

		try {
			//パラメータの取得
			request.setCharacterEncoding("UTF-8");
			String buttonId = request.getParameter("buttonId");
			System.out.println("buttonId:" + buttonId);
			
			// パラメータがない場合
			if (buttonId == null || buttonId.equals("")) {
				// デフォルト値
				buttonId = "p0000";
			}

			// リクエスト種別の判定
			switch (buttonId) {
			case "p0000":
				// デフォルト画面：ログイン画面
				action = new IndexAction();
				break;
			// ログイン処理
			case "p0001":
				// 通常ログイン処理
				action = new LoginAction();
				break;
			case "p0002":
				// QRログイン処理
				action = new QRLoginAction();
				break;	
			// 初期設定処理
			case "p0003":
				// 署名確認処理 > トス・サーブ画面
				action = new SignCheckAction();
				break;
			case "p0004":
				// トス・サーブ権処理 > 2分練画面
				action = new TossAndServeAction();
				break;
			case "p0005":
				// 2分練ページ処理 > ゲーム開始画面
				action = new TwoMinutesWarmUpAction();
				break;
			case "p0006":
				// ゲーム開始処理 > メイン画面
				action = new GameStartAction();
				break;
			// メイン画面：ヘッダーメニュー
			case "p0101":
				// メイン画面：メニュー：試合基本情報
				action = new GameBasicInfoAction();
				break;
			case "p0102":
				// メイン画面：メニュー：タイムアウト
				action = new TimeoutAction();
				break;
			case "p0103":
				// メイン画面：メニュー：選手交代
				action = new PlayerSubstitutionAction();
				break;
			case "p0104":
				// メイン画面：メニュー：サービス/コートサイド選択
				action = new ServiceCourtSideAction();
				break;
			case "p0105":
				// メイン画面：メニュー：得点経過表
				action = new PointProgressAction();
				break;
			case "p0106":
				// メイン画面：メニュー：試合設定
				action = new GameSettingAction();
				break;
			// セット終了処理
			case "p0201":
				// セット終了処理 > 次セット画面
				action = new SetEndAction();				
				shouldRedirect = true;
				break;
			case "p0202":
				// セット終了処理 > 次セット画面
				action = new MainPageAction();
				break;
			// 試合終了処理
			case "p0210":
				// 試合終了処理 > 試合終了画面
				action = new GameEndAction();
				break;		
			case "p9000":
				// システムエラー画面
				action = new SystemErrorAction();
				break;
			// デフォルト画面
			default:
				// buttonIdが存在しない場合
				action = new IndexAction();
			}

			page = action.execute(request);
			// リダイレクトの場合
			if (shouldRedirect) {
				response.sendRedirect(request.getContextPath() + page);
				return;
			}
			// 結果画面に転送
			RequestDispatcher rd = request.getRequestDispatcher(path + page);
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			// デフォルト画面：ログイン画面
			action = new IndexAction();
			page = action.execute(request);
			// 結果画面に転送
			RequestDispatcher rd = request.getRequestDispatcher(path + page);
			rd.forward(request, response);
		}
		
		
	}

}
