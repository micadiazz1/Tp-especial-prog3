public class Maquina {
    private int piezas;
    private String nombre;


    public Maquina(int piezas, String nombre) {
        this.piezas = piezas;
        this.nombre = nombre;
    }

    public int getPiezas() {
        return piezas;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre + " (" + piezas + ")";
    }
}
