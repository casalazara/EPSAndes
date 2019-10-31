package uniandes.isis2304.epsAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.epsAndes.negocio.Orden;

/**
 * Clase SQLOrden.
 */
class SQLOrden
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
	public SQLOrden (PersistenciaEPSAndes pp)
	{
		this.pp = pp;
	}

	/**
	 * Crea y ejecuta la sentencia SQL para adicionar una ORDEN a la base de datos de EPSAndes.
	 *
	 * @param pm - El manejador de persistencia
	 * @param id the id
	 * @param id_Afiliado el id afiliado
	 * @param id_Medico el id medico
	 * @param id_Servicio the id servicio
	 * @return EL número de tuplas insertadas
	 */
	public long adicionarOrden (PersistenceManager pm, long id, String id_Afiliado, String id_Medico,String id_Servicio) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaOrden() + "(ID, ID_AFILIADO, ID_MEDICO,NOM_SERVICIO) values (?, ?, ?,?)");
		q.setParameters(id, id_Afiliado, id_Medico,id_Servicio);
		return (long) q.executeUnique();            
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UNA ORDEN de la 
	 * base de datos de EPSAndes, por su identificador.
	 *
	 * @param pm - El manejador de persistencia
	 * @param identificacion la identificacion
	 * @return El objeto ORDEN que tiene el identificador dado
	 */
	public Orden darOrdenPorId (PersistenceManager pm, long identificacion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOrden () + " WHERE ID = ?");
		q.setResultClass(Orden.class);
		q.setParameters(identificacion);
		return (Orden) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de ORDENES de la 
	 * base de datos de EPSAndes, por su afiliado.
	 *
	 * @param pm - El manejador de persistencia
	 * @param id_Afiliado - El nombre del afiliado
	 * @return Una lista de objetos ORDEN que tienen el nombre dado
	 */
	public List<Orden> darOrdenesPorAfiliado (PersistenceManager pm, String id_Afiliado) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOrden () + " WHERE ID_AFILIADO = ?");
		q.setResultClass(Orden.class);
		q.setParameters(id_Afiliado);
		return (List<Orden>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LAS ORDENES de la 
	 * base de datos de EPSAndes.
	 *
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos ORDEN
	 */
	public List<Orden> darOrdenes (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOrden());
		q.setResultClass(Orden.class);
		return (List<Orden>) q.executeList();
	}

}
