
UPDATE Lancamento t1 SET observacao=sq.observacao || '- Possuem mais que 10 itens'
FROM  (
	select l.oid, l.observacao, count(i.oid)  from lancamentoitem li
	inner join lancamento l on l.oid = li.oid_lancamento
	inner join item i on i.oid = li.oid_item
	group by l.oid
	having count(i.oid)  > 10
   ) AS sq
WHERE  t1.oid=sq.oid;

