package uniandes.isis2304.epsAndes.persistencia;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import uniandes.isis2304.epsAndes.negocio.Afiliado;
import uniandes.isis2304.epsAndes.negocio.Cita;
import uniandes.isis2304.epsAndes.negocio.IPS;
import uniandes.isis2304.epsAndes.negocio.Medico;
import uniandes.isis2304.epsAndes.negocio.Orden;
import uniandes.isis2304.epsAndes.negocio.Prestan;
import uniandes.isis2304.epsAndes.negocio.RecepcionistaIPS;
import uniandes.isis2304.epsAndes.negocio.Rol;
import uniandes.isis2304.epsAndes.negocio.Servicio;
import uniandes.isis2304.epsAndes.negocio.Trabajan;
import uniandes.isis2304.epsAndes.negocio.Usuario;

// TODO: Auto-generated Javadoc
/**
 * The Class PersistenciaEPSAndes.
 */
public class PersistenciaEPSAndes 
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/** Logger para escribir la traza de la ejecución. */
	private static Logger log = Logger.getLogger(PersistenciaEPSAndes.class.getName());

	/** Cadena para indicar el tipo de sentencias que se va a utilizar en una consulta. */
	public final static String SQL = "javax.jdo.query.SQL";

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/** Atributo privado que es el único objeto de la clase - Patrón SINGLETON. */
	private static PersistenciaEPSAndes instance;

	/** Fábrica de Manejadores de persistencia, para el manejo correcto de las transacciones. */
	private PersistenceManagerFactory pmf;

	/** Arreglo de cadenas con los nombres de las tablas de la base de datos, en su orden: Secuenciador, tipoBebida, bebida, bar, bebedor, gustan, sirven y visitan. */
	private List <String> tablas;

	/** Atributo para el acceso a las sentencias SQL propias a PersistenciaParranderos. */
	private SQLUtil sqlUtil;

	/** Atributo para el acceso a la tabla TIPOBEBIDA de la base de datos. */
	private SQLAdminDatosEPS sqlAdminDatosEPS;

	/** Atributo para el acceso a la tabla BEBIDA de la base de datos. */
	private SQLAfiliado sqlAfiliado;

	/** Atributo para el acceso a la tabla BAR de la base de datos. */
	private SQLCita sqlCita;

	/** Atributo para el acceso a la tabla BEBIDA de la base de datos. */
	private SQLEPS sqlEPS;

	/** Atributo para el acceso a la tabla GUSTAN de la base de datos. */
	private SQLGerente_EPS sqlGerenteEPS;

	/** Atributo para el acceso a la tabla SIRVEN de la base de datos. */
	private SQLIPS sqlIPS;

	/** Atributo para el acceso a la tabla VISITAN de la base de datos. */
	private SQLMedico sqlMedico;

	/** Atributo para el acceso a la tabla VISITAN de la base de datos. */
	private SQLOfrecen sqlOfrecen;

	/** Atributo para el acceso a la tabla VISITAN de la base de datos. */
	private SQLOrden sqlOrden;

	/** Atributo para el acceso a la tabla VISITAN de la base de datos. */
	private SQLPrestan sqlPrestan;

	/** Atributo para el acceso a la tabla VISITAN de la base de datos. */
	private SQLRecepcionista sqlRecepcionista;

	/** Atributo para el acceso a la tabla VISITAN de la base de datos. */
	private SQLServicio sqlServicio;

	/** Atributo para el acceso a la tabla VISITAN de la base de datos. */
	private SQLTrabajan sqlTrabajan;

	/** Atributo para el acceso a la tabla VISITAN de la base de datos. */
	private SQLUsuario sqlUsuario;

	/** Atributo para el acceso a la tabla VISITAN de la base de datos. */
	private SQLRol sqlRol;

	/* ****************************************************************
	 * 			Métodos del MANEJADOR DE PERSISTENCIA
	 *****************************************************************/

	/**
	 * Constructor privado con valores por defecto - Patrón SINGLETON.
	 */
	private PersistenciaEPSAndes ()
	{
		pmf = JDOHelper.getPersistenceManagerFactory("EPSAndes");		
		crearClasesSQL ();

		// Define los nombres por defecto de las tablas de la base de datos
		tablas = new LinkedList<String> ();
		tablas.add ("epsandes_sequence");
		tablas.add ("ADMINDATOSEPS");
		tablas.add ("AFILIADO");
		tablas.add ("CITA");
		tablas.add ("EPS");
		tablas.add ("GERENTE_EPS");
		tablas.add ("IPS");
		tablas.add ("MEDICO");
		tablas.add ("OFRECEN");
		tablas.add ("ORDEN");
		tablas.add ("PRESTAN");
		tablas.add ("RECEPCIONISTA_IPS");
		tablas.add ("SERVICIO");
		tablas.add ("TRABAJAN");
		tablas.add ("USUARIO");
		tablas.add("ROL");
	}

	/**
	 * Constructor privado, que recibe los nombres de las tablas en un objeto Json - Patrón SINGLETON.
	 *
	 * @param tableConfig - Objeto Json que contiene los nombres de las tablas y de la unidad de persistencia a manejar
	 */
	private PersistenciaEPSAndes (JsonObject tableConfig)
	{
		crearClasesSQL ();
		tablas = leerNombresTablas (tableConfig);

		String unidadPersistencia = tableConfig.get ("unidadPersistencia").getAsString ();
		log.trace ("Accediendo unidad de persistencia: " + unidadPersistencia);
		pmf = JDOHelper.getPersistenceManagerFactory (unidadPersistencia);
	}

	/**
	 * Gets the single instance of PersistenciaParranderos.
	 *
	 * @return Retorna el único objeto PersistenciaParranderos existente - Patrón SINGLETON
	 */
	public static PersistenciaEPSAndes getInstance ()
	{
		if (instance == null)
		{
			instance = new PersistenciaEPSAndes ();
		}
		return instance;
	}

	/**
	 * Constructor que toma los nombres de las tablas de la base de datos del objeto tableConfig.
	 *
	 * @param tableConfig - El objeto JSON con los nombres de las tablas
	 * @return Retorna el único objeto PersistenciaParranderos existente - Patrón SINGLETON
	 */
	public static PersistenciaEPSAndes getInstance (JsonObject tableConfig)
	{
		if (instance == null)
		{
			instance = new PersistenciaEPSAndes (tableConfig);
		}
		return instance;
	}

	/**
	 * Cierra la conexión con la base de datos.
	 */
	public void cerrarUnidadPersistencia ()
	{
		pmf.close ();
		instance = null;
	}

	/**
	 * Genera una lista con los nombres de las tablas de la base de datos.
	 *
	 * @param tableConfig - El objeto Json con los nombres de las tablas
	 * @return La lista con los nombres del secuenciador y de las tablas
	 */
	private List <String> leerNombresTablas (JsonObject tableConfig)
	{
		JsonArray nombres = tableConfig.getAsJsonArray("tablas") ;

		List <String> resp = new LinkedList <String> ();
		for (JsonElement nom : nombres)
		{
			resp.add (nom.getAsString ());
		}

		return resp;
	}

	/**
	 * Crea los atributos de clases de apoyo SQL.
	 */
	private void crearClasesSQL ()
	{
		sqlAdminDatosEPS = new SQLAdminDatosEPS(this);
		sqlAfiliado = new SQLAfiliado(this);
		sqlCita = new SQLCita(this);
		sqlEPS = new SQLEPS(this);
		sqlGerenteEPS = new SQLGerente_EPS(this);
		sqlIPS = new SQLIPS(this);
		sqlMedico = new SQLMedico(this);
		sqlOfrecen = new SQLOfrecen(this);
		sqlOrden = new SQLOrden(this);
		sqlPrestan = new SQLPrestan(this);
		sqlRecepcionista = new SQLRecepcionista(this);
		sqlServicio = new SQLServicio(this);
		sqlTrabajan = new SQLTrabajan(this);
		sqlUsuario = new SQLUsuario(this);
		sqlUtil = new SQLUtil(this);
		sqlRol=new SQLRol(this);
	}

	/**
	 * Dar seq parranderos.
	 *
	 * @return La cadena de caracteres con el nombre del secuenciador de parranderos
	 */
	public String darSeqParranderos ()
	{
		return tablas.get (0);
	}

	/**
	 * Dar tabla admin datos eps.
	 *
	 * @return La cadena de caracteres con el nombre de la tabla de TipoBebida de parranderos
	 */
	public String darTablaAdminDatosEps ()
	{
		return tablas.get (1);
	}

	/**
	 * Dar tabla afiliado.
	 *
	 * @return La cadena de caracteres con el nombre de la tabla de Bebida de parranderos
	 */
	public String darTablaAfiliado ()
	{
		return tablas.get (2);
	}

	/**
	 * Dar tabla cita.
	 *
	 * @return La cadena de caracteres con el nombre de la tabla de Bar de parranderos
	 */
	public String darTablaCita ()
	{
		return tablas.get (3);
	}

	/**
	 * Dar tabla eps.
	 *
	 * @return La cadena de caracteres con el nombre de la tabla de Bebedor de parranderos
	 */
	public String darTablaEps ()
	{
		return tablas.get (4);
	}

	/**
	 * Dar tabla gerente eps.
	 *
	 * @return La cadena de caracteres con el nombre de la tabla de Gustan de parranderos
	 */
	public String darTablaGerente_Eps ()
	{
		return tablas.get (5);
	}

	/**
	 * Dar tabla ips.
	 *
	 * @return La cadena de caracteres con el nombre de la tabla de Sirven de parranderos
	 */
	public String darTablaIps ()
	{
		return tablas.get (6);
	}

	/**
	 * Dar tabla medico.
	 *
	 * @return La cadena de caracteres con el nombre de la tabla de Sirven de parranderos
	 */
	public String darTablaMedico()
	{
		return tablas.get (7);
	}


	/**
	 * Dar tabla ofrecen.
	 *
	 * @return La cadena de caracteres con el nombre de la tabla de Sirven de parranderos
	 */
	public String darTablaOfrecen ()
	{
		return tablas.get (8);
	}


	/**
	 * Dar tabla orden.
	 *
	 * @return La cadena de caracteres con el nombre de la tabla de Sirven de parranderos
	 */
	public String darTablaOrden ()
	{
		return tablas.get (9);
	}


	/**
	 * Dar tabla prestan.
	 *
	 * @return La cadena de caracteres con el nombre de la tabla de Sirven de parranderos
	 */
	public String darTablaPrestan ()
	{
		return tablas.get (10);
	}


	/**
	 * Dar tabla recepcionista ips.
	 *
	 * @return La cadena de caracteres con el nombre de la tabla de Sirven de parranderos
	 */
	public String darTablaRecepcionista_Ips ()
	{
		return tablas.get (11);
	}


	/**
	 * Dar tabla servicio.
	 *
	 * @return La cadena de caracteres con el nombre de la tabla de Sirven de parranderos
	 */
	public String darTablaServicio ()
	{
		return tablas.get (12);
	}

	/**
	 * Dar tabla trabajan.
	 *
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaTrabajan ()
	{
		return tablas.get (13);
	}

	/**
	 * Dar tabla usuario.
	 *
	 * @return La cadena de caracteres con el nombre de la tabla de Sirven de parranderos
	 */
	public String darTablaUsuario ()
	{
		return tablas.get (14);
	}

	/**
	 * Dar tabla rol.
	 *
	 * @return La cadena de caracteres con el nombre de la tabla de Sirven de parranderos
	 */
	public String darTablaRol ()
	{
		return tablas.get (15);
	}


	/**
	 * Transacción para el generador de secuencia de Parranderos
	 * Adiciona entradas al log de la aplicación.
	 *
	 * @return El siguiente número del secuenciador de Parranderos
	 */
	private long nextval()
	{
		long resp = sqlUtil.nextval (pmf.getPersistenceManager());
		log.trace ("Generando secuencia: " + resp);
		return resp;
	}

	/**
	 * Extrae el mensaje de la exception JDODataStoreException embebido en la Exception e, que da el detalle específico del problema encontrado.
	 *
	 * @param e - La excepción que ocurrio
	 * @return El mensaje de la excepción JDO
	 */
	private String darDetalleException(Exception e) 
	{
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
		{
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions() [0].getMessage();
		}
		return resp;
	}

	/* ****************************************************************
	 * 			Métodos para manejar los TIPOS DE BEBIDA
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla TipoBebida
	 * Adiciona entradas al log de la aplicación.
	 *
	 * @param nombre - El nombre del tipo de bebida
	 * @return El objeto TipoBebida adicionado. null si ocurre alguna Excepción
	 */
	public Rol adicionarRol(String nombre)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlRol.adicionarRol(pm,nombre);
			tx.commit();

			log.trace ("Inserción de rol: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");

			return new Rol(nombre);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/* ****************************************************************
	 * 			Métodos para manejar las BEBIDAS
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla Bebida
	 * Adiciona entradas al log de la aplicación.
	 *
	 * @param numero_Documento the numero documento
	 * @param nombre - El nombre de la bebida
	 * @param email the email
	 * @param rol the rol
	 * @param tipo_Documento the tipo documento
	 * @return El objeto Bebida adicionado. null si ocurre alguna Excepción
	 */
	public Usuario registrarUsuario(String numero_Documento, String nombre, String email, String rol, String tipo_Documento)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();            
			long tuplasInsertadas = sqlUsuario.adicionarUsuario(pm, email, nombre, numero_Documento, rol, tipo_Documento);
			tx.commit();

			log.trace ("Inserción usuario: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
			return new Usuario (numero_Documento,nombre, email, rol,tipo_Documento);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/* ****************************************************************
	 * 			Métodos para manejar las BEBIDAS
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla Bebida
	 * Adiciona entradas al log de la aplicación.
	 *
	 * @param numero_Documento the numero documento
	 * @param nombre - El nombre de la bebida
	 * @param email the email
	 * @param rol the rol
	 * @param tipo_Documento the tipo documento
	 * @param pIPS the ips
	 * @return El objeto Bebida adicionado. null si ocurre alguna Excepción
	 */
	public RecepcionistaIPS registrarRecepcionista(String numero_Documento, String nombre, String email, String rol, String tipo_Documento, String pIPS)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			if(sqlUsuario.darUsuarioPorId(pm, numero_Documento)==null)
			{
				registrarUsuario(numero_Documento, nombre, email, rol, tipo_Documento);
				log.trace("Se está creando el usuario recepcionista");
			}
			tx.begin();            
			long tuplasInsertadas = sqlRecepcionista.adicionarRecepcionista(pm,pIPS, numero_Documento);
			tx.commit();
			log.trace ("Inserción recepcionista: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
			return new RecepcionistaIPS (pIPS, numero_Documento,nombre, email, rol,  tipo_Documento) ;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla Bebida
	 * Adiciona entradas al log de la aplicación.
	 *
	 * @param localizacion the localizacion
	 * @param nombreIPS - El nombre de la bebida
	 * @param recepcionista the recepcionistas
	 * @param nombreEPS the nombre EPS
	 * @return El objeto Bebida adicionado. null si ocurre alguna Excepción
	 */
	public IPS registrarIPS(String localizacion, String nombreIPS,RecepcionistaIPS recepcionista, String nombreEPS)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();   
			long tuplasInsertadas = sqlIPS.adicionarIPS(pm, nombreEPS, localizacion, nombreIPS);
			tx.commit();

			if(recepcionista!=null)
			{
				log.trace ("Inserción ips: registrando recepcionista");

				registrarRecepcionista( recepcionista.getNumero_Documento(), recepcionista.getNombre(), recepcionista.getEmail(), recepcionista.getRol(), recepcionista.getTipo_Documento(), nombreIPS);
			}
			else
			{
				log.trace("El recepcionista es null");
			}
			log.trace ("Inserción ips: " + nombreIPS + ": " + tuplasInsertadas + " tuplas insertadas");
			return new IPS (localizacion, nombreIPS);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Registrar trabajan.
	 *
	 * @param id_Medico el id medico
	 * @param id_IPS el id IPS
	 * @return el objeto trabajan creado
	 */
	public Trabajan registrarTrabajan(String id_Medico,String id_IPS)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();            
			long tuplasInsertadas = sqlTrabajan.adicionarTrabajan(pm, id_Medico, id_IPS);
			tx.commit();
			log.trace ("Inserción relación trabajan: " + id_Medico+"-"+id_IPS + ": " + tuplasInsertadas + " tuplas insertadas");
			return new Trabajan (id_Medico,id_IPS);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Dar indice de uso.
	 *
	 * @return the list
	 */
	public List<Object[]> darIndiceDeUso()
	{
		return sqlCita.darindiceDeUso(pmf.getPersistenceManager());
	}

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla Medico
	 * Adiciona entradas al log de la aplicación.
	 *
	 * @param numero_Documento el numero de documento
	 * @param nombre - El nombre del medico
	 * @param email el email
	 * @param rol el rol
	 * @param tipo_Documento el tipo de documento
	 * @param especialidad la especialidad
	 * @param registroMedico el registro medico
	 * @param ipse the ipse
	 * @return El objeto Medico adicionado. null si ocurre alguna Excepción
	 */
	public Medico registrarMedico(String numero_Documento, String nombre, String email,String rol, String tipo_Documento, String especialidad, String registroMedico,String ipse)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			if(sqlUsuario.darUsuarioPorId(pm, numero_Documento)==null)
			{
				registrarUsuario(numero_Documento, nombre, email, rol, tipo_Documento);

			}
			tx.begin();            
			long tuplasInsertadas = sqlMedico.adicionarMedico(pm, especialidad, numero_Documento, registroMedico);
			tx.commit();
			log.trace ("Inserción médico: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
			registrarTrabajan(numero_Documento,ipse);

			return new Medico (numero_Documento, nombre,email, rol, tipo_Documento,especialidad, registroMedico);

		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla Afiliado
	 * Adiciona entradas al log de la aplicación.
	 *
	 * @param numero_Documento el numero de documento
	 * @param nombre - El nombre del afiliado
	 * @param rol el rol
	 * @param email el email
	 * @param tipo_Documento el tipo de documento
	 * @param fechaNacimiento la fecha de nacimiento
	 * @param eps la eps
	 * @return El objeto Afiliado adicionado. null si ocurre alguna Excepción
	 */
	public Afiliado registrarAfiliado(String numero_Documento, String nombre,String rol, String email, String tipo_Documento, String fechaNacimiento,String eps)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			if(sqlUsuario.darUsuarioPorId(pm, numero_Documento)==null)
			{
				registrarUsuario(numero_Documento, nombre, email, rol, tipo_Documento);
			}
			tx.begin();            
			long tuplasInsertadas = sqlAfiliado.adicionarAfiliado(pm, eps, numero_Documento, fechaNacimiento);
			tx.commit();
			log.trace ("Inserción afiliado: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
			return new Afiliado ( eps, numero_Documento, nombre, email, rol, tipo_Documento, fechaNacimiento);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla Servicio
	 * Adiciona entradas al log de la aplicación.
	 *
	 * @param nombre - El nombre del servicio
	 * @param tipo el tipo
	 * @return El objeto Servicio adicionado. null si ocurre alguna Excepción
	 */
	public Servicio registrarServicioAPrestar(String nombre, String tipo)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();            
			long tuplasInsertadas = sqlServicio.adicionarServicio(pm, nombre, tipo);
			tx.commit();

			log.trace ("Inserción servicio a prestar: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
			return new Servicio(nombre, tipo);
		}	
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla PRESTAN
	 * Adiciona entradas al log de la aplicación.
	 *
	 * @param duracion la duracion
	 * @param horaInicio la hora inicio
	 * @param dia el dia
	 * @param idServicio el id servicio
	 * @param idIps el id ips
	 * @param capacidad la capacidad
	 * @param tipo el tipo
	 * @return El objeto Prestan adicionado. null si ocurre alguna Excepción
	 */
	public Prestan registrarServicio(String duracion, String horaInicio, String dia, String idServicio, String idIps,int capacidad,String tipo)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			if(sqlServicio.darServicioPorId(pm, idServicio)==null)
			{
				registrarServicioAPrestar(idServicio,tipo);
			}
			tx.begin();            
			long tuplasInsertadas = sqlPrestan.adicionarPrestan(pm, duracion, horaInicio, dia, idServicio, idIps, capacidad);
			tx.commit();

			log.trace ("Inserción servicio: " + idServicio + ": " + tuplasInsertadas + " tuplas insertadas");
			return new Prestan (idIps, idServicio,duracion,horaInicio,dia,capacidad);
		}	
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}


	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla Cita
	 * Adiciona entradas al log de la aplicación.
	 *
	 * @param servicio el servicio
	 * @param paciente el paciente
	 * @param fecha la fecha
	 * @param id el id
	 * @param hora la hora
	 * @param idIPS el id IPS
	 * @return El objeto CITA adicionado. null si ocurre alguna Excepción
	 */
	public Cita registrarCita(String servicio, String paciente, String fecha, long id, String hora, String idIPS)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			Servicio servicio2=sqlServicio.darServicioPorId(pm, servicio);
			Orden orden=(Orden)sqlOrden.darOrdenPorId(pm, id);
			if(orden!=null || (servicio2.getTipo().equals("Consulta con medico") || servicio2.getTipo().equals("Consulta de urgencias")) )
			{
				if(orden==null) orden=registrarOrden(servicio, paciente, "Generada por paciente");
				tx.begin();        
				long tuplasInsertadas = sqlCita.adicionarCita(pm, 0, orden.getId(), fecha, servicio, paciente, "",hora);
				long capacidadActualizada=sqlPrestan.actualizarCapacidad(pm, fecha, hora, servicio,idIPS);
				log.trace ("Actualizando capacidad del servicio: " + servicio + ": " + capacidadActualizada + " tuplas insertadas");
				tx.commit();

				log.trace ("Inserción cita: " + id + ": " + tuplasInsertadas + " tuplas insertadas");
				return new Cita(orden.getId(), 0, servicio, paciente, fecha, "",hora);
			}
			else 
			{
				log.error ("Se quiere registrar una cita que no es consulta con especialista o urgencia sin una orden");
				return null;
			}
		}	
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Da la IPS y la cantidad de servicios que ofrece.
	 *
	 * @param fechaInicio la fecha inicio
	 * @param fechaFin la fecha fin
	 * @return la lista con la IPS y la cantidad de servicios que ofrece
	 */
	public List<Object []> darIPSYCantidadServiciosOfrece (String fechaInicio, String fechaFin)
	{
		return sqlPrestan.darIPSYCantidadServiciosOfrecen(pmf.getPersistenceManager(), fechaInicio, fechaFin);
	}

	/**
	 * Da los 20 servicios mas solicitados.
	 *
	 * @param fechaInicio la fecha inicio
	 * @param fechaFin la fecha fin
	 * @return la lista con los 20 servicios más solciitados.
	 */
	public List<Object []> dar20ServiciosMasSolicitados (String fechaInicio, String fechaFin)
	{
		return sqlCita.dar20ServiciosMasSolicitados(pmf.getPersistenceManager(), fechaInicio, fechaFin);
	}


	/**
	 * Utilizacion por afiliado.
	 *
	 * @param idAfiliado the id afiliado
	 * @param fechaInic the fecha inic
	 * @param fechaFin the fecha fin
	 * @return the list
	 */
	public List<Object[]> utilizacionPorAfiliado (String idAfiliado, String fechaInic, String fechaFin)
	{
		return sqlCita.utilizacionPorAfiliado(pmf.getPersistenceManager(), idAfiliado, fechaInic, fechaFin);
	}


	/**
	 * Da los objetos prestan disponibles por servicio.
	 *
	 * @param idServicio el id servicio
	 * @return la lista con objetos PRESTAN que tienen el id ingresado
	 */
	public List<Prestan> darPrestanDisponibles (String idServicio)
	{
		return sqlPrestan.darPrestanDisponibles(pmf.getPersistenceManager(), idServicio);
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
	public List<Servicio> mostrarServiciosPorCaracteristicas(String idRecepcionista, String tipo, int veces,String fechaInic, String fechaFin){
		String caracteristicas = "";
		if((!fechaInic.equalsIgnoreCase(null) && fechaInic!=null) && (!fechaFin.equalsIgnoreCase(null) && fechaFin!=null) && veces!= -1) {
			caracteristicas = "TO_DATE(c.FECHA, 'DD-MM-YY HH24:MI:SS') "
					+ "BETWEEN '" + fechaInic + "' AND '" + fechaFin + "' "
					+ "AND Count(s.NOMBRE) = " + veces;
		}
		if(!idRecepcionista.equalsIgnoreCase(null) && idRecepcionista!=null) {
			if(!caracteristicas.equalsIgnoreCase(""))
				caracteristicas+=" AND ";
			caracteristicas += "c.CUMPLIDA = 1 AND c.ID_RECEPCIONISTA = " + idRecepcionista;
		}
		if(!tipo.equalsIgnoreCase(null) && tipo!=null) {
			if(!caracteristicas.equalsIgnoreCase(""))
				caracteristicas+=" AND ";
			caracteristicas += "TIPO = " + tipo;
		}		
		return sqlServicio.mostrarServiciosPorCaracteristicas(pmf.getPersistenceManager(), caracteristicas);
	}

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla Orden
	 * Adiciona entradas al log de la aplicación.
	 *
	 * @param servicio el servicio
	 * @param id_Afiliado el id afiliado
	 * @param id_Medico el id medico
	 * @return El objeto Orden adicionado. null si ocurre alguna Excepción
	 */
	public Orden registrarOrden(String servicio, String id_Afiliado, String id_Medico)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin(); 
			long id=nextval();
			long tuplasInsertadas = sqlOrden.adicionarOrden(pm, id, id_Afiliado, id_Medico,servicio);
			tx.commit();

			log.trace ("Inserción orden: " +id + ": " + tuplasInsertadas + " tuplas insertadas");
			return new Orden( id_Afiliado, id_Medico,id,servicio);
		}	
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que inserta, de manera transaccional una prestación
	 * Adiciona entradas al log de la aplicación.
	 *
	 * @param idCita el id cita
	 * @param id_Recepcionista el id recepcionistan
	 */
	public void registrarPrestacion(long idCita,String id_Recepcionista,String id_servicio,String id_paciente)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();            
			long tuplasInsertadas = sqlCita.registrarPrestacion(pm, idCita, id_Recepcionista,id_servicio,id_paciente);
			tx.commit();

			log.trace ("Registro prestación: " +idCita + ": " + tuplasInsertadas + " tuplas insertadas");
		}	
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
		}

		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Crea y ejecuta las sentencias SQL para cada tabla de la base de datos - EL ORDEN ES IMPORTANTE .
	 * @return Un arreglo con el número de tuplas borradas de cada tabla.
	 */
	public long[] limpiarEPSAndes()
	{
		return sqlUtil.limpiarEPSAndes(pmf.getPersistenceManager());
	}




}
