async function insertGameEventsHistoryDB(bodyInfo = null) {
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
        console.error('Fetch error:', error);
        throw error;
    }
}

async function insertGameEventsHistoryTimeout() {	
	const data = {
	    gameId: document.getElementById("gameId").textContent,
	    setNum: document.getElementById("setNow").textContent,
	    type: 'TIMEOUT',
	    firstDetail: isNormalTimeout ? '1' : '5',
	    secondDetail: null,
	    isSequential: false,
	    isAreguGot: currentRegu == 'A' ? true : false,
        buttonId: "p0151"
	};
	try {
		const result = await insertGameEventsHistoryDB(data);
		console.log('Success:', result);
	} catch (error) {
	    console.error('Error:', error);
	}
}