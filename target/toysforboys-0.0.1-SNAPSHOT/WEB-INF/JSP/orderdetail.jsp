<%@ page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="v" uri='http://vdab.be/tags'%>
<!doctype html>
<html lang='nl'>
<v:head title='Orderdetail ${order.id}' />
<v:header />
<c:if test="${not empty fout}">
	<div class="page-header">
		<div class="alert alert-danger" role="alert">
			<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
			<span class="sr-only"><strong>Error:</strong></span>
			<strong>WARNING!</strong> ${fout}.
		</div>
	</div>
</c:if>
<c:if test="${not empty order}">
	<div class="page-header">
		<h1>Order ${order.id}</h1>
	</div>
	<dl>
		<dt>Ordered:</dt>
		<dd>${order.orderDate}</dd>
		<br>
		<dt>Required:</dt>
		<dd>${order.requiredDate}</dd>
		<br>
		<dt>Customer:</dt>
		<dd>${order.customer.name}<br>
			${order.customer.adres.streetAndNumber}<br>
			${order.customer.adres.postalCode}&nbsp;${order.customer.adres.city}&nbsp;${order.customer.adres.state}
			${order.customer.adres.country.name}
		</dd>
		<br>
		<dt>Comments:</dt>
		<dd>${order.comments}</dd>
		<br>
		<dt>Details:</dt>
		<dd>
			<table class="table table-striped">
				<tr>
					<th class="text-center">Product</th>
					<th class="col-md-2">Price each</th>
					<th class="col-md-2">Quantity</th>
					<th class="col-md-2">Value</th>
					<th class="text-center col-md-1">Deliverable</th>
				</tr>
				<c:forEach items='${order.orderdetails}' var='orderdetail'>
					<tr>
						<td>${orderdetail.product.name}</td>
						<td class="col-md-2">${orderdetail.priceEach}</td>
						<td class="col-md-2">${orderdetail.quantityOrdered}</td>
						<td class="col-md-2">${orderdetail.totalValue}</td>
						<td class="text-center col-md-1"><c:choose>
								<c:when
									test="${orderdetail.quantityOrdered <= orderdetail.product.quantityInStock}">
									<span class="glyphicon glyphicon-ok"></span>
								</c:when>
								<c:otherwise>
									<span class="glyphicon glyphicon-remove"></span>
								</c:otherwise>
							</c:choose></td>
					</tr>
				</c:forEach>
				<tr class="info">
					<th>Total Amount:</th>
					<th class="col-md-2"></th>
					<th class="col-md-2"></th>
					<th class="col-md-2 ">${order.totalValue}</th>
					<th class="text-center col-md-1"></th>
				</tr>
			</table>
		</dd>
	</dl>
</c:if>
<v:footer />