function updateWinner(winner) {
    const reguA = document.getElementById("reguA");
    const reguB = document.getElementById("reguB");
    reguA.innerHTML = winner ? "〇" : "";
    reguB.innerHTML = winner ? "" : "〇";
    reguA.classList.toggle("win", winner);
    reguB.classList.toggle("win", !winner);
}
  
function selectWinner(regu) {
    const isAreguGameWin = regu === "A";
    document.getElementById("isAreguGameWin").value = isAreguGameWin;
    updateWinner(isAreguGameWin);
}