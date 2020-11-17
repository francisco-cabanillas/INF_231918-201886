package Dominio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Sistema {
   private final ArrayList<Usuario> usuarios;
   private final ArrayList<Recurso> recursos;
   private final ArrayList<Programa> programas;
   
   private final ArrayList<Proceso> procesosFinalizados; //procesos finalizados (con su estado y posicionEjecucion)
   private final ArrayList<Proceso> procesosListos; //procesos listos en orden, para ejecutsr el siguiente en la lista
   private final ArrayList<Proceso> procesosBloqueados; //procesos bloqueados (sin orden)
                                                                            //esperan eventos para ser desbloqueados.
  
   
   
   public Sistema(){

    this.usuarios = new ArrayList<>();
    this.recursos = new ArrayList<>();
    this.programas = new ArrayList<>();
    
    this.procesosFinalizados = new ArrayList<>(); //procesos (con su estado y posicionEjecucion)
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

    public ArrayList<Proceso> getProcesosFinalizados() {
        return procesosFinalizados;
    }

    public ArrayList<Proceso> getProcesosListos() {
        return procesosListos;
    }

    public ArrayList<Proceso> getProcesosBloqueados() {
        return procesosBloqueados;
    }
   
    
   public void correrProcesos(int Quantum){
        
        while(!this.getProcesosListos().isEmpty()){
            Proceso proceso = this.getProcesosListos().get(0);
            
            proceso.setEstado(1);              
            correrPrograma(proceso, Quantum);
                
        }

        System.out.println("El array procesos listos tiene " + getProcesosListos().size() + " procesos.");
        System.out.println("El array procesos finalizados tiene " + getProcesosFinalizados().size() + " procesos.");
        System.out.println("El array procesos bloqueados quedo con " + getProcesosBloqueados().size() + " procesos.\n");
           
    }
    
    private void correrPrograma(Proceso proceso, int Quantum) {
        System.out.println("\nCorrer programa de proceso " + proceso + "\n");
        int tiempo = 0;
        Programa programa = proceso.getPrograma();
        int posicion = proceso.getPosicionEjecucion();
        
        List<Instruccion> instrucciones = programa.getInstrucciones();
        Iterator<Instruccion> it = instrucciones.iterator();
        while(tiempo <= Quantum && it.hasNext()){
            
            Instruccion instruccion = it.next();
            tiempo += instruccion.getTiempo();
            
        
            if(instruccion.getTipo() == "Pedir"){
                Boolean disponible = solicitarRecurso(instruccion.getRecurso(), proceso);
                
                if(!disponible){
                    System.out.println("El proceso " + proceso + " no pudo completar la tarea " + 
                        instruccion + " porque el recurso se encuentra en uso.");
                    proceso.setEstado(0);
                    proceso.setPosicionEjecucion(posicion);
                    this.getProcesosBloqueados().add(proceso);
                    this.getProcesosListos().remove(proceso);

                    return;
                } else {
                    System.out.println(instruccion.getMensaje());
                }
                    
            }else if(proceso.getRecursoEnUso() != null && proceso.getRecursoEnUso().equals(instruccion.getRecurso())){
                 if(instruccion.getTipo() == "Devolver"){ //si se libera un recurso, pasa de los bloqueados a los listos
                    devolverRecurso(proceso);
                    System.out.println(instruccion); 
                    //le pregunte a caffa si es solo mover el primero de la lista de bloqueados que tenga ese recurso, a la lista de listos o todos los que lo tengan 
                    
                    validarEstados();
                }else{
                    System.out.println(instruccion);
                }
            } else {
                System.out.println("El proceso " + proceso + " intento utilizar el recurso (" + instruccion.getRecurso() + ") sin solicitarlo previamente.");

            }
            
        
            proceso.setPosicionEjecucion(posicion++);
        }
        
        if(tiempo > Quantum){ //se fue por timeout
            System.out.println("\nProceso "+ proceso +" perdio CPU por timeOut en posicion "+ proceso.getPosicionEjecucion() + "\n");
            //lo elimina de la primer posicion de los listos y lo agrega en la ultima 
            this.getProcesosListos().remove(0); //que solo elimine uno

            eliminarInstruccionesYaEjecutadas(proceso.getPrograma().getInstrucciones(), proceso.getPosicionEjecucion());
            proceso.setPosicionEjecucion(0);
            int ultimoIndex = getUltimoIndex(this.getProcesosListos());
            this.getProcesosListos().add(ultimoIndex, proceso); //lo agrega ultimo, pero con menos instrucciones.
            
        }else{  //logro hacer todas las instrucciones
            System.out.println( "\n Proceso "+ proceso +" se ejecuto completamente. \n");
            finalizarProceso(proceso);
        }
    }   

    public void finalizarProceso(Proceso proceso){
        this.getProcesosFinalizados().add(proceso);
        this.getProcesosListos().remove(proceso);
    }


    private static void eliminarInstruccionesYaEjecutadas(List<Instruccion> instrucciones, int ultimaPosicion){
        for (int i=0; i <= ultimaPosicion; i++) {
            instrucciones.remove(0);
            
        }
    }

    private static int getUltimoIndex(List<Proceso> list){ 
        if(list != null) {
            return list.size();
        }
        return -1;
    }

    
    public Boolean solicitarRecurso(Recurso recurso, Proceso proceso){
        if(!recurso.getDisponible()){
            proceso.setRecursoEnEspera(recurso);
            proceso.setEstado(0);
            return false;
        } else {
            recurso.setDisponible(Boolean.FALSE);
            proceso.setRecursoEnUso(recurso);
        }
        return true;
    }
    public void devolverRecurso(Proceso proceso){
        proceso.getRecursoEnUso().setDisponible(Boolean.TRUE);
        proceso.setRecursoEnUso(null);
    }

    public void validarEstados() {
        Iterator<Proceso> it = procesosBloqueados.iterator();
        while(it.hasNext()){
            Proceso proceso = it.next();
            Recurso recursoEnEspera = proceso.getRecursoEnEspera();
            if(recursoEnEspera.getDisponible()){
                proceso.setEstado(3);
                getProcesosListos().add(proceso);
                
                proceso.setRecursoEnEspera(null);
            }
        }
        it = procesosListos.iterator();
        while(it.hasNext()){
            Proceso proceso = it.next();
            if(getProcesosBloqueados().contains(proceso)){
                getProcesosBloqueados().remove(proceso);
            }
        }
        
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
   
   public boolean solicitudEjecutarPrograma(Usuario unUsuario, Programa unPrograma){
        //1. se chequea en matriz de Usuario-Programa (si el usuario puede usar ese programa)
        //2. se chequea en matriz Usuario-recursos (si el usuario puede usar todos los recursos que estan en ese programa)
        
        boolean puedeCrear = true;
        return puedeCrear;        
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

    
    */

    
   
}
