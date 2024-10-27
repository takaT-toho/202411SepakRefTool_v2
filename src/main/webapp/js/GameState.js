// GameState.js
export default class GameState {
    constructor(gameData, gameController) {
      this.gameData = gameData;
      this.gameController = gameController;
    }
  
    // アクションハンドラ
    async handleAction(action) {
      switch (action) {
        case "addLeftScore":
          this.gameData.score.addLeftScore();
          await this.gameController.handleScoreUpdate();
          break;
        case "addRightScore":
          this.gameData.score.addRightScore();
          await this.gameController.handleScoreUpdate();
          break;
        case "backScore":
          this.gameData.score.backScore();
          await this.gameController.handleScoreUpdate();
          break;
        case "endSet":
          await this.gameController.handleSetEnd();
          break;
        default:
          break;
      }
  
      if (this.gameData.score.checkChangeCourtIn3set()) {
        this.gameController.handleCourtChange();
      }
  
      // 状態変更を通知
      this.gameController.notify();
    }
  }