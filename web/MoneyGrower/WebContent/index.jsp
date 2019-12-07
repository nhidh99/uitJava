<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/jsp; charset=UTF-8" />
<title>Money Grower</title>
<link href="css/content.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="_header.jsp"></jsp:include>
	<div id="all-content">
		<table class="center-box">
			<c:forEach items="${transactions}" var="transaction">
				<tr>
					<td>${transaction.transactionID}</td>
					<td>${transaction.nameID}</td>
					<td>${transaction.price}</td>
					<td>${transaction.date}</td>
					<td>${transaction.note}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>