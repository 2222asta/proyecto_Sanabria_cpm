
import java.util.*;

public class CalculoCPM {
    private List<Actividad> actividades;

    public CalculoCPM(List<Actividad> actividades) {
        this.actividades = actividades;
    }

    public void resolver() {
        Map<String, Actividad> mapa = new HashMap<>();

        for (Actividad a : actividades) {
            if (a.duracion < 0 || mapa.put(a.id, a) != null) {
                throw new IllegalArgumentException(
                    "Duración inválida o ID repetido: " + a.id
                );
            }
        }

        // Validar las predecesoras
        for (Actividad a : actividades) {
            for (String p : a.predecesoras) {
                if (!mapa.containsKey(p)) {
                    throw new IllegalArgumentException(
                        "No existe la actividad predecesora: " + p
                    );
                }
            }
        }

        // Ordenar actividades según sus dependencias
        List<Actividad> orden = new ArrayList<>();
        Set<String> procesadas = new HashSet<>();

        while (orden.size() < actividades.size()) {
            boolean progreso = false;

            for (Actividad a : actividades) {
                if (procesadas.contains(a.id)) {
                    continue;
                }

                if (procesadas.containsAll(a.predecesoras)) {
                    orden.add(a);
                    procesadas.add(a.id);
                    progreso = true;
                }
            }
}   }   }
