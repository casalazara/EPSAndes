package uniandes.isis2304.epsAndes.negocio;

import java.util.LinkedList;
import java.util.List;

/**
 * La Afiliado.
 */
public class Afiliado extends Usuario implements VOAfiliado{

	/** La fecha de nacimiento. */
	private String fechaNacimiento;
	
	/** Las citas. */
	private List<Object []> citas;
	
	/** Las ordenes. */
	private List<Object []> ordenes;
	
	/** La eps. */
	private String eps;


	/**
	 * Instancia un nuevo afiliado.
	 *
	 * @param eps la eps
	 * @param numero_Documento el numero de documento
	 * @param nombre el nombre
	 * @param email el email
	 * @param rol el rol
	 * @param tipo_Documento el tipo de documento
	 * @param fechaNacimiento la fecha de nacimiento
	 */
	public Afiliado(String eps,String numero_Documento, String nombre, String email, String rol, String tipo_Documento, String fechaNacimiento) {
		super(email, nombre, numero_Documento,  rol,  tipo_Documento);
		this.fechaNacimiento = fechaNacimiento;
		this.ordenes=new LinkedList<Object[]>();
		this.citas = new LinkedList<Object[]>();
		this.eps=eps;
	}

	/**
	 * Da la eps.
	 *
	 * @return la eps
	 */
	public String getEps()
	{
		return eps;
	}
	
	/**
	 * Establece la eps.
	 *
	 * @param eps la nueva eps
	 */
	public void setEps(String eps)
	{
		this.eps=eps;
	}
	
	/**
	 * Da la fecha de nacimiento.
	 *
	 * @return la fecha de nacimiento
	 */
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * Establece la fecha nacimiento.
	 *
	 * @param fechaNacimiento la nueva fecha de nacimiento
	 */
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * Da las citas.
	 *
	 * @return las citas
	 */
	public List<Object[]> getCitas() {
		return citas;
	}

	/**
	 * Establece las citas.
	 *
	 * @param citas las nuevas citas
	 */
	public void setCitas(List<Object[]> citas) {
		this.citas = citas;
	}
	
	/**
	 * Da las ordenes.
	 *
	 * @return las ordenes
	 */
	public List<Object[]> getOrdenes() {
		return ordenes;
	}

	/**
	 * Establece las ordenes.
	 *
	 * @param ordenes las nuevas ordenes
	 */
	public void setOrdenes(List<Object[]> ordenes) {
		this.ordenes = ordenes;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Afiliado [ nombre=" + nombre + ", email=" + email + ", rol=" + rol + ", tipo_Documento=" + tipo_Documento + ", numero_Documento=" + numero_Documento + ", fechaNacimiento=" + fechaNacimiento + ", eps="+eps+"]";
	}
	
	
}