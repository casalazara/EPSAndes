package uniandes.isis2304.epsAndes.negocio;

/**
 * La clase Usuario.
 */
public class Usuario implements VOUsuario{

	/** El numero de documento. */
	protected String numero_Documento;

	/** El nombre. */
	protected String nombre;

	/** El email. */
	protected String email;

	/** El rol. */
	protected String rol;

	/** El tipo de documento. */
	protected String tipo_Documento;

	/**
	 * Instancia un nuevo usuario.
	 */
	public Usuario() {
		this.numero_Documento = "";
		this.nombre = "";
		this.email = "";
		this.rol = "";
		this.tipo_Documento = "";
	}

	/**
	 * Instancia un nuevo usuario.
	 *
	 * @param email el email
	 * @param nombre el nombre
	 * @param numero_Documento el numero de documento
	 * @param rol el rol
	 * @param tipo_Documento el tipo de documento
	 */
	public Usuario(String email,String nombre,String numero_Documento, String rol, String tipo_Documento) {
		this.email = email;
		this.nombre = nombre;
		this.numero_Documento = numero_Documento;
		this.rol = rol;
		this.tipo_Documento = tipo_Documento;
	}

	/**
	 * Da el email.
	 *
	 * @return el email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Establece el email.
	 *
	 * @param email el nuevo email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Da el rol rol.
	 *
	 * @return el rol
	 */
	public String getRol() {
		return rol;
	}

	/**
	 * Establece rol.
	 *
	 * @param rol el nuevo rol
	 */
	public void setRol(String rol) {
		this.rol = rol;
	}

	/**
	 * Da el tipo de documento.
	 *
	 * @return el tipo de documento
	 */
	public String getTipo_Documento() {
		return tipo_Documento;
	}

	/**
	 * Establece el tipo de documento.
	 *
	 * @param tipo_Documento el nuevo tipo de documento
	 */
	public void setTipo_Documento(String tipo_Documento) {
		this.tipo_Documento = tipo_Documento;
	}

	/**
	 * Da el numero de documento.
	 *
	 * @return el numero de documento
	 */
	public String getNumero_Documento() {
		return numero_Documento;
	}

	/**
	 * Establece el numero de documento.
	 *
	 * @param numero_Documento el nuevo numero de documento
	 */
	public void setNumero_Documento(String numero_Documento) {
		this.numero_Documento = numero_Documento;
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
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() 
	{
		return "Usuario [nombre=" + nombre + ", email=" + email + ", rol=" + rol + ", tipo_Documento=" + tipo_Documento + ", numero_Documento=" + numero_Documento + "]";
	}

}
