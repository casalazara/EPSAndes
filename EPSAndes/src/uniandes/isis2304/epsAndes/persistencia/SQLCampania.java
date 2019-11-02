package uniandes.isis2304.epsAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.epsAndes.negocio.Cita;


/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto CITA de EPSAndes.
 */
class SQLCampania 
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
	public SQLCampania (PersistenciaEPSAndes pp)
	{
		this.pp = pp;
	}

	/**
	 * Crea y ejecuta la sentencia SQL para adicionar una CITA a la base de datos de EPSAndes.
	 *
	 * @param pm - El manejador de persistencia
	 * @param cumplida el estado cumplida
	 * @param id el id
	 * @param fecha la fecha
	 * @param id_Servicio el id servicio
	 * @param id_Afiliado el id afiliado
	 * @param id_Recepcionista el id recepcionista
	 * @param hora la hora
	 * @return EL número de tuplas insertadas
	 */
	//	public long crearCampania(PersistenceManager pm, String nada) 
	//	{
	//		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaCita() + "(CUMPLIDA, ID_ORDEN , FECHA, ID_SERVICIO, ID_AFILIADO, ID_RECEPCIONISTA, HORA) values (?,?, ?, ?, ?, ?, ?)");
	//		q.setParameters(cumplida,id,fecha,id_Servicio,id_Afiliado,id_Recepcionista,hora);
	//		return (long) q.executeUnique();            
	//	}

	/**
	 * Registrar prestacion.
	 *
	 * @param pm the pm
	 * @param idCita el id cita
	 * @param id_Recepcionista el id recepcionista
	 * @return the long
	 */
	public long registrarCampania(PersistenceManager pm, String nombre, String fechaFin, String fechaInicio,String idOrganizador)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaCampania()+ " (NOMBRE,FECHAFIN,FECHAINICIO,ID_ORGANIZADOR) VALUES (?,?,?,?)");
		q.setParameters(nombre,fechaFin,fechaInicio,idOrganizador);
		return (long) q.executeUnique();   
	}
}
