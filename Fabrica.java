import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Fabrica {
    private List<Maquina> maquinas;
    private int piezasTotales;
    private List<Maquina> solucion;
    private int cantEstadoGenerado;

    public Fabrica() {
        maquinas = new ArrayList<>();
        solucion = new ArrayList<>();
        cantEstadoGenerado = 0;
    }

    public void cargarDesdeArchivo(String archivo) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(archivo));
        String linea = br.readLine();

        // Leer cantidad total de piezas
        piezasTotales = Integer.parseInt(linea.trim());

        // Leer máquinas
        while ((linea = br.readLine()) != null) {
            String[] partes = linea.split(",");
            String nombre = partes[0].trim();
            int piezas = Integer.parseInt(partes[1].trim());
            maquinas.add(new Maquina(piezas, nombre));
        }

        br.close();
    }

    public List<Maquina> getMaquinas() {
        return maquinas;
    }

    public int getPiezasTotales() {
        return piezasTotales;
    }
    public int getCantEstadoGenerado(){
        return cantEstadoGenerado;
    }

    public int getCantSolucion() {
        int cont=0;
        for(Maquina m : solucion){
            cont++;
        }
        return cont;
    }

    /*
     * <<Breve explicación de la estrategia de resolución. Por ejemplo:
     *
     * - Cómo se genera el árbol de exploración.
     *    mediante la cantidad de piezas producidas en el momento y la secuencia de maquinas utilizadas.
     *
     * - Cuáles son los estados finales y estados solución.
     *      Un estado se considera estado final cuando la cantidad total acumulada de piezas
     *      es igual al valor objetivo.
     *
     *      Si la cantidad acumulada supera el objetivo, el estado no es valido y se descarta (poda),
     *      ya que no nos conduce a una solución correcta.
     *
     *      Un estado solución es un estado final que se guarda si cumple con el objetivo.
     *      La busqueda continua para encontrar posibles soluciones con menor cantidad de maquinas (menor costo).
     *
     *
     * - Posibles podas.
     * -    Si el total de piezas acumuladas supera el objetivo.
     *
     *
     * >>
     */
    public List<Maquina> backtracking() {
        solucion.clear();
        int sumaAcumuladaPiezas = 0;

        ArrayList<Maquina>caminoActual=new ArrayList<>();
        backtracking(caminoActual, sumaAcumuladaPiezas);

        return solucion;
    }
    private void backtracking(ArrayList<Maquina>caminoActual, int sumaAcumuladaPiezas){
        cantEstadoGenerado++;
        //si la cantidad de piezas acumuladas es igual al objetivo (condicion de corte)
        if (sumaAcumuladaPiezas == piezasTotales){
            //si la cant de maquinas actuales es menor a la maquinas solucion
            if (solucion.isEmpty() || caminoActual.size() < solucion.size()){
                //se limpia solucion, y se guarda la mejor
                solucion.clear();
                solucion.addAll(caminoActual);
            }
        }
        //poda (cuando superamos el valor objetivo)
        if (sumaAcumuladaPiezas > piezasTotales){
            return;
        }
        //recorro las maquinas //arreglo maquinas
        for(Maquina maquina: maquinas){
            //agrego la maquina a camino actual
            caminoActual.add(maquina);
            //back

            backtracking(caminoActual,sumaAcumuladaPiezas + maquina.getPiezas());
            //saco de caminoActual la maquina
            caminoActual.remove(caminoActual.size()-1);

        }

    }


    /*
     * <<Breve explicación de la estrategia de resolución. Por ejemplo:
     * - Cómo se genera el árbol de exploración.
     * - Cuáles son los estados finales y estados solución.
     * - Posibles podas.
     * - etc.>>
     *
     *   public Solucion greedy() {

    }

     */





}
