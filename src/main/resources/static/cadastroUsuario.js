const $nomeCompletoInput = document.querySelector('input[name="nome"');
const $cpfInput = document.querySelector('input[name="cpf"');
const $dataNascimento = document.querySelector('input[name="dataNascimento"');
const $telefone1Input = document.querySelector('input[name="telefone1"');
const $telefone2Input = document.querySelector('input[name="telefone2"');
const $pesoInput = document.querySelector('input[name="peso"');
const $alturaInput = document.querySelector('input[name="altura"');
const $sexoSelect = document.querySelector('select[name="sexo"');
const $tipoUsuarioSelect = document.querySelector('select[name="tipoUsuario"');
const $empresaSelect = document.querySelector('select[name="empresa"');
const $ruaInput = document.querySelector('input[name="rua"');
const $numeroInput = document.querySelector('input[name="numero"');
const $bairroInput = document.querySelector('input[name="bairro"');
const $cepInput = document.querySelector('input[name="cep"');
const $estadoSelect = document.querySelector('select[name="estado"');
const $cidadeSelect = document.querySelector('select[name="cidade"');
const $emailInput = document.querySelector('input[name="email"');
const $senhaInput = document.querySelector('input[name="senha"');

const $submitButton = document.querySelector('#submitButton');

$submitButton.addEventListener('click', submitForm);

function submitForm() {
  console.log({
    nomeCompleto: $nomeCompletoInput.value,
    cpf: $cpfInput.value,
    dataNasci: $dataNascimento.value,
    telefone1: $telefone1Input.value,
    telefone2: $telefone2Input.value,
    peso: $pesoInput.value,
    altura: $alturaInput.value,
    sexo: $sexoSelect.value,
    tipoUsuario: $tipoUsuarioSelect.value,
    empresa: $empresaSelect.value,
    rua: $ruaInput.value,
    numero: $numeroInput.value,
    bairro: $bairroInput.value,
    cep: $cepInput.value,
    estadoS: $estadoSelect.value,
    cidadeS: $cidadeSelect.value,
    email: $emailInput.value,
    senha: $senhaInput.value,
  });
}
