// Score.js
export default class Score {
    constructor(gameData) {
      this.gameData = gameData;
      this.scoreLeft = parseInt(document.getElementById("scoreLeft").textContent);
      this.scoreRight = parseInt(document.getElementById("scoreRight").textContent);
    }
  
    addLeftScore() {
      this.scoreLeft++;
      this.gameData.scoreStockList.push('L');
      this.gameData.serialNumber++;
      this.updateGameData();
    }
  
    addRightScore() {
      this.scoreRight++;
      this.gameData.scoreStockList.push('R');
      this.gameData.serialNumber++;
      this.updateGameData();
    }
  
    backScore() {
      const popped = this.gameData.scoreStockList.slice(-1)[0];
      if (popped === 'L' && this.scoreLeft > 0) {
        this.scoreLeft--;
        this.gameData.serialNumber--;
      } else if (popped === 'R' && this.scoreRight > 0) {
        this.scoreRight--;
        this.gameData.serialNumber--;
      }
      this.gameData.scoreStockList.pop();
      this.updateGameData();
    }
  
    updateGameData() {
      this.gameData.is3setCourtChanged = this.checkChangeCourtIn3set();
      sessionStorage.setItem('scoreStockList', JSON.stringify(this.gameData.scoreStockList));
    }
  
    // 3セット目のコートチェンジを判定する
    checkChangeCourtIn3set() {
      if (this.gameData.is3setCourtChanged) return true;
      if (this.gameData.setNow === 3 && (this.scoreLeft === this.gameData.COURT_CHANGE_SCORE || this.scoreRight === this.gameData.COURT_CHANGE_SCORE)) {
        return true;
      }
      return false;
    }
  }