package characters;

import java.util.Random;
import arena.Stadium;

public class Bot extends MasterBall {

    public Bot(Stadium stadium){
        super(
            generatesXAxis(stadium.getWidth()), 
            generatesYAxis(stadium.getHeight())
        );
    }

    public static int generatesXAxis(int sizeWidth){
        Random random = new Random();
        return random.nextInt(sizeWidth - 10) + 1;
    }

    public static int generatesYAxis(int sizeHeight){
        Random random = new Random();
        return random.nextInt(sizeHeight - 10) + 1;
    }

    private boolean checkCollisionsWithPlayer(Player player){
        double distance = Math.sqrt(
            Math.pow(super.getAxisX() - player.getAxisX(), 2) + 
            Math.pow(super.getAxisY() - player.getAxisY(), 2)
        );
        double radiusSum = (super.getCircleSize() / 2.0) + 
        (player.getCircleSize() / 2.0);
        
        return distance <= radiusSum;
    }

    public boolean consumePlayer(Player player){
        return checkCollisionsWithPlayer(player) && super.getCircleSize() > player.getCircleSize(); 
    }

    @Override
    public void move(){
        Random random = new Random();
        int direction = random.nextInt(2);
        int deltaX = 0, deltaY = 0;

        if (direction == 0) {
            deltaX = random.nextInt(3) - 1;
        } else {
            deltaY = random.nextInt(3) - 1;
        }
        this.setAxisX(this.getAxisX() + deltaX * this.getSpeed() / 2);
        this.setAxisY(this.getAxisY() + deltaY * this.getSpeed() / 2);
    }
}
