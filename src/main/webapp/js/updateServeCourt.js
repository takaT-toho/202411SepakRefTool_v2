const serveButton = document.getElementById('serveButton');
const courtButton = document.getElementById('courtButton');
const serveInput = document.getElementById('serve');
const courtInput = document.getElementById('court');
const labelLeft = document.getElementById('labelLeft');
const labelRight = document.getElementById('labelRight');
const serveLeft = document.getElementById('serveLeft');
const serveRight = document.getElementById('serveRight');

serveButton.addEventListener('click', toggleServe);
courtButton.addEventListener('click', toggleCourt);

function toggleServe() {
  serveInput.value = 1 - parseInt(serveInput.value, 10);
  updateDisplay();
}

function toggleCourt() {
  courtInput.value = 1 - parseInt(courtInput.value, 10);
  [labelLeft.textContent, labelRight.textContent] = [labelRight.textContent, labelLeft.textContent];
  updateDisplay();
}

function updateDisplay() {
  const isEvenSum = (parseInt(serveInput.value, 10) + parseInt(courtInput.value, 10)) % 2 === 0;
  
  if (isEvenSum) {
    serveLeft.textContent = "先手";
    serveRight.textContent = "後手";
    serveLeft.classList.add('active');
    serveRight.classList.remove('active');
  } else {
    serveLeft.textContent = "後手";
    serveRight.textContent = "先手";
    serveLeft.classList.remove('active');
    serveRight.classList.add('active');
  }
}
