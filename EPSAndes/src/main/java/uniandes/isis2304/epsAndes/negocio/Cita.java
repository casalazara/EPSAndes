package uniandes.isis2304.epsAndes.negocio;

/**
 * La Clase Cita.
 */
public class Cita implements VOCita{

	/** El id. */
	private Long id;
	
	/** Estado cumplida. */
	private int cumplida;
	
	/** El servicio. */
	private String servicio;
	
	/** El paciente. */
	private String paciente;
	
	/** La fecha. */
	private String fecha;
	
	/** El recepcionista. */
	private String recepcionista;
	
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
		this.id = id;
		this.cumplida = cumplida;
		this.servicio = servicio;
		this.paciente = paciente;
		this.fecha = fecha;
		this.hora=hora;
		this.recepcionista = recepcionista;
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
		return id;
	}

	/**
	 * Establece id.
	 *
	 * @param id el nuevo id
	 */
	public void setId(Long id) {
		this.id = id;
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
		return servicio;
	}

	/**
	 * Establece el servicio.
	 *
	 * @param servicio el nuevo servicio
	 */
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	/**
	 * Da el paciente.
	 *
	 * @return el paciente
	 */
	public String getPaciente() {
		return paciente;
	}

	/**
	 * Establece el paciente.
	 *
	 * @param paciente el nuevo paciente
	 */
	public void setPaciente(String paciente) {
		this.paciente = paciente;
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
		return recepcionista;
	}

	/**
	 * Da el recepcionista.
	 *
	 * @param recepcionista el nuevo recepcionista
	 */
	public void setRecepcionista(String recepcionista) {
		this.recepcionista = recepcionista;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Cita [id=" + id + ", cumplida=" + cumplida + ", servicio=" + servicio + ", afiliado="
				+ paciente + ", fecha=" + fecha + ", recepcionista=" + recepcionista + ", hora="+hora+"]";
	}

}