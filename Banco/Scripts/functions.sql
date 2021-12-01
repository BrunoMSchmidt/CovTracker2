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