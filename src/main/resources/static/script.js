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
    <div class="formulario" id="formulario">
    </div>
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
    <h1>Formulario benef</h1>
    `
}
function formularioConta() {
    var formulario = document.getElementById("formulario");
    formulario.innerHTML = ""
    formulario.innerHTML = `
    <h1>Formulario Conta</h1>
    `
}
function formularioTipo() {
    var formulario = document.getElementById("formulario");
    formulario.innerHTML = ""
    formulario.innerHTML = `
    <h1>Formulario Tipo</h1>
    `
}