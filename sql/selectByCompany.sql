select 
	p.name as person,
	c.name as company
from 
	person p
	join company c
	on p.company_id = c.id
where 
	p.company_id <> 5;

select
	c.name company,
	count(p.id) count
from
	company c
	join person p
	on c.id = p.company_id
group by
	c.name
having 
	count(p.id) = (select count(id) from person group by company_id order by count(id) desc limit 1);
	