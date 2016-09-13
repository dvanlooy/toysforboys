<%@ page contentType='text/html' pageEncoding='UTF-8'%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="v" uri='http://vdab.be/tags'%>
<!doctype html>
<html lang='nl'>
<v:head title='404' />
<body>
	<section class='jumbotron'>
		<div class="container">
			<div class="panel panel-warning">
				<div class="panel-heading">
					<h2>
						<span class="glyphicon glyphicon-cog" aria-hidden="true"></span><strong>
							Whoops!</strong> Page not found.
					</h2>
				</div>
				<div class="panel-body">
					<div class="container text-center">
						<h1>We looked everywhere</h1>
						<p class="text-warning">And couldn't find that page. But we
							did find these under the couch cushions:</p>
						<img alt="404 error" src="images/404.png">
					</div>
					<c:url value='/index.htm' var='indexURL'>
					</c:url>
					<div class="container text-center">
						<a href="${indexURL}" class="btn btn-warning" role="button">You
							might want to start over here.</a> <a href="http://www.theuselessweb.com/"
							class="btn btn-info" role="button">Or if you want to continue feeling useless...</a>
					</div>
				</div>
			</div>

		</div>
	</section>
</body>
</html>