<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/jsp; charset=UTF-8" />
<title>Money Grower</title>
<link href="css/content.css" rel="stylesheet" />
<link href="css/transaction.css" rel="stylesheet" />
</head>
<body>
	<button id="btn-add">+</button>
	<jsp:include page="_header.jsp"></jsp:include>
	<div id="all-content">
		<table class="center-box table">
			<tr>
				<td class="user-info" colspan="2">Người dùng:</td>
				<td class="user-value">${user.name}</td>
			</tr>
			<tr>
				<td class="user-info" colspan="2">Số dư:</td>
				<c:choose>
					<c:when test="${user.income > 0}">
						<td class="green transaction-price user-value "><fmt:formatNumber
								type="number" groupingUsed="true" value="${user.income}" />đ</td>
					</c:when>
					<c:otherwise>
						<td class="red transaction-price user-value "><fmt:formatNumber
								type="number" groupingUsed="true" value="${user.income}" />đ</td>
					</c:otherwise>
				</c:choose>
			</tr>

			<c:forEach items="${transactionsMap}" var="entry">
				<tr>
					<td class="divider" colspan="3"><hr></td>
				</tr>
				<c:set var="totalPrice" value="${0}" />
				<c:forEach items="${entry.value}" var="transaction">
					<c:set var="totalPrice" value="${totalPrice + transaction.price}" />
				</c:forEach>

				<tr class="transaction-summary">
					<td class="transaction-date" colspan="2">⚝<fmt:parseDate
							value="${entry.key}" type="date" pattern="yyyy-MM-dd"
							var="parsedDate" /> <fmt:formatDate pattern="dd/MM/yyyy"
							value="${parsedDate}" /></td>
					<c:choose>
						<c:when test="${totalPrice > 0}">
							<td class="green transaction-price"><fmt:formatNumber
									type="number" groupingUsed="true" value="${totalPrice}" />đ</td>
						</c:when>
						<c:otherwise>
							<td class="red transaction-price"><fmt:formatNumber
									type="number" groupingUsed="true" value="${totalPrice}" />đ</td>
						</c:otherwise>
					</c:choose>
				</tr>
				<c:forEach items="${entry.value}" var="transaction">
					<tr id="${transaction.transactionID}">
						<td class="transaction-icon">${transaction.type.icon}</td>
						<td class="transaction-detail"><span
							class="transaction-title">${transaction.type.name}</span> <c:if
								test="${not empty transaction.note}">
								<br />
								<span class="transaction-note">${transaction.note}</span>
							</c:if></td>
						<c:choose>
							<c:when test="${transaction.price > 0}">
								<td class="green transaction-price"><fmt:formatNumber
										type="number" groupingUsed="true" value="${transaction.price}" />đ
								</td>
							</c:when>
							<c:otherwise>
								<td class="red transaction-price"><fmt:formatNumber
										type="number" groupingUsed="true" value="${transaction.price}" />đ</td>
							</c:otherwise>
						</c:choose>
					</tr>
				</c:forEach>
			</c:forEach>
		</table>
	</div>
</body>
</html>
