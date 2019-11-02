package uniandes.isis2304.epsAndes.negocio;

import java.util.LinkedList;
import java.util.List;

public class Campania implements VOCampania{
	/** Las citas. */
	private List<Object []> servicios;
	private String fechaInicio;
	private String fechaFin;
	private String nombre;



	public Campania( String fechaInicio,String fechaFin, String nombre) {
		super();
		this.servicios = new LinkedList<Object[]>();
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.nombre = nombre;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<Object []> getServicios() {
		return servicios;
	}
	public void setServicios(List<Object []> servicios) {
		this.servicios = servicios;
	}

}
