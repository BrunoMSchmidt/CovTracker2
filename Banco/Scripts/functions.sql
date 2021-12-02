--Criando função para contagem de usuarios

create or replace function conta_usuarios()
returns numeric
as 
$body$
declare
conta numeric :=0;
begin
	select count(*) into conta from usuario;
	return conta;
end
$body$
language plpgsql;

select conta_usuarios(); 

--Criando função para contagem de registro 
create or replace function conta_registro(nome_tabela varchar)
returns setof record
as
$body$
declare
	tabela varchar; 
	conta numeric;
	rec record;
begin 
	--pg-tables
	for tabela in (select tablename from pg_tables
	where tablename ilike nome_tabela and schemaname = 'public')
	loop
	---select count(*) from tabela; da erro
	execute format('select count(*) from %I', tabela) into conta;
	return next rec; --retorna o record
	end loop;
end
$body$
language plpgsql;

select * from conta_registro('comorbidade c %')
as (tabela varchar, conta numeric);

--CRIAR TRIGGER PARA VERIFICAÇÃO DA VACINA
-- PARA NÃO PODER TOMAR A VACINA ANTES OU EM ATRASO DO PRAZO


---Criando trigger para verificar o caso do usuario
--create or replace function verifica_caso()
--returns trigger 
--as
--$body$
--declare
--	sit_caso varchar;
--begin	
	
--end

create trigger data_vacina_tg
before insert 
on usuario_vacina
for each row
execute procedure