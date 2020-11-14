package Dominio;

import java.util.List;

public class Proceso {
    private int numero;
    private int posicionEjecucion;
    private Usuario propiedad;
    private Programa programa;
    private int estado; //0 bloqueado, 1 en ejecucion, 3 listo.
    
    public Proceso(Usuario unUsuario, Programa unPrograma) {
      //el numero nose cual ponerle
        this.setPosicionEjecucion(0);
        this.setPrograma(unPrograma);
        this.setPropiedad(unUsuario);
    }

    public Proceso() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        this.setNumero(0);
        
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

    //public List<Instruccion> getEjecucion() {
      //  return ejecucion;
    //}

   // public void setEjecucion(List<Instruccion> ejecucion) {
   //     this.ejecucion = ejecucion;
   // }

    public void setPrograma(Programa unPrograma) {
        this.programa = unPrograma;
    }
    private Programa getPrograma() {
        return programa;
    }

    public void setEstado(int unEstado) {
        this.estado = unEstado;
    }
    public int getEstado() {
        return estado;
    }
    
}
