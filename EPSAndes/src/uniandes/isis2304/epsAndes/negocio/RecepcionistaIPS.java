package uniandes.isis2304.epsAndes.negocio;

// TODO: Auto-generated Javadoc
/**
 * The Class RecepcionistaIPS.
 */
public class RecepcionistaIPS extends Usuario implements VORecepcionistaIPS{

	/** La ips. */
	public String ips;

	/**
	 * Instancia un nuevo recepcionista IPS.
	 *
	 * @param ips la ips
	 * @param numero_Documento el numero de documento
	 * @param nombre el nombre
	 * @param email el email
	 * @param rol el rol
	 * @param tipo_Documento el tipo de documento
	 */
	public RecepcionistaIPS(String ips,String numero_Documento, String nombre, String email, String rol, String tipo_Documento) 
	{
		super(numero_Documento, nombre, email, rol, tipo_Documento);
		this.ips = ips;
	}

	/**
	 * Da la ips.
	 *
	 * @return la ips
	 */
	public String getIps() {
		return ips;
	}


	/**
	 * Establece la ips.
	 *
	 * @param ips la nueva ips
	 */
	public void setIps(String ips) {
		this.ips = ips;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() 
	{
		return "Usuario [nombre=" + nombre + ", email=" + email + ", rolUsuario=" + rol + ", tipoDocumentacion=" + tipo_Documento + ", identificacion=" + numero_Documento + "]";
	}

}