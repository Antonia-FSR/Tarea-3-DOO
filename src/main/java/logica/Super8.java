package logica;
/**
 * Clase que representa un dulce Super8.
 * Extiende de {@link Dulce}, por lo que hereda el metodo serie.
 *
 * @author Antonia-FSR
 */
public class Super8 extends Dulce {
    public Super8(int serie){
        super(serie);
    }
    public String Consumir(){
        return"productos.Super8";
    }
}
