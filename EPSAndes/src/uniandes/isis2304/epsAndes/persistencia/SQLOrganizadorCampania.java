package uniandes.isis2304.epsAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

/**
 * The Class SQLOrganizadorCampania.
 */
public class SQLOrganizadorCampania {
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
	public SQLOrganizadorCampania (PersistenciaEPSAndes pp)
	{
		this.pp = pp;
	}

	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un GERENTE a la base de datos de EPSAndes.
	 *
	 * @param pm - El manejador de persistencia
	 * @param identificacion la identificacion
	 * @return EL número de tuplas insertadas
	 */
	public long adicionarOrganizador (PersistenceManager pm,String identificacion) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaOrganizadorCampania() + " (IDENTIFICACION) values (?)");
		q.setParameters(identificacion);
		return (long) q.executeUnique();
	}
}
