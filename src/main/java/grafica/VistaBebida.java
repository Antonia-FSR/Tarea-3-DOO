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
     * @param x La coordenada en el eje X que sirve como punto de anclaje para la lata.
     * @param y La coordenada en el eje Y que sirve como punto de anclaje para la lata.
     */
    public void paint(Graphics g, int x, int y) {
        if (producto == null) return;

        // 1. Detectar qué bebida es para asignarle su color característico
        Color colorLata = Color.GRAY;
        String nombreEtiqueta = "Bebida";

        // Usamos el nombre de la clase lógica para saber de qué sabor es
        String tipo = producto.getClass().getSimpleName();

        if (tipo.equals("CocaCola")) {
            colorLata = new Color(200, 0, 0); // Rojo clásico
            nombreEtiqueta = "Coca";
        } else if (tipo.equals("Sprite")) {
            colorLata = new Color(0, 150, 0); // Verde
            nombreEtiqueta = "Sprite";
        } else if (tipo.equals("Fanta")) {
            colorLata = new Color(255, 120, 0); // Naranja
            nombreEtiqueta = "Fanta";
        }

        // 2. Dibujar el cuerpo de la lata (un rectángulo redondeado)
        g.setColor(colorLata);
        g.fillRoundRect(x, y, 30, 50, 8, 8);

        // 3. Efecto de cilindro: un pequeño brillo blanco semitransparente a un costado
        g.setColor(new Color(255, 255, 255, 100));
        g.fillRect(x + 4, y + 2, 6, 46);

        // 4. Tapas metálicas (arriba y abajo) usando óvalos aplastados
        g.setColor(Color.LIGHT_GRAY);
        g.fillOval(x, y - 4, 30, 10); // Tapa superior
        g.fillOval(x, y + 44, 30, 10); // Base de la lata

        // 5. Etiqueta con el nombre
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 10)); // Letra más grande y en negrita
        g.drawString(nombreEtiqueta, x + 2, y + 22); // Subimos un poco el nombre

        // 6. Serie visible
        g.setFont(new Font("Arial", Font.BOLD, 9)); // Serie en negrita para que resalte
        g.drawString("S:" + producto.getSerie(), x + 2, y + 36); // Posición ajustada para quedar dentro de la lata
    }
}
