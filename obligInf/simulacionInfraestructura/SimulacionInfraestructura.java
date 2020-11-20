package simulacionInfraestructura;

import Logica.Sistema;
import Dominio.Instruccion;
import Dominio.Usuario;
import Dominio.Programa;
import Dominio.Proceso;
import Dominio.Recurso;



public class SimulacionInfraestructura {

    private static Consola consola;

    private static Recurso recursoDemo1 = new Recurso("Impresora", 0);
    private static Recurso recursoDemo2 = new Recurso("Calculadora", 1);
    private static Recurso recursoDemo3 = new Recurso("Pantalla", 2);

    public static void main(String[] args) {

        consola = new Consola();
        consola.PrintInicial();

        int opcion = 0;

        int correrDemo = 1;
        int correrDatosPropios = 2;
        int finalizar = 3;

        while (opcion != finalizar) {
            opcion = consola.PrintMenuPrincipal();

            if (opcion == correrDemo) {
                int quantumDemo = 15;
                CorrerDemo(recursoDemo1, recursoDemo2, recursoDemo3, quantumDemo);
            } else if(opcion == correrDatosPropios){
                Recurso recurso1 = consola.PedirRecurso(1);
                Recurso recurso2 = consola.PedirRecurso(2);
                Recurso recurso3 = consola.PedirRecurso(3);

                int quantum = consola.PedirQuantum();

                CorrerDemo(recurso1, recurso2, recurso3, quantum);
            } else {
                consola.PrintFinal();
            }
        }
    }

    public static void CorrerDemo(Recurso recurso1, Recurso recurso2, Recurso recurso3, int quantum) {
        Sistema sis = new Sistema();

        Usuario usuario1 = new Usuario("Juan", 0);
        Usuario usuario2 = new Usuario("Pepe", 1);
        Usuario usuario3 = new Usuario("Maria", 2);

        sis.getUsuarios().add(usuario1);
        sis.getUsuarios().add(usuario2);
        sis.getUsuarios().add(usuario3);

        ///////////////////////////////////////////////////// VERSION 2 /////////////////////////////////////////////////////

        sis.getRecursos().add(recurso1);
        sis.getRecursos().add(recurso2);
        sis.getRecursos().add(recurso3);

        ///////////////////////////////////////////////////// FIN VERSION 2 /////////////////////////////////////////////////////

        
        Instruccion A = new Instruccion(2); //pide impresora
        Instruccion B = new Instruccion(4); //usa impresora
        Instruccion C = new Instruccion(2); //devuelve impresora
        A.setTipo("Pedir");
        B.setTipo("Usar");
        C.setTipo("Devolver");

        A.setRecurso(recurso1);
        B.setRecurso(recurso1);
        C.setRecurso(recurso1);
        
        A.setMensaje(A.getTipo() + " " + A.getRecurso().getNombre());
        B.setMensaje(B.getTipo() + " " + B.getRecurso().getNombre());
        C.setMensaje(C.getTipo() + " " + C.getRecurso().getNombre());


        Instruccion D = new Instruccion(1); //pide calculadora
        Instruccion E = new Instruccion(6); //usa calculadora
        Instruccion F = new Instruccion(1); //devuelve calculadora
        D.setTipo("Pedir");
        E.setTipo("Usar");
        F.setTipo("Devolver");

        D.setRecurso(recurso2);
        E.setRecurso(recurso2);
        F.setRecurso(recurso2);
        
        D.setMensaje(D.getTipo() + " " + D.getRecurso().getNombre());
        E.setMensaje(E.getTipo() + " " + E.getRecurso().getNombre());
        F.setMensaje(F.getTipo() + " " + F.getRecurso().getNombre());

        Instruccion G = new Instruccion(1); //pide pantalla
        Instruccion H = new Instruccion(6); //usa pantalla
        Instruccion I = new Instruccion(1); //devuelve pantalla
        G.setTipo("Pedir");
        H.setTipo("Usar");
        I.setTipo("Devolver");

        G.setRecurso(recurso3);
        H.setRecurso(recurso3);
        I.setRecurso(recurso3);
        
        G.setMensaje(G.getTipo() + " " + G.getRecurso().getNombre());
        H.setMensaje(H.getTipo() + " " + H.getRecurso().getNombre());
        I.setMensaje(I.getTipo() + " " + I.getRecurso().getNombre());

        
        Programa programa1 = new Programa();
        Programa programa2 = new Programa();
        Programa programa3 = new Programa();

        programa1.setId(1);
        programa2.setId(2);
        programa3.setId(3);

        //Agrega instrucciones al programa
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

        sis.getProgramas().add(programa1);
        sis.getProgramas().add(programa2);
        sis.getProgramas().add(programa3);

        

        ///////////////////////////////////////////////////// VERSION 3 /////////////////////////////////////////////////////

        /* VERSION 3…………… multiplicidad de usuarios. Todos los programas tienen permiso de ejecución.  Sólo usamos la matriz de permisos usuarios contra recursos */

        //Crear matriz Usuarios-Recursos
        if(quantum == 15){
            int permisosRecursos[][] = new int[sis.getUsuarios().size()][sis.getRecursos().size()];

            //Filas usuarios, columnas recursos
            permisosRecursos[0][0] = 0; //fila 0 = usuario 1. //col 0 = recurso =
            permisosRecursos[0][1] = 1; //fila 0 = usuario 0. //col 1 = recurso 1
            permisosRecursos[0][2] = 0;
            permisosRecursos[1][0] = 1;
            permisosRecursos[1][1] = 0;
            permisosRecursos[1][2] = 1;
            permisosRecursos[2][0] = 0;
            permisosRecursos[2][1] = 0;
            permisosRecursos[2][2] = 1;

            sis.setPermisosRecursos(permisosRecursos);
        } else { //Si la prueba es con datos del usuario se asume permiso total sobre ellos
            int permisosRecursos[][] = new int[sis.getUsuarios().size()][sis.getRecursos().size()];

            //Filas usuarios, columnas recursos
            permisosRecursos[0][0] = 1; //fila 0 = usuario 1. //col 0 = recurso =
            permisosRecursos[0][1] = 1; //fila 0 = usuario 0. //col 1 = recurso 1
            permisosRecursos[0][2] = 1;
            permisosRecursos[1][0] = 1;
            permisosRecursos[1][1] = 1;
            permisosRecursos[1][2] = 1;
            permisosRecursos[2][0] = 1;
            permisosRecursos[2][1] = 1;
            permisosRecursos[2][2] = 1;

            sis.setPermisosRecursos(permisosRecursos);
        }

        ///////////////////////////////////////////////////// VERSION 4 /////////////////////////////////////////////////////

        /* VERSION 4…………… multiplicidad de usuarios. Los programas tienen permisos de ejecución.  Usamos las matrices de permisos usuarios contra recursos y de usuarios contra programas */

        //Crear matriz Usuarios-Programas
        int permisosProgramas[][] = new int[sis.getUsuarios().size()][sis.getProgramas().size()];

        //Filas usuarios, Columnas recursos
        permisosProgramas[0][0] = 0; //fila 0 = usuario 1. //col 0 = programa 0
        permisosProgramas[0][1] = 1; //fila 0 = usuario 0. //col 1 = programa 1
        permisosProgramas[0][2] = 0;
        permisosProgramas[1][0] = 1;
        permisosProgramas[1][1] = 0;
        permisosProgramas[1][2] = 1;
        permisosProgramas[2][0] = 0;
        permisosProgramas[2][1] = 0;
        permisosProgramas[2][2] = 1;

        sis.setPermisosProgramas(permisosProgramas);


        //Crear los procesos validando permisos
    
        if(sis.solicitudEjecutarPrograma(usuario1, programa1)){
            Proceso proc1 = new Proceso(usuario1, programa1);
            proc1.setNumero(1);
            sis.getProcesosListos().add(proc1);
        }

        if(sis.solicitudEjecutarPrograma(usuario2, programa2)){
            Proceso proc2 = new Proceso(usuario2, programa2);
            proc2.setNumero(2);
            sis.getProcesosListos().add(proc2);
        }

        if(sis.solicitudEjecutarPrograma(usuario3, programa3)){
            Proceso proc3 = new Proceso(usuario3, programa3);
            proc3.setNumero(3);
            sis.getProcesosListos().add(proc3);
        }

        sis.correrProcesos(quantum);
    }
    
}
    

