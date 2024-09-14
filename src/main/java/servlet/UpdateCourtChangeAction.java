package servlet;

import common.JudgeBusinessException;
import common.JudgeSystemException;
import entity.PointJSON;
import logic.UpdateGameDetailLogic;

public class UpdateCourtChangeAction {

    public UpdateCourtChangeAction() {
    }

    public boolean execute(PointJSON pointJson) {
        boolean result = false;
        try {

            // メイン処理 =========================================================
            // パラメータの取得
            String gameId = pointJson.getGameId();
            boolean is3setCourtChanged = pointJson.getIs3setCourtChanged();
            if (gameId == null || gameId.equals("")) {
                throw new JudgeBusinessException("gameId is null or empty.");
            }
            
            // 得点加算処理
            // GameDetailへの登録
            UpdateGameDetailLogic logic = new UpdateGameDetailLogic();
            boolean res = logic.UpdateCourtChange(Integer.parseInt(gameId), is3setCourtChanged);
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
