package uniandes.isis2304.epsAndes.persistencia;

import java.math.BigDecimal;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.epsAndes.negocio.Campania;
import uniandes.isis2304.epsAndes.negocio.Cita;
import uniandes.isis2304.epsAndes.negocio.ReservaCampania;


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

	public long registrarServCamp(PersistenceManager pm, int capacidadF, String idServ, String idCamp,String fechaIni,String fechaFin)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaServicioCampania()+ " (CAPACIDADF,ID_SERVICIO,ID_CAMPANIA,CAPACIDADINI,FECHAINI,FECHAFIN) VALUES (?,?,?,?,?,?)");
		q.setParameters(capacidadF,idServ,idCamp,capacidadF,fechaIni,fechaFin );
		return (long) q.executeUnique();   
	}

	public long eliminarServicioCampania(PersistenceManager pm,String idServ, String idCamp)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaServicioCampania()+ " WHERE ID_SERVICIO=? AND ID_CAMPANIA=?");
		q.setParameters(idServ,idCamp);
		return (long) q.executeUnique();   
	}

	public BigDecimal darCapacidad(PersistenceManager pm,String idServ, String idCamp)
	{
		Query q = pm.newQuery(SQL, "SELECT CAPACIDADINI FROM " + pp.darTablaServicioCampania() + " WHERE ID_SERVICIO=? AND ID_CAMPANIA=?");
		q.setParameters(idServ,idCamp);
		return (BigDecimal) q.executeUnique();
	}

	public List<Object[]> darCampania(PersistenceManager pm,String idServ, String idCamp)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaServicioCampania() + " WHERE ID_SERVICIO=? AND ID_CAMPANIA=?");
		q.setParameters(idServ,idCamp);
		return q.executeList();
	}

	public List<String> darServiciosCampania(PersistenceManager pm, String nombre)
	{
		Query q = pm.newQuery(SQL, "SELECT ID_SERVICIO  FROM " + pp.darTablaServicioCampania() +" WHERE ID_CAMPANIA=?");
		q.setParameters(nombre);
		return (List<String>)q.executeList();
	}

	public List<Campania> darCampanias(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCampania());
		q.setResultClass(Campania.class);
		return q.executeList();
	}

	public long eliminarCampaniaNombre(PersistenceManager pm, String nombre)
	{
		Query l = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaAfiliado()+" WHERE IDENTIFICACION=?");
		l.setParameters(nombre);
		l.executeUnique();

		Query m = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaUsuario()+" WHERE NUMERO_DOCUMENTO=?");
		m.setParameters(nombre);
		m.executeUnique();

		Query k = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaServicioCampania()+" WHERE ID_CAMPANIA=?");
		k.setParameters(nombre);
		k.executeUnique();

		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCampania()+" WHERE NOMBRE=?");
		q.setParameters(nombre);
		return (long) q.executeUnique();
	}

	public Campania darCampaniaNombre(PersistenceManager pm, String nombre)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCampania() + " WHERE ID_CAMPANIA=?");
		q.setResultClass(Campania.class);
		q.setParameters(nombre);
		return (Campania) q.executeUnique();
	}
}
