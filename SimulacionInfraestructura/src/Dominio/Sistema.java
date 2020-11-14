/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.util.ArrayList;

/**
 *
 * @author Paula Hernandez
 */
public class Sistema {
   private final ArrayList<Usuario> usuarios = new ArrayList<>();
   private final ArrayList<Recurso> recursos = new ArrayList<>();
   private final ArrayList<Programa> programas = new ArrayList<>();
   
   private final ArrayList<Proceso> procesos = new ArrayList<>(); //procesos con su estado y posicionEjecucion
   private final ArrayList<Proceso> procesosListos = new ArrayList<>(); //procesos listos en orden, para ejecutsr el siguiente
   private final ArrayList<Proceso> procesosBloqueados = new ArrayList<>(); //procesos bloqueados (sin orden)
                                                                            //esperan eventos para ser desbloqueados.
   
   
   public boolean solicitudEjecutarPrograma(Usuario unUsuario, Programa unPrograma){
        //1. se chequea en matriz de Usuario-Programa (si el usuario puede usar ese programa)
        //2. se chequea en matriz Usuario-recursos (si el usuario puede usar todos los recursos que estan en ese programa)
        boolean puedeCrear = true;
        
        if(puedeCrear){ //se crea un proceso para ese usuario con ese progrma
            
            Proceso proceso1 = new Proceso(unUsuario, unPrograma);
            
           proceso1.setEstado(1); //1 = en ejecucion.
           proceso1.setPosicionEjecucion(0);
           System.out.println("Se crea proceso: usuario: " + unUsuario + " ejecuta programa: " + unPrograma);
           return true;
           
        }else{
            System.out.println("El proceso: usuario: " + unUsuario + "  programa: " + unPrograma + " denegado por falta de permisos.");
            return false;  //y pasa al siguiente proceso.
        }
                
    }
}
