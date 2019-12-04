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
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
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
import uniandes.isis2304.epsAndes.negocio.VOAfiliado;
import uniandes.isis2304.epsAndes.negocio.VOCampania;
import uniandes.isis2304.epsAndes.negocio.VOCita;
import uniandes.isis2304.epsAndes.negocio.VOIPS;
import uniandes.isis2304.epsAndes.negocio.VOOrden;
import uniandes.isis2304.epsAndes.negocio.VOPrestan;
import uniandes.isis2304.epsAndes.negocio.VORol;
import uniandes.isis2304.epsAndes.negocio.VOUsuario;

/**
 * The Class InterfazEPSAndesApp.
 */
@SuppressWarnings("serial")
public class InterfazEPSAndesApp extends JFrame implements ActionListener
{

	/** Logger para escribir la traza de la ejecución. */

	private static Logger log = Logger.getLogger(InterfazEPSAndesApp.class.getName());

	/** Ruta al archivo de configuraciÃ³n de la interfaz. */
	private static final String CONFIG_INTERFAZ = "./resources/config/interfaceConfigApp.json"; 

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
	public InterfazEPSAndesApp( )
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
	 * Muestra en la interfaz RFC1.
	 */
	public void mostrarServiciosPrestadosEnRango() 
	{
		try {
			JTextField fechaInicio = new JTextField();
			JTextField fechaFin = new JTextField();



			Object message[] = {
					"Ingrese la fecha de inicio: ", fechaInicio,
					"Ingrese la fecha fin: ", fechaFin,
			};

			int option = JOptionPane.showConfirmDialog (this, message, "Mostrar servicios en rango", JOptionPane.OK_CANCEL_OPTION);
			if(option == JOptionPane.OK_OPTION) {
				if(!fechaInicio.getText().toString().equals("") && !fechaFin.getText().toString().equals("") )
				{

					String fInicio=fechaInicio.getText().toString();
					String fFin=fechaFin.getText().toString();

					JOptionPane.showMessageDialog(this, "Se realizó la consulta con exito!", "Consulta", JOptionPane.INFORMATION_MESSAGE);
					String resultado = "";
					List<Object[]> lista=epsAndes.darIPSYCantidadServiciosOfrece(fInicio, fFin);
					for (int i=0;i<lista.size();i++) {
						resultado+="IPS: " +(String)lista.get(i)[0]+" Cantidad servicios: "+((BigDecimal)lista.get(i)[1]).intValue()+"\n";
					}
					resultado += "\n OperaciÃ³n terminada";
					panelDatos.actualizarInterfaz(resultado);
				}
				else
				{
					JOptionPane.showMessageDialog(this, "Se deben llenar todos los campos.", "Error consultando", JOptionPane.ERROR_MESSAGE);

				}
			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error consultando", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Muestra en la interfaz RFC2.
	 */
	
	public void reqC9()
	{
		try {

			JTextField servicios = new JTextField();
			JTextField tipos = new JTextField();
			JTextField fechaInicial = new JTextField();
			JTextField fechaFinal = new JTextField();
			JTextField IPS = new JTextField();
			JComboBox<String>comboOrdenamiento =new JComboBox<String>();
			comboOrdenamiento.addItem("Nombre");
			comboOrdenamiento.addItem("Email");
			comboOrdenamiento.addItem("Fecha Nacimiento");
			comboOrdenamiento.addItem("N�mero de documento");
			JComboBox<String> comboAgrupamiento = new JComboBox<String>();
			comboAgrupamiento.addItem("Tipo de documento");
			comboAgrupamiento.addItem("Fecha Nacimiento");
			
			
			Object message[] = {
					"Ingrese los servicios de la forma ('A','B','C')  : ", servicios ,
					"Ingrese los tipos de la forma ('A','B','C'): ", tipos,
					"Ingrese la fecha incial (dd/mm/yyyy hh:mi:ss): ", fechaInicial,
					"Ingrese la fecha final (dd/mm/yyyy hh:mi:ss): ", fechaFinal,
					"Ingrese los nombres de la IPS de la forma ('A','B','C'): ", IPS , 
					"Seleccione 1 ordenamiento" , comboOrdenamiento , 
					"O Seleccione 1 agrupamiento" , comboAgrupamiento 
			};

			int option = JOptionPane.showConfirmDialog (this, message, "Registrar cita", JOptionPane.OK_CANCEL_OPTION);
			if(option == JOptionPane.OK_OPTION) {
				if(!servicios.getText().toString().equals("") &&!tipos.getText().toString().equals("") &&!fechaInicial.getText().toString().equals("") &&!IPS.getText().toString().equals("") && !fechaFinal.getText().toString().equals("") )
				{
					String pServicios = servicios.getText().toString();
					String pTipos =tipos.getText().toString();
					String pFechaInicial =fechaInicial.getText().toString();
					String PFechaFinal = fechaFinal.getText().toString();
					String pIPS =IPS.getText().toString();
					String pOrdenamiento = comboOrdenamiento.getSelectedItem().toString();
					String pAgrupamiento = comboAgrupamiento.getSelectedItem().toString();
					
					JOptionPane.showMessageDialog(this, "Se realiza la consulta con exito!", "Consulta", JOptionPane.INFORMATION_MESSAGE);
					String resultado = "";
					
					List<Object[]> lista =epsAndes.reqC9(pServicios, pTipos , pFechaInicial , PFechaFinal , pIPS ,  pOrdenamiento , pAgrupamiento);
					for (int i=0;i<lista.size();i++) {
						
						resultado += "Email: " + (String)lista.get(i)[0] +
									"Nombre: " + (String)lista.get(i)[1] +
									"Numero de documento: " + (String)lista.get(i)[2] +
									"Rol: " + (String)lista.get(i)[3] + 
									"Tipo documento: " + (String)lista.get(i)[4] + 
									"Fecha nacimiento: " + (String)lista.get(i)[5] +"\n";
					}
					resultado += "\n Operacion terminada";
					panelDatos.actualizarInterfaz(resultado);

					
				}
				else
				{
					JOptionPane.showMessageDialog(this, "Se deben llenar todos los campos.", "Error registrando afiliado", JOptionPane.ERROR_MESSAGE);

				}


			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error en Req c 9", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}
	
	public void reqC10()
	{
		try {

			JTextField servicios = new JTextField();
			JTextField tipos = new JTextField();
			JTextField fechaInicial = new JTextField();
			JTextField fechaFinal = new JTextField();
			JTextField IPS = new JTextField();
			JComboBox<String>comboOrdenamiento =new JComboBox<String>();
			comboOrdenamiento.addItem("Nombre");
			comboOrdenamiento.addItem("Email");
			comboOrdenamiento.addItem("Fecha Nacimiento");
			comboOrdenamiento.addItem("N�mero de documento");
			JComboBox<String> comboAgrupamiento = new JComboBox<String>();
			comboAgrupamiento.addItem("Tipo de documento");
			comboAgrupamiento.addItem("Fecha Nacimiento");
			
			
			Object message[] = {
					"Ingrese los servicios de la forma ('A','B','C')  : ", servicios ,
					"Ingrese los tipos de la forma ('A','B','C'): ", tipos,
					"Ingrese la fecha incial (dd/mm/yyyy hh:mi:ss): ", fechaInicial,
					"Ingrese la fecha final (dd/mm/yyyy hh:mi:ss): ", fechaFinal,
					"Ingrese los nombres de la IPS de la forma ('A','B','C'): ", IPS , 
					"Seleccione 1 ordenamiento" , comboOrdenamiento , 
					"O Seleccione 1 agrupamiento" , comboAgrupamiento 
			};

			int option = JOptionPane.showConfirmDialog (this, message, "Registrar cita", JOptionPane.OK_CANCEL_OPTION);
			if(option == JOptionPane.OK_OPTION) {
				if(!servicios.getText().toString().equals("") &&!tipos.getText().toString().equals("") &&!fechaInicial.getText().toString().equals("") &&!IPS.getText().toString().equals("") && !fechaFinal.getText().toString().equals("") )
				{
					String pServicios = servicios.getText().toString();
					String pTipos =tipos.getText().toString();
					String pFechaInicial =fechaInicial.getText().toString();
					String PFechaFinal = fechaFinal.getText().toString();
					String pIPS =IPS.getText().toString();
					String pOrdenamiento = comboOrdenamiento.getSelectedItem().toString();
					String pAgrupamiento = comboAgrupamiento.getSelectedItem().toString();
					
					JOptionPane.showMessageDialog(this, "Se realiza la consulta con exito!", "Consulta", JOptionPane.INFORMATION_MESSAGE);
					String resultado = "";
					
					List<Object[]> lista =epsAndes.reqC10(pServicios, pTipos , pFechaInicial , PFechaFinal , pIPS ,  pOrdenamiento , pAgrupamiento);
					for (int i=0;i<lista.size();i++) {
						
						resultado += "Identificacion: " + (String)lista.get(i)[0] +
								"Nombre: " + (String)lista.get(i)[1] +
								"Email: " + (String)lista.get(i)[2] +
								"Tipo de coumento: " + (String)lista.get(i)[3] + 
								"Fecha nacimiento: " + (String)lista.get(i)[4] +"\n";
						
					}
					resultado += "\n Operacion terminada";
					panelDatos.actualizarInterfaz(resultado);

					
				}
				else
				{
					JOptionPane.showMessageDialog(this, "Se deben llenar todos los campos.", "Error registrando afiliado", JOptionPane.ERROR_MESSAGE);

				}


			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error en Req C 10", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}
	
	public void reqC11()
	{
		try {

			Object message[] = {
					"Requerimiento C 11"
			};

			int option = JOptionPane.showConfirmDialog (this, message, "Consultar funcionamiento", JOptionPane.OK_CANCEL_OPTION);
			if(option == JOptionPane.OK_OPTION) {
				JOptionPane.showMessageDialog(this, "Se realiza la consulta con exito!", "Consulta", JOptionPane.INFORMATION_MESSAGE);
				String resultado = "";
				List<Object[]> lista=epsAndes.reqC11();
				
				for (int i=0;i<lista.size();i++) {
					
					resultado+= "Semana : " + (String)lista.get(i)[0] + " Tipos m�s usados :" + (String)lista.get(i)[1] + "Cantidad tipo m�s usado :" + (BigDecimal)lista.get(i)[2] + " Tipos menos usados :" + (String)lista.get(i)[3] +
												"Cantidad tipo menos usado: " + (BigDecimal)lista.get(i)[4] + " Servicios m�s usados : " + (String)lista.get(i)[5] + "Cantidad servicio m�s usado" + (BigDecimal)lista.get(i)[6] + 
												"Servicio menos usado: " + (String)lista.get(i)[7] + "Cantidad servicio menos usado  : " + (BigDecimal)lista.get(i)[8] + "Ips m�s usadas : " + (String)lista.get(i)[9] + "Cantidad IPS m�s usada : " + (BigDecimal)lista.get(i)[10] +
												"Ips menos usada : " + (String)lista.get(i)[11] + "Cantidad Ips menos usada : " + (BigDecimal)lista.get(i)[12] + "Afiliado m�s usador : " + (String)lista.get(i)[13] + "Cantidad usos : " + (BigDecimal)lista.get(i)[14] + "Afiliados que no usaron servicios edsa semana" + (BigDecimal)lista.get(i)[15] 
														; 
							
					
				}
				resultado += "\n OperaciÃ³n terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error consultando", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}
	
	public void reqC12()
	{
		try {

			Object message[] = {
					"Requerimiento C 12 "
			};

			int option = JOptionPane.showConfirmDialog (this, message, "Consultar afiliados costosos", JOptionPane.OK_CANCEL_OPTION);
			if(option == JOptionPane.OK_OPTION) {
				JOptionPane.showMessageDialog(this, "Se realiza la consulta con exito!", "Consulta", JOptionPane.INFORMATION_MESSAGE);
				String resultado = "";
				List<Object[]> lista=epsAndes.reqC12();
				
				for (int i=0;i<lista.size();i++) {
				

					resultado += "Nombre: " + (String)lista.get(i)[0] +
							"Email: " + (String)lista.get(i)[1] +
							"Identificaci�n: " + (String)lista.get(i)[2] +
							"Raz�n: " + (String)lista.get(i)[3] + 
							"Citas solicitadas: " + (String)lista.get(i)[4] +
							"Servicios distintos solicitados: " + (String)lista.get(i)[5] +
							"Hospitalizaciones: " + (String)lista.get(i)[6] 	+"\n";
				
				}
				resultado += "\n OperaciÃ³n terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error consultando", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}


	}
	public void mostrarTop20() 
	{
		try {
			JTextField fechaInicio = new JTextField();
			JTextField fechaFin = new JTextField();



			Object message[] = {
					"Ingrese la fecha de inicio: ", fechaInicio,
					"Ingrese la fecha fin: ", fechaFin,
			};

			int option = JOptionPane.showConfirmDialog (this, message, "Mostrar 20 servicios más solicitados", JOptionPane.OK_CANCEL_OPTION);
			if(option == JOptionPane.OK_OPTION) {
				if(!fechaInicio.getText().toString().equals("") && !fechaFin.getText().toString().equals("") )
				{

					String fInicio=fechaInicio.getText().toString();
					String fFin=fechaFin.getText().toString();

					JOptionPane.showMessageDialog(this, "Se realizó la consulta con exito!", "Consulta", JOptionPane.INFORMATION_MESSAGE);
					String resultado = "";
					List<Object[]> lista=epsAndes.dar20ServiciosMasSolicitados(fInicio, fFin);
					for (int i=0;i<lista.size();i++) {
						resultado+="Servicio: " +(String)lista.get(i)[0]+" Cantidad solicitudes: "+((BigDecimal)lista.get(i)[1]).intValue()+"\n";
					}
					resultado += "\n OperaciÃ³n terminada";
					panelDatos.actualizarInterfaz(resultado);
				}
				else
				{
					JOptionPane.showMessageDialog(this, "Se deben llenar todos los campos.", "Error consultando", JOptionPane.ERROR_MESSAGE);

				}
			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error consultando", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Muestra en la interfaz RFC3.
	 */
	public void mostrarIndiceDeUso() 
	{
		try {

			Object message[] = {
					"Consultar indice de uso"
			};

			int option = JOptionPane.showConfirmDialog (this, message, "Mostrar 20 servicios más solicitados", JOptionPane.OK_CANCEL_OPTION);
			if(option == JOptionPane.OK_OPTION) {
				JOptionPane.showMessageDialog(this, "Se realizó la consulta con exito!", "Consulta", JOptionPane.INFORMATION_MESSAGE);
				String resultado = "";
				List<Object[]> lista=epsAndes.darIndiceDeUso();
				for (int i=0;i<lista.size();i++) {
					resultado+="Servicio: " +(String)lista.get(i)[1]+" Indice de uso: "+((BigDecimal)lista.get(i)[0]).intValue()+"\n";
				}
				resultado += "\n OperaciÃ³n terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error consultando", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Mostrar servicios por caracteristica.
	 */
	public void mostrarServiciosPorCaracteristica() 
	{
		try {
			JTextField fechaInicio = new JTextField();
			JTextField fechaFin = new JTextField();
			JTextField vecesJ = new JTextField();
			JTextField idR = new JTextField();
			JComboBox<String> combo = new JComboBox<String>();
			combo.addItem("");
			combo.addItem("Consulta con medico");
			combo.addItem("Consulta de urgencias");
			combo.addItem("Remision con un especialista");
			combo.addItem("Consulta de control");
			combo.addItem("Examen diagnostico");
			combo.addItem("Terapia");
			combo.addItem("Procedimiento medico especializado");
			combo.addItem("Hospitalizacion");



			Object message[] = {
					"Ingrese la fecha de inicio: ", fechaInicio,
					"Ingrese la fecha fin: ", fechaFin,
					"Ingrese la cantidad de veces: ",vecesJ,
					"Ingrese el id del recepcionista: ",vecesJ,
					"Seleccione el tipo: ",combo,

			};

			int option = JOptionPane.showConfirmDialog (this, message, "Mostrar 20 servicios más solicitados", JOptionPane.OK_CANCEL_OPTION);
			if(option == JOptionPane.OK_OPTION) {
				String fInicio=fechaInicio.getText().toString();
				String fFin=fechaFin.getText().toString();
				int veces=vecesJ.getText().toString().isEmpty()?-1:Integer.parseInt(vecesJ.getText().toString());
				String idRecepcionista=idR.getText().toString();
				String tipo=combo.getSelectedItem().toString();
				JOptionPane.showMessageDialog(this, "Se realizó la consulta con exito!", "Consulta", JOptionPane.INFORMATION_MESSAGE);
				String resultado = "";
				List<Object[]> lista=epsAndes.mostrarServiciosPorCaracteristicas(idRecepcionista, tipo, veces, fInicio, fFin);
				for (int i=0;i<lista.size();i++) {
					Object[] actual=lista.get(i);
					resultado+="Nombre: "+ actual[1]+" Tipo: "+actual[2]+" Cuenta: "+actual[0]+ "\n";
				}
				resultado += "\n OperaciÃ³n terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Se deben llenar todos los campos.", "Error consultando", JOptionPane.ERROR_MESSAGE);

			}

		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error consultando", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Mostrar uso afiliado en rango.
	 */
	public void mostrarUsoAfiliadoEnRango()
	{
		try {
			JTextField fechaInicio = new JTextField();
			JTextField fechaFin = new JTextField();
			JTextField idA = new JTextField();



			Object message[] = {
					"Ingrese la fecha de inicio: ", fechaInicio,
					"Ingrese la fecha fin: ", fechaFin,
					"Ingrese la cédula del afiliado:",idA,
			};

			int option = JOptionPane.showConfirmDialog (this, message, "Mostrar servicios en rango", JOptionPane.OK_CANCEL_OPTION);
			if(option == JOptionPane.OK_OPTION) {
				if(!idA.getText().toString().equals("") && !fechaInicio.getText().toString().equals("") && !fechaFin.getText().toString().equals("") )
				{

					String fInicio=fechaInicio.getText().toString();
					String fFin=fechaFin.getText().toString();
					String idAfiliado=idA.getText().toString();
					JOptionPane.showMessageDialog(this, "Se realizó la consulta con exito!", "Consulta", JOptionPane.INFORMATION_MESSAGE);
					String resultado = "";
					List<Object[]> lista=epsAndes.utilizacionPorAfiliado(idAfiliado, fInicio, fFin);
					for (int i=0;i<lista.size();i++) {
						resultado+="Id afiliado: "+ idAfiliado +" Nombre del servicio: " +(String)lista.get(i)[1]+" Utilización: "+((BigDecimal)lista.get(i)[0]).intValue()+"\n";
					}
					resultado += "\n OperaciÃ³n terminada";
					panelDatos.actualizarInterfaz(resultado);
				}
				else
				{
					JOptionPane.showMessageDialog(this, "Se deben llenar todos los campos.", "Error consultando", JOptionPane.ERROR_MESSAGE);

				}
			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error consultando", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}	}

	/**
	 * Registrar roles usuario.
	 */
	public void registrarRolesUsuario()
	{
		try
		{
			JComboBox<String> combo = new JComboBox<String>();
			combo.addItem("Afiliado");
			combo.addItem("RecepcionistaIPS");
			combo.addItem("Medico");
			combo.addItem("AdminDatosEPS");
			combo.addItem("GerenteEPS");
			combo.addItem("OrganizadorCampania");
			Object message[] = {
					"Ingrese el rol: ", combo,
			};

			int option = JOptionPane.showConfirmDialog (this, message, "Registrar ROl", JOptionPane.OK_CANCEL_OPTION);
			if(option == JOptionPane.OK_OPTION)
			{
				VORol rol = epsAndes.registrarRol(combo.getSelectedItem().toString());
				if(rol != null) {
					JOptionPane.showMessageDialog(this, "Se registro el rol con exito!", "Registro de rol exitoso", JOptionPane.INFORMATION_MESSAGE);
					String resultado = "En registro ips \n\n";
					resultado += "rol registrado exitosamente: " + rol;
					resultado += "\n OperaciÃ³n terminada";
					panelDatos.actualizarInterfaz(resultado);
				}
			}
			else {
				JOptionPane.showMessageDialog(this, "Se debe llenar el tipo de rol.", "Error registrando rol", JOptionPane.ERROR_MESSAGE);
			}


		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error registrando rol", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}


	/**
	 * Registrar prestacion afiliado.
	 */
	public void registrarPrestacionAfiliado()
	{

		try {
			JTextField Id_Cita = new JTextField();
			JTextField Id_Recepcionista = new JTextField();
			JTextField Id_Servicio = new JTextField();
			JTextField Id_Paciente = new JTextField();


			Object message[] = {
					"Ingrese el id de la cita: ", Id_Cita,
					"Ingrese el id del recepcionista: ", Id_Recepcionista,
					"Ingrese el id del servicio: ", Id_Servicio,
					"Ingrese el id del paciente: ", Id_Paciente,

			};

			int option = JOptionPane.showConfirmDialog (this, message, "Registrar prestación a afiliado", JOptionPane.OK_CANCEL_OPTION);
			if(option == JOptionPane.OK_OPTION) {
				if(!Id_Paciente.getText().toString().equals("")&&!Id_Servicio.getText().toString().equals("")&&!Id_Recepcionista.getText().toString().equals("") && !Id_Cita.getText().toString().equals("") )
				{
					long idCita=Long.parseLong(Id_Cita.getText().toString());
					String id_Recepcionista=Id_Recepcionista.getText().toString();
					String id_servicio=Id_Servicio.getText().toString();
					String id_paciente=Id_Paciente.getText().toString();
					epsAndes.registrarPrestacion(idCita, id_Recepcionista,id_servicio,id_paciente);
					JOptionPane.showMessageDialog(this, "Se registró la prestación del con exito!", "Registro de rol exitoso", JOptionPane.INFORMATION_MESSAGE);
					String resultado = "";
					resultado += "\n OperaciÃ³n terminada";
					panelDatos.actualizarInterfaz(resultado);
				}
				else
				{
					JOptionPane.showMessageDialog(this, "Se deben llenar todos los campos.", "Error registrando afiliado", JOptionPane.ERROR_MESSAGE);

				}
			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error registrando Afiliado", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	/**
	 * Registrar usuario.
	 */
	public void registrarUsuario()
	{
		try {
			JTextField NumeroDocumento = new JTextField();
			JTextField Nombre = new JTextField();
			JComboBox<String> combo = new JComboBox<String>();
			combo.addItem("C.C");
			combo.addItem("T.I");
			combo.addItem("R.C");
			combo.addItem("C.E");
			combo.addItem("Pasaporte");

			JTextField textoEmail = new JTextField();

			JComboBox<String> combo2 = new JComboBox<String>();
			combo2.addItem("Afiliado");
			combo2.addItem("RecepcionistaIPS");
			combo2.addItem("Medico");
			combo2.addItem("AdminDatosEPS");
			combo2.addItem("GerenteEPS");

			Object message[] = {
					"Ingrese el numero de documento : ", NumeroDocumento,
					"Ingrese el nombre " , Nombre,
					"Ingrese su tipo docuemnto: ", combo,
					"Ingrese un correo electronico: ", textoEmail,
					"Escoga el rol de usuario: ", combo2,
			};
			int option = JOptionPane.showConfirmDialog (this, message, "Registrar Usuario", JOptionPane.OK_CANCEL_OPTION);
			if(option == JOptionPane.OK_OPTION)
			{
				if(!Nombre.getText().toString().equals("") && !textoEmail.getText().toString().equals("") && !NumeroDocumento.getText().toString().equals(""))
				{
					String identificacion =NumeroDocumento.getText().toString();
					String nombre = Nombre.getText().toString();
					String tipoCC = combo.getSelectedItem().toString();
					String email = textoEmail.getText().toString();
					String tipo2 = combo2.getSelectedItem().toString();

					VOUsuario usuario = epsAndes.registrarUsuario(identificacion, nombre, email, tipo2, tipoCC);
					if(usuario != null) {
						JOptionPane.showMessageDialog(this, "Se registro el usuario con exito!", "Registro de usuario exitoso", JOptionPane.INFORMATION_MESSAGE);
						String resultado = "En registro usuario \n\n";
						resultado += "usuario registrado exitosamente: " + usuario;
						resultado += "\n OperaciÃ³n terminada";
						panelDatos.actualizarInterfaz(resultado);
					}

					else {
						System.out.println("el usario llega nu");
					}
				}
				else {
					JOptionPane.showMessageDialog(this, "Se deben llenar todos los campos.", "Error registrando usuario", JOptionPane.ERROR_MESSAGE);
				}

			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error registrando usuario", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}


	}

	/**
	 * Registrar IPS.
	 */
	public void registrarIPS()
	{
		try {
			JTextField nombre = new JTextField();
			JTextField localizacion = new JTextField();

			JTextField NumeroDocumento = new JTextField();
			JTextField NombreRecepcionista = new JTextField();
			JComboBox<String> combo = new JComboBox<String>();
			combo.addItem("C.C");
			combo.addItem("T.I");
			combo.addItem("R.C");
			combo.addItem("C.E");
			combo.addItem("Pasaporte");

			JTextField textoEmail = new JTextField();


			Object message[] = {
					"Ingrese el nombre de la ips: ", nombre,
					"Ingrese la localizacion de la ips: ", localizacion,
					"Ingrese el número de documento del recepcionista: ", NumeroDocumento,
					"Ingrese el nombre del recepcionista: ", NombreRecepcionista,
					"Escoja el tipo de documento: ", combo,
					"Ingrese el email del recepcionista: ", textoEmail,
			};
			int option = JOptionPane.showConfirmDialog (this, message, "Registrar IPS", JOptionPane.OK_CANCEL_OPTION);
			if(option == JOptionPane.OK_OPTION) {
				if(!textoEmail.getText().toString().equals("") && !NombreRecepcionista.getText().toString().equals("") && !NumeroDocumento.getText().toString().equals("") && !nombre.getText().toString().equals("") && !localizacion.getText().toString().equals(""))
				{

					String Nombre = nombre.getText().toString();

					String Localizacion = localizacion.getText().toString();

					String NombreEPS = "EPSAndes";

					String Numerodocumento=NumeroDocumento.getText().toString();

					String Nombrerecepcionista=NombreRecepcionista.getText().toString();
					String tipoDocumento=combo.getSelectedItem().toString();
					String email=textoEmail.getText().toString();

					RecepcionistaIPS recepcionista=new RecepcionistaIPS(Nombre, Numerodocumento, Nombrerecepcionista, email, "RecepcionistaIPS", tipoDocumento);

					VOIPS ips = epsAndes.registrarIPS( Localizacion, Nombre,recepcionista,NombreEPS);
					if(ips != null) {
						JOptionPane.showMessageDialog(this, "Se registro la ips con exito!", "Registro de ips exitoso", JOptionPane.INFORMATION_MESSAGE);
						String resultado = "En registro ips \n\n";
						resultado += "ips registrado exitosamente: " + ips;
						resultado += "\n OperaciÃ³n terminada";
						panelDatos.actualizarInterfaz(resultado);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(this, "Se deben llenar todos los campos.", "Error registrando usuario", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error registrando IPS", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Registrar afiliado.
	 */
	public void registrarAfiliado( )
	{
		try {

			JTextField fecha = new JTextField();
			JTextField NumeroDocumento = new JTextField();
			JTextField Nombre = new JTextField();
			JComboBox<String> combo = new JComboBox<String>();
			combo.addItem("C.C");
			combo.addItem("T.I");
			combo.addItem("R.C");
			combo.addItem("C.E");
			combo.addItem("Pasaporte");
			JTextField textoEmail = new JTextField();


			Object message[] = {
					"Ingrese su fecha de nacimiento: ", fecha,
					"Ingrese su número de documento",NumeroDocumento,
					"Ingrese su nombre",Nombre,
					"Seleccione su tipo de documento",combo,
					"Ingrese su email",textoEmail,
			};

			int option = JOptionPane.showConfirmDialog (this, message, "Registrar afiliado", JOptionPane.OK_CANCEL_OPTION);
			if(option == JOptionPane.OK_OPTION) {
				if( !textoEmail.getText().toString().equals("") && !Nombre.getText().toString().equals("") && !NumeroDocumento.getText().toString().equals("") && !fecha.getText().toString().equals("") )
				{
					String fechaNacimiento = fecha.getText().toString();
					String numero_Documento=NumeroDocumento.getText().toString();
					String nombre=Nombre.getText().toString();
					String email=textoEmail.getText().toString();
					String tipo_Documento=combo.getSelectedItem().toString();
					String eps="EPSAndes";

					VOAfiliado afiliado = epsAndes.registrarAfiliado(numero_Documento, nombre, "Afiliado", email, tipo_Documento, fechaNacimiento, eps);

					if(afiliado != null) {
						JOptionPane.showMessageDialog(this, "Se registro el afiliado con exito!", "Registro de usuario exitoso", JOptionPane.INFORMATION_MESSAGE);
						String resultado = "En registro afiliado \n\n";
						resultado += "afiliado registrado exitosamente: " + afiliado;
						resultado += "\n OperaciÃ³n terminada";
						panelDatos.actualizarInterfaz(resultado);
					}
					// Agrego
				}
				else
				{
					JOptionPane.showMessageDialog(this, "Se deben llenar todos los campos.", "Error registrando afiliado", JOptionPane.ERROR_MESSAGE);

				}


			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error registrando Afiliado", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}


	/**
	 * Cancelar servicios campania.
	 */
	public void cancelarServiciosCampania() 
	{
		try {

			JTextField Servicio = new JTextField();
			JTextField campanias = new JTextField();


			Object message[] = {
					"Ingrese el nombre del servicio: ", Servicio,
					"Ingrese el nombre de la campania: ", campanias,
			};

			int option = JOptionPane.showConfirmDialog (this, message, "Cancelar Campania", JOptionPane.OK_CANCEL_OPTION);
			if(option == JOptionPane.OK_OPTION) {
				if(!Servicio.getText().toString().equals("") && !campanias.getText().toString().equals("") )
				{

					String servicio=Servicio.getText().toString();
					String campania=campanias.getText().toString();
					epsAndes.cancelarServicioCampania(campania, servicio);

				}
				else
				{
					JOptionPane.showMessageDialog(this, "Se deben llenar todos los campos.", "Error cancelando Campania", JOptionPane.ERROR_MESSAGE);

				}


			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error cancelando Campania", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Registrar orden.
	 */
	public void registrarOrden() 
	{
		try {

			JTextField Servicio = new JTextField();
			JTextField Id_Afiliado = new JTextField();
			JTextField Id_Medico = new JTextField();


			Object message[] = {
					"Ingrese el nombre del servicio: ", Servicio,
					"Ingrese el id del afiliado: ", Id_Afiliado,
					"Ingrese el id del médico: ", Id_Medico,
			};

			int option = JOptionPane.showConfirmDialog (this, message, "Registrar orden", JOptionPane.OK_CANCEL_OPTION);
			if(option == JOptionPane.OK_OPTION) {
				if(!Id_Medico.getText().toString().equals("") &&!Servicio.getText().toString().equals("") && !Id_Afiliado.getText().toString().equals("") )
				{

					String servicio=Servicio.getText().toString();
					String id_Afiliado=Id_Afiliado.getText().toString();
					String id_Medico=Id_Medico.getText().toString();

					VOOrden orden=epsAndes.registrarOrdenDeServicio(servicio, id_Afiliado, id_Medico);

					if(orden != null) {
						JOptionPane.showMessageDialog(this, "Se registro la orden con exito!", "Registro de usuario exitoso", JOptionPane.INFORMATION_MESSAGE);
						String resultado = "En registro orden \n\n";
						resultado += "orden registrada exitosamente: " + orden;
						resultado += "\n OperaciÃ³n terminada";
						panelDatos.actualizarInterfaz(resultado);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(this, "Se deben llenar todos los campos.", "Error registrando afiliado", JOptionPane.ERROR_MESSAGE);

				}


			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error registrando Afiliado", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Registrar cita.
	 */
	public void registrarCita()
	{
		try {

			JTextField Paciente = new JTextField();
			JTextField Fecha = new JTextField();
			JTextField Servicio = new JTextField();
			JTextField Hora = new JTextField();
			JTextField IdIPS = new JTextField();
			JTextField idOrden=new JTextField();

			Object message[] = {
					"Ingrese el numero de documento del paciente: ", Paciente,
					"Ingrese la fecha de la cita: ", Fecha,
					"Ingrese el nombre del servicio: ", Servicio,
					"Ingrese la hora de la cita: ", Hora,
					"Ingrese el nombre de la IPS: ", IdIPS,
					"Ingrese el id de la orden, si no tiene orden por favor escriba 0",idOrden
			};

			int option = JOptionPane.showConfirmDialog (this, message, "Registrar cita", JOptionPane.OK_CANCEL_OPTION);
			if(option == JOptionPane.OK_OPTION) {
				if(!idOrden.getText().toString().equals("") &&!IdIPS.getText().toString().equals("") &&!Hora.getText().toString().equals("") &&!Servicio.getText().toString().equals("") &&!Paciente.getText().toString().equals("") && !Fecha.getText().toString().equals("") )
				{
					String fecha = Fecha.getText().toString();
					String servicio=Servicio.getText().toString();
					String paciente=Paciente.getText().toString();
					String hora=Hora.getText().toString();
					String idIPS=IdIPS.getText().toString();
					Long id=Long.parseLong(idOrden.getText().toString());

					VOCita cita=epsAndes.registrarReserva(servicio, paciente, fecha, id, hora, idIPS);
					if(cita != null) {
						JOptionPane.showMessageDialog(this, "Se registro la cita con exito!", "Registro de usuario exitoso", JOptionPane.INFORMATION_MESSAGE);
						String resultado = "En registro cita \n\n";
						resultado += "cita registrada exitosamente: " + cita;
						resultado += "\n OperaciÃ³n terminada";
						panelDatos.actualizarInterfaz(resultado);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(this, "Se deben llenar todos los campos.", "Error registrando afiliado", JOptionPane.ERROR_MESSAGE);

				}


			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error registrando Afiliado", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}


	/**
	 * Registrar campania.
	 */
	public void registrarCampania()
	{
		try {

			JTextField Nombre = new JTextField();
			JTextField cantidadServicios = new JTextField();
			JTextField idOrganizador = new JTextField();
			Object message[] = {
					"Ingrese el nombre de la campana: ", Nombre,
					"Ingrese la cantidad de servicios: ", cantidadServicios,
					"Ingrese el documento del organizador: ", idOrganizador,
			};

			int option = JOptionPane.showConfirmDialog (this, message, "Registrar cita", JOptionPane.OK_CANCEL_OPTION);
			if(option == JOptionPane.OK_OPTION) {
				if(!idOrganizador.getText().toString().equals("") &&!Nombre.getText().toString().equals("") && !cantidadServicios.getText().toString().equals("") )
				{
					String servicios = cantidadServicios.getText().toString();
					String organizador=idOrganizador.getText().toString();
					String nombre=Nombre.getText().toString();
					String fechaInicio="";
					String fechaFin="";
					List<String[]> anadirFin= new LinkedList<String[]>();
					Afiliado x=null;
					for(int i=0;i<Integer.parseInt(servicios);i++)
					{
						JComboBox<String> nombreSer=new JComboBox<String>();
						nombreSer.addItem("Consultas medicas con medico general");
						nombreSer.addItem("Consultas medicas con especialistas");
						nombreSer.addItem("Examenes de sangre");
						nombreSer.addItem("Radiografias");
						nombreSer.addItem("Consultas odontologicas");
						nombreSer.addItem("Jornadas de vacunacion");
						JTextField capacidad=new JTextField();
						JTextField fechaInicServ=new JTextField();
						JTextField fechaFinServ=new JTextField();

						Object message2[] = {
								"Seleccione el nombre del servicio: ", nombreSer,
								"Ingrese la capacidad del servicio: ", capacidad,
								"Ingrese la fecha de inicio: ", fechaInicServ,
								"Ingrese la fecha de fin: ",fechaFinServ,
						};
						int option2 = JOptionPane.showConfirmDialog (this, message2, "Registrar cita", JOptionPane.OK_CANCEL_OPTION);
						if(option2 == JOptionPane.OK_OPTION) {
							if(!nombreSer.getSelectedItem().toString().equals("") && !capacidad.toString().equals("") && !fechaInicServ.getText().equals("") && !fechaFinServ.getText().contentEquals(""))
							{

								String serv=nombreSer.getSelectedItem().toString();
								int capacidadS=Integer.parseInt(capacidad.getText().toString());
								String fechaI=fechaInicServ.getText().toString();
								String fechaF=fechaFinServ.getText().toString();
								String[] tupla=new String[4];
								tupla[0]=serv;
								tupla[1]=capacidadS+"";
								tupla[2]=fechaI;
								tupla[3]=fechaF;
								anadirFin.add(tupla);

								if (i==0) {
									fechaInicio=fechaI;
									 x=epsAndes.registrarAfiliado("Campa"+nombre, "Campa"+nombre, "Afiliado", "Campa"+nombre+"@gmail.com", "C.C",fechaI , "EPSAndes");			
								}
								if(i==Integer.parseInt(servicios)-1)
									fechaFin=fechaF;
								if(capacidadS<epsAndes.darCantidadServicioEnRango(serv, fechaI, fechaF)){
									String id=x.getNumero_Documento();
									List<Object[]>lista=epsAndes.darInfoServicioEnRango(serv, fechaI, fechaF);
									for (int m=0;m<capacidadS;m++) {
										Object[] object=lista.get(m);
										int capacidadP=((BigDecimal)object[5]).intValue();


										if(capacidadS>capacidadP){
											for(int j=0;j<capacidadP-3;j++) 
											{
												epsAndes.registrarReserva(serv, id, (String)object[2], -10, (String)object[1], (String) object[4]);
											}
										}
										else if(capacidadS==capacidadP)
										{
											for(int j=0;j<capacidadS-3;j++)
											{
												epsAndes.registrarReserva(serv, id, (String)object[2], -10, (String)object[1], (String) object[4]);
											}
										}
										else 
										{
											for(int j=0;j<capacidadS;j++)
											{
												epsAndes.registrarReserva(serv, id, (String)object[2], -10, (String)object[1], (String) object[4]);
											}
										}
										capacidadS-=capacidadP-3;

									}
								}
								else 
									throw new Exception("No se puede crear la campana");
							}
						}
					}

					VOCampania campania=epsAndes.registrarCampania(nombre, fechaFin, fechaInicio, organizador);
					for (String[] strings : anadirFin) {
						int cap=Integer.parseInt(strings[1]);
						epsAndes.registrarServCamp(strings[0], nombre, cap,strings[2],strings[3]);
					}
					if(campania != null) {
						JOptionPane.showMessageDialog(this, "Se registro la campania con exito!", "Registro de usuario exitoso", JOptionPane.INFORMATION_MESSAGE);
						String resultado = "En registro campania \n\n";
						resultado += "campania registrada exitosamente: " + campania;
						resultado += "\n OperaciÃ³n terminada";
						panelDatos.actualizarInterfaz(resultado);
					}
				}

				else
				{
					JOptionPane.showMessageDialog(this, "Se deben llenar todos los campos.", "Error registrando afiliado", JOptionPane.ERROR_MESSAGE);

				}
			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error registrando campania", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
			//			e.printStackTrace();
		}
	}

	/**
	 * Registrar medico.
	 */
	public void registrarMedico()
	{
		try {
			JTextField NumeroDocumento = new JTextField();
			JTextField Nombre = new JTextField();
			JComboBox<String> combo = new JComboBox<String>();
			combo.addItem("C.C");
			combo.addItem("T.I");
			combo.addItem("R.C");
			combo.addItem("C.E");
			combo.addItem("Pasaporte");

			JTextField textoEmail = new JTextField();
			JTextField Especialidad = new JTextField();
			JTextField RegistroMedico = new JTextField();
			JTextField Ips = new JTextField();

			Object message[] = {
					"Ingrese el numero de documento : ", NumeroDocumento,
					"Ingrese el nombre " , Nombre,
					"Ingrese su tipo docuemnto: ", combo,
					"Ingrese un correo electronico: ", textoEmail,
					"Ingrese su especialidad: ", Especialidad,
					"Ingrese su registroMedico: ", RegistroMedico,
					"Ingrese la IPS: ", Ips,
			};
			int option = JOptionPane.showConfirmDialog (this, message, "Registrar médico", JOptionPane.OK_CANCEL_OPTION);
			if(option == JOptionPane.OK_OPTION)
			{
				if(!Ips.getText().toString().equals("") &&!RegistroMedico.getText().toString().equals("") &&!Especialidad.getText().toString().equals("") && !Nombre.getText().toString().equals("") && !textoEmail.getText().toString().equals("") && !NumeroDocumento.getText().toString().equals(""))
				{
					String identificacion =NumeroDocumento.getText().toString();
					String nombre = Nombre.getText().toString();
					String tipoCC = combo.getSelectedItem().toString();
					String email = textoEmail.getText().toString();
					String especialidad=Especialidad.getText().toString();
					String registroMedico=RegistroMedico.getText().toString();
					String ips=Ips.getText().toString();

					VOUsuario usuario = epsAndes.registrarMedico(identificacion, nombre, email, "Medico", tipoCC, especialidad, registroMedico, ips);
					if(usuario != null) {
						JOptionPane.showMessageDialog(this, "Se registro el medico con exito!", "Registro de medico exitoso", JOptionPane.INFORMATION_MESSAGE);
						String resultado = "En registro medico \n\n";
						resultado += "Medico registrado exitosamente: " + usuario;
						resultado += "\n OperaciÃ³n terminada";
						panelDatos.actualizarInterfaz(resultado);
					}

					else {
						System.out.println("el usario llega nu");
					}
				}
				else {
					JOptionPane.showMessageDialog(this, "Se deben llenar todos los campos.", "Error registrando usuario", JOptionPane.ERROR_MESSAGE);
				}

			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error registrando usuario", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Registrar organizador.
	 */
	public void registrarOrganizador()
	{
		try {
			JTextField NumeroDocumento = new JTextField();
			JTextField Nombre = new JTextField();
			JComboBox<String> combo = new JComboBox<String>();
			combo.addItem("C.C");
			combo.addItem("T.I");
			combo.addItem("R.C");
			combo.addItem("C.E");
			combo.addItem("Pasaporte");

			JTextField textoEmail = new JTextField();

			Object message[] = {
					"Ingrese el numero de documento : ", NumeroDocumento,
					"Ingrese el nombre " , Nombre,
					"Ingrese su tipo docuemnto: ", combo,
					"Ingrese un correo electronico: ", textoEmail,
			};
			int option = JOptionPane.showConfirmDialog (this, message, "Registrar organizador", JOptionPane.OK_CANCEL_OPTION);
			if(option == JOptionPane.OK_OPTION)
			{
				if(!Nombre.getText().toString().equals("") && !textoEmail.getText().toString().equals("") && !NumeroDocumento.getText().toString().equals(""))
				{
					String identificacion =NumeroDocumento.getText().toString();
					String nombre = Nombre.getText().toString();
					String tipoCC = combo.getSelectedItem().toString();
					String email = textoEmail.getText().toString();

					VOUsuario usuario = epsAndes.registrarOrganizadorCampania(identificacion, nombre, "OrganizadorCampania", email, tipoCC);
					if(usuario != null) {
						JOptionPane.showMessageDialog(this, "Se registro el organizador con exito!", "Registro de organizador exitoso", JOptionPane.INFORMATION_MESSAGE);
						String resultado = "En registro organizador \n\n";
						resultado += "Organizador registrado exitosamente: " + usuario;
						resultado += "\n OperaciÃ³n terminada";
						panelDatos.actualizarInterfaz(resultado);
					}

					else {
						System.out.println("el usario llega nu");
					}
				}
				else {
					JOptionPane.showMessageDialog(this, "Se deben llenar todos los campos.", "Error registrando usuario", JOptionPane.ERROR_MESSAGE);
				}

			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error registrando usuario", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Registrar prestacion de servicio.
	 */
	public void registrarPrestacionDeServicio()
	{
		try {
			JTextField HoraInicio = new JTextField();
			JTextField Duracion = new JTextField();
			JTextField Dia = new JTextField();
			JTextField IdServicio = new JTextField();
			JTextField IdIPS = new JTextField();
			JComboBox<String> combo = new JComboBox<String>();
			combo.addItem("Consulta con medico");
			combo.addItem("Consulta de urgencias");
			combo.addItem("Remision con un especialista");
			combo.addItem("Consulta de control");
			combo.addItem("Examen diagnostico");
			combo.addItem("Terapia");
			combo.addItem("Procedimiento medico especializado");
			combo.addItem("Hospitalizacion");

			JTextField Capacidad = new JTextField();

			Object message[] = {
					"Ingrese la hora de inicio: ", HoraInicio,
					"Ingrese la duración: ", Duracion,
					"Ingrese el dia siguiendo el formato DD-MM-YY HH24:MI:SS: ", Dia,
					"Ingrese el servicio: ", IdServicio,
					"Ingrese la IPS: ", IdIPS,
					"Ingrese el tipo: ", combo,
					"Ingrese la capacidad: ", Capacidad,
			};

			int option = JOptionPane.showConfirmDialog (this, message, "Registrar prestación de servicio", JOptionPane.OK_CANCEL_OPTION);
			if(option == JOptionPane.OK_OPTION) {
				if(!Capacidad.getText().toString().equals("") && !combo.getSelectedItem().toString().equals("") && !IdIPS.getText().toString().equals("") && !IdServicio.getText().toString().equals("") && !Dia.getText().toString().equals("") && !HoraInicio.getText().toString().equals("") && !Duracion.getText().toString().equals("") )
				{

					String duracion = Duracion.getText().toString();
					String horaInicio = HoraInicio.getText().toString();
					String dia = Dia.getText().toString();
					String idServicio = IdServicio.getText().toString();
					String idIps = IdIPS.getText().toString();
					int capacidad = Integer.parseInt(Capacidad.getText().toString());
					String tipo = combo.getSelectedItem().toString();

					VOPrestan prestan=epsAndes.registrarServicioDeSaludAPrestar(duracion, horaInicio, dia, idServicio, idIps, capacidad, tipo);
					if(prestan != null) {
						JOptionPane.showMessageDialog(this, "Se registro el servicio con exito!", "Registro de servicio exitoso", JOptionPane.INFORMATION_MESSAGE);
						String resultado = "En registro servicio \n\n";
						resultado += "servicio registrado exitosamente: " + prestan;
						resultado += "\n OperaciÃ³n terminada";
						panelDatos.actualizarInterfaz(resultado);
					}

					else {
						System.out.println("el servicio llega null");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(this, "Se deben llenar todos los campos.", "Error registrando afiliado", JOptionPane.ERROR_MESSAGE);

				}


			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error registrando Afiliado", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Habilitar.
	 */
	public void habilitar()
	{
		try {

			JTextField fechaInis = new JTextField();
			JTextField fechaFins = new JTextField();
			JTextField Servicio = new JTextField();
			JTextField IdIPS = new JTextField();

			Object message[] = {
					"Ingrese la fecha de inicio: ", fechaInis,
					"Ingrese la fecha de finalizaci[on: ", fechaFins,
					"Ingrese el nombre del servicio: ", Servicio,
					"Ingrese el nombre de la IPS: ", IdIPS,
			};

			int option = JOptionPane.showConfirmDialog (this, message, "Habilitar servicio", JOptionPane.OK_CANCEL_OPTION);
			if(option == JOptionPane.OK_OPTION) {
				if(!IdIPS.getText().toString().equals("") &&!Servicio.getText().toString().equals("") &&!fechaInis.getText().toString().equals("") && !fechaFins.getText().toString().equals("") )
				{
					String fechaFin = fechaFins.getText().toString();
					String servicio=Servicio.getText().toString();
					String fechaIni=fechaInis.getText().toString();
					String idIPS=IdIPS.getText().toString();
					epsAndes.habilitar(fechaIni, fechaFin, idIPS, servicio);
				}
				else
				{
					JOptionPane.showMessageDialog(this, "Se deben llenar todos los campos.", "Error habilitando", JOptionPane.ERROR_MESSAGE);

				}
			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error habilitando", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}


	/**
	 * Des habilitar.
	 */
	public void desHabilitar()
	{
		try {

			JTextField fechaInis = new JTextField();
			JTextField fechaFins = new JTextField();
			JTextField Servicio = new JTextField();
			JTextField IdIPS = new JTextField();

			Object message[] = {
					"Ingrese la fecha de inicio: ", fechaInis,
					"Ingrese la fecha de finalizaci[on: ", fechaFins,
					"Ingrese el nombre del servicio: ", Servicio,
					"Ingrese el nombre de la IPS: ", IdIPS,
			};

			int option = JOptionPane.showConfirmDialog (this, message, "Deshabilitar servicio", JOptionPane.OK_CANCEL_OPTION);
			if(option == JOptionPane.OK_OPTION) {
				if(!IdIPS.getText().toString().equals("") &&!Servicio.getText().toString().equals("") &&!fechaInis.getText().toString().equals("") && !fechaFins.getText().toString().equals("") )
				{
					String fechaFin = fechaFins.getText().toString();
					String servicio=Servicio.getText().toString();
					String fechaIni=fechaInis.getText().toString();
					String idIPS=IdIPS.getText().toString();
					JOptionPane.showMessageDialog(this, "Se deshabilito el servicio con exito!", "Deshabilitacion de servicio exitoso", JOptionPane.INFORMATION_MESSAGE);
					String resultado = "En registro servicio \n\n";
					resultado+=epsAndes.desHabilitar(fechaIni, fechaFin, idIPS, servicio)+"\n";
					resultado += "servicio deshabilitado exitosamente: ";
					resultado += "\n OperaciÃ³n terminada";
					panelDatos.actualizarInterfaz(resultado);
				}
				else
				{
					JOptionPane.showMessageDialog(this, "Se deben llenar todos los campos.", "Error deshabilitando", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error deshabilitando", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}


	/**
	 * Dar exigentes.
	 */
	public void darExigentes()
	{
		String resultado = "Se consultaron los exigentes \n\n";
		resultado+=epsAndes.darExigentes()+"\n";
		resultado += "Consulta realizada exitosamente: ";
		resultado += "\n operacion terminada";
		panelDatos.actualizarInterfaz(resultado);
	}

	/**
	 * Dar no muy demandados.
	 */
	public void darNoMuyDemandados()
	{
		String resultado = "Se consultaron los no muy demandados \n\n";
		resultado+=epsAndes.darNoMuyDemandados()+"\n";
		resultado += "Consulta realizada exitosamente: ";
		resultado += "\n operacion terminada";
		panelDatos.actualizarInterfaz(resultado);
	}

	/**
	 * Req C 6.
	 */
	public void reqC6()
	{
		try {


			JTextField Servicio = new JTextField();
			JComboBox<String>combo=new JComboBox<String>();
			combo.addItem("WW: Semana");
			combo.addItem("MM: Mes");
			combo.addItem("YY: Anio");
			;

			Object message[] = {
					"Ingrese el nombre del servicio: ", Servicio,
					"Seleccione la unidad de tiempo: ",combo,
			};

			int option = JOptionPane.showConfirmDialog (this, message, "Deshabilitar servicio", JOptionPane.OK_CANCEL_OPTION);
			if(option == JOptionPane.OK_OPTION) {
				if(!combo.getSelectedItem().toString().equals("") &&!Servicio.getText().toString().equals(""))
				{
					String unidad = combo.getSelectedItem().toString().split(":")[0];
					String servicio=Servicio.getText().toString();
					JOptionPane.showMessageDialog(this, "Se consulto la operacion con exito!", "Consulta de operacion exitosa", JOptionPane.INFORMATION_MESSAGE);
					String resultado = "Se consulto la operacion \n\n";
					resultado+=epsAndes.reqC6(servicio, unidad)+"\n";
					resultado += "Consulta realizada exitosamente: ";
					resultado += "\n OperaciÃ³n terminada";
					panelDatos.actualizarInterfaz(resultado);
				}
				else
				{
					JOptionPane.showMessageDialog(this, "Se deben llenar todos los campos.", "Error deshabilitando", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error deshabilitando", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
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
			Method req = InterfazEPSAndesApp.class.getMethod ( evento );			
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
			InterfazEPSAndesApp interfaz = new InterfazEPSAndesApp( );
			interfaz.setVisible( true );
		}
		catch( Exception e )
		{
			e.printStackTrace( );
		}
	}
}