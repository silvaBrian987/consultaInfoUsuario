<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:if test="${hideForm == true}">
	<script type="text/javascript">
		document.getElementById("analisisPermisosWAS_form").style.display = "none";
	</script>
</c:if>

<div class="container-fluid" id="analisisPermisosWAS_form">
	<form action="analizarPermisosWAS" method="post" enctype="multipart/form-data" class="form" role="form">
		<div class="row">
			<!-- panel preview -->
			<div class="col-md-5">
				<div class="panel panel-default">
					<div class="panel-body form-horizontal payment-form">
						<div class="form-group">
							<label for="user" class="col-md-3 control-label">Tipo</label>
							<div class="col-md-9">
								<c:forEach items="${tipos}" var="tipo">
									<input type="radio" name="tipo" value="${tipo}">
									<label>${tipo}</label>
									<br>
								</c:forEach>
							</div>
						</div>
						<div class="form-group">
							<label for="nombre" class="col-md-3 control-label">Nombre</label>
							<div class="col-md-9">
								<input type="text" class="form-control" id="nombre" name="nombre">
							</div>
						</div>
						<div class="form-group">
							<label for="appName" class="col-md-3 control-label">Nombre de aplicacion</label>
							<div class="col-md-9">
								<input type="text" class="form-control" id="appName" name="appName">
							</div>
						</div>
						<div class="form-group">
							<label for="archivoXMI" class="col-md-3 control-label">Archivo XMI</label>
							<div class="col-md-9">
								<input type="file" class="file" name="archivoXMI" />
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-12 text-right">
								<button type="submit" class="btn btn-primary btn-block">
									<span class="glyphicon glyphicon-plus"></span> Analizar
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</div>

<div id="analisisPermisosWAS_resultado">
	<c:if test="${fn:length(rolesPorUsuario) gt 0}">
		<label>Roles del usuario ${param.nombre}</label>
		<ul>
			<c:forEach items="${rolesPorUsuario}" var="rol">
				<li>${rol}</li>
			</c:forEach>
		</ul>
	</c:if>

	<c:if test="${fn:length(rolesPorGrupo) gt 0}">
		<label>Roles por grupo</label>
		<ul>
			<c:forEach items="${rolesPorGrupo}" var="rol">
				<li>${rol}</li>
			</c:forEach>
		</ul>
	</c:if>
</div>