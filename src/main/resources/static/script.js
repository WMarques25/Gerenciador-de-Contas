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

    criarPagina(btnNav);
}

function criarPagina(btnNav){
    var txt = btnNav.innerText;
    alert(txt);
    //TODO pegar atributo do botao para puxar a pagina a ser apresentada
    
}