CREATE TABLE Orientacao (
  codori SERIAL NOT NULL CONSTRAINT orientacao_pk primary key, 
  desori varchar(255) NOT NULL
);

COMMENT ON TABLE Orientacao IS 'Cadastro de orientações';
COMMENT ON COLUMN Orientacao.codori IS 'Código da orientação';
COMMENT ON COLUMN Orientacao.desori IS 'Descrição da orientação';

CREATE TABLE Estado (
  sigest varchar(2) NOT NULL CONSTRAINT estado_pk primary key, 
  nomest varchar(40) NOT NULL CONSTRAINT est_nomest_un UNIQUE
);

COMMENT ON TABLE Estado IS 'Cadastro do Estado';
COMMENT ON COLUMN Estado.sigest IS 'Sigla do estado';
COMMENT ON COLUMN Estado.nomest IS 'Nome do estado';

CREATE TABLE Cidade (
  codcid SERIAL NOT NULL CONSTRAINT cidade_pk primary key, 
  nomcid varchar(40) NOT NULL, 
  sigest varchar(2) NOT NULL CONSTRAINT cid_sigest_fk REFERENCES Estado (sigest)
);

COMMENT ON TABLE Cidade IS 'Cadastro da Cidade';
COMMENT ON COLUMN Cidade.codcid IS 'Código da cidade';
COMMENT ON COLUMN Cidade.nomcid IS 'Nome da cidade';

CREATE TABLE Bairro (
  codbai SERIAL NOT NULL CONSTRAINT bairro_pk primary key, 
  nombai varchar(40) NOT NULL, 
  cepbai numeric(8, 0) NOT NULL CONSTRAINT bai_cepbai_un UNIQUE,
  codcid int4 NOT NULL CONSTRAINT bai_codcid_fk REFERENCES Cidade (codcid)
);

COMMENT ON TABLE Bairro IS 'Cadastro do bairro';
COMMENT ON COLUMN Bairro.codbai IS 'Código do bairro';
COMMENT ON COLUMN Bairro.nombai IS 'Nome do bairro';
COMMENT ON COLUMN Bairro.cepbai IS 'Cep do bairro /ou cidade';

CREATE TABLE Endereco (
  codend SERIAL NOT NULL CONSTRAINT endereco_pk primary key, 
  ruaend varchar(40) NOT NULL, 
  numend varchar(8), 
  comend varchar(40), 
  codcid int4 NOT NULL CONSTRAINT end_codcid_fk REFERENCES Cidade (codcid),
  codbai int4 CONSTRAINT end_codbai_fk REFERENCES Bairro (codbai)
);

COMMENT ON TABLE Endereco IS 'Cadastro do endereço';
COMMENT ON COLUMN Endereco.codend IS 'Código do endereco';
COMMENT ON COLUMN Endereco.ruaend IS 'Rua do endereço';
COMMENT ON COLUMN Endereco.numend IS 'Número do endereço';
COMMENT ON COLUMN Endereco.comend IS 'Complemento do endereço';

CREATE TABLE Empresa (
  cnpjemp   numeric(14, 0) NOT NULL CONSTRAINT empresa_pk primary key, 
  nomfanemp varchar(60), 
  razsocemp varchar(60) NOT NULL CONSTRAINT emp_razsocemp_un UNIQUE,
  telemp    numeric(11, 0) NOT NULL, 
  emaemp    varchar(50) NOT NULL, 
  areatuemp varchar(50), 
  codend    int4 NOT NULL CONSTRAINT emp_codend_fk REFERENCES Endereco (codend)
);

COMMENT ON TABLE Empresa IS 'Empresa Responsavel pelo paciente';
COMMENT ON COLUMN Empresa.cnpjemp IS 'CNPJ da empresa';
COMMENT ON COLUMN Empresa.nomfanemp IS 'Nome Fantasia da Empresa';
COMMENT ON COLUMN Empresa.razsocemp IS 'Razão Social da Empresa';
COMMENT ON COLUMN Empresa.telemp IS 'Cadastro do telefone da empresa';
COMMENT ON COLUMN Empresa.emaemp IS 'Email da empresa';
COMMENT ON COLUMN Empresa.areatuemp IS 'Área de atuação da empresa';

CREATE TABLE Tipo_Usuario (
  codtipusu SERIAL NOT NULL CONSTRAINT tipo_usuario_pk primary key, 
  nomtipusu varchar(30) NOT NULL CONSTRAINT tipusu_nomtipusu_un UNIQUE
);

COMMENT ON COLUMN Tipo_Usuario.codtipusu IS 'Código do tipo de usuário';
COMMENT ON COLUMN Tipo_Usuario.nomtipusu IS 'Nome do tipo de usuário (Paciente, Administrador...)';

CREATE TABLE Usuario (
  cpfusu    numeric(11, 0) NOT NULL CONSTRAINT usuario_pk primary key, 
  nomusu    varchar(60) NOT NULL, 
  datnasusu date, 
  sexusu    char(1), 
  pesusu    numeric(3, 0) NOT NULL, 
  altusu    numeric(3, 2) NOT NULL, 
  funempusu varchar(40) NOT NULL, 
  cnpjemp   numeric(14, 0) NOT NULL CONSTRAINT usu_cnpjemp_fk REFERENCES Empresa (cnpjemp), 
  codtipusu int4 NOT NULL CONSTRAINT usu_codtipusu_fk REFERENCES Tipo_Usuario (codtipusu), 
  codend    int4 NOT NULL CONSTRAINT usu_codend_fk REFERENCES Endereco (codend)
);

COMMENT ON TABLE Usuario IS 'Cadastro do usuário';
COMMENT ON COLUMN Usuario.cpfusu IS 'CPF do usuário';
COMMENT ON COLUMN Usuario.nomusu IS 'Nome do Usuário.';
COMMENT ON COLUMN Usuario.datnasusu IS 'Data de nascimento do usuario.';
COMMENT ON COLUMN Usuario.sexusu IS 'Sexo do usuário: (M = Masculino, F = Feminino).';
COMMENT ON COLUMN Usuario.pesusu IS 'Peso do usuario';
COMMENT ON COLUMN Usuario.altusu IS 'Altura do usuario em metros';
COMMENT ON COLUMN Usuario.funempusu IS 'Função do usuário na empresa';

CREATE TABLE Usuario_Telefone (
  cpfusu numeric(11, 0) NOT NULL CONSTRAINT usutel_cpfusu_fk REFERENCES Usuario (cpfusu), 
  telpac numeric(11, 0) NOT NULL, 
  CONSTRAINT usuario_telefone_pk PRIMARY KEY (cpfusu, telpac)
);

COMMENT ON TABLE Usuario_Telefone IS 'Cadastro de telefones do usuário.';
COMMENT ON COLUMN Usuario_Telefone.telpac IS 'Telefone do paciente';

CREATE TABLE Comorbidade (
  codcom SERIAL NOT NULL CONSTRAINT comorbidade_pk primary key, 
  nomcom varchar(100) NOT NULL CONSTRAINT com_nomcom_un UNIQUE
);
  
COMMENT ON TABLE Comorbidade IS 'Cadastro de comorbidades';
COMMENT ON COLUMN Comorbidade.codcom IS 'Código da comorbidade';
COMMENT ON COLUMN Comorbidade.nomcom IS 'Nome da comorbidade';

CREATE TABLE Usuario_Comorbidade (
  cpfusu numeric(11, 0) NOT NULL CONSTRAINT usucom_cpfusu_fk REFERENCES Usuario (cpfusu), 
  codcom int4 NOT NULL CONSTRAINT usucom_codcom_fk REFERENCES Comorbidade (codcom), 
  CONSTRAINT usuario_comorbidades_pk PRIMARY KEY (cpfusu, codcom)
);

CREATE TABLE Sintoma (
  codsin SERIAL NOT NULL CONSTRAINT sintoma_pk primary key, 
  nomsin varchar(30) NOT NULL CONSTRAINT sin_nomsin_un UNIQUE,
  crisin int4 NOT NULL
);

COMMENT ON TABLE Sintoma IS 'Cadastro do sintoma';
COMMENT ON COLUMN Sintoma.codsin IS 'Código do Sintoma';
COMMENT ON COLUMN Sintoma.nomsin IS 'Nome do Sintoma';
COMMENT ON COLUMN Sintoma.crisin IS 'Criticidade do sintoma (1 = Baixa, 2 = Média, 3 = Alta)';

CREATE TABLE Usuario_Sintoma (
  cpfusu numeric(11, 0) NOT NULL CONSTRAINT ususin_cpfusu_fk REFERENCES Usuario (cpfusu), 
  codsin int4 NOT NULL CONSTRAINT ususin_codsin_fk REFERENCES Sintoma (codsin), 
  intsin int4 NOT NULL,
  datinisin date NOT NULL,
  datfinsin date,
  CONSTRAINT usuario_sintoma_pk PRIMARY KEY (cpfusu, codsin)
);

COMMENT ON TABLE Usuario_Sintoma IS 'Relacionamento entre Paciente e Sintoma';
COMMENT ON COLUMN Usuario_Sintoma.intsin IS 'Intensidade do sintoma (1 = Baixa, 2 = Média, 3 = Alta)';
COMMENT ON COLUMN Usuario_Sintoma.datinisin IS 'Data de início do sintoma.';
COMMENT ON COLUMN Usuario_Sintoma.datfinsin IS 'Data de fim do sintoma';

CREATE TABLE Teste (
  codtes SERIAL NOT NULL CONSTRAINT teste_pk primary key, 
  restes char(1) NOT NULL, 
  dattes date NOT NULL, 
  cpfusu numeric(11, 0) NOT NULL CONSTRAINT tes_cpfusu_fk REFERENCES Usuario (cpfusu)
);

COMMENT ON TABLE Teste IS 'Cadastro do teste de covid.';
COMMENT ON COLUMN Teste.codtes IS 'Código do teste';
COMMENT ON COLUMN Teste.restes IS 'Resultado do teste de covid. (P = Positivo, N = Negativo)';
COMMENT ON COLUMN Teste.dattes IS 'Data da realização do teste de covid';

CREATE TABLE Vacina (
  codvac SERIAL NOT NULL CONSTRAINT vacina_pk primary key, 
  nomvac varchar(30) NOT NULL CONSTRAINT vac_nomvac_un UNIQUE
);

COMMENT ON TABLE Vacina IS 'Cadastro da vacina';
COMMENT ON COLUMN Vacina.codvac IS 'Código da vacina';
COMMENT ON COLUMN Vacina.nomvac IS 'Nome da vacina';

CREATE TABLE Vacinacao (
  codvaccao SERIAL NOT NULL CONSTRAINT vacinacao_pk primary key, 
  datvaccao date NOT NULL, 
  dosvaccao int4 NOT NULL, 
  codvac    int4 NOT NULL CONSTRAINT vac_codvac_fk REFERENCES Vacina (codvac), 
  cpfusu    numeric(11, 0) NOT NULL CONSTRAINT vac_cpfusu_fk REFERENCES Usuario (cpfusu)
);

COMMENT ON TABLE Vacinacao IS 'Cadastro da vacinação';
COMMENT ON COLUMN Vacinacao.codvaccao IS 'Código da vacinação';
COMMENT ON COLUMN Vacinacao.datvaccao IS 'Data da vacinação';
COMMENT ON COLUMN Vacinacao.dosvaccao IS 'Dose da vacinação (1 ou 2)';
