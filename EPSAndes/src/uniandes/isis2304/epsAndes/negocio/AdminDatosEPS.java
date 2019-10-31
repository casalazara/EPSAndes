package uniandes.isis2304.epsAndes.negocio;

/**
 * La clase AdminDatosEPS.
 */
public class AdminDatosEPS extends Usuario implements VOAdminDatosEPS {
	

	/** La eps. */
	private String eps;
	
	/**
	 * Instancia un nuevo admin datos EPS.
	 *
	 * @param numero_Documento el numero de documento
	 * @param nombre el nombre
	 * @param email el email
	 * @param rol el rol
	 * @param tipo_Documento el tipo de documento
	 * @param eps la eps
	 */
	public AdminDatosEPS(String numero_Documento, String nombre, String email, String rol,String tipo_Documento, String eps) 
	{
		super(numero_Documento, nombre, email, rol, tipo_Documento);
		this.eps=eps;
	}
	
	/**
	 * Da la eps.
	 *
	 * @return la eps
	 */
	public String getEps() {
		return eps;
	}
	
	/**
	 * Establece la eps.
	 *
	 * @param eps la nueva eps
	 */
	public void setEps(String eps) {
		this.eps = eps;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "AdminDatosEPS [eps=" + eps + "]";
	}
	

}