package uniandes.isis2304.epsAndes.persistencia;


import javax.jdo.PersistenceManager;
import javax.jdo.Query;

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

}
