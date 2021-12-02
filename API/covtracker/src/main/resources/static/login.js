let login = document.getElementById('login');
let password = document.getElementById('password');
let form = document.getElementById('form');

form.addEventListener('submit', (event) => {
  event.preventDefault();

  console.log({
    login: login.value,
    password: password.value,
  });

  console.log({ email: login.value, senha: password.value });
  fetch('/api/autenticar', {
    method: 'POST',
    headers: {
      Accept: 'application/json, text/plain, */*',
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({ email: login.value, senha: password.value }),
  }).then((response) => {
    if (response.status == 200) {
      response.json().then(({ usuario, token }) => {
        localStorage.setItem('usuario', JSON.stringify({ ...usuario, token }));
        window.location.href = window.location.origin;
      });
    } else {
      localStorage.removeItem('usuario');
      alert('Senha Incorreta');
    }
  });
});

function redirectToCadastro() {
  this.window.location.href = this.window.location.origin + '/cadastro.html';
}
