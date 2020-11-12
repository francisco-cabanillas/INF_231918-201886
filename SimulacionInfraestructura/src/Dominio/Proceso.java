package Dominio;

import java.util.List;

public class Proceso {
    private int numero;
    private int posicionEjecucion;
    private Usuario propiedad;
    private List<Instruccion> ejecucion;
    
    public Proceso(int unNumero, List<Instruccion> unaEjecucion) {
        this.setNumero(unNumero);
        this.setPosicionEjecucion(0);
        this.setEjecucion(unaEjecucion);
    }
    
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getPosicionEjecucion() {
        return posicionEjecucion;
    }

    public void setPosicionEjecucion(int posicionEjecucion) {
        this.posicionEjecucion = posicionEjecucion;
    }

    public Usuario getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(Usuario propiedad) {
        this.propiedad = propiedad;
    }

    public List<Instruccion> getEjecucion() {
        return ejecucion;
    }

    public void setEjecucion(List<Instruccion> ejecucion) {
        this.ejecucion = ejecucion;
    }
    
}
