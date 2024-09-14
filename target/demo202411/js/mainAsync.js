async function insertGameEventHistoryDB(bodyInfo = null) {
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

async function insertGameEventHistoryPoints(score, isAreguGot) {
	previousRegu = scoreStockList[scoreStockList.length - 2];
	let isSequential = false;
	if ((previousRegu == 'A') && isAreguGot){
		isSequential = true;
	} else if ((previousRegu == 'B') && !isAreguGot) {
		isSequential = true;
	}
	const data = {
	    gameId: document.getElementById("gameId").textContent,
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
		const result = await insertGameEventHistoryDB(data);
		console.log('Success:', result);
	} catch (error) {
	    console.error('Error:');
        // TODO: システムエラー画面へ遷移する

	}
}

async function deleteGameEventHistoryDB() {
    const url = 'async';
    const options = {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            gameId: document.getElementById("gameId").textContent,
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

async function deleteGameEventHistoryPoints() {
	try {
		const result = await deleteGameEventHistoryDB();
		console.log('Success:', result);
	} catch (error) {
	    console.error('Error:', error);
	}
}

async function updateIs3setCourtChanged(is3setCourtChanged) {
    const url = 'async';
    const options = {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            gameId: document.getElementById("gameId").textContent,
            buttonId: "p0200",
            is3setCourtChanged: is3setCourtChanged
        })
    };

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

async function updateIsGameFinished(isGameFinished_) {
    const url = 'async';
    const options = {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            gameId: document.getElementById("gameId").textContent,
            buttonId: "p0210",
            isGameFinished: isGameFinished_
        })
    };

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