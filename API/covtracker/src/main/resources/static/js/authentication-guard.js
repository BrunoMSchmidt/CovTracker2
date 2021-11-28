const usuarioFromLocalStorage = JSON.parse(localStorage.getItem("usuario"));
if (!usuarioFromLocalStorage) {
  redirectToLoginAndClearStorage();
} else {
  fetch('api/autenticar/teste', {
    method: 'GET',
    headers: {
      Accept: 'application/json, text/plain, */*',
      'Content-Type': 'application/json',
      Authorization: 'Bearer ' + usuarioFromLocalStorage.token,
    },
  }).then((res) => {
    if (res.status == 403 || res.status == 500) {
      redirectToLoginAndClearStorage();
    }
  });
}

function redirectToLoginAndClearStorage(){
  localStorage.removeItem("usuario");
  window.location.href = window.location.origin + "/login.html"
}