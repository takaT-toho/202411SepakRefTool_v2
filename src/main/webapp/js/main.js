// ロジックに必要な変数(From Database)
let isAreguFirstServe = document.getElementById("isAreguFirstServe").textContent === "true";
let isAreguFirstLeft = document.getElementById("isAreguLeft").textContent === "true";
let AreguName = document.getElementById("reguAName").textContent;
let BreguName = document.getElementById("reguBName").textContent;
let setGotByA = parseInt(document.getElementById("setNumGotByA").textContent);
let setGotByB = parseInt(document.getElementById("setNumGotByB").textContent);
let setNow = parseInt(document.getElementById("setNow").value);
let serialNumber = parseInt(document.getElementById("sumPoints").textContent);
let is3setCourtChanged = document.getElementById("is3setCourtChanged").textContent === "true" || false;

const funcIsSetFinished = (setNow) => {
	let tempIsSetFinished = "";
	if (setNow === 1) {
		tempIsSetFinished = "isFin1Set";
	} else if (setNow === 2) {
		tempIsSetFinished = "isFin2Set";
	} else if (setNow === 3) {
		tempIsSetFinished = "isFin3Set";
	}
	return document.getElementById(tempIsSetFinished).textContent === "true" || false;
}

let isSetFinished = funcIsSetFinished(setNow);

// ロジックに必要な変数(JavaScript)
let isAreguServeArray = [1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 
	1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0];
let isProcessing = false;

// 設定値
// 0: 日本語, 1: 英語, 2: タイ語
const LANGUAGE = parseInt(document.getElementById("LANGUAGE").textContent) || 0;
const MAX_SCORE = [
	parseInt(document.getElementById("MAX_SCORE_1SET").textContent || 25),
	parseInt(document.getElementById("MAX_SCORE_2SET").textContent || 25),
	parseInt(document.getElementById("MAX_SCORE_3SET").textContent || 25)
];
const DEUCE_START_SCORE = [
	parseInt(document.getElementById("DEUCE_START_SCORE_1SET").textContent) || 20,
	parseInt(document.getElementById("DEUCE_START_SCORE_2SET").textContent) || 20,
	parseInt(document.getElementById("DEUCE_START_SCORE_3SET").textContent) || 20
];
const DEUCE_DIFFERENCE = [
	parseInt(document.getElementById("DEUCE_DIFFERENCE_1SET").textContent) || 2,
	parseInt(document.getElementById("DEUCE_DIFFERENCE_2SET").textContent) || 2,
	parseInt(document.getElementById("DEUCE_DIFFERENCE_3SET").textContent) || 2
];
const COURT_CHANGE_SCORE = parseInt(document.getElementById("COURT_CHANGE_SCORE").textContent) || 11;
const MAX_SET = parseInt(document.getElementById("MAX_SET").textContent) || 3;
const GAME_RULE = document.getElementById("GAME_RULE").textContent || "firstToGet";


// スコア履歴を格納する配列
let scoreStockList = JSON.parse(sessionStorage.getItem('scoreStockList')) || [];

// form要素を取得
const form = document.getElementById('main-submit-form');

// スコア表示用の変数
const displayLeft = document.getElementById('scoreLeft');
const displayRight = document.getElementById('scoreRight');

// スコア用の変数
let scoreLeft = parseInt(displayLeft.textContent);
let scoreRight = parseInt(displayRight.textContent);

// 加算ボタンの要素を取得
const buttonLeft = document.getElementById('addLeftRegu');
const buttonRight = document.getElementById('addRightRegu');

// 戻るボタンの要素を取得
const backButton = document.getElementById("backGame");

// スリープ関数
const sleep = (time) => new Promise((resolve) => setTimeout(resolve, time));




// 加算ボタンにイベントリスナーを追加
buttonLeft.addEventListener('click', async() => {
	if (isProcessing) return;

	try {
		isProcessing = true;
		buttonLeft.disabled = true;
		startAnimation('LEFT');

		scoreLeft++;
		scoreStockList.push('L');
		serialNumber++;
		displayLeft.textContent = scoreLeft;
		await insertGameEventHistoryPoints(scoreLeft, judgeAreguLeft());
		await checkSetFinished();
		await checkChangeCourtIn3set();
		updateMiddleButtonContainer();
		updateSetNumberDisplay(scoreLeft);
		updateCallTextDisplay();
		updateServeDisplay();
	} finally {
		isProcessing = false;
		buttonLeft.disabled = false;
	}
});
buttonRight.addEventListener('click', async() => {
	if (isProcessing) return;
	
	try {
		isProcessing = true;
		buttonRight.disabled = true;
		startAnimation('RIGHT');

		scoreRight++;
		scoreStockList.push('R');
		serialNumber++;
		displayRight.textContent = scoreRight;
		await insertGameEventHistoryPoints(scoreRight, !judgeAreguLeft());
		await checkSetFinished();
		await checkChangeCourtIn3set();
		updateMiddleButtonContainer();
		updateSetNumberDisplay(scoreRight);
		updateCallTextDisplay();
		updateServeDisplay();
	} finally {
		isProcessing = false;
		buttonRight.disabled = false;
	}
});

function startAnimation(position) {
	if (position === 'LEFT') {
		if (buttonLeft.classList.contains('active')) {
			buttonLeft.classList.remove('active');
			void buttonLeft.offsetWidth;
			buttonLeft.classList.add('active');
		} else {
			buttonLeft.classList.add('active');
		}

		const scoreLeft = document.getElementById("scoreLeft");
		if (scoreLeft.classList.contains('active')) {
			scoreLeft.classList.remove('active');
			void scoreLeft.offsetWidth;
			scoreLeft.classList.add('active');
		} else {
			scoreLeft.classList.add('active');
		}
	} else if (position === 'RIGHT') {
		if (buttonRight.classList.contains('active')) {
			buttonRight.classList.remove('active');
			void buttonRight.offsetWidth;
			buttonRight.classList.add('active');
		} else {
			buttonRight.classList.add('active');
		}

		const scoreRight = document.getElementById("scoreRight");
		if (scoreRight.classList.contains('active')) {
			scoreRight.classList.remove('active');
			void scoreRight.offsetWidth;
			scoreRight.classList.add('active');
		} else {
			scoreRight.classList.add('active');
		}
	}
}

// 戻るボタンにイベントリスナーを追加
backButton.addEventListener('click', async() => {
	if (isProcessing) return;

	try {
		isProcessing = true;
		backButton.disabled = true;

		let popped = scoreStockList.slice(-1)[0];
		if (popped === 'L') {
			if (scoreLeft > 0) {			
				scoreLeft--;
				serialNumber--;
				displayLeft.textContent = scoreLeft;
				updateSetNumberDisplay(scoreLeft);
				await deleteGameEventHistoryPoints(scoreLeft, true);
			}
		} else if (popped === 'R') {
			if (scoreRight > 0) {
				scoreRight--;
				serialNumber--;
				displayRight.textContent = scoreRight;
				updateSetNumberDisplay(scoreRight);
				await deleteGameEventHistoryPoints(scoreRight, false);
			}
		} else {
			return;
		}
		let isReseted = await reset3setCourtChanged(popped);
		scoreStockList.pop();

		if (isSetFinished) {
			resetEndSet();
		}

		if (isReseted) {
			sessionStorage.setItem('scoreStockList', JSON.stringify(scoreStockList));
			window.location.reload();
		}

		updateMiddleButtonContainer();
		updateCallTextDisplay();
		updateServeDisplay();
	} finally {
		isProcessing = false;
		backButton.disabled = false;
	}
});

form.addEventListener('submit', function(e) {
	sessionStorage.removeItem('scoreStockList');
});


// 試合が終了しているかどうかをチェック
async function checkSetFinished() {
	// どちらかのスコアが25に達した場合、セットが終了
	// どちらかのスコアも20以上で、差が2点以上の場合、セットが終了
	// どちらかのスコアが21で、差が2点以上の場合、セットが終了
	if (scoreLeft ===  MAX_SCORE[setNow - 1] || scoreRight === MAX_SCORE[setNow - 1]) {
		await endSet();
	} else if ((scoreLeft >= DEUCE_START_SCORE[setNow - 1] 
		&& scoreRight >= DEUCE_START_SCORE[setNow - 1]) 
		&& Math.abs(scoreLeft - scoreRight) >= DEUCE_DIFFERENCE[setNow - 1]) {
		await endSet();
	} else if ((scoreLeft === (DEUCE_START_SCORE[setNow - 1] + 1)
		|| scoreRight === (DEUCE_START_SCORE[setNow - 1] + 1)) 
		&& Math.abs(scoreLeft - scoreRight) >= DEUCE_DIFFERENCE[setNow - 1]) {
		await endSet();
	}
}

// セットが終了した際の処理
async function endSet() {
	let isDGZero = false;

	isSetFinished = true;
	try{
		document.getElementById("isAreguSetWin").value = isAreguSetWin();
	} catch (error) {
		isDGZero = true;
	}
	if (isGameFinished()) {
		await updateIsGameFinished(isSetFinished);
	}
	buttonLeft.style.visibility = 'hidden';
	buttonRight.style.visibility = 'hidden';
	stopServeDisplay();

	await sleep(1000);

	const SetEndButton = document.getElementById("SetEndButton");
	if (isDGZero) {
		SetEndButton.textContent = "結果が決まりません。じゃんけんを行います。";
		SetEndButton.style.display = "block";
		SetEndButton.value = "p0203";
	} else {
		SetEndButton.textContent = setNow.toString() + "セット目の結果を送る";
		SetEndButton.style.display = "block";
	}

	// 勝者・敗者を表示する

}

// セットが終了処理のキャンセル処理
async function resetEndSet() {
	isSetFinished = false;
	if (isGameFinished()) {
		await updateIsGameFinished(isSetFinished);
	}
	document.getElementById("isAreguSetWin").value = "";
	buttonLeft.style.visibility = 'visible';
	buttonRight.style.visibility = 'visible';
	
	// document.getElementById("SetEndButton").style.display = "none";
}

function getGoalDifference() {
	const scoreLeft1set = parseInt(document.getElementById("setNumber1setLeft").textContent);
	const scoreRight1set = parseInt(document.getElementById("setNumber1setRight").textContent);
	const scoreLeft2set = parseInt(document.getElementById("setNumber2setLeft").textContent);
	const scoreRight2set = parseInt(document.getElementById("setNumber2setRight").textContent);
	return scoreLeft1set - scoreRight1set + scoreLeft2set - scoreRight2set;
}

// Aレグこのセットを取ったのか判定する
function isAreguSetWin() {
	if (GAME_RULE === "drawGD") {
		if (!(isMatchPoint('A') || isMatchPoint('B'))) {
			const goalDifference = getGoalDifference();
			if (goalDifference > 0) {
				return judgeAreguLeft();
			} else if (goalDifference < 0) {
				return !judgeAreguLeft();
			} else {
				throw new Error("Goal Difference is 0");
			}
		}
	}
	let popped = scoreStockList.slice(-1)[0];
	if (popped === 'L') {
		return judgeAreguLeft() ? true : false;
	} else if (popped === 'R') {
		return judgeAreguLeft() ? false : true;
	}
}

// コールテキストを更新する
function updateCallTextDisplay() {
	const displayMessage = document.getElementById("callMsg");
	displayMessage.textContent = generateCallText();
}

// コールテキストを生成する
function generateCallText() {
	if (isSetFinished) {
		return "結果送信ボタンを押してください";
	}
	if (scoreLeft === 0 && scoreRight === 0) {
		return generateFirstCallText();
	} else if (scoreLeft === DEUCE_START_SCORE[setNow - 1] 
		&& scoreRight === DEUCE_START_SCORE[setNow - 1]) {
		let text = "セッティング アップ トゥー ";
		text += convertNumberToCallText[MAX_SCORE[setNow - 1]][1] + " ";
		text += convertNumberToCallText[scoreLeft][1] + " オール";
		return text;
	} else if (scoreLeft == scoreRight) {
		return convertNumberToCallText[scoreLeft][1] + " オール";
	} else if (scoreLeft >= DEUCE_START_SCORE[setNow - 1] 
		|| scoreRight >= DEUCE_START_SCORE[setNow - 1]) {
		return generateFinalCallText();
	} else {
		return generateNormalCallText();
	}
}

// セット開始時のコールテキストを生成する
function generateFirstCallText() {
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

	const serverName = judgeAreguServe(isAreguServeArray[serialNumber]) ? AreguName : BreguName;
	callTextFirst += serverName + " サーブ ラブ オール";
	
	return callTextFirst;
}

// セット終了間際のコールテキストを生成する
function generateFinalCallText() {
	var callText;
	let scoreA = judgeAreguLeft() ? scoreLeft : scoreRight;
	let scoreB = judgeAreguLeft() ? scoreRight : scoreLeft;
	if (scoreA > scoreB) {
		if (isMatchPoint('A')) {
			callText =  " マッチポイント ";
		} else {
			callText =  " セットポイント ";
		}
	// } else if (scoreA === scoreB === (MAX_SCORE[setNow - 1] - 1)) {
	// 	// ここ何ていうの？
	// 	callText = " デュース ";
	} else {
		if (isMatchPoint('B')) {
			callText =  " マッチポイント ";
		} else {
			callText =  " セットポイント ";
		}
	}
	if (judgeAreguServe(isAreguServeArray[serialNumber])) {
		callText += convertNumberToCallText[scoreA][1] + " - " + convertNumberToCallText[scoreB][1];
	} else {
		callText += convertNumberToCallText[scoreB][1] + " - " + convertNumberToCallText[scoreA][1];
	}
	return callText;
}

// 通常時のコールテキストを生成する
function generateNormalCallText() {
	var callText;
	let scoreA = judgeAreguLeft() ? scoreLeft : scoreRight;
	let scoreB = judgeAreguLeft() ? scoreRight : scoreLeft;
	if (judgeAreguServe(isAreguServeArray[serialNumber])) {
		callText = convertNumberToCallText[scoreA][1] + " - " + convertNumberToCallText[scoreB][1];
	} else {
		callText = convertNumberToCallText[scoreB][1] + " - " + convertNumberToCallText[scoreA][1];
	}
	return callText;
}

// 数字をコールテキストに変換する
const convertNumberToCallText = {
	0: ["Love", "ラブ"],
	1: ["One", "ワン"],
	2: ["Two", "トゥー"],
	3: ["Three", "スリー"],
	4: ["Four", "フォー"],
	5: ["Five", "ファイブ"],
	6: ["Six", "シックス"],
	7: ["Seven", "セブン"],
	8: ["Eight", "エイト"],
	9: ["Nine", "ナイン"],
	10: ["Ten", "テン"],
	11: ["Eleven", "イレブン"],
	12: ["Twelve", "トゥウェルブ"],
	13: ["Thirteen", "サーティーン"],
	14: ["Fourteen", "フォーティーン"],
	15: ["Fifteen", "フィフティーン"],
	16: ["Sixteen", "シックスティーン"],
	17: ["Seventeen", "セブンティーン"],
	18: ["Eighteen", "エイティーン"],
	19: ["Nineteen", "ナインティーン"],
	20: ["Twenty", "トゥウェンティ"],
	21: ["Twenty One", "トゥウェンティ ワン"],
	22: ["Twenty Two", "トゥウェンティ トゥー"],
	23: ["Twenty Three", "トゥウェンティ スリー"],
	24: ["Twenty Four", "トゥウェンティ フォー"],
	25: ["Twenty Five", "トゥウェンティ ファイブ"],
}

// サーブ表示を更新する
function updateServeDisplay() {
	if (isSetFinished) return;
	const teamLeft = document.getElementById("teamLeft");
	const teamRight = document.getElementById("teamRight");
	const serveGageLeft1 = document.getElementById("serveGageLeft1");
	const serveGageLeft2 = document.getElementById("serveGageLeft2");
	const serveGageLeft3 = document.getElementById("serveGageLeft3");
	const serveGageRight1 = document.getElementById("serveGageRight1");
	const serveGageRight2 = document.getElementById("serveGageRight2");
	const serveGageRight3 = document.getElementById("serveGageRight3");
	if (judgeAreguServe(isAreguServeArray[serialNumber]) === judgeAreguLeft()) {
		teamLeft.classList.add('active');
		teamRight.classList.remove('active');

		serveGageLeft1.classList.remove('active');
		serveGageLeft2.classList.remove('active');
		serveGageLeft3.classList.remove('active');
		serveGageRight1.classList.remove('active');
		serveGageRight2.classList.remove('active');
		serveGageRight3.classList.remove('active');
		if (serialNumber < (DEUCE_START_SCORE[setNow - 1] * 2 - 1)){
			if (serialNumber % 3 === 0) {
				serveGageLeft1.classList.add('active');
			} else if (serialNumber % 3 === 1) {
				serveGageLeft1.classList.add('active');
				serveGageLeft2.classList.add('active');
			} else if (serialNumber % 3 === 2) {
				serveGageLeft1.classList.add('active');
				serveGageLeft2.classList.add('active');
				serveGageLeft3.classList.add('active');
			}
		}		
	} else {
		teamLeft.classList.remove('active');
		teamRight.classList.add('active');

		serveGageLeft1.classList.remove('active');
		serveGageLeft2.classList.remove('active');
		serveGageLeft3.classList.remove('active');
		serveGageRight1.classList.remove('active');
		serveGageRight2.classList.remove('active');
		serveGageRight3.classList.remove('active');
		if (serialNumber < (DEUCE_START_SCORE[setNow - 1] * 2 - 1)){
			if (serialNumber % 3 === 0) {
				serveGageRight1.classList.add('active');
			} else if (serialNumber % 3 === 1) {
				serveGageRight1.classList.add('active');
				serveGageRight2.classList.add('active');
			} else if (serialNumber % 3 === 2) {
				serveGageRight1.classList.add('active');
				serveGageRight2.classList.add('active');
				serveGageRight3.classList.add('active');
			}
		}
	}
}

function stopServeDisplay() {
	document.getElementById("teamLeft").classList.remove('active');
	document.getElementById("teamRight").classList.remove('active');
	document.getElementById("serveGageLeft1").classList.remove('active');
	document.getElementById("serveGageLeft2").classList.remove('active');
	document.getElementById("serveGageLeft3").classList.remove('active');
	document.getElementById("serveGageRight1").classList.remove('active');
	document.getElementById("serveGageRight2").classList.remove('active');
	document.getElementById("serveGageRight3").classList.remove('active');
}

function updateSetNumberDisplay(score_) {
	let popped = scoreStockList.slice(-1)[0];
	if (popped === 'L') {
		if (judgeAreguLeft) {
			if (setNow === 1) {
				document.getElementById("setNumber1setLeft").textContent = score_;
			} else if (setNow === 2) {
				document.getElementById("setNumber2setLeft").textContent = score_;
			} else if (setNow === 3) {
				document.getElementById("setNumber3setLeft").textContent = score_;
			}
		} else {
			if (setNow === 1) {
				document.getElementById("setNumber1setRight").textContent = score_;
			} else if (setNow === 2) {
				document.getElementById("setNumber2setRight").textContent = score_;
			} else if (setNow === 3) {
				document.getElementById("setNumber3setRight").textContent = score_;
			}
		}
	} else if (popped === 'R') {
		if (!judgeAreguLeft) {
			if (setNow === 1) {
				document.getElementById("setNumber1setLeft").textContent = score_;
			} else if (setNow === 2) {
				document.getElementById("setNumber2setLeft").textContent = score_;
			} else if (setNow === 3) {
				document.getElementById("setNumber3setLeft").textContent = score_;
			}
		} else {
			if (setNow === 1) {
				document.getElementById("setNumber1setRight").textContent = score_;
			} else if (setNow === 2) {
				document.getElementById("setNumber2setRight").textContent = score_;
			} else if (setNow === 3) {
				document.getElementById("setNumber3setRight").textContent = score_;
			}
		}
	}
}

// Aレグがサブかどうかを判定する
function judgeAreguServe(isAreguServeInit_) {
	// Aレグがサーブの時は以下の時
	// 1セット目かつ、Aレグが最初にサーブする時
	// 2セット目かつ、Bレグが最初にサーブする時
	// 3セット目かつ、Aレグが最初にサーブする時
	const isOddSet = setNow === 1 || setNow === 3;

	if (isOddSet && isAreguFirstServe) {
		return isAreguServeInit_ === 1;
	} else if (!isOddSet && !isAreguFirstServe) {
		return isAreguServeInit_ === 1;
	} else {
		return isAreguServeInit_ === 0;
	}
}

// Aレグが左かどうかを判定する
function judgeAreguLeft() {
	const isOddSet = setNow === 1 || setNow === 3;
    
	if (isAreguFirstLeft) {
		return isOddSet && !is3setCourtChanged;
	} else {
		return !(isOddSet && !is3setCourtChanged);
	}
}

// マッチポイントのレグを判定する
function isMatchPoint(team) {
	if (team === 'A') {
		return setGotByA === 1;
	} else if (team === 'B') {
		return setGotByB === 1;
	}
	return false;
}

// 3セット目のコートチェンジを判定する
async function checkChangeCourtIn3set() {
	if (is3setCourtChanged) return;
	// 3セット目かつ、どちらかのチームが11点になった時にコートチェンジする
	if (setNow === 3 && (scoreLeft === COURT_CHANGE_SCORE || scoreRight === COURT_CHANGE_SCORE)) {
		is3setCourtChanged = true;
		await updateIs3setCourtChanged(is3setCourtChanged);
		window.alert(COURT_CHANGE_SCORE + "点になりました。コートチェンジしてください。\n「チェンジコート(Change Court)!」");
		let popped = scoreStockList.pop();
		if (popped === 'L') {
			popped = 'R';
		} else if (popped === 'R') {
			popped = 'L';
		}
		scoreStockList.push(popped);
		sessionStorage.setItem('scoreStockList', JSON.stringify(scoreStockList));
		window.location.reload();
	}
}

// 3セット目のコートチェンジを解除する
async function reset3setCourtChanged(popped) {
	// 3セット目かどうか
	if (setNow !== 3) return;
	if (!(popped)) return;

	if (popped === 'L') {
		if (!(scoreLeft === (COURT_CHANGE_SCORE - 1) 
			&& scoreRight <= (COURT_CHANGE_SCORE - 1))) {
			return;
		}
	} else if (popped === 'R') {
		if (!(scoreRight === (COURT_CHANGE_SCORE - 1) 
			&& scoreLeft <= (COURT_CHANGE_SCORE - 1))) {
			return;
		}
	}
	is3setCourtChanged = false;
	await updateIs3setCourtChanged(is3setCourtChanged);
	return true;
}

// 試合が終了しているかどうかを判定する
function isGameFinished() {
	if (setNow === MAX_SET) {
		return true;
	}
	if (isAreguSetWin()) {
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

// 中央ボタンコンテナを更新する
function updateMiddleButtonContainer() {
	// sumPointsが0の時はバックボタンは表示しない
	if (serialNumber === 0) {
		backButton.style.display = "none";
	} else {
		backButton.style.display = "block";
	}
	// isSetFinishedの時のみセットエンドボタンを表示する
	if (isSetFinished) {
		document.getElementById("SetEndButton").style.display = "block";
	} else {
		document.getElementById("SetEndButton").style.display = "none";
	}

}

// 読み込み時に、コールテキストとサーブ表示を更新する
window.onload = function() {
	updateMiddleButtonContainer();
	updateCallTextDisplay();
	updateServeDisplay();
};