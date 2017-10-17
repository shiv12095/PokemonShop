<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
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

	<p>
	Items ordered:
	<%= request.getSession().getAttribute("orderInfo") %>
	</p>

	<p>
	Card details:
	<%= request.getSession().getAttribute("paymentInfo") %>
	</p>
	
	<p>
	Shipping details:
	<%= request.getSession().getAttribute("shippingInfo") %>
	</p>	

		
	<form:form id="confirmForm" action="/PokemonPawnShop/purchase/confirmOrder" method="POST" > 	
    			<button>Submit</button>
	</form:form>
	</main>
	<jsp:include page="./Footer.jsp" />
</div>

	</body>

</body>
</html>