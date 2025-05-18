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
<link rel="stylesheet" href="./css/produtos.css">

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
				<!-- Este botÃ£o sÃ³ deve aparecer em telas pequenas -->
				<button class="navbar-toggler d-lg-none" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarNav"
					aria-controls="navbarNav" aria-expanded="false"
					aria-label="Toggle navigation">
					<i class="bi bi-filter-right"></i>
				</button>
				<div class="collapse navbar-collapse" id="navbarNav">
					<ul class="navbar-nav">
						<li class="nav-item ms-2 me-2"><a
							class="nav-link font-nav text-orange fw-medium me-2" href="perfilCliente">
								<p class="perfil">PERFIL</p> <i
								class="bi perfil-icon bi-person-circle text-orange"></i>
						</a></li>
					</ul>
					<div class="linha-vertical"></div>
					<ul class="navbar-nav ms-auto me-2 d-lg-flex">
						<!-- BotÃ£o Carrinho -->
						<div class="d-flex ms-3">
							<a class="carrinho-icon me-3" data-bs-toggle="offcanvas"
								href="#offcanvasExample" role="button"
								aria-controls="offcanvasExample"> <i
								class="bi bi-cart3 fs-4 text-orange"></i> <i
								class="bi bi-circle-fill  circle text-green position-absolute"><p
										class="bolinha position-absolute quant text-light"></p></i>
							</a> <a class="carrinho nav-link" data-bs-toggle="offcanvas"
								href="#offcanvasExample" role="button"
								aria-controls="offcanvasExample"> <span
								class=" font-nav text-orange fw-medium me-3">CARRINHO</span> <i
								class="bi bi-circle-fill circle text-green position-absolute"><p
										class="bolinha2 position-absolute quant text-light"></p></i>
							</a>
							<div class="offcanvas offcanvas-end" tabindex="-1"
								id="offcanvasExample" aria-labelledby="offcanvasExampleLabel">
								<div
									class="offcanvas-header p-2 color-orange d-flex flex-row align-items-center">
									<div class="d-flex flex-row align-items-center ms-3">
										<i class="bi bi-cart3 carrinho-icon fs-4 text-light me-2"></i>
										<h5 class="offcanvas-title text-light"
											id="offcanvasExampleLabel">MEU CARRINHO</h5>
									</div>
									<button type="button" class="btn color-light"
										data-bs-dismiss="offcanvas" aria-label="Close">
										<i class="bi bi-x text-light fs-2"></i>
									</button>
								</div>
								<div class="offcanvas-body color-light">
									<div class="produtoscar d-flex flex-column mb-4">

									</div>
								</div>
								<div class="subtotal mt-5">
									<hr class="linhatotal text-orange ms-4 me-4">
									<div class="d-flex flex-row justify-content-between align-items-center ps-4 pe-4">
										<p class="quantprod"></p>
										<p class="totalsub text-orange fw-semibold fs-4"></p>
									</div>
									
									<form class="d-flex justify-content-center" action = "produtoCliente" method = "post">
									<input id="produtosData" type="hidden" name="produtosData">
										<button type="submit" id="cadastrar"
											class="btn pt-2 mb-5 pb-2 ps-5 pe-5 mt-2 fw-medium text-light color-green fs-4">FINALIZAR
											PEDIDO</button>
									</form>
								</div>

							</div>
						</div>
					</ul>
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
	<!-- Fim navbar -->

	<!-- Produtos -->
	<section id="collection">
		<div class="container produtos">
			<div
				class="title-section mt-5 mb-4 d-flex justify-content-between align-items-center">
				<div>
					<h2 class="main-title fw-semibold">${categoria}</h2>
					<p class="subtitle fw-semibold">${produtos.size()} produtos
						encontrados</p>
				</div>
			</div>

			<div class="row g-0">
				<c:forEach var="produto" items="${produtos}">
					<div
						class="card-produto shadow col-md-6 col-lg-4 col-xl-3 p-2 new mb-4"
						data-quantidade="${produto.quantidade}" data-id="${produto.id}"
						data-nome="${produto.descricao}">
						<div class="card-img pt-4 pe-4 ps-4 pb-2 ">
							<img src="data:image/png;base64,${produto.imagemBase64}"
								class="imgprod">
						</div>
						<div class="text-center">
							<p class="text-capitalize my-1">${produto.descricao}</p>
							<span class="fw-bold fs-5">R$ ${produto.valor}</span>
							<p class="quantDisp my-1 fw-medium">${produto.quantidade} em estoque</p>
						</div>
						<div class="btn-container mt-2">
							<button type="button" class="btn btn-cart"
								onclick="addToCart(event)">
								<i class="bi bi-cart-plus text-orange fs-4"></i>
							</button>
							<div
								class="quantidade-wrapper d-flex justify-content-center align-items-center flex-row d-none">
								<button type="button" class="btn-quantidade btn-minus">-</button>
								<input class="quantidade-input fw-semibold text-center"
									value="1" />
								<button type="button" class="btn-quantidade btn-plus">+</button>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</section>

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

	<script>
	let subtotal = 0;
	let totalItems = 0;
	let cartItems = {}; 
	
	function updateLocalStorage() {
	    localStorage.setItem('cartItems', JSON.stringify(cartItems));
	    localStorage.setItem('subtotal', subtotal);
	    localStorage.setItem('totalItems', totalItems);
	}

	function addToCart(event) {
	    const button = event.currentTarget;
	    const card = button.closest('.card-produto');

	    const id = card.getAttribute('data-id');
	    const nome = card.getAttribute('data-nome');
	    const valorText = card.querySelector('.fw-bold').textContent.replace('R$ ', '').replace(',', '.');
	    const imagem = card.querySelector('.imgprod').src;
	    const quantidadeInput = card.querySelector('.quantidade-input');
	    const quantidadeDisponivel = parseInt(card.querySelector('.quantDisp').textContent);

	    const valor = parseFloat(valorText);
	    let quantidade = parseInt(quantidadeInput.value);

	    
	    if (cartItems[id]) {
	        cartItems[id].quantidade += quantidade;
	    } else {
	        cartItems[id] = { id, nome, valor, quantidade, imagem };
	    }

	   
	    const total = cartItems[id].valor * cartItems[id].quantidade;

	    let produtoElement = document.querySelector(`.produto[data-id="${id}"]`);
	    if (!produtoElement) {
	        produtoElement = document.createElement('div');
	        produtoElement.className = 'produto d-flex justify-content-center align-items-center flex-row pt-2 ps-2 pe-2';
	        produtoElement.setAttribute('data-id', id);
	        produtoElement.innerHTML = '<figure>' +
	            '<img class="prod" src="' + imagem + '" alt="' + nome + '">' +
	            '</figure>' +
	            '<div class="d-flex flex-column pt-3">' +
	            '<p class="nomeprod">' + nome + '</p>' +
	            '<div class="d-flex align-items-center flex-row">' +
	            '<p class="precoprod text-orange">R$ ' + valor.toFixed(2) + '</p>' +
	            '<div class="btn-container1">' +
	            '<div class="quantidade-wrapper d-flex justify-content-center align-items-center flex-row">' +
	            '<button type="button" class="btn-quantidade btn-minus">-</button>' +
	            '<input class="quantidade-input fw-semibold text-center" value="' + quantidade + '" />' +
	            '<button type="button" class="btn-quantidade btn-plus">+</button>' +
	            '</div>' +
	            '</div>' +
	            '<p class="total text-orange fw-semibold">R$ ' + total.toFixed(2) + '</p>' +
	            '</div>' +
	            '</div>';

	        const cartBody = document.querySelector('.produtoscar');
	        cartBody.appendChild(produtoElement);
	    } else {
	        produtoElement.querySelector('.quantidade-input').value = quantidade;
	        produtoElement.querySelector('.total').textContent = 'R$ ' + total.toFixed(2);
	    }

	    
	    atualizaCarrinho(valor, quantidade);
	    
	   
	    const btnMinus = produtoElement.querySelector('.btn-minus');
	    const btnPlus = produtoElement.querySelector('.btn-plus');
	    const quantityInput = produtoElement.querySelector('.quantidade-input');

	    btnMinus.addEventListener('click', () => {
	        if (cartItems[id].quantidade >= 1) {
	            cartItems[id].quantidade--;
	            quantityInput.value = cartItems[id].quantidade;
	            atualizaCarrinho(valor, -1);
	        }
	    });

	    btnPlus.addEventListener('click', () => {
	        if (cartItems[id].quantidade < quantidadeDisponivel) {
	            cartItems[id].quantidade++;
	            quantityInput.value = cartItems[id].quantidade;
	            atualizaCarrinho(valor, 1);
	        } 
	    });
	}

	function atualizaCarrinho(valor, quantidade) {
	    subtotal += valor * quantidade;
	    totalItems += quantidade;

	    const subtotalElement = document.querySelector('.quantprod');
	    subtotalElement.textContent = 'Total (' + totalItems + ' produtos)';
	    
	    const totalElement = document.querySelector('.totalsub');
	    totalElement.textContent = 'R$ ' + subtotal.toFixed(2);
	    
	    const bolinha = document.querySelector('.bolinha');
	    bolinha.textContent = totalItems;
	    
	    const bolinha2 = document.querySelector('.bolinha2');
	    bolinha2.textContent = totalItems;
	};
	
		
	document.getElementById('cadastrar').addEventListener('click', (event) => {
	    event.preventDefault();
	    
	    const produtosParaEnviar = [];
	    Object.keys(cartItems).forEach(id => {
	        const item = cartItems[id];
	        produtosParaEnviar.push(item.id + ',' + item.quantidade + ',' + item.valor.toFixed(2)); 
	    });

	    console.log("Produtos para enviar:", produtosParaEnviar); 

	    if (produtosParaEnviar.length > 0) {
	        document.getElementById('produtosData').value = produtosParaEnviar.join(';');
	        console.log("Dados preenchidos no campo invisível:", document.getElementById('produtosData').value);
	    } else {
	        console.error("Nenhum produto no carrinho para enviar.");
	    }

	    event.target.closest('form').submit();
	});
	
	

		</script>


</body>
</html>