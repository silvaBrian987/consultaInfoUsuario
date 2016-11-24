<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<div class="row">
		<div class="col-md-10">
			<div class="error-actions">
				<a href="./inicio" class="btn btn-primary btn-lg"><span class="glyphicon glyphicon-home"></span> Volver al inicio</a>
			</div>
		</div>
		<div class="col-md-10">URL: ${url}</div>
		<div class="col-md-10">
			<div class="error-template">
				<c:if test="${errors == null}">
					<h4>No se pudo encontrar el origen del problema</h4>
				</c:if>
				<c:if test="${errors != null}">
					<h4>Ha ocurrido el siguiente error:</h4>
					<c:forEach items="${errors}" var="error">
						<h3>Error: ${error.message}</h3>
						<c:forEach items="${error.stackTrace}" var="ste"> ${ste} <br />
						</c:forEach>
					</c:forEach>
				</c:if>
			</div>
		</div>
	</div>
</div>
