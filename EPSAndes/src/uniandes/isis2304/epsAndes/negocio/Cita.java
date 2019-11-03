package uniandes.isis2304.epsAndes.negocio;

/**
 * La Clase Cita.
 */
public class Cita implements VOCita{

	/** El id. */
	private Long id_Orden;
	
	/** Estado cumplida. */
	private int cumplida;
	
	/** El servicio. */
	private String id_Servicio;
	
	/** El paciente. */
	private String id_Afiliado;
	
	/** La fecha. */
	private String fecha;
	
	/** El recepcionista. */
	private String id_Recepcionista;
	
	/** L hora. */
	private String hora;

	/**
	 * Instancia una nueva cita.
	 *
	 * @param id el id
	 * @param cumplida el estado cumplida
	 * @param servicio el servicio
	 * @param paciente el paciente
	 * @param fecha la fecha
	 * @param recepcionista el recepcionista
	 * @param hora la hora
	 */
	public Cita(Long id, int cumplida, String servicio, String paciente,String fecha,String recepcionista, String hora) {
		super();
		this.id_Orden = id;
		this.cumplida = cumplida;
		this.id_Servicio = servicio;
		this.id_Afiliado = paciente;
		this.fecha = fecha;
		this.hora=hora;
		this.id_Recepcionista = recepcionista;
	}
	
	/**
	 * Da la hora.
	 *
	 * @return la hora
	 */
	public String getHora()
	{
		return hora;
	}
	
	/**
	 * Establece la hora.
	 *
	 * @param hora la nueva hora
	 */
	public void setHora(String hora)
	{
		this.hora=hora;
	}
	
	/**
	 * Da el id.
	 *
	 * @return el id
	 */
	public Long getId() {
		return id_Orden;
	}

	/**
	 * Establece id.
	 *
	 * @param id el nuevo id
	 */
	public void setId(Long id) {
		this.id_Orden = id;
	}

	/**
	 * Da el estado cumplida.
	 *
	 * @return estado cumplida
	 */
	public int getCumplida() {
		return cumplida;
	}

	/**
	 * Establece el estado cumplida.
	 *
	 * @param cumplida el nuevo estado cumplida
	 */
	public void setCumplida(int cumplida) {
		this.cumplida = cumplida;
	}

	/**
	 * Da el servicio.
	 *
	 * @return el servicio
	 */
	public String getServicio() {
		return id_Servicio;
	}

	/**
	 * Establece el servicio.
	 *
	 * @param servicio el nuevo servicio
	 */
	public void setServicio(String servicio) {
		this.id_Servicio = servicio;
	}

	/**
	 * Da el paciente.
	 *
	 * @return el paciente
	 */
	public String getPaciente() {
		return id_Afiliado;
	}

	/**
	 * Establece el paciente.
	 *
	 * @param paciente el nuevo paciente
	 */
	public void setPaciente(String paciente) {
		this.id_Afiliado = paciente;
	}

	/**
	 * Da la fecha.
	 *
	 * @return la fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * Da la fecha.
	 *
	 * @param fecha la nueva fecha
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	

	/**
	 * Da el recepcionista.
	 *
	 * @return el recepcionista
	 */
	public String getRecepcionista() {
		return id_Recepcionista;
	}

	/**
	 * Da el recepcionista.
	 *
	 * @param recepcionista el nuevo recepcionista
	 */
	public void setRecepcionista(String recepcionista) {
		this.id_Recepcionista = recepcionista;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Cita [id=" + id_Orden + ", cumplida=" + cumplida + ", servicio=" + id_Servicio + ", afiliado="
				+ id_Afiliado + ", fecha=" + fecha + ", recepcionista=" + id_Recepcionista + ", hora="+hora+"]";
	}

}