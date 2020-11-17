package simulacionInfraestructura;

import Dominio.Instruccion;
import Dominio.Sistema;
import Dominio.Usuario;
import Dominio.Programa;
import Dominio.Proceso;
import Dominio.Recurso;



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
           A.setTipo("Pedir");
           B.setTipo("Usar");
           C.setTipo("Devolver");

           
           
           A.setMensaje("Pedir impresora");
           B.setMensaje("Usar impresora");
           C.setMensaje("Devolver impresora");
           
           Programa programa1 = new Programa();
           Programa programa2 = new Programa();

           //agrega instrucciones al programa
           programa1.getInstrucciones().add(A);
           programa1.getInstrucciones().add(B);
           programa1.getInstrucciones().add(B);
           programa1.getInstrucciones().add(B);
           programa1.getInstrucciones().add(B);
           programa1.getInstrucciones().add(B);
           programa1.getInstrucciones().add(B);
           programa1.getInstrucciones().add(B);
           
           programa2.getInstrucciones().add(A);
           programa2.getInstrucciones().add(B);
           programa2.getInstrucciones().add(C);

           //verifico si se puede crear programa con la matriz programas if sis.solicitudEjecutarPrograma(usuario1, programa1)
           //verifico si ese usuario puede ejecutar todos los recursos de ese programa
           //si se puede, se crea el proceso
           Proceso proc1 = new Proceso(usuario1, programa1);
           Proceso proc2 = new Proceso(usuario1, programa2);
           
           proc1.setNumero(1);
           proc2.setNumero(2);
           
           proc1.setEstado(3); //3 = listo.
           proc1.setPosicionEjecucion(0);
            
           proc2.setEstado(3); //3 = listo.
           proc2.setPosicionEjecucion(0);
           
           sis.getProcesos().add(proc1);
           sis.getProcesos().add(proc2);
           sis.correrProcesos(8);

           System.out.println("El array procesos listos tiene " + sis.getProcesosListos().size() + " procesos.");
           System.out.println("El array procesos tiene " + sis.getProcesos().size() + " procesos.");
           
           
           
    }
    
    
        
        
    
    
}
    

