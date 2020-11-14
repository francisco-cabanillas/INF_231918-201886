/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.util.List;

/**
 *
 * @author Paula Hernandez
 */
public class Programa {
    private List<Instruccion> ejecucion;
    
    public Programa(List<Instruccion> unaEjecucion) {
        this.setEjecucion(unaEjecucion);
    }

    public Programa() {
        
    }
    public List<Instruccion> getEjecucion() {
        return ejecucion;
    }

    public void setEjecucion(List<Instruccion> ejecucion) {
        this.ejecucion = ejecucion;
    }
}
