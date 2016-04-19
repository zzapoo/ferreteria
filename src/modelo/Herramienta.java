package modelo;




import java.io.*;

public class Herramienta implements Serializable,Comparable<Herramienta>{
    
    private String razon;
    private String nombre;
    private String marca;
    private String modelo;
    private String tamaño;
    private double precio;
    private int existencias;

    public Herramienta(String razon, String nombre, String marca, String modelo, String tamaño, double precio, int existencias) {
        this.razon = razon;
        this.nombre = nombre;
        this.marca = marca;
        this.modelo = modelo;
        this.tamaño = tamaño;
        this.precio = precio;
        this.existencias = existencias;
    }

    public Herramienta(String marca, String modelo) {
        this.marca = marca;
        this.modelo = modelo;
    }
    
    

    public String getNombre() {
        return nombre;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getTamaño() {
        return tamaño;
    }

    public double getPrecio() {
        return precio;
    }

    public int getExistencias() {
        return existencias;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }

    public String getRazon() {
        return razon;
    }
    
    

    @Override
    public int compareTo(Herramienta o) {//metodo comparator que utilizaran 2 herramientas para comprarse entre ellas
        int i;
        
        
        if (this.marca.equalsIgnoreCase(o.marca)){
            i = this.modelo.compareToIgnoreCase(o.modelo);
        }
        
        else {
            i = this.marca.compareToIgnoreCase(o.marca);
        }
        /*if (this.marca.compareToIgnoreCase(o.getMarca())<0){
            i=-1;
        }
        if (this.marca.compareToIgnoreCase(o.getMarca())>0){
            i = 1;
        }
        if (this.marca.compareToIgnoreCase(o.getMarca())==0){
            if (this.modelo.compareToIgnoreCase(o.getModelo())<0){
                i=-1;
            }
            if (this.modelo.compareToIgnoreCase(o.getModelo())>0){
                i = 1;
            }
            
        }*/
        return i;           
    }

    void mostrarDatos() {
        System.out.println("La marca de la herramienta es: "+marca);
        System.out.println("El modelo de la herramienta es: "+modelo);
        System.out.println("el nombre de la herramienta es: "+nombre);
        System.out.println("El tamaño de la herramienta es: "+tamaño);
        System.out.println("El precio de la herramineta es: "+precio);
        System.out.println("Las existencias de la herramienta son: "+existencias);
    }
    
    
    
    
    
            
    
}
