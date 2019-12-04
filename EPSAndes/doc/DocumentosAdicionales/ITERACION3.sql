--Consulta 9

with cantidadS as(
SELECT sum(count(distinct id_servicio)) c2 FROM CITA WHERE ID_SERVICIO IN('Consultas odontologicas','Radiografias') group by id_servicio
),
cantidadXUS as(
select count(distinct c.id_servicio) c1,c.id_afiliado FROM CITA c WHERE c.ID_SERVICIO IN('Consultas odontologicas','Radiografias') and c.cumplida = 1 and to_date(c.fecha,'dd/mm/yyyy hh24:mi:ss') between to_date('25/12/2000 12:00:00','dd/mm/yyyy hh24:mi:ss') and to_date('25/12/2019 12:00:00','dd/mm/yyyy hh24:mi:ss') group by c.id_afiliado order by c.id_afiliado desc
),
selecxS as(
SELECT * FROM USUARIO u, AFILIADO a, cantidadXUS c,cantidadS cs where a.IDENTIFICACION=u.NUMERO_DOCUMENTO AND c.id_afiliado=a.IDENTIFICACION and c.c1=cs.c2
),
cantidadT as(
SELECT sum(count(distinct s.tipo)) c2 FROM CITA c,SERVICIO s WHERE c.ID_SERVICIO=s.nombre and s.tipo IN('Consulta de control') group by s.tipo
),
cantidadXUT as(
select count(distinct s.tipo) c1,id_afiliado FROM CITA c, SERVICIO s WHERE c.ID_SERVICIO=s.nombre and s.tipo IN('Consulta de control') and c.cumplida=1 group by id_afiliado order by count(distinct s.tipo) desc
),
selecxT as(
SELECT * FROM USUARIO u, AFILIADO a, cantidadXUT c,cantidadT cs where a.IDENTIFICACION=u.NUMERO_DOCUMENTO AND c.id_afiliado=a.IDENTIFICACION and c.c1=cs.c2
),
selectI as( select distinct c.id_afiliado from CITA c, PRESTAN p where c.fecha=p.dia And c.id_servicio=p.id_servicio and p.id_ips in(select ID_IPS from PRESTAN)
)
select  s1.email,s1.nombre,s1.numero_documento,s1.rol,s1.tipo_documento,s1.fecha_nacimiento from selecxS s1 where s1.identificacion in(select id_afiliado from selecxT) and s1.identificacion in(select id_afiliado from selectI);

--Consulta 10

with cantidadS as(
SELECT sum(count(distinct id_servicio)) c2 FROM CITA WHERE ID_SERVICIO  IN('Consultas odontologicas','Radiografias') group by id_servicio
),
cantidadXUS as(
select count(distinct c.id_servicio) c1,c.id_afiliado FROM CITA c WHERE c.ID_SERVICIO  IN('Consultas odontologicas','Radiografias') and c.cumplida = 1 and to_date(c.fecha,'dd/mm/yyyy hh24:mi:ss')  between to_date('25/12/2000 12:00:00','dd/mm/yyyy hh24:mi:ss') and to_date('25/12/2019 12:00:00','dd/mm/yyyy hh24:mi:ss') group by c.id_afiliado order by c.id_afiliado desc
),
selecxS as(
SELECT * FROM USUARIO u, AFILIADO a, cantidadXUS c,cantidadS cs where a.IDENTIFICACION=u.NUMERO_DOCUMENTO AND c.id_afiliado=a.IDENTIFICACION and c.c1=cs.c2
),
cantidadT as(
SELECT sum(count(distinct s.tipo)) c2 FROM CITA c,SERVICIO s WHERE c.ID_SERVICIO=s.nombre and s.tipo  IN('Consulta de control') group by s.tipo
),
cantidadXUT as(
select count(distinct s.tipo) c1,id_afiliado FROM CITA c, SERVICIO s WHERE c.ID_SERVICIO=s.nombre and s.tipo  IN('Consulta de control') and c.cumplida=1 group by id_afiliado order by count(distinct s.tipo) desc
),
selecxT as(
SELECT * FROM USUARIO u, AFILIADO a, cantidadXUT c,cantidadT cs where a.IDENTIFICACION=u.NUMERO_DOCUMENTO AND c.id_afiliado=a.IDENTIFICACION and c.c1=cs.c2
),
selectI as( select distinct c.id_afiliado from CITA c, PRESTAN p where c.fecha=p.dia And c.id_servicio=p.id_servicio and p.id_ips  in(select ID_IPS from PRESTAN)
),
npi as(select a.identificacion identificacion, 'Si' as NoTieneCitasCumplidas from AFILIADO a where a.identificacion not in (select distinct id_afiliado from cita where cumplida=1))
select s1.identificacion,u.nombre,u.email,u.tipo_documento,s1.fecha_nacimiento from AFILIADO s1, USUARIO u 
where s1.identificacion=u.numero_documento 
and s1.identificacion not in(select identificacion from selecxS) 
and s1.identificacion not in(select id_afiliado from selecxT)
and s1.identificacion not in(select id_afiliado from selectI);

--Consulta 11 
with tt1 as(SELECT (COUNT(s.TIPO)) ca,
                  s.TIPO as tipo,
                 to_char(to_date(c.FECHA, 'dd/mm/yyyy hh24:mi:ss'), 'WW/YYYY') AS SEMANA
          FROM CITA c,
               SERVICIO s
          WHERE c.ID_SERVICIO = s.NOMBRE
            AND c.CUMPLIDA = 1
          GROUP BY to_char(to_date(c.FECHA, 'dd/mm/yyyy hh24:mi:ss'), 'WW/YYYY'), s.TIPO
          order by semana desc),
tb_mayor as (
    SELECT tt1.SEMANA as SEMANA, listagg(tt1.tipo,', ')within group(order by tt1.tipo) as TiposMasUsados, t2.c1 as CantidadTipoMasUsado
    FROM tt1,
         (SELECT MAX(ca) C1, SEMANA
          FROM (SELECT   ca, TIPO,SEMANA FROM tt1)
          GROUP BY SEMANA) t2
    WHERE tt1.SEMANA = t2.SEMANA
      AND tt1.ca = t2.c1 group by tt1.SEMANA, t2.c1
),
tb_menor as (
    SELECT tt1.SEMANA as SEMANA, listagg(tt1.tipo,', ')within group(order by tt1.tipo) as TiposMenosUsados, t2.c1 as CantidadTipoMenosUsado
    FROM  tt1,
         (SELECT MIN(ca) C1, SEMANA
          FROM (SELECT   ca, TIPO,SEMANA FROM tt1)
          GROUP BY SEMANA) t2
    WHERE tt1.SEMANA = t2.SEMANA
      AND tt1.ca = t2.c1 
      group by tt1.SEMANA, t2.c1
),
t1 as(SELECT (COUNT(s.TIPO))                         ca,
                  s.NOMBRE as NOMBRE,
                 to_char(to_date(c.FECHA, 'dd/mm/yyyy hh24:mi:ss'), 'WW/YYYY') AS SEMANA
          FROM CITA c,
               SERVICIO s
          WHERE c.ID_SERVICIO = s.NOMBRE
            AND c.CUMPLIDA = 1
          GROUP BY to_char(to_date(c.FECHA, 'dd/mm/yyyy hh24:mi:ss'), 'WW/YYYY'), s.NOMBRE
          ),
tb_mayorS as (
    SELECT t1.SEMANA as SEMANA, listagg(t1.NOMBRE,', ')within group(order by t1.NOMBRE) as ServiciosMasUsados, t2.c1 as CantidadServicioMasUsado
    FROM  t1,
         (SELECT MAX(ca) C1, SEMANA
          FROM (SELECT * FROM t1)
          GROUP BY SEMANA) t2
    WHERE t1.SEMANA = t2.SEMANA
      AND t1.ca = t2.c1 group by t1.SEMANA, t2.c1
),
tb_menorS as (
    SELECT t1.SEMANA as SEMANA, listagg(t1.NOMBRE,', ')within group(order by t1.NOMBRE) as ServiciosMenosUsados, t2.c1 as CantidadServicioMenosUsado
    FROM t1,
         (SELECT MIN(ca) C1, SEMANA
          FROM (SELECT *
                FROM t1)
          GROUP BY SEMANA) t2
    WHERE t1.SEMANA = t2.SEMANA
      AND t1.ca = t2.c1 
      group by t1.SEMANA, t2.c1
),
ti1 as(SELECT (COUNT(s.ID_IPS))                         ca,
                  s.ID_IPS as ID_IPS,
                 to_char(to_date(c.FECHA, 'dd/mm/yyyy hh24:mi:ss'), 'WW/YYYY') AS SEMANA
          FROM CITA c,
               Prestan s
          WHERE c.ID_SERVICIO = s.ID_SERVICIO
            AND c.CUMPLIDA = 1 and c.fecha=s.dia
          GROUP BY to_char(to_date(c.FECHA, 'dd/mm/yyyy hh24:mi:ss'), 'WW/YYYY'), s.ID_IPS
          ),
tb_mayorI as( SELECT ti1.SEMANA as SEMANA, listagg(ti1.ID_IPS,', ')within group(order by ti1.ID_IPS) as IPSMasSolicitadas, t2.c1 as CantidadIPSMasUsado
    FROM ti1,
         (SELECT MAX(ca) C1, SEMANA
          FROM ( SELECT *
                FROM ti1)
          GROUP BY SEMANA) t2
    WHERE ti1.SEMANA = t2.SEMANA
      AND ti1.ca = t2.c1 group by ti1.SEMANA, t2.c1
),
tb_menorI as( SELECT ti1.SEMANA as SEMANA, listagg(ti1.ID_IPS,', ')within group(order by ti1.ID_IPS) as IPSMenosSolicitadas, t2.c1 as CantidadIPSMenosUsado
    FROM  ti1,
         (SELECT MIN(ca) C1, SEMANA
          FROM (SELECT *
                FROM ti1)
          GROUP BY SEMANA) t2
    WHERE ti1.SEMANA = t2.SEMANA
      AND ti1.ca = t2.c1 group by ti1.SEMANA, t2.c1
),
tu1 as(SELECT (COUNT(c.id_afiliado))                         ca,
                  c.id_afiliado as id_afiliado,
                 to_char(to_date(c.FECHA, 'dd/mm/yyyy hh24:mi:ss'), 'WW/YYYY') AS SEMANA
          FROM CITA c
            WHERE c.CUMPLIDA = 1 
          GROUP BY to_char(to_date(c.FECHA, 'dd/mm/yyyy hh24:mi:ss'), 'WW/YYYY'), c.id_afiliado
          ),
tb_mayorU as(SELECT tu1.SEMANA as SEMANA, listagg(tu1.id_afiliado,', ')within group(order by tu1.id_afiliado) as AfiliadoMasSolicitador, t2.c1 as AfiliadoMasUsador
    FROM tu1,
         (SELECT MAX(ca) C1, SEMANA
          FROM (SELECT * FROM tu1)
          GROUP BY SEMANA) t2
    WHERE tu1.SEMANA = t2.SEMANA
      AND tu1.ca = t2.c1 group by tu1.SEMANA, t2.c1
    order by SEMANA DESC),
    tb_cantiCita as(
    SELECT COUNT(DISTINCT c.id_afiliado) AfiliadosNoCitas, to_char(to_date(c.FECHA, 'dd/mm/yyyy hh24:mi:ss'), 'WW/YYYY') AS SEMANA
          FROM CITA c
         WHERE c.CUMPLIDA = 1 
          GROUP BY to_char(to_date(c.FECHA, 'dd/mm/yyyy hh24:mi:ss'), 'WW/YYYY')
          ),
tb_cantidadAf as(select count(*) as cuenta from AFILIADO),
tb_total as(select cuenta-AfiliadosNoCitas,semana as SEMANA from tb_cantiCita, tb_cantidadAf)
select *
from tb_mayor t1 natural inner join tb_menor t2 natural inner join tb_mayorS natural inner join tb_menorS natural inner join tb_mayorI natural inner join tb_menorI natural inner join tb_mayorU NATURAL inner join tb_total;

--Consulta 12

WITH oth_no_esp as (select id_afiliado
from cita
inner join SERVICIO sds on CITA.ID_SERVICIO = SDS.NOMBRE
and sds.TIPO != 'Procedimiento medico especializado'),

ESPEC AS (
select id_afiliado, 1 siempre_espec,count(distinct sds.TIPO)cuenta
from cita
inner join SERVICIO sds on CITA.ID_SERVICIO = SDS.NOMBRE
and sds.TIPO = 'Procedimiento medico especializado'
where cita.id_afiliado not in (select id_afiliado from oth_no_esp)
group by id_afiliado
),

cit_mes as( -- Cuenta el número de meses distintos en los que ha hecho una cita
 select id_afiliado, count(distinct to_char(TO_DATE(fecha,'dd/mm/yyyy hh24:mi:ss'),'mm/yy')) as count_citas
 from cita
 group by id_afiliado
 order by count_citas desc),

min_fe as( -- Cuenta el número de meses distintos en los que ha hecho una cita
 select id_afiliado, min(TO_DATE(fecha,'dd/mm/yyyy hh24:mi:ss')) as first_cita
 from cita
 group by id_afiliado
 ),

month_fd  as (  -- Cuenta cuántos meses han transcurrido desde su primera cita
SELECT id_afiliado, MONTHS_BETWEEN 
   (TO_DATE('12-2019','MM-YYYY'),
   TO_DATE(to_char(first_cita,'mm/yyyy') ,'MM-YYYY')
     ) meses_primera_cita, first_cita
    FROM min_fe  ),

final_freq as (
select cit_mes.id_afiliado, first_cita, count_citas, meses_primera_cita
 from month_fd
    inner join cit_mes 
    on cit_mes.id_afiliado = month_fd.id_afiliado
    where count_citas = meses_primera_cita),
    
citas as(SELECT COUNT(*) as citasUsuario, id_afiliado FROM CITA
GROUP BY id_afiliado order by count(*) desc),

hospital as (SELECT COUNT(*) as cuentaHospitalizaciones, r.id_afiliado FROM 
ORDEN r, SERVICIO s WHERE r.NOM_SERVICIO=s.NOMBRE AND s.TIPO='Hospitalizacion'
GROUP BY r.id_afiliado),
c2 as(SELECT c.id_afiliado, 1 siempreHosp, c.citasUsuario as citas, h.cuentaHospitalizaciones as hospitalizaciones FROM citas c,hospital h WHERE c.id_afiliado=h.id_afiliado AND c.citasUSUARIO/2=h.cuentaHospitalizaciones)

select usuario.nombre, usuario.email, afiliado.identificacion, case when siempre_espec = 1 then  'Siempre especializado'
when count_citas is not null then 'Pide cita todos los meses' when siempreHosp=1 then 'Siempre hospitalizado'
end razon 
, case when count_citas is not null then count_citas else 0 end CitasSolicitadas ,case when cuenta is not null then cuenta else 0 end ServiciosDistintosSolicitados, case when hospitalizaciones is not null then hospitalizaciones else 0 end hospitalizaciones
from usuario left outer join afiliado on usuario.numero_documento=afiliado.identificacion
left outer join final_freq
on final_freq.id_afiliado = afiliado.identificacion
left outer join espec 
on espec.id_afiliado = afiliado.identificacion left outer join c2 on c2.id_afiliado=espec.id_afiliado
where siempre_espec = 1 or count_citas is not null or siempreHosp=1;
