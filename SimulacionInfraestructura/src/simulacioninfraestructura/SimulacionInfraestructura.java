package simulacioninfraestructura;


import Dominio.Instruccion;
import Dominio.Proceso;
import Dominio.Programa;
import Dominio.Sistema;
import Dominio.Usuario;
import static java.lang.System.console;

public class SimulacionInfraestructura {

    public static void main(String[] args) {
        
           Sistema sis = new Sistema();
           //se crea un programa con un usuario y un proceso
           //sis.solicitudEjecutarPrograma(unUsuario, unPrograma);
           //se ejecuta un programa
           Usuario usuario1 = new Usuario("usuario 1");
           
           Instruccion A = new Instruccion(2); //pide impresora
           Instruccion B = new Instruccion(4); //usa impresora
           Instruccion C = new Instruccion(2); //devuelve impresora
           
           Programa programa1 = new Programa();
           
           //agrega instrucciones al programa
           programa1.getEjecucion().add(A);
           programa1.getEjecucion().add(B);
           programa1.getEjecucion().add(C);
           
           if( sis.solicitudEjecutarPrograma(usuario1, programa1) ){ //verificar si se puede crear un proceso para ese usuario con ese progrma
            
            Proceso proceso1 = new Proceso(usuario1, programa1);
            
           proceso1.setEstado(1); //1 = en ejecucion.
           proceso1.setPosicionEjecucion(0);
           System.out.println("Se crea proceso: usuario: " + usuario1 + " ejecuta programa: " + programa1);
           sis.getProcesos().add(proceso1);
           
           }else{
            System.out.println("El proceso: usuario: " + usuario1 + "  programa: " + programa1 + " denegado por falta de permisos.");
           }
           
           
    }
    
    
}
