// スコアボードの状態を管理するオブジェクト
const initialState = {
    GAME_ID: document.getElementById("gameId").textContent,
    IS_AREGU_FIRST_SERVE: document.getElementById("IS_AREGU_FIRST_SERVE").textContent === "true",
    IS_AREGU_FIRST_LEFT: document.getElementById("IS_AREGU_FIRST_LEFT").textContent === "true",
    AREGU_ABB: document.getElementById("AREGU_ABB").textContent,
    BREGU_ABB: document.getElementById("BREGU_ABB").textContent,
    SET_GOY_BY_A: parseInt(document.getElementById("SET_GOY_BY_A").textContent),
    SET_GOY_BY_B: parseInt(document.getElementById("SET_GOY_BY_B").textContent),
    SET_NOW: parseInt(document.getElementById("setNow").value),
    SUM_POINTS: parseInt(document.getElementById("SUM_POINTS").textContent),
    IS_3SET_COURT_CHANGED: document.getElementById("IS_3SET_COURT_CHANGED").textContent === "true",
    IS_FIN_SET: {
        1: document.getElementById("IS_FIN_1SET").textContent === "true",
        2: document.getElementById("IS_FIN_2SET").textContent === "true",
        3: document.getElementById("IS_FIN_3SET").textContent === "true",
    },
    IS_SET_FINISHED: false,
    IS_GAME_FINISHED: document.getElementById("IS_GAME_FINISHED").textContent === "true",
    IS_DEUCE_MODE: false,
  
    // スコア履歴
    SCORE_STOCK_LIST: JSON.parse(sessionStorage.getItem('SCORE_STOCK_LIST')) || [],
    SCORE_LEFT: parseInt(document.getElementById("scoreLeft").textContent) || 0,
    SCORE_RIGHT: parseInt(document.getElementById("scoreRight").textContent) || 0,
  
    // 設定値
    LANGUAGE: parseInt(document.getElementById("LANGUAGE").textContent) || 0,
    MAX_SCORE: {
        1: parseInt(document.getElementById("MAX_SCORE_1SET").textContent || 17),
        2: parseInt(document.getElementById("MAX_SCORE_2SET").textContent || 17),
        3: parseInt(document.getElementById("MAX_SCORE_3SET").textContent || 17)
    },
    DEUCE_START_SCORE: {
        1: parseInt(document.getElementById("DEUCE_START_SCORE_1SET").textContent) || 14,
        2: parseInt(document.getElementById("DEUCE_START_SCORE_2SET").textContent) || 14,
        3: parseInt(document.getElementById("DEUCE_START_SCORE_3SET").textContent) || 14
    },
    COURT_CHANGE_SCORE: parseInt(document.getElementById("COURT_CHANGE_SCORE").textContent) || 8,
    MAX_SET: parseInt(document.getElementById("MAX_SET").textContent) || 3,
    GAME_RULE: document.getElementById("GAME_RULE").textContent || "firstToGet",
  
    // 数字をコールテキストに変換する
    NUMBER_TO_CALL: {
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
    },
};
  
// DOM要素を取得
const scoreLeft = document.getElementById("scoreLeft");
const scoreRight = document.getElementById("scoreRight");
const buttonLeft = document.getElementById("buttonLeft");
const buttonRight = document.getElementById("buttonRight");
const undoButton = document.getElementById("undoButton");
const setEndButton = document.getElementById("SetEndButton");
const callMsg = document.getElementById("callMsg");
const reguLeft = document.getElementById("reguLeft");
const reguRight = document.getElementById("reguRight");
const serveGageLeft = document.getElementById("serveGageLeft");
const serveGageRight = document.getElementById("serveGageRight");
const setNumberLeft = {
    1: document.getElementById("setNumber1setLeft"),
    2: document.getElementById("setNumber2setLeft"),
    3: document.getElementById("setNumber3setLeft"),
};
const setNumberRight = {
    1: document.getElementById("setNumber1setRight"),
    2: document.getElementById("setNumber2setRight"),
    3: document.getElementById("setNumber3setRight"),
};

// === 純粋関数: 状態の更新と判定 ===
  
// 状態更新関数を定義
const updateState = (state, action) => {
    console.log("execute updateState");
    switch (action.type) {
        case 'ADD_LEFT_SCORE':
            return {
                ...state,
                SCORE_LEFT: state.SCORE_LEFT + 1,
                SCORE_STOCK_LIST: [...state.SCORE_STOCK_LIST, 'L'],
                SUM_POINTS: state.SUM_POINTS + 1,
                IS_SET_FINISHED: checkHasSetFinished(state.SCORE_LEFT + 1, state.SCORE_RIGHT, state.MAX_SCORE[state.SET_NOW], state.DEUCE_START_SCORE[state.SET_NOW]),
            };
        case 'ADD_RIGHT_SCORE':
            return {
                ...state,
                SCORE_RIGHT: state.SCORE_RIGHT + 1,
                SCORE_STOCK_LIST: [...state.SCORE_STOCK_LIST, 'R'],
                SUM_POINTS: state.SUM_POINTS + 1,
                IS_SET_FINISHED: checkHasSetFinished(state.SCORE_LEFT, state.SCORE_RIGHT + 1, state.MAX_SCORE[state.SET_NOW], state.DEUCE_START_SCORE[state.SET_NOW]),
            };
        case 'UNDO_SCORE':
            const popped = state.SCORE_STOCK_LIST[state.SCORE_STOCK_LIST.length - 1];
            return popped === 'L'
                ? {
                ...state,
                SCORE_LEFT: state.SCORE_LEFT > 0 ? state.SCORE_LEFT - 1 : 0,
                SCORE_STOCK_LIST: state.SCORE_STOCK_LIST.slice(0, -1),
                SUM_POINTS: state.SUM_POINTS > 0 ? state.SUM_POINTS - 1 : 0,
                }
                : {
                ...state,
                SCORE_RIGHT: state.SCORE_RIGHT > 0 ? state.SCORE_RIGHT - 1 : 0,
                SCORE_STOCK_LIST: state.SCORE_STOCK_LIST.slice(0, -1),
                SUM_POINTS: state.SUM_POINTS > 0 ? state.SUM_POINTS - 1 : 0,
            };
        case 'UNDO_END_SET':
            return {
                ...state,
                IS_GAME_FINISHED: false,
            };
        case 'UPDATE_DEUCE_MODE':
            return {
                ...state,
                IS_DEUCE_MODE: action.IS_DEUCE_MODE,
            }
        case 'UPDATE_3SET_COURT_CHANGED':
            return {
                ...state,
                IS_3SET_COURT_CHANGED: action.IS_3SET_COURT_CHANGED,
            }
        default:
        return state;
    }
};
  
// セットが終了しているかどうかをチェック
const checkHasSetFinished = (SCORE_LEFT, SCORE_RIGHT, MAX_SCORE, DEUCE_START_SCORE) => {
    console.log("execute checkHasSetFinished");
    let returnValue = false;
    // どちらかのスコアが最大スコアに達した場合、セットが終了
    if (SCORE_LEFT === MAX_SCORE || SCORE_RIGHT === MAX_SCORE) {
        returnValue = true;
    // 一方が基準点で、もう一方が基準点-2点以下の場合、セットが終了
    } else if (((SCORE_LEFT === DEUCE_START_SCORE + 1)
        && (SCORE_RIGHT <= DEUCE_START_SCORE - 1))
        || ((SCORE_LEFT <= DEUCE_START_SCORE - 1)
        && (SCORE_RIGHT === DEUCE_START_SCORE + 1))) {
            returnValue = true;
    }

    return returnValue;
}
  
// Aレグがその時点でサーブかどうかを判定する関数
const isAreguServe = (state) => {
    console.log("execute isAreguServe");
    const isOddSet = state.SET_NOW === 1 || state.SET_NOW === 3;
    const isEvenSumPoints = state.SUM_POINTS % 2 === 0;

    return isOddSet === state.IS_AREGU_FIRST_SERVE === isEvenSumPoints;
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
}
  
// マッチポイントのチームを判定する関数
const isMatchPoint = (state, regu) => {
    console.log("execute isMatchPoint");
    if (regu === 'A') {
        return state.SET_GOY_BY_A === 1;
    } else if (regu === 'B') {
        return state.SET_GOY_BY_B === 1;
    }
    return false;
}
  
// Aレグがこのセットを取ったのか判定する
const isAreguSetWin = (state) => {
    console.log("execute isAreguSetWin");
    if (state.GAME_RULE === "drawGD") {
        const scoreA = isAreguLeft(state) ? state.SCORE_LEFT : state.SCORE_RIGHT;
        const scoreB = isAreguLeft(state) ? state.SCORE_RIGHT : state.SCORE_LEFT;
        let hasUniqueWinner = false;
        if (((scoreA > scoreB) && isMatchPoint(state, 'A'))
            || ((scoreB > scoreA) && isMatchPoint(state, 'B'))) {
            hasUniqueWinner = true;
        }
        if (!hasUniqueWinner) {
            const popped = state.SCORE_STOCK_LIST.slice(-1)[0];
            const addScore = popped === 'L' ? 1 : -1;
            const goalDifference = getGoalDifference(state) + addScore;
            if (goalDifference > 0) {
                return isAreguLeft(state);
            } else if (goalDifference < 0) {
                return !isAreguLeft(state);
            } else {
                let returnValue = false;
                if (popped === 'L') {
                    returnValue = isAreguLeft(state) ? true : false;
                } else if (popped === 'R') {
                    returnValue = isAreguLeft(state) ? false : true;
                }
                // document.getElementById("isAreguSetWin").value = returnValue;
                throw new Error("Goal Difference is 0");
            }
        }
    }
    let popped = state.SCORE_STOCK_LIST.slice(-1)[0];
    if (popped === 'L') {
        return isAreguLeft(state) ? true : false;
    } else if (popped === 'R') {
        return isAreguLeft(state) ? false : true;
    }
}

// 得失点差を取得する
const getGoalDifference = (state) => {
    console.log("execute getGoalDifference");
    const scoreLeft1set = parseInt(document.getElementById("setNumber1setLeft").textContent);
    const scoreRight1set = parseInt(document.getElementById("setNumber1setRight").textContent);
    const scoreLeft2set = parseInt(document.getElementById("setNumber2setLeft").textContent);
    const scoreRight2set = parseInt(document.getElementById("setNumber2setRight").textContent);
    return scoreLeft1set - scoreRight1set + scoreLeft2set - scoreRight2set;
}
  
// 試合が終了しているかどうかを判定する
const checkIsGameFinished = (state) => {
    console.log("execute checkIsGameFinished");
    if (state.SET_NOW === state.MAX_SET) {
        return true;
    }
    if (isAreguSetWin(state)) {
        if (state.SET_GOY_BY_A === 1) {
            return true;
        }
    } else {
        if (state.SET_GOY_BY_B === 1) {
            return true;
        }
    }
    return false;
}

// アニメーション用の関数
const startAnimation = (position) => {
    console.log("execute startAnimation");
    const targetButton = position === 'LEFT' ? buttonLeft : buttonRight;
    const targetScore = position === 'LEFT' ? scoreLeft : scoreRight;
  
    if (targetButton.classList.contains('active')) {
      targetButton.classList.remove('active');
      void targetButton.offsetWidth;
      targetButton.classList.add('active');
    } else {
      targetButton.classList.add('active');
    }
  
    if (targetScore.classList.contains('active')) {
      targetScore.classList.remove('active');
      void targetScore.offsetWidth;
      targetScore.classList.add('active');
    } else {
      targetScore.classList.add('active');
    }
  };
  
// === /純粋関数: 状態の更新と判定 ===
  
  
// === 副作用を持つ関数: DOM操作、非同期処理、状態更新 ===
  
// 画面を更新する関数
const render = (state) => {
    console.log("execute render");
    // スコア表示を更新
    scoreLeft.textContent = state.SCORE_LEFT;
    scoreRight.textContent = state.SCORE_RIGHT;

    // セットごとのスコア表示を更新
    updateSetNumberDisplay(state);

    // ボタンの表示状態を更新
    updateButtonDisplay(state);

    // コールテキストを更新
    updateCallTextDisplay(state);

    // サーブ表示を更新
    updateServeDisplay(state);
};
  
// スコア更新時の処理
const handleScoreUpdate = (state, isLeftScore) => {
    console.log("execute handleScoreUpdate");
    const newState = updateState(state, { type: isLeftScore ? 'ADD_LEFT_SCORE' : 'ADD_RIGHT_SCORE' });

    // デュースモードの判定と処理
    const deuceModeChangedState = checkDeuceMode(newState);

    // 3セット目のコートチェンジ判定と処理
    const courtChangedState = checkAndHandleCourtChange(deuceModeChangedState);

    // データベースへの保存
    insertGameEventHistoryPoints(
        courtChangedState,
        isLeftScore ? courtChangedState.SCORE_LEFT : courtChangedState.SCORE_RIGHT,
        isLeftScore === isAreguLeft(courtChangedState)
    );

    // アニメーション開始
    startAnimation(isLeftScore ? 'LEFT' : 'RIGHT'); 

    return courtChangedState;
};
  
// 戻るボタンクリック時の処理
const handleUndoScore = (state) => {
    console.log("execute handleUndoScore");
    const newState = updateState(state, { type: 'UNDO_SCORE' });

    const undoDeuceModeState = undoIsDeuceMode(newState);

    const isReseted = undo3setCourtChanged(undoDeuceModeState);

    if (undoDeuceModeState.IS_SET_FINISHED) {
        undoEndSet(newState);
    }

    if (isReseted) {
        sessionStorage.setItem('SCORE_STOCK_LIST', JSON.stringify(undoDeuceModeState.SCORE_STOCK_LIST));
        window.location.reload();
    }
    deleteGameEventHistoryPoints(undoDeuceModeState);
    return undoDeuceModeState;
};
  
// セット終了ボタンクリック時の処理
const handleSetEnd = (state) => {
    console.log("execute handleSetEnd");
    try {
        document.getElementById("isAreguSetWin").value = isAreguSetWin(state);
    } catch (error) {
        // TODO: じゃんけん処理
    }
    updateIsGameFinished(state, state.IS_SET_FINISHED);
    return state;
}
  
// 非同期処理: ゲームイベント履歴をDBに保存
const insertGameEventHistoryPoints = async (state, score, isAreguGot) => {
    console.log("execute insertGameEventHistoryPoints");
    const previousRegu = state.SCORE_STOCK_LIST[state.SCORE_STOCK_LIST.length - 2];
    const isSequential = (previousRegu === 'A' && isAreguGot) || (previousRegu === 'B' && !isAreguGot);

    const data = {
        gameId: state.GAME_ID,
        setNum: state.SET_NOW,
        type: 'ADDPOINTS',
        firstDetail: score,
        secondDetail: null,
        isSequential: isSequential,
        isAreguGot: isAreguGot,
        buttonId: "p0150",
        sumPoints: state.SUM_POINTS
    };

    try {
        const response = await fetch('async', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data)
        });

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const result = await response.json();
        console.log('Success:', result);
    } catch (error) {
        console.error('Error:', error);
        // TODO: システムエラー画面へ遷移する
    }
};
  
// 非同期処理: DBのゲームイベント履歴を削除
const deleteGameEventHistoryPoints = async (state) => {
    console.log("execute deleteGameEventHistoryPoints");
    try {
        const response = await fetch('async', {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                gameId: state.GAME_ID,
                buttonId: "p0160",
            })
        });

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const result = await response.json();
        console.log('Success:', result);
    } catch (error) {
        console.error('Error:', error);
        // TODO: システムエラー画面へ遷移する
    }
};
  
const updateIs3setCourtChanged = async (state) => {
    console.log("execute updateIs3setCourtChanged");
    try {
        const response = await fetch('async', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                gameId: state.GAME_ID,
                buttonId: "p0200",
                is3setCourtChanged: state.IS_3SET_COURT_CHANGED,
            })
        });

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const result = await response.json();
        console.log('Success:', result);
    } catch (error) {
        console.error('Error:', error);
        // TODO: システムエラー画面へ遷移する
    }
};
  
const updateIsGameFinished = async (state, isGameFinished) => {
    console.log("execute updateIsGameFinished");
    try {
        const response = await fetch('async', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                gameId: state.GAME_ID,
                buttonId: "p0210",
                isGameFinished: isGameFinished,
            })
        });

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const result = await response.json();
        console.log('Success:', result);
    } catch (error) {
        console.error('Error:', error);
        // TODO: システムエラー画面へ遷移する
    }
};

// デュースモードかを判定する
const checkDeuceMode = (state) => {
    console.log("execute checkDeuceMode");
    if ((state.SCORE_LEFT === state.DEUCE_START_SCORE[state.SET_NOW])
        && (state.SCORE_RIGHT === state.DEUCE_START_SCORE[state.SET_NOW])) {
        const newState = updateState(state, { type: 'UPDATE_DEUCE_MODE', IS_DEUCE_MODE: true });
        return newState;
    }
    return state;
}
  
// 3セット目のコートチェンジを判定する
const checkAndHandleCourtChange = (state) => {
    console.log("execute checkAndHandleCourtChange");
    // 3セット目かつ、どちらかのチームが8点になった時にコートチェンジする
    if (state.SET_NOW === 3 && (state.SCORE_LEFT === state.COURT_CHANGE_SCORE || state.SCORE_RIGHT === state.COURT_CHANGE_SCORE)) {
        const newState = updateState(state, { type: 'UPDATE_3SET_COURT_CHANGED', IS_3SET_COURT_CHANGED: true });
        updateIs3setCourtChanged(newState);
        alert(state.COURT_CHANGE_SCORE + "点になりました。コートチェンジしてください。\n「チェンジコート(Change Court)!」");
        // データベースへの保存
        insertGameEventHistoryPoints(
            newState,
            state.SCORE_LEFT,
            isAreguLeft(newState)
        );
        return updateState(newState, { type: newState.SCORE_STOCK_LIST.pop() === 'L' ? 'ADD_RIGHT_SCORE' : 'ADD_LEFT_SCORE' });
    }
    return state;
}

// デュースモードを解除する
const undoIsDeuceMode = (state) => {
    console.log("execute undoIsDeuceMode");
    if (!state.IS_DEUCE_MODE) return state;

    if ((state.SCORE_LEFT < state.DEUCE_START_SCORE[state.SET_NOW])
        || (state.SCORE_RIGHT < state.DEUCE_START_SCORE[state.SET_NOW])) {
        return state;
    }
    return updateState(state, { type: 'UPDATE_DEUCE_MODE', IS_DEUCE_MODE: false });
};
  
// 3セット目のコートチェンジを解除する
const undo3setCourtChanged = (state) => {
    console.log("execute undo3setCourtChanged");
    // 3セット目かどうか
    if (state.SET_NOW !== 3) return false;

    const popped = state.SCORE_STOCK_LIST.slice(-1)[0];
    if (!popped) return false;

    if (popped === 'L') {
        if (!(state.SCORE_LEFT === (state.COURT_CHANGE_SCORE - 1)
        && state.SCORE_RIGHT <= (state.COURT_CHANGE_SCORE - 1))) {
            return false;
        }
    } else if (popped === 'R') {
        if (!(state.SCORE_RIGHT === (state.COURT_CHANGE_SCORE - 1)
        && state.SCORE_LEFT <= (state.COURT_CHANGE_SCORE - 1))) {
            return false;
        }
    }
    updateIs3setCourtChanged(updateState(state, { type: 'UPDATE_3SET_COURT_CHANGED', IS_3SET_COURT_CHANGED: false }));
    return true;
};
  
// セット終了処理のキャンセル処理
const undoEndSet = (state) => {
    console.log("execute undoEndSet");
    const newState = updateState(state, { type: 'UNDO_END_SET' });
    updateIsGameFinished(newState, newState.IS_GAME_FINISHED);
    return newState;
}
  
// === /副作用を持つ関数: DOM操作、非同期処理、状態更新 ===
  
  
// === ビュー関連の純粋関数 ===
  
// セットごとのスコア表示を更新する関数
const updateSetNumberDisplay = (state) => {
    console.log("execute updateSetNumberDisplay"); 
    const popped = state.SCORE_STOCK_LIST.slice(-1)[0];
    const targetElement = popped === 'L' ? setNumberLeft[state.SET_NOW] : setNumberRight[state.SET_NOW];
    if (targetElement) {
        targetElement.textContent = popped === 'L' ? state.SCORE_LEFT : state.SCORE_RIGHT;
    }
};
  
// ボタンの表示状態を更新する関数
const updateButtonDisplay = (state) => {
    console.log("execute updateButtonDisplay");
    // 戻るボタン
    undoButton.style.display = state.SUM_POINTS === 0 ? "none" : "block";

    // セット終了ボタン
    setEndButton.style.display = state.IS_SET_FINISHED ? "block" : "none";

    // 加算ボタン
    if (state.IS_GAME_FINISHED || state.IS_SET_FINISHED) {
        buttonLeft.style.visibility = 'hidden';
        buttonRight.style.visibility = 'hidden';
    } else {
        buttonLeft.style.visibility = 'visible';
        buttonRight.style.visibility = 'visible';
    }
};
  
// サーブ表示を更新する関数
const updateServeDisplay = (state) => {
    console.log("execute updateServeDisplay");
    if (state.IS_SET_FINISHED) return;

    if (isAreguServe(state) === isAreguLeft(state)) {
        reguLeft.classList.add('active');
        reguRight.classList.remove('active');
        resetServeGage();
        serveGageLeft.classList.add('active');
    } else {
        reguLeft.classList.remove('active');
        reguRight.classList.add('active');
        resetServeGage();
        serveGageRight.classList.add('active');
    }
};
  
// サーブゲージをリセットする関数
const resetServeGage = () => {
    console.log("execute resetServeGage");
    serveGageLeft.classList.remove('active');
    serveGageRight.classList.remove('active');
};
  
// コールテキストを更新する関数
const updateCallTextDisplay = (state) => {
    console.log("execute updateCallTextDisplay");
    callMsg.textContent = generateCallText(state);
};
  
// コールテキストを生成する関数
const generateCallText = (state) => {
    console.log("execute generateCallText");
    // 試合が終了している場合
    if (state.IS_GAME_FINISHED) {
        return generateGameEndCallText(state);
    }
    // セットが終了している場合
    if (state.IS_SET_FINISHED) {
        return generateSetEndCallText(state);
    }
    // デュースモードの場合
    if (state.IS_DEUCE_MODE) {
        return generateDeuceModeCallText(state);
    }
    // セット開始時
    if (state.SCORE_LEFT === 0 && state.SCORE_RIGHT === 0) {
        return generateFirstCallText(state);        
    }
    // デュースの場合
    if (state.SCORE_LEFT === state.DEUCE_START_SCORE[state.SET_NOW]
        && state.SCORE_RIGHT === state.DEUCE_START_SCORE[state.SET_NOW]) {
        let text = "セッティング アップ トゥー ";
        text += state.NUMBER_TO_CALL[state.MAX_SCORE[state.SET_NOW]][1] + " ";
        text += state.NUMBER_TO_CALL[state.SCORE_LEFT][1] + " オール";
        return text;        
    }
    // デュース以外で同点の場合
    if (state.SCORE_LEFT == state.SCORE_RIGHT) {
        return state.NUMBER_TO_CALL[state.SCORE_LEFT][1] + " オール";
    }
    // 通常セットポイントの場合
    if (((state.SCORE_LEFT === state.DEUCE_START_SCORE[state.SET_NOW])
        && (state.SCORE_RIGHT <= (state.DEUCE_START_SCORE[state.SET_NOW] - 1)))
        || ((state.SCORE_RIGHT === state.DEUCE_START_SCORE[state.SET_NOW])
        && (state.SCORE_LEFT <= (state.DEUCE_START_SCORE[state.SET_NOW] - 1)))
        || ((state.SCORE_LEFT === (state.MAX_SCORE[state.SET_NOW] - 1))
        && (state.SCORE_RIGHT <= (state.MAX_SCORE[state.SET_NOW] - 2)))
        || ((state.SCORE_RIGHT === (state.MAX_SCORE[state.SET_NOW] - 1))
        && (state.SCORE_LEFT <= (state.MAX_SCORE[state.SET_NOW] - 2)))) {
        return generateFinalCallText(state);
    }
    // 通常の場合
    return generateNormalCallText(state);
};

// 試合が終了している場合
const generateGameEndCallText = (state) => {
    console.log("execute generateGameEndCallText");
    let callText = "";
    const scoreLeft = {
        1: parseInt(document.getElementById("setNumber1setLeft").textContent),
        2: parseInt(document.getElementById("setNumber2setLeft").textContent),
        3: parseInt(document.getElementById("setNumber3setLeft").textContent),
    };
    const scoreRight = {
        1: parseInt(document.getElementById("setNumber1setRight").textContent),
        2: parseInt(document.getElementById("setNumber2setRight").textContent),
        3: parseInt(document.getElementById("setNumber3setRight").textContent),
    };
    const isEquals = isAreguLeft(state) === isAreguSetWin(state);
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
        scoreWin[i] = isEquals ? scoreLeft[i] : scoreRight[i];
        scoreLose[i] = isEquals ? scoreRight[i] : scoreLeft[i];
        callText += SET_TO_CALL[i] + " ";
        callText += scoreWin[i] + " 対 " + scoreLose[i] + "、\n";
    }    
    const reguNameWin = isAreguSetWin(state) ? state.AREGU_ABB : state.BREGU_ABB;
    callText += reguNameWin + " ウィン！ シェイクハンド プリーズ";

    return callText;
}

// セット終了時のコールテキストを生成する関数
const generateSetEndCallText = (state) => {
    console.log("execute generateSetEndCallText");
    var callText;    
    const isEquals = isAreguLeft(state) === isAreguSetWin(state);
    let scoreWin = isEquals ? state.SCORE_LEFT : state.SCORE_RIGHT;
    let scoreLose = isEquals ? state.SCORE_RIGHT : state.SCORE_LEFT;
    let reguNameWin = isAreguSetWin(state) ? state.AREGU_ABB : state.BREGU_ABB;
    
    const SET_TO_CALL = {
        1: "ファーストセット",
        2: "セカンドセット",
        3: "サードセット"
    };
    callText = SET_TO_CALL[state.SET_NOW] + " ";
    callText += scoreWin + " 対 " + scoreLose + " " + reguNameWin + "  ウィン！";

    return callText;
};

// デュースモードのコールテキストを生成する関数
const generateDeuceModeCallText = (state) => {
    console.log("execute generateDeuceModeCallText");
    let callText = "";    
    let scoreA = isAreguLeft(state) ? state.SCORE_LEFT : state.SCORE_RIGHT;
    let scoreB = isAreguLeft(state) ? state.SCORE_RIGHT : state.SCORE_LEFT;
    // セットポイントまたはマッチポイントの場合
    if ((state.SCORE_LEFT === state.MAX_SCORE[state.SET_NOW] - 1)
        && (state.SCORE_RIGHT <= state.MAX_SCORE[state.SET_NOW] - 2)) {
        let reguText = isAreguLeft(state) ? 'A' : 'B';
        callText += isMatchPoint(state, reguText) ? " マッチポイント " : " セットポイント ";
        if (isAreguServe(state)) {
            callText += state.NUMBER_TO_CALL[scoreA][1] + " - " + state.NUMBER_TO_CALL[scoreB][1];
        } else {
            callText += state.NUMBER_TO_CALL[scoreB][1] + " - " + state.NUMBER_TO_CALL[scoreA][1];
        }
        return callText;
    } else if ((state.SCORE_RIGHT === state.MAX_SCORE[state.SET_NOW] - 1)
        && (state.SCORE_LEFT <= state.MAX_SCORE[state.SET_NOW] - 2)) {
        let reguText = isAreguLeft(state) ? 'B' : 'A';
        callText += isMatchPoint(state, reguText) ? " マッチポイント " : " セットポイント ";
        if (isAreguServe(state)) {
            callText += state.NUMBER_TO_CALL[scoreA][1] + " - " + state.NUMBER_TO_CALL[scoreB][1];
        } else {
            callText += state.NUMBER_TO_CALL[scoreB][1] + " - " + state.NUMBER_TO_CALL[scoreA][1];
        }
        return callText;
    }
    // 同点の場合
    if (state.SCORE_LEFT === state.SCORE_RIGHT) {
        // セットポイントまたはマッチポイントの場合
        if ((state.SCORE_LEFT === state.MAX_SCORE[state.SET_NOW] - 1)
            && (state.SCORE_RIGHT === state.MAX_SCORE[state.SET_NOW] - 1)) {
            let isMatchPointForA = isMatchPoint(state, 'A');
            let isMatchPointForB = isMatchPoint(state, 'B');
            if (isMatchPointForA && isMatchPointForB) {
                callText += " マッチポイント ";
            } else if (!isMatchPointForA && !isMatchPointForB) {
                callText += " セットポイント ";
            }
        }
        callText += state.NUMBER_TO_CALL[state.SCORE_LEFT][1] + " オール";
        return callText;
    }
    // それ以外の場合
    if (isAreguServe(state)) {
        callText += state.NUMBER_TO_CALL[scoreA][1] + " - " + state.NUMBER_TO_CALL[scoreB][1];
    } else {
        callText += state.NUMBER_TO_CALL[scoreB][1] + " - " + state.NUMBER_TO_CALL[scoreA][1];
    }
    return callText;
}
  
// セット開始時のコールテキストを生成する関数
const generateFirstCallText = (state) => {
    console.log("execute generateFirstCallText");
    let callText;
    const SET_TO_CALL = {
        1: "ファーストセット",
        2: "セカンドセット",
        3: "サードセット"
    };
    callText = SET_TO_CALL[state.SET_NOW] + " ";

    const serverName = isAreguServe(state) ? state.AREGU_ABB : state.BREGU_ABB;
    callText += serverName + " サーブ ラブ オール";

    return callText;
};
  
// セット終了間際のコールテキストを生成する関数
const generateFinalCallText = (state) => {
    console.log("execute generateFinalCallText");
    var callText;
    let scoreA = isAreguLeft(state) ? state.SCORE_LEFT : state.SCORE_RIGHT;
    let scoreB = isAreguLeft(state) ? state.SCORE_RIGHT : state.SCORE_LEFT;
    let popped = state.SCORE_STOCK_LIST.slice(-1)[0];
    let isLeftReguPointGot = popped === 'L';
    let isAreguPointGot = isAreguLeft(state) === isLeftReguPointGot;

    if (scoreA > scoreB) {
        callText = isMatchPoint(state, 'A') ? " マッチポイント " : " セットポイント ";
    } else {
        callText = isMatchPoint(state, 'B') ? " マッチポイント " : " セットポイント ";
    }
    if ((isAreguPointGot && scoreB >= state.DEUCE_START_SCORE[state.SET_NOW])
        || (!isAreguPointGot) && scoreA >= state.DEUCE_START_SCORE[state.SET_NOW]) {
        callText = "";
    }
    if (isAreguServe(state)) {
        callText += state.NUMBER_TO_CALL[scoreA][1] + " - " + state.NUMBER_TO_CALL[scoreB][1];
    } else {
        callText += state.NUMBER_TO_CALL[scoreB][1] + " - " + state.NUMBER_TO_CALL[scoreA][1];
    }
    return callText;
};
  
// 通常時のコールテキストを生成する関数
const generateNormalCallText = (state) => {
    console.log("execute generateNormalCallText");
    var callText;
    let scoreA = isAreguLeft(state) ? state.SCORE_LEFT : state.SCORE_RIGHT;
    let scoreB = isAreguLeft(state) ? state.SCORE_RIGHT : state.SCORE_LEFT;
    if (isAreguServe(state)) {
        callText = state.NUMBER_TO_CALL[scoreA][1] + " - " + state.NUMBER_TO_CALL[scoreB][1];
    } else {
        callText = state.NUMBER_TO_CALL[scoreB][1] + " - " + state.NUMBER_TO_CALL[scoreA][1];
    }
    return callText;
}
  
// === /ビュー関連の純粋関数 ===
  
// === イベントリスナー ===
  
// 加算ボタン
buttonLeft.addEventListener('click', () => {
    currentState = handleScoreUpdate(currentState, true);
    render(currentState);
});
  
buttonRight.addEventListener('click', () => {
    currentState = handleScoreUpdate(currentState, false);
    render(currentState);
});
  
// 戻るボタン
undoButton.addEventListener('click', () => {
    currentState = handleUndoScore(currentState);
    render(currentState);
});
  
// セット終了ボタン
setEndButton.addEventListener('click', () => {
    handleSetEnd(currentState);
});
  
// フォーム送信時
const form = document.getElementById('main-submit-form');
form.addEventListener('submit', function(e) {
    sessionStorage.removeItem('gameState');
});
  
// === /イベントリスナー ===

// 状態の保存
const saveStateToSessionStorage = (state) => {
    sessionStorage.setItem('gameState', JSON.stringify(state));
};
// saveStateToSessionStorage(currentState);

// 状態の読み込み
const loadStateFromSessionStorage = () => {
    const storedState = sessionStorage.getItem('gameState');
    return storedState ? JSON.parse(storedState) : initialState;
};
  
// 初期状態を設定
let currentState = loadStateFromSessionStorage();
  
// 初期表示を更新
render(currentState);