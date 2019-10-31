package uniandes.isis2304.epsAndes.negocio;

import java.util.List;

/**
 * La interfaz VOServicio.
 */
public interface VOServicio {

	/**
	 * Da el nombre.
	 *
	 * @return el nombre
	 */
	public String getNombre();
	
	/**
	 * Da el tipo.
	 *
	 * @return el tipo
	 */
	public String getTipo();
	
	/**
	 * Da los medicos encargados.
	 *
	 * @return los medicos encargados
	 */
	public List<Object[]> getMedicosEncargados();
	
	/**
	 * Da los horarios.
	 *
	 * @return los horarios
	 */
	public List<Object[]> getHorarios();
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString();

}
