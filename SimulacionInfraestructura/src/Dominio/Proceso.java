package Dominio;

public class Proceso {
    private int numero;
    private int posicionEjecucion;
    private Usuario propiedad;
    //private List<> ejecucion;
    
    public Proceso(int unNumero) {
        this.numero = unNumero;
        this.posicionEjecucion = 0;
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
    
}
