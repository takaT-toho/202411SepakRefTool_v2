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
    SUM_POINTS_COURT_CHANGED: 0,
    IS_FIN_SET: {
        1: document.getElementById("IS_FIN_1SET").textContent === "true",
        2: document.getElementById("IS_FIN_2SET").textContent === "true",
        3: document.getElementById("IS_FIN_3SET").textContent === "true",
    },
    IS_SET_FINISHED: false,
    IS_GAME_FINISHED: document.getElementById("IS_GAME_FINISHED").textContent === "true",
    IS_DEUCE_MODE: false,
    IS_RPS_MODE: false,
    IS_AREGU_SET_WIN: false,
    IS_AREGU_GAME_WIN: false,
  
    // スコア履歴
    SCORE_STOCK_LIST: JSON.parse(sessionStorage.getItem('SCORE_STOCK_LIST')) || [],
    SCORE_LEFT: parseInt(document.getElementById("scoreLeft").textContent) || 0,
    SCORE_RIGHT: parseInt(document.getElementById("scoreRight").textContent) || 0,

    SET_NUMBER_LEFT: {
        1: parseInt(document.getElementById("setNumber1setLeft").textContent),
        2: parseInt(document.getElementById("setNumber2setLeft").textContent),
        3: parseInt(document.getElementById("setNumber3setLeft").textContent),
    },
    SET_NUMBER_RIGHT: {
        1: parseInt(document.getElementById("setNumber1setRight").textContent),
        2: parseInt(document.getElementById("setNumber2setRight").textContent),
        3: parseInt(document.getElementById("setNumber3setRight").textContent),
    },
  
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

    IS_PROCESSING: false,
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
                SET_NUMBER_LEFT: getSetNumber(state.SET_NUMBER_LEFT, state.SET_NOW, state.SCORE_LEFT + 1),
                SCORE_STOCK_LIST: [...state.SCORE_STOCK_LIST, 'L'],
                SUM_POINTS: state.SUM_POINTS + 1,
                IS_SET_FINISHED: checkHasSetFinished(state.SCORE_LEFT + 1, state.SCORE_RIGHT, state.MAX_SCORE[state.SET_NOW], state.DEUCE_START_SCORE[state.SET_NOW]),
            };
        case 'ADD_RIGHT_SCORE':
            return {
                ...state,
                SCORE_RIGHT: state.SCORE_RIGHT + 1,
                SET_NUMBER_RIGHT: getSetNumber(state.SET_NUMBER_RIGHT, state.SET_NOW, state.SCORE_RIGHT + 1),
                SCORE_STOCK_LIST: [...state.SCORE_STOCK_LIST, 'R'],
                SUM_POINTS: state.SUM_POINTS + 1,
                IS_SET_FINISHED: checkHasSetFinished(state.SCORE_LEFT, state.SCORE_RIGHT + 1, state.MAX_SCORE[state.SET_NOW], state.DEUCE_START_SCORE[state.SET_NOW]),
            };
        case 'UNDO_SCORE':
            const popped = state.SCORE_STOCK_LIST.slice(-1)[0];
            let newState = null;

            // コートチェンジ直後の状態に戻った場合
            if (state.SET_NOW === 3 && state.IS_3SET_COURT_CHANGED) {
                const courtChangeTargetScore = state.COURT_CHANGE_SCORE - 1;

                //共通処理を呼び出す
                if ((popped === 'R' && (state.SCORE_LEFT - 1) === courtChangeTargetScore && (state.SCORE_RIGHT <= courtChangeTargetScore) && state.SUM_POINTS_COURT_CHANGED === (state.SCORE_LEFT + state.SCORE_RIGHT)) ||
                    (popped === 'L' && (state.SCORE_RIGHT - 1) === courtChangeTargetScore && (state.SCORE_LEFT <= courtChangeTargetScore) && state.SUM_POINTS_COURT_CHANGED === (state.SCORE_LEFT + state.SCORE_RIGHT))) {
                    newState = revertCourtChange(state);
                }
            }
            const newNewState = newState ? newState : state;

            if (popped === 'L') {
                newNewState.SCORE_LEFT = newNewState.SCORE_LEFT > 0 ? newNewState.SCORE_LEFT - 1 : 0;
            } else {
                newNewState.SCORE_RIGHT = newNewState.SCORE_RIGHT > 0 ? newNewState.SCORE_RIGHT - 1 : 0;
            }
                
            newNewState.SET_NUMBER_LEFT = getSetNumber(newNewState.SET_NUMBER_LEFT, newNewState.SET_NOW, newNewState.SCORE_LEFT);
            newNewState.SET_NUMBER_RIGHT = getSetNumber(newNewState.SET_NUMBER_RIGHT, newNewState.SET_NOW, newNewState.SCORE_RIGHT);
            newNewState.SCORE_STOCK_LIST = newNewState.SCORE_STOCK_LIST.slice(0, -1);
            newNewState.SUM_POINTS = newNewState.SUM_POINTS > 0 ? newNewState.SUM_POINTS - 1 : 0;

            return newNewState;
        case 'UNDO_END_SET':
            return {
                ...state,
                IS_SET_FINISHED: false,
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
                SCORE_LEFT: state.SCORE_RIGHT,
                SCORE_RIGHT: state.SCORE_LEFT,
                SET_NUMBER_LEFT: reverseSetNumberDisplay(state).SET_NUMBER_LEFT,
                SET_NUMBER_RIGHT: reverseSetNumberDisplay(state).SET_NUMBER_RIGHT,
                SUM_POINTS_COURT_CHANGED: state.SUM_POINTS,
            }
        case 'UPDATE_NOT_COURT_CHANGED_YET':
            return {
                ...state,
                IS_NOT_COURT_CHANGED_YET: action.IS_NOT_COURT_CHANGED_YET,
            }
        case 'UPDATE_RPS_MODE':
            return {
                ...state,
                IS_RPS_MODE: action.IS_RPS_MODE,
            }
        case 'UPDATE_IS_AREGU_SET_WIN':
            return {
                ...state,
                IS_AREGU_SET_WIN: action.IS_AREGU_SET_WIN,
            }
        case 'UPDATE_GAME_FINISHED':
            return {
                ...state,
                IS_GAME_FINISHED: action.IS_GAME_FINISHED,
            }
        case 'UPDATE_IS_AREGU_GAME_WIN':
            return {
                ...state,
                IS_AREGU_GAME_WIN: action.IS_AREGU_GAME_WIN,
            }
        default:
        return state;
    }
};

// コートチェンジを戻す処理を関数として抽出
const revertCourtChange = (state) => {
    console.log("execute revertCourtChange");

    const scoreLeftTemp = state.SCORE_LEFT;
    const scoreRightTemp = state.SCORE_RIGHT;
    const newState = {
        ...state,
        SCORE_LEFT: scoreRightTemp,
        SCORE_RIGHT: scoreLeftTemp,
        IS_3SET_COURT_CHANGED: false,
        SUM_POINTS_COURT_CHANGED: 0,
    };
    const newState2 = {
        ...newState,
        SET_NUMBER_LEFT: {...newState.SET_NUMBER_RIGHT},
        SET_NUMBER_RIGHT: {...newState.SET_NUMBER_LEFT},
    };
    console.log("newState 5 : ", {...newState2});
    updateIs3setCourtChanged({...newState2});
    return {...newState2};
};

// すべてのセットのスコア表示を逆にする関数
const reverseSetNumberDisplay = (state) => {
    console.log("execute reverseSetNumberDisplay");
    return {
        ...state,
        SET_NUMBER_LEFT: {
            1: state.SET_NUMBER_RIGHT[1],
            2: state.SET_NUMBER_RIGHT[2],
            3: state.SCORE_RIGHT,
        },
        SET_NUMBER_RIGHT: {
            1: state.SET_NUMBER_LEFT[1],
            2: state.SET_NUMBER_LEFT[2],
            3: state.SCORE_LEFT,
        },
    };
}

// セットごとのスコアを取得する関数
const getSetNumber = (setNumber, setNow, score) => {
    console.log("execute getSetNumber");
    if (setNow === 1) {
        return {
            ...setNumber,
            1: score,
        };
    } else if (setNow === 2) {
        return {
            ...setNumber,
            2: score,
        };
    } else if (setNow === 3) {
        return {
            ...setNumber,
            3: score,
        };
    }
    return setNumber;
};
  
// セットが終了しているかどうかをチェック
const checkHasSetFinished = (SCORE_LEFT, SCORE_RIGHT, MAX_SCORE, DEUCE_START_SCORE) => {
    console.log("execute checkHasSetFinished");
    // どちらかのスコアが最大スコアに達した場合、セットが終了
    if (SCORE_LEFT === MAX_SCORE || SCORE_RIGHT === MAX_SCORE) {
        return true;
    // 一方が基準点で、もう一方が基準点-2点以下の場合、セットが終了
    } else if (((SCORE_LEFT === DEUCE_START_SCORE + 1)
        && (SCORE_RIGHT <= DEUCE_START_SCORE - 1))
        || ((SCORE_LEFT <= DEUCE_START_SCORE - 1)
        && (SCORE_RIGHT === DEUCE_START_SCORE + 1))) {
        return true;
    }

    return false;
};
  
// Aレグがその時点でサーブかどうかを判定する関数
const isAreguServe = (state) => {
    console.log("execute isAreguServe");
    const isOddSet = state.SET_NOW === 1 || state.SET_NOW === 3;
    const isEvenSumPoints = state.SUM_POINTS % 2 === 0;

    return isOddSet === state.IS_AREGU_FIRST_SERVE === isEvenSumPoints;
};
  
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

// Aレグがこのポイントを取ったのか判定する関数
const isAreguPointGot = (state) => {
    console.log("execute isAreguPointGot");
    const popped = state.SCORE_STOCK_LIST.slice(-1)[0];
    const isLeftReguPointGot = popped === 'L';
    return isAreguLeft(state) === isLeftReguPointGot;
};
  
// マッチポイントのチームを判定する関数
const isMatchPoint = (state, regu) => {
    console.log("execute isMatchPoint");
    if (regu === 'A') {
        return state.SET_GOY_BY_A === 1;
    } else if (regu === 'B') {
        return state.SET_GOY_BY_B === 1;
    }
    return false;
};

// 明らかに勝負がついている
const isPerfectWin = (state) => {
    console.log("isPerfectWin");
    // このセットはAレグが勝った場合
    if (isAreguPointGot(state)) {
        return state.SET_GOY_BY_A === 1;
    // このセットはBレグが勝った場合
    } else {
        return state.SET_GOY_BY_B === 1;
    }
};

const checkIsGameFinished = (state) => {
    console.log("execute checkIsGameFinished");
    // 最大セット数の場合
    if (state.SET_NOW === state.MAX_SET) {
        // 既に勝負がついている場合(どちらかがXセット目を獲得している場合)
        if (isPerfectWin(state)) {
            // 試合を終了して返す
            const newState = updateState(state, { type: 'UPDATE_IS_AREGU_GAME_WIN', IS_AREGU_GAME_WIN: isAreguPointGot(state)});
            return updateState(newState, { type: 'UPDATE_GAME_FINISHED', IS_GAME_FINISHED: true });
        } else {
        // そうでない場合(0セットまたは1セット差の場合)
            // 試合ルールが引き分け認める場合
            if (state.GAME_RULE === "draw") {
                return updateState(state, { type: 'UPDATE_GAME_FINISHED', IS_GAME_FINISHED: true });
            // 試合ルールが得失点で勝負をつける場合
            } else if (state.GAME_RULE === "drawGD") {
                const goalDifference = getGoalDifference(state);
                // 得失点で勝敗が決まる場合
                if (goalDifference > 0) {
                    // 試合を終了して返す
                    const newState = updateState(state, { type: 'UPDATE_IS_AREGU_GAME_WIN', IS_AREGU_GAME_WIN: isAreguLeft(state)});
                    return updateState(newState, { type: 'UPDATE_GAME_FINISHED', IS_GAME_FINISHED: true });
                } else if (goalDifference < 0) {
                    // 試合を終了して返す
                    const newState = updateState(state, { type: 'UPDATE_IS_AREGU_GAME_WIN', IS_AREGU_GAME_WIN: !isAreguLeft(state)});
                    return updateState(newState, { type: 'UPDATE_GAME_FINISHED', IS_GAME_FINISHED: true });
                // 得失点で勝敗が決まらない場合
                } else {
                    // じゃんけんモードにして返す
                    return updateState(state, { type: 'UPDATE_RPS_MODE', IS_RPS_MODE: true });
                }
            }
        }
    // そうでない場合
    } else {
        // 既に勝負がついている場合
        if (isPerfectWin(state)) {
            // 試合を終了して返す
            const newState = updateState(state, { type: 'UPDATE_IS_AREGU_GAME_WIN', IS_AREGU_GAME_WIN: isAreguPointGot(state)});
            return updateState(newState, { type: 'UPDATE_GAME_FINISHED', IS_GAME_FINISHED: true });
        // そうでない場合
        } else {
            // そのまま返す
            return state;
        }
    }
};

// 得失点差を取得する
const getGoalDifference = (state) => {
    console.log("execute getGoalDifference");
    return state.SET_NUMBER_LEFT[1] - state.SET_NUMBER_RIGHT[1] + state.SET_NUMBER_LEFT[2] - state.SET_NUMBER_RIGHT[2];
};

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
    if (state.IS_PROCESSING) return;
    console.log("execute render");

    // スコア表示を更新
    scoreLeft.textContent = state.SCORE_LEFT;
    scoreRight.textContent = state.SCORE_RIGHT;

    // 3セット目のコートチェンジがされた場合、左右のレグ名表示を逆にする
    if (state.SET_NOW === 3) {
        updateReguDisplay(state);
    }

    // セットごとのスコア表示を更新
    updateSetNumberDisplay(state);

    // ボタンの表示状態を更新
    updateButtonDisplay(state);

    // コールテキストを更新
    updateCallTextDisplay(state);

    // サーブ表示を更新
    updateServeDisplay(state);
    
    document.getElementById("isAreguSetWin").value = state.IS_AREGU_SET_WIN;

    // アニメーションの完了を待つ
};

// 上位の関数で状態と isProcessing を管理
const handleButtonClick = (state, isLeftScore) => {
    if (state.IS_PROCESSING) return state;
    const newState = {...state, IS_PROCESSING: true};

    const updatedState = isLeftScore ? handleScoreUpdateInner(newState, true) : handleScoreUpdateInner(newState, false);

    return {...updatedState, IS_PROCESSING: false};
}
  
// スコア更新時の処理
const handleScoreUpdateInner = (state, isLeftScore) => {
    console.log("execute handleScoreUpdate");

    // 連打対策：ボタンを無効化
    buttonLeft.style.pointerEvents = 'none';
    buttonRight.style.pointerEvents = 'none';
    undoButton.style.pointerEvents = 'none';
    buttonLeft.style.opacity = '0.5';
    buttonRight.style.opacity = '0.5';
    undoButton.style.opacity = '0.5';
    

    const newState = updateState(state, { type: isLeftScore ? 'ADD_LEFT_SCORE' : 'ADD_RIGHT_SCORE' });
    console.log(newState);

    try{
        // データベースへの保存
        insertGameEventHistoryPoints(
            newState,
            isLeftScore ? newState.SCORE_LEFT : newState.SCORE_RIGHT,
            isLeftScore === isAreguLeft(newState)
        );

        // アニメーション開始
        startAnimation(isLeftScore ? 'LEFT' : 'RIGHT');

        // デュースモードの判定と処理
        const deuceModeState = checkDeuceMode(newState);

        // 3セット目のコートチェンジ判定と処理
        const courtChangedState = checkAndHandleCourtChange(deuceModeState);

        // セットが終了している場合
        if (courtChangedState.IS_SET_FINISHED) {
            const isSetWinState = updateState(courtChangedState, { type: 'UPDATE_IS_AREGU_SET_WIN', IS_AREGU_SET_WIN: isAreguPointGot(courtChangedState)});
            document.getElementById("isAreguSetWin").value = isSetWinState.IS_AREGU_SET_WIN;
            updateIsSetFinished(isSetWinState);
            // ここがじゃんけんモードの判定ではなく、試合終了判定とし、じゃんけんモード、継続モード、試合終了モードに分岐する。
            const isGameFinishedState = checkIsGameFinished(isSetWinState);
            // それに応じて、処理を分岐する。
            if (isGameFinishedState.IS_RPS_MODE) {
                SetEndButton.textContent = "結果が決まりません。じゃんけんを行います";
                SetEndButton.style.display = "block";
                SetEndButton.value = "p0203";
            } else if (isGameFinishedState.IS_GAME_FINISHED) {
                document.getElementById("isAreguGameWin").value = isGameFinishedState.IS_AREGU_GAME_WIN;
                updateIsGameFinished(isGameFinishedState);
                SetEndButton.textContent = "結果を送信し、試合を終了する";
                SetEndButton.style.display = "block";
            } else {
                SetEndButton.textContent = isGameFinishedState.SET_NOW.toString() + "セット目の結果を送る";
                SetEndButton.style.display = "block";
            }
            return isGameFinishedState;
        }
        return courtChangedState;
    } catch (error) {
        console.error('Error:', error);
    } finally {
        // 連打対策：ボタンを有効化
        buttonLeft.style.pointerEvents = 'auto';
        buttonRight.style.pointerEvents = 'auto';
        undoButton.style.pointerEvents = 'auto';
        buttonLeft.style.opacity = '1';
        buttonRight.style.opacity = '1';
        undoButton.style.opacity = '1';

    }
    return newState;
};
  
// 戻るボタンクリック時の処理
const handleUndoScoreInner = (state) => {
    if (state.IS_PROCESSING) return state;
    console.log("execute handleUndoScore");
    const processingState = {...state, IS_PROCESSING: true};

    // 連打対策：ボタンを無効化
    buttonLeft.style.pointerEvents = 'none';
    buttonRight.style.pointerEvents = 'none';
    undoButton.style.pointerEvents = 'none';
    buttonLeft.style.opacity = '0.5';
    buttonRight.style.opacity = '0.5';
    undoButton.style.opacity = '0.5';

    const newState = updateState(processingState, { type: 'UNDO_SCORE' });

    const undoDeuceModeState = undoIsDeuceMode(newState);

    try {
        const undoEndSetState = undoDeuceModeState.IS_SET_FINISHED ? updateState(undoDeuceModeState, { type: 'UNDO_END_SET' }) : undoDeuceModeState;
        const undoEndGameState = undoEndSetState.IS_GAME_FINISHED ? updateState(undoEndSetState, { type: 'UPDATE_GAME_FINISHED', IS_GAME_FINISHED: false }) : undoEndSetState;
        deleteGameEventHistoryPoints(undoEndGameState);
        return {...undoEndGameState, IS_PROCESSING: false}
    } catch (error) {
        console.error('Error:', error);
    } finally {
        // 連打対策：ボタンを有効化
        buttonLeft.style.pointerEvents = 'auto';
        buttonRight.style.pointerEvents = 'auto';
        undoButton.style.pointerEvents = 'auto';
        buttonLeft.style.opacity = '1';
        buttonRight.style.opacity = '1';
        undoButton.style.opacity = '1';
    }
    return {...undoDeuceModeState, IS_PROCESSING: false};
};
  
// セット終了ボタンクリック時の処理
const handleSetEnd = (state) => {
    if (state.IS_PROCESSING) return state;
    console.log("execute handleSetEnd");
    const processingState = {...state, IS_PROCESSING: true};
    updateIsGameFinished(processingState);
    return {...processingState, IS_PROCESSING: false};
}
  
// 非同期処理: ゲームイベント履歴をDBに保存
const insertGameEventHistoryPoints = async (state, score, isAreguGot) => {
    console.log("execute insertGameEventHistoryPoints");
    const popped = state.SCORE_STOCK_LIST.slice(-2)[0];
    const previousRegu = (popped === 'L') === isAreguLeft(state) ? 'A' : 'B';
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
            method: 'POST',
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

// セット終了を更新する
const updateIsSetFinished = async (state) => {
    console.log("execute updateIsSetFinished");
    try {
        const response = await fetch('async', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                gameId: state.GAME_ID,
                buttonId: "p0201",
                setNum: state.SET_NOW,
                isSetFinished: state.IS_SET_FINISHED,
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
  
const updateIsGameFinished = async (state) => {
    console.log("execute updateIsGameFinished");
    try {
        const response = await fetch('async', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                gameId: state.GAME_ID,
                buttonId: "p0210",
                isGameFinished: state.IS_GAME_FINISHED,
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
        return updateState(state, { type: 'UPDATE_DEUCE_MODE', IS_DEUCE_MODE: true });
    }
    return state;
};
  
// 3セット目のコートチェンジを判定する
const checkAndHandleCourtChange = (state) => {
    console.log("execute checkAndHandleCourtChange");
    // 既にコートチェンジ済みの場合は何もしない
    if (state.IS_3SET_COURT_CHANGED) return state;
    // 3セット目かつ、どちらかのチームが8点になった時にコートチェンジする
    if (state.SET_NOW === 3 && (state.SCORE_LEFT === state.COURT_CHANGE_SCORE || state.SCORE_RIGHT === state.COURT_CHANGE_SCORE)) {
        const newState = updateState(state, { type: 'UPDATE_3SET_COURT_CHANGED', IS_3SET_COURT_CHANGED: true });
        updateIs3setCourtChanged(newState);
        window.alert(state.COURT_CHANGE_SCORE + "点になりました。コートチェンジしてください。\n「チェンジコート(Change Court)!」");

        return newState;
    }
    return state;
};

// デュースモードを解除する
const undoIsDeuceMode = (state) => {
    console.log("execute undoIsDeuceMode");
    if (!state.IS_DEUCE_MODE) return state;

    if ((state.SCORE_LEFT < state.DEUCE_START_SCORE[state.SET_NOW])
        || (state.SCORE_RIGHT < state.DEUCE_START_SCORE[state.SET_NOW])) {
        return updateState(state, { type: 'UPDATE_DEUCE_MODE', IS_DEUCE_MODE: false });
    }
    return state;
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
    const newState = updateState(state, { type: 'UNDO_END_SET', IS_SET_FINISHED: false });
    updateIsSetFinished(newState);
    updateIsGameFinished(newState);
    return newState;
};
  
// === /副作用を持つ関数: DOM操作、非同期処理、状態更新 ===
  
  
// === ビュー関連の純粋関数 ===

// レグ表示を更新する関数
const updateReguDisplay = (state) => {
    console.log("execute updateReguDisplay");

    if ((state.IS_AREGU_FIRST_LEFT === (state.SET_NOW % 2 === 1)) === !state.IS_3SET_COURT_CHANGED) {
        reguLeft.textContent = state.AREGU_ABB;
        reguRight.textContent = state.BREGU_ABB;
    } else {
        reguLeft.textContent = state.BREGU_ABB;
        reguRight.textContent = state.AREGU_ABB;
    }
};

  
// セットごとのスコア表示を更新する関数
const updateSetNumberDisplay = (state) => {
    console.log("execute updateSetNumberDisplay");

    const targetElement = {
        1: [setNumberLeft[1], setNumberRight[1]],
        2: [setNumberLeft[2], setNumberRight[2]],
        3: [setNumberLeft[3], setNumberRight[3]],
    };
    for (let i = 1; i <= 3; i++) {
        if (i === state.SET_NOW) {
            targetElement[i][0].textContent = state.SCORE_LEFT;
            targetElement[i][1].textContent = state.SCORE_RIGHT;
        } else {
            targetElement[i][0].textContent = state.SET_NUMBER_LEFT[i];
            targetElement[i][1].textContent = state.SET_NUMBER_RIGHT[i];
        }
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
    if (state.IS_SET_FINISHED) {
        resetReguServe();
        resetServeGage();
        return;
    };

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

// レグのサーブ表示をリセットする関数
const resetReguServe = () => {
    console.log("execute resetReguServe");
    if (reguLeft.classList.contains('active')) {
        reguLeft.classList.remove('active');
    }
    if (reguRight.classList.contains('active')) {
        reguRight.classList.remove('active');
    }
};
  
// サーブゲージをリセットする関数
const resetServeGage = () => {
    console.log("execute resetServeGage");
    if (serveGageLeft.classList.contains('active')) {
        serveGageLeft.classList.remove('active');
    }
    if (serveGageRight.classList.contains('active')) {
        serveGageRight.classList.remove('active');
    }
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
    const isAreguGameWin = state.IS_AREGU_GAME_WIN;
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

// セット終了時のコールテキストを生成する関数
const generateSetEndCallText = (state) => {
    console.log("execute generateSetEndCallText");
    let callText;    
    const isEquals = isAreguLeft(state) === isAreguPointGot(state);
    const scoreWin = isEquals ? state.SCORE_LEFT : state.SCORE_RIGHT;
    const scoreLose = isEquals ? state.SCORE_RIGHT : state.SCORE_LEFT;
    const reguNameWin = isAreguPointGot(state) ? state.AREGU_ABB : state.BREGU_ABB;
    
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
    const scoreA = isAreguLeft(state) ? state.SCORE_LEFT : state.SCORE_RIGHT;
    const scoreB = isAreguLeft(state) ? state.SCORE_RIGHT : state.SCORE_LEFT;
    // セットポイントまたはマッチポイントの場合
    if ((state.SCORE_LEFT === state.MAX_SCORE[state.SET_NOW] - 1)
        && (state.SCORE_RIGHT <= state.MAX_SCORE[state.SET_NOW] - 2)) {
        const reguText = isAreguLeft(state) ? 'A' : 'B';
        callText += isMatchPoint(state, reguText) ? " マッチポイント " : " セットポイント ";
        if (isAreguServe(state)) {
            callText += state.NUMBER_TO_CALL[scoreA][1] + " - " + state.NUMBER_TO_CALL[scoreB][1];
        } else {
            callText += state.NUMBER_TO_CALL[scoreB][1] + " - " + state.NUMBER_TO_CALL[scoreA][1];
        }
        return callText;
    }
    if ((state.SCORE_RIGHT === state.MAX_SCORE[state.SET_NOW] - 1)
        && (state.SCORE_LEFT <= state.MAX_SCORE[state.SET_NOW] - 2)) {
        const reguText = isAreguLeft(state) ? 'B' : 'A';
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
        // デュースになった場合
        if ((state.SCORE_LEFT === state.DEUCE_START_SCORE[state.SET_NOW])
            && (state.SCORE_RIGHT === state.DEUCE_START_SCORE[state.SET_NOW])) {
            callText += " セッティング アップ トゥー ";
            callText += state.NUMBER_TO_CALL[state.MAX_SCORE[state.SET_NOW]][1] + " ";
        }
        // セットポイントまたはマッチポイントの場合
        if ((state.SCORE_LEFT === state.MAX_SCORE[state.SET_NOW] - 1)
            && (state.SCORE_RIGHT === state.MAX_SCORE[state.SET_NOW] - 1)) {
            const isMatchPointForA = isMatchPoint(state, 'A');
            const isMatchPointForB = isMatchPoint(state, 'B');
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
};
  
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
    let callText;
    const scoreA = isAreguLeft(state) ? state.SCORE_LEFT : state.SCORE_RIGHT;
    const scoreB = isAreguLeft(state) ? state.SCORE_RIGHT : state.SCORE_LEFT;
    const _isAreguPointGot = isAreguPointGot(state);

    if (scoreA > scoreB) {
        callText = isMatchPoint(state, 'A') ? " マッチポイント " : " セットポイント ";
    } else {
        callText = isMatchPoint(state, 'B') ? " マッチポイント " : " セットポイント ";
    }
    if ((_isAreguPointGot && scoreB >= state.DEUCE_START_SCORE[state.SET_NOW])
        || (!_isAreguPointGot) && scoreA >= state.DEUCE_START_SCORE[state.SET_NOW]) {
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
    let callText;
    const scoreA = isAreguLeft(state) ? state.SCORE_LEFT : state.SCORE_RIGHT;
    const scoreB = isAreguLeft(state) ? state.SCORE_RIGHT : state.SCORE_LEFT;
    if (isAreguServe(state)) {
        callText = state.NUMBER_TO_CALL[scoreA][1] + " - " + state.NUMBER_TO_CALL[scoreB][1];
    } else {
        callText = state.NUMBER_TO_CALL[scoreB][1] + " - " + state.NUMBER_TO_CALL[scoreA][1];
    }
    return callText;
};
  
// === /ビュー関連の純粋関数 ===
  
// === イベントリスナー ===

// 最初の実行結果をすぐに返し、指定時間内の追加のリクエストを破棄する関数
const throttleOnceImmediate = (func, delay) => {
    let isThrottled = false; // 処理中かどうかのフラグ
    let result; // 結果を保存する変数

    return (...args) => {
        if (isThrottled) return result; // 処理中は前回の結果を返す

        isThrottled = true; // 処理を開始
        result = func(...args); // 関数を実行し、結果を保存

        // 指定時間後にフラグをリセット
        setTimeout(() => {
            isThrottled = false;
        }, delay);

        return result; // 最初の実行結果をすぐに返す
    };
};

const throttledHandleButtonClick = throttleOnceImmediate(handleButtonClick, 500);
const throttledHandleUndoScore = throttleOnceImmediate(handleUndoScoreInner, 500);

// 加算ボタン
buttonLeft.addEventListener('click', () => {
    currentState = throttledHandleButtonClick(currentState, true);
    saveStateToSessionStorage(currentState);
    render(currentState);
});
  
buttonRight.addEventListener('click', () => {
    currentState = throttledHandleButtonClick(currentState, false);
    saveStateToSessionStorage(currentState);
    render(currentState);
});
  
// 戻るボタン
undoButton.addEventListener('click', () => {
    currentState = throttledHandleUndoScore(currentState);
    saveStateToSessionStorage(currentState);
    render(currentState);
});
  
// セット終了ボタン
setEndButton.addEventListener('click', () => {
    currentState = handleSetEnd(currentState);
    saveStateToSessionStorage(currentState);
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

// 状態の読み込み
const loadStateFromSessionStorage = () => {
    const storedState = sessionStorage.getItem('gameState');
    return storedState ? JSON.parse(storedState) : initialState;
};
  
// 初期状態を設定
let currentState = loadStateFromSessionStorage();
  
// 初期表示を更新
render(currentState);