package uniandes.isis2304.epsAndes.negocio;

/**
 * La clase Orden.
 */
public class Orden implements VOOrden{

	/** El id. */
	private long id;

	/** El afiliado. */
	private String id_Afiliado;

	/** El medico. */
	private String id_Medico;

	private String nom_Servicio;

	public Orden()
	{
		this.id = 0;
		this.id_Afiliado = "";
		this.id_Medico = "";
		this.nom_Servicio = "";
	}

	/**
	 * Instancia una nueva orden.
	 *
	 * @param id el id
	 * @param afiliado el afiliado
	 * @param medico el medico
	 */
	public Orden(String afiliado, String medico,long id, String nombreSer) {
		this.id =id;
		this.id_Afiliado = afiliado;
		this.id_Medico = medico;
		this.nom_Servicio=nombreSer;
	}

	/**
	 * Da el id.
	 *
	 * @return el id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Establece el id.
	 *
	 * @param id el nuevo id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Da el afiliado.
	 *
	 * @return el afiliado
	 */
	public String getId_Afiliado() {
		return id_Afiliado;
	}

	/**
	 * Establece el afiliado.
	 *
	 * @param afiliado el nuevo afiliado
	 */
	public void setId_Afiliado(String afiliado) {
		this.id_Afiliado = afiliado;
	}

	/**
	 * Da el medico.
	 *
	 * @return el medico
	 */
	public String getId_Medico() {
		return id_Medico;
	}

	/**
	 * Establece el medico.
	 *
	 * @param medico el nuevo medico
	 */
	public void setId_Medico(String medico) {
		this.id_Medico = medico;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Orden [afiliado=" + id_Afiliado + ", medico=" + id_Medico +", id="+id+"]";
	}

	public String getNom_Servicio() {
		return nom_Servicio;
	}

	public void setNom_Servicio(String nombreServicio) {
		this.nom_Servicio = nombreServicio;
	}

}