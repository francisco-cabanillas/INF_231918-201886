package Dominio;

public class Usuario {
    private String nombre;
    
    public Usuario(String unNombre){
        this.nombre = unNombre;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
