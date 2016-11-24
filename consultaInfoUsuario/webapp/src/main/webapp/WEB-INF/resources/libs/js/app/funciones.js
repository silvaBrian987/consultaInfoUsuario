//EXTENSIONES DE JQUERY
//Funcion setNow
$.fn.setNow = function(onlyBlank) {
	var now = new Date($.now()), year, month, date, hours, minutes, seconds, formattedDateTime;

	year = now.getFullYear();
	month = now.getMonth().toString().length === 1 ? '0' + (now.getMonth() + 1).toString() : now.getMonth() + 1;
	date = now.getDate().toString().length === 1 ? '0' + (now.getDate()).toString() : now.getDate();
	hours = now.getHours().toString().length === 1 ? '0' + now.getHours().toString() : now.getHours();
	minutes = now.getMinutes().toString().length === 1 ? '0' + now.getMinutes().toString() : now.getMinutes();
	seconds = now.getSeconds().toString().length === 1 ? '0' + now.getSeconds().toString() : now.getSeconds();

	formattedDateTime = year + '-' + month + '-' + date + 'T' + hours + ':' + minutes + ':' + seconds;

	if (onlyBlank === true && $(this).val()) {
		return this;
	}

	$(this).val(formattedDateTime);

	return this;
}
/**
<p>Funcion para poner en modo edicion una fila de una grilla JQGrid.</p>
<p>Importante: definir en las siguientes variables globales</p>
<ul>
<li>"grilla" con el nombre del div donde esta la grilla.
<li>"lastSelection" para el id de la ultima fila seleccionada
</ul>
<p>Ejemplo: var grilla = "divGrilla";</p>
*/
function editRow(id) {
    if (id && id !== lastSelection) {
        var grid = $("#" + grilla);
        grid.jqGrid('restoreRow',lastSelection);
        grid.jqGrid('editRow',id, {keys:true, focusField: 4});
        lastSelection = id;
    }
}

// OBJETOS
// Exception simple
function Exception(message, code) {
	this.code = code ? 0 : code;
	this.message = message;
}

// Error de AJAX
function AjaxException(xhr, responseText, error) {
	this.xhr = xhr;
	this.responseText = responseText;
	this.error = error;
}

// FUNCIONES
// Carga una pagina dentro de un div pasado por parametro
function cargarPagina(div, rutaPagina) {
	$("#" + div).load(rutaPagina, function() {
		console.log("Pagina \"" + rutaPagina + "\" cargada!")
	});
}

// Devuelve la url root de la aplicación (http://localhost:8080/App)
function obtenerRootUrlApp() {
	return location.protocol + "//" + location.host + "/" + location.pathname.split("/")[1];
}

// Devuelve el valor de un parametro del query string dado un nombre (y una url)
function getParameterByName(name, url) {
	if (!url)
		url = window.location.href;
	name = name.replace(/[\[\]]/g, "\\$&");
	var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"), results = regex.exec(url);
	if (!results)
		return null;
	if (!results[2])
		return '';
	return decodeURIComponent(results[2].replace(/\+/g, " "));
}

// Devuelve un array con los ids de todos los inputs no-botones del formulario
// actual
function obtenerListaIdsInput() {
	var fields = $(":input");
	var ids = [];
	$(fields).each(function() {
		if (this.type != "button") {
			ids.push(this.id);
		}
	})
	return ids;
};

// Desabilita un campo dado un nombre
function bloquearCampo(nombre) {
	$("#" + nombre).attr("disabled", "disabled");
}

// Extension de la función "bloquearCampo"
function bloquearCampos(listaIds) {
	if (!listaIds)
		listaIds = obtenerListaIdsInput();
	$.each(listaIds, function() {
		bloquearCampo(this.toString());
	})
}

function llamadaAJAX(url, method, datatype, async, data, isFormData, contentType) {
	var res;
	$.ajax({
		url : url,
		method : method,
		data : data,
		datatype : datatype,
		async : async,
		processData : isFormData ? false : true,
		contentType : isFormData ? false : (contentType ? contentType : ""),
		success : function(response) {
			res = response;
		},
		error : function(jxhr, response, error) {
			res = new AjaxException(jxhr, response, error);
		}
	});
	return res;
}

// Llamada AJAX con metodo GET
function doDELETE(url) {
	return llamadaAJAX(url, "DELETE", "json", false, null, null, null);
}

// Llamada AJAX con metodo GET
function doGET(url) {
	return llamadaAJAX(url, "GET", "json", false, null, null, null);
}

// Llamada AJAX con metodo POST
function doPOST(url, data, isFormData, contentType) {
	return llamadaAJAX(url, "POST", "json", false, data, isFormData, contentType);
}

// Cierra ventana
function cerrar() {
	window.close();
}

// Genera un objeto de tipo FormData (Map<Object, Object>) a partir de una lista
// de campos
function obtenerCamposComoFormData(listaIds) {
	var data = new FormData();
	$.each(listaIds, function() {
		data.append(this.toString(), $("#" + this.toString()).val());
	});
	return data;
}

// Recorre una lista de campos y les asigna el valor correspondiente
function completarCampos(listaIds, itemsQuery) {
	if (listaIds.length != itemsQuery.length)
		throw new Exception("La cantidad de campos del formulario es diferente a la cantidad de items en el query");
	for (var x = 0; x < listaIds.length; x++) {
		$("#" + listaIds[x]).val(getParameterByName(itemsQuery[x]));
	}
}

function abrirPopup(url, options) {
	var popup;
	if (options) {
		popup = window.open(url, options.title, options.mode);
		if (!popup) {
			alert("El navegador esta impidiendo abrir una nueva ventana..");
			return;
		}
		if (options.grid != undefined) {
			if (options.grid.refreshBeforeUnload != undefined) {
				popup.onbeforeunload = function() {
					$("#" + options.grid.name).trigger("reloadGrid");
				}
			}
		}
	} else {
		popup = window.open(url, url, "_blank");
	}

	return popup;
}

function recargarGrillaContraServidor($grilla) {
	$grilla.jqGrid('setGridParam', {
		datatype : 'json'
	}).trigger('reloadGrid');
}

function isIE() {

	var ua = window.navigator.userAgent;
	var msie = ua.indexOf("MSIE ");

	if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./)) {
		return true;
	} else // If another browser, return 0
	{
		return false;
	}
}
function generarJQDatepicker(idCampo) {
	$("#" + idCampo).datepicker();
}

function generarJQDatepicker_old(idCampo) {
	/*
	 * After jquery ui datepicker selection, blur and change events fire before
	 * focus is returned to the input field, handling a quirk from IE browsers
	 */
	$("#" + idCampo).datepicker({
		changeMonth : true,
		changeYear : true,
		showAnim : "fadeIn",
		yearRange : 'c-30:c+30',
		showButtonPanel : true,

		/* fix buggy IE focus functionality */
		fixFocusIE : false,

		/* blur needed to correctly handle placeholder text */
		onSelect : function(dateText, inst) {
			this.fixFocusIE = true;
			$(this).blur().change().focus();
		},
		onClose : function(dateText, inst) {
			this.fixFocusIE = true;
			this.focus();
		},
		beforeShow : function(input, inst) {
			// var result = $.browser.msie ? !this.fixFocusIE : true;
			var result = Modernizr.ie8compat ? !this.fixFocusIE : true;
			this.fixFocusIE = false;
			return result;
		}
	});
}

function restringirCaracteres(e, tipo) {
	if (tipo == "numeros") {
		var code = (e.which) ? e.which : e.keyCode;
		// var especiales = "8-9-16-35-36-37-39-45-46-116";
		var especiales = [ 8, 9, 16, 35, 36, 37, 39, 45, 46, 116 ];

		var tecla_especial = false
		for ( var i in especiales) {
			if (code == especiales[i]) {
				tecla_especial = true;
				break;
			}
		}

		console.log("code = " + code);
		console.log("tecla_especial = " + tecla_especial);

		if ((code >= 48 && code <= 57) || tecla_especial) {
			return true;
		} else {
			return false;
		}
	} else if (tipo == "letras") {
		key = e.keyCode || e.which;
		tecla = String.fromCharCode(key).toLowerCase();
		letras = " �����abcdefghijklmn�opqrstuvwxyz";
		especiales = "8-9-37-39-46";

		tecla_especial = false
		for ( var i in especiales) {
			if (key == especiales[i]) {
				tecla_especial = true;
				break;
			}
		}

		if (letras.indexOf(tecla) == -1 && !tecla_especial) {
			return false;
		}
	}
}

/**
 * Funcion para manejar respuesta.
 * Ver objeto "ActionResult".
 * @param response
 * @returns {Array}
 */
function manejarRespuestaStruts2(response) {
	var json = response.responseJSON != undefined ? response.responseJSON : response;
	var actionResult = json.actionResult;
	if (actionResult) {
		response.status = actionResult.status;
		response.statusText = actionResult.message;
	}
	if (response.status == 200) {
		recargarGrillaContraServidor($('#' + grilla));
		return [ true, response.statusText, "campo2" ];
	} else {
		return [ false, response.statusText, "campo2" ];
	}
}