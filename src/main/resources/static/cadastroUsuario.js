const $nomeCompletoInput = document.querySelector('input[name="nome"');
const $cpfInput = document.querySelector('input[name="cpf"');
const $dataNascimento = document.querySelector('input[name="dataNascimento"');
const $telefone1Input = document.querySelector('input[name="telefone1"');
const $telefone2Input = document.querySelector('input[name="telefone2"');
const $pesoInput = document.querySelector('input[name="peso"');
const $alturaInput = document.querySelector('input[name="altura"');
const $sexoSelect = document.querySelector('select[name="sexo"');
const $tipoUsuarioSelect = document.querySelector('select[name="tipoUsuario"');
const $empresaSelect = document.querySelector('select[name="empresa"');
const $funcaoEmpresaInput = document.querySelector(
  'input[name="funcaoEmpresa"'
);
const $ruaInput = document.querySelector('input[name="rua"');
const $numeroInput = document.querySelector('input[name="numero"');
const $bairroInput = document.querySelector('input[name="bairro"');
const $cepInput = document.querySelector('input[name="cep"');
const $estadoSelect = document.querySelector('select[name="estado"');
const $cidadeSelect = document.querySelector('select[name="cidade"');
const $emailInput = document.querySelector('input[name="email"');
const $senhaInput = document.querySelector('input[name="senha"');

const $submitButton = document.querySelector('#submitButton');

$submitButton.addEventListener('click', submitForm);

function submitForm() {
  let dados = {
    cpf: $cpfInput.value,
    nome: $nomeCompletoInput.value,
    email: $emailInput.value,
    senha: $senhaInput.value,
    dataNascimento: $dataNascimento.value,
    sexo: $sexoSelect.value,
    peso: $pesoInput.value,
    altura: $alturaInput.value,
    funcaoEmpresa: $funcaoEmpresaInput.value,
    empresa: {
      cnpj: $empresaSelect.value,
    },
    tipoUsuario: {
      id: $tipoUsuarioSelect.value,
    },
    endereco: {
      rua: $ruaInput.value,
      numero: $numeroInput.value,
      // complemento: ,
      cidade: {
        id: $cidadeSelect.value,
      },
      cep: $cepInput.value,
      bairro: $bairroInput.value,
    },
  };

  fetch('/api/usuario', {
    method: 'POST',
    headers: {
      Accept: 'application/json, text/plain, */*',
      'Content-Type': 'application/json',
      Authorization: 'Bearer ' + token,
    },
    body: JSON.stringify(dados),
  }).then((res) => {
    if (res.statusCode === 200) {
      // ADICIONA TELEFONES
      let telefones = [];
      if ($telefone1Input.value?.length > 7) {
        telefones.push({
          id: { cpf: dados.cpf, telefone: $telefone1Input.value },
        });
      }
      if ($telefone2Input.value?.length > 7) {
        telefones.push({
          id: { cpf: dados.cpf, telefone: $telefone2Input.value },
        });
      }

      if (telefones.length > 0) {
        fetch('/api/usuariosTelefones', {
          method: 'POST',
          headers: {
            Accept: 'application/json, text/plain, */*',
            'Content-Type': 'application/json',
            Authorization: 'Bearer ' + token,
          },
          body: JSON.stringify(telefones),
        });
      }
    }
  });
}

const { token } = JSON.parse(localStorage.getItem('usuario'));

//##### REQUISIÇÕES ######

// TIPOS DE USUARIOS
fetch('/api/tiposUsuario', {
  headers: {
    Accept: 'application/json, text/plain, */*',
    'Content-Type': 'application/json',
    Authorization: 'Bearer ' + token,
  },
}).then((response) => {
  if (response.status == 200) {
    response.json().then((tiposUsuario) => {
      tiposUsuario.forEach((tipoUsuario) => {
        const el = document.createElement('option');
        el.value = tipoUsuario.id;
        el.text = tipoUsuario.nome;
        $tipoUsuarioSelect.appendChild(el);
      });
    });
  }
});

// EMPRESAS
fetch('/api/empresas', {
  headers: {
    Accept: 'application/json, text/plain, */*',
    'Content-Type': 'application/json',
    Authorization: 'Bearer ' + token,
  },
}).then((response) => {
  if (response.status == 200) {
    response.json().then((empresas) => {
      empresas.forEach((empresa) => {
        const el = document.createElement('option');
        el.value = empresa.cnpj;
        el.text = empresa.nomeFantasia;
        $empresaSelect.appendChild(el);
      });
    });
  }
});

// ESTADOS
fetch('/api/estados', {
  method: 'GET',
  headers: {
    Accept: 'application/json, text/plain, */*',
    'Content-Type': 'application/json',
    Authorization: 'Bearer ' + token,
  },
}).then((response) => {
  if (response.status == 200) {
    response.json().then((estados) => {
      estados.forEach((estado) => {
        const el = document.createElement('option');
        el.value = estado.sigla;
        el.text = estado.nome;
        $estadoSelect.appendChild(el);
      });
      fetchCidades();
    });
  }
});

// CIDADES
$estadoSelect.addEventListener('change', ({ target: { value: sigla } }) =>
  fetchCidades(sigla)
);

function fetchCidades(sigla = 'AC') {
  console.log(sigla);
  fetch(`/api/cidadeByEstado/${sigla}`, {
    method: 'GET',
    headers: {
      Accept: 'application/json, text/plain, */*',
      'Content-Type': 'application/json',
      Authorization: 'Bearer ' + token,
    },
  }).then((response) => {
    if (response.status == 200) {
      response.json().then((cidades) => {
        $cidadeSelect.innerHTML = '';
        cidades.forEach((cidade) => {
          const el = document.createElement('option');
          el.value = cidade.id;
          el.text = cidade.nome;
          $cidadeSelect.appendChild(el);
        });
      });
    }
  });
}
