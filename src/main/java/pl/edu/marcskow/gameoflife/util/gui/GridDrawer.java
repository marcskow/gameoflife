package pl.edu.marcskow.gameoflife.util.gui;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GridDrawer {

    public static void drawGrid( GraphicsContext graphicsContext, double width, double height) {
        graphicsContext.save();
        graphicsContext.clearRect(0, 0, width, height);

        graphicsContext.setStroke(Color.BLACK);
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.setLineWidth(1);
        graphicsContext.beginPath();
        for(int i = 0; i < (width / 30) - 1; i++){
            for(int j = 0; j < (height / 30) - 2; j++) {
                graphicsContext.strokeRect(30 * i + 5, j * 30 + 30, 30, 30);
            }
        }
    }

    public static void drawRectangle(GraphicsContext graphicsContext, int x, int y) {
        graphicsContext.save();
    //    graphicsContext.clearRect(y * 30 + 5, x * 30 + 30, 30, 30);

        graphicsContext.setStroke(Color.BLACK);
        graphicsContext.setFill(Color.RED);
        graphicsContext.setLineWidth(1);
        graphicsContext.beginPath();
        graphicsContext.strokeRect(30 * y + 5, x * 30 + 30, 30, 30);
        graphicsContext.fillRect(30 * y + 6, x * 30 + 31, 28, 28);
    }

}
