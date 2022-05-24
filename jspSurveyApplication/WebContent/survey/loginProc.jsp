<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="survey.*"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	InfoDao dao = new InfoDao();
String id = request.getParameter("id");
String pwd = request.getParameter("pwd");
int check = dao.login(id, pwd);
if (check == 1) {
	session.setAttribute("loginID", id);
	response.sendRedirect("main.jsp");
} else if (check == 0) {
%>
<script>
	alert("비밀번호가 틀림");
	history.go(-1);
</script>
<%
	} else {
%>
<script>
	alert("존재하지 않는 회원입니다. 회원가입을 진행하세요.");
	history.go(-1);
</script>
<%
	}
%>
