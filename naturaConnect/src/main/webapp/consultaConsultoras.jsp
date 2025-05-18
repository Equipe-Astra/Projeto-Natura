<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Natura Connect</title>

<!--  Google Fonts -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Rubik:ital,wght@0,300..900;1,300..900&display=swap"
	rel="stylesheet">

<!-- Minha CSS -->
<link rel="stylesheet" href="./css/consultaConsultoras.css">

<!-- Chamada da CSS do BS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9"
	crossorigin="anonymous">

<!-- BS Icons -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
</head>
<body>
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
					<ul class="navbar-nav">
		                    <div class="d-flex">
		                    	<form action="Logout" method="post">
							        <button class="nav-link font-nav text-orange fw-medium me-2" type="submit">SAIR</button>
							    </form>
		                    </div>
		                </ul>
					<ul class="navbar-nav">
						<div class="d-flex">
							<a class="nav-link font-nav text-orange fw-medium me-2" href="#">
								<p class="perfil">PERFIL</p> <i
								class="bi perfil-icon bi-person-circle text-orange"></i>
							</a>
						</div>
					</ul>
				</div>
			</div>
		</nav>
	</header>
	<main class="mb-3">
		<figure class="mb-0">
			<img class="onda" src="./assets/ondaConsultoras.svg">
		</figure>
		<div class="d-flex justify-content-around container-consulta">
			<section
				class="container-img d-flex justify-content-center flex-column align-items-center">
				<div class="container-img d-flex justify-content-center flex-column">
					<h3
						class="fw-semibold d-flex align-items-center flex-column title-1">
						CONSULTORAS RECOMENDADAS <br>
						<div class="d-flex align-items-center">
							PARA
							<h3 class="m-0 text-orange fw-semibold title-2">&nbsp;VOCÊ</h3>
						</div>
						<h5
							class="fw-regular d-flex align-items-center flex-column title-3">
							Encontramos as consultoras perfeitas para te ajudar <br>
							<div class="d-flex align-items-center">
								a praticar o
								<h5 class="text-orange fw-regular m-0 title-4">&nbsp;bem-estar-bem</h5>
							</div>
						</h5>
				</div>
				<figure>
					<img class="img-consulta" src="./assets/consulta.svg">
				</figure>
			</section>
			<div
				class="div-consulta d-flex justify-content-center align-items-center flex-column">
				<div
					class="consulta justify-content-center flex-column align-items-center">
					<c:forEach var="consultorasProximas" items="${consultorasProximas}">
						<div
							class="d-flex justify-content-center align-items-center flex-row">
							<input class="form-check-input me-3" type="checkbox" value="${consultorasProximas.id}" data-consultora-id="${consultorasProximas.id}">

							<div
								class="box shadow color-light rounded border-end-0 border-bottom-0 p-3 d-flex justify-content-center align-items-center">
								<figure>
									<img class="foto" src="./assets/foto.svg">
								</figure>
								<div class="infos ms-3">
									<h6 class="fw-semibold text-uppercase">
										${consultorasProximas.nomeCompleto}</h6>
									<div class="contato d-flex align-items-center">
										<i class="bi bi-whatsapp"></i>
										<p class="fone mb-0 fw-light">${consultorasProximas.telefone}</p>
									</div>

									<div class="tags mt-2 d-flex">
										<c:forEach var="tagConsult"
											items="${fn:split(consultorasProximas.tags, ',')}">
											<div
												class="tag rounded p-2 me-2 d-flex align-items-center justify-content-center">
												<p class="mb-0 nometag text-center fw-light">${tagConsult}</p>
											</div>
										</c:forEach>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
				<form id="consultoraForm" action="/naturaConnect/consultaConsultorasPost" method="POST">
				   <input type="hidden" id="codConsult" name="codConsult" value="">
				   <button type="submit" class="solicit pt-3 mt-3 pb-3 ps-4 pe-4 fw-medium text-light rounded color-orange border borner-0">SOLICITAR CONTATO</button>
				</form>
			</div>
		</div>
	</main>
	<footer
		class="shadow-lg pt-2 pb-2 d-flex justify-content-center align-items-center color-light">
		<p class="fw-regular mb-0">
			©TECH FIVE <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2024
		</p>
	</footer>
	<!-- Chamada JS do BS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="./js/script.js"></script>

	<script>
	document.addEventListener('DOMContentLoaded', function () {
	    const checkboxes = document.querySelectorAll('.form-check-input');
	    const consultoraCodeField = document.getElementById('codConsult');

	    checkboxes.forEach(checkbox => {
	        checkbox.addEventListener('change', function () {
	            checkboxes.forEach(cb => {
	                if (cb !== this) {
	                    cb.checked = false;
	                }
	            });

	            consultoraCodeField.value = this.checked ? this.dataset.consultoraId : '';
	        });
	    });
	});

</script>
</body>
</html>