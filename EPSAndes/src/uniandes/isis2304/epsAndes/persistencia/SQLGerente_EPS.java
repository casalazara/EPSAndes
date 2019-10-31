package uniandes.isis2304.epsAndes.persistencia;


import javax.jdo.PersistenceManager;
import javax.jdo.Query;

/**
 * Clase SQLGerente_EPS.
 */
class SQLGerente_EPS
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
	public SQLGerente_EPS (PersistenciaEPSAndes pp)
	{
		this.pp = pp;
	}

	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un GERENTE a la base de datos de EPSAndes.
	 *
	 * @param pm - El manejador de persistencia
	 * @param id_EPS el id de la EPS
	 * @param identificacion la identificacion
	 * @return EL número de tuplas insertadas
	 */
	public long adicionarGerente (PersistenceManager pm, String id_EPS, String identificacion) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaGerente_Eps() + "(IDENTIFICACION, ID_EPS) values (?, ?)");
		q.setParameters(identificacion,id_EPS);
		return (long) q.executeUnique();
	}
}
