package uniandes.isis2304.epsAndes.persistencia;


import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.epsAndes.negocio.IPS;
import uniandes.isis2304.epsAndes.negocio.VOIPS;

/**
 * Clase SQLIPS.
 */
class SQLIPS 
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
	public SQLIPS (PersistenciaEPSAndes pp)
	{
		this.pp = pp;
	}

	public List<IPS> darIPS(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaIps());
		q.setResultClass(IPS.class);
		return (List<IPS>) q.executeList();
	}

	public long eliminarIPSNombre(PersistenceManager pm, String nombre)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaRecepcionista_Ips() + " WHERE ID_IPS=?");
		q.setParameters(nombre);
		q.executeUnique();
		Query k = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaIps() + " WHERE NOMBRE=?");
		k.setParameters(nombre);
		return (long) k.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para adicionar una IPS a la base de datos de EPSAndes.
	 *
	 * @param pm - El manejador de persistencia
	 * @param nombreEPS el nombre EPS
	 * @param localizacion la localizacion
	 * @param id_IPS el id IPS
	 * @return EL número de tuplas insertadas
	 */
	public long adicionarIPS(PersistenceManager pm, String nombreEPS,String localizacion, String id_IPS) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaIps() + "(NOMBRE,LOCALIZACION,ID_EPS) values (?, ?, ?)");
		q.setParameters(id_IPS,localizacion,nombreEPS);
		return (long) q.executeUnique();
	}
	public IPS darIPSNombre(PersistenceManager pm, String nombre) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaIps() + " WHERE NOMBRE=?");
		q.setResultClass(IPS.class);
		q.setParameters(nombre);
		return ((IPS) q.executeUnique());
	}
}
