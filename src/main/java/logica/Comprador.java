package logica;

import excepciones.*;
import expendedor.Expendedor;
import monedas.Moneda;
import productos.Producto;

/**
 * Clase que actua como un comprador que interactua con {@link Expendedor}.
 * Entrega una moneda, un codigo de selección y procesa el consumo del producto comprado y el vuelto.
 *
 * @author 9ghtBug
 */
public class Comprador {
    private String sabor;
    private int vuelto;

    /**
     * @param m Moneda usada para pagar.
     * @param eleccion Codigo de selección del producto.
     * @param exp Referencia al expendedor del cual se comprara.
     * @throws PagoIncorrectoException Si la moneda es null.
     * @throws PagoInsuficienteException Si el valor de la moneda es inferior al del producto que se quiere comprar.
     * @throws NoHayProductoException Si no hay stock o el codigo del producto es invalido.
     */
    public Comprador(Moneda m, int eleccion, Expendedor exp) throws PagoIncorrectoException, PagoInsuficienteException, NoHayProductoException {

        Producto p = exp.comprarProducto(m, eleccion);

        if (p!= null) {
            sabor= p.Consumir();
        } else {
            sabor= null;
        }

        vuelto = 0;
        Moneda monedaVuelto;

        while ((monedaVuelto= exp.getVuelto())!= null) {
            vuelto+= monedaVuelto.getValor();
        }
    }

    public String queSabor() {
        return sabor;
    }

    public int cuantoVuelto() {
        return vuelto;
    }
}