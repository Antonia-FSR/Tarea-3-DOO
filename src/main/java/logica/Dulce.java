package logica;
/**
 * Clase abstracta que representa un producto del tipo "dulce".
 * Extiende a {@link Producto} para categorizar productos que no son {@link Bebida}.
 *
 * @author Antonia-FSR
 */
public abstract class Dulce extends Producto {
    public Dulce(int serie){
        super(serie);
    }
}
