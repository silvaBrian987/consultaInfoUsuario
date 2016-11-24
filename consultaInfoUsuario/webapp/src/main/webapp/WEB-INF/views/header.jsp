<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- GLOSARIO -->
<%-- CONDICION EN JSTL PARA SABER SI ES IE => test="${fn:contains(header['User-Agent'],'MSIE')}" --%>

<c:set var="url">${pageContext.request.requestURL}</c:set>

<!-- Always force latest IE rendering engine (even in intranet) & Chrome Frame 
Remove this if you use the .htaccess -->
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

<base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />

<!-- The jQuery library is a prerequisite for all jqSuite products -->
<script type="text/ecmascript" src="resources/libs/js/jquery/jquery-1.12.2.js"></script>
<!-- We support more than 40 localizations -->
<script type="text/javascript" src="resources/libs/js/jqgrid/grid.locale-es.js"></script>
<!-- This is the Javascript file of jqGrid -->
<script type="text/ecmascript" src="resources/libs/js/jqgrid/jquery.jqGrid.js"></script>
<!-- A link to a Boostrap  and jqGrid Bootstrap CSS siles-->
<link rel="stylesheet" href="resources/libs/css/bootstrap/bootstrap.css">
<link rel="stylesheet" type="text/css" media="screen" href="resources/libs/css/jqgrid/ui.jqgrid-bootstrap.css" />

<!-- CONFIG DE JQGRID + BOOTSTRAP -->
<script>
	var baseURL = "${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}";
	$.jgrid.defaults.width = 1024;
	$.jgrid.defaults.responsive = true;
	$.jgrid.defaults.styleUI = 'Bootstrap';
</script>

<!-- BOOTSTRAP3 -->
<script src="resources/libs/js/bootstrap/bootstrap.js"></script>
<script src="resources/libs/js/bootstrap/bootstrap3-typeahead.min.js"></script>

<!-- JQUERY UI -->
<link rel="stylesheet" type="text/css" media="screen" href="resources/libs/css/jquery_ui/jquery-ui-1.10.0.custom.css" />
<link rel="stylesheet" type="text/css" media="screen" href="resources/libs/css/jquery_ui/jquery.ui.1.10.0.ie.css" />
<script type="text/ecmascript" src="resources/libs/js/jquery_ui/jquery-ui-1.12.0.min.js"></script>
<script type="text/ecmascript" src="resources/libs/js/jquery_ui/confDatepicker.js"></script>

<!-- Librerias propias -->
<link rel="stylesheet" href="resources/libs/css/app/grillas.css">
<link rel="stylesheet" href="resources/libs/css/app/botones.css">
<link rel="stylesheet" href="resources/libs/css/app/otros.css">
<script type="text/javascript" src="resources/libs/js/modernizr/modernizr.js"></script>
<script type="text/javascript" src="resources/libs/js/app/funciones.js"></script>
<script type="text/javascript" src="resources/libs/js/app/funcionesABM.js"></script>

<link rel="icon" href="/resources/img/favicon.ico" type="image/x-icon" />