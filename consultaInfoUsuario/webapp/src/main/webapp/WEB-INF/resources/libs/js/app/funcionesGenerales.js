function callServlet(dir) {
	window.location = dir;
}

function redirigir(dir) {
	window.location = "./pages/main.jsp?url=" + dir;
}

function cerrarSesion(){
	callServlet('./login.do?closeSession')
}