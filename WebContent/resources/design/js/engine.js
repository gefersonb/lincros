$.extend({
    password: function(length) {
        var iteration = 0;
        var password = "";
        var randomNumber;
        while (iteration < length) {
            randomNumber = (Math.floor((Math.random() * 100)) % 94) + 33;
            iteration++;
            password += String.fromCharCode(randomNumber);
        }
        return password;
    },
    urlParam: function(name) {
        var results = new RegExp('[\\?&]' + name + '=([^&#]*)').exec(window.location.href);
        return (results === null ? null : results[1] || 0);
    },
    watch: function() {
        if ($('#watch').val().length !== 0) {
            location.reload(true);
        }
    }
});

$(document).ready(function() {

    // Previne o cache em caso de "history.back"
    $(document).bind('pageshow', $.watch);
    window.onpageshow = $.watch;    
    
    getCookie("LoginAD");
        
    var hashObj = new jsSHA($.password(20), "ASCII");
    var password = hashObj.getHash("SHA-512", "HEX");
    var cryptoServlet = $('#contextPath').val() + "/secure";

    $.jCryption.authenticate(password, cryptoServlet + "?keys", cryptoServlet + "?handshake",
        function(AESKey) {
            $("#login,#senha,#entrar").attr("disabled", false);                
        },
        function() {
            $("#mensagem").html('Serviço de autenticação indisponí­vel.');
        }
    );

    $('#entrar').click(function() {
        if ($('#reinfHabilitado').val() == 'false' && ($('#login').val().length !== 7 || $('#senha').val().length < 3)) {
            $('#mensagem').html('Matrícula ou senha inválida.');
            $("#login").val('');
            $('#senha').val('');        
        } else {
            $("#login,#senha,#entrar").attr("disabled", true);        
            var login = $.jCryption.encrypt($("#login").val(), password);
            var senha = $.jCryption.encrypt($("#senha").val(), password);
            setCookie("LoginAD",$("#login").val(),1);
            // Criptografa dados do formulário
            $('#login-crypto').val(login);
            $('#senha-crypto').val(senha);
            // Previne o cache em caso de "history.back"                    
            $('#watch').val('S');
            // Submete os dados do login
            $('#logon').click();
        }
    });

});

function setCookie(cookie_nome, cookie_valor, expira_em_dias) {
	var cookie_expira = "";
	if (expira_em_dias !== null) {
		var expira = new Date();
		expira.setTime(expira.getTime() + 1000*60*60*24*parseInt(expira_em_dias));
		cookie_expira = "; expires=" + expira.toGMTString();
	}
	document.cookie = escape(cookie_nome) + "=" + escape(cookie_valor) + cookie_expira;
    return true;
}

function getCookie(cookie_nome) {
	if (!document.cookie.match(eval("/" + escape(cookie_nome) + "=/"))) {
	    return;
	} else {
		var valor_cookie = unescape(document.cookie.replace(eval("/^.*?" + escape(cookie_nome) + "=([^\\s;]*).*$/"), "$1"));
	   if (cookie_nome === "LoginAD"){
		document.getElementById('login').value = valor_cookie;
	   }
	}
    return;
}

function verificaEnter(e){
    if(document.layers){
	document.captureEvents(Event.KEYPRESS);
    }
    var key = (window.event) ? window.event.keyCode : e.which;
    if(key === 13){
        $('#entrar').click();
    }else{
        return;
    }
}

function limpar(){
	$('#login').val('');
    $('#senha').val('');
    $("#mensagem").html('');
}