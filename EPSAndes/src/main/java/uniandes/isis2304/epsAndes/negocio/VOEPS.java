package uniandes.isis2304.epsAndes.negocio;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Interface VOEPS.
 */
public interface VOEPS {
	
	/**
	 * Da el nombre.
	 *
	 * @return el nombre
	 */
	public String getNombre();
	
	/**
	 * Da los afiliados.
	 *
	 * @return los afiliados
	 */
	public List<Object[]> getAfiliados();
	
	/**
	 * Da las ips.
	 *
	 * @return las ips
	 */
	public List<Object[]> getIps();
	
	/**
	 * Da el administrador de datos.
	 *
	 * @return el administrador de datos
	 */
	public AdminDatosEPS getAdministradorDeDatos();
	
	/**
	 * Da el gerente.
	 *
	 * @return el gerente
	 */
	public GerenteEPS getGerente();
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString();

}
