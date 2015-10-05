/*
 * Licensed to STRATIO (C) under one or more contributor license agreements.
 * See the NOTICE file distributed with this work for additional information
 * regarding copyright ownership.  The STRATIO (C) licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
/* Inicialización en español para la extensión 'UI date picker' para jQuery. */
/* Traducido por Vester (xvester@gmail.com). */
(function( factory ) {
	if ( typeof define === "function" && define.amd ) {

		// AMD. Register as an anonymous module.
		define([ "../datepicker" ], factory );
	} else {

		// Browser globals
		factory( jQuery.datepicker );
	}
}(function( datepicker ) {

datepicker.regional['es'] = {
	closeText: 'Cerrar',
	prevText: '&#x3C;Ant',
	nextText: 'Sig&#x3E;',
	currentText: 'Hoy',
	monthNames: ['enero','febrero','marzo','abril','mayo','junio',
	'julio','agosto','septiembre','octubre','noviembre','diciembre'],
	monthNamesShort: ['ene','feb','mar','abr','may','jun',
	'jul','ago','sep','oct','nov','dic'],
	dayNames: ['domingo','lunes','martes','miércoles','jueves','viernes','sábado'],
	dayNamesShort: ['dom','lun','mar','mié','jue','vie','sáb'],
	dayNamesMin: ['D','L','M','X','J','V','S'],
	weekHeader: 'Sm',
	dateFormat: 'dd/mm/yy',
	firstDay: 1,
	isRTL: false,
	showMonthAfterYear: false,
	yearSuffix: ''};
datepicker.setDefaults(datepicker.regional['es']);

return datepicker.regional['es'];

}));
