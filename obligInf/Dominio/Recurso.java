package Dominio;

public class Recurso {
    private String nombre;
    private Boolean disponible;
    
    public Recurso(String unNombre){
        this.setNombre(unNombre);
        this.setDisponible(true);
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean estaDisponible) {
        this.disponible = estaDisponible;
    }

    @Override
    public String toString(){
        return this.nombre;
    }
    
}
