
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
    
        // agregamos las actividades que no dependen de otras
        for (Actividad a : actividades) {
            if (entradas.get(a.getId()) == 0) {
                cola.add(a);
            }
        }

          // Ordenamos las actividades segun sus dependencias
        orden.clear();

        while (!cola.isEmpty()) {
            Actividad a = cola.poll();
            orden.add(a);

            for (Actividad siguiente : sucesoras.get(a.getId())) {

                int n = entradas.get(siguiente.getId()) - 1;
                entradas.put(siguiente.getId(), n);

                if (n == 0) {
                    cola.add(siguiente);
                }
            }
        }

        // Si no se pudieron ordenar todas, hay dependencias circulares
        if (orden.size() != actividades.size()) {
            throw new IllegalArgumentException(
                "Existen ciclos en las dependencias.");
        }

        // Calculamos los tiempos de inicio y final mas tempranos
        duracionTotal = 0;

        for (Actividad a : orden) {
            int inicio = 0;

            // Buscamos el final mas tardio de sus predecesoras
            for (String id : a.getPredecesoras()) {
                inicio = Math.max(inicio,
                    mapa.get(id).getFinTemprano());
            }

            a.setInicioTemprano(inicio);
            a.setFinTemprano(inicio + a.getDuracion());

            // Guardamos la duracion mas grande del proyecto
            duracionTotal = Math.max(
                duracionTotal, a.getFinTemprano());
        }

        // Asignamos la duracion total a todas las actividades
        for (Actividad a : actividades) {
            a.setDuracionTotal(duracionTotal);
        }

        // Recorremos las actividades al reves para calcular tiempos tardios
        for (int i = orden.size() - 1; i >= 0; i--) {

            Actividad a = orden.get(i);
            int fin = duracionTotal;

            // Si tiene actividades siguientes, buscamos el menor inicio tardio
            if (!sucesoras.get(a.getId()).isEmpty()) {
                fin = Integer.MAX_VALUE;

                for (Actividad s : sucesoras.get(a.getId())) {
                    fin = Math.min(fin, s.getInicioTardio());
                }
            }

            a.setFinTardio(fin);
            a.setInicioTardio(fin - a.getDuracion());

            // La diferencia entre tiempos nos da la holgura
            a.setHolgura(
                a.getInicioTardio() - a.getInicioTemprano());
        }

        // Las actividades con holgura cero forman parte de la ruta critica
        rutaCritica.clear();

        for (Actividad a : orden) {
            if (a.esCritica()) {
                rutaCritica.add(a);
            }
        }
    }

    // Regresamos la lista de actividades criticas
    public List<Actividad> getRutaCritica() {
        return rutaCritica;
    }

    // Regresamos el tiempo que tarda todo el proyecto
    public int getDuracionTotal() {
        return duracionTotal;
    }
 }