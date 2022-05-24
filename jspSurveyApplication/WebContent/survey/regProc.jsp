<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%@ page import="survey.*"%>
<%
	InfoDao dao = new InfoDao();
%>
<jsp:useBean id="dto" class="survey.InfoDto" />
<jsp:setProperty name="dto" property="*" />
<%
	boolean flag = dao.insertInfo(dto);
System.out.println(flag);
if (flag) {
	response.sendRedirect("survey.jsp");
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Refresh" content="3;url=index.jsp">
<title>회원 가입 확인</title>
</head>
<body>
	<p>
		회원가입에 실패했습니다.<br>
	</p>
	<p>3초 후에 홈페이지로 이동합니다.</p>
</body>
</html>