<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>Money Grower</title>
<link href="css/content.css" rel="stylesheet" />
<link href="css/login.css" rel="stylesheet" />
<meta http-equiv="Content-Type" content="text/jsp; charset=UTF-8" />
</head>
<body>
	<jsp:include page="_header.jsp" />
	<div id="all-content">
		<form id="login" class="center-box" method="POST" action="view">
			<p id="login-label">ĐĂNG NHẬP</p>
			<input type="text" placeholder="Tài khoản" autofocus name="username"/><br /> <input
				type="password" placeholder="Mật khẩu" name="password"/><br /> <input
				type="submit" value="Đăng nhập" />
			<c:if test="${not empty isFailLogin}">
				<p id="login-fail">Tên tài khoản hoặc mật khẩu không chính xác</p>
			</c:if>
		</form>
	</div>
</body>
</html>
