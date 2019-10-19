package uniandes.isis2304.epsAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.epsAndes.negocio.RecepcionistaIPS;

/**
 * Clase SQLRecepcionista.
 */
class SQLRecepcionista 
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
	public SQLRecepcionista (PersistenciaEPSAndes pp)
	{
		this.pp = pp;
	}

	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un RECEPCIONISTA a la base de datos de EPSAndes.
	 *
	 * @param pm - El manejador de persistencia
	 * @param id_IPS el id de la IPS
	 * @param identificacion la identificacion
	 * @return EL número de tuplas insertadas
	 */
	public long adicionarRecepcionista (PersistenceManager pm, String id_IPS, String identificacion) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaRecepcionista_Ips() + "(ID_IPS, IDENTIFICACION) values (?, ?)");
		q.setParameters(id_IPS,identificacion);
		return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN RECEPCIONISTA de la 
	 * base de datos de EPSAndes, por su identificador.
	 *
	 * @param pm - El manejador de persistencia
	 * @param identificacion - El identificador del recepcionista
	 * @return El objeto RECEPCIONISTA que tiene el identificador dado
	 */
	public RecepcionistaIPS darRecepcionistaPorId (PersistenceManager pm, String identificacion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaRecepcionista_Ips() + " WHERE IDENTIFICACION = ?");
		q.setResultClass(RecepcionistaIPS.class);
		q.setParameters(identificacion);
		return (RecepcionistaIPS) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS RECEPCIONISTAS de la 
	 * base de datos de Parranderos, por su IPS.
	 *
	 * @param pm - El manejador de persistencia
	 * @param nombreIPS el nombre IPS
	 * @return Una lista de objetos RECEPCIONISTAIPS que pertenecen a la ips dada
	 */
	public List<RecepcionistaIPS> darRecepcionistasPorIPS (PersistenceManager pm, String nombreIPS) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaRecepcionista_Ips () + " WHERE ID_IPS = ?");
		q.setResultClass(RecepcionistaIPS.class);
		q.setParameters(nombreIPS);
		return (List<RecepcionistaIPS>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS RECEPCIONISTAS de la 
	 * base de datos de EPSAndes.
	 *
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos RECEPCIONISTAIPS
	 */
	public List<RecepcionistaIPS> darRecepcionistas (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaRecepcionista_Ips ());
		q.setResultClass(RecepcionistaIPS.class);
		return (List<RecepcionistaIPS>) q.executeList();
	}
}
