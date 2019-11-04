package uniandes.isis2304.epsAndes.negocio;

// TODO: Auto-generated Javadoc
/**
 * The Interface VOPrestan.
 */
public interface VOPrestan {

	/**
	 * Da la hora de inicio.
	 *
	 * @return la hora de inicio
	 */
	public String getHoraInicio();

	/**
	 * Da la duracion.
	 *
	 * @return la duracion
	 */
	public String getDuracion();

	/**
	 * Da el dia.
	 *
	 * @return el dia
	 */
	public String getDia();

	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString();

	/**
	 * Da el id de la IPS.
	 *
	 * @return el id de la IPS
	 */
	public String getIdIPS();

	/**
	 * Da la capacidad.
	 *
	 * @return la capacidad
	 */
	public int getCapacidad();

	/**
	 * Gets the capacidad max.
	 *
	 * @return the capacidad max
	 */
	public int getCapacidadMax();

	/**
	 * Gets the cancelada.
	 *
	 * @return the cancelada
	 */
	public int getCancelada();


	/**
	 * Da el id del servicio.
	 *
	 * @return el id del servicio
	 */
	public String getIdServicio();

}
