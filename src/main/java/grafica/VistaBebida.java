package grafica;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import logica.Producto;

/**
 * Clase encargada de la representación visual de las bebidas en la vitrina.
 * Identifica dinámicamente si el producto es Coca-Cola, Sprite o Fanta
 * para asignarle su color característico, etiqueta y número de serie.
 * * @author Antonia-FSR
 */
public class VistaBebida {
    /**
     * Referencia al producto lógico real del cual se extraen los datos para el dibujo.
     */
    private Producto producto;
    private int x;
    private int y;
    /**
     * Constructor de la clase VistaBebida.
     * * @param producto El objeto Producto lógico que se va a representar gráficamente.
     */
    public VistaBebida(Producto producto) {
        this.producto = producto;
    }
    /**
     * Actualiza la posición de la vista dentro del panel.
     * @param x Coordenada horizontal.
     * @param y Coordenada vertical.
     */
    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }
    /**
     * Dibuja la lata de bebida en el panel gráfico utilizando las coordenadas proporcionadas.
     * El metodo calcula de forma relativa las posiciones de la estructura, tapas,
     * brillo cilíndrico y texto para asegurar un posicionamiento dinámico en los estantes.
     * * @param g El contexto gráfico (Graphics) utilizado para pintar en la pantalla.
     */
    public void paint(Graphics g) {
        if (producto == null) return;

        Color colorLata = Color.GRAY;
        String nombreEtiqueta = "Bebida";
        String tipo = producto.getClass().getSimpleName();

        if (tipo.equals("CocaCola")) {
            colorLata = new Color(200, 0, 0);
            nombreEtiqueta = "Coca";
        } else if (tipo.equals("Sprite")) {
            colorLata = new Color(0, 150, 0);
            nombreEtiqueta = "Sprite";
        } else if (tipo.equals("Fanta")) {
            colorLata = new Color(255, 120, 0);
            nombreEtiqueta = "Fanta";
        }

        g.setColor(colorLata);
        g.fillRoundRect(this.x, this.y, 30, 50, 8, 8);

        g.setColor(new Color(255, 255, 255, 100));
        g.fillRect(this.x + 4, this.y + 2, 6, 46);

        g.setColor(Color.LIGHT_GRAY);
        g.fillOval(this.x, this.y - 4, 30, 10);
        g.fillOval(this.x, this.y + 44, 30, 10);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 10));
        g.drawString(nombreEtiqueta, this.x + 2, this.y + 22);

        g.setFont(new Font("Arial", Font.BOLD, 9));
        g.drawString("S:" + producto.getSerie(), this.x + 2, this.y + 36);
    }
}
