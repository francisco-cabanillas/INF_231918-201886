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

           Recurso impresora = new Recurso("Impresora");
           Recurso calculadora = new Recurso("Calculadora");
           Recurso pantalla = new Recurso("Pantalla");

           sis.getRecursos().add(impresora);
           sis.getRecursos().add(calculadora);
           sis.getRecursos().add(pantalla);
           
           Instruccion A = new Instruccion(2); //pide impresora
           Instruccion B = new Instruccion(4); //usa impresora
           Instruccion C = new Instruccion(2); //devuelve impresora
           A.setTipo("Pedir");
           B.setTipo("Usar");
           C.setTipo("Devolver");

           A.setRecurso(impresora);
           B.setRecurso(impresora);
           C.setRecurso(impresora);
           
           A.setMensaje(A.getTipo() + " " + A.getRecurso().getNombre());
           B.setMensaje(B.getTipo() + " " + B.getRecurso().getNombre());
           C.setMensaje(C.getTipo() + " " + C.getRecurso().getNombre());

           Instruccion D = new Instruccion(1); //pide calculadora
           Instruccion E = new Instruccion(6); //usa calculadora
           Instruccion F = new Instruccion(1); //devuelve calculadora
           D.setTipo("Pedir");
           E.setTipo("Usar");
           F.setTipo("Devolver");

           D.setRecurso(calculadora);
           E.setRecurso(calculadora);
           F.setRecurso(calculadora);
           
           D.setMensaje(D.getTipo() + " " + D.getRecurso().getNombre());
           E.setMensaje(E.getTipo() + " " + E.getRecurso().getNombre());
           F.setMensaje(F.getTipo() + " " + F.getRecurso().getNombre());

           Instruccion G = new Instruccion(1); //pide pantalla
           Instruccion H = new Instruccion(6); //usa pantalla
           Instruccion I = new Instruccion(1); //devuelve pantalla
           G.setTipo("Pedir");
           H.setTipo("Usar");
           I.setTipo("Devolver");

           G.setRecurso(pantalla);
           H.setRecurso(pantalla);
           I.setRecurso(pantalla);
           
           G.setMensaje(G.getTipo() + " " + G.getRecurso().getNombre());
           H.setMensaje(H.getTipo() + " " + H.getRecurso().getNombre());
           I.setMensaje(I.getTipo() + " " + I.getRecurso().getNombre());
           
           Programa programa1 = new Programa();
           Programa programa2 = new Programa();
           Programa programa3 = new Programa();

           //agrega instrucciones al programa
           programa1.getInstrucciones().add(C); //uso impresora
           programa1.getInstrucciones().add(E); //uso calculadora
           programa1.getInstrucciones().add(A); //pido impresora
           programa1.getInstrucciones().add(B); //uso impresora
           programa1.getInstrucciones().add(B); //uso impresora
           programa1.getInstrucciones().add(B); //uso impresora
           programa1.getInstrucciones().add(B); //uso impresora
           programa1.getInstrucciones().add(C); //devuelvo impresora
           programa1.getInstrucciones().add(D); //pido calculadora
           programa1.getInstrucciones().add(E); //uso calculadora
           programa1.getInstrucciones().add(F); //devuelvo calculadora
           
           programa2.getInstrucciones().add(G); //pido pantalla
           programa2.getInstrucciones().add(H); //uso pantalla
           programa2.getInstrucciones().add(I); //devuelvo pantalla
           programa2.getInstrucciones().add(A); //pido impresora
           programa2.getInstrucciones().add(B); //uso impresora
           programa2.getInstrucciones().add(C); //devuelvo impresora
           programa2.getInstrucciones().add(G); //pido pantalla
           programa2.getInstrucciones().add(H); //uso pantalla
           programa2.getInstrucciones().add(I); //devuelvo pantalla

           programa3.getInstrucciones().add(D); //pido calculadora
           programa3.getInstrucciones().add(E); //uso calculadora
           programa3.getInstrucciones().add(H); //intento usar pantalla
           programa3.getInstrucciones().add(F); //devuelvo calculadora
           programa3.getInstrucciones().add(G); //pido pantalla
           programa3.getInstrucciones().add(H); //uso pantalla
           programa3.getInstrucciones().add(I); //devuelvo pantalla

           //verifico si se puede crear programa con la matriz programas if sis.solicitudEjecutarPrograma(usuario1, programa1)
           //verifico si ese usuario puede ejecutar todos los recursos de ese programa
           //si se puede, se crea el proceso
           Proceso proc1 = new Proceso(usuario1, programa1);
           Proceso proc2 = new Proceso(usuario1, programa2);
           Proceso proc3 = new Proceso(usuario1, programa3);
           
           proc1.setNumero(1);
           proc2.setNumero(2);
           proc3.setNumero(3);
           
           
           sis.getProcesosListos().add(proc1);
           sis.getProcesosListos().add(proc2);
           sis.getProcesosListos().add(proc3);

           sis.correrProcesos(Quantum);

    }
    
    
        
        
    
    
}
    

