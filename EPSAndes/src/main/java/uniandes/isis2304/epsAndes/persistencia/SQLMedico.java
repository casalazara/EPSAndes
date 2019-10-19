package uniandes.isis2304.epsAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.epsAndes.negocio.Medico;


/**
 * Clase SQLMedico.
 */
class SQLMedico 
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
	public SQLMedico (PersistenciaEPSAndes pp)
	{
		this.pp = pp;
	}

	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un MEDICO a la base de datos de EPSAndes.
	 *
	 * @param pm - El manejador de persistencia
	 * @param especialidad - La especialidad
	 * @param identificacion el identificacion
	 * @param registroMedico el registro medico
	 * @return EL número de tuplas insertadas
	 */
	public long adicionarMedico (PersistenceManager pm, String especialidad, String identificacion, String registroMedico) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaMedico()+ "(ESPECIALIDAD, IDENTIFICACION, NUMERO_REGISTRO_MEDICO) values (?, ?, ?)");
		q.setParameters(especialidad, identificacion, registroMedico);
		return (long) q.executeUnique();            
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN MEDICO de la 
	 * base de datos de EPSAndes, por su identificador.
	 *
	 * @param pm - El manejador de persistencia
	 * @param identificacion la identificacion
	 * @return El objeto MEDICO que tiene el identificador dado
	 */
	public Medico darMedicoPorId (PersistenceManager pm, String identificacion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaMedico () + " WHERE IDENTIFICACION = ?");
		q.setResultClass(Medico.class);
		q.setParameters(identificacion);
		return (Medico) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de MEDICO de la 
	 * base de datos de EPSAndes, por su especialidad.
	 *
	 * @param pm - El manejador de persistencia
	 * @param especialidad - El nombre de la especialidad
	 * @return Una lista de objetos MEDICO que tienen la especialidad dada
	 */
	public List<Medico> darMedicosPorEspecialidad (PersistenceManager pm, String especialidad) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaMedico () + " WHERE ESPECIALIDAD = ?");
		q.setResultClass(Medico.class);
		q.setParameters(especialidad);
		return (List<Medico>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS MEDICOS de la 
	 * base de datos de EPSAndes.
	 *
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos MEDICO
	 */
	public List<Medico> darMedicos (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaMedico());
		q.setResultClass(Medico.class);
		return (List<Medico>) q.executeList();
	}

}
