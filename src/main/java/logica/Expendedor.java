package logica;

import excepciones.*;
import monedas.*;
import productos.*;

/**
 * Clase que simula expendedor de productos.
 * Gestiona depositos con los productos que se pueden comprar, acepta pagos con monedas.
 * Calcula el vuelto en monedas de 100.
 *
 * @author jaocSec
 * @see Producto
 * @see Moneda
 */
public class Expendedor {
    private Deposito<Bebida> cocacolaD;
    private Deposito<Bebida> spriteD;
    private Deposito<Bebida> fantaD;
    private Deposito<Dulce> snickersD;
    private Deposito<Dulce> super8D;
    private Deposito<Moneda> monVU;

    private void limpiarVuelto(){
        while(monVU.get() != null){}
    }

    /**
     * Constructor que inicializa cada deposito y los llena con una cantidad dada.
     * @param cantidad Número de unidades de cada producto que cada deposito contendrá.
     */
    public Expendedor(int cantidad){
        this.cocacolaD= new Deposito<Bebida>();
        this.spriteD= new Deposito<Bebida>();
        this.fantaD= new Deposito<Bebida>();
        this.snickersD= new Deposito<Dulce>();
        this.super8D= new Deposito<Dulce>();
        this.monVU= new Deposito<Moneda>();

        for (int i= 0; i < cantidad; i++)
        {
            cocacolaD.add(new CocaCola(1000 + i));
            spriteD.add(new Sprite(2000 + i));
            fantaD.add(new Fanta(3000 + i));
            snickersD.add(new Snickers(4000 + i));
            super8D.add(new Super8(5000 + i));
        }
    }

    /**
     * Procesa la compra de un producto, recibiendo la elección y una moneda de pago.
     * @param m Moneda con la que se realizará el pago
     * @param eleccion  Codigo con el que el producto elegido se identifica en {@link Inventario}
     * @return producto solicitado si la transaccion es completada. {@link Producto}
     * @throws PagoIncorrectoException Si se intenta pagar con una moneda nula.
     * @throws PagoInsuficienteException Si se intenta pagar con una moneda de valor menor al del precio del producto.
     * @throws NoHayProductoException Si el codigo de la elección no existe o el deposito está vacio.
     */
    public Producto comprarProducto(Moneda m, int eleccion) throws PagoIncorrectoException, PagoInsuficienteException, NoHayProductoException {
        Producto p= null;
        int precioProducto= 0;

        limpiarVuelto();

        if(m == null) //No existe moneda
        {
            throw new PagoIncorrectoException("Error: No hay moneda para pagar.");
        }

        Inventario seleccion= null;
        for(Inventario i: Inventario.values()){
            if(i.getSeleccion() == eleccion){
                seleccion= i;
                break;
            }
        }
        if (seleccion == null){
            monVU.add(m);
            throw new NoHayProductoException("No existe el producto!");
        }

        precioProducto = seleccion.getPrecio();
        switch (seleccion){
            case COCA_COLA:
                if(m.getValor() >= precioProducto) p = cocacolaD.get();
                break;

            case SPRITE:
                if(m.getValor() >= precioProducto) p = spriteD.get();
                break;

            case FANTA:
                if(m.getValor() >= precioProducto) p = fantaD.get();
                break;

            case SNICKERS:
                if(m.getValor() >= precioProducto) p = snickersD.get();
                break;

            case SUPER8:
                if(m.getValor() >= precioProducto) p = super8D.get();
                break;
        }


        if(m.getValor() < precioProducto){ //El valor de la moneda es menor al del producto

            monVU.add(m);
            throw new PagoInsuficienteException("Cantidad insuficiente de dinero.");
        }

        if (p == null){ //No hay producto

            monVU.add(m);
            throw new NoHayProductoException("No hay existencias del producto seleccionado");
        }

        int vuelto= m.getValor() - precioProducto;
        while(vuelto >= 100){
            monVU.add(new Moneda100());
            vuelto -= 100;
        }

        return p;
    }

    /**
     * Permite retirar las monedas almacenadas en el deposito del vuelto tras una transacción exitosa o un pago fallido.
     * Extrae las monedas de una en una.
     * @return {@link Moneda} de valor 100 o la moneda original si el pago es fallido.
     */
    public Moneda getVuelto() {
        return monVU.get();
    }
}

