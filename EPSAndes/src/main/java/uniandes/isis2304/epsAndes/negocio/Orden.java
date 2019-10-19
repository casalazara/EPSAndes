package uniandes.isis2304.epsAndes.negocio;

/**
 * La clase Orden.
 */
public class Orden implements VOOrden{

	/** El id. */
	private Long id;
	
	/** El afiliado. */
	private String afiliado;
	
	/** El medico. */
	private String medico;

	/**
	 * Instancia una nueva orden.
	 *
	 * @param id el id
	 * @param afiliado el afiliado
	 * @param medico el medico
	 */
	public Orden(Long id, String afiliado, String medico) {
		this.id =id;
		this.afiliado = afiliado;
		this.medico = medico;
	}

	/**
	 * Da el id.
	 *
	 * @return el id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Establece el id.
	 *
	 * @param id el nuevo id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Da el afiliado.
	 *
	 * @return el afiliado
	 */
	public String getAfiliado() {
		return afiliado;
	}

	/**
	 * Establece el afiliado.
	 *
	 * @param afiliado el nuevo afiliado
	 */
	public void setAfiliado(String afiliado) {
		this.afiliado = afiliado;
	}

	/**
	 * Da el medico.
	 *
	 * @return el medico
	 */
	public String getMedico() {
		return medico;
	}

	/**
	 * Establece el medico.
	 *
	 * @param medico el nuevo medico
	 */
	public void setMedico(String medico) {
		this.medico = medico;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Orden [afiliado=" + afiliado + ", medico=" + medico +", id="+id+"]";
	}

}