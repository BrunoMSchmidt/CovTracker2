import { get, post } from './js/requestUtils.js';

document.querySelector('#nome-usuario-menu').innerHTML = JSON.parse(localStorage.getItem('usuario')).nome;

const parametros = new URLSearchParams(window.location.search);

const $usuarioNotFound = document.querySelector('#usuario-not-found');
const $loading = document.querySelector('#loading');
const $usuarioContent = document.querySelector('#usuario-content');

const $nome = document.querySelector('#nome');
const $email = document.querySelector('#email');
const $idade = document.querySelector('#idade');
const $cpf = document.querySelector('#cpf');
const $dataNascimento = document.querySelector('#data-nascimento');
const $sexo = document.querySelector('#sexo');
const $peso = document.querySelector('#peso');
const $altura = document.querySelector('#altura');
const $situacao = document.querySelector('#situacao');
const $obs = document.querySelector('#obs')
const $rua = document.querySelector('#rua')
const $cidade = document.querySelector('#cidade')
const $estado = document.querySelector('#estado')
const $bairro = document.querySelector('#bairro')
const $cep = document.querySelector('#cep')
const $num = document.querySelector('#num')
const $cnpj = document.querySelector('#cnpj');
const $nomeEmpresa = document.querySelector('#nomeEmpresa')
const $emailEmpresa = document.querySelector('#emailEmpresa');
const $razaoSocialEmpresa = document.querySelector('#razaoSocialEmpresa')
const $telefoneEmpresa = document.querySelector('#telefoneEmpresa')
const $areaAtuacaoEmpresa = document.querySelector('#areaAtuacaoEmpresa')
const $funcaoEmpresa = document.querySelector('#funcaoEmpresa')


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

    // INFORMAÇÕES DO USUÁRIO
    $cpf.innerHTML = `CPF: <b>${maskCPF(usuario.cpf.toString().padStart(11, "0"))}</b>`;

    let datNas = new Date(usuario.dataNascimento);
    $dataNascimento.innerHTML = `Data de nascimento: <b>${datNas.getDate()+"/"+datNas.getMonth()+"/"+datNas.getFullYear()}</b>`;
    if(usuario.sexo === "M" || usuario.sexo === "F"){
      $sexo.innerHTML = `Sexo: <b>${usuario.sexo === "M"? "Masculino" : "Feminino"}</b>`
    }
    $peso.innerHTML = `Peso: <b>${usuario.peso} Kg</b>`;
    $altura.innerHTML = `Altura: <b>${usuario.altura}m</b>`;
    if(usuario.situacao?.length){
      $situacao.innerHTML = `Situação: <b>${usuario.situacao}</b>` 
    }
    if(usuario.obs?.length){
      $obs.innerHTML = `Obs: <b>${usuario.obs}</b>`; 
    }

    console.log(usuario);
    // ENDEREÇO
    if (usuario?.endereco?.rua){
      $rua.innerHTML = `Rua: <b>${usuario.endereco.rua}</b>`;
    }
    if (usuario?.endereco?.cidade?.nome){
      $cidade.innerHTML = `Cidade: <b>${usuario.endereco.cidade.nome}</b>`
    }
    if (usuario?.endereco?.cidade?.estado) {
      $estado.innerHTML = `Estado: <b>${usuario.endereco.cidade.estado.nome}</b>`
    }
    if (usuario?.endereco?.bairro) {
      $bairro.innerHTML = `Bairro: <b>${usuario.endereco.bairro}</b>`
    }
    if (usuario?.endereco?.cep) {
      $cep.innerHTML = `CEP: <b>${usuario.endereco.cep}</b>`
    }
    if (usuario?.endereco?.numero) {
      $num.innerHTML = `Número: <b>${usuario.endereco.numero}</b>`;
    }
    
    if (usuario?.empresa?.areaDeAtuacao) {
      console.log("alo");
      $areaAtuacaoEmpresa.innerHTML = `Área de atuação: <b>${usuario?.empresa.areaDeAtuacao}</b>`;
    }
    if (usuario?.empresa?.razaoSocial) {
      $razaoSocialEmpresa.innerHTML = `Razão Social: <b>${usuario?.empresa?.razaoSocial}</b>`;
    }
    if (usuario?.empresa?.nomeFantasia) {
      $nomeEmpresa.innerHTML = `Nome Fantasia: <b>${usuario?.empresa?.nomeFantasia}</b>`;
    }
    if (usuario?.empresa?.cnpj) {
      $cnpj.innerHTML = `CNPJ: <b>${usuario?.empresa?.cnpj}</b>`;
    }
    if (usuario?.empresa?.telefone) {
      $telefoneEmpresa.innerHTML = `Telefone: <b>${usuario?.empresa?.telefone}</b>`;
    }
    if (usuario?.empresa?.email) {
      $emailEmpresa.innerHTML = `E-mail: <b>${usuario?.empresa?.email}</b>`;
    }
    if (usuario?.funcaoEmpresa) {
      $funcaoEmpresa.innerHTML = `Função do usuário na empresa: <b>${usuario?.funcaoEmpresa}</b>`;
    }
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
