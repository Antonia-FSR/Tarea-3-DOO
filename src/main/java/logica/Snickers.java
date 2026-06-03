package logica;

/**
 * Clase que representa un dulce Snickers.
 * Extiende de {@link Dulce}, por lo que hereda el metodo serie.
 *
 * @author Antonia-FSR
 */
public class Snickers extends Dulce {
    public Snickers(int serie){
        super(serie);
    }
    public String Consumir(){
        return"productos.Snickers";
    }
}
