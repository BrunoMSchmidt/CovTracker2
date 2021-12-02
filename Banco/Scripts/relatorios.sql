CREATE VIEW vw_rel_1 AS
SELECT us.cpfusu AS CPF,nomusu AS Nome
FROM usuario u
INNER JOIN usuario_sintoma us ON u.cpfusu = us.cpfusu
INNER JOIN sintoma s ON s.codsin = us.codsin AND nomsin = 'Febre'
WHERE ((CURRENT_DATE - u.datnasusu)/365) BETWEEN 60 AND 70
ORDER BY nomusu ASC;

COMMENT ON VIEW vw_rel_1 IS 'Código e nome de pacientes com entre 60 e 70 anos que apresentaram febre. Ordem ascendente de nome';

CREATE VIEW vw_rel_2 AS
SELECT nomusu AS nome, nomcid AS cidade
FROM usuario u
INNER JOIN endereco e ON u.codend = e.codend
INNER JOIN cidade c ON e.codcid = c.codcid
    AND nomcid IN ('Descanso', 'Pinhalzinho', 'Maravilha', 'Chapecó', 'Itapiranga')
WHERE u.sexusu = 'F' AND NOT u.cpfusu IN (
    SELECT u.cpfusu FROM teste t
    INNER JOIN usuario u ON t.cpfusu = u.cpfusu AND t.restes = 'P'
)
ORDER BY nomcid ASC, nomusu DESC;

COMMENT ON VIEW vw_rel_2 IS 'Nome do paciente e cidade de pacientes do sexo feminino residentes de Maravilha, Descanso, Pinhalzinho, Chapecó e Itapiranga que apresentaram sintomas e não foram positivados com covid. ordenado por Cidade ascendente e Nome descendente';

CREATE VIEW vw_rel_3 as
select c.codcid as codigo_cidade, nomcid as cidade , count(cpfusu) as suspeitas
from cidade c
left join endereco e on c.codcid = e.codcid
left join usuario u on e.codend = u.codend
    and u.cpfusu in (
        select cpfusu
        from usuario_sintoma
        inner join sintoma s on s.codsin = usuario_sintoma.codsin
        group by cpfusu
        having sum(crisin) >= 2
    )
group by c.codcid
order by suspeitas desc, c.codcid asc;

COMMENT ON VIEW vw_rel_3 IS 'Relátorio de casos suspeitos por cidade.';

CREATE VIEW vw_rel_4 AS
SELECT ((CURRENT_DATE - u.datnasusu)/365) AS idade, count(u.cpfusu) AS casos
FROM teste t
INNER JOIN usuario u ON t.cpfusu = u.cpfusu AND t.restes = 'P'
WHERE t.dattes BETWEEN '01/08/2020' AND '31/10/2020'
GROUP BY idade
ORDER BY casos DESC;

select * from teste;

COMMENT ON VIEW vw_rel_4 IS 'Relatório de casos por idade';

