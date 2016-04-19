package Comparators;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.*;
import modelo.Herramienta;
public class ordenarTamaño implements Comparator<Herramienta>{

    public ordenarTamaño() {
    }

    @Override
    public int compare(Herramienta o1, Herramienta o2) {//comparador utilizado para ordenar por tamaño
        return o1.getTamaño().compareToIgnoreCase(o2.getTamaño());
    }
    
    
    
}
