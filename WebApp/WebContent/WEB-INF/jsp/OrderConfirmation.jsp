<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Pokemon Pawn Shop</title>
<jsp:include page="./StyleReference.jsp" />
</head>
<body>
<jsp:include page="./Header.jsp" />
<main class="mdl-layout__content">
	<p>Thank you for your order from Pokemon Pawn Shop</p>
	<p>Your order number is: <%= request.getSession().getAttribute("orderNumber") %></p>
</main>
<jsp:include page="./Footer.jsp" />
</div>
</body>
</html>