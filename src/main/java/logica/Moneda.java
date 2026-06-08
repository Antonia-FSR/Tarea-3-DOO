package logica;

/**
 * Clase abstracta que define la base para todas las monedas.
 * Implementa {@link Comparable} para permitir la comparación de valores entre estas.
 *
 * @author Antonia-FSR
 */
public abstract class Moneda implements Comparable<Moneda> {

    //Generador automático para que cada moneda tenga un número único.
    // Al ser "static", pertenece a la clase y no se reinicia con cada moneda nueva.
    private static int generadorSerie = 1;

    private int serie;

    public Moneda(){
        //inicializamos la serie en el constructor y aumentamos el contador para la próxima.
        this.serie = generadorSerie;
        generadorSerie++;
    }

    //metodo para obtener la serie (será vital para cuando queramos dibujarla en la interfaz gráfica).
    public int getSerie() {
        return serie;
    }

    public abstract int getValor();

    public String toString() {
        // NUEVO: Ahora usamos nuestro propio atributo serie en lugar del hashCode().
        return "Moneda (Serie: " + this.serie + ") Valor: $" + this.getValor();
    }

    public int compareTo(Moneda otraMoneda) {
        // Compara los valores de menor a mayor
        return Integer.compare(this.getValor(), otraMoneda.getValor());
    }
}

