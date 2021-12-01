import { get, post } from './js/requestUtils.js';

document.querySelector('#nome-usuario-menu').innerHTML = JSON.parse(localStorage.getItem('usuario')).nome;

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

    // INFORMAÇÕES CARD
    $nome.textContent = usuario.nome;
    $email.textContent = usuario.email;
    $idade.textContent =
      new Date(
        new Date().getTime() - new Date(usuario.dataNascimento).getTime()
      ).getFullYear() -
      1970 +
      ' anos';

    let datNas = new Date(usuario.dataNascimento);

    let usuarioInformações = [
      {
        descricao: 'Informações do usuário',
        dados: [
          {
            descricao: 'CPF',
            valor: maskCPF(usuario.cpf.toString().padStart(11, '0')),
          },
          {
            descricao: 'Data de Nascimento',
            valor:
              datNas.getDate() +
              '/' +
              datNas.getMonth() +
              '/' +
              datNas.getFullYear(),
          },
          {
            descricao: 'Sexo',
            valor:
              usuario.sexo === 'M'
                ? 'Masculino'
                : usuario.sexo === 'F'
                ? 'Feminino'
                : 'Indefinido',
          },
          {
            descricao: 'Peso',
            valor: usuario.peso + ' Kg',
          },
          {
            descricao: 'Altura',
            valor: usuario.altura + 'm',
          },
          {
            descricao: 'Situação',
            valor: usuario.situacao,
          },
          {
            descricao: 'Obs',
            valor: usuario.obs,
          },
        ],
      },
      {
        descricao: 'Endereço',
        dados: [
          {
            descricao: 'Rua',
            valor: usuario.endereco.rua,
          },
          {
            descricao: 'Cidade',
            valor: usuario.endereco.cidade.nome,
          },
          {
            descricao: 'Estado',
            valor: usuario.endereco.cidade.estado.nome,
          },
          {
            descricao: 'Bairro',
            valor: usuario.endereco.bairro,
          },
          {
            descricao: 'CEP',
            valor: usuario.endereco.cep,
          },
          {
            descricao: 'Número',
            valor: usuario.endereco.numero,
          },
        ],
      },
      {
        descricao: 'Empresa',
        dados: [
          {
            descricao: 'CNPJ',
            valor: usuario.empresa.cnpj,
          },
          {
            descricao: 'Nome Fantasia',
            valor: usuario.empresa.nomeFantasia,
          },
          {
            descricao: 'Razão Social',
            valor: usuario.empresa.razaoSocial,
          },
          {
            descricao: 'Telefone',
            valor: usuario.empresa.telefone,
          },
          {
            descricao: 'E-mail',
            valor: usuario.empresa.email,
          },
          {
            descricao: 'Área de atuação',
            valor: usuario.empresa.areaDeAtuacao,
          },
          {
            descricao: 'Função do usuário na empresa',
            valor: usuario.funcaoEmpresa,
          },
        ],
      },
    ];

    let $usuarioDetalhes = document.querySelector("#usuario-detalhes-wrapper");

    usuarioInformações.forEach(informacao => {
      let header = document.createElement("h3")
      let headerText = document.createTextNode(informacao.descricao);
      header.appendChild(headerText);

      let informacaoContainer = document.createElement("div");
      informacaoContainer.classList.add("usuario-detalhes")
      informacao.dados.forEach(dado => {
        if(dado.valor){
          let paragrafo = document.createElement("p");
          paragrafo.innerHTML = `${dado.descricao}: <b>${dado.valor}</b>`
          informacaoContainer.appendChild(paragrafo);
        }
      })
      $usuarioDetalhes.append(header, informacaoContainer);
    })

  }
}

function maskCPF(cpf){
    cpf = cpf.replace(/\D/g, '');                    //Remove tudo o que não é dígito
    cpf = cpf.replace(/(\d{3})(\d)/, '$1.$2');       //Coloca um ponto entre o terceiro e o quarto dígitos
    cpf = cpf.replace(/(\d{3})(\d)/, '$1.$2');       //Coloca um ponto entre o terceiro e o quarto dígitos
                                             //de novo (para o segundo bloco de números)
    cpf = cpf.replace(/(\d{3})(\d{1,2})$/, '$1-$2'); //Coloca um hífen entre o terceiro e o quarto dígitos
    return cpf;
}
