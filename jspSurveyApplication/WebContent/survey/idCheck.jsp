<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="survey.*"%>
<%
	InfoDao dao = new InfoDao();
%>
<%
	String id = request.getParameter("id");
boolean check = dao.idCheck(id);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ID 중복 확인</title>
<script scr="script.js"></script>
<style>
a {
	text-decoration: none;
}
</style>
</head>
<body>
<b><%=id%></b>
	<%
		if (check) {
		out.println("는 이미 존재하는 ID입니다.<br/>");
	} else {
		out.println("는 사용가능합니다.<br/>");
	}
	%>
	<a href="#" onClick="javascript:self.close()">닫기</a>
</body>
</html>