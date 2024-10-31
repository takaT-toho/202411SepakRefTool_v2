package servlet;

import common.JudgeBusinessException;
import common.JudgeSystemException;
import entity.PointJSON;
import logic.UpdateGameLogic;

public class UpdateIsSetFinishedAction {

    public UpdateIsSetFinishedAction() {
    }

    public boolean execute(PointJSON pointJson) {
        boolean result = false;
        try {

            // メイン処理 =========================================================
            // パラメータの取得
            String gameId = pointJson.getGameId();
            int setNow = pointJson.getSetNum();
            if (gameId == null || gameId.equals("")) {
                throw new JudgeBusinessException("gameId is null or empty.");
            }
            
            // 試合情報を更新する
            UpdateGameLogic gameLogic = new UpdateGameLogic();
            gameLogic.updateGameWhenSetFinishedTemp(Integer.parseInt(gameId) , setNow);

            result = true;

        } catch (JudgeBusinessException e) {
            e.printStackTrace();
        } catch (JudgeSystemException e) {
            e.printStackTrace();
        }
        return result;
    }

}
