<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
<head>
<tiles:insertAttribute name="header" />
<title><tiles:insertAttribute name="title" /></title>
</head>
<body>
	<div id="menu">
		<tiles:insertAttribute name="menu" />
	</div>
	<div id="contenido">
		<tiles:insertAttribute name="body" />
	</div>
	<div id="footer">
		<tiles:insertAttribute name="footer" />
	</div>
</body>
</html>