const usuarioCorrente = localStorage.getItem("usuarioCorrente");
const LOGIN_URL = "login.html";
function loginTeste(){
		if (usuarioCorrente==null) {
        window.location.href = LOGIN_URL;
      }
		else{
			window.location.href = "login_table.html";
			initPage();
		}
	}
	$("#login-link").click(loginTeste);