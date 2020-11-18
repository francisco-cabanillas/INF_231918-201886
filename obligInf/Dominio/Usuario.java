package Dominio;

public class Usuario {
    private String nombre;
    private int id;
    
    public Usuario(String unNombre, int unId){
        this.setNombre(unNombre);
        this.setId(unId);
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    @Override
    public String toString(){
        return this.nombre;
    }
}
