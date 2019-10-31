package uniandes.isis2304.epsAndes.negocio;

import java.util.LinkedList;
import java.util.List;

/**
 * La clase EPS.
 */
public class EPS implements VOEPS{

	/** El nombre. */
	private String nombre;
	
	/** Los afiliados. */
	public List<Object []> afiliados;
	
	/** Las ips. */
	public List<Object []> ips;
	
	/** El administrador de datos. */
	public AdminDatosEPS administradorDeDatos;
	
	/** El gerente. */
	public GerenteEPS gerente;
	
	/**
	 * Instancia una nueva eps.
	 *
	 * @param nombre el nombre
	 * @param administradorDeDatos el administrador de datos
	 * @param gerente el gerente
	 */
	public EPS(String nombre, AdminDatosEPS administradorDeDatos, GerenteEPS gerente) {
		this.nombre = nombre;
		afiliados = new LinkedList<Object []> ();
		ips =  new LinkedList<Object []> ();
		this.administradorDeDatos = administradorDeDatos;
		this.gerente = gerente;
	}

	/**
	 * Da el nombre.
	 *
	 * @return el nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre.
	 *
	 * @param nombre el nuevo nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Da los afiliados.
	 *
	 * @return los afiliados
	 */
	public List<Object[]> getAfiliados() {
		return afiliados;
	}

	/**
	 * Establece los afiliados.
	 *
	 * @param afiliados los nuevos afiliados
	 */
	public void setAfiliados(List<Object[]> afiliados) {
		this.afiliados = afiliados;
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
	 * Da el administrador de datos.
	 *
	 * @return el administrador de datos
	 */
	public AdminDatosEPS getAdministradorDeDatos() {
		return administradorDeDatos;
	}

	/**
	 * Establece el administrador de datos.
	 *
	 * @param administradorDeDatos el nuevo administrador de datos
	 */
	public void setAdministradorDeDatos(AdminDatosEPS administradorDeDatos) {
		this.administradorDeDatos = administradorDeDatos;
	}

	/**
	 * Da el gerente.
	 *
	 * @return el gerente
	 */
	public GerenteEPS getGerente() {
		return gerente;
	}

	/**
	 * Establece el gerente.
	 *
	 * @param gerente el nuevo gerente
	 */
	public void setGerente(GerenteEPS gerente) {
		this.gerente = gerente;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() 
	{
		return "EPS [nombre=" + nombre + ", administradorDatos=" + administradorDeDatos + ", gerente=" + gerente + "]";
	}

	/**
	 * To string completo.
	 *
	 * @return the string
	 */
	public String toStringCompleto () 
	{
		String resp =  this.toString();
		resp += "\n --- Ips \n";
		int i = 1;
		for (Object [] ips : ips)
		{
			IPS obIps = (IPS) ips [0];
			resp += i++ + ". " + "[" +obIps.toString() + "]\n";
		}
		resp += "\n\n --- Ips \n";
		
		resp += "\n --- Afiliados \n";
		i = 1;
		for (Object [] afiliados : afiliados)
		{
			Afiliado afiliado = (Afiliado) afiliados [0];
			resp += i++ + ". " + "[" +afiliado.toString() + "]\n";
		}
		resp += "\n\n --- Afiliados \n";
		return resp;
	}
}