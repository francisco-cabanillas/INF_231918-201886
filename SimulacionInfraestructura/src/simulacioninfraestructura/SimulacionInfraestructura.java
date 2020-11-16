package simulacioninfraestructura;


import Dominio.Instruccion;
import Dominio.Proceso;
import Dominio.Programa;
import Dominio.Sistema;
import Dominio.Usuario;
import static java.lang.System.console;
import java.util.Iterator;
import java.util.List;

public class SimulacionInfraestructura {

    public static void main(String[] args) {
        
           Sistema sis = new Sistema();
           //se crea un programa con un usuario y un proceso
           //sis.solicitudEjecutarPrograma(unUsuario, unPrograma);
           //se ejecuta un programa
           
           ///////////////////////////////////////////////// agregue 2 procesos fijos al array (version 0)
           
           Usuario usuario1 = new Usuario("usuario 1");
           
           Instruccion A = new Instruccion(2); //pide impresora
           Instruccion B = new Instruccion(4); //usa impresora
           Instruccion C = new Instruccion(2); //devuelve impresora
           
           Programa programa1 = new Programa();
           Programa programa2 = new Programa();

           //agrega instrucciones al programa
           programa1.getEjecucion().add(A);
           programa1.getEjecucion().add(B);
           programa1.getEjecucion().add(C);
           
           programa2.getEjecucion().add(A);
           programa2.getEjecucion().add(B);
           programa2.getEjecucion().add(C);
           
           //verifico si se puede crear programa con la matriz programas if sis.solicitudEjecutarPrograma(usuario1, programa1)
           //verifico si ese usuario puede ejecutar todos los recursos de ese programa
           //si se puede, se crea el proceso
           Proceso proc1 = new Proceso(usuario1, programa1);
           Proceso proc2 = new Proceso(usuario1, programa2);
           
           proc1.setEstado(3); //3 = listo.
           proc1.setPosicionEjecucion(0);
            
           proc2.setEstado(3); //3 = listo.
           proc2.setPosicionEjecucion(0);
           
           sis.getProcesosListos().add(proc1);
           sis.getProcesosListos().add(proc2);
           ////////////////////////////////////////////////////////
           
           
           
    }
    
    public void CorrerProcesos(Sistema sis){
        
        
    }
    
    private Boolean CorrerPrograma(Proceso proceso, int Quantum) {
        int tiempo = 0;
        Programa programa = proceso.getPrograma();
        List<Instruccion> instrucciones = programa.getEjecucionesPendientes();
        Iterator<Instruccion> it = instrucciones.iterator();
        while(it.hasNext() && tiempo >= this.Quantum){
            Instruccion instruccion = it.next();
            tiempo += instruccion.getTiempo();
            
            //Validar el uso de recursos en la matriz
            
            if(instruccion.getRecurso() != null){
                Boolean disponible = SolicitarRecurso(instruccion.getRecurso());
                if(!disponible){
                    return false;
                }
            }
            //Validar el uso de recursos en la matriz
            
            
            //Falta seguirlo creo
        }
        if(programa.getEjecucionesPendientes().isEmpty()){
            return true;
        }
        else {
            return false;
        }
        
    
    
}
