// GameEventHistory.js
export default class GameEventHistory {
    constructor(gameData) {
      this.gameData = gameData;
    }
  
    async insertGameEventHistoryPoints(score, isAreguGot) {
      const { gameId, setNow, scoreStockList, serialNumber } = this.gameData;
      const previousRegu = scoreStockList[scoreStockList.length - 2];
      let isSequential = false;
      if ((previousRegu === 'A' && isAreguGot) || (previousRegu === 'B' && !isAreguGot)) {
        isSequential = true;
      }
      const data = {
        gameId: gameId,
        setNum: setNow,
        type: 'ADDPOINTS',
        firstDetail: score,
        secondDetail: null,
        isSequential: isSequential,
        isAreguGot: isAreguGot,
        buttonId: "p0150",
        serialNumber: serialNumber
      };
      try {
        const result = await this.insertGameEventHistoryDB(data);
        console.log('Success:', result);
      } catch (error) {
        console.error('Error:', error);
        // TODO: システムエラー画面へ遷移する
      }
    }
  
    async deleteGameEventHistoryPoints() {
      try {
        const result = await this.deleteGameEventHistoryDB();
        console.log('Success:', result);
      } catch (error) {
        console.error('Error:', error);
      }
    }
  
    async insertGameEventHistoryDB(bodyInfo = null) {
      const url = 'async';
      const options = {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        }
      };
      if (bodyInfo) {
        options.body = JSON.stringify(bodyInfo);
      }
  
      try {
        const response = await fetch(url, options);
        if (!response.ok) {
          throw new Error("HTTP error! status: " + response.status);
        }
        return await response.json();
      } catch (error) {
        console.error('Fetch error:');
        throw error;
      }
    }
  
    async deleteGameEventHistoryDB() {
      const url = 'async';
      const options = {
        method: 'DELETE',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          gameId: this.gameData.gameId,
          buttonId: "p0160",
        })
      };
  
      try {
        const response = await fetch(url, options);
        if (!response.ok) {
          throw new Error("HTTP error! status: " + response.status);
        }
        return await response.json();
      } catch (error) {
        console.error('Fetch error:', error);
        throw error;
      }
    }
  }