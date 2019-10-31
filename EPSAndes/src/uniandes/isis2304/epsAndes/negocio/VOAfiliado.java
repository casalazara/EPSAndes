package uniandes.isis2304.epsAndes.negocio;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Interface VOAfiliado.
 */
public interface VOAfiliado extends VOUsuario {
	
	/**
	 * Da la eps.
	 *
	 * @return la eps
	 */
	public String getEps();	
	
	/**
	 * Da la fecha de nacimiento.
	 *
	 * @return la fecha de nacimiento
	 */
	public String getFechaNacimiento();
	
	/**
	 * Da las citas.
	 *
	 * @return las citas
	 */
	public List<Object[]> getCitas();	
	
	/**
	 * Da las ordenes.
	 *
	 * @return las ordenes
	 */
	public List<Object[]> getOrdenes();
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString();
}
