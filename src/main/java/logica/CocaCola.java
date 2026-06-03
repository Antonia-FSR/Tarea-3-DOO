package logica;

/**
 * Clase que representa una bebida Coca-Cola.
 * Extiende de {@link Bebida}, por lo que hereda el metodo serie.
 *
 * @author Antonia-FSR
 */
public class CocaCola extends Bebida {

    public CocaCola(int serie){
        super(serie);
    }
    public String Consumir(){
        return "productos.CocaCola";
    }
}
