package simulacionInfraestructura;

import java.util.Scanner;

import Dominio.Instruccion;
import Dominio.Proceso;
import Dominio.Recurso;
import Dominio.Usuario;
import Logica.Sistema;

public class Consola {
    private Scanner in = new Scanner(System.in); 

    public void PrintInicial(){
        System.out.println("Simulacion Obligatorio Infraestructura\n");
    }

    public void PrintFinal(){
        System.out.println("La aplicacion fue finalizada.");
    }

    public int PrintMenuPrincipal(){
        
        System.out.println("------ Menu ------");
        System.out.println("Opcion 1: Correr demo");
        System.out.println("Opcion 2: Ingresar 3 recursos, 3 usuarios y el Quantum total para correr en la demo");
        System.out.println("Opcion 3: Finalizar");

        int retorno = 0;
        System.out.print("Ingrese un valor (1/2/3) dependiendo su eleccion: ");
        boolean ingresoCorrecto = false;
        while(!ingresoCorrecto){
            try{
                retorno = in.nextInt();
                if(retorno == 1 || retorno == 2 || retorno == 3){
                    ingresoCorrecto = true;
                } else {
                    System.out.print("Vuelva a ingresar su opcion (1/2/3): ");
                }
            }
            catch(Exception e){
                System.out.print("Vuelva a ingresar su opcion (1/2/3): ");
                retorno = 0;
            }
        }

        return retorno;
    }

    public String PedirDato(int numDato, String tipo){
        String nombre = "";
        boolean ingresoCorrecto = false;

        while(!ingresoCorrecto){
            System.out.print("Ingrese el nombre del " + tipo + " " + numDato + "/3: ");
            nombre = in.next();
            if(!nombre.equals("") && !nombre.equals(" ")) {
                ingresoCorrecto = true;
                System.out.println("\n El " + tipo + " '" + nombre + "' fue añadido exitosamente.\n");
            } else {
                System.out.println("\n El nombre del " + tipo + " no puede ser vacio. \n");
            }
        }
        return nombre;
    }

    public int[][] PedirPermisos(int[][] matrizPermisos, Sistema sis){
        int permiso = 0;
        boolean ingresoCorrecto;
        for(int i =0; i<matrizPermisos.length; i++){
            for(int j=0; j<matrizPermisos[i].length; j++){
                ingresoCorrecto = false;

                while(!ingresoCorrecto){
                    System.out.print("Ingrese 1 o 0 para darle permisos al usuario " + sis.getUsuarios().get(i) + " sobre el recurso " + sis.getRecursos().get(j)+ ":  ");                
                    permiso = in.nextInt();
                    if(permiso == 0 || permiso == 1) {
                        ingresoCorrecto = true;
                        
                    } else {
                        System.out.println("\n Permiso incorrecto, solo valores 1 o 0 \n");
                    }
                }
  
                matrizPermisos[i][j] = permiso;
            }
        }
        System.out.println("\n Permisos agregados exitosamente. \n");
        return matrizPermisos;
    }
    
    
	public int PedirQuantum() {
        int quantum = 0;
        boolean ingresoCorrecto = false;
        
        System.out.print("Ingrese un valor para representar el quantum (ciclo total por proceso): \n ");

        while(!ingresoCorrecto){
            try{
                quantum = in.nextInt();
                if(quantum >= 0){
                    ingresoCorrecto = true;
                } else {
                    System.out.print("Vuelva a ingresar su opcion con un valor positivo: ");
                }
            }
            catch(Exception e){
                System.out.print("Vuelva a ingresar su opcion con un valor numerico: ");
                quantum = 0;
            }
        }

        return quantum;
	}

	public void Print(String string) {
        System.out.println("\n" + string);
	}

	public void PrintSizeProcesos(int listos, int finalizados, int bloqueados) {
        Print("-- El array procesos listos tiene " + listos + " procesos. --");
        Print("-- El array procesos finalizados tiene " + finalizados + " procesos. --");
        Print("-- El array procesos bloqueados quedo con " + bloqueados + " procesos. --\n");
	}

	public void PrintDenegacionEjecucion(int idPrograma, String nombreUsuario) {
        Print("ALERTA: Se denegó la ejecucion del programa " + idPrograma + " por parte del usuario "+ nombreUsuario);
	}

	public void PrintCorrerPrograma(Proceso proceso) {
        Print("--- Correr programa de proceso " + proceso + " ---");
	}

	public void PrintRecursoEnUso(Proceso proceso, Instruccion instruccion) {
        Print("ALERTA: El proceso " + proceso + " no pudo completar la tarea " + instruccion + " porque el recurso se encuentra en uso.");
	}

	public void PrintMensajeInstruccion(String mensaje) {
        Print(mensaje);
	}

	public void PrintDenegacionRecurso(Proceso proceso, String nombreUsuario, Recurso recurso) {
        Print("ACCESO DENEGADO: El proceso " + proceso +" del usuario "+ nombreUsuario + " solicitó acceso al recurso " + recurso + " fue denegado por falta de permisos, se corta la ejecución del proceso. ");
	}

	public void PrintFaltaSolicitudRecurso(Proceso proceso, Recurso recurso) {
        Print("ERROR: El proceso " + proceso + " intento utilizar el recurso (" + recurso + ") sin solicitarlo previamente.");
	}

	public void PrintFaltaCpu(Proceso proceso) {
        Print("\nProceso "+ proceso +" perdio CPU por timeOut en posicion " +  proceso.getPosicionEjecucion() + "\n");
	}

	public void PrintEjecucionExitosa(Proceso proceso) {
        Print("\n Proceso "+ proceso +" se ejecuto completamente. \n");
	}
}