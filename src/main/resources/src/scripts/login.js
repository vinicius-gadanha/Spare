//
//
// Disciplina: Trabalho Interdisciplinar - AplicaÃ§Ãµes Web
// Professor: Rommel Vieira Carneiro (rommelcarneiro@gmail.com)
//
// CÃ³digo LoginApp utilizado como exemplo para alunos de primeiro perÃ­odo

// PÃ¡gina inicial de Login
const LOGIN_URL = "login.html";

// Objeto para o banco de dados de usuÃ¡rios baseado em JSON
let db_usuarios = {};

// Objeto para o usuÃ¡rio corrente
let usuarioCorrente = {};

// funÃ§Ã£o para gerar cÃ³digos randÃ´micos a serem utilizados como cÃ³digo de usuÃ¡rio
// Fonte: https://stackoverflow.com/questions/105034/how-to-create-guid-uuid
function generateUUID() {
  // Public Domain/MIT
  let d = new Date().getTime(); //Timestamp
  let d2 = (performance && performance.now && performance.now() * 1000) || 0; //Time in microseconds since page-load or 0 if unsupported
  return "xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx".replace(/[xy]/g, function (c) {
    let r = Math.random() * 16; //random number between 0 and 16
    if (d > 0) {
      //Use timestamp until depleted
      r = (d + r) % 16 | 0;
      d = Math.floor(d / 16);
    } else {
      //Use microseconds since page-load if supported
      r = (d2 + r) % 16 | 0;
      d2 = Math.floor(d2 / 16);
    }
    return (c === "x" ? r : (r & 0x3) | 0x8).toString(16);
  });
}

// Dados de usuÃ¡rios para serem utilizados como carga inicial
const dadosIniciais = {
  usuarios: [
    {
      id: generateUUID(),
      login: "admin",
      senha: "123",
      nome: "Administrador do Sistema",
      sobrenome: "abcd",
      email: "admin@abc.com",
    },
    {
      id: generateUUID(),
      login: "user",
      senha: "123",
      nome: "Usuario Comum",
      sobrenome: "abcd",
      email: "user@abc.com",
    },
    {
      id: generateUUID(),
      login: "ViniciusGC",
      senha: "123",
      nome: "VinÃ­cius",
      sobrenome: "Gadanha",
      email: "viniciusgadanha@hotmail.com",
    },
  ],
};

// Inicializa o usuarioCorrente e banco de dados de usuÃ¡rios da aplicaÃ§Ã£o de Login
function initLoginApp() {
  // PARTE 1 - INICIALIZA USUARIOCORRENTE A PARTIR DE DADOS NO LOCAL STORAGE, CASO EXISTA
  usuarioCorrenteJSON = localStorage.getItem("usuarioCorrente");
  if (usuarioCorrenteJSON) {
    usuarioCorrente = JSON.parse(usuarioCorrenteJSON);
  }

  // PARTE 2 - INICIALIZA BANCO DE DADOS DE USUÃ�RIOS
  // Obtem a string JSON com os dados de usuÃ¡rios a partir do localStorage
  let usuariosJSON = localStorage.getItem("db_usuarios");

  // Verifica se existem dados jÃ¡ armazenados no localStorage
  if (!usuariosJSON) {
    // Se NÃƒO hÃ¡ dados no localStorage

    // Copia os dados iniciais para o banco de dados
    db_usuarios = dadosIniciais;

    // Salva os dados iniciais no local Storage convertendo-os para string antes
    localStorage.setItem("db_usuarios", JSON.stringify(dadosIniciais));
  } else {
    // Se hÃ¡ dados no localStorage

    // Converte a string JSON em objeto colocando no banco de dados baseado em JSON
    db_usuarios = JSON.parse(usuariosJSON);
  }
}

// Verifica se o login do usuÃ¡rio estÃ¡ ok e, se positivo, direciona para a pÃ¡gina inicial
function loginUser(login, senha) {
  // Verifica todos os itens do banco de dados de usuarios
  // para localizar o usuÃ¡rio informado no formulario de login
	$.ajax({
		url: 'http://localhost:6789/usuario/login',
		type: "POST",
		data: { nomeusuario: login, senha: senha }
    }).done(function(data) { 
	localStorage.setItem("usuarioCorrente", data);
	window.location.href="login_table.html";
    }).fail(function() {
    alert("Usuário ou senha incorretos");
	location.reload();
    })
}

// Apaga os dados do usuÃ¡rio corrente no sessionStorage
function logoutUser() {
  localStorage.removeItem("usuarioCorrente");
  window.location = LOGIN_URL;
}

function addUser(nome, sobrenome, login, senha, email) {
  // Cria um objeto de usuario para o novo usuario
  let newId = generateUUID();
  let usuario = {
    id: newId,
    login: login,
    senha: senha,
    nome: nome,
    sobrenome: sobrenome,
    email: email,
  };

  // Inclui o novo usuario no banco de dados baseado em JSON
  db_usuarios.usuarios.push(usuario);

  // Salva o novo banco de dados com o novo usuÃ¡rio no localStorage
  localStorage.setItem("db_usuarios", JSON.stringify(db_usuarios));
}

function setUserPass() {}

// Inicializa as estruturas utilizadas pelo LoginApp
initLoginApp();

//Scripts login_table
    
      // Verifica se o usuÃ¡rio jÃ¡ esta logado e se negativo, redireciona para tela de login
      if (usuarioCorrente==null) {
        window.location.href = LOGIN_URL;
      }
		
      function exibeUsuarios() {
        $.ajax({
        url: 'http://localhost:6789/usuario',
        type: "GET",
        dataType: "json"
	    }).done(function (data) {
		let listausuarios = "";
		for (let i=0; i<data.length; i++){
		listausuarios += `<tr><td scope="row">${data[i].nome}</td><td scope="row">${data[i].sobrenome}</td><td>${data[i].nomeusuario}</td><td>${data[i].email}</td></tr>`;
		}
		$("#table-usuarios").html(listausuarios);
	    })
      }

      function initPage() {
        // Associa a funÃ§Ã£o de logout ao botÃ£o
        document
          .getElementById("btn_logout")
          .addEventListener("click", logoutUser);

        // Informa o nome do usuÃ¡rio logado
        document.getElementById("nomeUsuario").innerHTML = usuarioCorrente.nome;

        // Lista os usuÃ¡rios
        exibeUsuarios();
      }
	
