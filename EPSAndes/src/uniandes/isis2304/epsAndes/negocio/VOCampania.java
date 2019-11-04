package uniandes.isis2304.epsAndes.negocio;

import java.util.List;

/**
 * The Interface VOCampania.
 */
public interface VOCampania {
	
	/**
	 * Gets the fecha inicio.
	 *
	 * @return the fecha inicio
	 */
	public String getFechaInicio();
	
	/**
	 * Gets the fecha fin.
	 *
	 * @return the fecha fin
	 */
	public String getFechaFin() ;
	
	/**
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre(); 
	
	/**
	 * Gets the id organizador.
	 *
	 * @return the id organizador
	 */
	public String getId_Organizador();
	
	/**
	 * Gets the servicios.
	 *
	 * @return the servicios
	 */
	public List<Object []> getServicios();
}
