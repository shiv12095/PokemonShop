<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Pokemon Pawn Shop</title>
<jsp:include page="../StyleReference.jsp" />
</head>
<body>
<jsp:include page="../Header.jsp" />
<main class="mdl-layout__content">
<div class="mdl-grid">
	<div class="mdl-cell mdl-cell--1-col"></div>
	<div class="mdl-cell mdl-cell--10-col">
	<form:form modelAttribute="shippingInfo" method="post" action="submitShipping">
		<table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp" >
			<tr>
				<td class="mdl-data-table__cell--non-numeric">Address Line 1:</td>
				<td class="mdl-data-table__cell--non-numeric"><form:input path="addressLine1"/></td>
			</tr>
			<tr>
				<td class="mdl-data-table__cell--non-numeric">Address Line 2:</td>
				<td class="mdl-data-table__cell--non-numeric"><form:input path="addressLine2"/></td>
			</tr>
			<tr>
				<td class="mdl-data-table__cell--non-numeric">City:</td>
				<td class="mdl-data-table__cell--non-numeric"><form:input path="city"/></td>
			</tr>
			<tr>
				<td class="mdl-data-table__cell--non-numeric">State:</td>
				<td class="mdl-data-table__cell--non-numeric"><form:input path="state"/></td>
			</tr>
			<tr>
				<td class="mdl-data-table__cell--non-numeric">Zip Code:</td>
				<td class="mdl-data-table__cell--non-numeric"><form:input path="zipCode"/></td>
			</tr>
		</table>
		<button class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect">
			Submit
		</button>
	</form:form>
	</div>
	</div>
</main>
<jsp:include page="../Footer.jsp" />
</div>

</body>
</html>