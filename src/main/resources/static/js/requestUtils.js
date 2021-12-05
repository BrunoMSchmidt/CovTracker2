// FUNÇÕES QUE FAZEM REQUISIÇÕES E ABSTRAEM A ADIÇÃO DO TOKEN DE AUTENTICAÇÃO

export function get(endpoint) {
  return fetch(endpoint, {
    method: 'GET',
    headers: {
      Accept: 'application/json, text/plain, */*',
      'Content-Type': 'application/json',
      Authorization: 'Bearer ' + JSON.parse(localStorage.getItem("usuario")).token,
    },
  });
}

export function post(endpoint, body) {
  return fetch(endpoint, {
    method: 'POST',
    headers: {
      Accept: 'application/json, text/plain, */*',
      'Content-Type': 'application/json',
      Authorization:
        'Bearer ' + JSON.parse(localStorage.getItem('usuario')).token,
    },
    body: JSON.stringify(body)
  });
}

export function deleteRequest(endpoint, body = null) {
  return fetch(endpoint, {
    method: 'DELETE',
    headers: {
      Accept: 'application/json, text/plain, */*',
      'Content-Type': 'application/json',
      Authorization:
        'Bearer ' + JSON.parse(localStorage.getItem('usuario')).token,
    },
    body: JSON.stringify(body)
  });
}

export function put(endpoint, body){
  return fetch(endpoint, {
    method: 'PUT',
    headers: {
      Accept: 'application/json, text/plain, */*',
      'Content-Type': 'application/json',
      Authorization:
        'Bearer ' + JSON.parse(localStorage.getItem('usuario')).token,
    },
    body: JSON.stringify(body)
  });
}