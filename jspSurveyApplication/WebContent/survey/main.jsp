<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="survey.*"%>
<%@ page import="java.util.*"%>
<%
	String loginID = (String) session.getAttribute("loginID");
BrandDao dao = new BrandDao();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>투표하기</title>
<script src="js/script.js"></script>
</head>
<body>
	<session>
	<form action="voteProc.jsp" method="post" name="voteForm">
		<%
			if (loginID != null) {
			ArrayList<BrandVo> result = dao.selectAll();
			for (BrandVo vo : result) {
		%>
		<input type="radio" name="brand" id="<%=vo.getBrandNum()%>" value="<%=vo.getBrandNum()%>"> <label
			for="<%=vo.getBrandNum()%>"><%=vo.getBrand()%></label><br />
		<%
			}
		%>
		<input type="radio" name="brand" id="0"> <label
			for="0">기타</label><input type="text" name="etc" onclick="clicked()"><br />
			<input type="submit" value="투표하기">
			<button type="button" onclick="location.href='index.jsp'">홈으로 가기</button>
			<!-- 이 버튼 누르면 로그아웃 되는것도 추가하기(confirm으로 로그아웃 된다고 말하기) -->
		<%
			} else {
		%>
		<script>
			alert("정상적인 접근이 아닙니다. 로그인 해 주세요.");
			location.href = "login.jsp";
		</script>
		<%
			}
		%>
	</form>
	</session>
</body>
</html>