package conexBD;

import java.sql.*;
import modelo.*;
import Exceptions.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class operacionesSQL {
    
    static operacionesSQL operacion = null;

    private operacionesSQL() {
    }
    
    public static operacionesSQL instancia() {
        if (operacion == null) {
            operacion = new operacionesSQL();
        }
        
        return operacion;
    }
    
    public void crerF (Ferreteria f) throws ErrorCreacionFerreteria{
        try{
            ConexionBD.instancia().getStatement().execute(
            "Insert into Ferreterias values ('"+f.getRazonSocial()+"','"
                    +f.getDireccion()+"','"+f.getTitular()+"');"
            );            
        }catch(SQLException a){
            throw new ErrorCreacionFerreteria();
        }
    }
    
    public void addH (Herramienta h){
            try {
            ConexionBD.instancia.getStatement().execute(
                "Insert into Herramientas values ('"+h.getRazon()+"','"+h.getNombre()+"','"
                +h.getMarca()+"','"+h.getModelo()+"','"+h.getTamaño()+"','"+Double.toString(h.getPrecio())
                +"','"+Integer.toString(h.getExistencias())+"');"
            );
            } catch (SQLException ex) {
                Logger.getLogger(operacionesSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public ArrayList<Herramienta> buscarH (String razon){
        ArrayList<Herramienta> a = null;
        
        try{
            ResultSet rs = ConexionBD.instancia().getStatement().executeQuery(
            "Select * from herramientas where razon_social='"+razon+"';"            
            );
            
            while (rs.next()){
                a.add(new Herramienta(rs.getString(1),rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getDouble(6), rs.getInt(7)));                
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }       
        return a;
    }
    
    public void borrarF (Ferreteria f) throws ErrorBorradoFerreteria{
        try {
            ConexionBD.instancia().getStatement().execute(
            "Delete from ferreterias where razon_social='"+f.getRazonSocial()+"';'"
            );
        } catch (SQLException e) {
            throw new ErrorBorradoFerreteria();
        }
    }
    
    public String[][] tablaF (){
        String a [][] = null;
        try{
        
        ResultSet rsi = ConexionBD.instancia().getStatement().executeQuery(
        "Select count(razon_social) from ferreterias;");
        int r = 0;
        if(rsi.next()){
            r = rsi.getInt(1);
        }
        int j = 0;
        a = new String [r][3];
        ResultSet rs = ConexionBD.instancia().getStatement().executeQuery(
        "Select * from ferreterias;");
        while (rs.next())
        {
            for (int i=0;i<3;i++){
                 a[j][i]=rs.getString(i+1);
            }
            j++;
 
        }
        }catch(Exception e){
            e.printStackTrace();
        }
        return a;
    }
    
    
}
