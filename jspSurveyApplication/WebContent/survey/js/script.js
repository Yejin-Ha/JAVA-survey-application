function idCheck(id){
	if (id == ""){
		alert("ID를 입력하세요");
		document.regForm.id.focus();
		document.regForm.isChecked.value = "false";
	} else{
		var popWidth = 300;
        var popHeight = 200;
        var winHeight = document.body.clientHeight;    // 현재창의 높이
        var winWidth = document.body.clientWidth;    // 현재창의 너비
        var winX = window.screenLeft;    // 현재창의 x좌표
        var winY = window.screenTop;    // 현재창의 y좌표
        var popX = winX + (winWidth - popWidth)/2;
        var popY = winY + (winHeight - popHeight)/2;
        url="idCheck.jsp?id="+id;
        window.open(url, "post", 
                "left="+popX+",top="+popY+",width="+popWidth+", height="+popHeight);
		document.regForm.isChecked.value = "true";
    }
}

function inputCheck(){
	if(document.regForm.id.value==""){
		alert("아이디를 입력하세요");
		document.regForm.id.focus();
		// return을 만나면서 함수가 종료된다.
		return;
	}
	if (document.regForm.pwd.value==""){
		alert("비밀번호를 입력하세요.");
		document.regForm.pwd.focus();
		return;
	}
	if (document.regForm.repwd.value != document.regForm.pwd.value) {
		alert("비밀번호가 다릅니다.");
		document.regForm.repwd.focus();
		return;
	}
	if (document.regForm.age.value==""){
		alert("나이를 입력하세요.");
		document.regForm.age.focus();
		return;
	} else if(isNaN(document.regForm.age.value)){
		alert("나이는 숫자를 입력하세요.");
		document.regForm.age.focus();
		return;
	}
	if (document.regForm.isChecked.value == "false"){
		alert("아이디 중복 확인을 해주세요.");
		return;
	}
	document.regForm.submit();
}

function clicked(){
	var arr = document.getElementsByName("brand");
	for (var i = 0; i < arr.length; i++){
		if (arr[i].checked){
			arr[i].checked = false;
		}
	}
	document.getElementById("0").checked = true;
}

function clickAll(){
	var all = document.getElementById("all");
	var age = document.getElementById("age");
	if(all.style.display =='none'){
		all.style.display ='block';
	}
	if(age.style.display !='none'){
		age.style.display ='none';
	}
}

function clickAge(){
	var all = document.getElementById("all");
	var age = document.getElementById("age");
	if(all.style.display !='none'){
		all.style.display ='none';
	}
	if(age.style.display =='none'){
		age.style.display ='block';
	}
}

