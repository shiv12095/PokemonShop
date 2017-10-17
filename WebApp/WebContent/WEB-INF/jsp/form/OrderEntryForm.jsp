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
			<form id="orderForm">

				<table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp" style="width: 100%;">
					<tr>
						<th class="mdl-data-table__cell--non-numeric">Pokemon</th>
						<th class="mdl-data-table__cell">Quantity</th>
					</tr>
					
					<c:forEach var="item" items="${inventory.items}" varStatus="status">
						<tr>
						    <td data-label="Pokemon" class="mdl-data-table__cell--non-numeric" id ="input-label-${status.index}">${item.name}</td>
						    	<td>
						    		<input class="input-field" type="text" value=0></input>
						    	</td>
						</tr>
					</c:forEach>			

				</table>		
			</form>
			<button
				id="submit"
				class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect">
				Submit
			</button>
		<div class="mdl-cell mdl-cell--1-col"></div>
	</div>
	</main>
<jsp:include page="../Footer.jsp" />
</div>
</body>

 <script type="text/javascript">
      $(document).ready(function() {
	    	  $('#submit').click(function () {
	    		  var data = [];
	    		  i = 0;
	    		  $(".input-field").each(function(elem, i) {
	    			    var val = $(this).val();
	    			    console.log(val);
					if(val > 0){
						var id = "input-label-" + i;
						console.log(i);						
						var name = $("#" + id).val();
						data.push({
							"name": name,
							"quantity": val
						});
					}
    			 	 });
	    		  console.log(data);
/* 	          $.ajax({
	              type: "post",
	              url: "testme", //this is my servlet
	              data: "input=" +$('#ip').val()+"&output="+$('#op').val(),
	              success: function(msg){      
	                      $('#output').append(msg);
	              }
	          }); */
		  });
      });
  </script>

</html>