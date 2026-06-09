package logica;
/**
 * Excepción lanzada cuando no se ha retirado producto del expendedor.
 *
 *
 * @author 9ghtBug
 */
public class DepositoProductoOcupadoException extends Exception{
    public DepositoProductoOcupadoException(String mensaje){
        super(mensaje);
    }
}
