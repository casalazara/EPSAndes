package uniandes.isis2304.epsAndes.negocio;

/**
 * La clase Ofrecen.
 */
public class Ofrecen implements VOOfrecen{

	/** el id del medico. */
	String id_Medico;

	/** el id del servicio. */
	String id_Servicio;

	/**
	 * Instancia una nueva relación ofrecen.
	 *
	 * @param idMedico el id del medico
	 * @param idServicio el id del servicio
	 */
	public Ofrecen(String idMedico, String idServicio)
	{
		this.id_Medico=idMedico;
		this.id_Servicio=idServicio;
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
	 * Da el id del servicio.
	 *
	 * @return el id del servicio
	 */
	public String getId_Servicio() {
		return id_Servicio;
	}

	/**
	 * Establece el id del servicio.
	 *
	 * @param id_Servicio el nuevo id del servicio servicio
	 */
	public void setId_Servicio(String id_Servicio) {
		this.id_Servicio = id_Servicio;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Ofrecen [id_Medico=" + id_Medico + ", id_Servicio="
				+ id_Servicio + "]";
	}
}
