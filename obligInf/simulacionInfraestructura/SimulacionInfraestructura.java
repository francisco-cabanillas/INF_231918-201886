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

    private static Usuario usuarioDemo1 = new Usuario("Juan", 0);
    private static Usuario usuarioDemo2 = new Usuario("Pepe", 1);
    private static Usuario usuarioDemo3 = new Usuario("Maria", 2);

    public static void main(String[] args) {

        consola = new Consola();
        consola.PrintInicial();

        int opcion = 0;

        int correrDemo = 1;
        int correrDatosPropios = 2;
        int finalizar = 3;
        
        Recurso[] recursos = new Recurso[3];
        Usuario[] usuarios = new Usuario[3];

        while (opcion != finalizar) {
            opcion = consola.PrintMenuPrincipal();

            if (opcion == correrDemo) {
                int quantumDemo = 15;

                recursos[0] = recursoDemo1;
                recursos[1] = recursoDemo2;
                recursos[2] = recursoDemo3;

                usuarios[0] = usuarioDemo1;
                usuarios[1] = usuarioDemo2;
                usuarios[2] = usuarioDemo3;

                CorrerDemo(recursos, usuarios, quantumDemo);
            } else if(opcion == correrDatosPropios){
                String tipo = "recurso";
                Recurso recurso1 = new Recurso(consola.PedirDato(1, tipo), 0);
                Recurso recurso2 = new Recurso(consola.PedirDato(2, tipo), 1);
                Recurso recurso3 = new Recurso(consola.PedirDato(3, tipo), 2);

                recursos[0] = recurso1;
                recursos[1] = recurso2;
                recursos[2] = recurso3;
                
                tipo = "usuario";
                Usuario usuario1 = new Usuario(consola.PedirDato(1, tipo), 0);
                Usuario usuario2 = new Usuario(consola.PedirDato(2, tipo), 1);
                Usuario usuario3 = new Usuario(consola.PedirDato(3, tipo), 2);

                usuarios[0] = usuario1;
                usuarios[1] = usuario2;
                usuarios[2] = usuario3;

                int quantum = consola.PedirQuantum();

                CorrerDemo(recursos, usuarios, quantum);
            } else {
                consola.PrintFinal();
            }
        }
    }

    public static void CorrerDemo(Recurso[] recursos, Usuario[] usuarios, int quantum) {
        Sistema sis = new Sistema();

        sis.getUsuarios().add(usuarios[0]);
        sis.getUsuarios().add(usuarios[1]);
        sis.getUsuarios().add(usuarios[2]);

        ///////////////////////////////////////////////////// VERSION 2 /////////////////////////////////////////////////////

        sis.getRecursos().add(recursos[0]);
        sis.getRecursos().add(recursos[1]);
        sis.getRecursos().add(recursos[2]);

        ///////////////////////////////////////////////////// FIN VERSION 2 /////////////////////////////////////////////////////

        
        Instruccion A = new Instruccion(2); //pide impresora
        Instruccion B = new Instruccion(4); //usa impresora
        Instruccion C = new Instruccion(2); //devuelve impresora
        A.setTipo("Pedir");
        B.setTipo("Usar");
        C.setTipo("Devolver");

        A.setRecurso(recursos[0]);
        B.setRecurso(recursos[0]);
        C.setRecurso(recursos[0]);
        
        A.setMensaje(A.getTipo() + " " + A.getRecurso().getNombre());
        B.setMensaje(B.getTipo() + " " + B.getRecurso().getNombre());
        C.setMensaje(C.getTipo() + " " + C.getRecurso().getNombre());


        Instruccion D = new Instruccion(1); //pide calculadora
        Instruccion E = new Instruccion(6); //usa calculadora
        Instruccion F = new Instruccion(1); //devuelve calculadora
        D.setTipo("Pedir");
        E.setTipo("Usar");
        F.setTipo("Devolver");

        D.setRecurso(recursos[1]);
        E.setRecurso(recursos[1]);
        F.setRecurso(recursos[1]);
        
        D.setMensaje(D.getTipo() + " " + D.getRecurso().getNombre());
        E.setMensaje(E.getTipo() + " " + E.getRecurso().getNombre());
        F.setMensaje(F.getTipo() + " " + F.getRecurso().getNombre());

        Instruccion G = new Instruccion(1); //pide pantalla
        Instruccion H = new Instruccion(6); //usa pantalla
        Instruccion I = new Instruccion(1); //devuelve pantalla
        G.setTipo("Pedir");
        H.setTipo("Usar");
        I.setTipo("Devolver");

        G.setRecurso(recursos[2]);
        H.setRecurso(recursos[2]);
        I.setRecurso(recursos[2]);
        
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
        if(recursos[0].getNombre().equals("Impresora")){
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
            permisosRecursos = consola.PedirPermisos(permisosRecursos, sis);
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
    
        if(sis.solicitudEjecutarPrograma(usuarios[0], programa1)){
            Proceso proceso1 = new Proceso(usuarios[0], programa1);
            proceso1.setNumero(1);
            sis.getProcesosListos().add(proceso1);
        }

        if(sis.solicitudEjecutarPrograma(usuarios[1], programa2)){
            Proceso proceso2 = new Proceso(usuarios[1], programa2);
            proceso2.setNumero(2);
            sis.getProcesosListos().add(proceso2);
        }

        if(sis.solicitudEjecutarPrograma(usuarios[2], programa3)){
            Proceso proceso3 = new Proceso(usuarios[2], programa3);
            proceso3.setNumero(3);
            sis.getProcesosListos().add(proceso3);
        }

        if(sis.solicitudEjecutarPrograma(usuarios[1], programa1)){
            Proceso proceso4 = new Proceso(usuarios[1], programa1);
            proceso4.setNumero(4);
            sis.getProcesosListos().add(proceso4);
        }

        if(sis.solicitudEjecutarPrograma(usuarios[2], programa2)){
            Proceso proceso5 = new Proceso(usuarios[2], programa2);
            proceso5.setNumero(5);
            sis.getProcesosListos().add(proceso5);
        }

        if(sis.solicitudEjecutarPrograma(usuarios[0], programa3)){
            Proceso proceso6 = new Proceso(usuarios[0], programa3);
            proceso6.setNumero(6);
            sis.getProcesosListos().add(proceso6);
        }

        sis.correrProcesos(quantum);
    }
    
}
    

