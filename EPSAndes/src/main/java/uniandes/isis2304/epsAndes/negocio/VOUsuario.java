package uniandes.isis2304.epsAndes.negocio;

/**
 * La interfaz VOUsuario.
 */
public interface VOUsuario {

	/**
	 * Da el email.
	 *
	 * @return el email
	 */
	public String getEmail();

	/**
	 * Da el rol.
	 *
	 * @return el rol
	 */
	public String getRol();

	/**
	 * Da el tipo de documento.
	 *
	 * @return el tipo de documento
	 */
	public String getTipo_Documento();

	/**
	 * Da el numero de documento.
	 *
	 * @return el numero de documento
	 */
	public String getNumero_Documento();

	/**
	 * Da el nombre.
	 *
	 * @return el nombre
	 */
	public String getNombre();

	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString();
}
