package uniandes.isis2304.epsAndes.interfazApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import uniandes.isis2304.epsAndes.negocio.Afiliado;
import uniandes.isis2304.epsAndes.negocio.EPSAndes;
import uniandes.isis2304.epsAndes.negocio.RecepcionistaIPS;
import uniandes.isis2304.epsAndes.negocio.VOCampania;
import uniandes.isis2304.epsAndes.negocio.VOIPS;


/**
 * The Class InterfazEPSAndesApp.
 */
@SuppressWarnings("serial")
public class InterfazEPSAndesAppDemo extends JFrame implements ActionListener
{

	/** Logger para escribir la traza de la ejecución. */

	private static Logger log = Logger.getLogger(InterfazEPSAndesAppDemo.class.getName());

	/** Ruta al archivo de configuraciÃ³n de la interfaz. */
	private static final String CONFIG_INTERFAZ = "./resources/config/interfaceConfigAppDemo.json"; 

	/** Ruta al archivo de configuraciÃ³n de los nombres de tablas de la base de datos. */
	private static final String CONFIG_TABLAS = "./resources/config/TablasBD.json"; 

	/* **********************
	 * 			Atributos
	 ***********************/
	/** Objeto JSON con los nombres de las tablas de la base de datos que se quieren utilizar. */
	private JsonObject tableConfig;

	/**
	 * AsociaciÃ³n a la clase principal del negocio.
	 */
	private EPSAndes epsAndes;


	/* **********************
	 * 			Atributos de interfaz
	 ***********************/
	/**
	 * Objeto JSON con la configuraciÃ³n de interfaz de la app.
	 */
	private JsonObject guiConfig;

	/** Panel de despliegue de interacciÃ³n para los requerimientos. */
	private PanelDatos panelDatos;

	/** MenÃº de la aplicaciÃ³n. */
	private JMenuBar menuEPSAndes;

	/* **********************
	 * 			MÃ©todos
	 ***********************/
	/**
	 * Construye la ventana principal de la aplicaciÃ³n. <br>
	 * <b>post:</b> Todos los componentes de la interfaz fueron inicializados.
	 */
	public InterfazEPSAndesAppDemo( )
	{
		// Carga la configuraciÃ³n de la interfaz desde un archivo JSON
		guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ);

		// Configura la apariencia del frame que contiene la interfaz grÃ¡fica
		configurarFrame ( );

		if (guiConfig != null) 	   
		{
			crearMenu( guiConfig.getAsJsonArray("menuEPSAndes") );
		}

		tableConfig = openConfig ("Tablas BD", CONFIG_TABLAS);
		epsAndes = new EPSAndes(tableConfig);

		String path = guiConfig.get("bannerPath").getAsString();
		panelDatos = new PanelDatos ( );

		setLayout (new BorderLayout());
		add (new JLabel (new ImageIcon (path)), BorderLayout.NORTH );          
		add( panelDatos, BorderLayout.CENTER );        
	}


	/* **********************
	 * 			MÃ©todos de configuraciÃ³n de la interfaz
	 ***********************/
	/**
	 * Lee datos de configuraciÃ³n para la aplicaciÃ³, a partir de un archivo JSON o con valores por defecto si hay errores.
	 * @param tipo - El tipo de configuraciÃ³n deseada
	 * @param archConfig - Archivo Json que contiene la configuraciÃ³n
	 * @return Un objeto JSON con la configuraciÃ³n del tipo especificado
	 * 			NULL si hay un error en el archivo.
	 */
	private JsonObject openConfig (String tipo, String archConfig)
	{
		JsonObject config = null;
		try 
		{
			Gson gson = new Gson( );
			FileReader file = new FileReader (archConfig);
			JsonReader reader = new JsonReader ( file );
			config = gson.fromJson(reader, JsonObject.class);
			log.info ("Se encontrÃ³ un archivo de configuraciÃ³n vÃ¡lido: " + tipo);
		} 
		catch (Exception e)
		{
			//			e.printStackTrace ();
			log.info ("NO se encontrÃ³ un archivo de configuraciÃ³n vÃ¡lido");			
			JOptionPane.showMessageDialog(null, "No se encontrÃ³ un archivo de configuraciÃ³n de interfaz vÃ¡lido: " + tipo, "EPSAndes App", JOptionPane.ERROR_MESSAGE);
		}	
		return config;
	}

	/**
	 * MÃ©todo para configurar el frame principal de la aplicaciÃ³n.
	 */
	private void configurarFrame(  )
	{
		int alto = 0;
		int ancho = 0;
		String titulo = "";	

		if ( guiConfig == null )
		{
			log.info ( "Se aplica configuraciÃ³n por defecto" );			
			titulo = "Superandes APP Default";
			alto = 300;
			ancho = 500;
		}
		else
		{
			log.info ( "Se aplica configuraciÃ³n indicada en el archivo de configuraciÃ³n" );
			titulo = guiConfig.get("title").getAsString();
			alto= guiConfig.get("frameH").getAsInt();
			ancho = guiConfig.get("frameW").getAsInt();
		}

		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setLocation (50,50);
		setResizable( true );
		setBackground( Color.WHITE );

		setTitle( titulo );
		setSize ( ancho, alto);        
	}



	/**
	 * MÃ©todo para crear el menÃº de la aplicaciÃ³n con base em el objeto JSON leÃ­do
	 * Genera una barra de menÃº y los menÃºs con sus respectivas opciones.
	 *
	 * @param jsonMenu - Arreglo Json con los menÃ¹s deseados
	 */
	private void crearMenu(  JsonArray jsonMenu )
	{    	
		// Creación de la barra de menús

		menuEPSAndes = new JMenuBar();       
		for (JsonElement men : jsonMenu)
		{
			// Creación de cada uno de los menús
			JsonObject jom = men.getAsJsonObject(); 


			String menuTitle = jom.get("menuTitle").getAsString(); 
			JsonArray opciones = jom.getAsJsonArray("options");

			JMenu menu = new JMenu( menuTitle);

			for (JsonElement op : opciones)
			{       	

				// Creación de cada una de las opciones del menú
				JsonObject jo = op.getAsJsonObject(); 


				String lb =   jo.get("label").getAsString();

				String event = jo.get("event").getAsString();

				JMenuItem mItem = new JMenuItem( lb );
				mItem.addActionListener( this );
				mItem.setActionCommand(event);

				menu.add(mItem);

			}       
			menuEPSAndes.add( menu );
		}        
		setJMenuBar ( menuEPSAndes );	
	}

	/**
	 * Gets the EPS andes.
	 *
	 * @return the EPS andes
	 */
	public EPSAndes getEPSAndes(){
		return this.epsAndes;
	}

	/**
	 * Demo IPS.
	 */
	public void demoIPS( )
	{
		try 
		{
			String nombre = "IPSAlpes";
			String localizacion = "-18,7679039";
			String id_eps = "EPSAndes";
			boolean errorTipoIPS = false;
			RecepcionistaIPS recepcionistas=new RecepcionistaIPS(nombre, "1005755560", "Carlos Salazar", "ca.salazara@uniandes.edu.co", "RecepcionistaIPS", "C.C");
			VOIPS ips = epsAndes.registrarIPS(localizacion, nombre, recepcionistas, id_eps);
			if( ips == null)
			{
				ips = epsAndes.darIPSPorNombre(nombre);
				errorTipoIPS = true; 
			}

			List<VOIPS> lista = epsAndes.darIPS();
			long eliminados = epsAndes.eliminarIPSNombre(ips.getNombre());
			String resultado = "Demo de creacin y listado de IPS\n\n";
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			if (errorTipoIPS)
			{
				resultado += "*** Exception creando IPS !!\n";
				resultado += "*** Es probable que la IPS ya existiera y hay restriccion de UNICIDAD sobre el nombre de IPS\n";
				resultado += "*** Revise el log de EPSAndes para mas detalles\n";
			}
			resultado += "Adicionado la IPS con nombre: " + nombre + "\n";
			resultado += "\n\n************ Ejecutando la demo ************ \n";
			resultado +=  "\n" + listarIPS (lista);
			resultado += "\n\n************ Limpiando la base de datos ************ \n";
			resultado += eliminados + " IPS eliminados\n";
			resultado += "\n Demo terminada";

			panelDatos.actualizarInterfaz(resultado);
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}


	/**
	 * Demo campana.
	 */
	public void demoCampana( )
	{
		try 
		{
			String nombre="Campania uniAlpes";
			String fechaFin="08-12-18 11:00:00";
			String fechaIni="06-11-18 06:30:00";
			String idOrganizador="93345171";
			epsAndes.registrarOrganizadorCampania(idOrganizador, "Smits", "OrganizadorCampania", "je.hernandezr@uniandes.edu.co", "C.C");
			String nombreIPS= "IPSAndes";
			String localizacion = "-18,7679039";
			String id_eps = "EPSAndes";
			RecepcionistaIPS recepcionistas=new RecepcionistaIPS(nombreIPS, "1005755560", "Carlos Salazar", "ca.salazara@uniandes.edu.co", "RecepcionistaIPS", "C.C");
			epsAndes.registrarIPS(localizacion, nombreIPS, recepcionistas, id_eps);

			epsAndes.registrarServicioDeSaludAPrestar("30", "08:00:00", "06-12-18 08:00:00", "Consultas medicas con medico general", nombreIPS, 12, "Consulta con medico");
			epsAndes.registrarServicioDeSaludAPrestar("30", "07:00:00", "07-12-18 07:00:00", "Consultas medicas con especialistas", nombreIPS, 15, "Remision con un especialista");
			epsAndes.registrarServicioDeSaludAPrestar("45", "11:00:00", "08-12-18 11:00:00", "Examenes de sangre", nombreIPS, 18, "Examen diagnostico");
			epsAndes.registrarServicioDeSaludAPrestar("20", "10:00:30", "08-11-18 10:00:30", "Radiografias", nombreIPS, 16, "Procedimiento medico especializado");
			epsAndes.registrarServicioDeSaludAPrestar("60", "06:30:00", "06-11-18 06:30:00", "Consultas odontologicas", nombreIPS, 13, "Consulta de control");
			epsAndes.registrarServicioDeSaludAPrestar("15", "06:00:00", "09-11-18 06:00:00","Jornadas de vacunacion", nombreIPS, 14, "Procedimiento medico especializado");

			Afiliado x=epsAndes.registrarAfiliado("Campa"+nombre, "Campa"+nombre, "Afiliado", "Campa"+nombre+"@gmail.com", "C.C",fechaIni , "EPSAndes");			
			String id=x.getNumero_Documento();

			for(int i=0;i<7;i++)
			{
				epsAndes.registrarReserva("Consultas medicas con medico general", id, "06-12-18 08:00:00", -10, "08:00:00", nombreIPS);
			}
			for(int i=0;i<6;i++)
			{
				epsAndes.registrarReserva("Consultas medicas con especialistas", id, "07-12-18 07:00:00", -10, "07:00:00", nombreIPS);
			}
			for(int i=0;i<3;i++)
			{
				epsAndes.registrarReserva("Examenes de sangre", id,"08-12-18 11:00:00", -10, "11:00:00", nombreIPS);
			}
			for(int i=0;i<12;i++)
			{
				epsAndes.registrarReserva("Radiografias", id, "08-11-18 10:00:30", -10, "10:00:30", nombreIPS);
			}
			for(int i=0;i<9;i++)
			{
				epsAndes.registrarReserva("Consultas odontologicas", id, "06-11-18 06:30:00", -10, "06:30:00", nombreIPS);
			}
			for(int i=0;i<10;i++)
			{
				epsAndes.registrarReserva("Jornadas de vacunacion", id,  "09-11-18 06:00:00", -10, "06:00:00", nombreIPS);
			}
			VOCampania campania=epsAndes.registrarCampania(nombre, fechaFin, fechaIni, idOrganizador);
			epsAndes.registrarServCamp("Consultas medicas con medico general", nombre, 7, fechaIni, fechaFin);
			epsAndes.registrarServCamp("Consultas medicas con especialistas", nombre, 6, fechaIni, fechaFin);
			epsAndes.registrarServCamp("Examenes de sangre", nombre, 3, fechaIni, fechaFin);
			epsAndes.registrarServCamp("Consultas odontologicas", nombre, 9, fechaIni, fechaFin);
			epsAndes.registrarServCamp("Radiografias", nombre, 12, fechaIni, fechaFin);
			epsAndes.registrarServCamp("Jornadas de vacunacion", nombre, 10, fechaIni, fechaFin);

			boolean errorcampania=false;
			if( campania == null)
			{
				campania = epsAndes.darCampaniaPorNombre(nombre);
				errorcampania = true; 
			}

			List<VOCampania> lista = epsAndes.darCampanias();
			long eliminados = epsAndes.eliminarCampaniaPorNombre(campania.getNombre());
			String resultado = "Demo de creacion y listado de Campanias\n\n";
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			if (errorcampania)
			{
				resultado += "*** Exception creando campania !!\n";
				resultado += "*** Es probable que la campania ya existiera y hay restriccion de UNICIDAD sobre el nombre de campania\n";
				resultado += "*** Revise el log de EPSAndes para mas detalles\n";
			}
			resultado += "Adicionado la Campania con nombre: " + nombre + "\n";
			resultado += "\n\n************ Ejecutando la demo ************ \n";
			resultado +=  "\n" + listarCampania (lista);
			resultado += "\n\n************ Limpiando la base de datos ************ \n";
			resultado += eliminados + " Campanias eliminadas\n";
			resultado += "\n Demo terminada";

			panelDatos.actualizarInterfaz(resultado);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Listar campania.
	 *
	 * @param lista the lista
	 * @return the string
	 */
	private String listarCampania (List<VOCampania> lista) 
	{
		String resp = "Las Campanias existentes son:\n";
		int i = 1;
		for (VOCampania bar : lista)
		{
			resp += i++ + ". " + bar.toString() + "\n";
		}
		return resp;
	}


	/**
	 * Listar IPS.
	 *
	 * @param lista the lista
	 * @return the string
	 */
	private String listarIPS (List<VOIPS> lista) 
	{
		String resp = "Las IPS existentes son:\n";
		int i = 1;
		for (VOIPS bar : lista)
		{
			resp += i++ + ". " + bar.toString() + "\n";
		}
		return resp;
	}
	/* **********************
	 * 			MÃ©todos administrativos
	 ***********************/
	/**
	 * Muestra el log.
	 */
	public void mostrarLogEPSAndes ()
	{

	}

	/**
	 * Muestra el log de datanucleus.
	 */
	public void mostrarLogDatanuecleus ()
	{

	}

	/**
	 * Limpia el contenido del log de superandes
	 * Muestra en el panel de datos la traza de la ejecuciÃ³n.
	 */
	public void limpiarLogSuperandes ()
	{

	}

	/**
	 * Limpia el contenido del log de datanucleus
	 * Muestra en el panel de datos la traza de la ejecuciÃ³n.
	 */
	public void limpiarLogDatanucleus ()
	{
	}

	/**
	 * Abre el archivo dado como parÃ¡metro con la aplicaciÃ³n por defecto del sistema.
	 *
	 * @param nombreArchivo - El nombre del archivo que se quiere mostrar
	 */
	private void mostrarArchivo (String nombreArchivo)
	{
		try
		{
			Desktop.getDesktop().open(new File(nombreArchivo));
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Limpia el contenido de un archivo dado su nombre.
	 *
	 * @param nombreArchivo - El nombre del archivo que se quiere borrar
	 * @return true si se pudo limpiar
	 */
	private boolean limpiarArchivo(String nombreArchivo) 
	{
		BufferedWriter bw;
		try 
		{
			bw = new BufferedWriter(new FileWriter(new File (nombreArchivo)));
			bw.write ("");
			bw.close ();
			return true;
		} 
		catch (IOException e) 
		{
			//			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Genera una cadena para indicar al usuario que hubo un error en la aplicaciÃ³n.
	 *
	 * @param e - La excepciÃ³n generada
	 * @return La cadena con la informaciÃ³n de la excepciÃ³n y detalles adicionales
	 */
	private String generarMensajeError(Exception e) 
	{
		String resultado = "**** Error en la ejecuciÃ³n\n";
		resultado += e.getLocalizedMessage() + ", " + darDetalleException(e);
		resultado += "\n\nRevise datanucleus.log y EPSAndes.log para mÃ¡s detalles";
		return resultado;
	}

	/**
	 * Genera una cadena de caracteres con la descripciÃ³n de la excepcion e, haciendo Ã©nfasis en las excepcionsde JDO.
	 *
	 * @param e - La excepciÃ³n recibida
	 * @return La descripciÃ³n de la excepciÃ³n, cuando es javax.jdo.JDODataStoreException, "" de lo contrario
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



	/* **********************
	 * 			MÃ©todos de la InteracciÃ³n
	 ***********************/
	/**
	 * MÃ©todo para la ejecuciÃ³n de los eventos que enlazan el menÃº con los mÃ©todos de negocio
	 * Invoca al mÃ©todo correspondiente segÃºn el evento recibido.
	 *
	 * @param sEvento - El evento del usuario
	 */
	@Override
	public void actionPerformed(ActionEvent sEvento) {
		// TODO Auto-generated method stub
		String evento = sEvento.getActionCommand( );		
		try 
		{
			Method req = InterfazEPSAndesAppDemo.class.getMethod ( evento );			
			req.invoke ( this );
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	/* **********************
	 * 			Programa principal
	 ***********************/
	/**
	 * Este mÃ©todo ejecuta la aplicaciÃ³n, creando una nueva interfaz.
	 *
	 * @param args Arreglo de argumentos que se recibe por lÃ­nea de comandos
	 */
	public static void main( String[] args )
	{
		BasicConfigurator.configure();
		try
		{
			// Unifica la interfaz para Mac y para Windows.
			UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName( ) );
			InterfazEPSAndesAppDemo interfaz = new InterfazEPSAndesAppDemo( );
			interfaz.setVisible( true );
		}
		catch( Exception e )
		{
			e.printStackTrace( );
		}
	}
}