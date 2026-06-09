package logica;


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
    private int serieCoca = 1000;
    private int serieSprite = 2000;
    private int serieFanta = 3000;
    private int serieSnickers = 4000;
    private int serieSuper8 = 5000;

    private int capacidadInicial;
    private int numeroDigitado = -1;

    public void setNumeroDigitado(int numero) {
        this.numeroDigitado = numero;
    }

    public int getNumeroDigitado() {
        return numeroDigitado;
    }
    //Depósito para guardar las monedas con las que se paga
    private Deposito<Moneda> depositoGanancias;

    //"Depósito especial" de capacidad 1 para el producto recién comprado.
    private Producto productoEntregado;
    private void limpiarVuelto(){
        while(monVU.get() != null){}
    }

    /**
     * Constructor que inicializa cada deposito y los llena con una cantidad dada.
     * @param cantidad Número de unidades de cada producto que cada deposito contendrá.
     */
    public Expendedor(int cantidad){
        capacidadInicial = cantidad;

        this.cocacolaD= new Deposito<Bebida>();
        this.spriteD= new Deposito<Bebida>();
        this.fantaD= new Deposito<Bebida>();
        this.snickersD= new Deposito<Dulce>();
        this.super8D= new Deposito<Dulce>();
        this.monVU= new Deposito<Moneda>();

        // inicializamos el depósito de ganancias y dejamos la entrega vacia
        this.depositoGanancias = new Deposito<Moneda>();
        this.productoEntregado = null;

        for(int i=0;i<cantidad;i++){

            cocacolaD.add(new CocaCola(serieCoca++));
            spriteD.add(new Sprite(serieSprite++));
            fantaD.add(new Fanta(serieFanta++));
            snickersD.add(new Snickers(serieSnickers++));
            super8D.add(new Super8(serieSuper8++));
        }
    }

    /**
     * Procesa la compra de un producto, recibiendo la elección y una moneda de pago.
     * Si la compra es exitosa, el producto quedará disponible en el compartimento de entrega.
     * @param m Moneda con la que se realizará el pago
     * @param eleccion  Codigo con el que el producto elegido se identifica en {@link Inventario}
     * @throws PagoIncorrectoException Si se intenta pagar con una moneda nula.
     * @throws PagoInsuficienteException Si se intenta pagar con una moneda de valor menor al del precio del producto.
     * @throws NoHayProductoException Si el codigo de la elección no existe o el deposito está vacio.
     */

    public void comprarProducto(Moneda m, int eleccion) throws PagoIncorrectoException, PagoInsuficienteException, NoHayProductoException, DepositoProductoOcupadoException {
        Producto p= null;
        int precioProducto= 0;


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

        if (productoEntregado != null) {
            monVU.add(m);
            throw new DepositoProductoOcupadoException("Retire primero el producto anterior!");
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

        //La compra fue exitosa. Guardamos la moneda de pago en las ganancias.
        depositoGanancias.add(m);

        //Dejamos el producto en el "depósito especial" de entrega.
        this.productoEntregado = p;

        // vuelto inteligente en valores distintos de 100.
        int vuelto = m.getValor() - precioProducto;

        while (vuelto >= 1000) {
            monVU.add(new Moneda1000());
            vuelto -= 1000;
        }
        while (vuelto >= 500) {
            monVU.add(new Moneda500());
            vuelto -= 500;
        }
        while(vuelto >= 100){
            monVU.add(new Moneda100());
            vuelto -= 100;
        }

    }
    //Metodo equivalente a "meter la mano" para sacar el producto.
    /**
     * Permite retirar el producto comprado del compartimento de entrega.
     * @return El {@link Producto} comprado o null si está vacío.
     */
    public Producto getProducto() {
        Producto productoRetirado = this.productoEntregado;
        this.productoEntregado = null; // Se vacía el compartimento al sacarlo
        return productoRetirado;
    }

    /**
     * Permite retirar las monedas almacenadas en el deposito del vuelto.
     */
    public Moneda getVuelto() {
        return monVU.get();
    }

    public Deposito<Bebida> getCocacolaD() { return cocacolaD; }
    public Deposito<Bebida> getSpriteD() { return spriteD; }
    public Deposito<Bebida> getFantaD() { return fantaD; }
    public Deposito<Dulce> getSnickersD() { return snickersD; }
    public Deposito<Dulce> getSuper8D() { return super8D; }

    public Producto getProductoEntregado() { return productoEntregado; }


    public void rellenarDepositos() {

        while (cocacolaD.getSize() < capacidadInicial) {
            cocacolaD.add(new CocaCola(serieCoca++));
        }

        while (spriteD.getSize() < capacidadInicial) {
            spriteD.add(new Sprite(serieSprite++));
        }

        while (fantaD.getSize() < capacidadInicial) {
            fantaD.add(new Fanta(serieFanta++));
        }

        while (snickersD.getSize() < capacidadInicial) {
            snickersD.add(new Snickers(serieSnickers++));
        }

        while (super8D.getSize() < capacidadInicial) {
            super8D.add(new Super8(serieSuper8++));
        }
    }
}

