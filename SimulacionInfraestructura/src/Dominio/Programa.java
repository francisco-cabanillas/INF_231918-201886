package Dominio;

import java.util.List;

public class Programa {
    private List<Instruccion> instrucciones;
    private List<Instruccion> instruccionesFinalizadas;
    
    public Programa(List<Instruccion> instrucciones) {
        this.setInstrucciones(instrucciones);
    }

    public Programa() {
        
    }
    

    public List<Instruccion> getInstrucciones() {
        return instrucciones;
    }

    /*public void setEjecucionesPendientes(List<Instruccion> ejecucionPendiente) {
        this.ejecuciones = ejecucionPendiente;
    }

    public List<Instruccion> getInstruccionesFinalizadas() {
        return instruccionesFinalizadas;
    }

    public void setInstruccionesFinalizadas(List<Instruccion> instruccionesFinalizadas) {
        this.instruccionesFinalizadas = instruccionesFinalizadas;
    }
    
    
    
    public void FinalizarInstruccion(Instruccion instruccion){
        instruccionesFinalizadas.add(instruccion);
        ejecuciones.remove(instruccion);
    } */

    public void setInstrucciones(List<Instruccion> instrucciones) {
        this.instrucciones = instrucciones;
    }
}