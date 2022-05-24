<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script src="js/script.js"></script>
</head>
<body>
	<section>
		<form method="post" action="regProc.jsp" name="regForm">
			<table class="regtable">
				<tr>
					<th colspan="2">회원 가입 정보 입력</th>
				</tr>
				<tr>
					<td>아이디 :</td>
					<td><input type="text" name="id" required /> <br />
					<input type="button" value="중복확인"
						onClick="idCheck(this.form.id.value)" /></td>
				</tr>
				<tr>
					<td>비밀번호 :</td>
					<td><input type="password" name="pwd" required /></td>
				</tr>
				<tr>
					<td>비밀번호 확인 :</td>
					<td><input type="password" name="repwd" required /></td>
				</tr>
				<tr>
					<td>나이 :</td>
					<td><input type="password" name="age" required /></td>
				</tr>
				<tr>
					<td colspan="2" class="regsubmit"><input type="button"
						value="회원가입" onclick="inputCheck()" />&nbsp;&nbsp; <input
						type="reset" value="다시 입력" /></td>
				</tr>
			</table>
			<input type="hidden" name="isChecked" value="false">
		</form>
	</section>
</body>
</html>