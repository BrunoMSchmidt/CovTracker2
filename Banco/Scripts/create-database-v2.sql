CREATE TABLE autenticacao (
  senaut varchar(255) NOT NULL, 
  cpfusu numeric(11, 0) NOT NULL, 
  PRIMARY KEY (cpfusu));
COMMENT ON COLUMN autenticacao.senaut IS 'Senha do usuário';
CREATE TABLE bairro (
  codbai SERIAL NOT NULL, 
  nombai varchar(40) NOT NULL, 
  cepbai numeric(8, 0) NOT NULL, 
  PRIMARY KEY (codbai));
COMMENT ON TABLE bairro IS 'Cadastro do bairro';
COMMENT ON COLUMN bairro.codbai IS 'Código do bairro';
COMMENT ON COLUMN bairro.nombai IS 'Nome do bairro';
COMMENT ON COLUMN bairro.cepbai IS 'Cep do bairro /ou cidade';
CREATE TABLE cidade (
  codcid SERIAL NOT NULL, 
  nomcid varchar(40) NOT NULL, 
  sigest varchar(2) NOT NULL, 
  PRIMARY KEY (codcid));
COMMENT ON TABLE cidade IS 'Cadastro da cidade';
COMMENT ON COLUMN cidade.codcid IS 'Código da cidade';
COMMENT ON COLUMN cidade.nomcid IS 'Nome da cidade';
CREATE TABLE comorbidade (
  codcom SERIAL NOT NULL, 
  nomcom varchar(100) NOT NULL UNIQUE, 
  PRIMARY KEY (codcom));
COMMENT ON TABLE comorbidade IS 'Cadastro de comorbidades';
COMMENT ON COLUMN comorbidade.codcom IS 'Código da comorbidade';
COMMENT ON COLUMN comorbidade.nomcom IS 'Nome da comorbidade';
CREATE TABLE empresa (
  cnpjemp   numeric(14, 0) NOT NULL, 
  nomfanemp varchar(60), 
  razsocemp varchar(60) NOT NULL UNIQUE, 
  telemp    numeric(11, 0) NOT NULL, 
  emaemp    varchar(50) NOT NULL, 
  areatuemp varchar(50), 
  codend    int4 NOT NULL, 
  PRIMARY KEY (cnpjemp));
COMMENT ON TABLE empresa IS 'empresa Responsavel pelo paciente';
COMMENT ON COLUMN empresa.cnpjemp IS 'CNPJ da empresa';
COMMENT ON COLUMN empresa.nomfanemp IS 'Nome Fantasia da empresa';
COMMENT ON COLUMN empresa.razsocemp IS 'Razão Social da empresa';
COMMENT ON COLUMN empresa.telemp IS 'Cadastro do telefone da empresa';
COMMENT ON COLUMN empresa.emaemp IS 'Email da empresa';
COMMENT ON COLUMN empresa.areatuemp IS 'Área de atuação da empresa';
CREATE TABLE endereco (
  codend SERIAL NOT NULL, 
  ruaend varchar(40) NOT NULL, 
  numend varchar(8), 
  comend varchar(40), 
  codcid int4 NOT NULL, 
  codbai int4, 
  PRIMARY KEY (codend));
COMMENT ON TABLE endereco IS 'Cadastro do endereço';
COMMENT ON COLUMN endereco.codend IS 'Código do endereco';
COMMENT ON COLUMN endereco.ruaend IS 'Rua do endereço';
COMMENT ON COLUMN endereco.numend IS 'Número do endereço';
COMMENT ON COLUMN endereco.comend IS 'Complemento do endereço';
CREATE TABLE estado (
  sigest varchar(2) NOT NULL, 
  nomest varchar(40) NOT NULL UNIQUE, 
  PRIMARY KEY (sigest));
COMMENT ON TABLE estado IS 'Cadastro do estado';
COMMENT ON COLUMN estado.sigest IS 'Sigla do estado';
COMMENT ON COLUMN estado.nomest IS 'Nome do estado';
CREATE TABLE orientacao (
  codori SERIAL NOT NULL, 
  desori varchar(255) NOT NULL, 
  PRIMARY KEY (codori));
COMMENT ON TABLE orientacao IS 'Cadastro de orientações';
COMMENT ON COLUMN orientacao.codori IS 'Código da orientação';
COMMENT ON COLUMN orientacao.desori IS 'Descrição da orientação';
CREATE TABLE sintoma (
  codsin SERIAL NOT NULL, 
  nomsin varchar(30) NOT NULL UNIQUE, 
  crisin int4 NOT NULL, 
  PRIMARY KEY (codsin));
COMMENT ON TABLE sintoma IS 'Cadastro do sintoma';
COMMENT ON COLUMN sintoma.codsin IS 'Código do sintoma';
COMMENT ON COLUMN sintoma.nomsin IS 'Nome do sintoma';
COMMENT ON COLUMN sintoma.crisin IS 'Criticidade do sintoma (1 = Baixa, 2 = Média, 3 = Alta)';
CREATE TABLE teste (
  codtes SERIAL NOT NULL, 
  restes char(1) NOT NULL, 
  dattes date NOT NULL, 
  cpfusu numeric(11, 0) NOT NULL, 
  PRIMARY KEY (codtes));
COMMENT ON TABLE teste IS 'Cadastro do teste de covid.';
COMMENT ON COLUMN teste.codtes IS 'Código do teste';
COMMENT ON COLUMN teste.restes IS 'Resultado do teste de covid. (P = Positivo, N = Negativo)';
COMMENT ON COLUMN teste.dattes IS 'Data da realização do teste de covid';
CREATE TABLE tipo_usuario (
  codtipusu SERIAL NOT NULL, 
  nomtipusu varchar(30) NOT NULL UNIQUE, 
  PRIMARY KEY (codtipusu));
COMMENT ON COLUMN tipo_usuario.codtipusu IS 'Código do tipo de usuário';
COMMENT ON COLUMN tipo_usuario.nomtipusu IS 'Nome do tipo de usuário (Paciente, Administrador...)';
CREATE TABLE usuario (
  cpfusu    numeric(11, 0) NOT NULL, 
  nomusu    varchar(60) NOT NULL, 
  datnasusu date, 
  sexusu    char(1), 
  pesusu    numeric(3, 0) NOT NULL, 
  altusu    numeric(3, 2) NOT NULL, 
  funempusu varchar(40) NOT NULL, 
  cnpjemp   numeric(14, 0) NOT NULL, 
  codtipusu int4 NOT NULL, 
  codend    int4 NOT NULL, 
  PRIMARY KEY (cpfusu));
COMMENT ON TABLE usuario IS 'Cadastro do usuário';
COMMENT ON COLUMN usuario.cpfusu IS 'CPF do usuário';
COMMENT ON COLUMN usuario.nomusu IS 'Nome do Usuário.';
COMMENT ON COLUMN usuario.datnasusu IS 'Data de nascimento do usuario.';
COMMENT ON COLUMN usuario.sexusu IS 'Sexo do usuário: (M = Masculino, F = Feminino).';
COMMENT ON COLUMN usuario.pesusu IS 'Peso do usuario';
COMMENT ON COLUMN usuario.altusu IS 'Altura do usuario em metros';
COMMENT ON COLUMN usuario.funempusu IS 'Função do usuário na empresa';
CREATE TABLE usuario_comorbidade (
  cpfusu numeric(11, 0) NOT NULL, 
  codcom int4 NOT NULL, 
  PRIMARY KEY (cpfusu, codcom));
CREATE TABLE usuario_sintoma (
  cpfusu    numeric(11, 0) NOT NULL, 
  codsin    int4 NOT NULL, 
  intsin    int4 NOT NULL, 
  datinisin date NOT NULL, 
  datfinsin date, 
  PRIMARY KEY (cpfusu, codsin));
COMMENT ON TABLE usuario_sintoma IS 'Relacionamento entre Paciente e sintoma';
COMMENT ON COLUMN usuario_sintoma.intsin IS 'Intensidade do sintoma (1 = Baixa, 2 = Média, 3 = Alta)';
COMMENT ON COLUMN usuario_sintoma.datinisin IS 'Data de início do sintoma';
COMMENT ON COLUMN usuario_sintoma.datfinsin IS 'Data final do sintoma';
CREATE TABLE usuario_telefone (
  cpfusu numeric(11, 0) NOT NULL, 
  telpac numeric(11, 0) NOT NULL, 
  PRIMARY KEY (cpfusu, telpac));
COMMENT ON TABLE usuario_telefone IS 'Cadastro de telefones do usuário.';
COMMENT ON COLUMN usuario_telefone.telpac IS 'Telefone do paciente';
CREATE TABLE vacina (
  codvac SERIAL NOT NULL, 
  nomvac varchar(30) NOT NULL UNIQUE, 
  PRIMARY KEY (codvac));
COMMENT ON TABLE vacina IS 'Cadastro da vacina';
COMMENT ON COLUMN vacina.codvac IS 'Código da vacina';
COMMENT ON COLUMN vacina.nomvac IS 'Nome da vacina';
CREATE TABLE vacinacao (
  codvaccao SERIAL NOT NULL, 
  datvaccao date NOT NULL, 
  dosvaccao int4 NOT NULL, 
  codvac    int4 NOT NULL, 
  cpfusu    numeric(11, 0) NOT NULL, 
  PRIMARY KEY (codvaccao));
COMMENT ON TABLE vacinacao IS 'Cadastro da vacinação';
COMMENT ON COLUMN vacinacao.codvaccao IS 'Código da vacinação';
COMMENT ON COLUMN vacinacao.datvaccao IS 'Data da vacinação';
COMMENT ON COLUMN vacinacao.dosvaccao IS 'Dose da vacinação (1 ou 2)';
ALTER TABLE usuario_sintoma ADD CONSTRAINT ususin_codsin_fk FOREIGN KEY (codsin) REFERENCES sintoma (codsin);
ALTER TABLE usuario_comorbidade ADD CONSTRAINT usucom_codcom_fk FOREIGN KEY (codcom) REFERENCES comorbidade (codcom);
ALTER TABLE usuario_telefone ADD CONSTRAINT usutel_cpfusu_fk FOREIGN KEY (cpfusu) REFERENCES usuario (cpfusu);
ALTER TABLE usuario_sintoma ADD CONSTRAINT ususin_cpfusu_fk FOREIGN KEY (cpfusu) REFERENCES usuario (cpfusu);
ALTER TABLE usuario_comorbidade ADD CONSTRAINT usucom_cpfusu_fk FOREIGN KEY (cpfusu) REFERENCES usuario (cpfusu);
ALTER TABLE usuario ADD CONSTRAINT usu_codtipusu_fk FOREIGN KEY (codtipusu) REFERENCES tipo_usuario (codtipusu);
ALTER TABLE usuario ADD CONSTRAINT usu_cnpjemp_fk FOREIGN KEY (cnpjemp) REFERENCES empresa (cnpjemp);
ALTER TABLE cidade ADD CONSTRAINT cid_sigest_fk FOREIGN KEY (sigest) REFERENCES estado (sigest);
ALTER TABLE usuario ADD CONSTRAINT usu_codend_fk FOREIGN KEY (codend) REFERENCES endereco (codend);
ALTER TABLE empresa ADD CONSTRAINT emp_codend_fk FOREIGN KEY (codend) REFERENCES endereco (codend);
ALTER TABLE teste ADD CONSTRAINT tes_cpfusu_fk FOREIGN KEY (cpfusu) REFERENCES usuario (cpfusu);
ALTER TABLE vacinacao ADD CONSTRAINT vac_codvac_fk FOREIGN KEY (codvac) REFERENCES vacina (codvac);
ALTER TABLE vacinacao ADD CONSTRAINT vac_cpfusu_fk FOREIGN KEY (cpfusu) REFERENCES usuario (cpfusu);
ALTER TABLE endereco ADD CONSTRAINT end_codcid_fk FOREIGN KEY (codcid) REFERENCES cidade (codcid);
ALTER TABLE endereco ADD CONSTRAINT end_codbai_fk FOREIGN KEY (codbai) REFERENCES bairro (codbai);
ALTER TABLE autenticacao ADD CONSTRAINT aut_cpfusu_fk FOREIGN KEY (cpfusu) REFERENCES usuario (cpfusu);
