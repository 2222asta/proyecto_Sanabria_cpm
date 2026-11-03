
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
       