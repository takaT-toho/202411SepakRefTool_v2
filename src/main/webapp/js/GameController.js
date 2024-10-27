// GameController.js
import GameData from "./GameData.js";
import Score from "./Score.js";
import Set from "./Set.js";
import GameEventHistory from "./GameEventHistory.js";
import GameView from "./GameView.js";
import GameState from "./GameState.js";
import Observer from "./Observer.js";

export default class GameController extends Observer {
  constructor() {
    super();
    this.gameData = new GameData();
    this.score = new Score(this.gameData);
    this.set = new Set(this.gameData);
    this.gameEventHistory = new GameEventHistory(this.gameData);
    this.gameView = new GameView(this.gameData);
    this.gameState = new GameState(this.gameData, this);
    this.initialize();
  }

  initialize() {
    this.attach(this.gameView);
    this.updateButtonDisplay();
    this.updateCallTextDisplay();
    this.updateServeDisplay();
    this.addEventListeners();
  }

  addEventListeners() {
    // 加算ボタン
    this.gameView.buttonLeft.addEventListener('click', async () => {
      await this.gameState.handleAction("addLeftScore");
    });

    this.gameView.buttonRight.addEventListener('click', async () => {
      await this.gameState.handleAction("addRightScore");
    });

    // 戻るボタン
    this.gameView.backButton.addEventListener('click', async () => {
      await this.gameState.handleAction("backScore");
    });

    // セット終了ボタン
    this.gameView.setEndButton.addEventListener('click', async () => {
      await this.gameState.handleAction("endSet");
    });
  }

  updateButtonDisplay() {
    this.gameView.updateButtonDisplay(this.set.isGameFinished(), this.set.isSetFinished(), this.gameData.serialNumber);
  }

  updateCallTextDisplay() {
    this.gameView.updateCallTextDisplay(this.generateCallText());
  }

  updateServeDisplay() {
    this.gameView.updateServeDisplay(
      this.gameData.judgeAreguServe(this.gameData.isAreguServeArray[this.gameData.serialNumber]),
      this.gameData.judgeAreguLeft(),
      this.gameData.serialNumber,
      this.gameData.DEUCE_START_SCORE,
      this.gameData.setNow
    );
  }

  // コールテキストを生成する
  generateCallText() {    
    const { scoreLeft, scoreRight, setNow, AreguName, BreguName } = this.gameData;
    if (isSetFinished) {
		// 試合が終了している場合
		if (setNow === MAX_SET) {
			return generateGameEndCallText();
		// 次のセットに進む場合
		} else {
			return generateSetEndCallText();
		}
	}
	// セット開始時のコールテキストを生成
	if (scoreLeft === 0 && scoreRight === 0) {
		return generateFirstCallText();
	// デュースの場合
	} else if (scoreLeft === DEUCE_START_SCORE[setNow - 1] 
		&& scoreRight === DEUCE_START_SCORE[setNow - 1]) {
		let text = "セッティング アップ トゥー ";
		text += this.gameData.convertNumberToCallText[MAX_SCORE[setNow - 1]][1] + " ";
		text += this.gameData.convertNumberToCallText[scoreLeft][1] + " オール";
		return text;
	// セット終了間際の場合
	} else if (scoreLeft == scoreRight == (MAX_SCORE[setNow - 1] - 1)) {
		return generateFinalCallText();
	// デュース以外で同点の場合
	} else if (scoreLeft == scoreRight) {
		return this.gameData.convertNumberToCallText[scoreLeft][1] + " オール";
	// 通常セットポイントの場合
	} else if (((scoreLeft === DEUCE_START_SCORE[setNow - 1])
		&& (scoreRight <= (DEUCE_START_SCORE[setNow - 1] - 1))) 
		|| ((scoreRight === DEUCE_START_SCORE[setNow - 1]) 
		&& (scoreLeft <= (DEUCE_START_SCORE[setNow - 1] - 1)))
		|| ((scoreLeft === (MAX_SCORE[setNow - 1] - 1))
		&& (scoreRight <= (MAX_SCORE[setNow - 1] - 2)))
		|| ((scoreRight === (MAX_SCORE[setNow - 1] - 1))
		&& (scoreLeft <= (MAX_SCORE[setNow - 1] - 2)))) {
		return generateFinalCallText();
	// デュース後、セットポイントの場合
	} else if (scoreLeft >= DEUCE_START_SCORE[setNow - 1] 
		|| scoreRight >= DEUCE_START_SCORE[setNow - 1]) {
		return generateFinalCallText();
	// 通常の場合
	} else {
		return generateNormalCallText();
	}
  }

  
// セット開始時のコールテキストを生成する
generateFirstCallText() {
	let callTextFirst;

	switch (setNow) {
		case 1:
			callTextFirst = "ファーストセット ";
			break;
		case 2:
			callTextFirst = "セカンドセット ";
			break;
		case 3:
			callTextFirst = "サードセット ";
			break;
		default:
			callTextFirst = "";
	}

	const serverName = this.gameData.judgeAreguServe(this.gameData.isAreguServeArray[serialNumber]) ? AreguName : BreguName;
	callTextFirst += serverName + " サーブ ラブ オール";
	
	return callTextFirst;
}

// セット終了間際のコールテキストを生成する
generateFinalCallText() {
	var callText;
	let scoreA = this.gameData.judgeAreguLeft() ? scoreLeft : scoreRight;
	let scoreB = this.gameData.judgeAreguLeft() ? scoreRight : scoreLeft;
	let popped = scoreStockList.slice(-1)[0];
	let isLeftReguPointGot = popped === 'L';
	let isAreguPointGot = this.gameData.judgeAreguLeft() === isLeftReguPointGot;
	
	if (scoreA > scoreB) {
		callText = isMatchPoint('A') ? " マッチポイント " : " セットポイント ";
	} else {
		callText = isMatchPoint('B') ? " マッチポイント " : " セットポイント ";
	}
	if ((isAreguPointGot && scoreB >= DEUCE_START_SCORE[setNow - 1])
		|| (!isAreguPointGot) && scoreA >= DEUCE_START_SCORE[setNow - 1]) {
		callText = "";
	}
	if (this.gameData.judgeAreguServe(this.gameData.isAreguServeArray[serialNumber])) {
		callText += this.gameData.convertNumberToCallText[scoreA][1] + " - " + this.gameData.convertNumberToCallText[scoreB][1];
	} else {
		callText += this.gameData.convertNumberToCallText[scoreB][1] + " - " + this.gameData.convertNumberToCallText[scoreA][1];
	}
	return callText;
}

// セット終了時のコールテキストを生成する
generateSetEndCallText() {
	var callText;
	let scoreA = this.gameData.judgeAreguLeft() ? scoreLeft : scoreRight;
	let scoreB = this.gameData.judgeAreguLeft() ? scoreRight : scoreLeft;	
	let scoreWin = this.gameData.isAreguSetWin() ? scoreA : scoreB;
	let scoreLose = this.gameData.isAreguSetWin() ? scoreB : scoreA;
	let reguNameWin = this.gameData.isAreguSetWin() ? AreguName : BreguName;

	switch (setNow) {
		case 1:
			callText = "ファーストセット ";
			break;
		case 2:
			callText = "セカンドセット ";
			break;
		case 3:
			callText = "サードセット ";
			break;
		default:
			callText = "";
	}

	callText += scoreWin + " 対 " + scoreLose + " " + reguNameWin + " ウィン！ トゥーミニッツ レスト";

	return callText;
}

generateGameEndCallText() {
	// if (isRPSMode) {
	// 	return "じゃんけんを行います。";
	// }
	// try{
	// 	isAreguSetWin();
	// } catch (error) {
	// 	return "";
	// }
	let callText = "";
	const scoreLeft1set = parseInt(document.getElementById("setNumber1setLeft").textContent);
	const scoreRight1set = parseInt(document.getElementById("setNumber1setRight").textContent);
	const scoreLeft2set = parseInt(document.getElementById("setNumber2setLeft").textContent);
	const scoreRight2set = parseInt(document.getElementById("setNumber2setRight").textContent);
	const scoreLeft3set = parseInt(document.getElementById("setNumber3setLeft").textContent);
	const scoreRight3set = parseInt(document.getElementById("setNumber3setRight").textContent);
	const scoreA1set = this.gameData.judgeAreguLeft() ? scoreLeft1set : scoreRight1set;
	const scoreB1set = this.gameData.judgeAreguLeft() ? scoreRight1set : scoreLeft1set;
	const scoreA2set = this.gameData.judgeAreguLeft() ? scoreLeft2set : scoreRight2set;
	const scoreB2set = this.gameData.judgeAreguLeft() ? scoreRight2set : scoreLeft2set;
	const scoreA3set = this.gameData.judgeAreguLeft() ? scoreLeft3set : scoreRight3set;
	const scoreB3set = this.gameData.judgeAreguLeft() ? scoreRight3set : scoreLeft3set;
	const _isAreguSetWin = document.getElementById("isAreguSetWin").value === "true";
	const scoreWin1set = _isAreguSetWin ? scoreA1set : scoreB1set;
	const scoreLose1set = _isAreguSetWin ? scoreB1set : scoreA1set;
	const scoreWin2set = _isAreguSetWin ? scoreA2set : scoreB2set;
	const scoreLose2set = _isAreguSetWin ? scoreB2set : scoreA2set;
	const scoreWin3set = _isAreguSetWin ? scoreA3set : scoreB3set;
	const scoreLose3set = _isAreguSetWin ? scoreB3set : scoreA3set;
	const reguNameWin = _isAreguSetWin ? AreguName : BreguName;

	if (setNow >= 1) {
		callText += "ファーストセット ";
		callText += scoreWin1set + " 対 " + scoreLose1set + "、\n";
	} 
	if (setNow >= 2) {
		callText += "セカンドセット ";
		callText += scoreWin2set + " 対 " + scoreLose2set + "、\n";
	} 
	if (setNow === 3) {
		callText += "サードセット ";
		callText += scoreWin3set + " 対 " + scoreLose3set + "、\n";
	}
	callText += reguNameWin + " ウィン！ シェイクハンド プリーズ";

	return callText;
}

// 通常時のコールテキストを生成する
generateNormalCallText() {
	var callText;
	let scoreA = this.gameData.judgeAreguLeft() ? scoreLeft : scoreRight;
	let scoreB = this.gameData.judgeAreguLeft() ? scoreRight : scoreLeft;
	if (this.gameData.judgeAreguServe(this.gameData.isAreguServeArray[serialNumber])) {
		callText = this.gameData.convertNumberToCallText[scoreA][1] + " - " + this.gameData.convertNumberToCallText[scoreB][1];
	} else {
		callText = this.gameData.convertNumberToCallText[scoreB][1] + " - " + this.gameData.convertNumberToCallText[scoreA][1];
	}
	return callText;
}

  // スコア更新時の処理
  async handleScoreUpdate() {
    const { scoreLeft, scoreRight, scoreStockList, setNow } = this.gameData;
    const isLeftRegu = scoreStockList.slice(-1)[0] === 'L';
    await this.gameEventHistory.insertGameEventHistoryPoints(isLeftRegu ? scoreLeft : scoreRight, isLeftRegu === this.gameData.judgeAreguLeft());
    this.updateSetNumberDisplay(isLeftRegu ? scoreLeft : scoreRight, isLeftRegu, setNow);
    this.updateButtonDisplay();
    this.updateCallTextDisplay();
    this.updateServeDisplay();
  }

  // セット終了時の処理
  async handleSetEnd() {
    try {
      document.getElementById("isAreguSetWin").value = this.set.isAreguSetWin();
    } catch (error) {
      // TODO: じゃんけん処理
    }
    this.updateButtonDisplay();
    this.updateCallTextDisplay();
    this.updateServeDisplay();
  }

  // 3セット目のコートチェンジ時の処理
  handleCourtChange() {
    alert(`${this.gameData.COURT_CHANGE_SCORE}点になりました。コートチェンジしてください。\n「チェンジコート(Change Court)!」`);
  }

  // スコア表示を更新する
  updateScoreDisplay(scoreLeft, scoreRight) {
    this.gameView.updateScoreDisplay(scoreLeft, scoreRight);
  }

  // セットごとのスコア表示を更新する
  updateSetNumberDisplay(score, isLeftRegu, setNow) {
    this.gameView.updateSetNumberDisplay(score, isLeftRegu, setNow);
  }

  // 状態変更を通知する
  notify() {
    this.notifyObservers();
  }
}