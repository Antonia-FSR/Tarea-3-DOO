package logica;

/**
 * Clase que representa una bebida Fanta.
 * Extiende de {@link Bebida}, por lo que hereda el metodo serie.
 *
 * @author Antonia-FSR
 */
public class Fanta extends Bebida {
    public Fanta(int serie){
        super(serie);
    }
    public String Consumir(){
        return "productos.Fanta";
    }
}
