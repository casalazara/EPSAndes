package uniandes.isis2304.epsAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.epsAndes.negocio.Ofrecen;

/**
 * Clase SQLOfrecen.
 */
class SQLOfrecen 
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
	public SQLOfrecen (PersistenciaEPSAndes pp)
	{
		this.pp = pp;
	}

	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un OFRECEN a la base de datos de EPSAndes.
	 *
	 * @param pm - El manejador de persistencia
	 * @param idMedico el id medico
	 * @param idServicio el id servicio
	 * @return EL número de tuplas insertadas
	 */
	public long adicionarOfrecen (PersistenceManager pm, String idMedico,String idServicio) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaOfrecen() + "(ID_MEDICO,ID_SERVICIO) values (?,?)");
		q.setParameters(idMedico,idServicio);
		return (long)q.executeUnique();            
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN OFRECEN de la base de datos de EPSAndes, por sus identificador.
	 *
	 * @param pm - El manejador de persistencia
	 * @param idMedico el id medico
	 * @param idServicio el id servicio
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarOfrecen (PersistenceManager pm, String idMedico,String idServicio) 
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaOfrecen() + " WHERE ID_MEDICO = ? AND ID_SERVICIO= ?");
		q.setParameters(idMedico,idServicio);
		return (long) q.executeUnique();            
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de los OFRECEN de la 
	 * base de datos de EPSAndes.
	 *
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos OFRECEN
	 */
	public List<Ofrecen> darOfrecen (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOfrecen ());
		q.setResultClass(Ofrecen.class);
		return (List<Ofrecen>) q.execute();
	}
}
