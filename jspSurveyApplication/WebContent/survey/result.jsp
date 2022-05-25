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
<style>
button {
	margin: 5px;
}

table {
	min-width: 400px;
}

.tableBox {
	display: flex;
	justify-content: center;
}
</style>
</head>
<body style="text-align: center;">
	<h1>투표 결과 확인하기</h1>
	<div style="margin: 5px 0;">
		<button onclick="clickAll()">전체 결과 보기</button>
		<button onclick="clickAge()">나이대별 결과 보기</button>
	</div>
	<br />
	<div id="all" class="tableBox">
		<table border="2">
			<th>브랜드</th>
			<th>투표수</th>
			<%
				for (VoteVo tmp : all) {
			%>
			<tr>
				<td><%=tmp.getBrand()%></td>
				<td><%=tmp.getVote()%></td>
			</tr>
			<%
				}
			%>
		</table>
	</div>
	<div id="age"
		style="display: none; flex-direction: column; align-items: center;"
		class="tableBox">
		<%
			if (ages != null) {
			for (int i : ages) {
				map = dao.getVotes(i);
				Iterator<Integer> keys = map.keySet().iterator();
				int person = keys.next();
		%>
		<h3><%=i%>대
			<%=person%>명
		</h3>
		<table border="2">
			<th>브랜드</th>
			<th>투표수</th>
			<%
				for (int j = 0; j < map.get(person).size(); j++) {
			%>
			<tr>
				<td><%=map.get(person).get(j).getBrand()%></td>
				<td><%=map.get(person).get(j).getVote()%></td>
			</tr>
			<%
				}
			%>

		</table>
		<%
			}
		}
		%>
	</div>

</body>
</html>