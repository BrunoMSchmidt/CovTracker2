CREATE TABLE cidade (
  codcid SERIAL NOT NULL, 
  nomcid varchar(40) NOT NULL, 
  sigest varchar(2) NOT NULL, 
  PRIMARY KEY (codcid));
COMMENT ON TABLE cidade IS 'Cadastro da cidade';
COMMENT ON COLUMN cidade.codcid IS 'C�digo da cidade';
COMMENT ON COLUMN cidade.nomcid IS 'Nome da cidade';
CREATE TABLE comorbidade (
  codcom SERIAL NOT NULL, 
  nomcom varchar(100) NOT NULL UNIQUE, 
  PRIMARY KEY (codcom));
COMMENT ON TABLE comorbidade IS 'Cadastro de comorbidades';
COMMENT ON COLUMN comorbidade.codcom IS 'C�digo da comorbidade';
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
COMMENT ON COLUMN empresa.razsocemp IS 'Raz�o Social da empresa';
COMMENT ON COLUMN empresa.telemp IS 'Cadastro do telefone da empresa';
COMMENT ON COLUMN empresa.emaemp IS 'Email da empresa';
COMMENT ON COLUMN empresa.areatuemp IS '�rea de atua��o da empresa';
CREATE TABLE endereco (
  codend SERIAL NOT NULL, 
  ruaend varchar(40) NOT NULL, 
  numend varchar(8), 
  comend varchar(40), 
  codcid int4 NOT NULL, 
  cepend numeric(8, 0) not NULL,
  baiend varchar(40),
  PRIMARY KEY (codend));
COMMENT ON TABLE endereco IS 'Cadastro do endere�o';
COMMENT ON COLUMN endereco.codend IS 'C�digo do endereco';
COMMENT ON COLUMN endereco.ruaend IS 'Rua do endere�o';
COMMENT ON COLUMN endereco.numend IS 'N�mero do endere�o';
COMMENT ON COLUMN endereco.comend IS 'Complemento do endere�o';
COMMENT ON COLUMN endereco.cepend IS 'Cep do endereco';
COMMENT ON COLUMN endereco.baiend IS 'Bairro do endereco';
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
COMMENT ON TABLE orientacao IS 'Cadastro de orienta��es';
COMMENT ON COLUMN orientacao.codori IS 'C�digo da orienta��o';
COMMENT ON COLUMN orientacao.desori IS 'Descri��o da orienta��o';
CREATE TABLE sintoma (
  codsin SERIAL NOT NULL, 
  nomsin varchar(30) NOT NULL UNIQUE, 
  crisin int4 NOT NULL, 
  PRIMARY KEY (codsin));
COMMENT ON TABLE sintoma IS 'Cadastro do sintoma';
COMMENT ON COLUMN sintoma.codsin IS 'C�digo do sintoma';
COMMENT ON COLUMN sintoma.nomsin IS 'Nome do sintoma';
COMMENT ON COLUMN sintoma.crisin IS 'Criticidade do sintoma (1 = Baixa, 2 = M�dia, 3 = Alta)';
CREATE TABLE teste (
  codtes SERIAL NOT NULL, 
  restes char(1) NOT NULL, 
  dattes date NOT NULL, 
  cpfusu numeric(11, 0) NOT NULL, 
  PRIMARY KEY (codtes));
COMMENT ON TABLE teste IS 'Cadastro do teste de covid.';
COMMENT ON COLUMN teste.codtes IS 'C�digo do teste';
COMMENT ON COLUMN teste.restes IS 'Resultado do teste de covid. (P = Positivo, N = Negativo)';
COMMENT ON COLUMN teste.dattes IS 'Data da realiza��o do teste de covid';
CREATE TABLE tipo_usuario (
  codtipusu SERIAL NOT NULL, 
  nomtipusu varchar(30) NOT NULL UNIQUE, 
  PRIMARY KEY (codtipusu));
COMMENT ON COLUMN tipo_usuario.codtipusu IS 'C�digo do tipo de usu�rio';
COMMENT ON COLUMN tipo_usuario.nomtipusu IS 'Nome do tipo de usu�rio (Paciente, Administrador...)';
CREATE TABLE usuario (
  cpfusu    numeric(11, 0) NOT NULL, 
  nomusu    varchar(60) NOT NULL, 
  datnasusu date, 
  emausu	varchar(50) not null unique,
  senusu	varchar(50) not null,
  sexusu    char(1), 
  pesusu    numeric(3, 0) NOT NULL, 
  altusu    numeric(3, 2) NOT NULL, 
  funempusu varchar(40) NOT NULL, 
  cnpjemp   numeric(14, 0) NOT NULL, 
  codtipusu int4 NOT NULL, 
  codend    int4 NOT NULL,
  situsu varchar(30),
  obsusu varchar(255),
  PRIMARY KEY (cpfusu));
COMMENT ON TABLE usuario IS 'Cadastro do usu�rio';
COMMENT ON COLUMN usuario.cpfusu IS 'CPF do usu�rio';
COMMENT ON COLUMN usuario.nomusu IS 'Nome do Usu�rio.';
COMMENT ON COLUMN usuario.datnasusu IS 'Data de nascimento do usuario.';
COMMENT ON COLUMN usuario.sexusu IS 'Sexo do usu�rio: (M = Masculino, F = Feminino).';
COMMENT ON COLUMN usuario.pesusu IS 'Peso do usuario';
COMMENT ON COLUMN usuario.altusu IS 'Altura do usuario em metros';
COMMENT ON COLUMN usuario.funempusu IS 'Fun��o do usu�rio na empresa';
COMMENT ON COLUMN usuario.emausu IS 'Email do usuario';
COMMENT ON COLUMN usuario.senusu IS 'Senha do usuario';
COMMENT ON COLUMN usuario.situsu IS 'Situacao do usuario("positivado", "em observacao...")';
COMMENT ON COLUMN usuario.obsusu IS 'Observacoes';
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
COMMENT ON COLUMN usuario_sintoma.intsin IS 'Intensidade do sintoma (1 = Baixa, 2 = M�dia, 3 = Alta)';
COMMENT ON COLUMN usuario_sintoma.datinisin IS 'Data de in�cio do sintoma';
COMMENT ON COLUMN usuario_sintoma.datfinsin IS 'Data final do sintoma';
CREATE TABLE usuario_telefone (
  cpfusu numeric(11, 0) NOT NULL, 
  telpac numeric(11, 0) NOT NULL, 
  PRIMARY KEY (cpfusu, telpac));
COMMENT ON TABLE usuario_telefone IS 'Cadastro de telefones do usu�rio.';
COMMENT ON COLUMN usuario_telefone.telpac IS 'Telefone do paciente';
CREATE TABLE vacina (
  codvac SERIAL NOT NULL, 
  nomvac varchar(30) NOT NULL UNIQUE, 
  PRIMARY KEY (codvac));
COMMENT ON TABLE vacina IS 'Cadastro da vacina';
COMMENT ON COLUMN vacina.codvac IS 'C�digo da vacina';
COMMENT ON COLUMN vacina.nomvac IS 'Nome da vacina';
CREATE TABLE usuario_vacina (
  codusuvac serial not null,
  datusuvac date NOT NULL, 
  dosusuvac int4 NOT NULL, 
  codvac    int4 NOT NULL, 
  cpfusu    numeric(11, 0) NOT NULL, 
  PRIMARY KEY (codusuvac));
COMMENT ON TABLE usuario_vacina IS 'Cadastro da vacina��o';
COMMENT ON COLUMN usuario_vacina.datusuvac IS 'Data da vacina��o';
COMMENT ON COLUMN usuario_vacina.dosusuvac IS 'Dose da vacina��o (1 ou 2)';
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
ALTER TABLE usuario_vacina ADD CONSTRAINT vac_codvac_fk FOREIGN KEY (codvac) REFERENCES vacina (codvac);
ALTER TABLE usuario_vacina ADD CONSTRAINT vac_cpfusu_fk FOREIGN KEY (cpfusu) REFERENCES usuario (cpfusu);
ALTER TABLE endereco ADD CONSTRAINT end_codcid_fk FOREIGN KEY (codcid) REFERENCES cidade (codcid);

