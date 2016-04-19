package Comparators;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.*;
import modelo.Herramienta;
public class ordenarPrecio implements Comparator<Herramienta>{

    public ordenarPrecio() {
    }
    
    

    @Override
    public int compare(Herramienta o1, Herramienta o2) {//comparador utilizados para ordenar por precio
        int i = 0;
        if (o1.getPrecio() < o2.getPrecio())
            i = -1;
        if (o1.getPrecio() > o2.getPrecio())
            i = 1;        
        return i;
    }
    
}
