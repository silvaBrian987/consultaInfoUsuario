function obtenerDatosUsuario(usuario) {
	var dir = "./obtenerDatosUsuario.do?usuario=" + usuario;
	callServlet(dir);
}

function obtenerDatosGrupo(grupo) {
	var dir = "./obtenerDatosGrupo.do?grupo_nombre=" + grupo;
	callServlet(dir);
}