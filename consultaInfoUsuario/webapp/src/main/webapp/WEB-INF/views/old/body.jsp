<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="bodyMain" class="body_main">
	<c:if test="${param.url != null}">
		<jsp:include page="${param.url}"></jsp:include>
	</c:if>
</div>