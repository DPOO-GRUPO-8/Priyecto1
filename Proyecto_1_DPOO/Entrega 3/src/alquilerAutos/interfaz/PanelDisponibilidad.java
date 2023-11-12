package alquilerAutos.interfaz;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;

import javax.swing.JPanel;

import alquilerAutos.logica.CalculadoraEstadisticas;

public class PanelDisponibilidad extends JPanel {
    
    private Color rojoOscuro = new Color(112, 51, 41);
    private Color rojoPromedio = new Color(255, 87,87);
    private Color rojoClaro = new Color (249,179,179);
    private CalculadoraEstadisticas calculadora = new CalculadoraEstadisticas();
    private int promedio;
    private HashMap<String, Integer> info;
    
    
    
    
    private static final String[] MESES = {
            "Enero", "Febrero", "Marzo", "Abril", "Mayo",
            "Junio", "Julio", "Agosto", "Septiembre", "Octubre",
            "Noviembre", "Diciembre"
    };

    private final int rows;
    private final int cols;
    private final int cellMargin = 5; // Ajusta el espacio entre los cuadros
    private final int fruitNameMargin = 10; // Ajusta el espacio alrededor de los nombres de las frutas

    public PanelDisponibilidad(int rows, int cols, HashMap<String, Integer> datos) {
        this.rows = rows;
        this.cols = cols;
        promedio = calculadora.promedioColeccion(datos.values());
        this.info = datos;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int cellWidth = (getWidth() - (cols + 1) * cellMargin) / cols;
        int cellHeight = (getHeight() - (rows + 1) * cellMargin - fruitNameMargin) / rows;

        // Dibujar fondo blanco en la fila de las letras
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), cellHeight + fruitNameMargin);

        // Dibujar fondo blanco en la columna de los nombres de las frutas
        g.fillRect(0, 0, cellWidth + cellMargin, getHeight());

        for (int i = 0; i < rows-1; i++) {
            for (int j = 0; j < cols; j++) {
                // Dibujar celda cuadriculada con un color
                
                if (j == 0){
                    g.setColor(Color.WHITE);
                } else{
                	if (i != 0) {
                		int valor = info.get(i + " " + j);
                    	
                		if (valor > promedio) {
                			g.setColor(rojoClaro);
                		} else if (valor == promedio) {
                			g.setColor(rojoPromedio);
                		} else {
                			g.setColor(rojoOscuro);
                		}
                    	
                	}
                	
                }
                

                int x = j * (cellWidth + cellMargin) + cellMargin;
                int y = i * (cellHeight + cellMargin) + cellHeight + fruitNameMargin;
                g.fillRect(x, y, cellWidth, cellHeight);

                // Dibujar nombre de fruta a la izquierda de cada fila
                g.setColor(Color.BLACK);
                g.drawString(MESES[i], cellMargin, y + cellHeight / 2 + g.getFontMetrics().getAscent() / 2);
            }
        }

        for (int j = 1; j < cols; j++) {
            // Dibujar letra sobre cada columna
            g.setColor(Color.BLACK);
            String[] dias = {"L", "M", "MI", "J", "V","S","D"};
            g.drawString(dias[j-1], j * (cellWidth + cellMargin) + cellMargin + cellWidth / 2 - g.getFontMetrics().stringWidth("A") / 2, cellHeight + fruitNameMargin - cellMargin);
        }
    }
}
