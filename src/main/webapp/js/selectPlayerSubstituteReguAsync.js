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

async function insertGameEventHistoryPlayerSubstitution() {
	const names = extractNames();	
	const data = {
	    gameId: document.getElementById("gameId").textContent,
	    setNum: document.getElementById("setNow").textContent,
	    type: 'PLAYERSUBSTITUTION',
	    firstDetail: names.inNames,
	    secondDetail: names.outNames,
	    isSequential: false,
	    isAreguGot: reguRequestedData['A'].isClicked ? true : false,
        buttonId: "p0152"
	};
	console.log("data: ", data);
	try {
		const result = await insertGameEventHistoryDB(data);
		console.log('Success:', result);
        location.reload();
	} catch (error) {
	    console.error('Error:');
	}
}

function extractNames() {
  let inNames;
  let outNames;

  playersData.rows.forEach((row, index) => {
    const statusElement = playersData.status[index];
    const inCourtElement = playersData.inCourt[index];
    const substitutionElement = playersData.substitution[index];

    if (statusElement.classList.contains("in")) {
      inNames = substitutionElement.textContent.trim();
    } else if (statusElement.classList.contains("out")) {
      outNames = inCourtElement.textContent.trim();
    }
  });

  return { inNames, outNames };
}
