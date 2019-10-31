package uniandes.isis2304.epsAndes.negocio;

/**
 * La clase Rol.
 */
public class Rol implements VORol{
	
	/** El nombre. */
	private String nombre;

	/**
	 * Da el nombre nombre.
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
	 * Instancia un nuevo rol.
	 *
	 * @param nombre el nombre
	 */
	public Rol(String nombre) {
		super();
		this.nombre = nombre;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Rol [nombre=" + nombre + "]";
	}
	
}
