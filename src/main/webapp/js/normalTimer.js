const timerDisplay = document.querySelector('.timer');
const startButton = document.querySelector('.start');
const pauseButton = document.querySelector('.pause');
const stopButton = document.querySelector('.stop');

let timer;
let seconds = 120;

function startTimer() {
  timer = setInterval(updateTimer, 1000);
  startButton.classList.add('hidden');
  pauseButton.classList.remove('hidden');
  stopButton.classList.remove('hidden');
}

function pauseTimer() {
  clearInterval(timer);
  pauseButton.classList.add('hidden');
  startButton.classList.remove('hidden');
}

function stopTimer() {
  clearInterval(timer);
  seconds = 120;
  updateTimerDisplay();
  pauseButton.classList.add('hidden');
  stopButton.classList.add('hidden');
  startButton.classList.remove('hidden');
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

startButton.addEventListener('click', startTimer);
pauseButton.addEventListener('click', pauseTimer);
stopButton.addEventListener('click', stopTimer);