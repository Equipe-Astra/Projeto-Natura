<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Natura Connect</title>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.10.0/font/bootstrap-icons.min.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com/" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Rubik:ital,wght@0,300..900;1,300..900&display=swap"
	rel="stylesheet">

<!-- bootstrap css -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
<!-- css -->
<link rel="stylesheet" href="css/estoque.css">
</head>
<body>

	<!-- navbar -->
	<header class="color-light shadow">
		<nav class="navbar navbar-expand-lg background-nav ps-4 pe-4">
			<div class="container-fluid">
				<a class="navbar-brand logo" href="#"> <img class="logoHeader"
					src="assets/logo.svg" alt="Logo" />
				</a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarNav"
					aria-controls="navbarNav" aria-expanded="false"
					aria-label="Toggle navigation">
					<i class="bi bi-filter-right"></i>
				</button>
				<div class="collapse navbar-collapse" id="navbarNav">
					<ul class="navbar-nav ms-auto me-2">
						<div class="d-flex">
							<a class="nav-link font-nav text-orange fw-medium me-2" href="Carteira">
								CARTEIRA </a>
						</div>
					</ul>

					<ul class="navbar-nav">
						<div class="d-flex">
							<a class="nav-link font-nav text-orange fw-medium me-2" href="estoqueConsultora">
								ESTOQUE </a>
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
								<p class="perfil">PERFIL</p> <i
								class="bi perfil-icon bi-person-circle text-orange"></i>
							</a>
						</div>
					</ul>
				</div>
			</div>
		</nav>
	</header>

	<section id="collection" class="">
		<div class="container produtos">
			<form id="estoque-form" action="estoqueConsultora" method="post">
				<div
					class="title-section mb-5 d-flex justify-content-between align-items-center">
					<div>
						<h2 class="main-title fw-semibold">MEU ESTOQUE</h2>
						<p class="subtitle fw-semibold">${produtos.size()} produtos encontrados</p>
					</div>
					<button type="button" id="btn-alterar-estoque"
						class="btn position-relative">
						<i class="bi bi-check-circle text-orange me-2 fs-5"></i>
						<span class="btn-text fs-5">Confirmar alterações</span>
					</button>
				</div>

				<div class="row g-0">
					<c:forEach var="produto" items="${produtos}">
						<div class="card-produto shadow col-md-6 col-lg-4 col-xl-3 p-2 d-flex justify-content-center alignt-items-center flex-column new mb-4"
							data-id="${produto.id}">
							<div class="card-img position-relative">
								<img src="data:image/png;base64,${produto.imagemBase64}"
									class="imgprod">
							</div>
							<div class="text-center">
								<p class="text-capitalize my-1">${produto.descricao}</p>
								<span class="fw-bold fs-5">R$ ${fn:replace(fn:replace(produto.valor, '.', ','), '.', ',')}</span>
							</div>
							<div class="btn-container mt-2">
								<div class="quantidade-wrapper d-flex justify-content-center align-itens-center flex-row">
									<button type="button" class="btn-quantidade btn-minus">-</button>
									<input type="text" class="quantidade-input fw-semibold text-center"
										value="${produto.quantidade}" />
									<button type="button" class="btn-quantidade btn-plus">+</button>
								</div>
							</div>
							<input type="hidden" name="product-${produto.id}"
								class="product-id" value="${produto.id}" /> <input
								type="hidden" name="quantity-${produto.id}"
								class="product-quantity" value="${produto.quantidade}" />
						</div>
					</c:forEach>
				</div>

				<input type="hidden" id="products-data" name="products-data" />
			</form>
		</div>
	</section>
	<footer
		class="shadow-lg pt-2 pb-2 mt-2 d-flex justify-content-center align-items-center color-light">
		<p class="fw-regular mb-0">
			©TECH FIVE <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2024
		</p>
	</footer>

	<!-- bootstrap js -->
	<script src="bootstrap-5.0.2-dist/js/bootstrap.min.js"></script>
	<script src="js/estoque.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
	<script>
	document.addEventListener('DOMContentLoaded', function() {
	  
	    function updateQuantityFields() {
	        document.querySelectorAll('.card-produto').forEach(card => {
	            let id = card.getAttribute('data-id');
	            let quantityInput = card.querySelector('.quantidade-input').value;
	            let hiddenField = card.querySelector(`.product-quantity`);
	            if (hiddenField) hiddenField.value = quantityInput;
	        });
	    }

	   
	    document.querySelectorAll('.btn-plus').forEach(button => {
	        button.addEventListener('click', function() {
	            let card = this.closest('.card-produto');
	            let input = card.querySelector('.quantidade-input');
	            if (input) {
	                let currentValue = parseInt(input.value);
	                let newQuantity = currentValue + 1;
	                input.value = newQuantity;
	                updateQuantityFields(); 
	            }
	        });
	    });

	    document.querySelectorAll('.btn-minus').forEach(button => {
	        button.addEventListener('click', function() {
	            let card = this.closest('.card-produto');
	            let input = card.querySelector('.quantidade-input');
	            if (input) {
	                let currentValue = parseInt(input.value);
	                if (currentValue > 0) {
	                    let newQuantity = currentValue - 1;
	                    input.value = newQuantity;
	                    updateQuantityFields(); 
	                }
	            }
	        });
	    });
	    

	    document.querySelectorAll('.quantidade-input').forEach(input => {
	        input.addEventListener('input', function() {
	            let card = this.closest('.card-produto');
	            let newQuantity = this.value;
	            if (!isNaN(newQuantity) && newQuantity >= 0) {
	                updateQuantityFields(); 
	            }
	        });
	    });

	    const btnAlterarEstoque = document.getElementById('btn-alterar-estoque');
	    btnAlterarEstoque.addEventListener('click', function() {
	        updateQuantityFields();
	        document.getElementById('estoque-form').submit();
	    });
	});
</script>

</body>
</html>