package uniandes.isis2304.epsAndes.negocio;

/**
 * La clase Prestan.
 */
public class Prestan implements VOPrestan{

	/** El dia. */
	private String dia;

	/** La duracion. */
	private String duracion;

	/** La hora de inicio. */
	private String horaInicio;

	/** El id de la IPS. */
	private String idIPS;

	/** El id del servicio. */
	private String idServicio;

	/** La capacidad. */
	private int capacidad;

	/** The capacidad max. */
	private int capacidadMax;
	
	/** The cancelada. */
	private int cancelada;

	/**
	 * Instancia una nueva relación prestan.
	 *
	 * @param idIPS el id de la IPS
	 * @param idServicio el id del servicio
	 * @param duracion la duracion
	 * @param horaInicio la hora de inicio
	 * @param dia el dia
	 * @param capacidad la capacidad
	 * @param capacidadMax the capacidad max
	 * @param cancelada the cancelada
	 */
	public Prestan(String idIPS, String idServicio,String duracion,String horaInicio, String dia, int capacidad, int capacidadMax, int cancelada) {
		this.dia = dia;
		this.capacidad=capacidad;
		this.duracion = duracion;
		this.horaInicio=horaInicio;
		this.idIPS=idIPS;
		this.idServicio=idServicio;
		this.capacidadMax=capacidadMax;
		this.cancelada=cancelada;
	}

	/**
	 * Da el dia.
	 *
	 * @return el dia
	 */
	public String getDia() {
		return dia;
	}


	/**
	 * Establece el dia.
	 *
	 * @param dia el nuevo dia
	 */
	public void setDia(String dia) {
		this.dia = dia;
	}



	/**
	 * Da la duracion.
	 *
	 * @return la duracion
	 */
	public String getDuracion() {
		return duracion;
	}



	/**
	 * Establece la duracion.
	 *
	 * @param duracion la nueva duracion
	 */
	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}



	/**
	 * Da la hora de inicio.
	 *
	 * @return la hora de inicio
	 */
	public String getHoraInicio() {
		return horaInicio;
	}

	/**
	 * Establece la hora de inicio.
	 *
	 * @param horaInicio la nueva hora de inicio
	 */
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	/**
	 * Da el id de la IPS.
	 *
	 * @return el id de la IPS
	 */
	public String getIdIPS() {
		return idIPS;
	}

	/**
	 * Establece el id de la IPS.
	 *
	 * @param idIPS el nuevo id de la IPS
	 */
	public void setIdIPS(String idIPS) {
		this.idIPS = idIPS;
	}

	/**
	 * Da el id del servicio.
	 *
	 * @return el id del servicio
	 */
	public String getIdServicio() {
		return idServicio;
	}

	/**
	 * Establece el id del servicio.
	 *
	 * @param idServicio el nuevo id del servicio
	 */
	public void setIdServicio(String idServicio) {
		this.idServicio = idServicio;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Horario [dia=" + dia + ", idServicio="+idServicio+", idIPS="+ idIPS+", duracion=" + duracion +", horaInicio"+ horaInicio+", capacidad="+capacidad+"]";
	}

	/**
	 * Da la capacidad.
	 *
	 * @return la capacidad
	 */
	public int getCapacidad() {
		return capacidad;
	}

	/**
	 * Establece la capacidad.
	 *
	 * @param capacidad la nueva capacidad
	 */
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	/**
	 * Gets the capacidad max.
	 *
	 * @return the capacidad max
	 */
	public int getCapacidadMax() {
		return capacidadMax;
	}

	/**
	 * Sets the capacidad max.
	 *
	 * @param capacidadMax the new capacidad max
	 */
	public void setCapacidadMax(int capacidadMax) {
		this.capacidadMax = capacidadMax;
	}

	/**
	 * Gets the cancelada.
	 *
	 * @return the cancelada
	 */
	public int getCancelada() {
		return cancelada;
	}

	/**
	 * Sets the cancelada.
	 *
	 * @param cancelada the new cancelada
	 */
	public void setCancelada(int cancelada) {
		this.cancelada = cancelada;
	}


}