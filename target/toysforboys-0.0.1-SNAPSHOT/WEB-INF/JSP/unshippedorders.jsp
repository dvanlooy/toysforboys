<%@ page contentType='text/html' pageEncoding='UTF-8'%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="v" uri='http://vdab.be/tags'%>
<!doctype html>
<html lang='nl'>
<v:header />
<c:if test="${not empty noStockUnshippedOrders }">
	<div class="page-header">
		<div class="panel panel-danger">
			<div class="panel-heading">
				<h2>Not enough Stock</h2>
			</div>
			<div class="panel-body">
				<p>Could not complete shipping for following orders:</p>
			</div>
			<table class="table table-striped">
				<tr>
					<th class="col-md-1 text-center">ID</th>
					<th class="col-md-1">Ordered</th>
					<th class="col-md-1">Required</th>
					<th class="col-md-2">Customer</th>
					<th>Comments</th>
					<th class="col-md-2">Status</th>
				</tr>
				<c:forEach items='${noStockUnshippedOrders}'
					var='noStockUnshippedOrder' varStatus="status">
					<c:url value='/orderdetail.htm' var='orderDetailURL'>
						<c:param name='id' value="${noStockUnshippedOrder.id}" />
					</c:url>
					<tr>
						<td class="col-md-1 text-center"><a class="btn btn-danger"
							href="<c:out value='${orderDetailURL}'/>" role="button">${noStockUnshippedOrder.id}</a></td>
						<td class="col-md-1">${noStockUnshippedOrder.orderDate}</td>
						<td class="col-md-1">${noStockUnshippedOrder.requiredDate}</td>
						<td class="col-md-2">${noStockUnshippedOrder.customer.name}</td>
						<td>${noStockUnshippedOrder.comments}</td>
						<td class="col-md-2"><img
							src="images/${noStockUnshippedOrder.status}.png">&nbsp;
							${fn:toUpperCase(fn:substring(noStockUnshippedOrder.status, 0, 1))}${fn:toLowerCase(fn:substring(noStockUnshippedOrder.status, 1, -1))}
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</c:if>
<c:if test="${not empty shippedOrdersIds }">
	<div class="panel panel-success">
		<div class="panel-heading">
			<h2 class="panel-title">Shipping success</h2>
		</div>
		<div class="panel-body">
			Shipping completed for orders
			<c:forEach items='${shippedOrdersIds}' var='shippedOrdersId'
				varStatus="status">
				${shippedOrdersId}${status.last ? "." : ", "}
			</c:forEach>
		</div>
	</div>
</c:if>
<div class="page-header">
	<h1>Unshipped orders</h1>
</div>
 
<c:if test="${not empty unshippedorders}">
	<form action="" method="post" id="setAsShippedForm">
		<table class="table table-striped">
			<tr>
				<th class="col-md-1 text-center">ID</th>
				<th class="col-md-1">Ordered</th>
				<th class="col-md-1">Required</th>
				<th class="col-md-2">Customer</th>
				<th>Comments</th>
				<th class="col-md-2">Status</th>
				<th class="col-md-1 text-center">Ship</th>
			</tr>
			<c:forEach items='${unshippedorders}' var='unshippedorder'>
				<c:url value='/orderdetail.htm' var='orderDetailURL'>
					<c:param name='id' value="${unshippedorder.id}" />
				</c:url>
				<tr>
					<td class="col-md-1 text-center"><a class="btn btn-info"
						href="<c:out value='${orderDetailURL}'/>" role="button">${unshippedorder.id}</a></td>
					<td class="col-md-1">${unshippedorder.orderDate}</td>
					<td class="col-md-1">${unshippedorder.requiredDate}</td>
					<td class="col-md-2">${unshippedorder.customer.name}</td>
					<td>${unshippedorder.comments}</td>
					<td class="col-md-2"><img
						src="images/${unshippedorder.status}.png">&nbsp;
						${fn:toUpperCase(fn:substring(unshippedorder.status, 0, 1))}${fn:toLowerCase(fn:substring(unshippedorder.status, 1, -1))}
					</td>
					<td class="col-md-1 text-center"><input type='checkbox'
						name='ship' value='${unshippedorder.id}'></td>
				</tr>
			</c:forEach>
		</table>
		<input type="submit" value="Set as shipped"
			class="btn btn-primary btn-block" id='submit'>
	</form>
	<ul class="pagination">
		<c:if test='${vanafRij != 0}'>
			<c:url value='' var='vorigePaginaURL'>
				<c:param name='vanafRij' value='${vanafRij - aantalRijen}' />
			</c:url>
			<li><a href="<c:out value='${vorigePaginaURL}'/>"
				title='Previous'>Previous</a></li>
		</c:if>
		<c:if test='${empty laatstePagina}'>
			<c:url value='' var='volgendePaginaURL'>
				<c:param name='vanafRij' value='${vanafRij + aantalRijen}' />
			</c:url>
			<li><a href="<c:out value='${volgendePaginaURL}'/>" title='Next'>Next</a></li>
		</c:if>
	</ul>
</c:if>
<script>
	document.getElementById('setAsShippedForm').onsubmit = function() {
		document.getElementById('submit').disabled = true;
	};
</script>
<v:footer />