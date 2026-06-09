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
    private Moneda monedaLogica;
    private int x;
    private int y;
    /**
     * Constructor de la clase VistaMoneda.
     * * @param monedaLogica El objeto Moneda real del cual se extraerán el valor y la serie.
     */
    public VistaMoneda(Moneda monedaLogica) {
        this.monedaLogica = monedaLogica;
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Dibuja la moneda en el panel gráfico utilizando las coordenadas proporcionadas.
     * Determina el color (bronce, plata u oro) basándose en la clase de la moneda lógica.
     * * @param g El contexto gráfico (Graphics) utilizado para dibujar en el panel.
     */
    public void paint(Graphics g) {
        if (monedaLogica == null) return;
        Color colorMoneda = Color.YELLOW;
        String tipo = monedaLogica.getClass().getSimpleName();

        if (tipo.equals("Moneda100")) {
            colorMoneda = new Color(205, 127, 50);
        } else if (tipo.equals("Moneda500")) {
            colorMoneda = new Color(192, 192, 192);
        } else if (tipo.equals("Moneda1000")) {
            colorMoneda = new Color(255, 215, 0);
        }

        g.setColor(colorMoneda);
        g.fillOval(this.x, this.y, 50, 50);

        g.setColor(Color.DARK_GRAY);
        g.drawOval(this.x, this.y, 50, 50);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString("$" + monedaLogica.getValor(), this.x + 10, this.y + 22);

        g.setFont(new Font("Arial", Font.PLAIN, 10));
        g.drawString("S:" + monedaLogica.getSerie(), this.x + 12, this.y + 38);
    }
}