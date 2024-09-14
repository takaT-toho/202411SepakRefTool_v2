package servlet;

import common.JudgeBusinessException;
import common.JudgeSystemException;
import entity.Court;
import entity.LoginMap;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import logic.SelectCourtLogic;
import logic.UpdateCourtLogic;

public class SessionCheck {
    
    public static boolean checkSession(HttpServletRequest request, int courtId) {
        // セッションチェック結果
        boolean result = false;

        try {
            // セッションの取得
            HttpSession session = request.getSession(false);
            // コートのログイン情報の取得
            SelectCourtLogic logic = new SelectCourtLogic();
            Court court = logic.selectCourtByCourtId(courtId);
            if (court == null) {
                throw new JudgeBusinessException("コート情報が取得できませんでした。");
            }
            // セッションが存在しない場合
            if (session == null) {
                // DBにセッションIdがない場合
                if (court.getSessionId() == null) {
                    result = true;
                } else {
                    // DBにセッションIdがある場合
                    // 最新更新日時が5分以上経過している場合
                    // または最新更新日時が0の場合
                    if ((System.currentTimeMillis() - court.getLastUpdate() > 300000) || court.getLastUpdate() == 0) {
                        result = true;
                    } else {
                        throw new JudgeBusinessException("既に他のユーザーがログインしています。");
                    }
                }
                if (result) {
                    // セッションの取得
                    session = request.getSession(true);
                }
            } else {
                // セッションが存在する場合
                // DBにセッションIdがない場合
                if (court.getSessionId() == null) {
                    result = true;
                } else {
                    // DBにセッションIdがある場合
                    if (!session.getId().equals(court.getSessionId())) {
                        // セッションIDが異なる場合
                        // 最新更新日時が5分以上経過している場合
                        // または最新更新日時が0の場合
                        if ((System.currentTimeMillis() - court.getLastUpdate() > 300000) || court.getLastUpdate() == 0) {
                            result = true;
                        } else {
                            throw new JudgeBusinessException("既に他のユーザーがログインしています。");
                        }
                    } else {
                        // セッションIDが同じ場合
                        result = true;
                    }                    
                }
            }

            if (result) {                
                // DBのコートログイン情報を更新
                LoginMap loginMap = new LoginMap();
                loginMap.setCourtId(courtId);
                loginMap.setSessionId(session.getId());
                loginMap.setLastUpdate(System.currentTimeMillis());
                UpdateCourtLogic cLogic = new UpdateCourtLogic();
                cLogic.updateCourtLoginInfo(loginMap);
            }
        } catch (JudgeBusinessException e) {
            request.setAttribute("errorMsgList", e.getMessage());
        } catch (JudgeSystemException e) {
            request.setAttribute("errorMsg", e.getMessage());
        }
        return result;
    }

}
