/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexBD;

import java.sql.*;
import Exceptions.ErrorConexionBD;

public class ConexionBD {
    
    Connection conn;
    Statement stmt;
    
    static ConexionBD instancia = null;
    
    private ConexionBD() throws ErrorConexionBD {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ferreteriaDB","root","mysql");
            stmt = conn.createStatement();
            if ( conn != null ){
                System.out.println("Conexion establecida");
            }
            
        }
        catch(SQLException e) {
            throw new ErrorConexionBD();
        }
    }
    
    public Connection getConnection() {
        return conn;
    }
    
    public Statement getStatement() {
        return stmt;
    }
    
    public static void crearConexion() throws ErrorConexionBD {
        if (instancia == null) {
            instancia = new ConexionBD();
        }
        
    }
    
    public static ConexionBD instancia() {
        return instancia;
    }
    
    public static void desconectar() {
        if (instancia != null) {
            try {
                instancia.stmt.execute("shutdown");
                instancia.stmt.close();
                instancia.conn.close();
                instancia = null;
            }
            catch(SQLException e) {
            }
        }
    }
    
}
