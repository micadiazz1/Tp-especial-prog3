public class Main {

    public static void main(String[] args) {
        String archivo = "config.txt";

        try {
            Fabrica fabrica = new Fabrica();
            fabrica.cargarDesdeArchivo(archivo);
            System.out.println("Backtracking");
            System.out.println("Solucion Obtenida: " + fabrica.backtracking());
            System.out.println("Cantidad de piezas producidas: " + fabrica.getPiezasTotales() + " y" + " cantidad de maquinas en funcionamiento: " + fabrica.getCantSolucion());
            System.out.println("Cantidad de estados generados : " + fabrica.getCantEstadoGenerado() );


            System.out.println("--------------------------------------------");

            System.out.println("Greedy");
            System.out.println("Solucion Obtenida: " + fabrica.greedy());
            System.out.println("Solucion Obtenida: " + " cantidad de piezas producidas: " + fabrica.getPiezasTotales() + " y" + " cantidad de maquinas funcionamiento " + fabrica.getCantSolucionGreedy());
            System.out.println("Cantidad de estados generados : " + fabrica.getCantEstadoGeneradoGreedy());



        } catch (Exception e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
