package logica;
/**
 * Excepción lanzada cuando el expendedor no recibe una moneda valida.
 * Esto ocurre si la moneda entregda es nula (null).
 *
 *
 * @author Antonia-FSR
 */
public class PagoIncorrectoException extends Exception{
    public PagoIncorrectoException(String mensaje){
        super(mensaje);

    }
}
