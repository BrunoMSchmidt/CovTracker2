let token = JSON.parse(localStorage.getItem('usuario')).token;

if (!token) {
  alert('Usuário não está logado');
  localStorage.removeItem("usuario");
  window.location.href = window.location.origin + "login.html"
}

const estadoInput = document.getElementById('estado');
const cidadeInput = document.getElementById('cidade');
let estados = [];
let cidades = [];

fetchEstados();

// A CADA MUDANCA NO SELECT DE ESTADOS, CARREGA AS CIDADES DO RESPECTIVO ESTADO
estadoInput.addEventListener('change', fetchCidades);

function setEstados() {
  estados.forEach((estado) => {
    let option = document.createElement('option');
    option.value = estado.sigla;
    option.textContent = estado.nome;
    estadoInput.appendChild(option);
  });
}

function setCidades() {
  cidadeInput.innerHTML = '';
  cidades.forEach((cidade) => {
    let option = document.createElement('option');
    option.value = cidade.id;
    option.textContent = cidade.nome;
    cidadeInput.appendChild(option);
  });
}

// BUSCA OS ESTADOS NA API
function fetchEstados() {
  fetch('/api/estados', {
    method: 'GET',
    headers: {
      Accept: 'application/json, text/plain, */*',
      'Content-Type': 'application/json',
      Authorization: 'Bearer ' + token,
    },
  }).then((response) => {
    if (response.status == 200) {
      response.json().then((data) => {
        estados = data;
        setEstados();
        fetchCidades();
      });
    }
  });
}

// BUSCA AS CIDADES NA API
function fetchCidades() {
  fetch(`/api/cidadeByEstado/${estadoInput.value}`, {
    method: 'GET',
    headers: {
      Accept: 'application/json, text/plain, */*',
      'Content-Type': 'application/json',
      Authorization: 'Bearer ' + token,
    },
  }).then((response) => {
    if (response.status == 200) {
      response.json().then((data) => {
        cidades = data;
        setCidades();
        console.log(cidadeInput.value, estadoInput.value);
      });
    }
  });
}
