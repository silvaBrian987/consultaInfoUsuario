<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<nav class="navbar navbar-default" role="navigation">
	<div class="container-fluid">
		<!-- 		<div class="navbar-header"> -->
		<!-- 			<a class="navbar-brand navbar-left" rel="home" href="inicio"></a> -->
		<!-- 		</div> -->
		<c:if test="${userIsLogged == true}">
			<ul class="nav navbar-nav">
				<li class="dropdown"><a href="/#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Consultas<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="buscarUsuarioGrupo">Active Directory - Usuario/Grupo</a></li>
						<li><a href="analizarPermisosWAS">Analizar archivo de permisos WAS</a></li>
						<li><a href="directorioGlobal/buscarUsuarioGrupo">Directorio Global - Usuario/Grupo</a></li>
					</ul></li>
				<li class="dropdown"><a href="./logout" class="dropdown-toggle"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> Cerrar sesion<span class="caret"></span></a></li>

			</ul>
		</c:if>
		<jsp:include page="/WEB-INF/views/datosUsuario.jsp"></jsp:include>
	</div>
</nav>