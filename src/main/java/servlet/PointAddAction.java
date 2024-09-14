package servlet;

import common.JudgeBusinessException;
import common.JudgeSystemException;
import entity.GameEventHistory;
import entity.PointJSON;
import logic.UpdateGameDetailLogic;
import logic.UpdateGameEventHistoryLogic;

public class PointAddAction {

    public PointAddAction() {
    }

    public boolean execute(PointJSON pointJson) {
        boolean result = false;
        
        try {

            // メイン処理 =========================================================
            // パラメータの取得
            String gameId = pointJson.getGameId();
            int setNum = pointJson.getSetNum();
            String type = pointJson.getType();
            String firstDetail = pointJson.getFirstDetail();
            String secondDetail = pointJson.getSecondDetail();
            boolean isSequential = pointJson.getIsSequential();
            boolean isAreguGot = pointJson.getIsAreguGot();
            int serialNumber = pointJson.getSerialNumber();
            if (gameId == null || gameId.equals("")) {
                throw new JudgeBusinessException("gameId is null or empty.");
            }
            if (setNum == 0) {
                throw new JudgeBusinessException("setNum is 0.");
            }
            if (type == null || type.equals("") || !type.equals("ADDPOINTS")) {
                throw new JudgeBusinessException("type is null or empty or invalid.");
            }
            if (firstDetail == null || firstDetail.equals("")) {
                throw new JudgeBusinessException("firstDetail is null or empty.");
            }
            if (serialNumber < 0) {
                throw new JudgeBusinessException("serialNumber is invalid.");
            }
            
            // 得点加算処理
            // GameDetailへの登録
            UpdateGameDetailLogic logic = new UpdateGameDetailLogic();
            boolean res = logic.addPoints(Integer.parseInt(gameId), setNum, isAreguGot, serialNumber);
            if (res == false) {
                throw new JudgeBusinessException("データベースの更新に失敗しました。");
            }
            // GameEventHistoryへの登録
            GameEventHistory gameEventHistory = new GameEventHistory(
                Integer.parseInt(gameId), setNum, type, 
                firstDetail, secondDetail,
			    isSequential, isAreguGot);
            UpdateGameEventHistoryLogic gLogic = new UpdateGameEventHistoryLogic();
            boolean res2 = gLogic.insertGameEventHistory(gameEventHistory);
            if (res2 == false) {
                throw new JudgeBusinessException("データの更新に失敗しました");
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