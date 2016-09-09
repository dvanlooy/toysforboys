<%@ page contentType='text/html' pageEncoding='UTF-8'%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="v" uri='http://vdab.be/tags'%>
<!doctype html>
<html lang='nl'>
<v:header />
<c:if test="${not empty noStockUnshippedOrders }">
	<div class="page-header">
		<div class="alert alert-danger">
			Shipping failed for following order(s):
			<c:forEach items='${noStockUnshippedOrders}'
				var='noStockUnshippedOrder' varStatus="status">
				<c:url value='/orderdetail.htm' var='orderDetailURL'>
					<c:param name='id' value="${noStockUnshippedOrder}" />
				</c:url>
				<a class="btn btn-danger" href="<c:out value='${orderDetailURL}'/>"
					role="button">${noStockUnshippedOrder}</a>
			</c:forEach>
		</div>
	</div>
</c:if>
<div class="page-header">
	<h1>Unshipped orders</h1>
</div>

<c:if test="${not empty unshippedorders}">
	<form action="" method="post">
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
			class="btn btn-primary btn-block">
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
<v:footer />