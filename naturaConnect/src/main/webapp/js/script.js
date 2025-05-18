$(document).ready(function() {
    function atualizarVisibilidade() {
        // Verifica qual botão está ativo e ajusta a visibilidade dos campos
        if ($('.btn-toggle .btn.cliente').hasClass('active')) {
            $('#tipoUsuario').val('cliente');
            $('.cliente-only').show();
            $('.consultora-only').hide();
            $('.cepcliente-only').show();
            $('.cepconsultora-only').hide();
            $('.ruacli').show();
            $('.ruacon').hide();
        } else if ($('.btn-toggle .btn.consultora').hasClass('active')) {
            $('#tipoUsuario').val('consultora');
            $('.cliente-only').show();
            $('.consultora-only').show();
            $('.cepconsultora-only').show();
            $('.cepcliente-only').hide();
            $('.ruacon').show();
            $('.ruacli').hide();
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

        // Atualiza o valor do campo oculto com base no botão clicado
        atualizarVisibilidade();
    });
});

document.addEventListener("DOMContentLoaded", () => {
    const consulta = document.querySelector('.consulta');
    if (consulta) {
        consulta.style.maxHeight = `${consulta.scrollHeight}px`;
    }
});

const togglePassword = document.querySelector('#togglePass');
const togglePassword2 = document.querySelector('#togglePass2');
const password = document.querySelector('#password');
const confpassword = document.querySelector('#confpassword');

togglePassword.addEventListener("click", function () {
    const type = password.type === "password" ? "text" : "password";
    password.type = type;
    this.classList.toggle("bi-eye");
    this.classList.toggle("bi-eye-slash");
});

togglePassword2.addEventListener("click", function () {
    const type = confpassword.type === "password" ? "text" : "password";
    confpassword.type = type;
    this.classList.toggle("bi-eye");
    this.classList.toggle("bi-eye-slash");
});


//máscara

const masks = {
    cpf(value) {
        return value
            .replace(/\D/g, '') // Remove todos os caracteres não numéricos
            .replace(/(\d{3})(\d)/, '$1.$2') // Adiciona um ponto após os primeiros três dígitos
            .replace(/(\d{3})(\d)/, '$1.$2') // Adiciona um ponto após os próximos três dígitos
            .replace(/(\d{3})(\d{1,2})/, '$1-$2') // Adiciona um hífen antes dos últimos dois dígitos
            .replace(/(-\d{2})\d+?$/, '$1'); // Impede que mais dígitos sejam digitados após os dois últimos
    },

    tel(value) {
        return value
        .replace(/\D/g, '')
        .replace(/(\d{2})(\d)/, '($1) $2')
        .replace(/(\d{4})(\d)/, '$1-$2')
        .replace(/(\d{4})-(\d)(\d{4})/, '$1$2-$3')
        .replace(/(-\d{4})\d+?$/, '$1') 
    },

    cep(value) {
        return value
            .replace(/\D/g, '') // Remove todos os caracteres não numéricos
            .replace(/(\d{5})(\d)/, '$1-$2') // Adiciona um hífen entre os grupos de cinco e três dígitos
            .replace(/(-\d{3})\d+?$/, '$1'); // Impede que mais dígitos sejam digitados após os últimos três
    }
};

document.querySelectorAll('input[data-js]').forEach(($input) => {
    const field = $input.dataset.js;

    $input.addEventListener('input', (e) => {
        if (masks[field]) {
            e.target.value = masks[field](e.target.value);
        }
    }, false);
});

// Validação

const button = document.getElementById('cadastrar');

button.addEventListener('click', (event) => {
    event.preventDefault();
    let isValid = true; // Variável de controle
    
    const tipoUsuario = $('#tipoUsuario').val(); // Pega o valor do tipo de usuário
    
    const nome = document.getElementById('nome');
    if (nome.value === '') {
        nome.classList.add("errorInput");
        isValid = false;
    } else {
        nome.classList.remove("errorInput");
    }

    const cpf = document.getElementById('cpf');
    if (cpf.value === '') {
        cpf.classList.add("errorInput");
        isValid = false;
    } else {
        cpf.classList.remove("errorInput");
    }

    // Se o tipo de usuário for consultora, valida os campos específicos de consultora
    if (tipoUsuario === 'consultora') {
        const codigoconsultora = document.getElementById('codigoconsultora');
        const cepcon = document.getElementById('cepcon');
        const ruacon = document.getElementById('ruacon');

        if (codigoconsultora.value === '') {
            codigoconsultora.classList.add("errorInput");
            isValid = false;
        } else {
            codigoconsultora.classList.remove("errorInput");
        }

        if (cepcon.value === '' || cepcon.value.length !== 9) {
            cepcon.classList.add("errorInput");
            isValid = false;
        } else {
            cepcon.classList.remove("errorInput");
        }

        if (ruacon.value === '') {
            ruacon.classList.add("errorInput");
            isValid = false;
        } else {
            ruacon.classList.remove("errorInput");
        }
    }

    // Se o tipo de usuário for cliente, valida os campos específicos de cliente
    if (tipoUsuario === 'cliente') {
        const cepcli = document.getElementById('cepcli');
        const ruacli = document.getElementById('ruacli');

        if (cepcli.value === '' || cepcli.value.length !== 9) {
            cepcli.classList.add("errorInput");
            isValid = false;
        } else {
            cepcli.classList.remove("errorInput");
        }

        if (ruacli.value === '') {
            ruacli.classList.add("errorInput");
            isValid = false;
        } else {
            ruacli.classList.remove("errorInput");
        }
    }

    const num = document.getElementById('num');
    const bairro = document.getElementById('bairro');
    const cidade = document.getElementById('cidade');
    const estado = document.getElementById('estado');
    const email = document.getElementById('email');
    const tel = document.getElementById('tel');
    const password = document.getElementById('password');
    const confpassword = document.getElementById('confpassword');

    // Valida os campos que são comuns para ambos os tipos de usuário
    if (num.value === '') {
        num.classList.add("errorInput");
        isValid = false;
    } else {
        num.classList.remove("errorInput");
    }

    if (bairro.value === '') {
        bairro.classList.add("errorInput");
        isValid = false;
    } else {
        bairro.classList.remove("errorInput");
    }

    if (cidade.value === '') {
        cidade.classList.add("errorInput");
        isValid = false;
    } else {
        cidade.classList.remove("errorInput");
    }

    if (estado.value === '') {
        estado.classList.add("errorInput");
        isValid = false;
    } else {
        estado.classList.remove("errorInput");
    }

    if (email.value === '' || email.value.indexOf("@") === -1 || email.value.indexOf(".") === -1 || (email.value.indexOf(".") - email.value.indexOf("@") === 1)) {
        email.classList.add("errorInput");
        isValid = false;
    } else {
        email.classList.remove("errorInput");
    }

    if (tel.value === '') {
        tel.classList.add("errorInput");
        isValid = false;
    } else {
        tel.classList.remove("errorInput");
    }

    if (password.value === '') {
        password.classList.add("errorInput");
        isValid = false;
    } else {
        password.classList.remove("errorInput");
    }

    if (confpassword.value === '') {
        confpassword.classList.add("errorInput");
        isValid = false;
    } else {
        confpassword.classList.remove("errorInput");
    }

    if (isValid) {
        document.querySelector('form').submit(); // Submissão do formulário se todos os campos forem válidos
    }
});

document.querySelectorAll('input').forEach(($input) => {
    $input.addEventListener('input', (e) => {
        e.target.classList.remove("errorInput");
    });
});

