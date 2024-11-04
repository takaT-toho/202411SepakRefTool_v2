const coin = document.getElementById('coin');
const coinToss = document.getElementById("tossButton");
const teamA = document.getElementById("teamA");
const teamB = document.getElementById("teamB");

let isSpinning = false;
let angle = 0;
let angularVelocity = 10;
let deceleration = 0.9;
let spinCount = 0;
let side = "";
let isJumping = false;
let isTopTouched = false;
let jumpHeight = 200;
let jumpStartY = 0;
let currentY = 0;
let calculatedY = 0.0;

function startSpin(result) {
  if (isSpinning) return;
  isSpinning = true;
  spinCount = 0;
  coin.querySelector('text').textContent = '表';
  side = result ? '表' : '裏';
  isJumping = true;
  window.requestAnimationFrame(update);
}

function update() {
  if (!isSpinning) return;

  angle += angularVelocity;
  coin.style.transform = "rotateY(" + angle + "deg)" + "translateY(-" + calculatedY + "px)";

  spinCount++;

  if (spinCount > 62) {
    angularVelocity *= deceleration;
  }

  if (isJumping) {
    currentY += isTopTouched ? -4 : 4;
    if (!isTopTouched && currentY >= jumpHeight) {
      isTopTouched = true;
    }
    calculatedY = jumpStartY + (jumpHeight * Math.sin(Math.PI * (currentY - jumpStartY) / jumpHeight));
    if (isTopTouched && calculatedY <= jumpStartY + 4) {
      isJumping = false;
      isTopTouched = false;
      calculatedY = jumpStartY;
      currentY = jumpStartY;
    }
  }

  if (Math.abs(angularVelocity) < 9 && side === '裏') {
    coin.querySelector('text').textContent = '裏';
  }
  
  if (Math.abs(angularVelocity) < 0.1) {    
    coin.classList.remove('animate');
    isSpinning = false;
    angle = 0;
    angularVelocity = 10;
    deceleration = 0.9;
    spinCount = 0;
  }

  window.requestAnimationFrame(update);
}

function debounce(func, delay) {
  let timeoutId;
  return function(...args) {
    clearTimeout(timeoutId);
    timeoutId = setTimeout(() => func.apply(this, args), delay);
  };
}

const debouncedTossCoin = debounce(tossCoin, 100);

coinToss.addEventListener('click', debouncedTossCoin);

function tossCoin() {
  const result = Math.random() < 0.5 ? 0 : 1;
  startSpin(result);
  setTimeout(() => {
    document.getElementById("isAreguTossWin").value = result;
    updateWinner(result);
  }, 1400);
}

function updateWinner(isAreguWin) {
  const reguA = document.getElementById("reguA");
  const reguB = document.getElementById("reguB");

  reguA.classList.toggle("win", isAreguWin);
  reguB.classList.toggle("win", !isAreguWin);

  coinToss.style.animation = "none";
  coinToss.offsetHeight;
}

function selectWinner(regu) {
  const isAreguWin = regu === "A";
  document.getElementById("isAreguTossWin").value = isAreguWin;
  updateWinner(isAreguWin);
}
