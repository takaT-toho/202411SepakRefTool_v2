//審判署名の入力チェック

function check() {
	var message = "";
	var sign = document.getElementById("sign").value;
	if (sign.length == 0) {
		message = "<p>名前を入力してください</p>";
	}
	if (message != "") {
		var errorMsgDiv = document.getElementById("errorMsg");
		errorMsgDiv.innerHTML = message;
		return false;
	}
	return true;
}

window.onload = function () {
	var checkForm = document.getElementById("checkForm");
	checkForm.onsubmit = check;
}