package grafica;
import javax.swing.JFrame;
public class Ventana extends JFrame {
    public Ventana() {
        // 1. Le damos un título a la ventana
        super("Tarea 3 - Máquina Expendedora");

        // 2. Le decimos al programa que termine completamente al cerrar la ventana
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // 3. Definimos el tamaño inicial (Ancho en X, Alto en Y)
        this.setSize(1000, 700);

        // 4. Hacemos que la ventana aparezca en el centro de la pantalla
        this.setLocationRelativeTo(null);
        this.add(new PanelPrincipal());
        // 5. ¡Hacemos que la ventana sea visible!
        this.setVisible(true);
    }
}
