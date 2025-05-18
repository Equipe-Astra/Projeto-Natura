<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Natura Connect</title>
	
	<!-- Google Fonts -->
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link
		href="https://fonts.googleapis.com/css2?family=Rubik:ital,wght@0,300..900;1,300..900&display=swap"
		rel="stylesheet">
	
	<!-- Minha CSS -->
	<link rel="stylesheet" href="./css/consultora.css">
	
	<!-- Font Awesome -->
	<link rel="stylesheet"
		href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
	
	<!-- Bootstrap CSS -->
	    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">

	<!-- Swiper CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper/swiper-bundle.min.css" />

	<!-- Bootstrap Icons -->
	<link rel="stylesheet"
		href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
	
	</head>
	<body>
		<div class="modal fade" id="solicitacaoModal" tabindex="-1" aria-labelledby="solicitacaoModalLabel" aria-hidden="true">
			  <div class="modal-dialog modal-dialog-centered">
			    <div class="modal-content">
			      <div class="modal-header">
			       <button type="button" class="btn-close m-0" data-bs-dismiss="modal" aria-label="Close"></button>
			        <h5 class="modal-title" id="solicitacaoModalLabel"></h5>
			      </div>
			      <div class="modal-body">
			      		<div class=" d-flex align-items-center ps-3 pe-3">
							<figure>
								<img class="foto" src="./assets/foto.svg"
									alt="Foto de Perfil">
							</figure>
							<div
								class="card-text d-flex flex-column justify-content-center" id="open-popup">
								<h3 class="nomeModal" style="text-transform: uppercase;"></h3>
								<div class="contato d-flex flex-column mt-2">
									<div class="endereco d-flex flex-row align-items-center">
										<i class="bi bi-geo-alt fs-5"></i>
										<p class="enderecoModal mb-0 fw-light ms-2"></p>
									</div>
									<div class="fone d-flex flex-row align-items-center mt-1">
										<i class="bi bi-whatsapp"></i>
										<p class="foneModal mb-0 fw-light ms-2"></p>

									</div>
								</div>
							</div>
						</div>
						<div class="mensagem d-flex pt-3 ps-5 pe-5">
							Olá, meu nome é <p class="modalMens"></p>. Gostaria que você entrasse em contato comigo.
							Meus interesses são:
						</div>
						<div class="tags d-flex justify-content-center">
						<div class="tags-modal d-flex justify-content-center mt-3">
						    <!-- Aqui as tags serão adicionadas dinamicamente -->
						</div>
						</div>
			      </div>
			      <div class="modal-footer d-flex flex-column mb-3">
			      	<p class="fw-semibold">ADICIONAR CLIENTE À CARTEIRA?</p>
			      	<div class="botoes"> 
			      	<form action="perfilConsultora" method="post">
						    <!-- Outros campos do modal -->
						    <input type="hidden" name="idCliente" class="idCliente" value="">
						    <input type="hidden" name="codConsult" class="codConsult" value="">
							<button type="submit" class="btn me-5 fs-6 color-light shadow" name="action" value="removerSolicitacao" data-bs-dismiss="modal">
							                <i class="bi bi-trash"></i> RECUSAR
							            </button>							
            				<button type="submit" class="btn color-orange text-light shadow fs-6" name="action" value="adicionarCarteira"><i class="bi bi-wallet2"></i> CARTEIRA</button>
						</form>
			      	</div>
			       
			      </div>
			    </div>
			  </div>
		</div>
		<div class="modal fade" id="solicitacaoPedModal" tabindex="-1" aria-labelledby="solicitacaoPedModalLabel" aria-hidden="true">
		  <div class="modal-dialog modal-dialog-centered">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="btn-close m-0" data-bs-dismiss="modal" aria-label="Close"></button>
		        <h5 class="modal-title modal-titlep" id="solicitacaoPedModalLabel"></h5>
		      </div>
		      <div class="modal-body">
		        <div class="d-flex align-items-center ps-3 pe-3">
		          <figure>
		            <img class="foto" src="./assets/foto.svg" alt="Foto de Perfil">
		          </figure>
		          <div class="card-text d-flex flex-column justify-content-center">
		            <h3 class="nomeModalPed" style="text-transform: uppercase;"></h3>
		            <div class="contato d-flex flex-column mt-2">
		              <div class="endereco d-flex flex-row align-items-center">
		                <i class="bi bi-geo-alt fs-5"></i>
		                <p class="enderecoModalPed mb-0 fw-light ms-2"></p>
		              </div>
		              <div class="fone d-flex flex-row align-items-center mt-1">
		                <i class="bi bi-whatsapp"></i>
		                <p class="foneModalPed mb-0 fw-light ms-2"></p>
		              </div>
		            </div>
		          </div>
		        </div>
		        <div class="mensagem d-flex pt-3 ps-5 pe-5">
		          Olá, acabei de fazer um pedido com você! Os produtos que solicitei são:
		        </div>
		        <div class="peds d-flex justify-content-center">
		          <div class="peds-modal d-flex mt-3 flex-column">
		            <!-- Aqui as tags serão adicionadas dinamicamente -->
		          </div>
		        </div>
		      </div>
		      <div class="modal-footer d-flex flex-column mb-3">
		        <p class="fw-semibold">ATENDER PEDIDO?</p>
		        <div class="botoes">
		          <form id="formAtenderPedido" action="perfilConsultora" method="post">
		            <input type="hidden" name="idCompra" class="idCompra" value="">
		            <input type="hidden" name="idClip" class="idClip" value="">
		            <input type="hidden" name="valorTotal" class="valorTotal" value="">
		            <button type="submit" class="btn me-5 fs-6 color-light shadow" name="action" value="removerSolicitacaoPed" data-bs-dismiss="modal">
		              <i class="bi bi-trash"></i> RECUSAR
		            </button>
		            <button type="submit" class="btn color-orange text-light shadow fs-6" name="action" value="atenderPedido">
		              <i class="bi bi-bag-check me-1"></i> ATENDER
		            </button>
		          </form>
		        </div>
		      </div>
		    </div>
		  </div>
		</div>

		<header class="color-light shadow">
			<nav class="navbar navbar-expand-lg background-nav ps-4 pe-4">
				<div class="container-fluid">
					<a class="navbar-brand logo" href="#"> <img class="logoHeader"
						src="./assets/logo.svg" alt="Logo">
					</a>
					<button class="navbar-toggler" type="button"
						data-bs-toggle="collapse" data-bs-target="#navbarNav"
						aria-controls="navbarNav" aria-expanded="false"
						aria-label="Toggle navigation">
						<i class="bi bi-filter-right"></i>
					</button>
					<div class="collapse navbar-collapse" id="navbarNav">
						<ul class="navbar-nav ms-auto me-2">
							<li class="nav-item"><a
								class="nav-link font-nav text-orange fw-medium me-2" href="Carteira">CARTEIRA</a>
							</li>
						</ul>
						<ul class="navbar-nav">
							<li class="nav-item"><a
								class="nav-link font-nav text-orange fw-medium me-2" href="estoqueConsultora">ESTOQUE</a>
							</li>
						</ul>
						<ul class="navbar-nav">
		                    <div class="d-flex">
		                    	<form action="Logout" method="post">
							        <button class="nav-link font-nav text-orange fw-medium me-2" type="submit">SAIR</button>
							    </form>
		                    </div>
		                </ul>
						<ul class="navbar-nav">
							<li class="nav-item"><a
								class="nav-link font-nav text-orange fw-medium me-2" href="perfilConsultora">
									<p class="perfil">PERFIL</p> <i
									class="bi perfil-icon bi-person-circle text-orange"></i>
							</a></li>
						</ul>
					</div>
				</div>
			</nav>
		</header>
	
		<div class="tituloebotao">
			<h2 class="carousel-title">
				<i class="bi bi-clipboard pe-3 fw-bold"></i>MINHAS SOLICITAÇÕES
			</h2>
			<div class="btn-group btn-toggle"> 
                  <button class="btn btn-default shadow mb-3 mt-1 rounded atendimento">ATENDIMENTO</button>
                  <button class="btn shadow mb-3 mt-1 rounded pedido active ">PEDIDOS</button>
             	</div>
             <input type="hidden" id="tipoSolic" name="tipoSolic" value="">
		</div>
		<section id="carteira" class="swiper carteira atendimento-section container-light">
		      <div class="swiper mySwiper">
   				<div class="swiper-wrapper">
		            <c:choose>
		                <c:when test="${not empty solicAtendimento}">
		                    <c:forEach var="solic" items="${solicAtendimento}">
									<div class="swiper-slide d-flex justify-content-center <c:if test="${solic == solicAtendimento[0]}">active</c:if>" data-cod="${solic.codigoConsult}" data-idCli="${solic.idCliente}" data-tag="${solic.tags}" data-id="${solic.idContato}" data-nome="${solic.nomeCompleto}" data-endereco="${solic.endereco}" data-telefone="${solic.telefone}">
											<div class="card-carousel color-light shadow ps-4 pe-4">
												<p
													class="statusCliente fw-medium d-flex justify-content-end align-items-center mb-0 mt-1">
													<i class="bi bi-hash fs-4"></i>CON${solic.idContato}
												</p>
												<input type="hidden" name="idCliente" class="idCliente" value="${solic.idCliente}">
												<input type="hidden" name="idConsult" class="idConsult" value="${solic.codigoConsult}">
												
												<div class="container-card d-flex align-items-center">
													<figure>
														<img class="foto" src="./assets/foto.svg"
															alt="Foto de Perfil">
													</figure>
													<div
														class="card-text d-flex flex-column justify-content-center" id="open-popup">
														<h3 class="nomeCompl" style="text-transform: uppercase;">${solic.nomeCompleto}</h3>
														<div class="contato d-flex flex-column mt-2">
															<div class="endereco d-flex flex-row align-items-center">
																<i class="bi bi-geo-alt fs-5"></i>
																<p class="endereco mb-0 fw-light ms-2">${solic.endereco}</p>
															</div>
															<div class="fone d-flex flex-row align-items-center mt-1">
																<i class="bi bi-whatsapp"></i>
																<p class="fone mb-0 fw-light ms-2">${solic.telefone}</p>
															</div>
															<c:forEach var="tag" items="${fn:split(solic.tags, ',')}">
																<div
																	class="tag rounded p-2 me-2 d-flex align-items-center justify-content-center d-none">
																	<p class="mb-0 nometag text-center fw-light"
																		style="text-transform: uppercase;">${tag}</p>
																</div>
															</c:forEach>
														</div>
													</div>
												</div>
											</div>
										</div>
								</c:forEach>
		                </c:when>
		            </c:choose>
		        </div>
		        <!-- Adiciona controles do Swiper -->
		    </div>
		    <div class="swiper-button-next swiper-next-atend d-flex justify-content-center text-orange"></div>
	        <div class="swiper-button-prev swiper-prev-atend text-orange"></div>
		</section>
		<section id="pedidos" class="swiper carteira pedido-section container-light">
		      <div class="swiper mySwiperPed">
   				<div class="swiper-wrapper">
		            <c:choose>
		                <c:when test="${not empty solicPedido}">
		                    <c:forEach var="solicp" items="${solicPedido}">
									<div class="swiper-slide d-flex justify-content-center <c:if test="${solicp == solicPedido[0]}">active</c:if>" data-codp="${solicp.codigoConsult}" data-idClip="${solicp.idCliente}" data-peds="${solicp.descricaoProds}" data-idp="${solicp.idCompra}" data-nomep="${solicp.nomeCompleto}" data-enderecop="${solicp.endereco}" data-telefonep="${solicp.telefone}" data-total="${solicp.valorTotal}">
											<div class="card-carousel color-light shadow ps-4 pe-4">
												<p
													class="statusCliente fw-medium d-flex justify-content-end align-items-center mb-0 mt-1">
													<i class="bi bi-hash fs-4"></i>PED${solicp.idCompra}
												</p>
												<input type="hidden" name="idClip" class="idClip" value="${solicp.idCliente}">
												<input type="hidden" name="idConsultp" class="idConsultp" value="${solicp.codigoConsult}">
												
												<div class="container-card d-flex align-items-center">
													<figure>
														<img class="foto" src="./assets/foto.svg"
															alt="Foto de Perfil">
													</figure>
													<div
														class="card-text d-flex flex-column justify-content-center" id="open-popup">
														<h3 class="nomeCompl" style="text-transform: uppercase;">${solicp.nomeCompleto}</h3>
														<div class="contato d-flex flex-column mt-2">
															<div class="endereco d-flex flex-row align-items-center">
																<i class="bi bi-geo-alt fs-5"></i>
																<p class="endereco mb-0 fw-light ms-2">${solicp.endereco}</p>
															</div>
															<div class="fone d-flex flex-row align-items-center mt-1">
																<i class="bi bi-whatsapp"></i>
																<p class="fone mb-0 fw-light ms-2">${solicp.telefone}</p>
															</div>
															<c:forEach var="ped" items="${fn:split(solicp.descricaoProds, ',')}" varStatus="status">
															    <div class="prod rounded p-2 me-2 d-flex align-items-center justify-content-center d-none">
															        <p class="mb-0 descrprod text-center fw-light" style="text-transform: uppercase;">
															            ${status.index + 1}. ${ped}
															        </p>
															        <p class="mb-0 total text-center fw-light" style="text-transform: uppercase;">
															            ${solicp.valorTotal}
															        </p>
															    </div>
															</c:forEach>
														</div>
													</div>
												</div>
											</div>
										</div>
								</c:forEach>
		                </c:when>
		            </c:choose>
		        </div>
		        <!-- Adiciona controles do Swiper -->
		    </div>
		    <div class="swiper-button-next swiper-next-ped d-flex justify-content-center text-orange"></div>
	        <div class="swiper-button-prev swiper-prev-ped text-orange"></div>
		</section>
		
		<section class="corpo">
			<main class="container mb-3">
				<div class="d-flex container-consulta">
					<section class="container-img d-flex flex-column align-items-center">
						<h2 class="cards-title mb-4">
							<i class="bi bi-bar-chart-line-fill p-2 fw-bold"></i>RELATÓRIOS MENSAIS
						</h2>
						<section class="d-flex flex-column align-items-center">
							<div class="card-container d-flex justify-content-between">
								<div class="left-cards">
									<div
										class="card vertical-card shadow color-light rounded border-end-0 border-bottom-0 p-3 mb-3">
										<h5 class="fw-bold title">ANÁLISE GERAL - 2024</h5>
										<div class="card-body">
											<canvas id="analiseGeral"></canvas>
										</div>
									</div>
	
									<div
										class="card vertical-card produtosVendidos shadow color-light rounded border-end-0 border-bottom-0 p-3 mb-3">
										<h5 class="title fw-bold">PRODUTOS MAIS VENDIDOS</h5>
										<div class="">
											<canvas id="myChart"></canvas>
										</div>
									</div>
								</div>
								<div class="right-card d-flex align-items-stretch">
									<div
										class="card horizontal-card shadow color-light rounded border-end-0 border-bottom-0 p-3">
										<h5 class="card-title text-center fw-bold">METAS</h5>
										<div
											class="card-body d-flex flex-column ">
												<ul class="todo-list mt-3">
													<c:forEach var="meta" items="${metas}">
														<li class="text-uppercase fw-medium">${meta}</li>
													</c:forEach>
												</ul>
										</div>
									</div>
								</div>
							</div>
						</section>
					</section>
	
					<div
						class="div-consulta d-flex justify-content-center align-items-center flex-column">
						<h2 class="cards-title mb-4">
							<i class="bi bi-geo-alt p-2"></i>CLIENTES PRÓXIMOS
						</h2>
						<div class="consulta d-flex flex-column align-items-start">
							<div
								class="d-flex justify-content-start align-items-center flex-column">
								<c:forEach var="clientes" items="${clientesProximos}">
									<div
										class="box shadow color-light rounded border-end-0 border-bottom-0 p-3 d-flex justify-content-center align-items-center">
										<figure>
											<img class="foto" src="./assets/foto.svg"
												alt="Foto de cliente">
										</figure>
										<div class="infos ms-3">
											<h6 class="fw-semibold" style="text-transform: uppercase;">${clientes.nomeCompleto}</h6>
											<div class="contato d-flex align-items-center">
												<i class="bi bi-whatsapp"></i>
												<p class="fone mb-0 fw-light">${clientes.telefone}</p>
											</div>
	
											<div class="tags mt-2 d-flex flex-row">
												<c:forEach var="tag" items="${fn:split(clientes.tags, ',')}">
													<div
														class="tag rounded p-2 me-2 d-flex align-items-center justify-content-center">
														<p class="mb-0 nometag text-center fw-light"
															style="text-transform: uppercase;">${tag}</p>
													</div>
												</c:forEach>
											</div>
	
										</div>
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
			</main>
		</section>
	
		<footer
			class="shadow-lg pt-2 pb-2 d-flex justify-content-center align-items-center color-light">
			<p class="fw-regular mb-0">
				©TECH FIVE <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2024
			</p>
		</footer>
	
		<!-- Bootstrap JS -->
		<!-- Chamada JS do BS -->
    	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-...your-integrity-hash..." crossorigin="anonymous"></script>
		<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
		<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
		<!-- Swiper JS -->
<script src="https://cdn.jsdelivr.net/npm/swiper/swiper-bundle.min.js"></script>
		<script>
		
		document.addEventListener('DOMContentLoaded', function () {
		    var swiper = new Swiper(".mySwiper", {
		        slidesPerView: 3,
		        spaceBetween: 30,
		        loop: false,
		        navigation: {
		            nextEl: '.swiper-next-atend',
		            prevEl: '.swiper-prev-atend',
		        },
		        breakpoints: {
		            375: {
		                slidesPerView: 1,
		                slidesPerGroup: 1,
		            },
		            640: {
		                slidesPerView: 1,
		                slidesPerGroup: 1,
		            },
		            768: {
		                slidesPerView: 2,
		                slidesPerGroup: 1,
		            },
		            1024: {
		                slidesPerView: 3,
		                slidesPerGroup: 1,
		            },
		        },
		    });

		    var swiperPed = new Swiper(".mySwiperPed", {
		        slidesPerView: 3,
		        spaceBetween: 30,
		        loop: false,
		        navigation: {
		            nextEl: '.swiper-next-ped',
		            prevEl: '.swiper-prev-ped',
		        },
		        breakpoints: {
		            375: {
		                slidesPerView: 1,
		                slidesPerGroup: 1,
		            },
		            640: {
		                slidesPerView: 1,
		                slidesPerGroup: 1,
		            },
		            768: {
		                slidesPerView: 2,
		                slidesPerGroup: 1,
		            },
		            1024: {
		                slidesPerView: 3,
		                slidesPerGroup: 1,
		            },
		        },
		    });

		    var modal = new bootstrap.Modal(document.getElementById('solicitacaoModal'));
		    var modalp = new bootstrap.Modal(document.getElementById('solicitacaoPedModal'));

		    var modalTitle = document.querySelector('.modal-title');
		    var nomeModal = document.querySelector('.nomeModal');
		    var nomeModalMens = document.querySelector('.modalMens');
		    var modalTagContainer = document.querySelector('.tags-modal');
		    var modalIdCli = document.querySelector('.idCliente');
		    var modalCodConsult = document.querySelector('.codConsult');
		    var enderecoModal = document.querySelector('.enderecoModal');
		    var foneModal = document.querySelector('.foneModal');

		    var modalTitlep = document.querySelector('.modal-titlep');
		    var nomeModalp = document.querySelector('.nomeModalPed');
		    var modalPedContainer = document.querySelector('.peds-modal');
		    var enderecoModalp = document.querySelector('.enderecoModalPed');
		    var foneModalp = document.querySelector('.foneModalPed');

		    function updateModalContent(slide) {
		        var id = slide.getAttribute('data-id');
		        var nome = slide.getAttribute('data-nome');
		        var endereco = slide.getAttribute('data-endereco');
		        var fone = slide.getAttribute('data-telefone');
		        var tags = slide.getAttribute('data-tag').split(',');
		        var idCli = slide.getAttribute('data-idCli');
		        var codConsult = slide.getAttribute('data-cod');

		        modalTitle.innerHTML = '#CON' + id;
		        foneModal.innerHTML = fone;
		        nomeModal.innerHTML = nome;
		        nomeModalMens.innerHTML = nome;
		        enderecoModal.innerHTML = endereco;
		        modalIdCli.value = idCli;
		        modalCodConsult.value = codConsult;

		        modalTagContainer.innerHTML = '';

		        tags.forEach(function(tag) {
		            var tagElement = document.createElement('div');
		            tagElement.classList.add('tag', 'rounded', 'p-2', 'me-2', 'd-flex', 'align-items-center', 'justify-content-center');
		            
		            var tagText = document.createElement('p');
		            tagText.classList.add('mb-0', 'nometag', 'text-center', 'fw-light');
		            tagText.style.textTransform = 'uppercase';
		            tagText.innerText = tag;

		            tagElement.appendChild(tagText);
		            modalTagContainer.appendChild(tagElement);
		        });
		    }

		    function updateModalContentPedido(slide) {
		        var idp = slide.getAttribute('data-idp');
		        var nomep = slide.getAttribute('data-nomep');
		        var enderecop = slide.getAttribute('data-enderecop');
		        var fonep = slide.getAttribute('data-telefonep');
		        var pedsp = slide.getAttribute('data-peds').split(',');
		        var total = slide.getAttribute('data-total');

		        modalTitlep.innerHTML = '#PED' + idp;
		        foneModalp.innerHTML = fonep;
		        nomeModalp.innerHTML = nomep;
		        enderecoModalp.innerHTML = enderecop;

		        modalPedContainer.innerHTML = ''; // Limpa antes de adicionar novos elementos

		        pedsp.forEach(function(ped, index) {
		            var pedElement = document.createElement('div');
		            pedElement.classList.add('ped', 'rounded', 'p-2', 'me-2', 'd-flex', 'align-items-center');
		            
		            var pedText = document.createElement('p');
		            pedText.classList.add('mb-0', 'descrprod', 'text-center', 'fw-semibold');
		            pedText.style.textTransform = 'uppercase';
		            pedText.style.fontSize = '0.9rem';
		         // Cria um span para o número
		            var numSpan = document.createElement('span');
		            numSpan.innerText = (index + 1) + '. ';
		            numSpan.style.color = '#F5821F';  // Define a cor desejada para o número
		            
		            // Cria um span para o nome do produto
		            var pedSpan = document.createElement('span');
		            pedSpan.innerText = ped;
		            
		            // Adiciona o span do número e o span do produto ao texto
		            pedText.appendChild(numSpan);
		            pedText.appendChild(pedSpan);
		            
		            pedElement.appendChild(pedText);
		            modalPedContainer.appendChild(pedElement);
		        });
		        
		        var totalElement = document.createElement('p');
		        totalElement.classList.add('mb-0', 'total', 'text-center', 'fw-semibold');
		        totalElement.style.textTransform = 'uppercase';
		        totalElement.style.color = '#F5821F';  // Define a cor laranja para o texto

			     // Define o texto com "Valor Total:" antes do valor total
			     totalElement.innerText = 'R$ ' + total;// Aqui você coloca o valor total da variável

		        modalPedContainer.appendChild(totalElement);
		    }


		    function handleSlideClick(event) {
		        var target = event.target.closest('.swiper-slide');
		        if (target) {
		            console.log('Slide clicked:', target);
		            console.log('Tipo de solicitação:', $('#tipoSolic').val());

		            if ($('#tipoSolic').val() === 'atendimento') {
		                console.log('Atendimento modal será exibido');
		                updateModalContent(target);
		                modal.show();
		            } else if ($('#tipoSolic').val() === 'pedido') {
		                console.log('Pedido modal será exibido');
		                updateModalContentPedido(target);
		                modalp.show();
		            } else {
		                console.log('Tipo de solicitação não reconhecido');
		            }
		        } else {
		            console.log('Nenhum slide encontrado');
		        }
		    }

		    document.querySelectorAll('.swiper-slide').forEach(slide => {
		        slide.addEventListener('click', function() {

		            handleSlideClick({ target: this });
		            var idCompra = this.getAttribute('data-idp');
		            var valorTotal = this.getAttribute('data-total');
		            var idCliente = this.getAttribute('data-idClip');
		            var form = document.getElementById('formAtenderPedido');
		            form.querySelector('.idCompra').value = idCompra;
		            form.querySelector('.valorTotal').value = valorTotal;
		            form.querySelector('.idClip').value = idCliente;
		        });
		    });
		});
	    
		
		
		   const descricoes = ${descricoes};
	       const quantidades = ${quantidades};
	       const vendas = ${vendas}
	
	        const ctx = document.getElementById('myChart');
	        new Chart(ctx, {
	            type: 'pie',
	            data: {
	                labels: descricoes,  
	                datasets: [{
	                    label: 'Quantidades',
	                    data: quantidades,  
	                    backgroundColor: ['#FF808B', '#FE821F', '#F6E58D', '#05705D'], 
	                    borderColor: ['#fff', '#fff', '#fff', '#fff', '#fff'],
	                    borderWidth: 1
	                }]
	            },
	            options: {
	            
	                plugins: {
	                    legend: {
	                        position: 'left', 
	                        labels: {
	                            boxWidth: 30, 
	                            boxHeight: 20,
	                            marginRight: 140
	                        }
	                    }
	                }
	            }
	        });
	        
	        const ct = document.getElementById('analiseGeral');
	        new Chart(ct, {
	            type: 'bar',
	            data: {
	                labels: ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'],
	                datasets: [{
	                    label: ' ',
	                    data: vendas, 
	                    backgroundColor: '#F5821F',  
	                    barThickness: 20,
	                    borderWidth: 1,
	                    borderRadius: 4
	                }]
	            },
	            options: {
	                scales: {
	                    x: {
	                        display: true,  
	                        ticks: {
	                            color: '#000',  
	                            font: {
	                                size: 10
	                            }
	                        },
	                        grid: {
	                            display: false  
	                        }
	                    },
	                    y: {
	                        display: false,  
	                        grid: {
	                            display: false  
	                        }
	                    }
	                },
	                plugins: {
	                    legend: {
	                        display: false  
	                    }
	                }
	            }
	        });
	        
	     
	
	        $(document).ready(function() {
	            function atualizarVisibilidade() {
	                // Verifica qual botão está ativo e ajusta a visibilidade das seções
	                if ($('.btn-toggle .btn.atendimento').hasClass('active')) {
	                    $('#tipoSolic').val('atendimento');
	                    $('.pedido-section').addClass('d-none'); // Oculta seção de pedidos
	                    $('.atendimento-section').removeClass('d-none'); // Exibe seção de atendimento
	                } else if ($('.btn-toggle .btn.pedido').hasClass('active')) {
	                    $('#tipoSolic').val('pedido');
	                    $('.atendimento-section').addClass('d-none'); // Oculta seção de atendimento
	                    $('.pedido-section').removeClass('d-none'); // Exibe seção de pedidos
	                }
	            }

	            // Inicializa a visibilidade ao carregar a página
	            atualizarVisibilidade();

	            // Atualiza a visibilidade quando um botão é clicado
	            $('.btn-toggle .btn').click(function() {
	                // Remove a classe 'active' de todos os botões e a classe 'btn-primary' se presente
	                $('.btn-toggle .btn').removeClass('active');
	                $('.btn-toggle .btn').removeClass('btn-primary').addClass('btn-default');

	                // Adiciona a classe 'active' e a classe 'btn-primary' ao botão clicado
	                $(this).addClass('active').removeClass('btn-default').addClass('btn-primary');

	                // Atualiza a visibilidade das seções
	                atualizarVisibilidade();
	            });
	        });

	
	    </script>
	</body>
</html>