package dataconexion;
import java.beans.PropertyVetoException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataConexion {
	
	private static java.sql.Connection con;
	private static DataConexion INSTANCE = null;
	public static String user;
	public static String pass;
	public static final String ip_server = "localhost";
	public static final String bd = "roll_game";
			
	public DataConexion(){
	
	}
	
	public static void performedConnection(){
	
		try {
			ComboPooledDataSource cpds = new ComboPooledDataSource(); 
			cpds.setDriverClass("com.mysql.jdbc.Driver"); 
			//localhost->IP DEL SERVIDOR
			cpds.setJdbcUrl("jdbc:mysql://"+ip_server+"/"+bd); 
			cpds.setUser("Antonio"); cpds.setPassword("nico2222");
			cpds.setAcquireRetryAttempts(1); cpds.setAcquireRetryDelay(1); cpds.setBreakAfterAcquireFailure(true);
			DataSource ds = cpds; 
			con = ds.getConnection();
		} catch (SQLException | PropertyVetoException e1) {	
			System.err.println(e1.getMessage());
		}
	}

	private synchronized static void createInstance(){
		if(INSTANCE == null){
			INSTANCE = new DataConexion();
			performedConnection();
		}	
	}
	
	public static DataConexion getInstance(){
		
		if(INSTANCE == null) createInstance();
		return INSTANCE;		
	}
	
	public static ArrayList<String> Consulta(String columnas,String Tabla,
											 String elementoAbuscar,String condicion) throws SQLException{

		//Tabla -> tabla en la que queremos buscar
		//String ->Elementos a buscar
		ArrayList<String> ls = new ArrayList<String>();
		PreparedStatement ps = con.prepareStatement("SELECT "+columnas+" FROM " + Tabla + condicion);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()){
			ls.add(rs.getString(elementoAbuscar));
		}
		
		rs.close();
		
		return ls;
	}

	public static void UpDateMapa(String mapa){
		
		PreparedStatement ps;
		String aux = "UPDATE caracteristicas set idMapa  = '" + mapa + "' WHERE nombre = " +
		"'"+DataConexion.user+"'"+"LIMIT  1";
		
		try {
			ps = con.prepareStatement(aux);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public static void Insercion(String Tabla,ArrayList<String> columnasAinsertar,ArrayList<String> elementosAinsertar){
		
		//(Inserta una serie de valores en unos atributos de una tabla)
		//Tabla-> tabla de donde sacamos los datos
		//columnaAinsertar -> atributos de la tabla que queremos modificar
		//elementosAinsertar -> datos que queremos meter en esos atributos
		
		
		String tab_aux = "`" + Tabla + "`";
		String seleccion = "INSERT INTO" + tab_aux + "( ";
		String aux_interrogaciones = "";
		
		for(int i = 0; i < columnasAinsertar.size();i++){

			if(i == columnasAinsertar.size()-1){
				
				aux_interrogaciones = aux_interrogaciones + "?";
				seleccion =  seleccion + "`" + columnasAinsertar.get(i) + "`";
			}else{
				
				aux_interrogaciones = aux_interrogaciones + "?,";
				seleccion =  seleccion + "`" + columnasAinsertar.get(i) + "`,";
			}
		}
		
		seleccion = seleccion + ")VALUES ("+aux_interrogaciones +")";
		
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(seleccion);
			for(int i = 0;i < elementosAinsertar.size();i++){
				ps.setString(i+1,elementosAinsertar.get(i));
				
			}
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	public static void closeConnection() {
		try {
			con.close();
		}catch (Exception e) {
			System.out.println("Error al cerrar la conexion.");
		}
	}
		
}
