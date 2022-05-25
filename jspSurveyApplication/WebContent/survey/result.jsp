<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="survey.*"%>
<%@ page import="java.util.*"%>
<%
	VoteDao dao = new VoteDao();
ArrayList<VoteVo> all = dao.selectAll();
ArrayList<Integer> ages = dao.getAges();
HashMap<Integer, ArrayList<VoteVo>> map = null;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>설문조사 현황</title>
<script src="js/script.js"></script>
</head>
<body>
<h1>투표 결과 확인하기</h1>
	<button onclick="clickAll()">전체 결과 보기</button>
	<button onclick="clickAge()">나이대별 결과 보기</button>
	<br />
	<div id="all">
		<%
			for (VoteVo tmp : all) {
		%>
		<%=tmp%><br />
		<%
			}
		%>
	</div>
	<div id="age" style="display: none;">
		<%
			boolean flag = false;
		if (ages != null) {
			flag = true;
			for (int i : ages) {
				map = dao.getVotes(i);
				Iterator<Integer> keys = map.keySet().iterator();
				int person = keys.next();
		%>
		<%=i%>대
		<%=person%>명 <br />
		<%
			for (int j = 0; j < map.get(person).size(); j++) {
		%>
		<%=map.get(person).get(j)%><br />
		<%
			}
		}
		}
		%>
	</div>

</body>
</html>