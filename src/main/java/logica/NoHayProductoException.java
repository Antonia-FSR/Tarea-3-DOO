package logica;
/**
 * Excepción lanzada cuando el expendedor no puede entregar un producto.
 * Esto ocurre si el depósito solicitado está vacío o si el código de selección no corresponde a ningún producto.
 *
 *
 * @author Antonia-FSR
 */
public class NoHayProductoException extends Exception{
    public NoHayProductoException(String mensaje){
        super(mensaje);
    }
}
