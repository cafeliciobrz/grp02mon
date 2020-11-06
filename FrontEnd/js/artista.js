function montartabelas(lista){
    var tabela = 
    "<table border='1' align='center' width='80%' cellspacing='2'>" +
    "<tr>" + 
    "<th>Musica</th><th>Cadastro</th>" +
    "</th>";

    for (cont=0; cont < lista.length; cont++){
        tabela+= "<tr>"+
        "<td>" + lista[cont].titulo + "</td>" +
        "<td>" + lista[cont].cadastro + "</td></tr>";
    }

    tabela += "</table>";
    document.getElementById("resultado").innerHTML = tabela;
}

function filtrar(){
    fetch("http://localhost:8080/artista/" + document.getElementById("cmbartistas").value)
    .then(res => res.json())
    .then(res => montartabelas(res.musicas));
}

function preencherartistas(lista){
    var dropbox = "";
    for(cont=0; cont<lista.length; cont++){
        dropbox+=
        "<option value='" + lista[cont].id + "'>" + lista[cont].nome + "</option>";
    }

    document.getElementById("cmbartistas").innerHTML = dropbox;
}

function carregarartistas(){
    var usuario = localStorage.getItem("usuariologado");
    if (usuario == null){
        window.location = "index.html";
    } else {
        fetch("http://localhost:8080/todosartistas")
        .then(res => res.json())
        .then(res => preencherartistas(res));
    }
}