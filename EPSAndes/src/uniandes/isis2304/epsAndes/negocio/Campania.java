package uniandes.isis2304.epsAndes.negocio;

import java.util.LinkedList;
import java.util.List;

/**
 * The Class Campania.
 */
public class Campania implements VOCampania{
	/** Las citas. */
	private List<Object []> servicios;
	
	/** The fecha inicio. */
	private String fechaInicio;
	
	/** The fecha fin. */
	private String fechaFin;
	
	/** The nombre. */
	private String nombre;
	
	/** The id organizador. */
	private String id_Organizador;


	/**
	 * Instantiates a new campania.
	 *
	 * @param nombre the nombre
	 * @param fechaFin the fecha fin
	 * @param fechaInicio the fecha inicio
	 * @param id_Organizador the id organizador
	 */
	public Campania(  String nombre,String fechaFin,String fechaInicio, String id_Organizador) {
		super();
		this.servicios = new LinkedList<Object[]>();
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.nombre = nombre;
		this.setId_Organizador(id_Organizador);
	}

	/**
	 * Gets the fecha inicio.
	 *
	 * @return the fecha inicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}
	
	/**
	 * Sets the fecha inicio.
	 *
	 * @param fechaInicio the new fecha inicio
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	/**
	 * Gets the fecha fin.
	 *
	 * @return the fecha fin
	 */
	public String getFechaFin() {
		return fechaFin;
	}

	/**
	 * Sets the fecha fin.
	 *
	 * @param fechaFin the new fecha fin
	 */
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Sets the nombre.
	 *
	 * @param nombre the new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * Gets the servicios.
	 *
	 * @return the servicios
	 */
	public List<Object []> getServicios() {
		return servicios;
	}
	
	/**
	 * Sets the servicios.
	 *
	 * @param servicios the new servicios
	 */
	public void setServicios(List<Object []> servicios) {
		this.servicios = servicios;
	}

	/**
	 * Gets the id organizador.
	 *
	 * @return the id organizador
	 */
	public String getId_Organizador() {
		return id_Organizador;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Campania [servicios=" + servicios + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin
				+ ", nombre=" + nombre + ", id_Organizador=" + id_Organizador + "]";
	}

	/**
	 * Sets the id organizador.
	 *
	 * @param id_Organizador the new id organizador
	 */
	public void setId_Organizador(String id_Organizador) {
		this.id_Organizador = id_Organizador;
	}

}
