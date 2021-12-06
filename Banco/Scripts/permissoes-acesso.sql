create group paciente;
create group funcionario;
create group administrador;


--  Permiss�es para todas as tabelas do banco para o administrador do sistema--
grant select, insert, update, delete
on cidade, comorbidade, empresa, endereco, estado, orientacao, sintoma, teste, tipo_usuario, usuario, usuario_comorbidade, usuario_sintoma, usuario_telefone, vacina, usuario_vacina
to administrador;

--Permiss�es de acesso para funcionarios do sistema--
grant select, update, insert
on cidade, comorbidade, empresa, endereco, estado, orientacao, sintoma, teste, usuario, usuario_comorbidade, usuario_sintoma, usuario_telefone, vacina, usuario_vacina
to funcionario;

--Permiss�es de acesso para paciente do sitema--
grant select, update
on cidade, comorbidade, empresa, endereco, estado, orientacao, sintoma, teste, usuario, usuario_comorbidade, usuario_sintoma, usuario_telefone, vacina, usuario_vacina
to paciente;



