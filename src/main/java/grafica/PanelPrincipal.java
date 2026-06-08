package grafica;
import logica.Expendedor;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class PanelPrincipal extends JPanel {
    private Expendedor expLogico;
    private PanelExpendedor exp;
    private PanelComprador com;

    public PanelPrincipal() {
        // Fondo gris muy claro para que la máquina resalte
        this.setBackground(new Color(240, 240, 240));

        expLogico= new Expendedor(5);
        exp= new PanelExpendedor(expLogico);
        com= new PanelComprador();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        exp.paintComponent(g);

    }
}
