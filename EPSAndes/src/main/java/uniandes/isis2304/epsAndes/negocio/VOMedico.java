package uniandes.isis2304.epsAndes.negocio;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Interface VOMedico.
 */
public interface VOMedico {
	
	/**
	 * Da la especialidad.
	 *
	 * @return la especialidad
	 */
	public String getEspecialidad();
	
	/**
	 * Da el numero de registro medico.
	 *
	 * @return el numero de registro medico
	 */
	public String getNumero_Registro_Medico();
	
	/**
	 * Da las ordenes generadas.
	 *
	 * @return las ordenes generadas
	 */
	public List<Object[]> getOrdenesGeneradas();
	
	/**
	 * Da los servicios prestados.
	 *
	 * @return los servicios prestados
	 */
	public List<Object[]> getServiciosPrestados();
	
	/**
	 * Da la ips.
	 *
	 * @return la ips
	 */
	public List<Object[]> getIps();
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString();


}
