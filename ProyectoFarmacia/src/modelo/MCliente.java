package modelo;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import entidades.ECliente;

public class MCliente {
	//Campos o atributos
	private MConexion mysql = new MConexion();
	private Connection cn = mysql.Conectar(); //Almacenando la conexión
	private CallableStatement Stm;
	private ResultSet Rs;
	private ArrayList<ECliente> Lista;
	//Constructor 
	public MCliente() {	}
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	//DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
	//Método Listar 
	public ArrayList<ECliente> ListarCliente(){
		Lista = new ArrayList<>();
		String SQL = "call ListarCliente()";
		try {
			Stm = cn.prepareCall(SQL);
			Rs = Stm.executeQuery();
			
			while(Rs.next()){
				ECliente ObjP = new ECliente(
						Rs.getString("COD_CLI"),
						Rs.getString("NOM_CLI"),
						Rs.getString("APAT_CLI"),
						Rs.getString("AMAT_CLI"),
						Rs.getDate("FEC_REG_CLI"),
						Rs.getString("TLF_CLIENTE")
						);
				Lista.add(ObjP);
			}
		} catch (Exception e) {
			System.out.println("Error al listar Clientes.\n"+e.getMessage());
		}
		return Lista;
	}	
	// Método para buscar
	public ECliente BuscarCliente(String Id){
		ECliente Obj =null;
		Obj = null;
		String SQL = "CALL BuscarCliente(?)";
		try {
				Stm = cn.prepareCall(SQL);
				Stm.setString(1, Id);
				Rs = Stm.executeQuery();
				if(Rs.next()){
					Obj = new ECliente(
							Rs.getString("COD_CLI"),
							Rs.getString("NOM_CLI"),
							Rs.getString("APAT_CLI"),
							Rs.getString("AMAT_CLI"),
							Rs.getDate("FEC_REG_CLI"),
							Rs.getString("TLF_CLIENTE")
						);
				}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return Obj;
	}	
	

	// Método para modificar
	public void ModificarCliente(ECliente ObjP){
		String SQL = "CALL ModificarCliente(?,?,?,?,?,?)";
		try {
				Stm = cn.prepareCall(SQL);
				Stm.setString(1, ObjP.getCod_cli());
				Stm.setString(2, ObjP.getNom_cli());
				Stm.setString(3, ObjP.getApat_cli());
				Stm.setString(4, ObjP.getAmaT_cli());
				Stm.setString(5, sdf.format(ObjP.getFec_reg_cli()));
				Stm.setString(6, ObjP.getTlf_cliente());
				Stm.executeUpdate();
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}

	// Método para insertar
	public void InsertarCliente(ECliente ObjP){
		String SQL = "CALL InsertarCliente(?,?,?,?,?,?)";
		try {
			
				Stm = cn.prepareCall(SQL);
				Stm.setString(1, ObjP.getCod_cli());
				Stm.setString(2, ObjP.getNom_cli());
				Stm.setString(3, ObjP.getApat_cli());
				Stm.setString(4, ObjP.getAmaT_cli());
				Stm.setString(5, sdf.format(ObjP.getFec_reg_cli()));
				Stm.setString(6, ObjP.getTlf_cliente());			
				Stm.executeUpdate();
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}
	// Método para eliminar
	public void EliminarCliente(String Id){
		String SQL = "CALL EliminarCliente(?)";
		try {
				Stm = cn.prepareCall(SQL);
				Stm.setString(1, Id);
				Stm.executeUpdate();
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}
	//Método para cerrar la conexión al Base de Datos
	private void CerrarRecursos(){
		try {
			//Cerrar el ResultSet
			if(Rs != null)
				Rs.close();
			if(Stm != null)
				Stm.close();
			if(cn != null)
				cn.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}


}
