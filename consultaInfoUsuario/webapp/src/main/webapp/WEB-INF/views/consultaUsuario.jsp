<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container-fluid">
	<form class="form" role="form">
		<div class="row">
			<!-- panel preview -->
			<div class="col-md-12">
				<h4>Datos de ${usuarioConsultado.nombre} ${usuarioConsultado.apellido}</h4>
				<div class="panel panel-default">
					<div class="panel-body form-horizontal payment-form">
						<div class="form-group">
							<label for="nombre" class="col-md-3 control-label">Nombre</label>
							<div class="col-md-9">
								<input type="text" class="form-control" id="nombre" name="nombre" disabled="disabled" value="${usuarioConsultado.nombre}">
							</div>
						</div>
						<div class="form-group">
							<label for="apellido" class="col-md-3 control-label">Apellido</label>
							<div class="col-md-9">
								<input type="text" class="form-control" id="apellido" name="apellido" disabled="disabled" value="${usuarioConsultado.apellido}">
							</div>
						</div>
						<div class="form-group">
							<label for="usuario" class="col-md-3 control-label">Usuario</label>
							<div class="col-md-9">
								<input type="text" class="form-control" id="usuario" name="usuario" disabled="disabled" value="${usuarioConsultado.usuario}">
							</div>
						</div>
						<div class="form-group">
							<label for="scriptInicio" class="col-md-3 control-label">Script de inicio de sesion</label>
							<div class="col-md-9">
								<input type="text" class="form-control" id="scriptInicio" name="scriptInicio" disabled="disabled" value="${usuarioConsultado.scriptInicio}">
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- / panel preview -->
			<div class="col-md-12">
				<h4>Grupos a los que pertenece ${usuarioConsultado.nombre} ${usuarioConsultado.apellido}</h4>
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
									<c:forEach items="${usuarioConsultado.grupos}" var="grupo">
										<c:if test="${grupo.nombreCompleto != null}">
											<tr class="active">
												<td class="input-text" id="user.usuario"><a href="obtenerDatos?nombre=${grupo.nombreCompleto}&tipo=GRUPO">${grupo.nombre}</a></td>
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
	var gridTitle = "Grupos a los que pertenece ${usuarioConsultado.nombre} ${usuarioConsultado.apellido}";
	var listaURL = new Array();
	var URL = function(url) {
		this.url = url;
	};
</script>

<%-- <c:forEach items="${usuarioConsultado.grupos}" var="grupo"> --%>
<%-- 	<c:if test="${grupo.nombreCompleto != null}"> --%>
<!-- 		<script> -->
<%-- 			listaURL.push(new URL('<a href="obtenerDatos?nombre=${grupo.nombreCompleto}&tipo=GRUPO">${grupo.nombre}</a>')); --%>
<!-- 		</script> -->
<%-- 		<a href="obtenerDatos?nombre=${grupo.nombreCompleto}&tipo=GRUPO">${grupo.nombre}</a><br> --%>
<%-- 	</c:if> --%>
<%-- </c:forEach> --%>

<!-- <script type="text/javascript" src="resources/libs/js/app/consultaDatos.js"></script> -->

<%-- <jsp:include page="/WEB-INF/views/commons/grillaConsulta.jsp"></jsp:include> --%>