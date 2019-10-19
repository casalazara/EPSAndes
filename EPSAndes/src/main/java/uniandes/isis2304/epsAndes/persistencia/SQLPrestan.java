package uniandes.isis2304.epsAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.epsAndes.negocio.Prestan;

/**
 * The Class SQLPrestan.
 */
class SQLPrestan 
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
	public SQLPrestan (PersistenciaEPSAndes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un PRESTAN a la base de datos de EPSAndes.
	 *
	 * @param pm - El manejador de persistencia
	 * @param duracion la duracion
	 * @param horaInicio la hora inicial
	 * @param dia el dia
	 * @param idServicio el id del servicio
	 * @param idIps el id de la ips
	 * @param capacidad la capacidad
	 * @return El número de tuplas insertadas
	 */
	public long adicionarPrestan (PersistenceManager pm, String duracion,String horaInicio,String dia,String idServicio, String idIps, int capacidad) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPrestan() + "(DURACION, HORAINICIO, DIA, ID_SERVICIO, ID_IPS, CAPACIDAD) values (?,?,?,?,?,?)");
        q.setParameters(duracion,horaInicio, dia, idServicio,  idIps, capacidad);
        return (long)q.executeUnique();            
	}

	/**
	 * Actualiza la capacidad.
	 *
	 * @param pm el pm
	 * @param fecha la fecha
	 * @param hora la hora
	 * @param idServicio el id del servicio
	 * @param idIps el id de la ips
	 * @return El número de tuplas insertadas
	 */
	public long actualizarCapacidad(PersistenceManager pm,String fecha, String hora, String idServicio, String idIps)
	{
		int capacidad=darCapacidad(pm, fecha,  hora,  idServicio,  idIps);
		Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaPrestan() + "SET CAPACIDAD=? WHERE DIA=?, ID_SERVICIO=?, ID_IPS=?, HORAINICIO=?");
        q.setParameters(--capacidad,fecha,idServicio,idIps,hora);
        return (long) q.executeUnique();   
	}
	
	/**
	 * Dar capacidad.
	 *
	 * @param pm el manejador de persistencia
	 * @param fecha la fecha
	 * @param hora la hora
	 * @param idServicio el id servicio
	 * @param idIps la id ips
	 * @return la capacidad
	 */
	public int darCapacidad(PersistenceManager pm,String fecha, String hora, String idServicio, String idIps)
	{
		Query q = pm.newQuery(SQL, "SELECT CAPACIDAD FROM" + pp.darTablaPrestan() + "WHERE DIA=?, ID_SERVICIO=?, ID_IPS=?, HORAINICIO=?");
        q.setParameters(fecha,idServicio,idIps,hora);
        return (int) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para eliminar un SIRVEN de la base de datos de Parranderos, por sus identificador.
	 *
	 * @param pm - El manejador de persistencia
	 * @param duracion la duracion
	 * @param horaInicio la hora inicio
	 * @param dia el dia
	 * @param idServicio el id servicio
	 * @param idIps la id ips
	 * @return el número de tuplas eliminadas
	 */
	public long eliminarPrestan(PersistenceManager pm, String duracion,String horaInicio,String dia,String idServicio, String idIps) 
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPrestan() + " WHERE DURACION=? AND HORAINICIO=? AND DIA=? AND ID_SERVICIO = ? AND ID_IPS= ?");
        q.setParameters(duracion,horaInicio, dia, idServicio,  idIps);
        return (long) q.executeUnique();            
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de los SIRVEN de la 
	 * base de datos de EPSAndes.
	 *
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos PRESTAN
	 */
	public List<Prestan> darPrestan (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPrestan());
		q.setResultClass(Prestan.class);
		return (List<Prestan>) q.execute();
	}
 
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de los SIRVEN de la 
	 * base de datos de EPSAndes.
	 *
	 * @param pm - El manejador de persistencia
	 * @param idServicio el id servicio
	 * @return Una lista de objetos PRESTAN
	 */
	public List<Prestan> darPrestanDisponibles (PersistenceManager pm, String idServicio)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPrestan()+"WHERE TO_DATE(DIA,'DD-MM-YY HH24:MI:SS')>CURRENT_DATE AND ID_SERVICIO=? AND CAPACIDAD>0");
        q.setParameters(idServicio);
		q.setResultClass(Prestan.class);
		return (List<Prestan>) q.execute();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar el identificador y el número de servicios que ofrecen las IPS de la base de datos de 
	 * EPSAndes en un rango de fechas.
	 *
	 * @param pm - El manejador de persistencia
	 * @param fechaInicio la fecha inicio
	 * @param fechaFin la fecha fin
	 * @return Una lista de parejas de objetos, el primer elemento de cada pareja representa el identificador de una IPS,
	 * 	el segundo elemento representa el número de servicios que ofrece
	 */
	public List<Object []> darIPSYCantidadServiciosOfrecen (PersistenceManager pm, String fechaInicio, String fechaFin)
	{
        String sql = "SELECT ID_IPS, count (*) as numServicios";
        sql += " FROM " + pp.darTablaPrestan()+" tp,";
        sql += pp.darTablaRecepcionista_Ips()+" tr,";
        sql += pp.darTablaCita()+" tc";
        sql += "WHERE tc.ID_RECEPCIONISTA IS NOT NULL AND tc.ID_SERVICIO=tp.ID_SERVICIO AND tr.ID_IPS=tp.ID_IPS AND tc.ID_RECEPCIONISTA=tr.IDENTIFICACION";
        sql += "AND TO_DATE(tp.DIA,'DD-MM-YY HH24:MI:SS') BETWEEN TO_DATE(?,'DD-MM-YY HH24:MI:SS') AND TO_DATE(?,'DD-MM-YY HH24:MI:SS')";
       	sql	+= " GROUP BY ID_IPS";
		Query q = pm.newQuery(SQL, sql);
       	q.setParameters(fechaInicio,fechaFin);
		return q.executeList();
	}
}
