package Dominio;

public class Recurso {
    private String nombre;
    private Boolean enUso;
    
    public Recurso(String unNombre){
        this.nombre = unNombre;
        this.enUso = false;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getEnUso() {
        return enUso;
    }

    public void setEnUso(Boolean enUso) {
        this.enUso = enUso;
    }
    
}
