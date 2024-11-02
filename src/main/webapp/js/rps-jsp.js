// function updateWinner(winner) {
//     const reguA = document.getElementById("reguA");
//     const reguB = document.getElementById("reguB");
//     reguA.innerHTML = winner ? "〇" : "";
//     reguB.innerHTML = winner ? "" : "〇";
//     reguA.classList.toggle("win", winner);
//     reguB.classList.toggle("win", !winner);
// }
  
// function selectWinner(regu) {
//     const isAreguGameWin = regu === "A";
//     document.getElementById("isAreguGameWin").value = isAreguGameWin;
//     updateWinner(isAreguGameWin);
// }

function updateWinner(winner) {
    const reguA = document.getElementById("reguA");
    const reguB = document.getElementById("reguB");
    const resultA = document.getElementById("resultA");
    const resultB = document.getElementById("resultB");

    reguA.classList.toggle("win", winner);
    reguB.classList.toggle("win", !winner);
    reguA.classList.toggle("lose", !winner);
    reguB.classList.toggle("lose", winner);

    resultA.textContent = winner ? "WIN" : "LOSE";
    resultB.textContent = winner ? "LOSE" : "WIN";

    resultA.setAttribute("aria-hidden", !winner);
    resultB.setAttribute("aria-hidden", winner);
}

function selectWinner(regu) {
    const isAreguGameWin = regu === "A";
    document.getElementById("isAreguGameWin").value = isAreguGameWin;
    updateWinner(isAreguGameWin);
}


function handleKeyDown(event, regu) {
    if (event.key === "Enter" || event.key === " ") {
      selectWinner(regu);
    }
}