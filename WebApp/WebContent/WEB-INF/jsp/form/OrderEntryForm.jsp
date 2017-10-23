<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
			<c:if test="${error eq 't'}">
				<h1>ERROR: Invalid quantities</h1>
			</c:if>
			<form:form id="orderForm"  action="purchase/submitItems" method="POST" modelAttribute="inventory">

				<table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp" style="width: 100%;">
					<tr>
						<th class="mdl-data-table__cell--non-numeric">Pokemon</th>
						<th class="mdl-data-table__cell">Quantity</th>
					</tr>
					
					<c:forEach var="item" items="${inventory.items}" varStatus="status">
						<tr>
						    <td data-label="Pokemon" class="mdl-data-table__cell--non-numeric" id ="input-label-${status.index}">${item.name}</td>
							<td data-label="Quantity">
								<form:input path="items[${status.index}].quantity" type="text" value="0" /> 
								<form:input path="items[${status.index}].name" type="hidden" />
							</td>
						</tr>
					</c:forEach>			
				</table>		

			<button
				class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect">
				Submit
			</button>
				
			</form:form>
		<div class="mdl-cell mdl-cell--1-col"></div>
	</div>
	</main>
<jsp:include page="../Footer.jsp" />
</div>
</body>

</html>