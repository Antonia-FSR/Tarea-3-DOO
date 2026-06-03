package logica;
/**
 * Clase abstracta que representa un producto del tipo "Bebida".
 * Extiende a {@link Producto} para categorizar productos que no son {@link Dulce}.
 *
 * @author Antonia-FSR
 */
public abstract class Bebida extends Producto {
    public Bebida(int serie){
        super(serie);
    }
}
