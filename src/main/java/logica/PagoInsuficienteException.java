package logica;
/**
 * Excepción lanzada cuando el expendedor no recibe una moneda con un valor suficiente.
 * Esto ocurre si el valor de la moneda ingresada es menor al valor del producto.
 *
 *
 * @author Antonia-FSR
 */
public class PagoInsuficienteException extends Exception{
    public PagoInsuficienteException(String mensaje){
        super(mensaje);
    }
}
