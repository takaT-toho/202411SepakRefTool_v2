// GameView.js
export default class GameView {
    constructor(gameData) {
      this.gameData = gameData;
      // DOM要素を取得
      this.displayLeft = document.getElementById("scoreLeft");
      this.displayRight = document.getElementById("scoreRight");
      this.buttonLeft = document.getElementById("addLeftRegu");
      this.buttonRight = document.getElementById("addRightRegu");
      this.backButton = document.getElementById("backGame");
      this.setEndButton = document.getElementById("SetEndButton");
      this.displayMessage = document.getElementById("callMsg");
      this.teamLeft = document.getElementById("teamLeft");
      this.teamRight = document.getElementById("teamRight");
      this.serveGageLeft1 = document.getElementById("serveGageLeft1");
      this.serveGageLeft2 = document.getElementById("serveGageLeft2");
      this.serveGageLeft3 = document.getElementById("serveGageLeft3");
      this.serveGageRight1 = document.getElementById("serveGageRight1");
      this.serveGageRight2 = document.getElementById("serveGageRight2");
      this.serveGageRight3 = document.getElementById("serveGageRight3");
      this.setNumber1setLeft = document.getElementById("setNumber1setLeft");
      this.setNumber1setRight = document.getElementById("setNumber1setRight");
      this.setNumber2setLeft = document.getElementById("setNumber2setLeft");
      this.setNumber2setRight = document.getElementById("setNumber2setRight");
      this.setNumber3setLeft = document.getElementById("setNumber3setLeft");
      this.setNumber3setRight = document.getElementById("setNumber3setRight");
    }
  
    // スコア表示を更新する
    updateScoreDisplay(scoreLeft, scoreRight) {
      this.displayLeft.textContent = scoreLeft;
      this.displayRight.textContent = scoreRight;
    }
  
    // ボタンの表示状態を更新する
    updateButtonDisplay(isGameFinished, isSetFinished, serialNumber) {
      // 戻るボタン
      this.backButton.style.display = serialNumber === 0 ? "none" : "block";
  
      // セット終了ボタン
      this.setEndButton.style.display = isSetFinished ? "block" : "none";
  
      // 加算ボタン
      if (isGameFinished || isSetFinished) {
        this.buttonLeft.style.visibility = 'hidden';
        this.buttonRight.style.visibility = 'hidden';
      } else {
        this.buttonLeft.style.visibility = 'visible';
        this.buttonRight.style.visibility = 'visible';
      }
    }
  
    // コールテキストを更新する
    updateCallTextDisplay(callText) {
      this.displayMessage.textContent = callText;
    }
  
    // サーブ表示を更新する
    updateServeDisplay(isAreguServe, isAreguLeft, serialNumber, DEUCE_START_SCORE, setNow) {
      if (isAreguServe === isAreguLeft) {
        this.teamLeft.classList.add('active');
        this.teamRight.classList.remove('active');
        this.resetServeGage();
        if (serialNumber < (DEUCE_START_SCORE[setNow - 1] * 2 - 1)) {
          if (serialNumber % 3 === 0) {
            this.serveGageLeft1.classList.add('active');
          } else if (serialNumber % 3 === 1) {
            this.serveGageLeft1.classList.add('active');
            this.serveGageLeft2.classList.add('active');
          } else if (serialNumber % 3 === 2) {
            this.serveGageLeft1.classList.add('active');
            this.serveGageLeft2.classList.add('active');
            this.serveGageLeft3.classList.add('active');
          }
        }
      } else {
        this.teamLeft.classList.remove('active');
        this.teamRight.classList.add('active');
        this.resetServeGage();
        if (serialNumber < (DEUCE_START_SCORE[setNow - 1] * 2 - 1)) {
          if (serialNumber % 3 === 0) {
            this.serveGageRight1.classList.add('active');
          } else if (serialNumber % 3 === 1) {
            this.serveGageRight1.classList.add('active');
            this.serveGageRight2.classList.add('active');
          } else if (serialNumber % 3 === 2) {
            this.serveGageRight1.classList.add('active');
            this.serveGageRight2.classList.add('active');
            this.serveGageRight3.classList.add('active');
          }
        }
      }
    }
  
    // サーブゲージをリセットする
    resetServeGage() {
      this.serveGageLeft1.classList.remove('active');
      this.serveGageLeft2.classList.remove('active');
      this.serveGageLeft3.classList.remove('active');
      this.serveGageRight1.classList.remove('active');
      this.serveGageRight2.classList.remove('active');
      this.serveGageRight3.classList.remove('active');
    }
  
    // セットごとのスコア表示を更新する
    updateSetNumberDisplay(score, isLeftRegu, setNow) {
      if (isLeftRegu) {
        switch (setNow) {
          case 1:
            this.setNumber1setLeft.textContent = score;
            break;
          case 2:
            this.setNumber2setLeft.textContent = score;
            break;
          case 3:
            this.setNumber3setLeft.textContent = score;
            break;
          default:
            break;
        }
      } else {
        switch (setNow) {
          case 1:
            this.setNumber1setRight.textContent = score;
            break;
          case 2:
            this.setNumber2setRight.textContent = score;
            break;
          case 3:
            this.setNumber3setRight.textContent = score;
            break;
          default:
            break;
        }
      }
    }
  }