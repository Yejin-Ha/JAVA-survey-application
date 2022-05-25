<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="survey.*"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	BrandDao bdao = new BrandDao();
VoteDao vdao = new VoteDao();
String voteNum = request.getParameter("brand");
String voteVal = "";

if (voteNum.equals("0")) {
	voteVal = request.getParameter("etc");
	// 기타를 선택 시 insert brand를 하면된다.	
	voteNum = bdao.insertBrand(voteVal);
} else {
	voteVal = request.getParameter("brand"); 
}
// 기타를 선택하지 않을경우 voteBrand를 하면 된다.
vdao.vote(voteNum, session.getAttribute("loginNum"));
// logout 진행
session.setAttribute("loginID", null);
session.setAttribute("loginNum", null);
%>
<script>
	// 홈페이지로 이동
	alert("투표하였습니다.");
	location.href = "index.jsp";
</script>
