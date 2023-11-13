package alquilerAutos.interfaz;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class GraficarDisponibilidad extends JFrame {
    private static final int ROWS = 13;
    private static final int COLS = 7;

    public GraficarDisponibilidad(HashMap<String, Integer> datos) {
        setTitle("Grid Panel Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Configurar el layout del contenido
        setLayout(new BorderLayout());

        // Crear un panel cuadriculado personalizado
        PanelDisponibilidad gridPanel = new PanelDisponibilidad(ROWS, COLS, datos);

        // Agregar el panel al centro de la ventana
        add(gridPanel, BorderLayout.CENTER);

        // Ajustar el tamaño de la ventana
        setSize(800, 600);

        // Centrar la ventana en la pantalla
        setLocationRelativeTo(null);
    }
}

