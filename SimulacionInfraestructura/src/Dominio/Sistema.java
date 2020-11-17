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
    
   private final ArrayList<Usuario> usuarios;
   private final ArrayList<Recurso> recursos;
   private final ArrayList<Programa> programas;
   
   private final ArrayList<Proceso> procesos; //procesos (con su estado y posicionEjecucion)
   private final ArrayList<Proceso> procesosListos; //procesos listos en orden, para ejecutsr el siguiente en la lista
   private final ArrayList<Proceso> procesosBloqueados; //procesos bloqueados (sin orden)
                                                                            //esperan eventos para ser desbloqueados.
   private final int Quantum = 15;
   
   
   public Sistema(){

    this.usuarios = new ArrayList<>();
    this.recursos = new ArrayList<>();
    this.programas = new ArrayList<>();
    
    this.procesos = new ArrayList<>(); //procesos (con su estado y posicionEjecucion)
    this.procesosListos = new ArrayList<>(); //procesos listos en orden, para ejecutsr el siguiente en la lista
    this.procesosBloqueados = new ArrayList<>(); //procesos bloqueados (sin orden)
                                                                             //esperan eventos para ser desbloqueados.
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
        
        //relleno el aray de procesosListos
        for (int i=0;i<this.procesos.size();i++) {
      
            this.procesosListos.add(this.procesos.get(i));      
        }

        int posicion = 0;
        while(!this.getProcesos().isEmpty()){

            while(!this.getProcesosListos().isEmpty()){
                Proceso proceso = this.getProcesosListos().get(0);
               
                proceso.setEstado(1);              
                correrPrograma(proceso, Quantum);
                posicion++;
            }
            
       }

      
        
    }
    
    private void correrPrograma(Proceso proceso, int Quantum) {
        System.out.println( "correr programa");
        int tiempo = 0;
        Programa programa = proceso.getPrograma();
        int posicion = proceso.getPosicionEjecucion();
        
        List<Instruccion> instrucciones = programa.getInstrucciones();
        Iterator<Instruccion> it = instrucciones.iterator();
        while(it.hasNext() && tiempo <= Quantum){ 
            Instruccion instruccion = it.next();
            tiempo += instruccion.getTiempo();
           // System.out.println(programa.getInstrucciones().get(posicion).getMensaje());
        
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
            
           
            proceso.setPosicionEjecucion(posicion++);      
        }
        
        if(!it.hasNext()){ //logro hacer todas las instrucciones
            this.getProcesosListos().remove(proceso); 
            this.getProcesos().remove(proceso); 
           
        }else{ //se fue por timeout
            System.out.println( "proceso"+ proceso.getNumero() +"CPU perdido por timeOut en posicion"+ proceso.getPosicionEjecucion());
            //lo elimina de la primer posicion de los listos y lo agrega en la ultima 
            this.getProcesosListos().remove(proceso);

            eliminarInstruccionesYaEjecutadas(proceso.getPrograma().getInstrucciones(), proceso.getPosicionEjecucion());
            proceso.setPosicionEjecucion(0);
            int ultimoIndex = getUltimoIndex(this.getProcesosListos());
            this.getProcesosListos().add(ultimoIndex, proceso); //lo agrega ultimo, pero con menos instrucciones.
            
            
        }
        
        
        
    }   


    private static void eliminarInstruccionesYaEjecutadas(List<Instruccion> instrucciones, int ultimaPosicion){

        for (int i=0; i<ultimaPosicion; i++) {
            instrucciones.remove(i);
            
        }
    }

    private static int getUltimoIndex(List<Proceso> list){ 
        if(list != null) {
            return list.size();
        }
        return -1;
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
    
   
}
