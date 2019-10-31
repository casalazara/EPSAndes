package uniandes.isis2304.epsAndes.negocio;

// TODO: Auto-generated Javadoc
/**
 * The Interface VOCita.
 */
public interface VOCita {
	
	/**
	 * Da el id.
	 *
	 * @return the id
	 */
	public Long getId();
	
	/**
	 * Da el estado cumplida.
	 *
	 * @return el estado cumplida
	 */
	public int getCumplida();
	
	/**
	 * Da el servicio.
	 *
	 * @return el servicio
	 */
	public String getServicio();
	
	/**
	 * Da el paciente.
	 *
	 * @return el paciente
	 */
	public String getPaciente();
	
	/**
	 * Da la fecha.
	 *
	 * @return la fecha
	 */
	public String getFecha();
	
	/**
	 * Da el recepcionista.
	 *
	 * @return el recepcionista
	 */
	public String getRecepcionista();
	
	/**
	 * Da la hora.
	 *
	 * @return la hora
	 */
	public String getHora();
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString();


}
