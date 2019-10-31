package uniandes.isis2304.epsAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

/**
 * Clase SQLUtil.
 */
class SQLUtil
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
	public SQLUtil (PersistenciaEPSAndes pp)
	{
		this.pp = pp;
	}

	/**
	 * Crea y ejecuta la sentencia SQL para obtener un nuevo número de secuencia.
	 *
	 * @param pm - El manejador de persistencia
	 * @return El número de secuencia generado
	 */
	public long nextval (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT "+ pp.darSeqEPSAndes() + ".nextval FROM DUAL");
		q.setResultClass(Long.class);
		long resp = (long) q.executeUnique();
		return resp;
	}

	/**
	 * Crea y ejecuta las sentencias SQL para cada tabla de la base de datos - EL ORDEN ES IMPORTANTE .
	 *
	 * @param pm - El manejador de persistencia
	 * @return Un arreglo con el número de tuplas borradas de cada tabla.
	 */
	public long [] limpiarEPSAndes (PersistenceManager pm)
	{
		Query qAdminDatos = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaAdminDatosEps());          
		Query qOrden = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaOrden());
		Query qCita = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCita());
		Query qGerente = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaGerente_Eps());
		Query qOfrecen = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaOfrecen());
		Query qPrestan = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPrestan());
		Query qRecepcionista = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaRecepcionista_Ips());
		Query qServicio = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaServicio());
		Query qTrabajan = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaTrabajan());
		Query qAfiliado = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaAfiliado());
		Query qMedico = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaMedico());
		Query qUsuario = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaUsuario());
		Query qRol = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaRol());
		Query qIPS = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaIps());
		Query qEPS = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaEps());

		long adminsEliminados = (long) qAdminDatos.executeUnique ();
		long ordenEliminados = (long) qOrden.executeUnique ();
		long citaEliminados = (long) qCita.executeUnique ();
		long gerenteEliminados = (long) qGerente.executeUnique ();
		long ofrecenEliminados = (long) qOfrecen.executeUnique ();
		long prestanEliminados = (long) qPrestan.executeUnique ();
		long recepcionistasEliminados = (long) qRecepcionista.executeUnique ();
		long serviciosEliminados = (long) qServicio.executeUnique ();
		long trabajanEliminados = (long) qTrabajan.executeUnique ();
		long afiliadosEliminados = (long) qAfiliado.executeUnique ();
		long medicosEliminados = (long) qMedico.executeUnique ();
		long usuariosEliminados = (long) qUsuario.executeUnique ();
		long rolesEliminados=(long) qRol.executeUnique ();
		long ipsEliminados = (long) qIPS.executeUnique ();
		long epsEliminados = (long) qEPS.executeUnique ();

		return new long[] {adminsEliminados, ordenEliminados, citaEliminados, gerenteEliminados, 
				ofrecenEliminados, prestanEliminados, recepcionistasEliminados,serviciosEliminados,trabajanEliminados
				,afiliadosEliminados,medicosEliminados,usuariosEliminados,rolesEliminados,ipsEliminados,epsEliminados};
	}

}
