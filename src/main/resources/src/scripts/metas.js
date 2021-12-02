let metasStorage = [];

const registroMetaFormElements = {
    titulo: document.getElementById("meta-titulo"),
    valor: document.getElementById("meta-valor"),
    salvarBtn: document.getElementById("salvar-meta"),
  };
  
  const editarMetaFormElements = {
    valorAtual: document.getElementById("editar-valor-atual"),
    valor: document.getElementById("editar-meta-valor"),
    salvarBtn: document.getElementById("salvar-editar-meta"),
  };

  const generateUUID = () => {
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
  };

  const adicionarMetaLocalStorage = () => {
	const usuarioCorrente = JSON.parse(localStorage.getItem("usuarioCorrente"));
    const novaMeta = {
      id: "",
      titulo: "",
      valor: "",
      valorAtual: "0",
    };
  
    if (
      registroMetaFormElements.titulo.value === "" &&
      registroMetaFormElements.valor.value === ""
    )
      return;
	
	novaMeta.id=generateUUID;
    novaMeta.titulo = registroMetaFormElements.titulo.value;
    novaMeta.valor = registroMetaFormElements.valor.value;
    metasStorage.unshift(novaMeta);
    
	
	if (localStorage.getItem("usuarioCorrente")!=null){
	$.ajax({
	  url: 'http://localhost:6789/meta',
	  type: "POST",
	  data: { titulo: novaMeta.titulo, valor: novaMeta.valor, emailusuario: usuarioCorrente.email}
	  })
	}
	localStorage.setItem("metas", JSON.stringify(metasStorage));
    novaMeta.id = "";
    novaMeta.titulo = "";
    novaMeta.valor = "";
  
    location.reload();
  };

  const excluirMetasLocalStorage = (index) => {
    metasStorage.splice(index, 1);
  
    localStorage.setItem("metas", JSON.stringify(metasStorage));
  
    const tbodyHtml = document.querySelector(`tr[name="${index}-metas"]`);
    tbodyHtml.remove();
  
    clearInputs();
    location.reload();
  };

  const editarMetasLocalStorage = () => {
    const metaIndex = localStorage.getItem("metaIndex");
    const meta = metasStorage[metaIndex];
  
    meta.valorAtual =
      parseFloat(meta.valorAtual) +
      parseFloat(editarMetaFormElements.valorAtual.value);
    meta.valor = parseFloat(editarMetaFormElements.valor.value);
  
    metasStorage[metaIndex] = meta;
  
    localStorage.setItem("metas", JSON.stringify(metasStorage));
  
    clearInputs();
    location.reload();
  };
  
  const openEditarMetasModal = (index) => {
    localStorage.setItem("metaIndex", index);
    const meta = metasStorage[index];
  
    editarMetaFormElements.valor.value = meta.valor;
  };

  const clearInputs = () => {
    registroMetaFormElements.titulo.value = "";
    registroMetaFormElements.valor.value = "";
  
    editarMetaFormElements.valorAtual.value = "";
    editarMetaFormElements.valor.value = "";
  };

  const adicionarMetasHtml = () => {
    const tableHtml = document.querySelector("#tableMetas");
    let tbodyHtml;
    if (document.querySelector("#tbodyMetas") === null) {
      tbodyHtml = document.createElement("tbody");
      tbodyHtml.setAttribute("id", "tbodyMetas");
    } else {
      tbodyHtml = document.querySelector("#tbodyMetas");
    }
    tableHtml.appendChild(tbodyHtml);
  
    metasStorage.forEach((meta, index) => {
      if (document.querySelector(`tr[name="${index}-metas"]`)) return;
      const tableRow = document.createElement("tr");
      tableRow.setAttribute("name", `${index}-metas`);
  
      const tableDataTitulo = document.createElement("td");
      tableDataTitulo.textContent = meta.titulo;
  
      const tableDataProgresso = document.createElement("td");
      tableDataProgresso.textContent = `R$${meta.valorAtual}/R$${meta.valor}`;
  
      //data-toggle="modal" data-target="#myModal"
      const tableDataEditar = document.createElement("td");
      const editarIcon = document.createElement("img");
      editarIcon.setAttribute("src", "./assets/pencil.png");
      editarIcon.setAttribute("class", "img_lixo");
      editarIcon.setAttribute("data-toggle", "modal");
      editarIcon.setAttribute("data-target", "#editarMetaModal");
      editarIcon.setAttribute("onclick", `openEditarMetasModal(${index})`);
      tableDataEditar.appendChild(editarIcon);
  
      const tableDataDeletar = document.createElement("td");
      const deletarIcon = document.createElement("img");
      deletarIcon.setAttribute("src", "./assets/lixo.png");
      deletarIcon.setAttribute("class", "img_lixo");
      deletarIcon.setAttribute("onclick", `excluirMetasLocalStorage(${index})`);
      tableDataDeletar.appendChild(deletarIcon);
  
      tableRow.appendChild(tableDataTitulo);
      tableRow.appendChild(tableDataProgresso);
      tableRow.appendChild(tableDataEditar);
      tableRow.appendChild(tableDataDeletar);
  
      tbodyHtml.appendChild(tableRow);
  
      clearInputs();
    });
  };
  const criarLocalStorage = () => {
    const isHistoricoStorageCreated = localStorage.getItem("historico");
    const isMetasStorageCreated = localStorage.getItem("metas");
    if (!isMetasStorageCreated) {
      localStorage.setItem("metas", JSON.stringify([]));
    } else {
      metasStorage = JSON.parse(isMetasStorageCreated);
      adicionarMetasHtml();
    }
  };
  
  criarLocalStorage();