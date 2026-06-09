package grafica;
import logica.*;

import java.awt.*;
import javax.swing.JPanel;

/**
 *  Sobrescribe el metodo de dibujo para renderizar todos los componentes del panel.
 * Dibuja de manera secuencial el chasis de la máquina, la cavidad interna oscura,
 * los estantes con sus etiquetas numéricas de selección, el panel de control lateral,
 * el teclado indexado y los elementos gráficos de prueba.
 * * @param g El contexto gráfico (Graphics) utilizado para pintar en el componente.
 */
public class PanelExpendedor extends JPanel {
    private Expendedor expendedor;

    private VistaBebida displaySprite;
    private VistaBebida displayFanta;
    private VistaBebida displayCoca;
    private VistaDulce displaySnickers;
    private VistaDulce displaySuper8;

    public PanelExpendedor(Expendedor exp) {
        this.expendedor = exp;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int mX = 100;
        int mY = 50;
        int mW = 400;
        int mH = 550;
        int radius = 30;

        GradientPaint metalGradient = new GradientPaint(
                mX, 0, new Color(200, 0, 0),
                mX + mW / 2, 0, new Color(255, 60, 60),
                true);

        g2.setPaint(metalGradient);
        g2.fillRoundRect(mX, mY, mW, mH, radius, radius);

        g2.setColor(new Color(120, 0, 0));
        g2.setStroke(new BasicStroke(4));
        g2.drawRoundRect(mX, mY, mW, mH, radius, radius);

        int pX = mX + 50;
        int pY = mY + 60;
        int pW = 200;
        int pH = 350;
        int pRad = 20;

        g2.setColor(new Color(30, 35, 40));
        g2.fillRoundRect(pX, pY, pW, pH, pRad, pRad);

        g2.setColor(new Color(80, 80, 80));
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(pX, pY, pW, pH, pRad, pRad);

        g2.setColor(new Color(255, 255, 255, 150));
        g2.setStroke(new BasicStroke(2));
        g2.setColor(new Color(60, 60, 60));
        g2.setStroke(new BasicStroke(2));

        g2.drawLine(pX, pY + 110, pX + pW, pY + 110);

        g2.drawLine(pX, pY + 210, pX + pW, pY + 210);

        g2.drawLine(pX, pY + 310, pX + pW, pY + 310);

        g2.setColor(new Color(255, 215, 0)); // Un color amarillo dorado para que resalte en el fondo oscuro
        g2.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 9));

        g2.drawString("1", pX + 30, pY + 122);  // Espacio para la primera bebida
        g2.drawString("2", pX + 90, pY + 122);  // Espacio para la segunda bebida
        g2.drawString("3", pX + 150, pY + 122); // Espacio para la tercera bebida

        g2.drawString("4", pX + 30, pY + 222);  // Espacio para el primer dulce
        g2.drawString("5", pX + 90, pY + 222);  // Espacio para el segundo dulce
        g2.drawString("6", pX + 150, pY + 222); // Espacio para el tercer dulce

        g2.drawString("7", pX + 30, pY + 322);
        g2.drawString("8", pX + 90, pY + 322);
        g2.drawString("9", pX + 150, pY + 322);

        g2.setColor(new Color(60, 60, 60)); // Gris muy oscuro
        g2.fillRoundRect(pX, pY + pH + 30, pW, 50, 15, 15);

        g2.setColor(new Color(180, 180, 180));
        g2.setStroke(new BasicStroke(1));
        g2.drawRoundRect(pX, pY + pH + 30, pW, 50, 15, 15);

        int ctrlX = mX + 280;
        int ctrlY = pY + 30;
        int ctrlW = 80;
        int ctrlH = 220;

        g2.setColor(new Color(30, 30, 30));
        g2.fillRoundRect(ctrlX, ctrlY, ctrlW, ctrlH, 15, 15);

        g2.setColor(new Color(100, 100, 100));
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(ctrlX, ctrlY, ctrlW, ctrlH, 15, 15);

        g2.setColor(new Color(150, 220, 255));
        g2.fillRoundRect(ctrlX + 10, ctrlY + 15, 60, 30, 5, 5);

        g2.setColor(new Color(20, 20, 20));
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(ctrlX + 10, ctrlY + 15, 60, 30, 5, 5);

        int btnInicioX = ctrlX + 13;
        int btnInicioY = ctrlY + 65;
        int numeroBoton = 1;

        for (int fila = 0; fila < 3; fila++) {
            for (int col = 0; col < 3; col++) {
                int bx = btnInicioX + (col * 20);
                int by = btnInicioY + (fila * 20);

                g2.setColor(new Color(200, 200, 200));
                g2.fillRoundRect(bx, by, 14, 14, 4, 4);

                g2.setColor(Color.BLACK);
                g2.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 10));

                g2.drawString(String.valueOf(numeroBoton), bx + 4, by + 12);

                numeroBoton++;
            }
        }

        int pushX = ctrlX + 10;
        int pushY = btnInicioY + 75;
        g2.setColor(new Color(0, 180, 0)); // Color verde
        g2.fillRoundRect(pushX, pushY, 38, 28, 8, 8);

        g2.setColor(Color.WHITE);
        g2.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 9));
        g2.drawString("PUSH", pushX + 7, pushY + 18);

        int slotX = pushX + 45;
        int slotY = pushY - 5;

        g2.setColor(Color.LIGHT_GRAY);
        g2.fillRoundRect(slotX, slotY, 16, 38, 4, 4);

        g2.setColor(Color.BLACK);
        g2.fillRect(slotX + 6, slotY + 4, 4, 30);

        int vueltoX = ctrlX + 10;
        int vueltoY = ctrlY + ctrlH + 25;
        int vueltoW = 60;
        int vueltoH = 45;

        g2.setColor(Color.BLACK);
        g2.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 10));
        g2.drawString("VUELTO", vueltoX + 8, vueltoY - 6);

        g2.setColor(new Color(40, 40, 40));
        g2.fillRoundRect(vueltoX, vueltoY, vueltoW, vueltoH, 10, 10);

        g2.setColor(new Color(160, 160, 160));
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(vueltoX, vueltoY, vueltoW, vueltoH, 10, 10);

        g2.setColor(new Color(25, 25, 25));
        g2.fillRoundRect(vueltoX + 5, vueltoY + 5, vueltoW - 10, vueltoH - 12, 5, 5);

        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Consolas", Font.BOLD, 18));

        if (expendedor.getNumeroDigitado() != -1) {
            String texto = String.valueOf(expendedor.getNumeroDigitado());

            FontMetrics fm = g2.getFontMetrics();
            int x = ctrlX + 10 + (60 - fm.stringWidth(texto)) / 2;
            int y = ctrlY + 15 + ((30 - fm.getHeight()) / 2) + fm.getAscent();

            g2.drawString(texto, x, y);
        }

        //DISPLAY
        int alturaRepisa1 = pY + 60;

        //Cocacola
        int frontXCoca = pX + 20;
        int frontYCoca = alturaRepisa1;
        int cantCoca = expendedor.getCocacolaD().getSize();
        for (int i = cantCoca - 1; i >= 0; i--) {
            Producto p = expendedor.getCocacolaD().getItem(i);
            VistaBebida vb = new VistaBebida(p);
            vb.setXY(frontXCoca + (i * 6), frontYCoca - (i * 6));
            vb.paint(g2);
        }

        //Sprite
        int frontXSprite = pX + 80;
        int frontYSprite = alturaRepisa1;
        int cantSprite = expendedor.getSpriteD().getSize();
        for (int i = cantSprite - 1; i >= 0; i--) {
            Producto p = expendedor.getSpriteD().getItem(i);
            VistaBebida vb = new VistaBebida(p);
            vb.setXY(frontXSprite + (i * 6), frontYSprite - (i * 6));
            vb.paint(g2);
        }

        //Fanta
        int frontXFanta = pX + 140;
        int frontYFanta = alturaRepisa1;
        int cantFanta = expendedor.getFantaD().getSize();
        for (int i = cantFanta - 1; i >= 0; i--) {
            Producto p = expendedor.getFantaD().getItem(i);
            VistaBebida vb = new VistaBebida(p);
            vb.setXY(frontXFanta + (i * 6), frontYFanta - (i * 6));
            vb.paint(g2);
        }

        int alturaRepisa2 = pY + 160;

        //Snickers
        int frontXSnickers = pX + 20;
        int frontYSnickers = alturaRepisa2;
        int cantSnickers = expendedor.getSnickersD().getSize();
        for (int i = cantSnickers - 1; i >= 0; i--) {
            Producto p = expendedor.getSnickersD().getItem(i);
            VistaDulce vd = new VistaDulce(p);
            vd.setXY(frontXSnickers + (i * 6), frontYSnickers - (i * 6));
            vd.paint(g2);
        }

        //Super8
        int frontXSuper8 = pX + 80;
        int frontYSuper8 = alturaRepisa2;
        int cantSuper8 = expendedor.getSuper8D().getSize();
        for (int i = cantSuper8 - 1; i >= 0; i--) {
            Producto p = expendedor.getSuper8D().getItem(i);
            VistaDulce vd = new VistaDulce(p);
            vd.setXY(frontXSuper8 + (i * 6), frontYSuper8 - (i * 6));
            vd.paint(g2);
        }

        //Bandeja
        Producto entregado = expendedor.getProductoEntregado();
        if (entregado != null) {
            if (entregado instanceof Bebida) {
                VistaBebida vb = new VistaBebida(entregado);
                vb.setXY(pX + 85, pY + pH + 35);
                vb.paint(g2);
            } else if (entregado instanceof Dulce) {
                VistaDulce vd = new VistaDulce(entregado);
                vd.setXY(pX + 85, pY + pH + 35);
                vd.paint(g2);
            }
        }
    }

    public void evaluarClick(int x, int y) {
        // Zona del vidrio
        int pX = 150;
        int pY = 110;
        int pW = 200;
        int pH = 350;

        if (x >= pX && x <= pX + pW &&
                y >= pY && y <= pY + pH) {

            expendedor.rellenarDepositos();
        }
    }
}
