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
	 * @return the long
	 */
	public long registrarPrestacion(PersistenceManager pm, long idCita, String id_Recepcionista)
	{
		Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaCita() + "SET CUMPLIDA=?, ID_RECEPCIONISTA=? WHERE ID=?");
		q.setParameters(1,id_Recepcionista,idCita);
		return (long) q.executeUnique();   
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
		sql += "WHERE TO_DATE(FECHA,'DD-MM-YY HH24:MI:SS') BETWEEN TO_DATE(?,'DD-MM-YY HH24:MI:SS') AND TO_DATE(?,'DD-MM-YY HH24:MI:SS')";
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
		String sql="SELECT aux2.cuenta/CONVERT(DECIMAL(9,2),aux1.cuenta) indice, aux2.servicio"
				+ "FROM "
				+ "( SELECT COUNT(*) cuenta "
				+ "FROM CITA c "
				+ "WHERE c.CUMPLIDA = 1"
				+ ") aux1,"
				+ "( SELECT COUNT(*) cuenta, o.NOM_SERVICIO servicio "
				+ "FROM ORDEN o INNER JOIN CITA c "
				+ "ON o.ID = c.ID_ORDEN "
				+ "AND c.realizada = 1 "
				+ ") aux2;";
		Query q = pm.newQuery(SQL, sql);
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
				+ "WHERE c.ID_ORDEN = o.ID AND o.ID_AFILIADO = "+idAfiliado+" "
				+ "AND TO_DATE(c.FECHA, 'DD-MM-YY HH24:MI:SS') " 
				+ "BETWEEN '" + fechaInic + "' AND '" + fechaFin + "' "
				+ "GROUP BY o.NOM_SERVICIO" ;
		Query q = pm.newQuery(SQL,sql);
		return q.executeList();
	}
}
