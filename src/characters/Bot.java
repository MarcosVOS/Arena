package characters;

import java.util.Random;

public class Bot extends Player {
        
    public Bot(int sizeWidth, int sizeHeight){
        super(generatesXAxis(sizeWidth), generatesYAxis(sizeHeight));
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
