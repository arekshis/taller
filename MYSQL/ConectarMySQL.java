package Componentes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;



/**
 * Esta clase permite conectar a una base de datos Mysql y ademas ejecutar sentencias DML
 */


final public class ConectarMySQL {
 
  //** Declaracion de variables
  private Connection conexion;
  
  protected Statement sentencia;
   
  
  /** 
   *Constructor general que se conecta a la base de datos dependiendo de los parametros
   *
   *@param servidorNombre Nombre del servidor o direccion IP
   *@param nombreBD  nombre de la base de datos
   *@param usuario Usuario autorizado
   *@param  password
   *
   */
  
  public ConectarMySQL(String servidorNombre,String nombreBD,String usuario,String password) throws Exception{
  	 
  	 //** Se carga el driver para conectarse a la base de datos
  	 
  	 try {
  	 
  	 	Class.forName("com.mysql.jdbc.Driver").newInstance();
  	 
  	 }catch  (Exception e) {
  	 	System.out.println("Error"+e);
  	 }
  	 
  	    
    // Se conecta a la base de datos
    // Se crea un URL hacia la maquina y la base de datos
 	String url= "jdbc:mysql://" + servidorNombre + "/" + nombreBD; 
    
    //se crea la conexion a la base de datos
    conexion=DriverManager.getConnection(url,usuario,password);
    
    //conexion.setAutoCommit(false);
    
    sentencia=conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
    
      
   
  }
  
  
  
  
  /** 
   *Permite ejecutar una sentencia SQL de tipo DML
   *
   *@param sentenciaSQL Sentencia SQL que pueder ser un Insert, Update, Delete
   *
   */
  
   public void ejecutaSentencia(String sentenciaSQL) throws Exception {
     
      // Se ejecutan las sentencias DML que llegan como parametro
  	  sentencia.execute(sentenciaSQL);
  }
  
  
  /** 
   *Permite buscar un/unos registro(s) en la base de datos
   *
   *@param sentenciaSQL Sentencia SQL para buscar un registro
   *@return ResultSet Registro encontrado
   *
   */

  public  ResultSet buscarRegistro (String sentenciaSQL) throws Exception {
  	
  	
  	// Se hace la busqueda del registro en la B.D
  	ResultSet resultado = sentencia.executeQuery(sentenciaSQL);
  	
  	return resultado;
  
  }	
  
  
  
   public Connection getConexion() {
  	
  	 return conexion;
   }
  
  
  /** 
   * Devuelve un objeto de tipo Statement para realizar sentencias SQL contra la BD
   *
   * @return Statement Sentencia de conexion
   *
   */
  
  public Statement getSentencia(){
  	
  	 return sentencia;
  	
  }
  
  
  /** 
   * Permite hacer los cambios permanentes en la BD
   *
   */
  
  public void commit() throws Exception {
  	
  	 conexion.commit();
  }
  
  
  
  /** 
   * Permite deshacer  cambios en la BD antes del ultimo commit
   *
   */
  
  public void rollback() {
  	
  	 try {
  	 	
  	 	conexion.rollback();
  	 	
  	 } catch (Exception e) {
  	 	
  	 	System.out.println("Error "+ e);
  	 }
  }
  
  	
}


