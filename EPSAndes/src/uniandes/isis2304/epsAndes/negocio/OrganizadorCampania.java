package uniandes.isis2304.epsAndes.negocio;

import java.util.LinkedList;
import java.util.List;

public class OrganizadorCampania extends Usuario implements VOOrganizadorcampania{

	private List<Object[]> campanias;

	public OrganizadorCampania(String numero_Documento, String nombre,String email, String rol, String tipo_Documento) {
		super(numero_Documento, nombre, email, rol, tipo_Documento);
		this.campanias=new LinkedList<Object []> ();
	}


	public List<Object[]> getCampanias() {
		return campanias;
	}

	public void setCampanias(List<Object[]> campanias) {
		this.campanias = campanias;
	}

}
