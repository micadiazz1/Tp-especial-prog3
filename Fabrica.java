import org.w3c.dom.ls.LSOutput;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Fabrica {
    private List<Maquina> maquinas;
    private List<Maquina> solucion;
    private int piezasTotales;

    private int estadosExplorados;
    private int cantEstadoGeneradoGreedy;
    private List<Maquina> solucionGreedy;

    public Fabrica() {
        maquinas = new ArrayList<>();
        solucion = new ArrayList<>();
        estadosExplorados= 0;
        cantEstadoGeneradoGreedy = 0;
        solucionGreedy= new ArrayList<>();
    }

    public void cargarDesdeArchivo(String archivo) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(archivo));
        String linea = br.readLine();

        // leer cantidad total de piezas
        piezasTotales = Integer.parseInt(linea.trim());

        // leer maquinas
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
        return estadosExplorados;
    }

    public int getCantEstadoGeneradoGreedy(){
        return cantEstadoGeneradoGreedy;
    }

    public int getCantSolucion() {
        int cont=0;
        for(Maquina m : solucion){
            cont++;
        }
        return cont;
    }
    public int getCantSolucionGreedy() {
        int cont=0;
        for(Maquina m : solucionGreedy){
            cont++;
        }
        return cont;
    }


    /*
     * <<Breve explicación de la estrategia de resolución:
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
     *     Si el total de piezas acumuladas supera el objetivo o si el camino actual es menos eficiente que la mejor solucion actual.
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
        estadosExplorados++;
        //si la cantidad de piezas acumuladas es igual al objetivo (condicion de corte)
        if (sumaAcumuladaPiezas == piezasTotales){
            //si la cant de maquinas actuales es menor a la maquinas solucion
            if (solucion.isEmpty() || caminoActual.size() < solucion.size()){
                //se limpia solucion, y se guarda la mejor
                solucion.clear();
                solucion.addAll(caminoActual);

            }

        }else {
            if (sumaAcumuladaPiezas < piezasTotales && (solucion.isEmpty() || caminoActual.size() < solucion.size())) {
                for (Maquina maquina : maquinas) {
                    if(sumaAcumuladaPiezas + maquina.getPiezas() <= piezasTotales){
                        caminoActual.add(maquina);
                        backtracking(caminoActual, sumaAcumuladaPiezas + maquina.getPiezas());
                        caminoActual.remove(caminoActual.size() - 1);
                    }
                }
            }
        }
    }


    /*
     * <<Breve explicación de la estrategia de resolución:
     *  ¿Cuáles son los candidatos?
     *      Los candidatos son las maquinas encargadas en generar piezas.
     *
     * - Estrategia de selección de candidatos.
     *      nuestra estrategia consta de ordenar los candidatos de mayor a menor para asi poder comenzar de la maquina que contenga
     *      mayor cantidad de piezas entonces, brindando la posibilidad de que no tengamos que recurrir a
     *      otra maquina o a varias maquinas, optimizando la busqueda.
     *
     * - Consideraciones respecto a encontrar o no solución.
     *     Esta estrategia no siempre garantiza que se llegue a una solución. Si con las máquinas que elegimos no se puede alcanzar
     *     la cantidad de piezas que necesitamos, entonces no se cumple el objetivo.
     *     Como se trata de una estrategia greedy, en cada paso elegimos la mejor opción en ese momento (la máquina que más piezas hace),
     *     sin pensar si más adelante esa elección nos va a complicar. Por eso, puede pasar que no encontremos una solución,
     *     aunque sí exista otra combinación de máquinas que sí lo logre.
     *
     *
     */



    public List<Maquina> greedy(){
        List<Maquina> candidatos = this.maquinas;
        int cantPiezasTotales = 0;
        candidatos.sort((m1, m2) -> Integer.compare(m2.getPiezas(), m1.getPiezas()));
        while (!candidatos.isEmpty() && !solucion(cantPiezasTotales)){
            cantEstadoGeneradoGreedy++;

            Maquina x = candidatos.get(0);

            if(factible(x,cantPiezasTotales)){
                solucionGreedy.add(x);
                cantPiezasTotales+=x.getPiezas();

            }else{
                candidatos.remove(x);
            }

        }

        if (solucion(cantPiezasTotales)){
            return solucionGreedy;
        }

        System.out.println("No se ha encontrado solucion");
        return null;

    }

    public boolean factible(Maquina x, int cantPiezasTotales){
        return cantPiezasTotales+x.getPiezas()<=piezasTotales;
    }

    public boolean solucion(int cantPiezaTotales){
        return cantPiezaTotales==piezasTotales;
    }

}
