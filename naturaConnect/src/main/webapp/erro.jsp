<!DOCTYPE html>
<html lang="pt-BR">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Natura Connect</title>

<!--  Google Fonts -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Rubik:ital,wght@0,300..900;1,300..900&display=swap" rel="stylesheet">

<!-- Minha CSS -->
<link rel="stylesheet" href="./css/cadastro.css">

<!-- Chamada da CSS do BS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">

<!-- BS Icons -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">

<style>
.error-box {
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    padding: 20px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
    z-index: 1001;
    width: 23rem;
    height: auto;
    border-top: 6px solid #F5821F;
    border-radius: 0.7rem;
    background-color: #FEFCFB;
    display: none;
} 

.errorButton {
    display: flex;
    justify-content: center;
}

.error-box button {
    background:#F5821F;
    color: #fff;
    border: none;
    padding: 8px;
    width: 6rem;
    cursor: pointer;
    border-radius: 0.3rem; 
}

.overlay {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(0, 0, 0, 0.5);
    z-index: 1000;
    display: none;
}

#errorMessage {
	font-weight: 600;
	text-align: justify;
    margin-left: 3rem;
    margin-right: 3rem;
    margin-bottom: 1rem;
}

/* Estilo da imagem de erro */
.error-img {
    display: block;
    margin: 0 auto auto auto;
    width: 15rem !important;
}

.circle {
	width: 8rem;
    height: 8rem;
    background-color: #FEFCFB;
    border-radius: 50rem;
    margin: -7rem auto auto;
    display: flex;
    justify-content: center;
    align-items: center;
    position: relative;
    z-index: 10;    
    border: 6px solid #F5821F;
}

.alert {
	width: 7rem;
}

</style>
</head>
<body>
<header>
    <figure>
        <img class="forma" src="./assets/forma.svg" alt="">
    </figure>
</header>
<main class="container-fluid">
    <div class="container-cadastro d-flex justify-content-center align-items-center">
        <div class="ladoLaranja"></div>
        <figure class="d-flex justify-content-center align-items-center">
            <img class="cadastro position-absolute figure-img img-fluid" src="./assets/tela-cadastro.svg">
        </figure>
        <div class="ladoBranco d-flex justify-content-center align-items-center">
            <div class="conteiner-ladoBranco d-flex justify-content-center align-items-center flex-column">
                <figure>
                        <img class="logo" src="./assets/logo.svg">
                    </figure>
                    <div class="btn-group btn-toggle"> 
                        <button class="btn btn-default shadow mb-3 mt-1 rounded cliente">CLIENTE</button>
                        <button class="btn shadow mb-3 mt-1 rounded consultora active ">CONSULTORA</button>
                    </div>
                    <form action="Cadastro" method="post" class="d-flex flex-column justify-content-center align-items-center form">
                        <div class="formCadastro row">
                            <!-- Campo de entrada oculto -->
                            <input type="hidden" id="tipoUsuario" name="tipoUsuario" value="">
                            <div class="mb-2 col">
                                <input type="text" name="nome" class="form-control col" id="nome" placeholder="NOME COMPLETO">
                            </div>
                            <div class="mb-2 col">
                                <input type="text" name="nomesocial" class="form-control" id="nomesocial" placeholder="NOME SOCIAL">
                            </div>
                        </div>
                        <div class="formCadastro row">
                            <div class="mb-2 col cliente-only" style="display: none;">
                                <input type="text" name="cpf" class="form-control col" id="cpf" placeholder="CPF" autocomplete="off" data-js="cpf">
                            </div>
                            <div class="mb-2 col consultora-only" style="display: none;">
                                <input type="text" name="codigoconsultora" class="form-control col" id="codigoconsultora" placeholder="C DIGO CONSULTORA">
                            </div>
                            <div class="mb-2 col cepcliente-only">
                                <input type="text" name="cepcli" class="form-control" id="cepcli" placeholder="CEP" autocomplete="off" data-js="cep">
                            </div>
                        </div>
                        <div class="formCadastro row">
                            <div class="mb-2 col cepconsultora-only">
                                <input type="text" name="cepcon" class="form-control" id="cepcon" placeholder="CEP" autocomplete="off" data-js="cep">
                            </div>
                            <div class="mb-2 col ruacli">
                                <input type="text" name="ruacli" class="form-control col" id="ruacli" placeholder="RUA">
                            </div>
                            <div class="mb-2 col ruacon">
                                <input type="text" name="ruacon" class="form-control col" id="ruacon" placeholder="RUA">
                            </div>
                            <div class="mb-2 col">
                                <input type="number" name="num" class="form-control" id="num" placeholder="N " min="1" max="10000">
                            </div>
                        </div>
                        <div class="formCadastro row">
                            <div class="mb-2 col">
                                <input type="text" name="complemento" class="form-control col" id="complemento" placeholder="COMPLEMENTO">
                            </div>
                            <div class="mb-2 col">
                                <input type="text" name="bairro" class="form-control" id="bairro" placeholder="BAIRRO">
                            </div>
                        </div>
                        <div class="formCadastro row">
                            <div class="mb-2 col">
                                <input type="text" name="cidade" class="form-control col" id="cidade" placeholder="CIDADE">
                            </div>
                            <div class="mb-2 col">
                                <input type="text" name="estado" class="form-control" id="estado" placeholder="ESTADO">
                            </div>
                        </div>
                        <div class="formCadastro row">
                            <div class="mb-2 col">
                                <input type="email" name="email" class="form-control" id="email" placeholder="E-MAIL" autocomplete="off" aria-describedby="emailHelp">
                            </div>
                            <div class="mb-2 col">
                                <input type="text" name="tel" class="form-control" id="tel" placeholder="TELEFONE" data-js="tel">
                            </div>
                        </div>
                        <div class="formCadastro row">
                            <div class="mb-2 col">
                                <div class="inputsenha d-flex justify-content-end align-items-center">
                                    <input type="password" name="password" class="form-control" placeholder="SENHA" id="password">
                                    <i class="bi bi-eye me-3" id="togglePass"></i>
                                </div>
                            </div>
                            <div class="mb-2 col">
                                <div class="inputsenha d-flex justify-content-end align-items-center">
                                    <input type="password" name="confpassword" class="form-control" placeholder="CONFIRMAR SENHA" id="confpassword">
                                    <i class="bi bi-eye me-3" id="togglePass2"></i>
                                </div>
                                <div class="conta d-flex flex-end">     
                                    <div class="login d-flex mt-1">    
                                            <div class="ms-1"  class="form-text">Já tem uma conta? 
                                            </div>
                                            <a type="submit" href="login.html" class="btn d-flex justify-content-center align-items-center cadastre text-orange ms-1">Entrar</a>
                                    </div>
                                </div> 
                            </div>
                        </div>
                        <div class="mb-3 d-flex justify-content-center align-items-center">
                            <button type="submit" id="cadastrar" class="btn pt-2 pb-2 ps-4 pe-4 mt-2 fw-medium text-light">CADASTRAR</button>
                        </div>
                      </form>
            </div>
        </div>
    </div>
    <div id="overlay" class="overlay"></div>
    <div id="errorPopup" class="error-box">
    	<div class="circle"><img class="alert" src="./assets/alerta.svg"></div>
        <!-- Aqui adicionamos a imagem que mudar  -->
        <img id="errorImg" class="error-img" src="" alt="Erro">
        <div id="errorMessage"></div>
        <div class="errorButton">
            <button onclick="closeErrorPopup()">Fechar</button>
        </div>
    </div>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="./js/script.js"></script>
<script>
    function showErrorPopup(message, imgSrc) {
        document.getElementById('errorMessage').textContent = message;
        document.getElementById('errorImg').src = imgSrc;  
        document.getElementById('errorPopup').style.display = 'block';
        document.getElementById('overlay').style.display = 'block';
    }

    function closeErrorPopup() {
        document.getElementById('errorPopup').style.display = 'none';
        document.getElementById('overlay').style.display = 'none';
    }

    // Exibe o pop-up de erro se houver uma mensagem de erro
    window.onload = function() {
    	var errorMessage = '<%= request.getAttribute("errorMessage") != null ? request.getAttribute("errorMessage") : "" %>';
        var errorType = '<%= request.getAttribute("errorType") != null ? request.getAttribute("errorType") : "" %>';
        var errorImg;

        if (errorMessage && errorMessage !== "") {
            var errorImg;

            switch (errorType) {
                case 'senha':
                    errorImg = './assets/senha.svg';
                    break;
                case 'email':
                    errorImg = './assets/email.svg';
                    break;
                case 'cliente':
                    errorImg = './assets/login.svg';
                    break;
                case 'consultor':
                    errorImg = './assets/login.svg';
                    break;
                default:
                    errorImg = './assets/error-default.png';
                    break;
            }

            showErrorPopup(errorMessage, errorImg);
        }
    };
</script>
</body>
</html>