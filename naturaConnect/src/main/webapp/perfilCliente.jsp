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
<link rel="stylesheet" href="./css/perfilCliente.css">

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
		<nav class="navbar d-lg-flex background-nav ps-4 pe-4">
			<div class="container-fluid">
				<a class="navbar-brand logo" href="#"> <img class="logoHeader"
					src="./assets/logo.svg" alt="Logo">
				</a>
				<!-- Este bot√£o s√≥ deve aparecer em telas pequenas -->
				<button class="navbar-toggler d-lg-none" type="button"
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
				</div>
			</div>
		</nav>
	</header>
	<div class="color-orange barra-header">
		<div class="nomeCli">
			<div class="perfilCli color-light shadow rounded">
				<figure>
					<img class="foto" src="./assets/foto.svg" alt="Foto de perfil">
				</figure>
				<div
					class="nome d-flex justify-content-center flex-column align-items-center">
					<p class="fw-semibold text-uppercase mb-0">${nome}</p>
					<div
						class="d-flex justify-content-center flex-row align-items-center">
						<p class="fw-semibold">${pontos} PONTOS</p>
						<a class="nav-link dropdown-toggle font-nav dropdown-check ms-3"
							href="" role="button" data-bs-toggle="dropdown"
							aria-expanded="false"></a>
						<ul class="dropdown-menu shadow">
							<div class="sidebar-header">
								<div
									class="d-flex justify-content-center align-items-baseline flex-column">
									<!-- N veis e Pontua  es -->
									<div
										class="nivel-row d-flex justify-content-center align-items-center flex-row"
										data-points="100">
										<div
											class="d-flex justify-content-center flex-column align-items-center">
											<div
												class="pontuacao d-flex justify-content-center align-items-center color-orange">
												<i class="bi bi-lock-fill text-light"></i>
											</div>
											<i
												class="bi d-flex justify-content-center align-items-center bi-three-dots-vertical separacao fs-4"></i>
										</div>
										<div class="texto flex-column ms-2">
											<p class="nivel fw-medium mb-0">NÌVEL INICIANTE</p>
											<p class="pontos fw-light" data-points="100">100 PONTOS</p>

										</div>
									</div>
									<div
										class="nivel-row d-flex justify-content-center align-items-center flex-row"
										data-points="200">
										<div
											class="d-flex justify-content-center flex-column align-items-center">
											<div
												class="pontuacao d-flex justify-content-center align-items-center color-orange">
												<i class="bi bi-lock-fill text-light"></i>
											</div>
											<i
												class="bi d-flex justify-content-center align-items-center bi-three-dots-vertical separacao fs-4"></i>
										</div>
										<div class="texto flex-column ms-2">
											<p class="nivel fw-medium mb-0">NÕVEL BRONZE</p>
											<p class="pontos fw-light" data-points="200">200 PONTOS</p>
										</div>
									</div>
									<div
										class="nivel-row d-flex justify-content-center align-items-center flex-row"
										data-points="300">
										<div
											class="d-flex justify-content-center flex-column align-items-center">
											<div
												class="pontuacao d-flex justify-content-center align-items-center color-orange">
												<i class="bi bi-lock-fill text-light"></i>
											</div>
											<i
												class="bi d-flex justify-content-center align-items-center bi-three-dots-vertical separacao fs-4"></i>
										</div>
										<div class="texto flex-column ms-2">
											<p class="nivel fw-medium mb-0">NÕVEL PRATA</p>
											<p class="pontos fw-light" data-points="300">300 PONTOS</p>
										</div>
									</div>
									<div
										class="nivel-row d-flex justify-content-center align-items-center flex-row"
										data-points="400">
										<div
											class="d-flex justify-content-center flex-column align-items-center">
											<div
												class="pontuacao d-flex justify-content-center align-items-center color-orange">
												<i class="bi bi-lock-fill text-light"></i>
											</div>
											<i
												class="bi d-flex justify-content-center align-items-center bi-three-dots-vertical separacao fs-4"></i>
										</div>
										<div class="texto flex-column ms-2">
											<p class="nivel fw-medium mb-0">NÕVEL OURO</p>
											<p class="pontos fw-light" data-points="400">400 PONTOS</p>
										</div>
									</div>
									<div
										class="nivel-row d-flex justify-content-center align-items-center flex-row"
										data-points="500">
										<div
											class="d-flex justify-content-center flex-column align-items-center">
											<div
												class="pontuacao d-flex justify-content-center align-items-center color-orange">
												<i class="bi bi-lock-fill text-light"></i>
											</div>
											<i
												class="bi d-flex justify-content-center align-items-center bi-three-dots-vertical text-light fs-4"></i>
										</div>
										<div class="texto flex-column ms-2">
											<p class="nivel fw-medium mb-0">NÕVEL DIAMANTE</p>
											<p class="pontos fw-light">500 PONTOS</p>
										</div>
									</div>
									<div
										class="pt-2 pb-2 d-flex justify-content-center align-items-center color-orange rounded text-light consultoraatual">
										<figure class="mb-0">
											<img class="consultora" src="./assets/foto.svg"
												alt="Consultora">
										</figure>
										<p class="nomeconsultora fw-medium mb-0 ms-2">
											CONSULTORA<br>${consultora}
										</p>
									</div>
								</div>
							</div>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<main class="container-fluid">
		<div class="row">
			<div class="col-md-3 p-0">
				<div class="sidebar">
					<div
						class="perfil color-light d-flex justify-content-center align-items-center flex-column shadow rounded">
						<figure>
							<img class="foto" src="./assets/foto.svg" alt="Foto de perfil">
						</figure>
						<div
							class="nome d-flex justify-content-center flex-column align-items-center">
							<p class="fw-semibold mb-0 text-uppercase fs-5">${nome}</p>
							<p class="fw-semibold fs-5 mt-2">${pontos} PONTOS</p>
						</div>
						<section
							class="d-flex justify-content-center align-items-baseline flex-column">
							<!-- N veis e Pontua  es -->
							<div
								class="nivel-row d-flex justify-content-center align-items-center flex-row"
								data-points="100">
								<div
									class="d-flex justify-content-center flex-column align-items-center">
									<div
										class="pontuacao d-flex justify-content-center align-items-center color-orange">
										<i class="icon bi-check-lg text-light"></i>
									</div>
									<i
										class="bi d-flex justify-content-center align-items-center bi-three-dots-vertical separacao fs-4"></i>
								</div>
								<div class="texto flex-column ms-2">
									<p class="nivel fw-medium mb-0">NÕVEL INICIANTE</p>
									<p class="pontos fw-light" data-points="100">100 PONTOS</p>
								</div>
							</div>
							<div
								class="nivel-row d-flex justify-content-center align-items-center flex-row"
								data-points="200">
								<div
									class="d-flex justify-content-center flex-column align-items-center">
									<div
										class="pontuacao d-flex justify-content-center align-items-center color-orange">
										<i class="bi bi-lock-fill text-light"></i>
									</div>
									<i
										class="bi d-flex justify-content-center align-items-center bi-three-dots-vertical separacao fs-4"></i>
								</div>
								<div class="texto flex-column ms-2">
									<p class="nivel fw-medium mb-0">NÕVEL BRONZE</p>
									<p class="pontos fw-light" data-points="200">200 PONTOS</p>
								</div>
							</div>
							<div
								class="nivel-row d-flex justify-content-center align-items-center flex-row"
								data-points="300">
								<div
									class="d-flex justify-content-center flex-column align-items-center">
									<div
										class="pontuacao d-flex justify-content-center align-items-center color-orange">
										<i class="bi bi-lock-fill text-light"></i>
									</div>
									<i
										class="bi d-flex justify-content-center align-items-center bi-three-dots-vertical separacao fs-4"></i>
								</div>
								<div class="texto flex-column ms-2">
									<p class="nivel fw-medium mb-0">NÕVEL PRATA</p>
									<p class="pontos fw-light" data-points="300">300 PONTOS</p>
								</div>
							</div>
							<div
								class="nivel-row d-flex justify-content-center align-items-center flex-row"
								data-points="400">
								<div
									class="d-flex justify-content-center flex-column align-items-center">
									<div
										class="pontuacao d-flex justify-content-center align-items-center color-orange">
										<i class="bi bi-lock-fill text-light"></i>
									</div>
									<i
										class="bi d-flex justify-content-center align-items-center bi-three-dots-vertical separacao fs-4"></i>
								</div>
								<div class="texto flex-column ms-2">
									<p class="nivel fw-medium mb-0">NÕVEL OURO</p>
									<p class="pontos fw-light">400 PONTOS</p>
								</div>
							</div>
							<div
								class="nivel-row d-flex justify-content-center align-items-center flex-row"
								data-points="500">
								<div
									class="d-flex justify-content-center flex-column align-items-center">
									<div
										class="pontuacao d-flex justify-content-center align-items-center color-orange">
										<i class="bi bi-lock-fill text-light"></i>
									</div>
									<i
										class="bi d-flex justify-content-center align-items-center bi-three-dots-vertical text-light fs-4"></i>
								</div>
								<div class="texto flex-column ms-2">
									<p class="nivel fw-medium mb-0">NÕVEL DIAMANTE</p>
									<p class="pontos fw-light" data-points="500">500 PONTOS</p>
								</div>
							</div>
							<div
								class="pt-2 pb-2 d-flex justify-content-center align-items-center color-orange rounded text-light consultoraatual">
								<figure class="mb-0">
									<img class="consultora" src="./assets/foto.svg"
										alt="Consultora">
								</figure>
								<p class="nomeconsultora fw-medium mb-0 ms-3">
									CONSULTORA<br>${consultora}
								</p>
							</div>
						</section>
					</div>
				</div>
			</div>
			<div class="col-md-9 main-content ps-5 pe-5">
				<div
					class="paraVoce d-flex justify-content-flex-start align-items-center">
					<i class="bi bi-journal-text fs-3"></i>
					<p class="mb-0 fs-4 fw-semibold ms-2">PARA VOC </p>
				</div>
				<div class="cards-container pb-3 ps-3 pe-3">
					<c:forEach var="tags" items="${tags}">
						<div class="card" data-categoria="${tags.descricao}">
							<img src="data:image/png;base64,${tags.imagemBase64}"
								class="card-img-top" alt="Perfumaria">
							<div class="card-body">
								<h5 class="card-title">${tags.descricao}</h5>
							</div>
						</div>
					</c:forEach>
				</div>

			</div>
		</div>
	</main>
	<!-- Chamada JS do BS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-...your-integrity-hash..." crossorigin="anonymous"></script>
	<script>
	window.addEventListener('load', function() {
	    const userPoints = parseInt('${pontos}', 10);

	    console.log('User Points:', userPoints);

	    function updateIcons() {
	        document.querySelectorAll('.nivel-row').forEach(function(nivelRow) {
	            const pointsRequired = parseInt(nivelRow.getAttribute('data-points'), 10);
	            const icon = nivelRow.querySelector('.pontuacao i');

	            console.log('Points Required:', pointsRequired);
	            console.log('Current Icon Classes:', icon.classList);

	            if (userPoints >= pointsRequired) {
	                icon.classList.remove('bi-lock-fill');
	                icon.classList.add('bi-check-lg');
	            } else {
	                icon.classList.remove('bi-check-lg');
	                icon.classList.add('bi-lock-fill');
	            }
	        });
	    }

	    updateIcons();

	    document.querySelectorAll('.card').forEach(function(card) {
	        card.addEventListener('click', function() {
	            console.log('Card clicked'); // Para verificar se o clique est· funcionando
	            const categoria = this.getAttribute('data-categoria');
	            window.location.href = 'produtoCliente?categoria=' + encodeURIComponent(categoria);
	        });
	    });
	});

	</script>

</body>
</html>
