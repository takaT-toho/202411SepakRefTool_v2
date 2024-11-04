// 送信用
const serveInput = document.getElementById('serve');
const courtInput = document.getElementById('court');
// 表示用
const courtButton = document.getElementById('courtButton');
const labelLeft = document.getElementById('labelLeft');
const labelRight = document.getElementById('labelRight');
const serveLeft = document.getElementById('serveLeft');
const serveRight = document.getElementById('serveRight');

labelLeft.addEventListener('click', toggleServe(1));
labelRight.addEventListener('click', toggleServe(0));
courtButton.addEventListener('click', toggleCourt);

function toggleServe(isLeft) {
  return function() {
    let isAregu = parseInt(courtInput.value) === isLeft;
    if (isAregu) {
      serveInput.value = 1;
    } else {
      serveInput.value = 0;
    }
    updateDisplay();
  }
}

function toggleCourt() {
  courtInput.value = 1 - parseInt(courtInput.value, 10);
  [labelLeft.textContent, labelRight.textContent] = [labelRight.textContent, labelLeft.textContent];
  updateDisplay();
}

function updateDisplay() {
  const isEvenSum = (parseInt(serveInput.value, 10) + parseInt(courtInput.value, 10)) % 2 === 0;
  
  if (isEvenSum) {
    serveLeft.textContent = "先サーブ";
    serveRight.textContent = "後サーブ";
    serveLeft.classList.add('active');
    serveRight.classList.remove('active');
    labelLeft.classList.add('active');
    labelRight.classList.remove('active');
  } else {
    serveLeft.textContent = "後サーブ";
    serveRight.textContent = "先サーブ";
    serveLeft.classList.remove('active');
    serveRight.classList.add('active');
    labelLeft.classList.remove('active');
    labelRight.classList.add('active');
  }
}
