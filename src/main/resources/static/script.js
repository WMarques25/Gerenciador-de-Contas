function toggleSidebar() {
    var sidebar = document.getElementById('sidebar');
    if (sidebar.style.left === "0px") {
        sidebar.style.left = "-250px";
    } else {
        sidebar.style.left = "0px";
    }
}

function btnNav(btnNav){
    var btns = document.querySelectorAll(".navegacao");
    btns.forEach(function(btn){
        btn.classList.remove("selecionado");
    });
    btnNav.classList.add("selecionado");

    criarPagina(btnNav.getAttribute("pag"));
    toggleSidebar();
}

function criarPagina(pagina){
    var mainContent = document.getElementById("content");
    switch (pagina) {
        case "cadastrar":
            pagCadastrar(mainContent);
            break;
        
        case "contas":
            //TODO listar contas
            break;
        
        case "parcelas":
            //TODO listar parcelas
            break;

        case "relatorios":
            //TODO pag relatorios
            break;
    
        default:
            break;
    }

}

function pagCadastrar(main) {
    main.innerHTML = `
    <h1>Cadastrar</h1>
    <label for="cadastro-drop"></label>
    <div style="text-align: center;">
        <select class="select-cadastro" id="cadastro-drop" name="cadastro-drop" onchange="selecionarCadastro(this)">
            <option value="">Selecione</option>
            <option value="beneficiario">Beneficiario</option>
            <option value="conta">Conta</option>
            <option value="tipo">Tipo de Conta</option>
        </select>
    </div>
    <form class="formulario" id="formulario">
    </form>
        `;

}

function selecionarCadastro(selecao){
    switch (selecao.value) {
        case "beneficiario":
            formularioBeneficiario();
            break;

        case "conta":
            formularioConta();
            break;

        case "tipo":
            formularioTipo();
            break;
    
        default:
            break;
    }
}

function formularioBeneficiario(){
    var formulario = document.getElementById("formulario");
    formulario.innerHTML = ""
    formulario.innerHTML = `
    <label for="beneficiario">Beneficiário<br></label> 
    <input type="text" id="inputBenef" name="nmBeneficiario"><br><br>
    <label for="beneficiario">Nome Fantasia<br></label> 
    <input type="text" id="inputBenef" name="nmFantasia"><br>
    <button class="enviar" form="postBenef" type="button" onclick="enviarFormulario(this)">Enviar</button>
    `
}
function formularioConta() {
    // TODO Buscar os beneficiarios, id e nmFantasia.
    var benefs;
    var formulario = document.getElementById("formulario");
    formulario.innerHTML = ""
    formulario.innerHTML = `
    <label for="beneficiario">Beneficiário<br></label> 
    <div style="text-align: center;">
        <select class="select-cadastro" id="benef-drop" name="idBeneficiaro">
            <option value="">Selecione</option>
    `
    // TODO Adicionar os beneficiarios | <option value="${id}">${nmFantasia}</option>
    formulario.innerHTML += `
        </select>
    </div>
    <div>
        <div style="display: inline-block; text-align: center; width: 49%;">
        <label for="conta">Tipo de Pagamento<br></label> 
            <select class="select-cadastro" id="pag-drop" onchange="formularioParela(this)" name="tipoPagamento">
                <option value="">Selecione</option>
                <option value="PIX">PIX</option>
                <option value="FATURADO">Faturado</option>
            </select>
        </div>
        <div style="display: inline-block; text-align: center; width: 49%; align: right;">
        <label for="conta">Tipo de Conta<br></label>
            <select class="select-cadastro" id="conta-d
            <br>rop" name="nmTipoConta">
                <option value="">Selecione</option>
    `
    // TODO Adicionar os tipos de conta | <option value="${id}">${nmTipoConta}</option>
    formulario.innerHTML += `
            </select>
        </div>
    </div>
    <div id="formularioParcela">
    </div>
    <br>
    `

    // TODO Tratar envio
    formulario.innerHTML += `
    <button class="enviar" form="postBenef" type="button" onclick="enviarFormulario(this)">Enviar</button>
    `
}

function formularioParela(selecao){
    var parcelas = document.getElementById("formularioParcela");
    if(selecao.value == "FATURADO"){
        parcelas.innerHTML += `
        <label for="qtParcela">Parcelas<br></label>
        <input class="input-num" style="display: flex; margin-left: 7.5%; width: 15%;" type="number" id="qtParcela" min="0" max="48" onchange="nParcelas(this)">
        <div id="nParcelas">
        </div>`
    }
}

function nParcelas(n){
    var nParcela = document.getElementById("nParcelas");
    nParcela.innerHTML = "";
    for(var i = 0; i<n.value;i++){
        nParcela.innerHTML += `
        <h2> AAAA </h2>
        `
    }
}

function formularioTipo() {
    var formulario = document.getElementById("formulario");
    formulario.innerHTML = ""
    formulario.innerHTML = `
    <h1>Formulario Tipo</h1>
    `
}

function enviarFormulario(btnEnviar){
    // Obter referência ao formulário
    var formulario = document.getElementById("formulario");

    // Criar objeto para armazenar os dados do formulário
    var dadosFormulario = {};

    // Iterar sobre os elementos do formulário
    for (var i = 0; i < formulario.elements.length; i++) {
        var elemento = formulario.elements[i];
        if (elemento.name) { // Verificar se o elemento tem um nome
            dadosFormulario[elemento.name] = elemento.value;
            elemento.value = "";
        }
    }

    // Transformar objeto em JSON
    var dadosJSON = JSON.stringify(dadosFormulario);

    axios.post("http://localhost:2508/beneficiarios", dadosJSON, {
        headers: {
            'Content-Type': 'application/json'
        }})
        //.then(response => console.log(response.data))
        .then(response => alert("Cadastro Realizado: \n\nid: "+ 
            response.data.id + "\nBeneficiario: " + response.data.nmBeneficiario + 
            "\nFantasia: " + response.data.nmFantasia))
        .catch(error => console.log("Erro: ", error));

    console.log("Dados do formulário em JSON:", dadosJSON);

}