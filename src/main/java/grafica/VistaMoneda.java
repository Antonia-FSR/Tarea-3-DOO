package grafica;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import logica.Moneda;
/**
 * Clase encargada de la representación visual de una moneda en la interfaz gráfica.
 * Extrae los datos de una instancia lógica de Moneda (valor, serie y tipo)
 * para renderizarla con sus colores metálicos correspondientes.
 * * @author Antonia-FSR
 */
public class VistaMoneda {
    /** * Referencia a la moneda lógica que proporciona los datos a dibujar.
     */
    private Moneda monedaLogica;
    /**
     * Constructor de la clase VistaMoneda.
     * * @param monedaLogica El objeto Moneda real del cual se extraerán el valor y la serie.
     */
    public VistaMoneda(Moneda monedaLogica) {
        this.monedaLogica = monedaLogica;
    }

    /**
     * Dibuja la moneda en el panel gráfico utilizando las coordenadas proporcionadas.
     * Determina el color (bronce, plata u oro) basándose en la clase de la moneda lógica.
     * * @param g El contexto gráfico (Graphics) utilizado para dibujar en el panel.
     * @param x La posición en el eje X donde se comenzará a dibujar la moneda.
     * @param y La posición en el eje Y donde se comenzará a dibujar la moneda.
     */
    public void paint(Graphics g, int x, int y) {
        if (monedaLogica == null) return; // Si no hay moneda, no dibujamos nada

        Color colorMoneda = Color.YELLOW; // Color por defecto

        //usamos monedaLogica.getClass()
        String tipo = monedaLogica.getClass().getSimpleName();

        // Asignamos colores metálicos según la clase
        if (tipo.equals("Moneda100")) {
            colorMoneda = new Color(205, 127, 50);  // Bronce
        } else if (tipo.equals("Moneda500")) {
            colorMoneda = new Color(192, 192, 192); // Plateado
        } else if (tipo.equals("Moneda1000")) {
            colorMoneda = new Color(255, 215, 0);   // Dorado brillante
        }

        //1. Dibujar primero el fondo (círculo sólido) con el color que elegimos
        g.setColor(colorMoneda);
        g.fillOval(x, y, 50, 50);

        // 2. Dibujar el borde oscuro (Gris oscuro para que combine con cualquier metal)
        g.setColor(Color.DARK_GRAY);
        g.drawOval(x, y, 50, 50);

        // 3. Escribir el valor en el centro
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString("$" + monedaLogica.getValor(), x + 10, y + 22);

        // 4. Escribir la serie autogenerada más abajito
        g.setFont(new Font("Arial", Font.PLAIN, 10));
        g.drawString("S:" + monedaLogica.getSerie(), x + 12, y + 38);
    }
}