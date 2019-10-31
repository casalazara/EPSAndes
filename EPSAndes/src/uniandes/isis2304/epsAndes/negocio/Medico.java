package uniandes.isis2304.epsAndes.negocio;

import java.util.LinkedList;
import java.util.List;

/**
 * La clase Medico.
 */
public class Medico extends Usuario implements VOMedico{

	/** La especialidad. */
	private String especialidad;
	
	/** El numero de registro medico. */
	private String numero_Registro_Medico;
	
	/** Las ordenes generadas. */
	private List<Object []> ordenesGeneradas;
	
	/** Los servicios ofrecidos. */
	private List<Object []> serviciosOfrecidos;
	
	/** Las ips. */
	private List<Object []> ips;


	/**
	 * Instancia un nuevo medico.
	 *
	 * @param numero_Documento el numero documento
	 * @param nombre el nombre
	 * @param email el email
	 * @param rol el rol
	 * @param tipo_Documento el tipo de documento
	 * @param especialidad la especialidad
	 * @param numeroRegistroMedico el numero de registro medico
	 */
	public Medico(String numero_Documento, String nombre, String email, String rol, String tipo_Documento,String especialidad, String numeroRegistroMedico) {
		super(numero_Documento, nombre, email, rol, tipo_Documento);
		this.especialidad = especialidad;
		this.numero_Registro_Medico = numeroRegistroMedico;
		this.ordenesGeneradas = new LinkedList<Object []> ();
		this.serviciosOfrecidos=new LinkedList<Object []> ();
		this.ips=new LinkedList<Object []> ();
	}

	/**
	 * Da la ips.
	 *
	 * @return la ips
	 */
	public List<Object[]> getIps() {
		return ips;
	}

	/**
	 * Establece la ips.
	 *
	 * @param ips la nueva ips
	 */
	public void setIps(List<Object[]> ips) {
		this.ips = ips;
	}
	
	/**
	 * Da los servicios prestados.
	 *
	 * @return los servicios prestados
	 */
	public List<Object[]> getServiciosPrestados() {
		return serviciosOfrecidos;
	}

	/**
	 * Establece los servicios prestados.
	 *
	 * @param serviciosPrestados los nuevos servicios prestados
	 */
	public void setServiciosPrestados(List<Object[]> serviciosPrestados) {
		this.serviciosOfrecidos = serviciosPrestados;
	}
	
	/**
	 * Da la especialidad.
	 *
	 * @return la especialidad
	 */
	public String getEspecialidad() {
		return especialidad;
	}

	/**
	 * Establece la especialidad.
	 *
	 * @param especialidad la nueva especialidad
	 */
	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	/**
	 * Da el numero registro medico.
	 *
	 * @return el numero de registro medico
	 */
	public String getNumero_Registro_Medico() {
		return numero_Registro_Medico;
	}

	/**
	 * Establece el numero de registro medico.
	 *
	 * @param numero_Registro_Medico el nuevo numero de registro medico
	 */
	public void setNumero_Registro_Medico(String numero_Registro_Medico) {
		this.numero_Registro_Medico = numero_Registro_Medico;
	}

	/**
	 * Da las ordenes generadas.
	 *
	 * @return las ordenes generadas
	 */
	public List<Object[]> getOrdenesGeneradas() {
		return ordenesGeneradas;
	}

	/**
	 * Establece las ordenes generadas.
	 *
	 * @param ordenesGeneradas las nuevas ordenes generadas
	 */
	public void setOrdenesGeneradas(List<Object[]> ordenesGeneradas) {
		this.ordenesGeneradas = ordenesGeneradas;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Medico [nombre=" + nombre + ", email=" + email + ", rol=" + rol + ", tipo_Documento=" + tipo_Documento + ", numero_Documento=" + numero_Documento + "especialidad=" + especialidad + ", numero_Registro_Medico=" + numero_Registro_Medico+ "]";
	}
	
	/**
	 * To string completo.
	 *
	 * @return the string
	 */
	public String toStringCompleto() {
		String resp =  this.toString();
		resp += "\n --- Ordenes \n";
		int i = 1;
		for (Object [] Ordenes : ordenesGeneradas)
		{
			Orden orden = (Orden) Ordenes [0];
			resp += i++ + ". " + "[" +orden.toString() + "]\n";
		}
		resp += "\n\n --- Ordenes \n";

		i=1;
		for (Object [] Servicios : ordenesGeneradas)
		{
			Servicio servicio = (Servicio) Servicios [0];
			resp += i++ + ". " + "[" +servicio.toString() + "]\n";
		}
		resp += "\n\n --- Servicios \n";
		
		i=1;
		for (Object [] Ips : ordenesGeneradas)
		{
			IPS ips = (IPS) Ips [0];
			resp += i++ + ". " + "[" +ips.toString() + "]\n";
		}
		resp += "\n\n --- Ips \n";

		return resp;
	}
	

}