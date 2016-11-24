function crearGrillaConsulta(url, colModel, events) {
	return $("#grillaConsulta").jqGrid({
		url : url,
		mtype : "GET",
		styleUI : 'Bootstrap',
		datatype : "json",
		loadError : function(jqXHR, textStatus, errorThrown) {
			alert("Ha ocurrido un error. Ver el log en consola.");
			console.log("textStatus: " + textStatus);
			console.log("errorThrown: " + errorThrown);
			console.log(jqXHR);
		},
		colModel : colModel,
		viewrecords : true,
		// height: 250,
		// align: 'center',
		// width: '100%',
		autowidth : true,
		rowNum : 20,
		pager : "#grillaConsultaPager",
		ondblClickRow : events.ondblClickRow,
		onSelectRow : events.onSelectRow,
		gridComplete : events.gridComplete
	});
}

function crear(url, $botonABM) {
	//var url = "./actions/marca/create";
	
	var res = doPOST(url, obtenerCamposComoFormData(listaIdsInputFormularioABM), true);
	if (res instanceof AjaxException) {
		manejarErrorEnABM(res.responseText);
	}else{
		$("#mensajeExito").html("Se ha guardado correctamente.");
		$("#div_alert_success").show();
		if($botonABM != undefined){
			$botonABM.val("Cerrar");
			$botonABM.unbind('click').click(function() {
				cerrar();
			})
		}
	}
}

function modificar(url, $botonABM) {
	//var url = "./actions/marca/update";
	var res = doPOST(url, obtenerCamposComoFormData(listaIdsInputFormularioABM), true);
	if (res instanceof AjaxException) {
		manejarErrorEnABM(res.responseText);
	}else{
		$("#mensajeExito").html("Se ha modificado correctamente.");
		$("#div_alert_success").show();
		if($botonABM != undefined){
			$botonABM.val("Cerrar");
			$botonABM.unbind('click').click(function() {
				cerrar();
			})
		}
	}
}

function borrar(url, $botonABM) {
	//var url = "./actions/marca/delete?id="+$("#idMarca").val();
	var res = doDELETE(url);
	if (res instanceof AjaxException) {
		manejarErrorEnABM(res.xhr.responseJSON.cause);
	}else{
		$("#mensajeExito").html("Se ha borrado correctamente.");
		$("#div_alert_success").show();
		if($botonABM != undefined){
			$botonABM.val("Cerrar");
			$botonABM.unbind('click').click(function() {
				cerrar();
			})
		}
	}
}

function manejarErrorEnABM(mensaje, botonCerrar){
	botonCerrar = botonCerrar == undefined ? true : botonCerrar;
	$("#mensajeError").html("Ha ocurrido el siguiente error: " + mensaje);
	$("#div_alert_error").show();
	if(botonCerrar){
		$botonABM.val("Cerrar");
		$botonABM.unbind('click').click(function() {
			cerrar();
		})
	}
}

function verificarRespuestaConsultaCombo(json, nombreCampo, botonCerrar){
	var mensaje;
	
	flag = true;
	
	if(json == null || json == undefined || json.length == 0){
		mensaje = "No se encontraron registros" + (nombreCampo == undefined ? "" : " de '" + nombreCampo + "'");
		manejarErrorEnABM(mensaje, botonCerrar);
		flag = false;
	}else if (json instanceof AjaxException) {
		manejarErrorEnABM(res.xhr.responseJSON.cause);
		flag = false;
	}
	
	return flag;
}

function crearBotonNuevoFormularioABMPopUp(campoRelacionado, urlPagina, titulo){
	var $campo = $("#" + campoRelacionado);
	var $divPadre = $($campo.parents()[0]);
	var $botonNuevoForm = $("<span class='glyphicon glyphicon-plus'></span>"); 
	$divPadre.append($botonNuevoForm);
	$botonNuevoForm.click(function(){
		var popup = abrirPopup(urlPagina, {title: titulo, mode: "_blank"});
		
		$(popup).unload(function(){
			completarCombos();
		});
		
		$(window).unload(function(){
			if(popup) popup.close();
		})
		
		$("#cboxOverlay").show();
		$("#cboxOverlay").click(function(){
			$(this).hide();
			popup.close()
			completarCombos();
		});
	});
}

function crearBotonNuevoFormularioABMInPage(campoRelacionado, divDestino, urlPagina, urlFormAction){
	var $campo = $("#" + campoRelacionado);
	var $divPadre = $($campo.parents()[0]);
	var $botonNuevoForm = $("<span class='glyphicon glyphicon-plus'></span>"); 
	$divPadre.append($botonNuevoForm);
	var $divForm = $("#" + divDestino);
	var local_completarCombos = completarCombos;
	$botonNuevoForm.click(function(){	
		cargarPagina(divDestino, urlPagina);
		
		$divForm.mousemove(function(){
			var $boton = $($divForm.find(":input[id*='bGuardar']")[0])
			$boton.val("Guardar");
			$boton.unbind('click').click(function(){
				crear(urlFormAction + "/create");
				$("#cboxOverlay").click();
			});
		})
		
		$divForm.css({
			position: 'absolute',
			"padding-top": '5%',
			"padding-bottom": '5%',
			"padding-left": '5%',
			"padding-right": '5%',
			"z-index": 9999,
			background: 'white'
		})
		
		$("#cboxOverlay").css({
		     position: 'absolute',
		     "padding-top": '100%',
		     "padding-bottom": '100%',
		     "padding-left": '100%',
		     "padding-right": '100%',
		     "background-color": 'rgba(0, 0, 0, 0.85)',
		     "z-index": 9998,
		     color: 'white',
		     display: 'inline-block'
		});
		
		$("#cboxOverlay").click(function(){
			/*
			$divForm.empty();
			$divForm.hide();
			local_completarCombos();
			$(this).hide();
			*/
			//FIXME - Ver si se puede mejorar la logica de esto, no me gusta que tenga que recargar la pagina... 
			location.reload(true);
		});
		
		$("#cboxOverlay").show();
		$divForm.show();
	});
	return $divForm;
}