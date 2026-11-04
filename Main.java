import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        List<Actividad> actividades = new ArrayList<>();

        System.out.println("================================");
        System.out.println("       CALCULO DEL METODO CPM");
        System.out.println("================================");

        System.out.print("Ingrese la cantidad de actividades: ");
        int cantidad = Integer.parseInt(teclado.nextLine());

        for (int i = 0; i < cantidad; i++) {

            System.out.println("\nActividad " + (i + 1));

            System.out.print("Ingrese el ID: ");
            String id = teclado.nextLine().trim();

            System.out.print("Ingrese la duracion: ");
            int duracion = Integer.parseInt(teclado.nextLine());

            System.out.print(
                "Ingrese las predecesoras (separadas por coma o - si no tiene): "
            );

            String entrada = teclado.nextLine().trim();

            List<String> predecesoras = new ArrayList<>();

            if (!entrada.equals("-") && !entrada.isEmpty()) {

                String[] datos = entrada.split(",");

                for (String dato : datos) {
                    predecesoras.add(dato.trim());
                }
            }

            Actividad actividad = new Actividad(
                id, duracion, predecesoras
            );

            actividades.add(actividad);
        }

        try {

            CalculoCPM calculo = new CalculoCPM(actividades);

            calculo.calcularCPM();

            System.out.println("\n================================");
            System.out.println("       RESULTADOS DEL CPM");
            System.out.println("================================");

            System.out.printf(
                "%-10s %-10s %-10s %-10s %-10s %-10s %-10s%n",
                "Actividad", "Duracion", "IT", "FT", "ITa", "FTa", "Holgura"
            );

            for (Actividad a : actividades) {

                System.out.printf(
                    "%-10s %-10d %-10d %-10d %-10d %-10d %-10d%n",
                    a.getId(),
                    a.getDuracion(),
                    a.getInicioTemprano(),
                    a.getFinTemprano(),
                    a.getInicioTardio(),
                    a.getFinTardio(),
                    a.getHolgura()
                );
            }

            System.out.println("\nDuracion total del proyecto: "
                + calculo.getDuracionTotal());

            System.out.print("Actividades de la ruta critica: ");

            for (Actividad a : calculo.getRutaCritica()) {
                System.out.print(a.getId() + " ");
            }

            System.out.println();

        } catch (IllegalArgumentException e) {

            System.out.println("\nError: " + e.getMessage());
        }

        teclado.close();
    }
}