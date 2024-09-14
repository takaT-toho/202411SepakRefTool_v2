/* reguRequestedContainer */
const reguRequestedData = {
    A: {
      isClicked: true,
      remainedNumOfPlayerSubstitution: parseInt(document.getElementById("playerSubstitutionRemainA").textContent),
      remainedNumberOfSubstitutionElement: document.getElementById("remainedNumberOfSubstitutionByA"),      
      player1: document.getElementById("player1A"),
      player2: document.getElementById("player2A"),
      player3: document.getElementById("player3A"),
      player4: document.getElementById("player4A"),
      player5: document.getElementById("player5A")
    },
    B: {
      isClicked: false,
      remainedNumOfPlayerSubstitution: parseInt(document.getElementById("playerSubstitutionRemainB").textContent),
      remainedNumberOfSubstitutionElement: document.getElementById("remainedNumberOfSubstitutionByB"),
      player1: document.getElementById("player1B"),
      player2: document.getElementById("player2B"),
      player3: document.getElementById("player3B"),
      player4: document.getElementById("player4B"),
      player5: document.getElementById("player5B")
    }
  };
  
  function onReguClick(element, team) {
    setActiveClass(element, "reguRequested");
    Object.keys(reguRequestedData).forEach(key => {
      reguRequestedData[key].isClicked = (key === team);
    });
    checkAndUpdateRemainedNumber();
    updatePlayerName();
    clearPlayersDisplay();
  }
  
  function setActiveClass(element, className) {
    const elems = Array.from(document.getElementsByClassName(className));
    elems.forEach(elem => elem.classList.toggle('active', elem === element));
  }

  function updatePlayerName() {
    const currentTeam = Object.keys(reguRequestedData).find(key => reguRequestedData[key].isClicked);
    playersData.inCourt[0].textContent = reguRequestedData[currentTeam].player1.textContent;
    playersData.inCourt[1].textContent = reguRequestedData[currentTeam].player2.textContent;
    playersData.inCourt[2].textContent = reguRequestedData[currentTeam].player3.textContent;
    playersData.substitution[3].textContent = reguRequestedData[currentTeam].player4.textContent;
    playersData.substitution[4].textContent = reguRequestedData[currentTeam].player5.textContent;
  }
  
  /* playersContainer */
  const playersData = {
    isOutOccupied: false,
    isInOccupied: false,
    isActive: [false, false, false, false, false],
    isInCourtOccupied: [true, true, true, false, false],
    selectedRow: null,
    rows: [
      document.getElementById("tr1st"),
      document.getElementById("tr2nd"),
      document.getElementById("tr3rd"),
      document.getElementById("tr4th"),
      document.getElementById("tr5th")
    ],
    inCourt: [
      document.getElementById("inCourt1st"),
      document.getElementById("inCourt2nd"),
      document.getElementById("inCourt3rd"),
      document.getElementById("inCourt4th"),
      document.getElementById("inCourt5th")
    ],
    status: [
      document.getElementById("status1st"),
      document.getElementById("status2nd"),
      document.getElementById("status3rd"),
      document.getElementById("status4th"),
      document.getElementById("status5th")
    ],
    substitution: [
      document.getElementById("substitution1st"),
      document.getElementById("substitution2nd"),
      document.getElementById("substitution3ed"),
      document.getElementById("substitution4th"),
      document.getElementById("substitution5th")
    ]
  };
  
  playersData.rows.forEach((row, index) => {
    row.addEventListener('click', () => {
      playersData.selectedRow = index;
      updatePlayersDisplay();
    });
  });
  
  function updatePlayersDisplay() {
    const sr = playersData.selectedRow;
    if (playersData.isInCourtOccupied[sr]) {
      if (playersData.isOutOccupied) {
        if (playersData.isActive[sr]) {
          playersData.inCourt[sr].classList.remove("active");
          playersData.status[sr].classList.remove("out");
          playersData.status[sr].textContent = "・";
          playersData.status[otherThanMeOut(sr)[0]].textContent = "・";
          playersData.status[otherThanMeOut(sr)[1]].textContent = "・";
          playersData.isActive[sr] = false;
          playersData.isOutOccupied = false;
        }
      } else {
        playersData.inCourt[sr].classList.add("active");
        playersData.status[sr].classList.add("out");
        playersData.status[sr].textContent = "out >";
        playersData.status[otherThanMeOut(sr)[0]].textContent = "";
        playersData.status[otherThanMeOut(sr)[1]].textContent = "";
        playersData.isActive[sr] = true;
        playersData.isOutOccupied = true;
      }
    } else {
      if (playersData.isInOccupied) {
        if (playersData.isActive[sr]) {
          playersData.status[sr].classList.remove("in");
          playersData.status[sr].textContent = "・";
          playersData.status[otherThanMeIn(sr)[0]].textContent = "・";
          playersData.substitution[sr].classList.remove("active");
          playersData.isActive[sr] = false;
          playersData.isInOccupied = false;
        }
      } else {
        playersData.status[sr].classList.add("in");
        playersData.status[sr].textContent = "< in";
        playersData.status[otherThanMeIn(sr)[0]].textContent = "";
        playersData.substitution[sr].classList.add("active");
        playersData.isActive[sr] = true;
        playersData.isInOccupied = true;
      }
    }
  }
  
  function otherThanMeOut(input) {
    return [0, 1, 2].filter(x => x !== input);
  }
  
  function otherThanMeIn(input) {
    return [3, 4].filter(x => x !== input);
  }
  
  /* executeButton */
  function onExecuteButtonClick() {
    if (checkRemainedNumber()) {
      insertGameEventHistoryPlayerSubstitution();
      updateReguRequestedDisplay();
      clearPlayersDisplay();
      checkAndUpdateRemainedNumber();
    }
  }
  
  function updateReguRequestedDisplay() {
    updateRemainedNumber();
    updateRemainedNumberDisplay();
  }
  
  function checkRemainedNumber() {
    const currentTeam = Object.keys(reguRequestedData).find(key => reguRequestedData[key].isClicked);
    return reguRequestedData[currentTeam].remainedNumOfPlayerSubstitution > 0;
  }
  
  function updateRemainedNumber() {
    const currentTeam = Object.keys(reguRequestedData).find(key => reguRequestedData[key].isClicked);
    reguRequestedData[currentTeam].remainedNumOfPlayerSubstitution--;
  }
  
  function updateRemainedNumberDisplay() {
    Object.keys(reguRequestedData).forEach(key => {
      reguRequestedData[key].remainedNumberOfSubstitutionElement.textContent = "残り " + reguRequestedData[key].remainedNumOfPlayerSubstitution + "回";
    });
  }
  
  function clearPlayersDisplay() {
      playersData.isActive.fill(false);
      playersData.isOutOccupied = false;
      playersData.isInOccupied = false;
      playersData.inCourt.forEach(elem => {
          if (elem) {
              elem.classList.remove("active");
          }
      });
      playersData.status.forEach(elem => {
          if (elem) {
              elem.classList.remove("out", "in");
              elem.textContent = "・";
          }
      });
      playersData.substitution.forEach(elem => {
          if (elem) {
              elem.classList.remove("active");
          }
      });
  }
  
  function hideErrorMessage() {
    document.getElementById("errorMsg").textContent = "";
  }
  
  function showErrorMessage() {
    document.getElementById("errorMsg").textContent = "残り回数がありません。";
  }
  
  function checkAndUpdateRemainedNumber() {
    if (checkRemainedNumber()) {
      hideErrorMessage();
    } else {
      showErrorMessage();
    }
  }
  