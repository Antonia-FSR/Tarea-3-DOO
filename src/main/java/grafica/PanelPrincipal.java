package grafica;
import logica.Expendedor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

/**
 * Panel principal de la interfaz gráfica que actúa como el contenedor de la aplicación.
 * Coordina la renderización de la máquina expendedora (vitrina, estantes con códigos,
 * panel de control y teclado numérico) junto con el espacio del comprador para interactuar
 * con las distintas denominaciones de monedas.
 * * @author Antonia-FSR
 */

public class PanelPrincipal extends JPanel {
    private Expendedor expLogico;
    private PanelExpendedor exp;
    private PanelComprador com;


    /**
     * Constructor del PanelPrincipal.
     * Inicializa el color de fondo del panel general e instancia todos los objetos
     * lógicos de prueba junto con sus respectivas clases de representación visual.
     */
    public PanelPrincipal() {

        // Fondo gris muy claro para que la máquina resalte
        this.setBackground(new Color(240, 240, 240));

        expLogico = new Expendedor(5);
        exp = new PanelExpendedor(expLogico);
        com = new PanelComprador(expLogico);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();
                com.evaluarClick(mouseX, mouseY);

                repaint();
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        exp.paintComponent(g);
    }
}
