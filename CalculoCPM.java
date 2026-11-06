
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

    
    // Aqui realizamos todos los calculos del CPM
    public void calcularCPM() {

        // Guardamos las actividades 
        Map<String, Actividad> mapa = new HashMap<>();

        // guardamos que actividades dependenden de otras
        Map<String, List<Actividad>> sucesoras = new HashMap<>();

        // Contamos cuantas actividades anteriores tiene cada una
        Map<String, Integer> entradas = new HashMap<>();

        Queue<Actividad> cola = new LinkedList<>();

        // Registramos las actividades y revisamos los datos
        for (Actividad a : actividades) {
            if (a.getDuracion() < 0 ||
                mapa.put(a.getId(), a) != null) {

                throw new IllegalArgumentException(
                    "Duracion invalida o ID duplicado: " + a.getId());
            }

            sucesoras.put(a.getId(), new ArrayList<>());
            entradas.put(a.getId(), 0);
        }
   
        // Revisamos las dependencias
        for (Actividad a : actividades) {
            for (String id : a.getPredecesoras()) {

                Actividad anterior = mapa.get(id);

                // Comprobamos que la actividad anterior exista
                if (anterior == null) {
                    throw new IllegalArgumentException(
                        "Predecesora inexistente: " + id);
                }

                sucesoras.get(id).add(a);

                entradas.put(a.getId(),
                    entradas.get(a.getId()) + 1);
            }
        }
    }
     
}

    
 