var grilla = "grillaConsulta", pager = "grillaConsultaPager", lastSelection;

$(document).ready(function() {
	if (listaURL.length > 0) {

		$("#" + grilla).jqGrid({
			datatype: "local",
			caption : gridTitle,
			data : listaURL,
			colModel : [ {
				label : ' ',
				name : 'url',
				width : '100%',
			}, ],
			autowidth : true,
			height : 500,
			rowNum : 20,
			scroll : 1,
			pager : "#" + pager,
			viewrecords: true,
		});
	}
});