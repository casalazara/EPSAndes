package uniandes.isis2304.epsAndes.negocio;

import java.util.LinkedList;
import java.util.List;


/**
 * La clase Servicio.
 */
public class Servicio implements VOServicio{

	/** El nombre. */
	private String nombre;
	
	/** El tipo. */
	private String tipo;

	/** Los medicos encargados. */
	private List<Object[]> medicosEncargados;
	
	/** Los horarios. */
	private List<Object[]> horarios;

	/**
	 * Instancia un nuevo servicio.
	 *
	 * @param nombre el nombre
	 * @param tipo el tipo
	 */
	public Servicio( String nombre, String tipo) {
		this.nombre = nombre;
		this.tipo = tipo;
		this.medicosEncargados = new LinkedList<Object[]>();
		this.horarios = new LinkedList<Object[]>();
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
	 * Da el tipo.
	 *
	 * @return el tipo
	 */
	public String getTipo() {
		return tipo;
	}
	
	/**
	 * Establece el tipo.
	 *
	 * @param tipo el nuevo tipo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	/**
	 * Da los medicos encargados.
	 *
	 * @return los medicos encargados
	 */
	public List<Object[]> getMedicosEncargados() {
		return medicosEncargados;
	}
	
	/**
	 * Establece los medicos encargados.
	 *
	 * @param medicosEncargados los nuevos medicos encargados
	 */
	public void setMedicosEncargados(List<Object[]> medicosEncargados) {
		this.medicosEncargados = medicosEncargados;
	}
	
	/**
	 * Da los horarios.
	 *
	 * @return los horarios
	 */
	public List<Object[]> getHorarios() {
		return horarios;
	}
	
	/**
	 * Establece los horarios.
	 *
	 * @param horarios los nuevos horarios
	 */
	public void setHorarios(List<Object[]> horarios) {
		this.horarios = horarios;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Servicio [ nombre=" + nombre + ", tipo=" + tipo + "]";
	}

	/**
	 * To string completo.
	 *
	 * @return the string
	 */
	public String toStringCompleto () 
	{
		String resp =  this.toString();
		resp += "\n --- Horarios \n";
		int i = 1;
		for (Object [] Horarios : horarios)
		{
			Prestan horario = (Prestan) Horarios [0];
			resp += i++ + ". " + "[" +horario.toString() + "]\n";
		}
		resp += "\n\n --- Horarios \n";

		resp += "\n --- Medicos \n";
		i = 1;
		for (Object [] MedicosEncargados : horarios)
		{
			Medico medico = (Medico) MedicosEncargados [0];
			resp += i++ + ". " + "[" +medico.toString() + "]\n";
		}
		resp += "\n\n --- MedicosEncargados \n";

		return resp;
	}
}