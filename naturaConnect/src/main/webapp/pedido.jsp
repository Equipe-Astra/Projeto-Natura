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

.botão {
	width: 13rem !important;
}

.bi-bag-heart {
	font-size: 4rem !important;
	color: #05705D;
}

.fonte {
	font-size: 0.9rem !important;
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
    background: rgba(0, 0, 0, 0.7);
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
					<p class="subtitle fw-semibold">${produtos.size()}</p>
				</div>
			</div>

			<div class="row g-0">
				<c:forEach var="produto" items="${produtos}">
					<div
						class="card-produto shadow col-md-6 col-lg-4 col-xl-3 p-2 new mb-4"
						data-quantidade="${produto.quantidade}" data-id="${produto.id}"
						data-nome="${produto.descricao}">
						<div class="card-img p-4 position-relative">
							<img src="data:image/png;base64,${produto.imagemBase64}"
								class="imgprod">
						</div>
						<div class="text-center">
							<p class="text-capitalize my-1">${produto.descricao}</p>
							<span class="fw-bold fs-5">R$ ${produto.valor}</span>
							<p class="quantDisp text-capitalize my-1">${produto.quantidade}</p>
						</div>
						<div class="btn-container mt-3">
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
<div id="overlay" class="overlay"></div>
	<div id="errorPopup" class="error-box">
		<div class="circle">
			<i class="bi bi-bag-heart"></i>
		</div>
		<!-- Aqui adicionamos a imagem que mudará -->
		<img id="errorImg" class="error-img" src="" alt="Erro">
		<div id="errorMessage"></div>
		<div class="errorButton">
			<button class=" botão d-flex justify-content-center"><a href="/naturaConnect/perfilCliente" class="nav-link fw-medium fonte">CONTINUAR COMPRANDO</a></button>
		</div>
	</div>
	<footer
		class="shadow-lg pt-2 pb-2 d-flex justify-content-center align-items-center color-light">
		<p class="fw-regular mb-0">
			©TECH FIVE <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2024
		</p>
		
	</footer>


	
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
	var errorMessage = '<%=request.getAttribute("errorMessage") != null ? request.getAttribute("errorMessage") : ""%>';
    var errorType = '<%=request.getAttribute("errorType") != null ? request.getAttribute("errorType") : ""%>';
    var errorImg;

    if (errorMessage && errorMessage !== "") {
        var errorImg;

        switch (errorType) {
            case 'email':
                errorImg = './assets/pedido.svg';
                break;
            default:
                errorImg = './assets/error-default.png';
                break;
        }

        showErrorPopup(errorMessage, errorImg);
    }
};
    
</script>


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
	        produtoElement.className = 'produto d-flex flex-row pt-2 ps-2 pe-2';
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
	            '</div>' +
	            '<p class="total text-orange fw-semibold">R$ ' + total.toFixed(2) + '</p>' +
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
	        if (cartItems[id].quantidade > 1) {
	            cartItems[id].quantidade--;
	            quantityInput.value = cartItems[id].quantidade;
	            atualizaCarrinho(-valor, -1);
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