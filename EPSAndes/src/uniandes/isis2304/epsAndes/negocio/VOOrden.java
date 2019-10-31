package uniandes.isis2304.epsAndes.negocio;

// TODO: Auto-generated Javadoc
/**
 * The Interface VOOrden.
 */
public interface VOOrden {

	/**
	 * Da el afiliado.
	 *
	 * @return el afiliado
	 */
	public String getId_Afiliado();

	/**
	 * Da el medico.
	 *
	 * @return el medico
	 */
	public String getId_Medico();

	/**
	 * Da el id.
	 *
	 * @return el id
	 */
	public long getId();

	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString();

	public String getNom_Servicio();
}
