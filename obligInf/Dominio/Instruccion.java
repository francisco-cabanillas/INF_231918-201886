package Dominio;


public class Instruccion {
    private int tiempo;
    private Recurso recurso;
    private String tipo;
    private String mensaje;
    
    public Instruccion(int unTiempo){
        this.setTiempo(unTiempo);
    }    
    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public Recurso getRecurso() {
        return recurso;
    }

    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    @Override
    public String toString(){
        return this.mensaje;
    }
}
