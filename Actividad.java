import java.util.ArrayList;
import java.util.List;

public class Actividad {

 private String id; // Agregar identificador de actividad.
    private int duracion; // Agregar duración de actividad.
    private List<String> predecesoras; // Agregar lista de predecesoras.

     private int inicioTemprano; // Agregar inicio temprano.
    private int finTemprano; // Agregar fin temprano.
    private int inicioTardio; // Agregar inicio tardío.
    private int finTardio; // Agregar fin tardío.
    private int holgura; // Agregar holgura de actividad.

     public Actividad(String id, int duracion, List<String> predecesoras) {
        this.id = id;
        this.duracion = duracion;
        this.predecesoras = new ArrayList<>(predecesoras);

        this.inicioTemprano = 0;
        this.finTemprano = 0;
        this.inicioTardio = 0;
        this.finTardio = 0;
        this.holgura = 0;

       
}

 public String getId() { //getter id 
            return id;
     }
      
public int getDuracion() { //getter Duracion 
   return duracion;
      }

public List<String> getPredecesoras() { //getter Predecesoras 
        return predecesoras;   
}



public int getInicioTemprano() { //getter Inicio Temprano 
        return inicioTemprano;
    }

public void setInicioTemprano(int inicioTemprano) { //setter Inicio Temprano 
        this.inicioTemprano = inicioTemprano;
    }
    
public int getFinTemprano() { //getter Fin Temprano 
        return finTemprano;
    }
    
public void setFinTemprano(int finTemprano) { //setter Fin Temprano 
        this.finTemprano = finTemprano;
    }    

    
}