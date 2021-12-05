import { deleteRequest, get, post, put } from './js/requestUtils.js';

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

// MODAIS
let modais = {
  confirmDelete: {
    modalInstance: bootstrap.Modal.getOrCreateInstance(
      document.getElementById('confirmDeleteModal'),
      {}
    ),
    modalLabel: document.getElementById('confirmDeleteModalLabel'),
    actionButton: document.getElementById('delete-vacina-btn'),
    caller: null // 'vacina' OR 'comorbidade' OR 'sintoma' OR 'teste'
  },
  vacina: {
    cadastrar: {
      inputs: {
        data: document.querySelector('#input-cadastro-vacina-data'),
        vacinaId: document.querySelector('#input-cadastro-vacina-id'),
        dose: document.querySelector('#input-cadastro-vacina-dose'),
      },
      modalInstance: bootstrap.Modal.getOrCreateInstance(
        document.getElementById('cadastrarVacinaModal'),
        {}
      ),
      actionButton: document.getElementById('cadastro-vacina-btn'),
    },
    delete: {
      idToDelete: null,
      function: async () => {
        let id = modais.vacina.delete.idToDelete;
        return await deleteRequest(`/api/usuario-vacina/${id}`);
      },
    },
  },
  sintoma: {
    cadastrar: {
      inputs: {
        dataFinal: document.querySelector('#input-cadastro-sintoma-data-final'),
        dataInicio: document.querySelector('#input-cadastro-sintoma-data-inicio'),
        sintomaId: document.querySelector('#input-cadastro-sintoma-id'),
        intensidade: document.querySelector(
          '#input-cadastro-sintoma-intensidade'
        ),
      },
      modalInstance: bootstrap.Modal.getOrCreateInstance(
        document.getElementById('cadastrarSintomaModal'),
        {}
      ),
      actionButton: document.getElementById('cadastro-sintoma-btn'),
    },
    editar: {
      inputs: {
        dataFinal: document.querySelector('#input-editar-sintoma-data-final'),
        dataInicio: document.querySelector('#input-editar-sintoma-data-inicio'),
        sintomaId: document.querySelector('#input-editar-sintoma-id'),
        intensidade: document.querySelector(
          '#input-editar-sintoma-intensidade'
        ),
      },
      modalInstance: bootstrap.Modal.getOrCreateInstance(
        document.getElementById('editarSintomaModal'),
        {}
      ),
      actionButton: document.getElementById('editar-sintoma-btn'),
    },
    delete: {
      bodyToDelete: null,
      function: async () => {
        let body = modais.sintoma.delete.bodyToDelete;
        return await deleteRequest('/api/usuario-sintoma', body);
      },
    },
  },
};

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
(async function loadDadosSistema() {
  const data = await Promise.allSettled([
    get('/api/comorbidades/').then((res) =>
      res.status === 200 ? res.json() : null
    ),
    get('/api/vacinas/').then((res) =>
      res.status === 200 ? res.json() : null
    ),
    get('/api/sintomas/').then((res) =>
      res.status === 200 ? res.json() : null
    ),
  ]);
  dadosSistema.comorbidades = data[0].value;
  dadosSistema.vacinas = data[1].value;
  dadosSistema.sintomas = data[2].value;

  populaSelectsModais();
})();

function populaSelectsModais() {
  const vacinasSelect = document.querySelector('#input-cadastro-vacina-id');
  dadosSistema.vacinas.forEach((vacina) => {
    const option = document.createElement('option');
    option.value = vacina.id;
    option.textContent = vacina.nome;
    vacinasSelect.appendChild(option);
  });

  const sintomasSelects = document.querySelectorAll('select[name="sintoma"]');
  dadosSistema.sintomas.forEach((sintoma) => {
    const option = document.createElement('option');
    option.value = sintoma.id;
    option.textContent = sintoma.nome;
    sintomasSelects.forEach(sintomaSelect => {
      sintomaSelect.appendChild(option.cloneNode(true));
    })
  });
}

let usuario;

if (parametros.has('cpf')) {
  // SE INFORMOU PARAM CPF NA URL
  const res = await get(`/api/usuario/${parametros.get('cpf')}`);

  if (res.status == 200) {
    const data = await res.json();

    usuario = data;
    $loading.setAttribute('hidden', '');
    $usuarioContent.removeAttribute('hidden');
    populaDadosGeraisUsuario();
    buscaDadosCovidUsuario();
  } else {
    $loading.setAttribute('hidden', '');
    $usuarioNotFound.removeAttribute('hidden');
  }
} else {
  usuario = JSON.parse(localStorage.getItem('usuario'));
  $loading.setAttribute('hidden', '');
  $usuarioContent.removeAttribute('hidden');

  populaDadosGeraisUsuario();
  buscaDadosCovidUsuario();
}

/**
 * Busca os dados relacionados ao covid do usuário(vacinas, testes, comorbidades e sintomas)
 * e na sequencia chama o método populaDadosCovidUsuario
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
  ]);

  usuarioDadosCovid.sintomas = data[0].value;
  usuarioDadosCovid.vacinas = data[1].value;
  usuarioDadosCovid.testes = data[2].value;
  usuarioDadosCovid.comorbidades = data[3].value;

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
  usuarioDadosCovid.vacinas = usuarioDadosCovid.vacinas.map((vacinaUsuario) => {
    return {
      ...vacinaUsuario,
      nomeVacina: dadosSistema.vacinas.find(
        (vacina) => vacina.id === vacinaUsuario.idVacina
      )?.nome,
    };
  });
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
}

/**
 * Popula os dados relacionados a covid do usuário
 * (vacinas, comorbidades, testes e sintomas)
 */
function populaDadosCovidUsuario() {
  const $testes = document.querySelector('#testes');
  const $comorbidades = document.querySelector('#comorbidades');
  $testes.innerHTML = '';
  $comorbidades.innerHTML = '';

  const { vacinas, comorbidades, sintomas, testes } = usuarioDadosCovid;

  // VACINAS
  const $vacinasTable = document.querySelector('#vacinas-table');
  const $vacinasTableBody = $vacinasTable.children[1];
  $vacinasTableBody.innerHTML = '';
  if (vacinas.length) {
    $vacinasTable.removeAttribute('hidden');
    vacinas.forEach((vacina) => {
      const tr = document.createElement('tr');
      const tdNome = document.createElement('td');
      tdNome.textContent = vacina.nomeVacina;
      const tdDose = document.createElement('td');
      tdDose.textContent = vacina.dose;
      const tdData = document.createElement('td');
      tdData.textContent = formataData(new Date(vacina.data));
      const tdAcoes = document.createElement('td');
      const removeButton = document.createElement('button');
      removeButton.classList.add('btn', 'btn-danger');
      removeButton.textContent = 'X';
      removeButton.addEventListener('click', () => {
        modais.confirmDelete.caller = 'vacina';
        modais.confirmDelete.modalLabel.textContent =
          'Deseja deletar a vacina?';
        modais.vacina.delete.idToDelete = vacina.id;
        modais.confirmDelete.modalInstance.show();
      });
      tdAcoes.appendChild(removeButton);
      tr.append(tdNome, tdDose, tdData, tdAcoes);
      $vacinasTableBody.appendChild(tr);
    });
  } else {
    $vacinasTable.setAttribute('hidden', '');
  }

  // SINTOMAS
  const $sintomasTable = document.querySelector('#sintomas-table');
  const $sintomasTableBody = $sintomasTable.children[1];
  $sintomasTableBody.innerHTML = '';
  if (sintomas.length) {
    $sintomasTable.removeAttribute('hidden');
    sintomas.forEach((sintoma) => {
      const tr = document.createElement('tr');
      const tdNome = document.createElement('td');
      tdNome.textContent = sintoma.nomeSintoma;
      const tdDataInicio = document.createElement('td');
      tdDataInicio.textContent = formataData(new Date(sintoma.dataInicio));
      const tdDataFim = document.createElement('td');
      tdDataFim.textContent = sintoma.dataFim
        ? formataData(new Date(sintoma.dataFim))
        : '__/__/____';
      const tdIntensidade = document.createElement('td');
      tdIntensidade.textContent = sintoma.intensidade;
      const tdAcoes = document.createElement('td');
      const editButton = document.createElement('button');
      editButton.classList.add('btn', 'btn-info');
      editButton.innerHTML = `
        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil" viewBox="0 0 16 16">
          <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"/>
        </svg>
      `;
      editButton.addEventListener('click', () => {
        const { dataFinal, dataInicio, sintomaId, intensidade } =
          modais.sintoma.editar.inputs;

        dataFinal.value = sintoma.dataFim;
        dataInicio.value = sintoma.dataInicio;
        intensidade.value = sintoma.intensidade;
        sintomaId.value = sintoma.idSintoma;

        modais.sintoma.editar.modalInstance.show();
      });

      const removeButton = document.createElement('button');
      removeButton.classList.add('btn', 'btn-danger');
      removeButton.textContent = 'X';
      removeButton.addEventListener('click', () => {
        modais.confirmDelete.caller = 'sintoma';
        modais.confirmDelete.modalLabel.textContent =
          'Deseja deletar o sintoma?';
        modais.sintoma.delete.bodyToDelete = {
          id: {
            cpfusu: sintoma.cpfUsuario,
            codsin: sintoma.idSintoma
          },
          intensidade: sintoma.intensidade,
          dataInicio: sintoma.dataInicio,
          dataFim: sintoma.dataFim
        }
        modais.confirmDelete.modalInstance.show();
      });
      tdAcoes.style.whiteSpace = 'nowrap';
      tdAcoes.append(editButton, removeButton);
      tr.append(tdNome, tdDataInicio, tdDataFim, tdIntensidade, tdAcoes);
      $sintomasTableBody.appendChild(tr);
    });
  } else {
    $sintomasTable.setAttribute('hidden', '');
  }

  console.log();

  comorbidades.forEach((comorbidade) => {
    let p = document.createElement('p');
    let text = document.createTextNode(comorbidade.nomeComorbidade);
    p.appendChild(text);
    $comorbidades.appendChild(p);
  });

  testes.forEach((teste) => {
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
          valor: formataData(new Date(usuario.dataNascimento)),
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

// MODAL CADASTRAR VACINA
modais.vacina.cadastrar.actionButton.addEventListener('click', () => {
  const {
    modalInstance,
    inputs: { data, vacinaId, dose },
  } = modais.vacina.cadastrar;
  if (data.value && vacinaId.value && dose.value) {
    let body = {
      usuario: {
        cpf: usuario.cpf,
      },
      vacina: {
        id: vacinaId.value,
      },
      data: data.value,
      dose: dose.value,
    };
    post('/api/usuario-vacina', body).then((res) => {
      if (res.status == 200) {
        buscaDadosCovidUsuario();
      }
    });
  }
  modalInstance.hide();
});

// MODAL CADASTRAR SINTOMA
modais.sintoma.cadastrar.actionButton.addEventListener('click', () => {
  console.log("alo");
  const {
    modalInstance,
    inputs: { sintomaId, dataInicio, dataFinal, intensidade },
  } = modais.sintoma.cadastrar;
  if (sintomaId.value && dataInicio.value && intensidade.value) {
    console.log("teste");
    let body = {
      id: {
        cpfusu: usuario.cpf,
        codsin: sintomaId.value,
      },
      intensidade: intensidade.value,
      dataInicio: dataInicio.value,
      dataFim: dataFinal.value,
    };
    post('/api/usuario-sintoma', body).then((res) => {
      if (res.status == 200) {
        buscaDadosCovidUsuario();
      }
    });
  }
  modalInstance.hide();
});

// MODAL EDITAR SINTOMA
modais.sintoma.editar.actionButton.addEventListener('click', () => {
  const {
    modalInstance,
    inputs: { sintomaId, dataInicio, dataFinal, intensidade },
  } = modais.sintoma.editar;
  if (sintomaId.value && dataInicio.value && intensidade.value) {
    let body = {
      id: {
        cpfusu: usuario.cpf,
        codsin: sintomaId.value,
      },
      intensidade: intensidade.value,
      dataInicio: dataInicio.value,
      dataFim: dataFinal.value,
    };
    put('/api/usuario-sintoma', body).then((res) => {
      if (res.status == 200) {
        buscaDadosCovidUsuario();
      }
    });
  }
  modalInstance.hide();
});

// MODAL CONFIRM DELETE
modais.confirmDelete.actionButton.addEventListener('click', deletar);

async function deletar() {
  const caller = modais.confirmDelete.caller;
  let res;
  switch (caller) {
    case 'vacina':
      res = await modais.vacina.delete.function();
      break;
    case 'comorbidade':
      break;
    case 'sintoma':
      res = await modais.sintoma.delete.function();
      break;
    case 'teste':
      break;
    default:
      break;
  }

  if(res && res.status == 200){
    buscaDadosCovidUsuario();
  }

  modais.confirmDelete.modalInstance.hide();
}

function maskCPF(cpf) {
  cpf = cpf.replace(/\D/g, ''); //Remove tudo o que não é dígito
  cpf = cpf.replace(/(\d{3})(\d)/, '$1.$2'); //Coloca um ponto entre o terceiro e o quarto dígitos
  cpf = cpf.replace(/(\d{3})(\d)/, '$1.$2'); //Coloca um ponto entre o terceiro e o quarto dígitos
  //de novo (para o segundo bloco de números)
  cpf = cpf.replace(/(\d{3})(\d{1,2})$/, '$1-$2'); //Coloca um hífen entre o terceiro e o quarto dígitos
  return cpf;
}

function formataData(data) {
  if (data instanceof Date) {
    return (
      data.getDate().toString().padStart(2, '0') +
      '/' +
      data.getMonth().toString().padStart(2, '0') +
      '/' +
      data.getFullYear()
    );
  }
}
