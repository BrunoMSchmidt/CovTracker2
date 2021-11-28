import { get, post } from './js/requestUtils.js';

const parametros = new URLSearchParams(window.location.search);

const $usuarioNotFound = document.querySelector('#usuario-not-found');
const $loading = document.querySelector('#loading');
const $usuarioContent = document.querySelector('#usuario-content');

const $nome = document.querySelector('#nome');
const $email = document.querySelector('#email');
const $idade = document.querySelector('#idade');

let usuario;
let usuarioNotFound = false;

if (parametros.has('cpf')) {
  get(`/api/usuario/${parametros.get('cpf')}`, (res) => {
    if (res.status == 200) {
      res.json().then((data) => {
        usuario = data;
        $loading.setAttribute('hidden', '');
        $usuarioContent.removeAttribute('hidden');
        populaDadosUsuario();
      });
    } else {
      $loading.setAttribute('hidden', '');
      $usuarioNotFound.removeAttribute('hidden');
      usuarioNotFound = true;

      populaDadosUsuario();
    }
  });
} else {
  usuario = usuarioFromLocalStorage;
  $loading.setAttribute('hidden', '');
  $usuarioContent.removeAttribute('hidden');

  populaDadosUsuario();
}

function populaDadosUsuario() {
  if (!usuarioNotFound) {
    console.log(usuario);

    $nome.textContent = usuario.nome;
    $email.textContent = usuario.email;
    $idade.textContent =
      new Date(
        new Date().getTime() - new Date(usuario.dataNascimento).getTime()
      ).getFullYear() -
      1970 +
      ' anos';
  }
}
