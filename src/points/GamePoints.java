package points;

import java.awt.Color;
import java.util.Random;
import arena.Stadium;

public class GamePoints {
    private int axisX; 
    private int axisY;
    private Color color; 

    private final Color[] availableColors = new Color[]{
        Color.GREEN,
        Color.ORANGE,
        Color.YELLOW,
        Color.BLUE,
        Color.RED,
    };

    public GamePoints(Stadium stadium){
        this.axisX = generatesXAxis(stadium.getWidth());
        this.axisY = generatesYAxis(stadium.getHeight());
        this.color = availableColors[generateARandomColor()];
    }

    public int generatesXAxis(int sizeWidth){
        Random random = new Random();
        return random.nextInt(sizeWidth - 10) + 1;
    }

    public int generatesYAxis(int sizeHeight){
        Random random = new Random();
        return random.nextInt(sizeHeight - 10) + 1;
    }

    public int generateARandomColor(){
        Random random = new Random();
        int randomColor = random.nextInt(availableColors.length);
        return randomColor;
    }

    public int getAxisX(){
        return this.axisX;
    }

    public int getAxisY(){
        return this.axisY;
    }

    public Color getColor(){
        return this.color;
    }
}
