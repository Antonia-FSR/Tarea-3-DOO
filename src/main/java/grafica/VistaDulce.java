package grafica;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import logica.Producto;
/**
 * Clase encargada de la representación visual de los dulces en la vitrina del expendedor.
 * Renderiza el envoltorio en forma de barra para productos como Snickers o Super 8,
 * garantizando un alto contraste para la lectura del nombre y número de serie.
 * * @author Antonia-FSR
 */
public class VistaDulce {
    /**
     * Referencia al producto lógico real del cual se extraen los datos para el dibujo.
     */
    private Producto producto;
    /**
     ** Constructor de la clase VistaDulce.
     * * @param producto El objeto Producto lógico que se va a representar gráficamente.
     */
    public VistaDulce(Producto producto) {
        this.producto = producto;
    }
    /**
     * Dibuja el dulce en el panel gráfico utilizando las coordenadas proporcionadas.
     * Genera el cuerpo de la barra y añade detalles poligonales en los extremos
     * para simular los bordes dentados de un envoltorio plástico.
     * * @param g El contexto gráfico (Graphics) utilizado para pintar en la pantalla.
     * @param x La coordenada en el eje X que sirve como punto de anclaje para el dulce.
     * @param y La coordenada en el eje Y que sirve como punto de anclaje para el dulce.
     */
    public void paint(Graphics g, int x, int y) {
        if (producto == null) return;

        Color colorEnvoltorio = Color.GRAY;
        String nombreEtiqueta = "Dulce";
        String tipo = producto.getClass().getSimpleName();

        // Asignamos colores según el tipo de dulce (Ajusta los nombres si tus clases lógicas se llaman distinto)
        if (tipo.equals("Snickers")) {
            colorEnvoltorio = new Color(139, 69, 19); // Café oscuro
            nombreEtiqueta = "Snick";
        } else if (tipo.equals("Super8")) {
            colorEnvoltorio = new Color(255, 215, 0); // Amarillo dorado
            nombreEtiqueta = "Sup 8";
        }

        // 1. Dibujar el cuerpo del dulce (una barra rectangular)
        g.setColor(colorEnvoltorio);
        g.fillRect(x, y + 15, 40, 20);

        // 2. Dibujar los bordes del envoltorio
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(x - 4, y + 18, 4, 14); // Orejita izquierda
        g.fillRect(x + 40, y + 18, 4, 14); // Orejita derecha

        // 3. Etiqueta con el nombre
        if (tipo.equals("Super8")) {
            g.setColor(Color.BLACK); // Texto negro para el amarillo
        } else {
            g.setColor(Color.WHITE); // Texto blanco para el café
        }
        g.setFont(new Font("Arial", Font.BOLD, 10));
        g.drawString(nombreEtiqueta, x + 2, y + 26); // Posición más arriba dentro de la barra

        // 4. Mostrar la serie del dulce bien visible
        g.setFont(new Font("Arial", Font.BOLD, 9)); // Letra en negrita
        // Usamos el mismo color que el nombre para mantener el buen contraste
        g.drawString("S:" + producto.getSerie(), x + 2, y + 34); // Queda justo debajo del nombre, bien centrado
    }
}
