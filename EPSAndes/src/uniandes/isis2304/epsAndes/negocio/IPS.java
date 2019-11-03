package uniandes.isis2304.epsAndes.negocio;

import java.util.LinkedList;
import java.util.List;

/**
 * La Class IPS.
 */
public class IPS implements VOIPS {

	/** La localizacion. */
	private String localizacion;

	/** El nombre. */
	private String nombre;

	private String id_EPS;
	/** Los recepcionistas. */
	private List<Object []> recepcionistas;

	/** Los medicos. */
	private List<Object []> medicos;

	/** Los servicios prestados. */
	private List<Object []> serviciosPrestados;


	/**
	 * Instancia una nueva ips.
	 *
	 * @param localizacion la localizacion
	 * @param nombre el nombre
	 */
	public IPS( String nombre, String localizacion,String id_Eps) {
		this.localizacion = localizacion;
		this.nombre = nombre;
		this.recepcionistas = new LinkedList<Object []> ();
		this.medicos = new LinkedList<Object []> ();
		this.serviciosPrestados = new LinkedList<Object []> ();
		this.id_EPS=id_Eps;
	}

	/**
	 * Da la localizacion.
	 *
	 * @return la localizacion
	 */
	public String getLocalizacion() {
		return localizacion;
	}

	/**
	 * Establece la localizacion.
	 *
	 * @param localizacion la nueva localizacion
	 */
	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	/**
	 * Da el nombre nombre.
	 *
	 * @return el nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre.
	 *
	 * @param nombre el nuevo nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Da los recepcionistas.
	 *
	 * @return los recepcionistas
	 */
	public List<Object []> getRecepcionistas() {
		return recepcionistas;
	}

	/**
	 * Establece los recepcionistas.
	 *
	 * @param recepcionistas los nuevos recepcionistas
	 */
	public void setRecepcionistas(List<Object []> recepcionistas) {
		this.recepcionistas = recepcionistas;
	}

	/**
	 * Da los medicos.
	 *
	 * @return los medicos
	 */
	public List<Object []> getMedicos() {
		return medicos;
	}

	/**
	 * Establece los medicos.
	 *
	 * @param medicos los nuevos medicos
	 */
	public void setMedicos(List<Object []> medicos) {
		this.medicos = medicos;
	}

	/**
	 * Da los servicios ofrecidos.
	 *
	 * @return los servicios ofrecidos
	 */
	public List<Object []> getServiciosOfrecidos() {
		return serviciosPrestados;
	}

	/**
	 * Establece los servicios ofrecidos.
	 *
	 * @param serviciosOfrecidos los nuevos servicios ofrecidos
	 */
	public void setServiciosOfrecidos(List<Object []> serviciosOfrecidos) {
		this.serviciosPrestados = serviciosOfrecidos;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "IPS [" + ", localizacion=" + localizacion + ", nombre=" + nombre +", id_Eps="+id_EPS+ "]";
	}

	/**
	 * To string completo.
	 *
	 * @return the string
	 */
	public String toStringCompleto () 
	{
		String resp =  this.toString();
		resp += "\n --- Servicios \n";
		int i = 1;
		for (Object [] Servicios : serviciosPrestados)
		{
			Servicio servicio = (Servicio) Servicios [0];
			resp += i++ + ". " + "[" +servicio.toString() + "]\n";
		}
		resp += "\n\n --- Servicios \n";

		resp += "\n --- Medicos \n";
		i = 1;
		for (Object [] Medicos : medicos)
		{
			Medico medico = (Medico) Medicos [0];
			resp += i++ + ". " + "[" +medico.toString() + "]\n";
		}
		resp += "\n\n --- Medicos \n";
		return resp;
	}

	public String getId_EPS() {
		return id_EPS;
	}

	public void setId_EPS(String id_EPS) {
		this.id_EPS = id_EPS;
	}

}