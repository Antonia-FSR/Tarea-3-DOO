package logica;

/**
 * Clase abstracta que define las características de los productos dispensados por el expendedor.
 *
 * @author Antonia-FSR
 */
public abstract class Producto {
    private int serie;
    public Producto(int serie){
        this.serie=serie;
    }

    public int getSerie() {
        return serie;
    }
    public abstract String Consumir();
}
