package uniandes.isis2304.epsAndes.negocio;

import java.util.LinkedList;
import java.util.List;

/**
 * The Class OrganizadorCampania.
 */
public class OrganizadorCampania extends Usuario implements VOOrganizadorcampania{

	/** The campanias. */
	private List<Object[]> campanias;

	/**
	 * Instantiates a new organizador campania.
	 *
	 * @param numero_Documento the numero documento
	 * @param nombre the nombre
	 * @param email the email
	 * @param rol the rol
	 * @param tipo_Documento the tipo documento
	 */
	public OrganizadorCampania(String numero_Documento, String nombre,String email, String rol, String tipo_Documento) {
		super(email, nombre,numero_Documento,  rol, tipo_Documento);
		this.campanias=new LinkedList<Object []> ();
	}

	/**
	 * Gets the campanias.
	 *
	 * @return the campanias
	 */
	public List<Object[]> getCampanias() {
		return campanias;
	}

	/**
	 * Sets the campanias.
	 *
	 * @param campanias the new campanias
	 */
	public void setCampanias(List<Object[]> campanias) {
		this.campanias = campanias;
	}

}
