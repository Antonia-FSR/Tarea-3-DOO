package grafica;
import logica.*;

import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;

/**
 * Clase que representa el panel gráfico donde el usuario interactúa con la máquina.
 * Se encarga de gestionar la billetera, las monedas seleccionadas, los productos comprados
 * y la recolección del vuelto mediante clics del mouse.
 *
 * @author jaocSec
 */
public class PanelComprador extends JPanel {
    private static final int MAQUINA_X = 100;
    private static final int MAQUINA_Y = 50;
    private Expendedor exp;

    private ArrayList<Moneda> billetera;
    private ArrayList<Producto> compras;
    private ArrayList<Moneda> vuelto;

    private Moneda monedaEnMano= null;

    public PanelComprador(Expendedor exp) {
        this.exp = exp;
        this.billetera= new ArrayList<>();
        this.compras= new ArrayList<>();
        this.vuelto= new ArrayList<>();

        billetera.add(new Moneda1000());
        billetera.add(new Moneda1000());
        billetera.add(new Moneda500());
        billetera.add(new Moneda100());
    }


    /**
     * Procesa los clicks del usuario para interactuar con la máquina.
     * Identifica si se presionó la billetera, el teclado numérico, el botón de compra o las zonas de retiro de productos y vuelto.
     * @param x Coordenada horizontal del clic.
     * @param y Coordenada vertical del clic.
     */
    public void evaluarClick(int x, int y) {
        //Hitbox billetera
        if (y >= 100 && y <= 150) {
            int posX = 570;

            for (int i = 0; i < billetera.size(); i++) {
                if (x >= posX && x <= posX + 50) {
                    if (monedaEnMano == null) {
                        monedaEnMano = billetera.remove(i);
                        System.out.println("Agarraste una moneda de: " + monedaEnMano.getValor());
                        repaint();
                    } else {
                        System.out.println("Ya tienes una moneda en la mano.");
                    }
                    return;
                }

                posX += 60;
            }
        }

        //Hitbox botón PUSH
        int pushX = 100 + 280 + 10;
        int pushY = 50 + 60 + 30 + 65 + 75;

        if (x >= pushX && x <= pushX + 38 && y >= pushY && y <= pushY + 28) {

            if (monedaEnMano != null && exp.getNumeroDigitado() != -1) {
                System.out.println("Procesando PUSH...");
                try {

                    exp.comprarProducto(monedaEnMano, exp.getNumeroDigitado());
                    System.out.println("¡Compra exitosa procesada por la máquina!");


                } catch (Exception e) {
                    System.out.println("La máquina rechazó la compra: " + e.getMessage());
                }

                monedaEnMano = null;
                exp.setNumeroDigitado(-1);

                repaint();

            } else {
                System.out.println("Falta plata en la mano o te faltó digitar el número.");
            }
            return;
        }


        //Hitbox vuelto
        int ctrlX = MAQUINA_X + 280;
        int ctrlY = MAQUINA_Y + 60 + 30;
        int vueltoX = ctrlX + 10;
        int vueltoY = ctrlY + 220 + 25;
        if (x >= vueltoX && x <= vueltoX + 60 && y >= vueltoY && y <= vueltoY + 45) {
            Moneda v = exp.getVuelto();
            if (v != null) {
                vuelto.add(v); // Lo guardamos en nuestros bolsillos
                System.out.println("Recogiste vuelto de: " + v.getValor());
                repaint();
            } else {
                System.out.println("No hay más vuelto que recoger.");
            }
            return;
        }

        //Hitbox bandeja de producto
        int pX = 100 + 50;
        int pY = 50 + 60;
        int pH = 350;
        if (x >= pX && x <= pX + 200 && y >= pY + pH + 30 && y <= pY + pH + 80) {
            Producto p = exp.getProducto();
            if (p != null) {
                compras.add(p); // Lo guardamos en el inventario
                System.out.println("¡Retiraste tu producto!");
                repaint();
            } else {
                System.out.println("La bandeja está vacía.");
            }
            return;
        }

        //Hitbox teclado numerico

        int btnInicioX = ctrlX + 13;
        int btnInicioY = ctrlY + 65;
        for (int fila = 0; fila < 3; fila++) {
            for (int col = 0; col < 3; col++) {
                int bx = btnInicioX + (col * 20);
                int by = btnInicioY + (fila * 20);

                if (x >= bx && x <= bx + 14 && y >= by && y <= by + 14) {
                    exp.setNumeroDigitado(fila * 3 + col + 1);
                    System.out.println("Presionaste: " + exp.getNumeroDigitado());
                    exp.setNumeroDigitado(exp.getNumeroDigitado());
                    repaint();
                    return;
                }
            }
        }
    }

    /**
     * Dibuja toda la interfaz del comprador, incluyendo su inventario.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        //fondo
        g2.setColor(new Color(230, 230, 230));
        g2.fillRect(550, 10, 425, 640);

        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Arial", Font.BOLD, 16));
        g2.drawString("INVENTARIO", 570, 40);

        //billetera
        g2.setFont(new Font("Arial", Font.BOLD, 14));
        g2.drawString("Mi Billetera:", 570, 80);

        int posX = 570;
        for (Moneda m : billetera) {
            VistaMoneda vm = new VistaMoneda(m);
            vm.paint(g2, posX, 100);
            posX += 60; // Separamos las monedas para que no se superpongan
        }

        //moneda en mano
        if (monedaEnMano != null) {
            g2.drawString("En la mano:", 570, 180);
            VistaMoneda vm = new VistaMoneda(monedaEnMano);
            vm.paint(g2, 570, 200);
        }

        if(exp.getNumeroDigitado()!=-1){
            g2.drawString("Código: "+exp.getNumeroDigitado(), 570, 170);
        }
        //vuelto
        g2.setColor(Color.BLACK);
        g2.drawString("Mi Vuelto:", 570, 280);
        int vPosX = 570;
        int vPosY = 300;
        for (int i = 0; i < vuelto.size(); i++) {
            VistaMoneda vm = new VistaMoneda(vuelto.get(i));
            vm.paint(g2, vPosX, vPosY);
            vPosX += 60;
            // Si hay muchas monedas, hacemos una segunda fila
            if (vPosX > 800) {
                vPosX = 570;
                vPosY += 60;
            }
        }

        //compras
        g2.setColor(Color.BLACK);
        g2.drawString("Mis Compras:", 570, 420);
        int cPosX = 570;
        int cPosY = 440;
        for (Producto p : compras) {
            // Evaluamos qué tipo de producto es para usar la vista correcta
            if (p instanceof Bebida) {
                VistaBebida vb = new VistaBebida((Bebida) p);
                vb.paint(g2, cPosX, cPosY);
            } else if (p instanceof Dulce) {
                VistaDulce vd = new VistaDulce((Dulce) p);
                vd.paint(g2, cPosX, cPosY);
            }
            cPosX += 60;
        }
    }
}

