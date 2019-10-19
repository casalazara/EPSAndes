package uniandes.isis2304.epsAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;


/**
 * La clase SQLEPS.
 */
class SQLEPS 
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
	public SQLEPS (PersistenciaEPSAndes pp)
	{
		this.pp = pp;
	}

	/**
	 * Crea y ejecuta la sentencia SQL para adicionar una EPS a la base de datos de EPSAndes.
	 *
	 * @param pm - El manejador de persistencia
	 * @param nombreEPS el nombre EPS
	 * @return EL número de tuplas insertadas
	 */
	public long adicionarEPS(PersistenceManager pm, String nombreEPS) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaEps() + "(NOMBRE) values (?)");
		q.setParameters(nombreEPS);
		return (long) q.executeUnique();
	}
}
