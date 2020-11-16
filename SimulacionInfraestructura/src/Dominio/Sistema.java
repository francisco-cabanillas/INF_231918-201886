/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Paula Hernandez
 */
public class Sistema {
   private final ArrayList<Usuario> usuarios = new ArrayList<>();
   private final ArrayList<Recurso> recursos = new ArrayList<>();
   private final ArrayList<Programa> programas = new ArrayList<>();
   
   private final ArrayList<Proceso> procesos = new ArrayList<>(); //procesos (con su estado y posicionEjecucion)
   private final ArrayList<Proceso> procesosListos = new ArrayList<>(); //procesos listos en orden, para ejecutsr el siguiente en la lista
   private final ArrayList<Proceso> procesosBloqueados = new ArrayList<>(); //procesos bloqueados (sin orden)
                                                                            //esperan eventos para ser desbloqueados.
   private final int Quantum = 15;
   
   
   public Sistema(){
       
   }
   
   
   public boolean solicitudEjecutarPrograma(Usuario unUsuario, Programa unPrograma){
        //1. se chequea en matriz de Usuario-Programa (si el usuario puede usar ese programa)
        //2. se chequea en matriz Usuario-recursos (si el usuario puede usar todos los recursos que estan en ese programa)
        
        boolean puedeCrear = true;
        return puedeCrear;        
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public ArrayList<Recurso> getRecursos() {
        return recursos;
    }

    public ArrayList<Programa> getProgramas() {
        return programas;
    }

    public ArrayList<Proceso> getProcesos() {
        return procesos;
    }

    public ArrayList<Proceso> getProcesosListos() {
        return procesosListos;
    }

    public ArrayList<Proceso> getProcesosBloqueados() {
        return procesosBloqueados;
    }
   
    
   public void correrProcesos(int Quantum){
        
        
        while(!this.getProcesos().isEmpty()){
           int posicion = 0;
           //if(sis.getProcesos().get(posicion).getEstado() == 3){
            Proceso proceso = this.getProcesos().get(posicion);
               
            proceso.setEstado(1);              
            correrPrograma(proceso, Quantum);
            
       }
        
    }
    
    private void correrPrograma(Proceso proceso, int Quantum) {
        int tiempo = 0;
        Programa programa = proceso.getPrograma();
        int posicion = proceso.getPosicionEjecucion();
        
        List<Instruccion> instrucciones = programa.getInstrucciones();
        Iterator<Instruccion> it = instrucciones.iterator();
        
        while(it.hasNext() && tiempo >= Quantum){
            Instruccion instruccion = it.next();
            tiempo += instruccion.getTiempo();
            System.out.println(programa.getInstrucciones().get(posicion).getMensaje());
        
          //  if(instruccion.getTipo() == "pide"){
                    //Boolean disponible = sis.SolicitarRecurso(instruccion.getRecurso());
                    
                    //if(!disponible){
                        proceso.setEstado(0);
                        proceso.setPosicionEjecucion(posicion);
                        this.getProcesosBloqueados().add(proceso);
                        //return;
                        
                    //}
                    
          //  }else if(instruccion.getTipo() == "devuelve"){ //si se libera un recurso, pasa de los bloqueados a los listos
                  //  sis.liberarRecurso(instruccion.getRecurso()); 
                    //le pregunte a caffa si es solo mover el primero de la lista de bloqueados que tenga ese recurso, a la lista de listos o todos los que lo tengan 
                    
                 //   sis.moverAListosProcesosConEseRecurso(instruccion.getRecurso());
         //   }else{
                
         //   }
            
           
            proceso.setPosicionEjecucion(posicion ++);      
        }
        
        if(!it.hasNext()){
            this.getProcesos().remove(proceso); //si logro hacer todas las instrucciones, elimina el proceso de la lista de procesos
            //return true;
        }else{ //se fue por timeout
            this.getProcesosListos().add(proceso);
            //return false;
            System.out.println( "proceso"+ proceso.getNumero() +"CPU perdido por timeOut");
        }
        
        
        
    }   
    
    public Boolean SolicitarRecurso(Recurso recurso) {
        Iterator<Recurso> it = this.getRecursos().iterator();
        while(it.hasNext()){
            if(it == recurso){
                return recurso.getDisponible(); 
            }
        }
        return false;
    }
    
    /*
    
    
   private final int Quantum = 15;
   
   public void CorrerProcesos(){
       while(!procesos.isEmpty()){
           int posicion = 0;
           if(procesos.get(posicion).getEstado() == 3){
               Proceso proceso = procesos.get(posicion);
               proceso.setEstado(1);
               Boolean finalizo = CorrerPrograma(proceso);
               if(finalizo){
                   FinalizarProceso(proceso);
               } else {
                   proceso.setEstado(0);
                   
               }
           }
           if(procesos.size() == posicion+1){
               posicion = 0;
           } else {
               posicion++;
           }
           if(posicion == 0){
               ValidarEstados();
           }
       }
   }
   
   public void FinalizarProceso(Proceso proceso){
       procesosFinalizados.add(proceso);
       procesos.remove(proceso);
   }
   
   public boolean solicitudEjecutarPrograma(Usuario unUsuario, Programa unPrograma){
        //1. se chequea en matriz de Usuario-Programa (si el usuario puede usar ese programa)
        //2. se chequea en matriz Usuario-recursos (si el usuario puede usar todos los recursos que estan en ese programa)
        
        boolean puedeCrear = true;
        return puedeCrear;        
    }
   
   //A confir
   public Boolean SolicitarRecurso(Recurso recurso){
       if(recurso.getEnUso()){
           return false;
       } else {
           recurso.setEnUso(Boolean.TRUE);
       }
       return true;
   }
   public void DevolverRecurso(Recurso recurso){
       recurso.setEnUso(Boolean.FALSE);
   }


    private Boolean CorrerPrograma(Proceso proceso) {
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

    private void ValidarEstados() {
        Iterator<Proceso> it = procesos.iterator();
        while(it.hasNext()){
            Proceso proceso = it.next();
            Recurso recursoEnEspera = proceso.getRecursoEnEspera();
            if(!recursoEnEspera.getEnUso()){
                proceso.setEstado(3);
            }
        }
        
    }
    */

    
   
}
