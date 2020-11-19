package simulacionInfraestructura;

import java.util.Scanner;

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
        System.out.println("Opcion 2: Ingresar datos y correrlos");
        System.out.println("Opcion 3: Finalizar");

        int retorno = 0;
        System.out.print("Ingrese un valor (1/2/3) dependiendo su eleccion: ");
        boolean ingresoCorrecto = false;
        while(!ingresoCorrecto){
            try{
                retorno = in.nextInt();;
                if(retorno == 1 || retorno == 2 || retorno == 3){
                    ingresoCorrecto = true;
                } else {
                    System.out.print("Vuelva a ingresar su opcion (1/2/3): ");
                }
            }
            catch(Exception e){
                System.out.print("Vuelva a ingresar su opcion (1/2/3): ");
            }
        }

        return retorno;
    }

}