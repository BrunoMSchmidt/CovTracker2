create group usuario;
create group funcionario;
create group gerente;


--  Permissões para todas as tabelas do banco para o administrador do sistema--
grant select, insert, update, delete
on cidade, comorbidade, empresa, endereco, estado, orientacao, sintoma, teste, tipo_usuario, usuario, usuario_comorbidade, usuario_sintoma, usuario_telefone, vacina, usuario_vacina
to gerente;

--Permissões de acesso para funcionarios do sistema--
grant select, update, insert
on cidade, comorbidade, empresa, endereco, estado, orientacao, sintoma, teste, usuario, usuario_comorbidade, usuario_sintoma, usuario_telefone, vacina, usuario_vacina
to funcionario;

--Permissões de acesso para usuarios do sitema--
grant select
on cidade, comorbidade, empresa, endereco, estado, orientacao, sintoma, teste, usuario, usuario_comorbidade, usuario_sintoma, usuario_telefone, vacina, usuario_vacina
to usuario;



