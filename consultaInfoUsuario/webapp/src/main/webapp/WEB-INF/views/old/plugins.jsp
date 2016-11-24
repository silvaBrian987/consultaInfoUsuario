<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="url">${pageContext.request.requestURL}</c:set>
<base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />

<!-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> -->

<link rel="stylesheet" type="text/css" href="./css/menu.css" />
<link rel="stylesheet" type="text/css" href="./css/pages.css" />

<script type="text/javascript" src="./js/funcionesGenerales.js"></script>
<script type="text/javascript" src="./js/endpoints.js"></script>

<link rel="icon" href="favicon.ico" type="image/x-icon" />