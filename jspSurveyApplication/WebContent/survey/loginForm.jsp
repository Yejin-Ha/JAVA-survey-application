<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
회원만 가능한 서비스입니다.

<section>
    <article class="login">
        <form method="post" action="loginProc.jsp">
            <table class="loginTable">
                <tr>
                    <th colspan="2">회원 로그인</th> 
                </tr>
                <tr>
                    <td><input type="text" class="id" name="id" placeholder="아이디 입력"/></td>
                </tr>
                <tr>
                    <td><input type="password" class="pass" name="pwd" placeholder="비밀번호 입력"></td>
                </tr>
                <tr>
                    <td colspan="2" class="loginsubmit">
                        <input type="submit" value="로그인"/>
                        <input type="button" value="회원가입" 
                        onclick="javascript:window.location='regForm.jsp'"/>
                    </td>
                </tr>
            </table>
        </form>
    </article>
</section>
</body>
</html>