// DOM要素の取得
//const teamAScore = document.querySelector('.team-a .score');
//const teamBScore = document.querySelector('.team-b .score');
//const teamASets = document.querySelectorAll('.team-a .set-number');
//const teamBSets = document.querySelectorAll('.team-b .set-number');
const toggleBtn = document.querySelector('.toggle-btn');
const navbarLinks = document.querySelector('.navbar-links');
//
//// スコアの更新関数
//function updateScore(scoreA, scoreB) {
//  teamAScore.textContent = scoreA;
//  teamBScore.textContent = scoreB;
//}
//
//// セットスコアの更新関数
//function updateSets(setsA, setsB) {
//  setsA.forEach((score, index) => {
//    teamASets[index].textContent = score;
//  });
//
//  setsB.forEach((score, index) => {
//    teamBSets[index].textContent = score;
//  });
//}

// ナビゲーションの切り替え関数
function toggleNavbar() {
  navbarLinks.classList.toggle('active');
}

// イベントリスナーの登録
toggleBtn.addEventListener('click', toggleNavbar);
//
//// 初期値の設定
//updateScore(15, 10);
//updateSets([21, 17, 15], [15, 21, 10]);
