package grafica;
import logica.*;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
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

        // --- PREPARAMOS EL PINCEL AVANZADO (Graphics2D) ---
        Graphics2D g2 = (Graphics2D) g;

        //Suaviza las líneas para que no se vean "pixeladas"
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
                mX + mW / 2, 0, new Color(255, 60, 60), // Rojo brillante (centro)
                true); // Repetir el gradiente

        g2.setPaint(metalGradient);
        g2.fillRoundRect(mX, mY, mW, mH, radius, radius);

        // --- 2. MARCO OSCURO PARA DARLE VOLUMEN (SOMBRA) ---
        g2.setColor(new Color(120, 0, 0));
        g2.setStroke(new BasicStroke(4)); // Línea más gruesa
        g2.drawRoundRect(mX, mY, mW, mH, radius, radius);

        // --- 3. EL FONDO (PANEL DE PRODUCTOS) ---
        int pX = mX + 50;
        int pY = mY + 60;
        int pW = 200;
        int pH = 350;
        int pRad = 20;

        // Color gris oscuro/azulado para dar profundidad al interior de la máquina
        g2.setColor(new Color(30, 35, 40));
        g2.fillRoundRect(pX, pY, pW, pH, pRad, pRad);

        // Marco oscuro para el borde interno
        g2.setColor(new Color(80, 80, 80));
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(pX, pY, pW, pH, pRad, pRad);

        // --- 4. EFECTO DE REFLEJO EN EL VIDRIO (DIBUJANDO LÍNEAS) ---
        g2.setColor(new Color(255, 255, 255, 150)); // Blanco semi-transparente
        g2.setStroke(new BasicStroke(2));
        // --- 4.5 ESTANTES HORIZONTALES ---
        // Dibujaremos 3 estantes distribuidos a lo largo del vidrio
        g2.setColor(new Color(60, 60, 60)); // Color gris oscuro metálico
        g2.setStroke(new BasicStroke(2)); // Línea delgada

        // Estante 1 (Superior, donde está la Coca)
        g2.drawLine(pX, pY + 110, pX + pW, pY + 110);

        // Estante 2 (Medio, donde está el Snickers)
        g2.drawLine(pX, pY + 210, pX + pW, pY + 210);

        // Estante 3 (Inferior)
        g2.drawLine(pX, pY + 310, pX + pW, pY + 310);

        // --- 4.6 ETIQUETAS DE LOS ESTANTES (NUEVO) ---
        g2.setColor(new Color(255, 215, 0)); // Un color amarillo dorado para que resalte en el fondo oscuro
        g2.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 9));

        // Etiquetas para el Estante 1 (Bebidas)
        // Las posicionamos justo debajo de la línea del estante (pY + 110)
        g2.drawString("1", pX + 30, pY + 122);  // Espacio para la primera bebida
        g2.drawString("2", pX + 90, pY + 122);  // Espacio para la segunda bebida
        g2.drawString("3", pX + 150, pY + 122); // Espacio para la tercera bebida

        // Etiquetas para el Estante 2 (Dulces)
        // Justo debajo del segundo estante (pY + 210)
        g2.drawString("4", pX + 30, pY + 222);  // Espacio para el primer dulce
        g2.drawString("5", pX + 90, pY + 222);  // Espacio para el segundo dulce
        g2.drawString("6", pX + 150, pY + 222); // Espacio para el tercer dulce

        // Etiquetas para el Estante 3
        // Justo debajo del tercer estante (pY + 310)
        g2.drawString("7", pX + 30, pY + 322);
        g2.drawString("8", pX + 90, pY + 322);
        g2.drawString("9", pX + 150, pY + 322);

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

        // b) Teclado numérico (Ahora con números pintados)
        int btnInicioX = ctrlX + 13;
        int btnInicioY = ctrlY + 65;
        int numeroBoton = 1; // Empezamos a contar desde el botón 1

        for (int fila = 0; fila < 3; fila++) {
            for (int col = 0; col < 3; col++) {
                int bx = btnInicioX + (col * 20);
                int by = btnInicioY + (fila * 20);

                // 1. Dibujamos el fondo gris del botón
                g2.setColor(new Color(200, 200, 200));
                g2.fillRoundRect(bx, by, 14, 14, 4, 4);

                // 2. Dibujamos el número en color negro
                g2.setColor(Color.BLACK);
                g2.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 10));
                // Ajustamos las coordenadas para que el número quede centrado en el cuadradito
                g2.drawString(String.valueOf(numeroBoton), bx + 4, by + 12);

                // Aumentamos el contador para el siguiente botón (2, 3, 4...)
                numeroBoton++;
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


        //DISPLAYS
        this.displayCoca = new VistaBebida(new CocaCola(0));
        this.displaySprite = new VistaBebida(new Sprite(0));
        this.displayFanta = new VistaBebida(new Fanta(0));
        this.displaySnickers = new VistaDulce(new Snickers(0));
        this.displaySuper8 = new VistaDulce(new Super8(0));

        this.displayCoca.paint(g2, pX + 20, pY + 60);   // Espacio 1
        this.displaySprite.paint(g2, pX + 80, pY + 60); // Espacio 2
        this.displayFanta.paint(g2, pX + 140, pY + 60); // Espacio 3

        this.displaySnickers.paint(g2, pX + 20, pY + 160); // Espacio 4
        this.displaySuper8.paint(g2, pX + 80, pY + 160);   // Espacio 5
    }
}
