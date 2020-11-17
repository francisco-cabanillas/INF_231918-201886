package Dominio;

import java.util.ArrayList;
import java.util.List;

public class Programa {
    private List<Instruccion> instrucciones;

    public Programa() {
        List<Instruccion> instrucciones = new ArrayList<>();
        this.setInstrucciones(instrucciones);
    }
    

    public List<Instruccion> getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(List<Instruccion> instrucciones) {
        this.instrucciones = instrucciones;
    }
}