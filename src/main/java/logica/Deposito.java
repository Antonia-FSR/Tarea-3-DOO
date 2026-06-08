package logica;

/**
 * Clase generica que representa un deposito que puede almacenar productos.
 * Utilizada para gestionar los productos y el vuelto.
 *
 * @author Antonia-FSR
 * @param <T> Tipo de dato que se almacenará en el deposito. ({@link productos.Bebida}, {@link productos.Dulce}, {@link monedas.Moneda})
 */

import java.util.ArrayList;

public class Deposito<T> {
    private ArrayList<T> lista;

    public Deposito(){
        lista=new ArrayList<>();
    }

    public void add(T item){
        lista.add(item);
    }

    public T get(){
        if(lista.isEmpty()){
            return null;
        }
        return lista.remove(0);
    }

    public T getItem(int index) {
        return lista.get(index);
    }
    public int getSize() {
        return lista.size();
    }
}
