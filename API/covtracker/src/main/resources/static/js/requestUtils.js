// FUNÇÕES QUE FAZEM REQUISIÇÕES E ABSTRAEM A ADIÇÃO DO TOKEN DE AUTENTICAÇÃO

export function get(endpoint, callback) {
  fetch(endpoint, {
    method: 'GET',
    headers: {
      Accept: 'application/json, text/plain, */*',
      'Content-Type': 'application/json',
      Authorization: 'Bearer ' + JSON.parse(localStorage.getItem("usuario")).token,
    },
  }).then((res) => callback(res));
}

export function post(endpoint, body, callback) {
  fetch(endpoint, {
    method: 'GET',
    headers: {
      Accept: 'application/json, text/plain, */*',
      'Content-Type': 'application/json',
      Authorization:
        'Bearer ' + JSON.parse(localStorage.getItem('usuario')).token,
    },
    body: JSON.stringify(body)
  }).then((res) => callback(res));
}