package Dominio;

import java.util.List;

public class Instruccion {
    private int tiempo;
    private List<Recurso> recursos;
    //private List<string> calculos;   a confirmar
    
    public Instruccion(int unTiempo){
        this.setTiempo(unTiempo);
        //this.setRecursos(losRecursos);
    }    
    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public List<Recurso> getRecursos() {
        return recursos;
    }

    public void setRecursos(List<Recurso> recursos) {
        this.recursos = recursos;
    }
    
    /*public boolean Correr(){
        boolean resultado = true;
        if(this.recursos.isEmpty())
        
        return resultado;
    }*/
}
