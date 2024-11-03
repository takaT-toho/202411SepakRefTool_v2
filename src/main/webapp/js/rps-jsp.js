function updateWinner(isAreguGameWin) {
    const reguA = document.getElementById("reguA");
    const reguB = document.getElementById("reguB");
    const resultA = document.getElementById("resultA");
    const resultB = document.getElementById("resultB");

    reguA.classList.toggle("win", isAreguGameWin);
    reguB.classList.toggle("win", !isAreguGameWin);
    reguA.classList.toggle("lose", !isAreguGameWin);
    reguB.classList.toggle("lose", isAreguGameWin);

    resultA.textContent = isAreguGameWin ? "WIN" : "LOSE";
    resultB.textContent = isAreguGameWin ? "LOSE" : "WIN";

    resultA.setAttribute("aria-hidden", !isAreguGameWin);
    resultB.setAttribute("aria-hidden", isAreguGameWin);
}

function selectWinner(regu) {
    const isAreguGameWin = regu === "A";
    document.getElementById("isAreguGameWin").value = isAreguGameWin;
    updateWinner(isAreguGameWin);
    updateCallTextDisplay(currentState, isAreguGameWin);
}


// Aレグが左かどうかを判定する関数
const isAreguLeft = (state) => {
    console.log("execute isAreguLeft");
    const isOddSet = state.SET_NOW === 1 || state.SET_NOW === 3;

    if (state.IS_AREGU_FIRST_LEFT) {
        return isOddSet && !state.IS_3SET_COURT_CHANGED;
    } else {
        return !(isOddSet && !state.IS_3SET_COURT_CHANGED);
    }
};

// コールテキストを更新する関数
const updateCallTextDisplay = (state, isAreguGameWin) => {
    console.log("execute updateCallTextDisplay");
    callMsg.textContent = generateGameEndCallText(state, isAreguGameWin);
};

// 試合が終了している場合
const generateGameEndCallText = (state, isAreguGameWin) => {
    console.log("execute generateGameEndCallText");
    let callText = "";
    const isEquals = isAreguLeft(state) === isAreguGameWin;
    let scoreWin = {
        1: 0,
        2: 0,
        3: 0
    };
    let scoreLose = {
        1: 0,
        2: 0,
        3: 0
    };
    const SET_TO_CALL = {
        1: "ファーストセット",
        2: "セカンドセット",
        3: "サードセット"
    };
    for (let i = 1; i <= state.SET_NOW; i++) {
        scoreWin[i] = isEquals ? state.SET_NUMBER_LEFT[i] : state.SET_NUMBER_RIGHT[i];
        scoreLose[i] = isEquals ? state.SET_NUMBER_RIGHT[i] : state.SET_NUMBER_LEFT[i];
        callText += SET_TO_CALL[i] + " ";
        callText += scoreWin[i] + " 対 " + scoreLose[i] + "、\n";
    }
    const reguNameWin = isAreguGameWin ? state.AREGU_ABB : state.BREGU_ABB;
    callText += reguNameWin + " ウィン！ シェイクハンド プリーズ";

    return callText;
};

// フォーム送信時
const form = document.getElementById('checkForm');
form.addEventListener('submit', function(e) {
    const isAreguGameWinValue = document.getElementById("isAreguGameWin").value;

    if (!isAreguGameWinValue) {
        e.preventDefault();
        document.getElementById("errorMsg").innerText = "勝者を選択してください。";
    }

    localStorage.removeItem('gameState');
});

let currentState = JSON.parse(localStorage.getItem('gameState'));