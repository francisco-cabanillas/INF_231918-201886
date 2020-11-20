package Logica;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Dominio.Instruccion;
import Dominio.Proceso;
import Dominio.Programa;
import Dominio.Recurso;
import Dominio.Usuario;
import simulacionInfraestructura.Consola;

public class Sistema {
   private final ArrayList<Usuario> usuarios;
   private final ArrayList<Recurso> recursos;
   private final ArrayList<Programa> programas;
   
   private final ArrayList<Proceso> procesosFinalizados; //procesos finalizados (con su estado y posicionEjecucion)
   private final ArrayList<Proceso> procesosListos; //procesos listos en orden, para ejecutsr el siguiente en la lista
   private final ArrayList<Proceso> procesosBloqueados; //procesos bloqueados (sin orden)
                                                                            //esperan eventos para ser desbloqueados.

   private int permisosRecursos[][];
   private int permisosProgramas[][];

   Consola consola;
  
   
   
   public Sistema(){
        consola = new Consola();
        
        this.usuarios = new ArrayList<>();
        this.recursos = new ArrayList<>();
        this.programas = new ArrayList<>();
        
        this.procesosFinalizados = new ArrayList<>(); //procesos (con su estado y posicionEjecucion)
        this.procesosListos = new ArrayList<>(); //procesos listos en orden, para ejecutsr el siguiente en la lista
        this.procesosBloqueados = new ArrayList<>(); //procesos bloqueados (sin orden)
                                                                                //esperan eventos para ser desbloqueados.
   }
   
    public int[][] getPermisosRecursos(){
        return this.permisosRecursos;
    }

    public void setPermisosRecursos(int[][] unaMatrizPermisosRecursos){
        this.permisosRecursos = unaMatrizPermisosRecursos;
    }
    public int[][] getPermisosProgramas(){
        return this.permisosProgramas;
    }

    public void setPermisosProgramas(int[][] unaMatrizPermisosProgramas){
        this.permisosProgramas = unaMatrizPermisosProgramas;
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
    public boolean solicitudPermisosRecursos(Usuario unUsuario, Programa unPrograma){
        //1. se chequea en matriz Usuario-recursos (si el usuario puede usar todos los recursos que estan en ese programa)

        boolean puedeCrear = true;
        for (int i=0; i < this.getPermisosRecursos().length; i++) {
            if(unUsuario.getId() == i){ //si es ese usuario (esa fila) confirmar que ninguno de los recursos de su programa está en 0.
                for (int j=0; j < this.getPermisosRecursos()[i].length; j++) {
                    //recurso de la matriz: j
                    int idRecurso = j;
                    if(this.getPermisosRecursos()[i][j]==0 && unPrograma.utilizaRecurso(idRecurso)){//si está denegado, y ese programa necesita ese recurso, solicitud denegada.
                        //System.out.println("Se denegó la ejecucion del programa " + unPrograma.getId() + " por parte del usuario "+ unUsuario.getNombre() + ". No tiene permisos para usar sus recursos.");
                        puedeCrear = false;
                    }
                  }
            }
            
        }
        return puedeCrear;        
    }

    public boolean solicitudEjecutarPrograma(Usuario unUsuario, Programa unPrograma){
        //2. se chequea en matriz Usuario-Programa (si el usuario puede ejecutar ese programa)

        boolean puedeCrear = true;
        for (int i=0; i < this.getPermisosProgramas().length; i++) {
            if(unUsuario.getId() == i){ //si es ese usuario (esa fila) recorrer programas
                for (int j=0; j < this.getPermisosProgramas()[i].length; j++) {
                    
                    int idPrograma = j;
                    if(this.getPermisosProgramas()[i][j]==0 && unPrograma.getId() == idPrograma){//si está denegado, y ese programa necesita ese recurso, solicitud denegada.
                        consola.PrintDenegacionEjecucion(unPrograma.getId(), unUsuario.getNombre());
                        puedeCrear = false;
                    }
                  }
            }           
        }
        return puedeCrear;        
    }

    
   public void correrProcesos(int Quantum){
        
        while(!this.getProcesosListos().isEmpty()){
            Proceso proceso = this.getProcesosListos().get(0);
            
            proceso.setEstado(1);              
            correrPrograma(proceso, Quantum);
                
        }

        consola.PrintSizeProcesos(getProcesosListos().size(), getProcesosFinalizados().size(), getProcesosBloqueados().size());
           
    }
    
    private void correrPrograma(Proceso proceso, int Quantum) {
        consola.PrintCorrerPrograma(proceso);

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
                if(!solicitudPermisosRecursos(proceso.getPropiedad(), proceso.getPrograma())){//si no tiene permisos, 
                    consola.PrintDenegacionRecurso(proceso, proceso.getPropiedad().getNombre(), instruccion.getRecurso());
                    
                    this.getProcesosFinalizados().add(proceso);
                    this.getProcesosListos().remove(proceso);
                    return;
                }
                if(!disponible){
                    consola.PrintRecursoEnUso(proceso, instruccion);
                    
                    proceso.setEstado(0);
                    proceso.setPosicionEjecucion(posicion);
                    this.getProcesosBloqueados().add(proceso);
                    this.getProcesosListos().remove(proceso);

                    return;
                } else {
                    consola.PrintMensajeInstruccion(instruccion.getMensaje());
                }
                    
            }else if(proceso.getRecursoEnUso() != null && proceso.getRecursoEnUso().equals(instruccion.getRecurso())){
                 if(instruccion.getTipo() == "Devolver"){ //si se libera un recurso
                    devolverRecurso(proceso);
                    consola.PrintMensajeInstruccion(instruccion.getMensaje());  
                    
                    desbloquearProcesos();
                }else{
                    consola.PrintMensajeInstruccion(instruccion.getMensaje());
                }
            } else {
                consola.PrintFaltaSolicitudRecurso(proceso, instruccion.getRecurso());
            }
            
        
            proceso.setPosicionEjecucion(posicion++);
        }
        
        if(tiempo > Quantum){ //se fue por timeout
            consola.PrintFaltaCpu(proceso);

            //lo elimina de la primer posicion de los listos y lo agrega en la ultima 
            this.getProcesosListos().remove(0); //que solo elimine uno

            eliminarInstruccionesYaEjecutadas(proceso.getPrograma().getInstrucciones(), proceso.getPosicionEjecucion());
            proceso.setPosicionEjecucion(0);
            int ultimoIndex = getUltimoIndex(this.getProcesosListos());
            this.getProcesosListos().add(ultimoIndex, proceso); //lo agrega ultimo, pero con menos instrucciones.
            
        }else{  //logro hacer todas las instruccionesc
            consola.PrintEjecucionExitosa(proceso);
            
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

    public void desbloquearProcesos() {
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
}
