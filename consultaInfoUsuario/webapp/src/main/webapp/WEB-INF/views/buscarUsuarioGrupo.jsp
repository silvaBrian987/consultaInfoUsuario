<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
	function buscar() {
		var tipos = document.getElementsByName("tipo");
		var tipo = "";
		if (tipos || tipos.length > 0) {
			for (var i = 0; i < tipos.length; i++) {
				if (tipos[i].checked == true) {
					tipo = tipos[i].value;
					break;
				}
			}
		}

		var nombre = document.getElementById("nombre").value;

		if (tipo == "usuario") {
			obtenerDatosUsuario(nombre);
		} else if (tipo == "grupo") {
			obtenerDatosGrupo(nombre);
		}
	}
</script>

<div class="container-fluid">
	<form action="obtenerDatos" method="post" class="form" role="form">
		<div class="row">
			<!-- panel preview -->
			<div class="col-md-5">
				<div class="panel panel-default">
					<div class="panel-body form-horizontal payment-form">
						<div class="form-group">
							<label for="user" class="col-md-3 control-label">Tipo</label>
							<div class="col-md-9">
								<c:forEach items="${tipos}" var="tipo">
									<input type="radio" name="tipo" value="${tipo}"><label>${tipo}</label><br>
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
							<div class="col-md-12 text-right">
								<button type="submit" class="btn btn-primary btn-block">
									<span class="glyphicon glyphicon-plus"></span> Buscar
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
</div>