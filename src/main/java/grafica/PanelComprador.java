package grafica;
import logica.*;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelComprador extends JPanel {
    private Expendedor exp;

    private int numeroDigitado = -1;

    public PanelComprador(Expendedor exp) {
        this.exp = exp;
    }


    public void evaluarClick(int x, int y) {
        int btnInicioX = 393;
        int btnInicioY = 205;

        for (int fila = 0; fila < 3; fila++) {
            for (int col = 0; col < 3; col++) {
                int bx = btnInicioX + (col * 20);
                int by = btnInicioY + (fila * 20);

                if (x >= bx && x <= bx + 14 && y >= by && y <= by + 14) {
                    numeroDigitado = fila * 3 + col + 1;
                    return;
                }
            }
        }
    }
}
