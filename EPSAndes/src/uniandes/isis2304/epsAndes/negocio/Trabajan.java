package uniandes.isis2304.epsAndes.negocio;

// TODO: Auto-generated Javadoc
/**
 * The Class Trabajan.
 */
public class Trabajan implements VOTrabajan{
	
	/** El id del medico. */
	private String id_Medico;
	
	/** El id de la IPS. */
	private String id_IPS;


	/**
	 * Instancia una nueva relación trabajan.
	 *
	 * @param idMedico el id del medico
	 * @param idIPS el id de la IPS
	 */
	public Trabajan(String idMedico, String idIPS)
	{
		this.id_Medico=idMedico;
		this.id_IPS=idIPS;
	}

	/**
	 * Da el id del medico.
	 *
	 * @return el id del medico
	 */
	public String getId_Medico() {
		return id_Medico;
	}


	/**
	 * Establece el id del medico.
	 *
	 * @param id_Medico el nuevo id del medico
	 */
	public void setId_Medico(String id_Medico) {
		this.id_Medico = id_Medico;
	}


	/**
	 * Da el id de la IPS.
	 *
	 * @return el id de la IPS
	 */
	public String getId_IPS() {
		return id_IPS;
	}


	/**
	 * Establece el id de la IPS.
	 *
	 * @param id_IPS el nuevo id de la  IPS
	 */
	public void setId_IPS(String id_IPS) {
		this.id_IPS = id_IPS;
	}


	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Trabajan [id_Medico=" + id_Medico + ", id_IPS=" + id_IPS + "]";
	}
}
