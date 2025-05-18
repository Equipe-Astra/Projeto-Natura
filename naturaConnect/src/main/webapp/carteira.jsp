<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Natura Connect</title>
    <!-- Google Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Rubik:ital,wght@0,300..900;1,300..900&display=swap" rel="stylesheet">
    <!-- Minha CSS -->
    <link rel="stylesheet" href="./css/carteira.css">
    <!-- Chamada da CSS do BS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <!-- BS Icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    
    <!-- Swiper CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper/swiper-bundle.min.css" />
</head>
<body>
    <header class="color-light shadow">
        <nav class="navbar navbar-expand-lg background-nav ps-4 pe-4">
            <div class="container-fluid">
                <a class="navbar-brand logo" href="#">
                    <img class="logoHeader" src="./assets/logo.svg" alt="Logo">
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <i class="bi bi-filter-right"></i>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav ms-auto me-2">
                        <div class="d-flex">
                            <a class="nav-link font-nav text-orange fw-medium me-2" href="Carteira">CARTEIRA</a>
                        </div>
                    </ul>
                    <ul class="navbar-nav">
                        <div class="d-flex">
                            <a class="nav-link font-nav text-orange fw-medium me-2" href="estoqueConsultora">ESTOQUE</a>
                        </div>
                    </ul>
                    <ul class="navbar-nav">
	                    <div class="d-flex">
	                    	<form action="Logout" method="post">
						        <button class="nav-link font-nav text-orange fw-medium me-2" type="submit">SAIR</button>
						    </form>
	                    </div>
	                </ul>
                    <ul class="navbar-nav">
                        <div class="d-flex">
                            <a class="nav-link font-nav text-orange fw-medium me-2" href="perfilConsultora">
                                <p class="perfil">PERFIL</p>
                                <i class="bi perfil-icon bi-person-circle text-orange"></i>
                            </a>
                        </div>
                    </ul>
                </div>
            </div>
        </nav>
    </header> 
    <figure class="mb-0">
        <img class="onda" src="./assets/ondaCarteira.svg">
    </figure>   
    <main>
        <div class="container-title d-flex flex-column">
            <div class="title d-flex flex-row">
                <i class="bi bi-wallet2 fs-3"></i>
                <p class="mb-0 fs-4 title-carteira fw-semibold ms-2">CARTEIRA DE CLIENTES</p>
            </div>
            <div class="filtro d-flex p-4 flex-column">
                <div class="title d-flex flex-row mt-2">
                    <i class="bi bi-funnel fs-5"></i>
                    <div class="mb-0 fs-5 fw-medium title-filtro ms-2">FILTRAR POR
                        <a class="nav-link dropdown-toggle font-nav dropdown-check" href="" role="button" data-bs-toggle="dropdown" aria-expanded="false"></a>
                        <ul class="dropdown-menu shadow">
                            <li class="nav-item me-2">
                                <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault1">
                                <label class="form-check-label fw-medium fs-6" for="flexCheckDefault1">ATIVO</label>
                            </li>
                            <li class="nav-item me-2">
                                <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked1">
                                <label class="form-check-label fw-medium fs-6" for="flexCheckChecked1">INATIVO</label>
                            </li>
                            <li class="nav-item me-2">
                                <input class="form-check-input" type="checkbox" value="" id="maisRecentes1">
                                <label class="form-check-label fw-medium fs-6" for="flexCheckChecked1">MAIS RECENTES</label>
                            </li>
                            <li class="nav-item me-2">
                                <input class="form-check-input" type="checkbox" value="" id="maisAntigos1">
                                <label class="form-check-label fw-medium fs-6" for="flexCheckChecked1">MAIS ANTIGOS</label>
                            </li>
                            <li class="nav-item me-2 d-flex justify-content-center align-items-center">
                                <button type="submit" id="filtrar1" class="btn pt-3 ps-2 pe-2 fw-medium text-orange color-light">FILTRAR</button>
                            </li>
                            <li class="nav-item me-2 d-flex justify-content-center align-items-center">
                                 <button type="submit" id="limpar1" class="btn ps-2 pe-2 fw-medium text-orange color-light">LIMPAR</button>
                            </li>
                        </ul>
                        <div class="checkBox align-items-center">
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                                <label class="form-check-label fw-medium fs-6" for="flexCheckDefault">ATIVO</label>
                            </div>
                            <div class="form-check ms-3">
                                <input class="form-check-input" type="checkbox" value="" id="flexCheckChecked">
                                <label class="form-check-label fw-medium fs-6" for="flexCheckChecked">INATIVO</label>
                            </div>
                            <div class="form-check ms-3">
                                <input class="form-check-input" type="checkbox" value="" id="maisRecentes">
                                <label class="form-check-label fw-medium fs-6" for="maisRecentes">MAIS RECENTES</label>
                            </div>
                            <div class="form-check ms-3">
                                <input class="form-check-input" type="checkbox" value="" id="maisAntigos">
                                <label class="form-check-label fw-medium fs-6" for="maisAntigos">MAIS ANTIGOS</label>
                            </div>
                            <div>
                                <button type="submit" id="filtrar" class="btn ms-3 pt-2 pb-2 ps-2 pe-2 fw-medium text-orange color-light">FILTRAR</button>
                            </div>
                            <div>
                                <button type="submit" id="limpar" class="btn ms-2 pt-2 pb-2 ps-2 pe-2 fw-medium text-orange color-light">LIMPAR</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <section id="carteira" class="swiper carteira container-light p-3">
		      <div class="swiper mySwiper">
   				<div class="swiper-wrapper ps-1 pe-1">
		            <c:choose>
		            	<c:when test="${not empty listausu}">
				            <c:forEach var="usuario" items="${listausu}" varStatus="status">
		    					<div class="swiper-slide d-flex justify-content-center <c:if test="${solic == listausu[0]}">active</c:if>">
			                        <div class="card-carousel color-light shadow">
			                            <p id="status" class="statusCliente fw-medium d-flex justify-content-end align-items-center me-4 mb-0 mt-1">
			                                <i class="bi bi-dot fs-3"></i>${usuario.status}
			                            </p>
			                            <div class="container-card d-flex justify-content-center align-items-center flex-column">
			                                <figure>
			                                    <img class="foto" src="./assets/foto.svg" alt="">
			                                </figure>
			                                <h3 class="fs-6 text-uppercase">${usuario.nomeCompleto}</h3>
			                                <div class="contato d-flex flex-column justify-content-start align-items-start mt-2">
			                                    <div class="endereco d-flex flex-row">
			                                        <i class="bi bi-geo-alt fs-5"></i>
			                                        <p class="endereco enderecotam mb-0 fw-light ms-2">${usuario.logradouro}, ${usuario.numeroCasa}</p>
			                                    </div>
			                                    <div class="fone d-flex flex-row">
			                                        <i class="bi bi-whatsapp"></i>
			                                        <p class="fone fonetam mb-0 fw-light ms-2">${usuario.telefone}</p>
			                                    </div>
			                                </div>
			                                <div class="infos d-flex mt-2">
			                                    <div class="cliente mt-3 d-flex flex-column justify-content-center align-items-center">
			                                        <p class="desde fw-medium text-orange mb-0">CLIENTE DESDE</p>
			                                        <p class="fw-light">${usuario.dataCadastro}</p>
			                                    </div>
			                                    <div class="compra mt-3 d-flex flex-column justify-content-center align-items-center">
			                                        <p class="ultima fw-medium text-orange ms-4 mb-0">ÚLTIMA COMPRA</p>
			                                        <p class="fw-light ms-4">${usuario.dataCompra}</p>
			                                    </div>
			                                </div>
			                            </div>
			                        </div>
			                    </div>
								</c:forEach>
		                </c:when>
		            </c:choose>
		        </div>
		        
		    </div>
		    <div class="swiper-button-next d-flex justify-content-center text-orange"></div>
        	<div class="swiper-button-prev text-orange"></div>
	    	 
		</section>
    </main>
    <footer class="shadow-lg pt-2 pb-2 d-flex justify-content-center align-items-center color-light">
        <p class="fw-regular mb-0">©TECH FIVE <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2024</p>
    </footer>
    <!-- Chamada JS do BS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-...your-integrity-hash..." crossorigin="anonymous"></script>
    <script src="./js/script.js"></script>
   	<!-- Swiper JS -->
	<script src="https://cdn.jsdelivr.net/npm/swiper/swiper-bundle.min.js"></script>
    <script>
    
    document.addEventListener('DOMContentLoaded', function () {
		var swiper = new Swiper(".mySwiper", {
		      slidesPerView: 4,
		      spaceBetween: 30,
	        loop: false,  // Desabilitar loop, o carrossel não vai voltar ao início automaticamente
	        navigation: {
	            nextEl: '.swiper-button-next',
	            prevEl: '.swiper-button-prev',
	        },
	        breakpoints: {
	        	375: {
	                slidesPerView: 1,  // Em telas pequenas, mostra 1 slide
	                slidesPerGroup: 1,
	            },
	            426: {
	                slidesPerView: 2,  // Em telas pequenas, mostra 1 slide
	                slidesPerGroup: 1,
	            },
	            768: {
	                slidesPerView: 2,  // Em telas médias, mostra 2 slides
	                slidesPerGroup: 1,
	            },
	            1024: {
	                slidesPerView: 3,  // Em telas maiores, mostra 3 slides
	                slidesPerGroup: 1,
	            },
	            
	            1200: {
	                slidesPerView: 3,  // Em telas maiores, mostra 3 slides
	                slidesPerGroup: 1,
	            },
	            1400: {
	                slidesPerView: 4,  // Em telas maiores, mostra 3 slides
	                slidesPerGroup: 1,
	            }
	        },
	    });
    });


    
    document.getElementById('filtrar').addEventListener('click', function(e) {
        e.preventDefault();
        
        // Captura os valores dos checkboxes
        const ativo = document.getElementById('flexCheckDefault').checked;
        const inativo = document.getElementById('flexCheckChecked').checked;
        const maisRecentes = document.getElementById('maisRecentes').checked;
        const maisAntigos = document.getElementById('maisAntigos').checked;

        // Monta a URL com os parâmetros
        const queryParams = new URLSearchParams({
            ativo: ativo,
            inativo: inativo,
            maisRecentes: maisRecentes,
            maisAntigos: maisAntigos
        }).toString();

        // Redireciona para o servlet com os parâmetros de filtro
        window.location.href = 'Carteira?' + queryParams;
    });

    document.getElementById('limpar').addEventListener('click', function(e) {
        e.preventDefault();
        
        // Redireciona para o servlet com o parâmetro de limpar filtros
        window.location.href = 'Carteira?limpar=true';
    });
    
    document.getElementById('filtrar1').addEventListener('click', function(e) {
        e.preventDefault();
        
        // Captura os valores dos checkboxes
        const ativo = document.getElementById('flexCheckDefault1').checked;
        const inativo = document.getElementById('flexCheckChecked1').checked;
        const maisRecentes = document.getElementById('maisRecentes1').checked;
        const maisAntigos = document.getElementById('maisAntigos1').checked;

        // Monta a URL com os parâmetros
        const queryParams = new URLSearchParams({
            ativo: ativo,
            inativo: inativo,
            maisRecentes: maisRecentes,
            maisAntigos: maisAntigos
        }).toString();

        // Redireciona para o servlet com os parâmetros de filtro
        window.location.href = 'Carteira?' + queryParams;
    });

    document.getElementById('limpar1').addEventListener('click', function(e) {
        e.preventDefault();
        
        // Redireciona para o servlet com o parâmetro de limpar filtros
        window.location.href = 'Carteira?limpar=true';
    });
    

            const statusElements = document.getElementsByClassName('statusCliente');
            Array.from(statusElements).forEach(status => {
                const text = status.textContent || status.innerText;
                switch (text.trim()) {
                    case 'ATIVO':
                        status.classList.add('text-green');
                        break;
                    case 'INATIVO':
                        status.classList.add('text-red');
                        break;
                    default:
                        // No change or default color
                        break;
                }
            });
   
    </script>
</body>
</html>
