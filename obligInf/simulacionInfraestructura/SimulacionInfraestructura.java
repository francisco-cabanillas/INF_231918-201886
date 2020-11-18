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
           //sis.solicitudRecursos(unUsuario, unPrograma);
           //se ejecuta un programa
           
           int Quantum = 15;
          
           
           Usuario usuario1 = new Usuario("Juan", 0);
           Usuario usuario2 = new Usuario("Pepe", 1);
           Usuario usuario3 = new Usuario("Maria", 2);

           sis.getUsuarios().add(usuario1);
           sis.getUsuarios().add(usuario2);
           sis.getUsuarios().add(usuario3);

           Recurso impresora = new Recurso("Impresora", 0);
           Recurso calculadora = new Recurso("Calculadora", 1);
           Recurso pantalla = new Recurso("Pantalla", 2);

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

           

          

          ///////////////////////////////////////////////////// VERSION 3 /////////////////////////////////////////////////////

          /* VERSION 3…………… multiplicidad de usuarios. Todos los programas tienen permiso de ejecución.  Sólo usamos la matriz de permisos usuarios contra recursos */

           //crear matriz Usuarios-Recursos
           int permisosRecursos[][] = new int[sis.getUsuarios().size()][sis.getRecursos().size()];

           //filas usuarios, columnas recursos
           permisosRecursos[0][0] = 0; //fila 0 = usuario 1. //col 0 = recurso =
           permisosRecursos[0][1] = 1; //fila 0 = usuario 0. //col 1 = recurso 1
           permisosRecursos[0][2] = 0;
           permisosRecursos[1][0] = 1;
           permisosRecursos[1][1] = 0;
           permisosRecursos[1][2] = 1;
           permisosRecursos[2][0] = 0;
           permisosRecursos[2][1] = 0;
           permisosRecursos[2][2] = 1;

           //crear matriz Usuarios-Programas
           int permisosProgramas[][] = new int[sis.getUsuarios().size()][sis.getProgramas().size()];

           //filas usuarios, columnas recursos
           permisosRecursos[0][0] = 0; //fila 0 = usuario 1. //col 0 = programa 0
           permisosRecursos[0][1] = 1; //fila 0 = usuario 0. //col 1 = programa 1
           permisosRecursos[0][2] = 0;
           permisosRecursos[1][0] = 1;
           permisosRecursos[1][1] = 0;
           permisosRecursos[1][2] = 1;
           permisosRecursos[2][0] = 0;
           permisosRecursos[2][1] = 0;
           permisosRecursos[2][2] = 1;


           //Crear los procesos validando permisos
       
           if(sis.solicitudRecursos(usuario1, programa1, permisosRecursos) && sis.solicitudEjecutarPrograma(usuario1, programa1, permisosProgramas)){
            Proceso proc1 = new Proceso(usuario1, programa1);
            proc1.setNumero(1);
            sis.getProcesosListos().add(proc1);
           }

           if(sis.solicitudRecursos(usuario2, programa2, permisosRecursos) && sis.solicitudEjecutarPrograma(usuario2, programa2, permisosProgramas)){
            Proceso proc2 = new Proceso(usuario2, programa2);
            proc2.setNumero(2);
            sis.getProcesosListos().add(proc2);
           }

           if(sis.solicitudRecursos(usuario3, programa3, permisosRecursos) && sis.solicitudEjecutarPrograma(usuario3, programa3, permisosProgramas)){
            Proceso proc3 = new Proceso(usuario3, programa3);
            proc3.setNumero(3);
            sis.getProcesosListos().add(proc3);
           }

           sis.correrProcesos(Quantum);

           

    }
    
    
        
        
    
    
}
    

