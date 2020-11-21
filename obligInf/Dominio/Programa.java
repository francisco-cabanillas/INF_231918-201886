package Dominio;

import java.util.ArrayList;
import java.util.List;

public class Programa {
    private List<Instruccion> instrucciones;
    private int id;

    public Programa() {
        List<Instruccion> instrucciones = new ArrayList<>();
        this.setInstrucciones(instrucciones);
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Instruccion> getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(List<Instruccion> instrucciones) {
        this.instrucciones = instrucciones;
    }

    public boolean utilizaRecurso(int idRecurso){
        for(int i = 0; i<this.getInstrucciones().size(); i++){
            if(this.getInstrucciones().get(i).getRecurso().getId() == idRecurso){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString(){
        return "" + this.id;
    }
}