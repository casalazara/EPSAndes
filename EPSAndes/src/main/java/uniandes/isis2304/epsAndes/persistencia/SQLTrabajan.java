/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Parranderos Uniandes
 * @version 1.0
 * @author Germán Bravo
 * Julio de 2018
 * 
 * Revisado por: Claudia Jiménez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.epsAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.epsAndes.negocio.Trabajan;

/**
 * Clase SQLTrabajan.
 */
class SQLTrabajan 
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
	public SQLTrabajan (PersistenciaEPSAndes pp)
	{
		this.pp = pp;
	}

	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un Trabajan a la base de datos de EPSAndes.
	 *
	 * @param pm - El manejador de persistencia
	 * @param id_Medico - El identificador del medico
	 * @param id_IPS - El identificador de la ips
	 * @return EL número de tuplas insertadas
	 */
	public long adicionarTrabajan (PersistenceManager pm, String id_Medico, String id_IPS) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaTrabajan() + "(ID_MEDICO, ID_IPS) values (?, ?)");
		q.setParameters(id_Medico, id_IPS);
		return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN TRABAJAN de la base de datos de EPSAndes, por sus identificador.
	 *
	 * @param pm - El manejador de persistencia
	 * @param idMedico el id medico
	 * @param idIPS el id IPS
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarTrabajan (PersistenceManager pm, String idMedico,String idIPS) 
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaTrabajan() + " WHERE ID_MEDICO = ? AND ID_IPS= ?");
		q.setParameters(idMedico,idIPS);
		return (long) q.executeUnique();            
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de los Trabajan de la 
	 * base de datos de EPSAndes.
	 *
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos Trabajan
	 */
	public List<Trabajan> darTrabajan (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaTrabajan() );
		q.setResultClass(Trabajan.class);
		return (List<Trabajan>) q.execute();
	}

}
