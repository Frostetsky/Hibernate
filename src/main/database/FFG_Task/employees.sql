База данных - PostgreSQL.

Необходимо написать запрос, который покажет всех сотрудников, у кого в работе менее трех задач.
================================================================
select employee.emp_name, count(t.id)
from employee
left join tasks t on employee.id = t.employee_id
group by employee.id
having count(employee.id) < 3;
================================================================


Необходимо написать запрос, который покажет сумму платежей по основному долгу и процентам.
Суммы необходимо считать только по тем договорам, которые платили и основной долг и проценты.
Результат предоставить в виде: id договора, сумма платежей по основному долгу, сумма платежей по процентам.
================================================================
SELECT payments_principal.agr_id, SUM(payments_principal.payment_sum) AS main_money,
SUM(payments_interest.payment_sum) AS procent_money
FROM payments_principal INNER JOIN payments_interest ON payments_interest.agr_id = payments_principal.agr_id
GROUP BY agr_id;
================================================================

