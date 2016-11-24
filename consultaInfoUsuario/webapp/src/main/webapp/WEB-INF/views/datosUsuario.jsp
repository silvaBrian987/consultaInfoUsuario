<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:useBean id="now" class="java.util.Date" />

<%-- <c:set var="legajo" value="${pageContext.request.userPrincipal.name}"></c:set> --%>

<script type="text/javascript" src="resources/libs/js/app/jquery_reloj.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		updateClock("reloj");
		setInterval(function() {
			updateClock("reloj")
		}, 1000);
	});
</script>

<ul class="nav navbar-nav navbar-right">
	<c:if test="${usuario != null}">
		<li><a href="obtenerDatos?nombre=${usuario.usuarioCompleto}&tipo=USUARIO"><span class="glyphicon glyphicon-user"></span> Usuario: ${usuario.usuario}</a></li>
	</c:if>
	<li><a id="reloj" href=""></a></li>
	<%-- <li><img src="http://ecompany.bancogalicia.com.ar/eCompany/fotos/${legajoNumero}.jpg" style="width: 50%; margin-top: -7px;"></li> --%>
</ul>