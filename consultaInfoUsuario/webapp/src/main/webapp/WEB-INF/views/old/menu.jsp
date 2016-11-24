<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="menu_simple">
	<ul>
		<li><a href="javascript:redirigir('/pages/buscarUsuarioGrupo.jsp')">Buscar Usuario/Grupo</a></li>
		<li><a href="javascript:redirigir('/pages/analisisPermisosWAS.jsp')">Analizar Permisos WebSphere</a></li>
	</ul>
		<c:if test="${usuario != null}">
		<ul>
			<li><a href="javascript:obtenerDatosUsuario('${usuario.usuarioCompleto}')">Usuario: ${usuario.nombre} ${usuario.apellido}</a></li>
			<li><li><a href="javascript:cerrarSesion()">Cerrar session</a></li></li>
			</ul>
		</c:if>	
</div>