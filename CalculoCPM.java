
import java.util.*;

public class CalculoCPM {

    private List<Actividad> actividades;
    private List<Actividad> orden;
    private List<Actividad> rutaCritica;
    private int duracionTotal;

    // Constructor donde recibimos las actividades
    public CalculoCPM(List<Actividad> actividades) {
        this.actividades = actividades;
        this.orden = new ArrayList<>();
        this.rutaCritica = new ArrayList<>();
    }
    
 }