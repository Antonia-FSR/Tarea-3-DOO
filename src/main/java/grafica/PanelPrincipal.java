package grafica;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class PanelPrincipal extends JPanel {

    public PanelPrincipal() {
        // Fondo gris muy claro para que la máquina resalte
        this.setBackground(new Color(240, 240, 240));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // --- PREPARAMOS EL PINCEL AVANZADO (Graphics2D) ---
        Graphics2D g2 = (Graphics2D) g;

        // ESTO ES CLAVE: Suaviza las líneas para que no se vean "pixeladas"
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // --- DEFINIMOS LAS COORDENADAS BASE DE LA MÁQUINA ---
        int mX = 100; // Posición X inicial
        int mY = 50;  // Posición Y inicial
        int mW = 400; // Ancho total
        int mH = 550; // Alto total
        int radius = 30; // Radio para las esquinas redondeadas

        // --- 1. CUERPO DE LA MÁQUINA CON EFECTO METÁLICO (GRADIENTE) ---
        // Creamos un degradado que va de un rojo brillante (centro) a un rojo oscuro (lados)
        GradientPaint metalGradient = new GradientPaint(
                mX, 0, new Color(200, 0, 0),       // Rojo oscuro (izquierda)
                mX + mW/2, 0, new Color(255, 60, 60), // Rojo brillante (centro)
                true); // Repetir el gradiente

        g2.setPaint(metalGradient);
        g2.fillRoundRect(mX, mY, mW, mH, radius, radius);

        // --- 2. MARCO OSCURO PARA DARLE VOLUMEN (SOMBRA) ---
        g2.setColor(new Color(120, 0, 0));
        g2.setStroke(new BasicStroke(4)); // Línea más gruesa
        g2.drawRoundRect(mX, mY, mW, mH, radius, radius);

        // --- 3. EL VIDRIO (PANEL DE PRODUCTOS) ---
        int pX = mX + 50;
        int pY = mY + 60;
        int pW = 200;
        int pH = 350;
        int pRad = 20;

        // Color cian muy claro y ligeramente transparente
        g2.setColor(new Color(150, 245, 255, 180));
        g2.fillRoundRect(pX, pY, pW, pH, pRad, pRad);

        // Marco oscuro para el vidrio
        g2.setColor(new Color(80, 80, 80));
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(pX, pY, pW, pH, pRad, pRad);

        // --- 4. EFECTO DE REFLEJO EN EL VIDRIO (DIBUJANDO LÍNEAS) ---
        g2.setColor(new Color(255, 255, 255, 150)); // Blanco semi-transparente
        g2.setStroke(new BasicStroke(2));
        // Dibujamos dos líneas diagonales finas para simular reflejo de luz
        g2.drawLine(pX + 20, pY + 20, pX + 70, pY + pH - 20);
        g2.drawLine(pX + 40, pY + 20, pX + 90, pY + pH - 20);

        // --- 5. BANDEJA DE ENTREGA (MÁS MODERNA) ---
        g2.setColor(new Color(60, 60, 60)); // Gris muy oscuro
        g2.fillRoundRect(pX, pY + pH + 30, pW, 50, 15, 15);

        // Agregamos un pequeño borde metalizado a la bandeja
        g2.setColor(new Color(180, 180, 180));
        g2.setStroke(new BasicStroke(1));
        g2.drawRoundRect(pX, pY + pH + 30, pW, 50, 15, 15);

        // --- 6. PANEL DE CONTROL Y BOTONES ESTÉTICOS (REDISENADO) ---
        int ctrlX = mX + 280;
        int ctrlY = pY + 30;
        int ctrlW = 80;
        int ctrlH = 220;

        // Fondo oscuro del panel de control
        g2.setColor(new Color(30, 30, 30));
        g2.fillRoundRect(ctrlX, ctrlY, ctrlW, ctrlH, 15, 15);

        // Borde sutil para el panel
        g2.setColor(new Color(100, 100, 100));
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(ctrlX, ctrlY, ctrlW, ctrlH, 15, 15);

        // a) Nueva Pantalla Digital (Celeste azulado)
        g2.setColor(new Color(150, 220, 255)); // Celeste claro brillante
        g2.fillRoundRect(ctrlX + 10, ctrlY + 15, 60, 30, 5, 5);

        // Borde oscuro para hundir un poco la pantalla
        g2.setColor(new Color(20, 20, 20));
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(ctrlX + 10, ctrlY + 15, 60, 30, 5, 5);

        // b) Teclado numérico (Subido un poco para hacer espacio abajo)
        g2.setColor(new Color(200, 200, 200));
        int btnInicioX = ctrlX + 13;
        int btnInicioY = ctrlY + 65;

        for (int fila = 0; fila < 3; fila++) {
            for (int col = 0; col < 3; col++) {
                int bx = btnInicioX + (col * 20);
                int by = btnInicioY + (fila * 20);
                g2.fillRoundRect(bx, by, 14, 14, 4, 4);
            }
        }

        // c) Botón "PUSH" (Movido a la izquierda y más angosto)
        int pushX = ctrlX + 10;
        int pushY = btnInicioY + 75;
        g2.setColor(new Color(0, 180, 0)); // Color verde
        g2.fillRoundRect(pushX, pushY, 38, 28, 8, 8);

        // Texto PUSH centrado en el nuevo botón
        g2.setColor(Color.WHITE);
        g2.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 9));
        g2.drawString("PUSH", pushX + 7, pushY + 18);

        // d) Nueva Ranura de Monedas (A la derecha del PUSH)
        int slotX = pushX + 45; // Se ubica a la derecha del botón
        int slotY = pushY - 5;  // Un poquito más alto que el botón

        // Rectángulo gris claro base
        g2.setColor(Color.LIGHT_GRAY);
        g2.fillRoundRect(slotX, slotY, 16, 38, 4, 4);

        // Raya negra vertical profunda
        g2.setColor(Color.BLACK);
        g2.fillRect(slotX + 6, slotY + 4, 4, 30);

        // --- 7. RECEPTÁCULO DE VUELTO (ESTÉTICO) ---
        // Lo ubicamos unos pixeles más abajo del panel de control negro
        int vueltoX = ctrlX + 10;
        int vueltoY = ctrlY + ctrlH + 25;
        int vueltoW = 60;
        int vueltoH = 45;

        // Texto "VUELTO" encima del cuadradito
        // Usamos color blanco o negro dependiendo de qué tan bien resalte sobre el fondo rojo
        g2.setColor(Color.BLACK);
        g2.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 10));
        g2.drawString("VUELTO", vueltoX + 8, vueltoY - 6);

        // Fondo oscuro de la cavidad donde caen las monedas
        g2.setColor(new Color(40, 40, 40));
        g2.fillRoundRect(vueltoX, vueltoY, vueltoW, vueltoH, 10, 10);

        // Borde gris metálico para darle profundidad y relieve
        g2.setColor(new Color(160, 160, 160));
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(vueltoX, vueltoY, vueltoW, vueltoH, 10, 10);

        // Una pequeña tapita plástica oscura adentro (la solapa que uno empuja para sacar las monedas)
        g2.setColor(new Color(25, 25, 25));
        g2.fillRoundRect(vueltoX + 5, vueltoY + 5, vueltoW - 10, vueltoH - 12, 5, 5);
    }
}
