package controlador;

import java.util.ArrayList;

import entidades.EProveedor;
import modelo.MProveedor;

public class CProveedor {
	//campos o atributos
	private MProveedor ObjM;

	//Constructor
	public CProveedor() {
		ObjM = new MProveedor();
	}
	//M�todos a exponer
	//M�todo listar proveedores
	public ArrayList<EProveedor> Listar(){
		return ObjM.ListarProveedor();
	}
	
}
