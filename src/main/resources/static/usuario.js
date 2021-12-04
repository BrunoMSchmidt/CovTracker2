import { get, post } from './js/requestUtils.js';

document.querySelector('#nome-usuario-menu').innerHTML = JSON.parse(
  localStorage.getItem('usuario')
).nome;

const parametros = new URLSearchParams(window.location.search);

// VARIAVEIS DO CARREGAMENTO E HEADER DO USUARIO
const $usuarioNotFound = document.querySelector('#usuario-not-found');
const $loading = document.querySelector('#loading');
const $usuarioContent = document.querySelector('#usuario-content');
const $nome = document.querySelector('#nome');
const $email = document.querySelector('#email');
const $idade = document.querySelector('#idade');
//

const dadosSistema = {
  comorbidades: [],
  vacinas: [],
  sintomas: [],
};

const usuarioDadosCovid = {
  vacinas: [],
  sintomas: [],
  comorbidades: [],
  testes: [],
};

/**
 * Carrega as >> comorbidades, vacinas e sintomas << e seus ids
 */
function loadDadosSistema() {
  Promise.allSettled([
    get('/api/comorbidades/').then((res) =>
      res.status === 200 ? res.json() : null
    ),
    get('/api/vacinas/').then((res) =>
      res.status === 200 ? res.json() : null
    ),
    get('/api/sintomas/').then((res) =>
      res.status === 200 ? res.json() : null
    ),
  ]).then((data) => {
    dadosSistema.comorbidades = data[0].value;
    dadosSistema.vacinas = data[1].value;
    dadosSistema.sintomas = data[2].value;
  });
}
loadDadosSistema();

let usuario;
let usuarioNotFound = false;

if (parametros.has('cpf')) {
  // SE INFORMOU PARAM CPF NA URL
  get(`/api/usuario/${parametros.get('cpf')}`).then((res) => {
    if (res.status == 200) {
      res.json().then((data) => {
        usuario = data;
        $loading.setAttribute('hidden', '');
        $usuarioContent.removeAttribute('hidden');
        populaDadosGeraisUsuario();
        buscaDadosCovidUsuario();
      });
    } else {
      $loading.setAttribute('hidden', '');
      $usuarioNotFound.removeAttribute('hidden');
      usuarioNotFound = true;
    }
  });
} else {
  usuario = JSON.parse(localStorage.getItem('usuario'));
  $loading.setAttribute('hidden', '');
  $usuarioContent.removeAttribute('hidden');

  populaDadosGeraisUsuario();
  buscaDadosCovidUsuario();
}

/**
 * Busca os dados relacionados ao covid do usuário(vacinas, testes, comorbidades e sintomas);
 */
async function buscaDadosCovidUsuario() {
  const { cpf } = usuario;
  if (!cpf) {
    return;
  }

  const data = await Promise.allSettled([
    get(`/api/usuario-sintoma/findByCpf/${cpf}`).then((res) =>
      res.status === 200 ? res.json() : []
    ),
    get(`/api/usuario-vacina/findByCpf/${cpf}`).then((res) =>
      res.status === 200 ? res.json() : []
    ),
    get(`/api/testes/findByCpf/${cpf}`).then((res) =>
      res.status === 200 ? res.json() : []
    ),
    get(`/api/usuario-comorbidade/findByCpf/${cpf}`).then((res) =>
      res.status === 200 ? res.json() : []
    ),
  ])

  usuarioDadosCovid.sintomas =        data[0].value;
  usuarioDadosCovid.vacinas =         data[1].value;
  usuarioDadosCovid.testes =          data[2].value;
  usuarioDadosCovid.comorbidades =    data[3].value;

  usuarioDadosCovid.sintomas = usuarioDadosCovid.sintomas.map(
    (sintomaUsuario) => {
      return {
        ...sintomaUsuario,
        nomeSintoma: dadosSistema.sintomas.find(
          (sintoma) => sintoma.id === sintomaUsuario.idSintoma
        )?.nome,
      };
    }
  );
  usuarioDadosCovid.vacinas = usuarioDadosCovid.vacinas.map(
    (vacinaUsuario) => {
      return {
        ...vacinaUsuario,
        nomeVacina: dadosSistema.vacinas.find(
          (vacina) => vacina.id === vacinaUsuario.idVacina
        )?.nome,
      };
    }
  );
  usuarioDadosCovid.comorbidades = usuarioDadosCovid.comorbidades.map(
    (comorbidadeUsuario) => {
      return {
        ...comorbidadeUsuario,
        nomeComorbidade: dadosSistema.comorbidades.find(
          (comorbidade) => comorbidade.id === comorbidadeUsuario.idComorbidade
        )?.nome,
      };
    }
  );

  console.log(usuarioDadosCovid);
  populaDadosCovidUsuario();
};

function populaDadosCovidUsuario() {
  const $vacinas = document.querySelector('#vacinas');
  const $sintomas = document.querySelector('#sintomas');
  const $testes = document.querySelector('#testes');
  const $comorbidades = document.querySelector('#comorbidades');

  usuarioDadosCovid.vacinas.forEach((vacina) => {
    let p = document.createElement("p");
    let text = document.createTextNode(vacina.nomeVacina)
    p.appendChild(text)
    $vacinas.appendChild(p);
  });
  usuarioDadosCovid.comorbidades.forEach((comorbidade) => {
    let p = document.createElement("p");
    let text = document.createTextNode(comorbidade.nomeComorbidade)
    p.appendChild(text)
    $comorbidades.appendChild(p);
  });
  usuarioDadosCovid.sintomas.forEach((sintoma) => {
    let p = document.createElement("p");
    let text = document.createTextNode(sintoma.nomeSintoma)
    p.appendChild(text)
    $sintomas.appendChild(p);
  });
  usuarioDadosCovid.testes.forEach((teste) => {
    let p = document.createElement('p');
    let text = document.createTextNode(
      `${teste.resultado === 'N' ? 'Negativo' : 'Positivo'} - ${new Date(
        teste.dataTeste
      ).toLocaleDateString()}`
    );
    p.appendChild(text);
    $testes.appendChild(p);
  });
}

/**
 * BUSCA DADOS GERAIS DO USUÁRIO (Informações, endereço, empresa) e pupula na tela
 */
function populaDadosGeraisUsuario() {
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

    let $usuarioDetalhes = document.querySelector('#usuario-detalhes-wrapper');

    usuarioInformações.forEach((informacao) => {
      let header = document.createElement('h3');
      let headerText = document.createTextNode(informacao.descricao);
      header.appendChild(headerText);

      let informacaoContainer = document.createElement('div');
      informacaoContainer.classList.add('usuario-detalhes');
      informacao.dados.forEach((dado) => {
        if (dado.valor) {
          let paragrafo = document.createElement('p');
          paragrafo.innerHTML = `${dado.descricao}: <b>${dado.valor}</b>`;
          informacaoContainer.appendChild(paragrafo);
        }
      });
      $usuarioDetalhes.append(header, informacaoContainer);
    });
  }
}

function maskCPF(cpf) {
  cpf = cpf.replace(/\D/g, ''); //Remove tudo o que não é dígito
  cpf = cpf.replace(/(\d{3})(\d)/, '$1.$2'); //Coloca um ponto entre o terceiro e o quarto dígitos
  cpf = cpf.replace(/(\d{3})(\d)/, '$1.$2'); //Coloca um ponto entre o terceiro e o quarto dígitos
  //de novo (para o segundo bloco de números)
  cpf = cpf.replace(/(\d{3})(\d{1,2})$/, '$1-$2'); //Coloca um hífen entre o terceiro e o quarto dígitos
  return cpf;
}
