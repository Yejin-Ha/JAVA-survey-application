<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	<h1 style="text-align: center;">회원만 가능한 서비스입니다.</h1>

	<h3 style="text-align: center;">로그인</h3>
	<section style="width: 100%;">
		<article class="login"
			style="display: flex; justify-content: center; width: 100%;">
			<form method="post" action="loginProc.jsp">
				<table class="loginTable" style="width: 100%;">
					<tr>
						<td><input type="text" class="id" name="id"
							placeholder="아이디 입력" style="width: 100%;"></td>
					</tr>
					<tr>
						<td><input type="password" class="pass" name="pwd"
							placeholder="비밀번호 입력" style="width: 100%;"></td>
					</tr>
					<tr>
						<td colspan="2" class="loginsubmit" style="text-align: center;"><input
							type="submit" value="로그인" /> <input type="button" value="회원가입"
							onclick="javascript:window.location='regForm.jsp'" /></td>
					</tr>
				</table>
			</form>
		</article>
	</section>
</body>
</html>