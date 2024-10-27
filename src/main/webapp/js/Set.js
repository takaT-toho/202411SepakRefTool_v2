// Set.js
export default class Set {
    constructor(gameData) {
      this.gameData = gameData;
    }
  
    // 試合が終了しているかどうかをチェック
    isSetFinished() {
      const { scoreLeft, scoreRight, setNow, MAX_SCORE, DEUCE_START_SCORE } = this.gameData;
      if (scoreLeft === MAX_SCORE[setNow - 1] || scoreRight === MAX_SCORE[setNow - 1]) {
        return true;
      } else if (((scoreLeft === DEUCE_START_SCORE[setNow - 1] + 1) && (scoreRight <= DEUCE_START_SCORE[setNow - 1] - 1)) ||
        ((scoreLeft <= DEUCE_START_SCORE[setNow - 1] - 1) && (scoreRight === DEUCE_START_SCORE[setNow - 1] + 1))) {
        return true;
      }
      return false;
    }
  
    // Aレグがこのセットを取ったのか判定する
    isAreguSetWin() {
      const { scoreLeft, scoreRight, scoreStockList, setGotByA, setGotByB, GAME_RULE, setNow, DEUCE_START_SCORE } = this.gameData;
      if (GAME_RULE === "drawGD") {
        const scoreA = this.gameData.judgeAreguLeft() ? scoreLeft : scoreRight;
        const scoreB = this.gameData.judgeAreguLeft() ? scoreRight : scoreLeft;
        let hasUniqueWinner = false;
        if ((scoreA > scoreB) && this.isMatchPoint('A')) {
          hasUniqueWinner = true;
        } else if ((scoreB > scoreA) && this.isMatchPoint('B')) {
          hasUniqueWinner = true;
        }
        if (!hasUniqueWinner) {
          const popped = scoreStockList.slice(-1)[0];
          const addScore = popped === 'L' ? 1 : -1;
          const goalDifference = this.getGoalDifference() + addScore;
          if (goalDifference > 0) {
            return this.gameData.judgeAreguLeft();
          } else if (goalDifference < 0) {
            return !this.gameData.judgeAreguLeft();
          } else {
            let returnValue = false;
            if (popped === 'L') {
              returnValue = this.gameData.judgeAreguLeft() ? true : false;
            } else if (popped === 'R') {
              returnValue = this.gameData.judgeAreguLeft() ? false : true;
            }
            // document.getElementById("isAreguSetWin").value = returnValue;
            throw new Error("Goal Difference is 0");
          }
        }
      }
      let popped = scoreStockList.slice(-1)[0];
      if (popped === 'L') {
        return this.gameData.judgeAreguLeft() ? true : false;
      } else if (popped === 'R') {
        return this.gameData.judgeAreguLeft() ? false : true;
      }
    }
  
    getGoalDifference() {
      const scoreLeft1set = parseInt(document.getElementById("setNumber1setLeft").textContent);
      const scoreRight1set = parseInt(document.getElementById("setNumber1setRight").textContent);
      const scoreLeft2set = parseInt(document.getElementById("setNumber2setLeft").textContent);
      const scoreRight2set = parseInt(document.getElementById("setNumber2setRight").textContent);
      return scoreLeft1set - scoreRight1set + scoreLeft2set - scoreRight2set;
    }
  
    // マッチポイントのレグを判定する
    isMatchPoint(team) {
      const { setGotByA, setGotByB } = this.gameData;
      if (team === 'A') {
        return setGotByA === 1;
      } else if (team === 'B') {
        return setGotByB === 1;
      }
      return false;
    }
  
    // 試合が終了しているかどうかを判定する
    isGameFinished() {
      const { setNow, MAX_SET, setGotByA, setGotByB } = this.gameData;
      if (setNow === MAX_SET) {
        return true;
      }
      if (this.isAreguSetWin()) {
        if (setGotByA === 1) {
          return true;
        }
      } else {
        if (setGotByB === 1) {
          return true;
        }
      }
      return false;
    }
  }