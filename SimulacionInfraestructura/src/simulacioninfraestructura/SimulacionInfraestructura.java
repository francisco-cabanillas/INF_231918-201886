package simulacioninfraestructura;


import Dominio.Proceso;
import Dominio.Programa;
import Dominio.Usuario;
import static java.lang.System.console;

public class SimulacionInfraestructura {

    public static void main(String[] args) {

    }
    public boolean solicitudEjecutarPrograma(Usuario unUsuario, Programa unPrograma){
        //1. se chequea en matriz de Usuario-Programa (si el usuario puede usar ese programa)
        //2. se chequea en matriz Usuario-recursos (si el usuario puede usar todos los recursos que estan en ese programa)
        boolean puedeCrear = true;
        
        if(puedeCrear){ //se crea un proceso para ese usuario con ese progrma
            
            Proceso proceso1 = new Proceso(unUsuario, unPrograma);
            
           proceso1.setEstado(1); //1 = en ejecucion.
           proceso1.setPosicionEjecucion(0);
           System.out.println("Se crea proceso: usuario: " + unUsuario + " ejecuta programa: " + unPrograma);
           return true;
           
        }else{
            System.out.println("El proceso: usuario: " + unUsuario + "  programa: " + unPrograma + " denegado por falta de permisos.");
            return false;  //y pasa al siguiente proceso.
        }
                
    }
    
}
