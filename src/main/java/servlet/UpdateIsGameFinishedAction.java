package servlet;

import common.JudgeBusinessException;
import common.JudgeSystemException;
import entity.PointJSON;
import logic.UpdateGameLogic;

public class UpdateIsGameFinishedAction {

    public UpdateIsGameFinishedAction() {
    }

    public boolean execute(PointJSON pointJson) {
        boolean result = false;
        try {

            // メイン処理 =========================================================
            // パラメータの取得
            String gameId = pointJson.getGameId();
            boolean isGameFinished = pointJson.getIsGameFinished();
            if (gameId == null || gameId.equals("")) {
                throw new JudgeBusinessException("gameId is null or empty.");
            }
            
            // 得点加算処理
            // GameDetailへの登録
            UpdateGameLogic logic = new UpdateGameLogic();
            boolean res = logic.UpdateIsGameFinished(Integer.parseInt(gameId), isGameFinished);
            if (res == false) {
                throw new JudgeBusinessException("データベースの更新に失敗しました。");
            }

            result = true;

        } catch (JudgeBusinessException e) {
            e.printStackTrace();
        } catch (JudgeSystemException e) {
            e.printStackTrace();
        }
        return result;
    }

}
