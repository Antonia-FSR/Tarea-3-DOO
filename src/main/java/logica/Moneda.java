package logica;

/**
 * Clase abstracta que define la base para todas las monedas.
 * Implementa {@link Comparable} para permitir la comparación de valores entre estas.
 *
 * @author Antonia-FSR
 */
public abstract class Moneda implements Comparable<Moneda> {
    public Moneda(){
    }
    public abstract int getValor();
    public String toString() {
        return "monedas.Moneda (Serie: " + this.hashCode() + ") Valor: $" + this.getValor();
    }
    public int compareTo(Moneda otraMoneda) {
        // Compara los valores de menor a mayor
        return Integer.compare(this.getValor(), otraMoneda.getValor());
    }
}
