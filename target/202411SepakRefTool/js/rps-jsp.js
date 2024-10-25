function updateWinner(winner) {
    const teamA = document.getElementById("teamA");
    const teamB = document.getElementById("teamB");
    teamA.innerHTML = winner ? "〇" : "";
    teamB.innerHTML = winner ? "" : "〇";
    teamA.classList.toggle("win", winner);
    teamB.classList.toggle("win", !winner);
}
  
function selectWinner(team) {
    const winner = team === "A" ? 1 : 0;
    document.getElementById("isAreguWin").value = winner;
    updateWinner(winner);
}