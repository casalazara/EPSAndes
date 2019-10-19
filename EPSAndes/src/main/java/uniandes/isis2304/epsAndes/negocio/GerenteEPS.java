package uniandes.isis2304.epsAndes.negocio;

/**
 * La clase GerenteEPS.
 */
public class GerenteEPS extends Usuario implements VOGerenteEPS{

	/** La eps. */
	private EPS eps;

	/**
	 * Instancia un nuevo gerente EPS.
	 *
	 * @param numero_Documento el numero de documento
	 * @param nombre el nombre
	 * @param email el email
	 * @param rol el rol
	 * @param tipo_Documento el tipo de documento
	 * @param eps la eps
	 */
	public GerenteEPS(String numero_Documento, String nombre, String email, String rol, String tipo_Documento, EPS eps) 
	{
		super(numero_Documento, nombre, email, rol, tipo_Documento);
		this.eps=eps;
	}
	
	/**
	 * Da la eps.
	 *
	 * @return la eps
	 */
	public EPS getEps() {
		return eps;
	}
	
	/**
	 * Establece la eps.
	 *
	 * @param eps la nueva eps
	 */
	public void setEps(EPS eps) {
		this.eps = eps;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "GerenteEPS [eps=" + eps + "]";
	}

}
