package uniandes.isis2304.epsAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.epsAndes.negocio.Afiliado;

/**
 * Clase SQLAfiliado.
 */
class SQLAfiliado 
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
	public SQLAfiliado (PersistenciaEPSAndes pp)
	{
		this.pp = pp;
	}

	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un AFILIADO a la base de datos de EPSAndes.
	 *
	 * @param pm - El manejador de persistencia
	 * @param eps - La eps
	 * @param identificacion la identificacion
	 * @param fechaNacimiento la fecha nacimiento
	 * @return EL número de tuplas insertadas
	 */
	public long adicionarAfiliado (PersistenceManager pm, String eps, String identificacion, String fechaNacimiento) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaAfiliado () + "(EPS, IDENTIFICACION, FECHA_NACIMIENTO) values (?, ?, ?)");
		q.setParameters(eps, identificacion, fechaNacimiento);
		return (long) q.executeUnique();            
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN AFILIADO de la 
	 * base de datos de EPSAndes, por su identificador.
	 *
	 * @param pm - El manejador de persistencia
	 * @param identificacion la identificacion
	 * @return El objeto AFILIADO que tiene el identificador dado
	 */
	public Afiliado darAfiliadoPorId (PersistenceManager pm, String identificacion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaAfiliado () + " WHERE IDENTIFICACION = ?");
		q.setResultClass(Afiliado.class);
		q.setParameters(identificacion);
		return (Afiliado) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS AFILIADOS de la 
	 * base de datos de EPSAndes.
	 *
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos de tipo AFILIADO
	 */
	public List<Afiliado> darAfiliados (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaAfiliado());
		q.setResultClass(Afiliado.class);
		return (List<Afiliado>) q.executeList();
	}

}
