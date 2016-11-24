<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="divOKDialog"></div>
<div id="divOKCancelDialog"></div>
<div id="divInputDialog"></div>
<script>
	$(document).ready(function() {
		$("#divOKDialog").dialog({
			autoOpen : false,
			buttons : [ {
				text : "OK",
				click : function() {
					$(this).empty();
					$(this).dialog("close");
				}
			} ]
		});
	});
</script>