package servlet;

import common.JudgeBusinessException;
import common.JudgeSystemException;
import entity.GameEventHistory;
import entity.PointJSON;
import logic.UpdateGameDetailLogic;
import logic.UpdateGameEventHistoryLogic;

public class PointSubtractAction {

    public PointSubtractAction() {
    }

    public boolean execute(PointJSON pointJson) {
        boolean result = false;
        
        try {

            // メイン処理 =========================================================
            // パラメータの取得
            String gameId = pointJson.getGameId();
            if (gameId == null || gameId.equals("")) {
                throw new JudgeBusinessException("gameId is null or empty.");
            }
            
            // 得点減算処理            
            // GameEventHistoryの更新
            UpdateGameEventHistoryLogic gLogic = new UpdateGameEventHistoryLogic();
            GameEventHistory geh = gLogic.deleteGameEventHistory(Integer.parseInt(gameId));
            if (geh == null) {
                throw new JudgeBusinessException("データの更新に失敗しました");
            }
            // GameDetailの更新
            UpdateGameDetailLogic logic = new UpdateGameDetailLogic();
            boolean res = logic.subtractPoints(Integer.parseInt(gameId), geh.getSetNum(), geh.getIsAreguGot());
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
