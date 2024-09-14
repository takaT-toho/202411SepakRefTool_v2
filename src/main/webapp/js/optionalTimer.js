const timerDisplay = document.querySelector('.timer');
const startButton = document.querySelector('.start');
const pauseButton = document.querySelector('.pause');
const stopButton = document.querySelector('.stop');
const remainedNormalDiv = document.getElementById("remainedNumberOfNormalTimeout");
const remainedInjuryDiv = document.getElementById("remainedNumberOfInjuryTimeout");

let timer;
let seconds = 60;
let isNormalTimeout = true;
let remainedNumOfNormalTimeoutByA = parseInt(document.getElementById("normalTimeoutRemainA").textContent);
let remainedNumOfInjuryTimeoutByA = parseInt(document.getElementById("technicalTimeoutRemainA").textContent);
let remainedNumOfNormalTimeoutByB = parseInt(document.getElementById("normalTimeoutRemainB").textContent);
let remainedNumOfInjuryTimeoutByB = parseInt(document.getElementById("technicalTimeoutRemainB").textContent);
let currentRegu = 'A';

function onReguClick(element, regu) {
  setActiveClass(element, "reguRequested");
  currentRegu = regu;
  updateRemainedNumberDisplay(currentRegu);
  checkAndUpdateRemainedNumber();
}

function setActiveClass(element, className) {
  const elems = Array.from(document.getElementsByClassName(className));
  elems.forEach(elem => elem.classList.toggle('active', elem === element));
}

function startTimer() {
  if (checkRemainedNumber()) {
    timer = setInterval(updateTimer, 1000);
    toggleButtons(true);
    updateRemainedNumber();
    // TODO: 2回目以降に再生ボタンが押された時は記録されないようにする
    insertGameEventsHistoryTimeout();
  } else {
    showErrorMessage();
  }
}

function pauseTimer() {
  clearInterval(timer);
  toggleButtons(false);
}

function stopTimer() {
  clearInterval(timer);
  seconds = isNormalTimeout ? 60 : 300;
  updateTimerDisplay();
  toggleButtons(false);
}

function updateTimer() {
  seconds--;
  if (seconds < 0) {
    clearInterval(timer);
    alert('タイマーが終了しました！');
    stopTimer();
  } else {
    updateTimerDisplay();
  }
}

function updateTimerDisplay() {
  const minutes = Math.floor(seconds / 60);
  const remainingSeconds = seconds % 60;
  timerDisplay.textContent = minutes.toString().padStart(2, '0') + ":" + remainingSeconds.toString().padStart(2, '0');
}

function toggleButtons(isRunning) {
  startButton.classList.toggle('hidden', isRunning);
  pauseButton.classList.toggle('hidden', !isRunning);
  stopButton.classList.toggle('hidden', !isRunning);
}

function onTimeoutTypeClick(element, isNormal) {
  setActiveClass(element, "timerType");
  isNormalTimeout = isNormal;
  seconds = isNormalTimeout ? 60 : 300;
  updateTimerDisplay();
  checkAndUpdateRemainedNumber();
}

function updateRemainedNumberDisplay(regu) {
  const remainedNormal = regu === 'A' ? remainedNumOfNormalTimeoutByA : remainedNumOfNormalTimeoutByB;
  const remainedInjury = regu === 'A' ? remainedNumOfInjuryTimeoutByA : remainedNumOfInjuryTimeoutByB;
  remainedNormalDiv.textContent = "残り " + remainedNormal + "回";
  remainedInjuryDiv.textContent = "残り " + remainedInjury + "回";
}

function updateRemainedNumber() {
  if (currentRegu === 'A') {
    isNormalTimeout ? remainedNumOfNormalTimeoutByA-- : remainedNumOfInjuryTimeoutByA--;
  } else {
    isNormalTimeout ? remainedNumOfNormalTimeoutByB-- : remainedNumOfInjuryTimeoutByB--;
  }
  updateRemainedNumberDisplay(currentRegu);
}

function checkRemainedNumber() {
  if (currentRegu === 'A') {
    return isNormalTimeout ? remainedNumOfNormalTimeoutByA > 0 : remainedNumOfInjuryTimeoutByA > 0;
  } else {
    return isNormalTimeout ? remainedNumOfNormalTimeoutByB > 0 : remainedNumOfInjuryTimeoutByB > 0;
  }
}

function hideErrorMessage() {
  const errorMsg = document.getElementById("errorMsg");
  errorMsg.textContent = "";
}

function showErrorMessage() {
  const errorMsg = document.getElementById("errorMsg");
  errorMsg.textContent = "残り回数がありません。";
}

function checkAndUpdateRemainedNumber() {
  checkRemainedNumber() ? hideErrorMessage() : showErrorMessage();
}

startButton.addEventListener('click', startTimer);
pauseButton.addEventListener('click', pauseTimer);
stopButton.addEventListener('click', stopTimer);
