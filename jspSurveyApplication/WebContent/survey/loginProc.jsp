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
int infoNum = dao.login(id, pwd);
if (infoNum > 0) {
	session.setAttribute("loginID", id);
	session.setAttribute("loginNum", infoNum);
	response.sendRedirect("voteForm.jsp");
} else {
%>
<script>
	alert("입력된 정보가 올바르지 않습니다.");
	history.go(-1);
</script>

<%
	}
%>
