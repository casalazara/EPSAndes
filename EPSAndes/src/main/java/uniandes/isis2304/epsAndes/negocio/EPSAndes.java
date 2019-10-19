package uniandes.isis2304.epsAndes.negocio;

import java.util.List;

import org.apache.log4j.Logger;
import com.google.gson.JsonObject;

import uniandes.isis2304.epsAndes.persistencia.PersistenciaEPSAndes;

/**
 * La clase EPSAndes.
 */
public class EPSAndes 
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/** The log. */
	private static Logger log = Logger.getLogger(EPSAndes.class.getName());

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/** The pp. */
	private PersistenciaEPSAndes pp;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Instancia una nueva EPSAndes.
	 */
	public EPSAndes ()
	{
		pp = PersistenciaEPSAndes.getInstance ();
	}

	/**
	 * Instancia una nueva EPSAndes.
	 *
	 * @param tableConfig the table config
	 */
	public EPSAndes (JsonObject tableConfig)
	{
		pp = PersistenciaEPSAndes.getInstance (tableConfig);
	}

	/**
	 * Cerrar unidad persistencia.
	 */
	public void cerrarUnidadPersistencia ()
	{
		pp.cerrarUnidadPersistencia ();
	}

	/**
	 * Registrar rol.
	 *
	 * @param nombre the nombre
	 * @return the rol
	 */
	public Rol registrarRol(String nombre)
	{
		log.info ("Adicionando rol " + nombre);
		Rol rol = pp.adicionarRol(nombre);
		log.info ("adicionado el rol: " + rol);
		return rol;
	}

	/**
	 * Registrar afiliado.
	 *
	 * @param numero_Documento the numero documento
	 * @param nombre the nombre
	 * @param rol the rol
	 * @param email the email
	 * @param tipo_Documento the tipo documento
	 * @param fechaNacimiento the fecha nacimiento
	 * @param eps the eps
	 * @return the afiliado
	 */
	public Afiliado registrarAfiliado(String numero_Documento,String nombre, String rol, String email, String tipo_Documento,String fechaNacimiento,String eps)
	{
		log.info ("Adicionando afiliado " + nombre);
		Afiliado afiliado = pp.registrarAfiliado(numero_Documento, nombre, rol, email, tipo_Documento, fechaNacimiento, eps);
		log.info ("adicionado el afiliado: " + afiliado);
		return afiliado;
	}

	/**
	 * Registrar IPS.
	 *
	 * @param localizacion the localizacion
	 * @param nombre the nombre
	 * @param recepcionistas the recepcionistas
	 * @param nombreEPS the nombre EPS
	 * @return the ips
	 */
	public IPS registrarIPS(String localizacion, String nombre,RecepcionistaIPS recepcionistas,String nombreEPS )
	{
		log.info ("Adicionando ips " + nombre);
		IPS ips = pp.registrarIPS(localizacion, nombre, recepcionistas, nombreEPS);
		log.info ("adicionado la ips: " + ips);
		return ips;
	}

	/**
	 * Registrar medico.
	 *
	 * @param numero_Documento the numero documento
	 * @param nombre the nombre
	 * @param email the email
	 * @param rol the rol
	 * @param tipo_Documento the tipo documento
	 * @param especialidad the especialidad
	 * @param registroMedico the registro medico
	 * @param ips the ips
	 * @return the usuario
	 */
	public Usuario registrarMedico(String numero_Documento, String nombre, String email, String rol, String tipo_Documento, String especialidad, String registroMedico,String ips)
	{
		log.info ("Adicionando medico " + nombre);
		Usuario medico = pp.registrarMedico(numero_Documento, nombre, email, rol, tipo_Documento, especialidad, registroMedico, ips);
		log.info ("adicionado el medico: " + medico);
		return medico;
	}

	/**
	 * Registrar usuario.
	 *
	 * @param numero_Documento the numero documento
	 * @param nombre the nombre
	 * @param email the email
	 * @param rol the rol
	 * @param tipo_Documento the tipo documento
	 * @return the usuario
	 */
	public Usuario registrarUsuario(String numero_Documento, String nombre, String email, String rol, String tipo_Documento)
	{
		log.info ("Adicionando usuario " + nombre);
		Usuario usuario = pp.registrarUsuario(numero_Documento, nombre, email, rol, tipo_Documento);
		log.info ("adicionado el usuario: " + usuario);
		return usuario;
	}

	/**
	 * Registrar servicio de salud A prestar.
	 *
	 * @param duracion the duracion
	 * @param horaInicio the hora inicio
	 * @param dia the dia
	 * @param idServicio the id servicio
	 * @param idIps the id ips
	 * @param capacidad the capacidad
	 * @param tipo the tipo
	 * @return the prestan
	 */
	public Prestan registrarServicioDeSaludAPrestar(String duracion,String horaInicio,String dia,String idServicio,String idIps,int capacidad, String tipo)
	{
		log.info ("Adicionando el servicio: " + idServicio);
		Prestan servicioAPrestar = pp.registrarServicio(duracion, horaInicio, dia, idServicio, idIps, capacidad, tipo);
		log.info ("adicionado el servicio: " + servicioAPrestar);
		return servicioAPrestar;
	}

	/**
	 * Registrar orden de servicio.
	 *
	 * @param servicio the servicio
	 * @param id_Afiliado the id afiliado
	 * @param id_Medico the id medico
	 * @return the orden
	 */
	public Orden registrarOrdenDeServicio(String servicio, String id_Afiliado, String id_Medico)
	{
		log.info ("Adicionando la orden de servicio: " + servicio);
		Orden ordenDeServicio = pp.registrarOrden(servicio, id_Afiliado, id_Medico);
		log.info ("adicionado la orden servicio: " + ordenDeServicio);
		return ordenDeServicio;
	}

	/**
	 * Registrar reserva.
	 *
	 * @param servicio the servicio
	 * @param paciente the paciente
	 * @param fecha the fecha
	 * @param id the id
	 * @param hora the hora
	 * @param idIPS the id IPS
	 * @return the cita
	 */
	public Cita registrarReserva(String servicio, String paciente, String fecha, long id, String hora, String idIPS)
	{
		log.info ("Adicionando la cita: " + servicio);
		Cita cita = pp.registrarCita(servicio, paciente, fecha, id, hora, idIPS);
		log.info ("adicionado la cita: " + cita);
		return cita;
	}

	/**
	 * Registrar prestacion.
	 *
	 * @param idCita the id cita
	 * @param id_Recepcionista the id recepcionista
	 */
	public void registrarPrestacion(long idCita, String id_Recepcionista)
	{
		log.info ("Adicionando la prestación: " + idCita);
		pp.registrarPrestacion(idCita, id_Recepcionista);
		log.info ("adicionado la prestación al recepcionista: " + id_Recepcionista);
	}

	/**
	 * Dar prestan disponibles.
	 *
	 * @param idServicio the id servicio
	 * @return the list
	 */
	public List<Prestan> darPrestanDisponibles(String idServicio)
	{
		log.info("Buscando servicios disponibles con id "+idServicio);
		return pp.darPrestanDisponibles(idServicio);
	}

	public String darTablaCita()
	{
		return pp.darTablaCita();
	}
	/**
	 * Dar IPSY cantidad servicios ofrece.
	 *
	 * @param fechaInicio the fecha inicio
	 * @param fechaFin the fecha fin
	 * @return the list
	 */
	public List<Object []> darIPSYCantidadServiciosOfrece ( String fechaInicio, String fechaFin)
	{
		log.info ("Dando IPS y cantidad de servicios ofrecidos en el rango: " + fechaInicio+" - "+fechaFin);

		return pp.darIPSYCantidadServiciosOfrece(fechaInicio, fechaFin);
	}

	/**
	 * Dar 20 servicios mas solicitados.
	 *
	 * @param fechaInicio the fecha inicio
	 * @param fechaFin the fecha fin
	 * @return the list
	 */
	public List<Object []> dar20ServiciosMasSolicitados (String fechaInicio, String fechaFin)
	{
		log.info ("Dando 20 servicios más solicitados en el rango: " + fechaInicio+" - "+fechaFin);
		return pp.dar20ServiciosMasSolicitados(fechaInicio, fechaFin);
	}


	/**
	 * Utilizacion por afiliado.
	 *
	 * @param idAfiliado the id afiliado
	 * @param fecha1 the fecha 1
	 * @param fecha2 the fecha 2
	 * @return the list
	 */
	public List<Object[]> utilizacionPorAfiliado (String idAfiliado, String fecha1, String fecha2)
	{
		log.info ("Dando  utilización por afiliado en el rango: " + fecha1+" - "+fecha2);

		return pp.utilizacionPorAfiliado(idAfiliado, fecha1, fecha2);
	}

	/**
	 * Mostrar servicios por caracteristicas.
	 *
	 * @param idRecepcionista the id recepcionista
	 * @param tipo the tipo
	 * @param veces the veces
	 * @param fechaInic the fecha inic
	 * @param fechaFin the fecha fin
	 * @return the list
	 */
	public List<Servicio> mostrarServiciosPorCaracteristicas(String idRecepcionista, String tipo, int veces,String fechaInic, String fechaFin)
	{
		log.info ("Dando los servicios por características: " +veces+" - "+tipo+" - "+ idRecepcionista+" - "+fechaFin);

		return pp.mostrarServiciosPorCaracteristicas(idRecepcionista, tipo, veces, fechaInic, fechaFin);
	}

	/**
	 * Dar indice de uso.
	 *
	 * @return the list
	 */
	public List<Object[]> darIndiceDeUso()
	{
		log.info ("Dando los indices de uso respecto a los demás servicios");
		return pp.darIndiceDeUso();
	}

	/* ****************************************************************
	 * 			Métodos para administración
	 *****************************************************************/

	/**
	 * Limpiar EPSAndes.
	 *
	 * @return the long[]
	 */
	public long [] limpiarEPSAndes ()
	{
		log.info ("Limpiando la BD de EPSAndes");
		long [] borrrados = pp.limpiarEPSAndes();	
		log.info ("Limpiando la BD de EPSAndes: Listo!");
		return borrrados;
	}
}
