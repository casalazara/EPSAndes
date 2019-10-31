package uniandes.isis2304.epsAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.epsAndes.negocio.Usuario;

/**
 * Clase SQLUsuario.
 */
class SQLUsuario 
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
	public SQLUsuario (PersistenciaEPSAndes pp)
	{
		this.pp = pp;
	}

	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un USUARIO a la base de datos de EPSAndes.
	 *
	 * @param pm - El manejador de persistencia
	 * @param email el email
	 * @param nombre - El nombre del usuario
	 * @param numero_documento the numero documento
	 * @param rol the rol
	 * @param tipo_Documento el tipo documento
	 * @return El número de tuplas insertadas
	 */
	public long adicionarUsuario (PersistenceManager pm, String email, String nombre, String numero_documento,String rol, String tipo_Documento) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaUsuario() + "(EMAIL, NOMBRE, NUMERO_DOCUMENTO, ROL, TIPO_DOCUMENTO) values (?, ?, ?, ?, ?)");
		q.setParameters(email, nombre, numero_documento, rol, tipo_Documento);
		return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN USUARIO de la 
	 * base de datos de EPSAndes, por su identificador.
	 *
	 * @param pm - El manejador de persistencia
	 * @param numero_documento the numero documento
	 * @return El objeto USUARIO que tiene el identificador dado
	 */
	public Usuario darUsuarioPorId (PersistenceManager pm, String numero_documento) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaUsuario() + " WHERE NUMERO_DOCUMENTO = ?");
		q.setResultClass(Usuario.class);
		q.setParameters(numero_documento);
		return (Usuario) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS USUARIOS de la 
	 * base de datos de EPSAndes.
	 *
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos USUARIO
	 */
	public List<Usuario> darUsuarios (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaUsuario());
		q.setResultClass(Usuario.class);
		return (List<Usuario>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN USUARIO de la base de datos de EPSAndes, por sus identificador.
	 *
	 * @param pm - El manejador de persistencia
	 * @param identificacion la identificacion
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarUsuario(PersistenceManager pm, String identificacion) 
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaUsuario() + " WHERE NUMERO_DOCUMENTO=? CASCADE CONSTRAINTS");
		q.setParameters(identificacion);
		return (long) q.executeUnique();            
	}

}
