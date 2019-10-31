package uniandes.isis2304.epsAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.epsAndes.negocio.Servicio;

/**
 * The Class SQLServicio.
 */
class SQLServicio 
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/** The Constant SQL. */
	private final static String SQL = PersistenciaEPSAndes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/** The pp. */
	private PersistenciaEPSAndes pp;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Instantiates a new SQL servicio.
	 *
	 * @param pp the pp
	 */
	public SQLServicio (PersistenciaEPSAndes pp)
	{
		this.pp = pp;
	}

	/**
	 * Adicionar servicio.
	 *
	 * @param pm the pm
	 * @param nombre the nombre
	 * @param tipo the tipo
	 * @return the long
	 */
	public long adicionarServicio (PersistenceManager pm, String nombre, String tipo) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaServicio() + "(NOMBRE, TIPO) values (?, ?)");
		q.setParameters(nombre, tipo);
		return (long) q.executeUnique();            
	}

	/**
	 * Dar servicio por id.
	 *
	 * @param pm the pm
	 * @param identificacion the identificacion
	 * @return the servicio
	 */
	public Servicio darServicioPorId (PersistenceManager pm, String identificacion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaServicio() + " WHERE NOMBRE = ?");
		q.setResultClass(Servicio.class);
		q.setParameters(identificacion);
		return (Servicio) q.executeUnique();
	}

	/**
	 * Dar servicios.
	 *
	 * @param pm the pm
	 * @return the list
	 */
	public List<Servicio> darServicios (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaServicio());
		q.setResultClass(Servicio.class);
		return (List<Servicio>) q.executeList();
	}

	/**
	 * Mostrar servicios por caracteristicas.
	 *
	 * @param pm the pm
	 * @param caracteristicas the caracteristicas
	 * @return the list
	 */
	public List<Object[]> mostrarServiciosPorCaracteristicas(PersistenceManager pm,String idRecepcionista,String tipo, int veces,String fechaInic, String fechaFin)
	{
		String caracteristicas = "";
		if((!fechaInic.equalsIgnoreCase(null)&& !fechaInic.equalsIgnoreCase("") && fechaInic!=null) && (!fechaFin.equalsIgnoreCase(null) && !fechaFin.equalsIgnoreCase("") && fechaFin!=null) && veces!= -1) {
			caracteristicas = "TO_DATE(c.FECHA, 'DD-MM-YY HH24:MI:SS') "
					+ "BETWEEN '" + fechaInic + "' AND '" + fechaFin + "' "
					+ "AND Count(s.NOMBRE) = " + veces;
		}
		if(!idRecepcionista.equalsIgnoreCase(null)&& !idRecepcionista.equalsIgnoreCase("") && idRecepcionista!=null) {
			if(!caracteristicas.equalsIgnoreCase(""))
				caracteristicas+=" AND ";
			caracteristicas += "c.CUMPLIDA = 1 AND c.ID_RECEPCIONISTA = " + idRecepcionista;
		}
		if(!tipo.equalsIgnoreCase(null) && !tipo.equalsIgnoreCase("") && tipo!=null) {
			if(!caracteristicas.equalsIgnoreCase(""))
				caracteristicas+=" AND ";
			caracteristicas += "TIPO = " + tipo;
		}		

		String sql="SELECT COUNT(s.NOMBRE), s.NOMBRE nombre, s.TIPO tipo"
				+ " FROM ORDEN o, CITA c, PRESTAN p, SERVICIO s "
				+ "WHERE o.ID = c.ID_ORDEN "
				+ "AND o.NOM_SERVICIO = s.NOMBRE "
				+ "AND s.NOMBRE = p.ID_SERVICIO ";
		if(!caracteristicas.equals(""))
			sql+="AND " + caracteristicas;
		sql+= " GROUP BY s.NOMBRE,s.TIPO";
		Query q = pm.newQuery(SQL, sql);
		return q.executeList();
	}
}
