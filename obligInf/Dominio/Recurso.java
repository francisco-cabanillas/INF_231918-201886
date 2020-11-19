package Dominio;

public class Recurso {
    private int id;
    private String nombre;
    private Boolean disponible;
    
    public Recurso(String unNombre, int unId){
        this.setNombre(unNombre);
        this.setId(unId);
        this.setDisponible(true);
        
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
