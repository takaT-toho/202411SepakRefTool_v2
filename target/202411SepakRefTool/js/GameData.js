export default class GameData {
    constructor() {
      // DBから取得したゲームデータ
      this.gameId = document.getElementById("gameId").textContent;
      this.isAreguFirstServe = document.getElementById("isAreguFirstServe").textContent === "true";
      this.isAreguFirstLeft = document.getElementById("isAreguLeft").textContent === "true";
      this.AreguName = document.getElementById("reguAName").textContent;
      this.BreguName = document.getElementById("reguBName").textContent;
      this.setGotByA = parseInt(document.getElementById("setNumGotByA").textContent);
      this.setGotByB = parseInt(document.getElementById("setNumGotByB").textContent);
      this.setNow = parseInt(document.getElementById("setNow").value);
      this.serialNumber = parseInt(document.getElementById("sumPoints").textContent);
      this.is3setCourtChanged = document.getElementById("is3setCourtChanged").textContent === "true";
  
      // 設定値
      this.LANGUAGE = parseInt(document.getElementById("LANGUAGE").textContent) || 0;
      this.MAX_SCORE = [
        parseInt(document.getElementById("MAX_SCORE_1SET").textContent || 25),
        parseInt(document.getElementById("MAX_SCORE_2SET").textContent || 25),
        parseInt(document.getElementById("MAX_SCORE_3SET").textContent || 25)
      ];
      this.DEUCE_START_SCORE = [
        parseInt(document.getElementById("DEUCE_START_SCORE_1SET").textContent) || 20,
        parseInt(document.getElementById("DEUCE_START_SCORE_2SET").textContent) || 20,
        parseInt(document.getElementById("DEUCE_START_SCORE_3SET").textContent) || 20
      ];
      this.DEUCE_DIFFERENCE = [
        parseInt(document.getElementById("DEUCE_DIFFERENCE_1SET").textContent) || 2,
        parseInt(document.getElementById("DEUCE_DIFFERENCE_2SET").textContent) || 2,
        parseInt(document.getElementById("DEUCE_DIFFERENCE_3SET").textContent) || 2
      ];
      this.COURT_CHANGE_SCORE = parseInt(document.getElementById("COURT_CHANGE_SCORE").textContent) || 11;
      this.MAX_SET = parseInt(document.getElementById("MAX_SET").textContent) || 3;
      this.GAME_RULE = document.getElementById("GAME_RULE").textContent || "firstToGet";
  
      // スコア履歴
      this.scoreStockList = JSON.parse(sessionStorage.getItem('scoreStockList')) || [];

      // 数字をコールテキストに変換する
        this.convertNumberToCallText = {
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
        };
    }
  }