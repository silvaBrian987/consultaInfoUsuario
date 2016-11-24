<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container-fluid">
	<form class="form" role="form">
		<div class="row">
			<!-- panel preview -->
			<div class="col-md-12">
				<h4>Datos de ${grupoConsultado.nombre}</h4>
				<div class="panel panel-default">
					<div class="panel-body form-horizontal payment-form">
						<div class="form-group">
							<label for="nombre" class="col-md-3 control-label">Nombre</label>
							<div class="col-md-9">
								<input type="text" class="form-control" id="nombre" name="nombre" disabled="disabled" value="${grupoConsultado.nombre}">
							</div>
						</div>
						<div class="form-group">
							<label for="nombreCompleto" class="col-md-3 control-label">Nombre completo</label>
							<div class="col-md-9">
								<input type="text" class="form-control" id="nombreCompleto" name="nombreCompleto" disabled="disabled" value="${grupoConsultado.nombreCompleto}">
							</div>
						</div>
						<div class="form-group">
							<label for="comentario" class="col-md-3 control-label">Comentario</label>
							<div class="col-md-9">
								<input type="text" class="form-control" id="comentario" name="comentario" disabled="disabled" value="${grupoConsultado.comentario}">
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- / panel preview -->
			<div class="col-md-12">
				<h4>Usuarios que son miembros de ${grupoConsultado.nombre}</h4>
				<div class="row">
					<div class="col-md-12">
						<div class="table-responsive">
							<table class="table preview-table" id="preview-table">
								<thead>
									<tr>
										<th>URL</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${grupoConsultado.usuarios}" var="user">
										<c:if test="${user.usuario != null}">
											<tr class="active">
												<td class="input-text" id="user.usuario"><a href="obtenerDatos?nombre=${user.usuario}&tipo=USUARIO">${user.usuario} - ${user.nombre} ${user.apellido}</a></td>
											</tr>
										</c:if>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</div>

<script>
	var gridTitle = "Usuarios que son miembros de ${grupoConsultado.nombre}";
	var listaURL = new Array();
	var URL = function(url) {
		this.url = url;
	};
</script>

<%-- <c:forEach items="${grupoConsultado.usuarios}" var="user"> --%>
<%-- 	<c:if test="${user.usuario != null}"> --%>
<!-- 		<script> -->
<%--                 listaURL.push(new URL(' <a href="obtenerDatos?nombre=${user.usuario}&tipo=USUARIO">${user.usuario} - ${user.nombre} ${user.apellido}</a>')); --%>
<!-- 		</script> -->
<%-- 	</c:if> --%>
<%-- </c:forEach> --%>

<!-- <script type="text/javascript" src="resources/libs/js/app/consultaDatos.js"></script> -->

<%-- <jsp:include page="/WEB-INF/views/commons/grillaConsulta.jsp"></jsp:include> --%>