/**
 * jqGrid Spanish Translation
 * Traduccion jqGrid en Español por Yamil Bracho
 * Traduccion corregida y ampliada por Faserline, S.L. 
 * http://www.faserline.com
 * Traduccion corregida y ampliada por Fernan Castro Asensio
 * Dual licensed under the MIT and GPL licenses:
 * http://www.opensource.org/licenses/mit-license.php
 * http://www.gnu.org/licenses/gpl.html
**/
/*global jQuery, define */
(function( factory ) {
	'use strict';
	if ( typeof define === 'function' && define.amd ) {
		// AMD. Register as an anonymous module.
		define([
			'jquery',
			'../grid.base'
		], factory );
	} else {
		// Browser globals
		factory( jQuery );
	}
}(function( $ ) {

$.jgrid = $.jgrid || {};
if(!$.jgrid.hasOwnProperty('regional')) {
	$.jgrid.regional = [];
}
$.jgrid.regional['es'] = {
	defaults : {
		recordtext: 'Mostrando {0} - {1} de {2}',
	    emptyrecords: 'Sin registros que mostrar',
		loadtext: 'Cargando...',
		savetext: 'Guardando...',
		pgtext : 'Pagina {0} de {1}',
		pgfirst : 'Primera Pagina',
		pglast : 'ultima Pagina',
		pgnext : 'Siguiente Pagina',
		pgprev : 'Anterior Pagina',
		pgrecs : 'Registros por Pagina',
		showhide: 'Alternar Contraer Expandir Grid',
		// mobile
		pagerCaption : 'Grid::Configurar Pagina',
		pageText : 'Pagina:',
		recordPage : 'Registros por Pagina',
		nomorerecs : 'No mas registros...',
		scrollPullup: 'Arrastrar arriba para cargar mas...',
		scrollPulldown : 'Arrastrar arriba para refrescar...',
		scrollRefresh : 'Soltar para refrescar...'		
	},
	search : {
	    caption: 'Busqueda...',
	    Find: 'Buscar',
	    Reset: 'Limpiar',
	    odata: [{ oper:'eq', text:'igual '},{ oper:'ne', text:'no igual a'},{ oper:'lt', text:'menor que'},{ oper:'le', text:'menor o igual que'},{ oper:'gt', text:'mayor que'},{ oper:'ge', text:'mayor o igual a'},{ oper:'bw', text:'empiece por'},{ oper:'bn', text:'no empiece por'},{ oper:'in', text:'esta en'},{ oper:'ni', text:'no esta en'},{ oper:'ew', text:'termina por'},{ oper:'en', text:'no termina por'},{ oper:'cn', text:'contiene'},{ oper:'nc', text:'no contiene'},{ oper:'nu', text:'is null'},{ oper:'nn', text:'is not null'}, {oper:'bt', text:'between'}],
	    groupOps: [	{ op: 'AND', text: 'todo' },	{ op: 'OR',  text: 'cualquier' }	],
		operandTitle : 'Click para seleccionar la operacion de busqueda.',
		resetTitle : 'Resetear valor de Busqueda'
	},
	edit : {
	    addCaption: 'Agregar registro',
	    editCaption: 'Modificar registro',
	    bSubmit: 'Guardar',
	    bCancel: 'Cancelar',
		bClose: 'Cerrar',
		saveData: 'Se han modificado los datos, ¿guardar cambios?',
		bYes : 'Si',
		bNo : 'No',
		bExit : 'Cancelar',
	    msg: {
	        required:'Campo obligatorio',
	        number:'Introduzca un numero',
	        minValue:'El valor debe ser mayor o igual a ',
	        maxValue:'El valor debe ser menor o igual a ',
	        email: 'no es una direccion de correo valida',
	        integer: 'Introduzca un valor entero',
			date: 'Introduza una fecha correcta ',
			url: 'no es una URL valida. Prefijo requerido ("http://" or "https://")',
			nodefined : ' no esta definido.',
			novalue : ' valor de retorno es requerido.',
			customarray : 'La funcion personalizada debe devolver un array.',
			customfcheck : 'La funcion personalizada debe estar presente en el caso de validacion personalizada.'
		}
	},
	view : {
	    caption: 'Consultar registro',
	    bClose: 'Cerrar'
	},
	del : {
	    caption: 'Eliminar',
	    msg: 'Desea eliminar los registros seleccionados?',
	    bSubmit: 'Eliminar',
	    bCancel: 'Cancelar'
	},
	nav : {
		edittext: ' ',
	    edittitle: 'Modificar fila seleccionada',
		addtext:' ',
	    addtitle: 'Agregar nueva fila',
	    deltext: ' ',
	    deltitle: 'Eliminar fila seleccionada',
	    searchtext: ' ',
	    searchtitle: 'Buscar informacion',
	    refreshtext: '',
	    refreshtitle: 'Recargar datos',
	    alertcap: 'Aviso',
	    alerttext: 'Seleccione una fila',
		viewtext: '',
		viewtitle: 'Ver fila seleccionada',
		savetext: '',
		savetitle: 'Guardar fila',
		canceltext: '',
		canceltitle : 'Cancelar edicion de fila',
		selectcaption : 'Actions...'
	},
	col : {
	    caption: 'Mostrar/ocultar columnas',
	    bSubmit: 'Enviar',
	    bCancel: 'Cancelar'	
	},
	errors : {
		errcap : 'Error',
		nourl : 'No se ha especificado una URL',
		norecords: 'No hay datos para procesar',
	    model : 'Las columnas de nombres son diferentes de las columnas de modelo'
	},
	formatter : {
		integer : {thousandsSeparator: '.', defaultValue: '0'},
		number : {decimalSeparator:',', thousandsSeparator: '.', decimalPlaces: 2, defaultValue: '0,00'},
		currency : {decimalSeparator:',', thousandsSeparator: '.', decimalPlaces: 2, prefix: '', suffix:'', defaultValue: '0,00'},
		date : {
			dayNames:   [
				'Do', 'Lu', 'Ma', 'Mi', 'Ju', 'Vi', 'Sa',
				'Domingo', 'Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sabado'
			],
			monthNames: [
				'Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic',
				'Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre'
			],
			AmPm : ['am','pm','AM','PM'],
			S: function (j) {return j < 11 || j > 13 ? ['st', 'nd', 'rd', 'th'][Math.min((j - 1) % 10, 3)] : 'th'},
			srcformat: 'Y-m-d',
			newformat: 'd-m-Y',
			parseRe : /[#%\\\/:_;.,\t\s-]/,
			masks : {
	            ISO8601Long:'Y-m-d H:i:s',
	            ISO8601Short:'Y-m-d',
	            ShortDate: 'n/j/Y',
	            LongDate: 'l, F d, Y',
	            FullDateTime: 'l, F d, Y g:i:s A',
	            MonthDay: 'F d',
	            ShortTime: 'g:i A',
	            LongTime: 'g:i:s A',
	            SortableDateTime: 'Y-m-d\\TH:i:s',
	            UniversalSortableDateTime: 'Y-m-d H:i:sO',
	            YearMonth: 'F, Y'
	        },
	        reformatAfterEdit : false,
			userLocalTime : false
		},
		baseLinkUrl: '',
		showAction: '',
	    target: '',
	    checkbox : {disabled:true},
		idName : 'id'
	},
	colmenu : {
		sortasc : 'Ordenamiento ascendente',
		sortdesc : 'Ordenamiento descendente',
		columns : 'Columnas',
		filter : 'Filtro',
		grouping : 'Agrupado por',
		ungrouping : 'Desagrupado',
		searchTitle : 'Obtener elementos con el valor::', 
		freeze : 'Bloqueado',
		unfreeze : 'Desbloqueado',
		reorder : 'Mover para reordenar'
	}
};
}));
