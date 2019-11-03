package uniandes.isis2304.epsAndes.negocio;

import java.util.List;

public interface VOCampania {
	public String getFechaInicio();
	public String getFechaFin() ;
	public String getNombre(); 
	public String getId_Organizador();
	public List<Object []> getServicios();
}
