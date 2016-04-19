package modelo;



import Comparators.ordenarPrecio;
import Comparators.ordenarTamaño;
import java.util.*;
import java.io.*;

public class Ferreteria implements Serializable{
    
    private String razonSocial;
    private String direccion;
    private String titular;
    private List <Herramienta> stock;
    private int nHerramientas;

    public Ferreteria(String razonSocial, String direccion, String titular, int nHerraminetas) {
        this.razonSocial = razonSocial;
        this.direccion = direccion;
        this.titular = titular;
        this.nHerramientas = nHerraminetas;
        stock = new ArrayList(nHerramientas);
    }

    public Ferreteria(String razonSocial, String direccion, String titular, List<Herramienta> stock) {
        this.razonSocial = razonSocial;
        this.direccion = direccion;
        this.titular = titular;
        this.stock = stock;
    }

    public Ferreteria(String razonSocial) {
        this.razonSocial = razonSocial;
    }
    
    
    
    
    
    public void guardar(){//metodo que guarda los atributos de un objeto en un fichero
        try{
            ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(razonSocial+".cnt"));
            oos.writeObject(stock);
            oos.writeUTF(razonSocial);
            oos.writeUTF(direccion);
            oos.writeUTF(titular);
            oos.close();
            System.out.println("Guardado con exito");
        }catch(IOException x){
            System.out.println("Error en el guardado");
        }
    }
    
    public Ferreteria(){//metodo constructor que lee los atributos de un fichero e instancia un objeto de esta clase
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(razonSocial+".cnt"));
            this.stock = (ArrayList<Herramienta>) ois.readObject();
            this.razonSocial = ois.readUTF();
            this.direccion = ois.readUTF();
            this.titular = ois.readUTF();
            ois.close();        
        }catch(IOException | ClassNotFoundException x){
            System.out.println("Error en la lectura");
        }
    }
    
    public void altaHerramienta(Herramienta a){
        stock.add(a);        
    }
    
    public boolean bajaHerramienta(String marca, String modelo){//busca una herramienta y la elimina
        if (busquedaBinaria(marca, modelo)>=0){
            stock.remove(busquedaBinaria(marca, modelo));
            return true;
        } else {
            return false;
        }
    }
    
    public void ordenarMM(){//ordena la lista por marca y modelo
        Collections.sort(stock);        
    }
    
    public int busquedaBinaria (String marca, String modelo){//busque uan herramienta por marca y modelo
        ordenarMM();
        Herramienta comp = new Herramienta(marca, modelo);
        int inicio=0;
        int fin = stock.size();
        int medio;
        int posicion = -1;
        while (inicio<=fin){
            medio = (inicio+fin)/2;
            if(stock.get(medio).compareTo(comp)<0)
                inicio = medio + 1;
            else if(stock.get(medio).compareTo(comp)>0)
                fin = medio - 1;
            else{
                posicion = medio;
                inicio = fin + 1;
            }         
        }
        return posicion;        
    }
    
    public void modificar(){//menu de modificaciones
        Scanner sc = new Scanner(System.in);
        int opcion;
        boolean salir=true;
        while(salir){
            System.out.println("¿Que desea modificar?");
            System.out.println("1.Existencias.");
            System.out.println("2.Precio.");
            System.out.println("0.Salir");
            opcion=sc.nextInt();
            sc.nextLine();
            switch(opcion){
                case 1:
                    System.out.println("Introduzca la marca de la herramineta a editar");
                    String marca = sc.next();
                    System.out.println("Introduzca el modelo de la herramienta a editar");
                    String modelo = sc.next();
                    System.out.println("Introduzca el nuevo numero de existencias");
                    int exist = sc.nextInt();
                    modificarExistencias(marca,modelo,exist);
                    break;
                case 2:
                    System.out.println("Introduzca la marca de la herramineta a editar");
                    String marca2 = sc.next();
                    System.out.println("Introduzca el modelo de la herramienta a editar");
                    String modelo2 = sc.next();
                    System.out.println("Introduzca el nuevo precio de la herramienta");
                    double precio = sc.nextDouble();
                    modificarPrecio(marca2,modelo2,precio);
                    break;
                default:
                    salir=false;
                    break;
            }
        }
    }
    
    public void mostrarDatos (){
        Iterator <Herramienta> iterator = stock.iterator();
        while(iterator.hasNext()){
            iterator.next().mostrarDatos();
        }
    }
    
    public boolean modificarExistencias(String marca,String modelo,int exist){//modifica las existencias de una herramienta
        Herramienta b = stock.get(busquedaBinaria(marca,modelo));
        b.setExistencias(exist);
        stock.set(busquedaBinaria(marca,modelo), b);
        return true;
    }

    public boolean modificarPrecio(String marca2, String modelo2, double precio) {//modifica el precio de una herramienta
        Herramienta b = stock.get(busquedaBinaria(marca2,modelo2));
        b.setPrecio(precio);
        stock.set(busquedaBinaria(marca2,modelo2), b);
        return true;
    }
    
    public void ordenarPrecio(){
        Collections.sort(stock,new ordenarPrecio());
    }
    
    public void mostrarOrdenadosPrecio(){//muestar ordenados por precio
        Collections.sort(stock, new ordenarPrecio());
        mostrarDatos();        
    }
    
    public void mostrarOrdenadosTamaño(){//mustra ordenados por tamaño
        Collections.sort(stock, new ordenarTamaño());
        mostrarDatos();               
    }
    
    public void mostrarOrdenadosMM(){//mustra ordenados por marca y modelo
        ordenarMM();
        mostrarDatos();
    }
    
    public void mostrarRandom(){//mustra la lista de manera aleatoria
        Collections.shuffle(stock);
        mostrarDatos();
    }
    
    public void mostrar(){//menu de muestras
        Scanner sc = new Scanner(System.in);
        int opcion;
        boolean salir=true;
        while(salir){
            System.out.println("¿Como desea mostrar las herramientas?");
            System.out.println("1.Ordenadas por marca y modelo.");
            System.out.println("2.Ordenadas por precio.");
            System.out.println("3.Ordenadas por tamaño.");
            System.out.println("4.Sin ordenar.");
            System.out.println("0.Salir");
            opcion=sc.nextInt();
            sc.nextLine();
            switch(opcion){
                case 1:
                    mostrarOrdenadosMM();
                    break;
                case 2:
                    mostrarOrdenadosPrecio();
                    break;
                case 3:
                    mostrarOrdenadosTamaño();
                    break;
                case 4:
                    mostrarRandom();
                    break;
                default:
                    salir=false;
                    break;
            }
        }
    }
    
    public String[][] tablaBid(){
        int indice = 0;
        String arrayHerramientas[][]= new String[stock.size()][6];
        Iterator <Herramienta> iterator = stock.iterator();
        Herramienta h;
        while(iterator.hasNext()){
            h = iterator.next();
            arrayHerramientas[indice][0] = h.getNombre();
            arrayHerramientas[indice][1] = h.getMarca();
            arrayHerramientas[indice][2] = h.getModelo();
            arrayHerramientas[indice][3] = h.getTamaño();
            arrayHerramientas[indice][4] = Integer.toString(h.getExistencias());
            arrayHerramientas[indice][5] = Double.toString(h.getPrecio());
            indice++;
        }
        return arrayHerramientas;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTitular() {
        return titular;
    }

    public List<Herramienta> getStock() {
        return stock;
    }

    public int getnHerramientas() {
        return nHerramientas;
    }
    
    


    
    
    
}
