package uniandes.isis2304.epsAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.epsAndes.negocio.Cita;


/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto CITA de EPSAndes.
 */
class SQLCita 
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/** Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos Se renombra acá para facilitar la escritura de las sentencias. */
	private final static String SQL = PersistenciaEPSAndes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/** El manejador de persistencia general de la aplicación. */
	private PersistenciaEPSAndes pp;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor.
	 *
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLCita (PersistenciaEPSAndes pp)
	{
		this.pp = pp;
	}

	/**
	 * Crea y ejecuta la sentencia SQL para adicionar una CITA a la base de datos de EPSAndes.
	 *
	 * @param pm - El manejador de persistencia
	 * @param cumplida el estado cumplida
	 * @param id el id
	 * @param fecha la fecha
	 * @param id_Servicio el id servicio
	 * @param id_Afiliado el id afiliado
	 * @param id_Recepcionista el id recepcionista
	 * @param hora la hora
	 * @return EL número de tuplas insertadas
	 */
	public long adicionarCita(PersistenceManager pm, int cumplida, long id, String fecha, String id_Servicio,String id_Afiliado, String id_Recepcionista,String hora) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaCita() + "(CUMPLIDA, ID_ORDEN , FECHA, ID_SERVICIO, ID_AFILIADO, ID_RECEPCIONISTA, HORA) values (?,?, ?, ?, ?, ?, ?)");
		q.setParameters(cumplida,id,fecha,id_Servicio,id_Afiliado,id_Recepcionista,hora);
		return (long) q.executeUnique();            
	}

	/**
	 * Registrar prestacion.
	 *
	 * @param pm the pm
	 * @param idCita el id cita
	 * @param id_Recepcionista el id recepcionista
	 * @param id_servicio the id servicio
	 * @param id_paciente the id paciente
	 * @return the long
	 */
	public long registrarPrestacion(PersistenceManager pm, long idCita, String id_Recepcionista,String id_servicio,String id_paciente)
	{
		Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaCita() + " SET CUMPLIDA=?, ID_RECEPCIONISTA=? WHERE ID_ORDEN=? AND ID_SERVICIO=? AND ID_AFILIADO =? AND CUMPLIDA=0");
		q.setParameters(1,id_Recepcionista,idCita,id_servicio,id_paciente);
		return (long) q.executeUnique();   
	}

	/**
	 * Eliminar cita.
	 *
	 * @param pm the pm
	 * @param id_Servicio the id servicio
	 * @param id_Afiliado the id afiliado
	 */
	public void eliminarCita(PersistenceManager pm,String id_Servicio,String id_Afiliado )
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCita() + " WHERE ID_SERVICIO=? AND ID_AFILIADO =? ");
		q.setParameters(id_Servicio,id_Afiliado);
		q.executeUnique();  
	}

	/**
	 * Eliminar cita IPS.
	 *
	 * @param pm the pm
	 * @param id_Servicio the id servicio
	 * @param id_Afiliado the id afiliado
	 * @param ips the ips
	 */
	public void eliminarCitaIPS(PersistenceManager pm,String id_Servicio,String id_Afiliado,String ips )
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCita() + " WHERE ID_SERVICIO=? AND ID_AFILIADO =? AND ID_RECEPCIONISTA=? ");
		q.setParameters(id_Servicio,id_Afiliado,ips);
		q.executeUnique();  
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UNA CITA de la 
	 * base de datos de EPSAndes, por su identificador.
	 *
	 * @param pm - El manejador de persistencia
	 * @param identificacion la identificacion
	 * @return El objeto CITA que tiene el identificador dado
	 */
	public Cita darCitaPorId (PersistenceManager pm, long identificacion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCita() + " WHERE ID = ?");
		q.setResultClass(Cita.class);
		q.setParameters(identificacion);
		return (Cita) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar los 20 servicios más solicitados de la base de datos
	 * EPSAndes en un rango de fechas.
	 *
	 * @param pm - El manejador de persistencia
	 * @param fechaInicio la fecha inicio
	 * @param fechaFin la fecha fin
	 * @return Una lista de parejas de objetos, el primer elemento de cada pareja representa el identificador de un servicio,
	 * 	el segundo elemento representa el número de servicios solicitados en el rango
	 */
	public List<Object []> dar20ServiciosMasSolicitados (PersistenceManager pm, String fechaInicio, String fechaFin)
	{
		String sql = "SELECT DISTINCT ID_SERVICIO, count (*) as numServicios";
		sql += " FROM " + pp.darTablaCita();
		sql += " WHERE TO_DATE(FECHA,'DD/MM/YYYY HH24:MI:SS') BETWEEN TO_DATE(?,'DD/MM/YYYY HH24:MI:SS') AND TO_DATE(?,'DD/MM/YYYY HH24:MI:SS')";
		sql	+= " GROUP BY ID_SERVICIO ORDER BY COUNT(*) DESC FETCH NEXT 20 ROWS ONLY";
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(fechaInicio,fechaFin);
		return q.executeList();
	}

	/**
	 * Darindice de uso.
	 *
	 * @param pm the pm
	 * @return the list
	 */
	public List<Object[]> darindiceDeUso(PersistenceManager pm)
	{
		String sql="SELECT (aux2.cuenta/aux1.cuenta) indice, aux2.servicio"
				+ " FROM "
				+ "( SELECT COUNT(*) cuenta "
				+ "FROM CITA c "
				+ "WHERE c.CUMPLIDA = 1"
				+ ") aux1,"
				+ "( SELECT COUNT(*) cuenta, o.NOM_SERVICIO servicio "
				+ "FROM ORDEN o INNER JOIN CITA c "
				+ "ON o.ID = c.ID_ORDEN "
				+ "AND c.CUMPLIDA = 1 GROUP BY o.NOM_SERVICIO"
				+ ") aux2";
		Query q = pm.newQuery(SQL, sql);
		return q.executeList();
	}

	/**
	 * Sacar citas por IPS servicio.
	 *
	 * @param pm the pm
	 * @param ips the ips
	 * @param servicio the servicio
	 * @param fechaI the fecha I
	 * @param fechaF the fecha F
	 * @return the list
	 */
	public List<Object[]> sacarCitasPorIPSServicio(PersistenceManager pm,String ips,String servicio,String fechaI,String fechaF)
	{
		String sql="SELECT tc.ID_ORDEN, tc.FECHA, tc.HORA, tc.ID_AFILIADO";
		sql+= " FROM "+pp.darTablaCita() +" tc, "+ pp.darTablaPrestan()+" tp, "+ pp.darTablaRecepcionista_Ips() +" tr WHERE tp.ID_SERVICIO=tc.ID_SERVICIO AND tp.ID_SERVICIO=? AND tp.ID_IPS=? AND tp.ID_IPS=tc.ID_RECEPCIONISTA AND TO_DATE(tc.FECHA,'DD/MM/YYYY HH24:MI:SS') BETWEEN TO_DATE(?,'DD/MM/YYYY HH24:MI:SS') AND TO_DATE(?,'DD/MM/YYYY HH24:MI:SS')";
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(servicio,ips,fechaI,fechaF);
		return q.executeList();
	}

	/**
	 * Utilizacion por afiliado.
	 *
	 * @param pm the pm
	 * @param idAfiliado the id afiliado
	 * @param fechaInic the fecha inic
	 * @param fechaFin the fecha fin
	 * @return the list
	 */
	public List<Object[]> utilizacionPorAfiliado (PersistenceManager pm,String idAfiliado, String fechaInic, String fechaFin){
		String sql="SELECT COUNT(*) uso, o.NOM_SERVICIO servicio "
				+ "FROM CITA c, Orden o"
				+ " WHERE c.ID_ORDEN = o.ID AND o.ID_AFILIADO = ? "
				+ "AND TO_DATE(c.FECHA, 'DD/MM/YYYY HH24:MI:SS') " 
				+ "BETWEEN TO_DATE(?, 'DD/MM/YYYY HH24:MI:SS') AND TO_DATE(?, 'DD/MM/YYYY HH24:MI:SS') "
				+ "GROUP BY o.NOM_SERVICIO" ;
		Query q = pm.newQuery(SQL,sql);
		q.setParameters(idAfiliado,fechaInic,fechaFin);
		return q.executeList();
	}

	/**
	 * Dar exigentes.
	 *
	 * @param pm the pm
	 * @return the list
	 */

	public List<Object[]> reqC9 (PersistenceManager pm , String pServicios, String pTipos , String pFechaInicial , String  PFechaFinal , String pIPS , String   pOrdenamiento , String pAgrupamiento){
		String sql="select s1.email,s1.nombre,s1.numero_documento,s1.rol,s1.tipo_documento,s1.fecha_nacimiento from ( " + 
				"SELECT * FROM USUARIO u, AFILIADO a, ( " + 
				"select count(distinct c.id_servicio) c1,c.id_afiliado FROM CITA c WHERE c.ID_SERVICIO IN( ? ) and c.cumplida = 1 and to_date(c.fecha,'dd/mm/yyyy hh24:mi:ss') between to_date( ? ,'dd/mm/yyyy hh24:mi:ss') and to_date( ? ,'dd/mm/yyyy hh24:mi:ss') group by c.id_afiliado order by c.id_afiliado desc " + 
				") c,( " + 
				"SELECT sum(count(distinct id_servicio)) c2 FROM CITA WHERE ID_SERVICIO IN( ? ) group by id_servicio " + 
				") cs where a.IDENTIFICACION=u.NUMERO_DOCUMENTO AND c.id_afiliado=a.IDENTIFICACION and c.c1=cs.c2 " + 
				") s1 where s1.identificacion in(select id_afiliado from ( " + 
				"SELECT * FROM USUARIO u, AFILIADO a, ( " + 
				"select count(distinct s.tipo) c1,id_afiliado FROM CITA c, SERVICIO s WHERE c.ID_SERVICIO=s.nombre and s.tipo IN( ? ) and c.cumplida=1 group by id_afiliado order by count(distinct s.tipo) desc " + 
				") c,( " + 
				"SELECT sum(count(distinct s.tipo)) c2 FROM CITA c,SERVICIO s WHERE c.ID_SERVICIO=s.nombre and s.tipo IN( ? ) group by s.tipo " + 
				") cs where a.IDENTIFICACION=u.NUMERO_DOCUMENTO AND c.id_afiliado=a.IDENTIFICACION and c.c1=cs.c2 " + 
				")) and s1.identificacion in(select id_afiliado from ( select distinct c.id_afiliado from CITA c, PRESTAN p where c.fecha=p.dia And c.id_servicio=p.id_servicio and p.id_ips in(select ID_IPS from PRESTAN) " + 
				"))";
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(pServicios  , pFechaInicial , PFechaFinal , pServicios , pTipos , pTipos  );
		return q.executeList();
	}
	
	public List<Object[]> reqC10 (PersistenceManager pm , String pServicios, String pTipos , String pFechaInicial , String  PFechaFinal , String pIPS , String   pOrdenamiento , String pAgrupamiento){
		String sql="select s1.identificacion,u.nombre,u.email,u.tipo_documento,s1.fecha_nacimiento from AFILIADO s1, USUARIO u  " + 
				"where s1.identificacion=u.numero_documento  " + 
				"and s1.identificacion not in(select identificacion from ( " + 
				"SELECT * FROM USUARIO u, AFILIADO a, ( " + 
				"select count(distinct c.id_servicio) c1,c.id_afiliado FROM CITA c WHERE c.ID_SERVICIO  IN(? ) and c.cumplida = 1 and to_date(c.fecha,'dd/mm/yyyy hh24:mi:ss')  between to_date( ? ,'dd/mm/yyyy hh24:mi:ss') and to_date( ? ,'dd/mm/yyyy hh24:mi:ss') group by c.id_afiliado order by c.id_afiliado desc " + 
				") c,( " + 
				"SELECT sum(count(distinct id_servicio)) c2 FROM CITA WHERE ID_SERVICIO  IN( ? ) group by id_servicio " + 
				") cs where a.IDENTIFICACION=u.NUMERO_DOCUMENTO AND c.id_afiliado=a.IDENTIFICACION and c.c1=cs.c2 " + 
				"))  " + 
				"and s1.identificacion not in(select id_afiliado from ( " + 
				"SELECT * FROM USUARIO u, AFILIADO a, ( " + 
				"select count(distinct s.tipo) c1,id_afiliado FROM CITA c, SERVICIO s WHERE c.ID_SERVICIO=s.nombre and s.tipo  IN( ? ) and c.cumplida=1 group by id_afiliado order by count(distinct s.tipo) desc " + 
				") c,( " + 
				"SELECT sum(count(distinct s.tipo)) c2 FROM CITA c,SERVICIO s WHERE c.ID_SERVICIO=s.nombre and s.tipo  IN(?) group by s.tipo " + 
				") cs where a.IDENTIFICACION=u.NUMERO_DOCUMENTO AND c.id_afiliado=a.IDENTIFICACION and c.c1=cs.c2 " + 
				")) " + 
				"and s1.identificacion not in(select id_afiliado from ( select distinct c.id_afiliado from CITA c, PRESTAN p where c.fecha=p.dia And c.id_servicio=p.id_servicio and p.id_ips  in(select ID_IPS from PRESTAN) " + 
				"))";
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(pServicios  , pFechaInicial , PFechaFinal , pServicios , pTipos , pTipos  );
		return q.executeList();
	}
	
	public List<Object[]> reqC12 (PersistenceManager pm){
		String sql= "select usuario.nombre, usuario.email, afiliado.identificacion, case when siempre_espec = 1 then  'Siempre especializado' " + 
				"when count_citas is not null then 'Pide cita todos los meses' when siempreHosp=1 then 'Siempre hospitalizado' " + 
				"end razon  " + 
				", case when count_citas is not null then count_citas else 0 end CitasSolicitadas ,case when cuenta is not null then cuenta else 0 end ServiciosDistintosSolicitados, case when hospitalizaciones is not null then hospitalizaciones else 0 end hospitalizaciones " + 
				"from usuario left outer join afiliado on usuario.numero_documento=afiliado.identificacion " + 
				"left outer join ( " + 
				"select cit_mes.id_afiliado, first_cita, count_citas, meses_primera_cita " + 
				" from (  -- Cuenta cu�ntos meses han transcurrido desde su primera cita " + 
				"SELECT id_afiliado, MONTHS_BETWEEN  " + 
				"   (TO_DATE('12-2019','MM-YYYY'), " + 
				"   TO_DATE(to_char(first_cita,'mm/yyyy') ,'MM-YYYY') " + 
				"     ) meses_primera_cita, first_cita " + 
				"    FROM ( -- Cuenta el n�mero de meses distintos en los que ha hecho una cita " + 
				" select id_afiliado, min(TO_DATE(fecha,'dd/mm/yyyy hh24:mi:ss')) as first_cita " + 
				" from cita " + 
				" group by id_afiliado " + 
				" ) min_fe  ) month_fd " + 
				"    inner join ( -- Cuenta el n�mero de meses distintos en los que ha hecho una cita " + 
				" select id_afiliado, count(distinct to_char(TO_DATE(fecha,'dd/mm/yyyy hh24:mi:ss'),'mm/yy')) as count_citas " + 
				" from cita " + 
				" group by id_afiliado " + 
				" order by count_citas desc) cit_mes  " + 
				"    on cit_mes.id_afiliado = month_fd.id_afiliado " + 
				"    where count_citas = meses_primera_cita) final_freq " + 
				"on final_freq.id_afiliado = afiliado.identificacion " + 
				"left outer join  ( " + 
				"select id_afiliado, 1 siempre_espec,count(distinct sds.TIPO)cuenta " + 
				"from cita " + 
				"inner join SERVICIO sds on CITA.ID_SERVICIO = SDS.NOMBRE " + 
				"and sds.TIPO = 'PROCEDIMIENTO ESPECIALIZADO' " + 
				"where cita.id_afiliado not in (select id_afiliado from  (select id_afiliado " + 
				"from cita " + 
				"inner join SERVICIO sds on CITA.ID_SERVICIO = SDS.NOMBRE " + 
				"and sds.TIPO != 'PROCEDIMIENTO ESPECIALIZADO') oth_no_esp) " + 
				"group by id_afiliado " + 
				") espec  " + 
				"on espec.id_afiliado = afiliado.identificacion left outer join (SELECT c.id_afiliado, 1 siempreHosp, c.citasUsuario as citas, h.cuentaHospitalizaciones as hospitalizaciones FROM (SELECT COUNT(*) as citasUsuario, id_afiliado FROM CITA " + 
				"GROUP BY id_afiliado order by count() desc)  c,(SELECT COUNT() as cuentaHospitalizaciones, r.id_afiliado FROM  " + 
				"ORDEN r, SERVICIO s WHERE r.NOM_SERVICIO=s.NOMBRE AND s.TIPO='HOSPITALIZACION' " + 
				"GROUP BY r.id_afiliado) h WHERE c.id_afiliado=h.id_afiliado AND c.citasUSUARIO/2=h.cuentaHospitalizaciones) c2 on c2.id_afiliado=espec.id_afiliado " + 
				"where siempre_espec = 1 or count_citas is not null or siempreHosp=1";
		Query q = pm.newQuery(SQL,sql);
		return q.executeList();
	}
	
	public List<Object[]> reqC11 (PersistenceManager pm){
		String sql="select * " + 
				"from ( " + 
				"    SELECT tt1.SEMANA as SEMANA, listagg(tt1.tipo,', ')within group(order by tt1.tipo) as TiposMasUsados, t2.c1 as CantidadTipoMasUsado " + 
				"    FROM (SELECT (COUNT(s.TIPO)) ca, " + 
				"                  s.TIPO as tipo, " + 
				"                 to_char(to_date(c.FECHA, 'dd/mm/yyyy hh24:mi:ss'), 'WW/YYYY') AS SEMANA " + 
				"          FROM CITA c, " + 
				"               SERVICIO s " + 
				"          WHERE c.ID_SERVICIO = s.NOMBRE " + 
				"            AND c.CUMPLIDA = 1 " + 
				"          GROUP BY to_char(to_date(c.FECHA, 'dd/mm/yyyy hh24:mi:ss'), 'WW/YYYY'), s.TIPO " + 
				"          order by semana desc)tt1, " + 
				"         (SELECT MAX(ca) C1, SEMANA " + 
				"          FROM (SELECT   ca, TIPO,SEMANA FROM (SELECT (COUNT(s.TIPO)) ca, " + 
				"                  s.TIPO as tipo, " + 
				"                 to_char(to_date(c.FECHA, 'dd/mm/yyyy hh24:mi:ss'), 'WW/YYYY') AS SEMANA " + 
				"          FROM CITA c, " + 
				"               SERVICIO s " + 
				"          WHERE c.ID_SERVICIO = s.NOMBRE " + 
				"            AND c.CUMPLIDA = 1 " + 
				"          GROUP BY to_char(to_date(c.FECHA, 'dd/mm/yyyy hh24:mi:ss'), 'WW/YYYY'), s.TIPO " + 
				"          order by semana desc)tt1) " + 
				"          GROUP BY SEMANA) t2 " + 
				"    WHERE tt1.SEMANA = t2.SEMANA " + 
				"      AND tt1.ca = t2.c1 group by tt1.SEMANA, t2.c1 " + 
				") t1 natural inner join ( " + 
				"    SELECT tt1.SEMANA as SEMANA, listagg(tt1.tipo,', ')within group(order by tt1.tipo) as TiposMenosUsados, t2.c1 as CantidadTipoMenosUsado " + 
				"    FROM (SELECT (COUNT(s.TIPO)) ca, " + 
				"                  s.TIPO as tipo, " + 
				"                 to_char(to_date(c.FECHA, 'dd/mm/yyyy hh24:mi:ss'), 'WW/YYYY') AS SEMANA " + 
				"          FROM CITA c, " + 
				"               SERVICIO s " + 
				"          WHERE c.ID_SERVICIO = s.NOMBRE " + 
				"            AND c.CUMPLIDA = 1 " + 
				"          GROUP BY to_char(to_date(c.FECHA, 'dd/mm/yyyy hh24:mi:ss'), 'WW/YYYY'), s.TIPO " + 
				"          order by semana desc) tt1, " + 
				"         (SELECT MIN(ca) C1, SEMANA " + 
				"          FROM (SELECT   ca, TIPO,SEMANA FROM (SELECT (COUNT(s.TIPO)) ca, " + 
				"                  s.TIPO as tipo, " + 
				"                 to_char(to_date(c.FECHA, 'dd/mm/yyyy hh24:mi:ss'), 'WW/YYYY') AS SEMANA " + 
				"          FROM CITA c, " + 
				"               SERVICIO s " + 
				"          WHERE c.ID_SERVICIO = s.NOMBRE " + 
				"            AND c.CUMPLIDA = 1 " + 
				"          GROUP BY to_char(to_date(c.FECHA, 'dd/mm/yyyy hh24:mi:ss'), 'WW/YYYY'), s.TIPO " + 
				"          order by semana desc) tt1) " + 
				"          GROUP BY SEMANA) t2 " + 
				"    WHERE tt1.SEMANA = t2.SEMANA " + 
				"      AND tt1.ca = t2.c1  " + 
				"      group by tt1.SEMANA, t2.c1 " + 
				") t2 natural inner join ( " + 
				"    SELECT t1.SEMANA as SEMANA, listagg(t1.NOMBRE,', ')within group(order by t1.NOMBRE) as ServiciosMasUsados, t2.c1 as CantidadServicioMasUsado " + 
				"    FROM (SELECT (COUNT(s.TIPO))                         ca, " + 
				"                  s.NOMBRE as NOMBRE, " + 
				"                 to_char(to_date(c.FECHA, 'dd/mm/yyyy hh24:mi:ss'), 'WW/YYYY') AS SEMANA " + 
				"          FROM CITA c, " + 
				"               SERVICIO s " + 
				"          WHERE c.ID_SERVICIO = s.NOMBRE " + 
				"            AND c.CUMPLIDA = 1 " + 
				"          GROUP BY to_char(to_date(c.FECHA, 'dd/mm/yyyy hh24:mi:ss'), 'WW/YYYY'), s.NOMBRE " + 
				"          ) t1, " + 
				"         (SELECT MAX(ca) C1, SEMANA " + 
				"          FROM (SELECT * FROM (SELECT (COUNT(s.TIPO))                         ca, " + 
				"                  s.NOMBRE as NOMBRE, " + 
				"                 to_char(to_date(c.FECHA, 'dd/mm/yyyy hh24:mi:ss'), 'WW/YYYY') AS SEMANA " + 
				"          FROM CITA c, " + 
				"               SERVICIO s " + 
				"          WHERE c.ID_SERVICIO = s.NOMBRE " + 
				"            AND c.CUMPLIDA = 1 " + 
				"          GROUP BY to_char(to_date(c.FECHA, 'dd/mm/yyyy hh24:mi:ss'), 'WW/YYYY'), s.NOMBRE " + 
				"          )t1) " + 
				"          GROUP BY SEMANA) t2 " + 
				"    WHERE t1.SEMANA = t2.SEMANA " + 
				"      AND t1.ca = t2.c1 group by t1.SEMANA, t2.c1 " + 
				") tb_mayorS natural inner join ( " + 
				"    SELECT t1.SEMANA as SEMANA, listagg(t1.NOMBRE,', ')within group(order by t1.NOMBRE) as ServiciosMenosUsados, t2.c1 as CantidadServicioMenosUsado " + 
				"    FROM (SELECT (COUNT(s.TIPO))                         ca, " + 
				"                  s.NOMBRE as NOMBRE, " + 
				"                 to_char(to_date(c.FECHA, 'dd/mm/yyyy hh24:mi:ss'), 'WW/YYYY') AS SEMANA " + 
				"          FROM CITA c, " + 
				"               SERVICIO s " + 
				"          WHERE c.ID_SERVICIO = s.NOMBRE " + 
				"            AND c.CUMPLIDA = 1 " + 
				"          GROUP BY to_char(to_date(c.FECHA, 'dd/mm/yyyy hh24:mi:ss'), 'WW/YYYY'), s.NOMBRE " + 
				"          ) t1, " + 
				"         (SELECT MIN(ca) C1, SEMANA " + 
				"          FROM (SELECT * " + 
				"                FROM (SELECT (COUNT(s.TIPO))                         ca, " + 
				"                  s.NOMBRE as NOMBRE, " + 
				"                 to_char(to_date(c.FECHA, 'dd/mm/yyyy hh24:mi:ss'), 'WW/YYYY') AS SEMANA " + 
				"          FROM CITA c, " + 
				"               SERVICIO s " + 
				"          WHERE c.ID_SERVICIO = s.NOMBRE " + 
				"            AND c.CUMPLIDA = 1 " + 
				"          GROUP BY to_char(to_date(c.FECHA, 'dd/mm/yyyy hh24:mi:ss'), 'WW/YYYY'), s.NOMBRE " + 
				"          )t1) " + 
				"          GROUP BY SEMANA) t2 " + 
				"    WHERE t1.SEMANA = t2.SEMANA " + 
				"      AND t1.ca = t2.c1  " + 
				"      group by t1.SEMANA, t2.c1 " + 
				") tb_menorS natural inner join ( SELECT ti1.SEMANA as SEMANA, listagg(ti1.ID_IPS,', ')within group(order by ti1.ID_IPS) as IPSMasSolicitadas, t2.c1 as CantidadIPSMasUsado " + 
				"    FROM (SELECT (COUNT(s.ID_IPS))                         ca, " + 
				"                  s.ID_IPS as ID_IPS, " + 
				"                 to_char(to_date(c.FECHA, 'dd/mm/yyyy hh24:mi:ss'), 'WW/YYYY') AS SEMANA " + 
				"          FROM CITA c, " + 
				"               Prestan s " + 
				"          WHERE c.ID_SERVICIO = s.ID_SERVICIO " + 
				"            AND c.CUMPLIDA = 1 and c.fecha=s.dia " + 
				"          GROUP BY to_char(to_date(c.FECHA, 'dd/mm/yyyy hh24:mi:ss'), 'WW/YYYY'), s.ID_IPS " + 
				"          ) ti1, " + 
				"         (SELECT MAX(ca) C1, SEMANA " + 
				"          FROM ( SELECT * " + 
				"                FROM (SELECT (COUNT(s.ID_IPS))                         ca, " + 
				"                  s.ID_IPS as ID_IPS, " + 
				"                 to_char(to_date(c.FECHA, 'dd/mm/yyyy hh24:mi:ss'), 'WW/YYYY') AS SEMANA " + 
				"          FROM CITA c, " + 
				"               Prestan s " + 
				"          WHERE c.ID_SERVICIO = s.ID_SERVICIO " + 
				"            AND c.CUMPLIDA = 1 and c.fecha=s.dia " + 
				"          GROUP BY to_char(to_date(c.FECHA, 'dd/mm/yyyy hh24:mi:ss'), 'WW/YYYY'), s.ID_IPS " + 
				"          ) ti1) " + 
				"          GROUP BY SEMANA) t2 " + 
				"    WHERE ti1.SEMANA = t2.SEMANA " + 
				"      AND ti1.ca = t2.c1 group by ti1.SEMANA, t2.c1 " + 
				") tb_mayorI natural inner join ( SELECT ti1.SEMANA as SEMANA, listagg(ti1.ID_IPS,', ')within group(order by ti1.ID_IPS) as IPSMenosSolicitadas, t2.c1 as CantidadIPSMenosUsado " + 
				"    FROM  (SELECT (COUNT(s.ID_IPS))                         ca, " + 
				"                  s.ID_IPS as ID_IPS, " + 
				"                 to_char(to_date(c.FECHA, 'dd/mm/yyyy hh24:mi:ss'), 'WW/YYYY') AS SEMANA " + 
				"          FROM CITA c, " + 
				"               Prestan s " + 
				"          WHERE c.ID_SERVICIO = s.ID_SERVICIO " + 
				"            AND c.CUMPLIDA = 1 and c.fecha=s.dia " + 
				"          GROUP BY to_char(to_date(c.FECHA, 'dd/mm/yyyy hh24:mi:ss'), 'WW/YYYY'), s.ID_IPS " + 
				"          ) ti1, " + 
				"         (SELECT MIN(ca) C1, SEMANA " + 
				"          FROM (SELECT * " + 
				"                FROM (SELECT (COUNT(s.ID_IPS))                         ca, " + 
				"                  s.ID_IPS as ID_IPS, " + 
				"                 to_char(to_date(c.FECHA, 'dd/mm/yyyy hh24:mi:ss'), 'WW/YYYY') AS SEMANA " + 
				"          FROM CITA c, " + 
				"               Prestan s " + 
				"          WHERE c.ID_SERVICIO = s.ID_SERVICIO " + 
				"            AND c.CUMPLIDA = 1 and c.fecha=s.dia " + 
				"          GROUP BY to_char(to_date(c.FECHA, 'dd/mm/yyyy hh24:mi:ss'), 'WW/YYYY'), s.ID_IPS " + 
				"          ) ti1) " + 
				"          GROUP BY SEMANA) t2 " + 
				"    WHERE ti1.SEMANA = t2.SEMANA " + 
				"      AND ti1.ca = t2.c1 group by ti1.SEMANA, t2.c1 " + 
				") tb_menorI natural inner join (SELECT tu1.SEMANA as SEMANA, listagg(tu1.id_afiliado,', ')within group(order by tu1.id_afiliado) as AfiliadoMasSolicitador, t2.c1 as AfiliadoMasUsador " + 
				"    FROM (SELECT (COUNT(c.id_afiliado))                         ca, " + 
				"                  c.id_afiliado as id_afiliado, " + 
				"                 to_char(to_date(c.FECHA, 'dd/mm/yyyy hh24:mi:ss'), 'WW/YYYY') AS SEMANA " + 
				"          FROM CITA c " + 
				"            WHERE c.CUMPLIDA = 1  " + 
				"          GROUP BY to_char(to_date(c.FECHA, 'dd/mm/yyyy hh24:mi:ss'), 'WW/YYYY'), c.id_afiliado " + 
				"          ) tu1, " + 
				"         (SELECT MAX(ca) C1, SEMANA " + 
				"          FROM (SELECT * FROM (SELECT (COUNT(c.id_afiliado))                         ca, " + 
				"                  c.id_afiliado as id_afiliado, " + 
				"                 to_char(to_date(c.FECHA, 'dd/mm/yyyy hh24:mi:ss'), 'WW/YYYY') AS SEMANA " + 
				"          FROM CITA c " + 
				"            WHERE c.CUMPLIDA = 1  " + 
				"          GROUP BY to_char(to_date(c.FECHA, 'dd/mm/yyyy hh24:mi:ss'), 'WW/YYYY'), c.id_afiliado " + 
				"          ) tu1) " + 
				"          GROUP BY SEMANA) t2 " + 
				"    WHERE tu1.SEMANA = t2.SEMANA " + 
				"      AND tu1.ca = t2.c1 group by tu1.SEMANA, t2.c1 " + 
				"    order by SEMANA DESC) tb_mayorU NATURAL inner join (select cuenta-AfiliadosNoCitas,semana as SEMANA from ( " + 
				"    SELECT COUNT(DISTINCT c.id_afiliado) AfiliadosNoCitas, to_char(to_date(c.FECHA, 'dd/mm/yyyy hh24:mi:ss'), 'WW/YYYY') AS SEMANA " + 
				"          FROM CITA c " + 
				"         WHERE c.CUMPLIDA = 1  " + 
				"          GROUP BY to_char(to_date(c.FECHA, 'dd/mm/yyyy hh24:mi:ss'), 'WW/YYYY') " + 
				"          ) tb_cantiCita, (select count(*) as cuenta from AFILIADO) tb_cantidadAf) tb_total";
		Query q = pm.newQuery(SQL,sql);
	
		return q.executeList();
	}
	
	
	public List<Object[]> darExigentes (PersistenceManager pm){
		String sql="SELECT aux.AFILIADO,aux.TIPOS,aux.SERVICIOS" + 
				"	FROM(SELECT tc.ID_AFILIADO AFILIADO, COUNT (DISTINCT ts.TIPO) TIPOS, COUNT (tc.ID_SERVICIO) SERVICIOS" + 
				"	FROM "+pp.darTablaCita()+" tc, "+pp.darTablaServicio()+" ts " + 
				"	WHERE ts.NOMBRE=tc.ID_SERVICIO AND to_number(to_char(TO_DATE(tc.FECHA,'DD/MM/YYYY HH24:MI:SS'), 'YY'))=to_number(to_char(CURRENT_DATE, 'YY'))-1" + 
				"	GROUP BY tc.ID_AFILIADO,ts.TIPO, tc.ID_SERVICIO)aux" + 
				"	WHERE aux.TIPOS>2 AND aux.SERVICIOS>12";
		Query q = pm.newQuery(SQL,sql);
		return q.executeList();
	}

}
