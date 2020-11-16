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
           
           int Quantum = 15;
           ///////////////////////////////////////////////// agregue 2 procesos fijos al array (version 0)
           
           Usuario usuario1 = new Usuario("usuario 1");
           
           Instruccion A = new Instruccion(2); //pide impresora
           Instruccion B = new Instruccion(4); //usa impresora
           Instruccion C = new Instruccion(2); //devuelve impresora
           A.setTipo("pedir");
           B.setTipo("usar");
           C.setTipo("devolver");
           
           Programa programa1 = new Programa();
           Programa programa2 = new Programa();

           //agrega instrucciones al programa
           programa1.getInstrucciones().add(A);
           programa1.getInstrucciones().add(B);
           programa1.getInstrucciones().add(C);
           
           programa2.getInstrucciones().add(A);
           programa2.getInstrucciones().add(B);
           programa2.getInstrucciones().add(C);
           
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
    
    public void CorrerProcesos(Sistema sis, int Quantum){
        
        
        while(!sis.getProcesos().isEmpty()){
           int posicion = 0;
           //if(sis.getProcesos().get(posicion).getEstado() == 3){
            Proceso proceso = sis.getProcesos().get(posicion);
               
            proceso.setEstado(1);              
            CorrerPrograma(proceso, Quantum, sis);
            
       }
        
    }
    
    private void CorrerPrograma(Proceso proceso, int Quantum, Sistema sis) {
        int tiempo = 0;
        Programa programa = proceso.getPrograma();
        int posicion = proceso.getPosicionEjecucion();
        
        List<Instruccion> instrucciones = programa.getEjecuciones();
        Iterator<Instruccion> it = instrucciones.iterator();
        
        while(it.hasNext() && tiempo >= Quantum){
            Instruccion instruccion = it.next();
            tiempo += instruccion.getTiempo();
        
            if(instruccion.getTipo() == "pide"){
                    Boolean disponible = SolicitarRecurso(instruccion.getRecurso());
                    if(!disponible){
                        proceso.setEstado(0);
                        proceso.setPosicionEjecucion(posicion);
                        sis.getProcesosBloqueados().add(proceso);
                        //return false;
                    }
            }else if(instruccion.getTipo() == "devuelve"){ //si se libera un recurso, pasa de los bloqueados a los listos
                    sis.liberarRecurso(instruccion.getRecurso()); 
                    //le pregunte a caffa si es solo mover el primero de la lista de bloqueados que tenga ese recurso, a la lista de listos o todos los que lo tengan 
                    
                    sis.moverAListosProcesosConEseRecurso(instruccion.getRecurso());
            }else{
                
            }
            
           
            proceso.setPosicionEjecucion(posicion ++);      
        }
        
        if(!it.hasNext()){
            sis.getProcesos().remove(proceso); //si logro hacer todas las instrucciones, elimina el proceso de la lista de procesos
            //return true;
        }else{ //se fue por timeout
            sis.getProcesosListos().add(proceso);
            //return false;
        }
        
        
        
    }   
        
        
    
    
}
    

