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
    private Producto producto;
    private int x;
    private int y;
    /**
     ** Constructor de la clase VistaDulce.
     * * @param producto El objeto Producto lógico que se va a representar gráficamente.
     */
    public VistaDulce(Producto producto) {
        this.producto = producto;
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }
    /**
     * Dibuja el dulce en el panel gráfico utilizando las coordenadas proporcionadas.
     * Genera el cuerpo de la barra y añade detalles poligonales en los extremos
     * para simular los bordes dentados de un envoltorio plástico.
     * * @param g El contexto gráfico (Graphics) utilizado para pintar en la pantalla.
     */
    public void paint(Graphics g) {
        if (producto == null) return;

        Color colorEnvoltorio = Color.GRAY;
        String nombreEtiqueta = "Dulce";
        String tipo = producto.getClass().getSimpleName();

        if (tipo.equals("Snickers")) {
            colorEnvoltorio = new Color(139, 69, 19);
            nombreEtiqueta = "Snick";
        } else if (tipo.equals("Super8")) {
            colorEnvoltorio = new Color(255, 215, 0);
            nombreEtiqueta = "Sup 8";
        }

        g.setColor(colorEnvoltorio);
        g.fillRect(this.x, this.y + 15, 40, 20);

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(this.x - 4, this.y + 18, 4, 14);
        g.fillRect(this.x + 40, this.y + 18, 4, 14);

        if (tipo.equals("Super8")) {
            g.setColor(Color.BLACK);
        } else {
            g.setColor(Color.WHITE);
        }
        g.setFont(new Font("Arial", Font.BOLD, 10));
        g.drawString(nombreEtiqueta, this.x + 2, this.y + 26);

        g.setFont(new Font("Arial", Font.BOLD, 9));
        g.drawString("S:" + producto.getSerie(), this.x + 2, this.y + 34);
    }
}
