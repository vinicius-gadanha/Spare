$( "#botao1" ).click(function() {
     $.ajax({
        url: 'http://localhost:6789/produto',
        type: "GET",
        dataType: "json"
    }).done(function (data) {
	let autores = "";
	for (let i=0; i<data.length; i++){
	autores += `<div>
        <p>Codigo: ${data[i].codigo}</p>
        <p>Nome: ${data[i].nome}</p>
        <p>Idade: ${data[i].idade}</p>
        <p>Gênero: ${data[i].genero}</p>
	</div>`;
	}
	$("#autores").html(autores);
    })
});
