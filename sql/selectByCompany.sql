select 
	p.name as person,
	c.name as company
from 
	person p
	join company c
	on p.company_id = c.id
where 
	p.company_id <> 1;

select
	t1.company,
	t1.count
from
	(select 
		c.name company,
		count(p.id) count
	from company c
		join person p
		on c.id = p.company_id
	group by
		c.name) as t1
	join (select
				max(t1.count) count
		from
			(select 
				count(p.id) count
			from company c
				join person p
				on c.id = p.company_id
			group by
				c.name) as t1) as t2
	on t1.count = t2.count
	