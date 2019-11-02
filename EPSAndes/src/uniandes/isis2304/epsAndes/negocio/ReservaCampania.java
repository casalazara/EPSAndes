package uniandes.isis2304.epsAndes.negocio;

public class ReservaCampania implements VOReservaCampania {
	/** El id de la campania. */
	private String idCampania;

	/** El id del servicio. */
	private String idServicio;
	private String fechaIni;
	private String fechaFin;
	private int capacidadF;
	private int capacidadIni;

	public ReservaCampania(String fechaIni,String fechaFin,String idCampania, String idServicio,int capacidadF, int capacidadIni) {
		super();
		this.idCampania = idCampania;
		this.idServicio = idServicio;
		this.capacidadF = capacidadF;
		this.capacidadIni = capacidadIni;
		this.fechaIni=(fechaIni);
		this.fechaFin=(fechaFin);
	}

	public String getIdCampania() {
		return idCampania;
	}

	public void setIdCampania(String idCampania) {
		this.idCampania = idCampania;
	}

	public String getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(String idServicio) {
		this.idServicio = idServicio;
	}

	public int getCapacidadF() {
		return capacidadF;
	}

	public void setCapacidadF(int capacidadF) {
		this.capacidadF = capacidadF;
	}

	public int getCapacidadIni() {
		return capacidadIni;
	}

	public void setCapacidadIni(int capacidadIni) {
		this.capacidadIni = capacidadIni;
	}

	public String getFechaIni() {
		return fechaIni;
	}

	public void setFechaIni(String fechaIni) {
		this.fechaIni = fechaIni;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
}
