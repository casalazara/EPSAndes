package uniandes.isis2304.epsAndes.negocio;

/**
 * The Class ReservaCampania.
 */
public class ReservaCampania implements VOReservaCampania {
	/** El id de la campania. */
	private String idCampania;

	/** El id del servicio. */
	private String idServicio;
	
	/** The fecha ini. */
	private String fechaIni;
	
	/** The fecha fin. */
	private String fechaFin;
	
	/** The capacidad F. */
	private int capacidadF;
	
	/** The capacidad ini. */
	private int capacidadIni;

	/**
	 * Instantiates a new reserva campania.
	 *
	 * @param fechaIni the fecha ini
	 * @param fechaFin the fecha fin
	 * @param idCampania the id campania
	 * @param idServicio the id servicio
	 * @param capacidadF the capacidad F
	 * @param capacidadIni the capacidad ini
	 */
	public ReservaCampania(String fechaIni,String fechaFin,String idCampania, String idServicio,int capacidadF, int capacidadIni) {
		super();
		this.idCampania = idCampania;
		this.idServicio = idServicio;
		this.capacidadF = capacidadF;
		this.capacidadIni = capacidadIni;
		this.fechaIni=(fechaIni);
		this.fechaFin=(fechaFin);
	}

	/**
	 * Gets the id campania.
	 *
	 * @return the id campania
	 */
	public String getIdCampania() {
		return idCampania;
	}

	/**
	 * Sets the id campania.
	 *
	 * @param idCampania the new id campania
	 */
	public void setIdCampania(String idCampania) {
		this.idCampania = idCampania;
	}

	/**
	 * Gets the id servicio.
	 *
	 * @return the id servicio
	 */
	public String getIdServicio() {
		return idServicio;
	}

	/**
	 * Sets the id servicio.
	 *
	 * @param idServicio the new id servicio
	 */
	public void setIdServicio(String idServicio) {
		this.idServicio = idServicio;
	}

	/**
	 * Gets the capacidad F.
	 *
	 * @return the capacidad F
	 */
	public int getCapacidadF() {
		return capacidadF;
	}

	/**
	 * Sets the capacidad F.
	 *
	 * @param capacidadF the new capacidad F
	 */
	public void setCapacidadF(int capacidadF) {
		this.capacidadF = capacidadF;
	}

	/**
	 * Gets the capacidad ini.
	 *
	 * @return the capacidad ini
	 */
	public int getCapacidadIni() {
		return capacidadIni;
	}

	/**
	 * Sets the capacidad ini.
	 *
	 * @param capacidadIni the new capacidad ini
	 */
	public void setCapacidadIni(int capacidadIni) {
		this.capacidadIni = capacidadIni;
	}

	/**
	 * Gets the fecha ini.
	 *
	 * @return the fecha ini
	 */
	public String getFechaIni() {
		return fechaIni;
	}

	/**
	 * Sets the fecha ini.
	 *
	 * @param fechaIni the new fecha ini
	 */
	public void setFechaIni(String fechaIni) {
		this.fechaIni = fechaIni;
	}

	/**
	 * Gets the fecha fin.
	 *
	 * @return the fecha fin
	 */
	public String getFechaFin() {
		return fechaFin;
	}

	/**
	 * Sets the fecha fin.
	 *
	 * @param fechaFin the new fecha fin
	 */
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
}
