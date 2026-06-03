package logica;

/**
 * Clase que representa una bebida Sprite.
 * Extiende de {@link Bebida}, por lo que hereda el metodo serie.
 *
 * @author Antonia-FSR
 */
public class Sprite extends Bebida {
    public Sprite(int serie){
        super(serie);

    }
    public String Consumir(){
        return "productos.Sprite";
    }
}
