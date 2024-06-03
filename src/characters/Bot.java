package characters;

import java.util.Random;

import points.GamePoints;

public class Bot extends Player {
    private GamePoints[] contextOfPoints;
    private int playerPositionOnTheXAxis;
    private int playerPositionOnTheYAxis;

    public Bot(int sizeWidth, int sizeHeight, GamePoints[] points, int playerAxisX, int playerAxisY){
        super(generatesXAxis(sizeWidth), generatesYAxis(sizeHeight));
        contextOfPoints = points;
        this.playerPositionOnTheXAxis = playerAxisX;
        this.playerPositionOnTheYAxis = playerAxisY;
    }

    public static int generatesXAxis(int sizeWidth){
        Random random = new Random();
        return random.nextInt(sizeWidth - 10) + 1;
    }

    public static int generatesYAxis(int sizeHeight){
        Random random = new Random();
        return random.nextInt(sizeHeight - 10) + 1;
    }
}
