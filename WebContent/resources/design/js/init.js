/**
 * Construtor
 * 
 * Quando o documento estiver carregado Esta funcção será executada
 * automaticamente.
 * 
 * Define características gerais da aplicação
 * 
 */
$(document).ready(function() {

	// Function responsável por expandir e recolher os submenus do menu vertical
	MenuVerticalToggle();
	// Define cantos arredondados para Boxes (divs)
	SetCorner();
	// Monta tabs com a primeira tab setada como ativa
	MontaAbas();
	// Monta os accordions
	MontaAccordion()
	// Monta grid
	MontaGrid();
	// Monta Tabela da Grid
	MontaDataTable();
	// Monta diálogo
	Dialog(); 
	// Ajustes visuais
	ajustesVisuais();
	$(function() {
		$('.sonums').live('keypress', function(event) {
		var tecla = (window.event) ? event.keyCode : event.which;
		if ((tecla > 47 && tecla < 58)) return true;
		else {
			if (tecla != 8 && tecla != 0) return false;
			else return true;
		}
		});
	});
});

function MontaAccordion(){
	$(".accordion_data").accordion({ icons: "", heightStyle: "content" });
	// Retirar tabulação de accordions
	$(".ui-accordion-header").attr("tabindex", "");
}

function escapeClientId(a){
    return"#"+a.replace(/:/g,"\\:");
}

/**
 * Function responsável por expandir e recolher os submenus do menu vertical
 * 
 */
function MenuVerticalToggle() {
	$('#MenuVertical > li a').click(function(e) {
		var _thisClass = $(this).parent().find('ul').attr('class');
		$(this).parent().parent().find('ul').each(function() {
			$(this).attr('class', '');
			$(this).hide();
		});
		if (_thisClass != 'display-block') {
			$(this).parent().find('ul').attr('class', 'display-block');
		}
	});
}

/**
 * function genérica para limpar todos os campos do formulário
 */
function FormReset() {
	$('form').find(':input').each(function() {
		switch (this.type) {
		case 'password':
		case 'select-multiple':
		case 'select-one':
		case 'text':
		case 'textarea':
			$(this).val('');
			break;
		case 'checkbox':
		case 'radio':
			this.checked = false;
		}
	});
}

/**
 * Atribui o componente de Abas ou Tabs com a primeira aba selecionada
 */
function MontaAbas() {
	$("#tabs").tabs();

}

/**
 * troca a tab ativa passando como parâmetro o index da aba sempre começando com
 * Zero(0)
 */
function TabSwap(index) {
	$("[id*=tabs]").tabs("option", "active", index);

}

/**
 * Atribui o componente DataTable
 */
function MontaGrid() { 
	$('.grid_list').on( 'draw.dt', function () {
		  $('.grid_list > thead > tr > th').css('border-bottom' , 'none');

		  $('.grid_list > thead > tr > th:nth-of-type(odd)')
		  .addClass('gradeC');
		  $('.grid_list  > thead > tr > th:nth-of-type(even)')
		  .addClass('gradeD');

		  $('.grid_list > tbody > tr:nth-child(odd) > td:nth-of-type(odd)')
		  .removeClass('gradeC').addClass('gradeA');
		  $('.grid_list > tbody > tr:nth-child(odd) > td:nth-of-type(even)')
		  .removeClass('gradeD').addClass('gradeB');  

		  $('.grid_list > tbody > tr:nth-child(even) > td:nth-of-type(odd)')
		  .removeClass('gradeA').addClass('gradeC');
		  $('.grid_list > tbody > tr:nth-child(even) > td:nth-of-type(even)')
		  .removeClass('gradeB').addClass('gradeD');
		  
		$('.paging_full_numbers a.paginate_button.current').each(function(){
			  this.style.setProperty('background-color', '#f63');
			  this.style.setProperty('color', '#fff', 'important');
		});
		
		$('.paging_full_numbers .paginate_button').each(function(){
			  this.style.setProperty('font-weight', 'normal', 'important');
		});
			
	});
}

function MontaDataTable() {
	
	$('.grid_list').DataTable({
		"jQueryUI" : false,
		"pagingType" : "full_numbers",
		"stripeClasses" :[],
		"order": [],
		"retrieve":true, 
		"language": {
            "decimal": ",",
            "thousands": ".",
            "emptyTable":     "Não há dados disponíveis na tabela",
            "info":           "Exibindo _START_ até _END_ de _TOTAL_ registros",
            "infoEmpty":      "Exibindo 0 até 0 de 0 atividades",
            "infoFiltered":   "",
            "infoPostFix":    "",
            "lengthMenu":     "Exibir _MENU_ registros",
            "loadingRecords": "Carregando...",
            "processing":     "Processando...",
            "search":         "Pesquisar:",
            "zeroRecords":    "Nenhum registro correspondente encontrado",
            "paginate": {
                "first":      "Primeiro",
                "last":       "Último",
                "next":       "Próximo",
                "previous":   "Anterior"
            },
            "aria": {
                "sortAscending":  ": Clique para ordernar a coluna de foma ascendente",
                "sortDescending": ": Clique para ordernar a coluna de foma descendente"
            }
		}
	});	
	
}
/**
 * Atribui o componente de bordas arredondadas
 */
function SetCorner() {
	$("#div-msg-form").corner("keep");
	$("#div-msg-informacao").corner("keep");
	$("#div-msg-sucesso").corner("keep");
}

function Dialog() {
	$(".dialog").dialog({
		modal : true,
		height : 170,
		width : 400,
		resizable : false,
		draggable : false,
		autoOpen: false
	});
}

function ajustesVisuais() {
	if($.browser.mozilla == undefined){
		$('.nomelogo').css('font-size', '10px');
		$('.nomelogo').css('padding-top', '8px');
	}
}