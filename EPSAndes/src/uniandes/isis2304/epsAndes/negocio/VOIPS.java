package uniandes.isis2304.epsAndes.negocio;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Interface VOIPS.
 */
public interface VOIPS {

	/**
	 * Da la localizacion.
	 *
	 * @return la localizacion
	 */
	public String getLocalizacion();
	
	/**
	 * Da el nombre.
	 *
	 * @return el nombre
	 */
	public String getNombre();
	
	/**
	 * Da los recepcionistas.
	 *
	 * @return los recepcionistas
	 */
	public List<Object []> getRecepcionistas();
	
	/**
	 * Da los medicos.
	 *
	 * @return los medicos
	 */
	public List<Object[]> getMedicos();
	
	/**
	 * Da los servicios ofrecidos.
	 *
	 * @return los servicios ofrecidos
	 */
	public List<Object[]> getServiciosOfrecidos();
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString();

}
