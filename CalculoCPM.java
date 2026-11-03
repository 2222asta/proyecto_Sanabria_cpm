
import java.util.*;

public class CalculoCPM {

    private final List<Actividad> actividades;
    private final Map<String, Actividad> mapa = new HashMap<>();
    private final Map<String, List<Actividad>> sucesoras = new HashMap<>();

    private List<Actividad> orden;
    private int duracionTotal;

    public CalculoCPM(List<Actividad> actividades) {
        this.actividades = actividades;
    }

    // Organiza las actividades y valida sus dependencias
    private void ordenarActividades() {

        mapa.clear();
        sucesoras.clear();

        Map<String, Integer> entradas = new HashMap<>();
        Queue<Actividad> cola = new LinkedList<>();
        orden = new ArrayList<>();

        // Registrar actividades
        for (Actividad a : actividades) {

            if (a.duracion < 0) {
                throw new IllegalArgumentException(
                    "Duración negativa en: " + a.id
                );
            }

            if (mapa.put(a.id, a) != null) {
                throw new IllegalArgumentException(
                    "ID duplicado: " + a.id
                );
            }

            sucesoras.put(a.id, new ArrayList<>());
            entradas.put(a.id, 0);

        // Construir relaciones entre actividades
        for (Actividad a : actividades) {

            Set<String> dependencias = new HashSet<>(a.predecesoras);

            if (dependencias.size() != a.predecesoras.size()) {
                throw new IllegalArgumentException(
                    "Predecesora repetida en: " + a.id
                );
            }

              // Agregar a la cola las actividades sin predecesoras
        for (Actividad a : actividades) {
            if (entradas.get(a.id) == 0) {
                cola.add(a);
            }
        }

         // Ordenamiento
        while (!cola.isEmpty()) {

            Actividad actual = cola.poll();
            orden.add(actual);

            for (Actividad siguiente : sucesoras.get(actual.id)) {

                int pendientes = entradas.get(siguiente.id) - 1;
                entradas.put(siguiente.id, pendientes);

                if (pendientes == 0) {
                    cola.add(siguiente);
                }
            }
        }

        // Detectar ciclos
        if (orden.size() != actividades.size()) {
            throw new IllegalArgumentException(
                "Error: existen ciclos en las dependencias."
            );
        }
    }

    
}   }   } 

  

