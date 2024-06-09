package characters;

import points.GamePoints;

public abstract class MasterBall {

    private int axisX;
    private int axisY;
    private int circleSize = 30;
    private int speed = 6;

    public MasterBall(int axisX, int axisY){
        this.axisX = axisX;
        this.axisY = axisY;
    }

    public int getAxisX(){
        return this.axisX;
    }

    public int getAxisY(){
        return this.axisY;
    }

    public int getCircleSize(){
        return this.circleSize;
    }

    public int getSpeed(){
        return this.speed;
    }

    public void setAxisX(int eixoX){
        int radius = this.circleSize / 2;
        if (eixoX - radius >= 0 && eixoX + radius <= 1280) {
            this.axisX = eixoX;
        } else if (eixoX - radius < 0) {
            this.axisX = radius;
        } else if (eixoX + radius > 1280) {
            this.axisX = 1280 - radius;
        }
    }

    public void setAxisY(int eixoY) {
        int radius = this.circleSize / 2;
        int bottomLimit = 720 - radius;
    
        if (eixoY - radius >= 0 && eixoY + radius <= 720) {
            this.axisY = eixoY;
        } else if (eixoY - radius < 0) {
            this.axisY = radius;
        } else if (eixoY + radius > 720) {
            this.axisY = bottomLimit;
        }
    }
    

    public void setsTheValueOfTheXAndYAxis(int axisX, int axisY){
        setAxisX(axisX);
        setAxisY(axisY);
        return;
    }

    public void setCircleSize(int circleSize){
        this.circleSize = circleSize;
    }    

    public void increasesTheSize(int increment){
        this.circleSize += increment;
    }

    private boolean checkCollisionsWithPoint(int pointInAxisX, int pointInAxisY){
        double distance = Math.sqrt(
            Math.pow(this.axisX - pointInAxisX, 2) + 
            Math.pow(this.axisY - pointInAxisY, 2)
        );
        double radiusSum = (this.circleSize / 2.0) + (10 / 2.0);
        return distance <= radiusSum;
    }

    private boolean checkCollisionsWithBot(Bot bot) {
        double distance = Math.sqrt(Math.pow(
            this.getAxisX() - bot.getAxisX(), 2) + 
            Math.pow(this.getAxisY() - bot.getAxisY(), 2)
        );
        double playerRadius = this.getCircleSize() / 2.0;
        double botRadius = bot.getCircleSize() / 2.0;
        double sumOfRadii = playerRadius + botRadius;
        boolean collision = distance <= sumOfRadii;        
        return collision;
    }
    
    private GamePoints[] removePoint(GamePoints[] points, int indexToRemove){
        GamePoints[] filteredPoints = new GamePoints[points.length - 1];
        int j = 0;
        for (int i = 0; i < points.length; i++) {
            if(i != indexToRemove){
                filteredPoints[j++] = points[i];
            }
        }
        return filteredPoints;
    }

    private Bot[] removeBot(Bot[] bots, int indexToRemove){
        Bot[] filteredPoints = new Bot[bots.length - 1];
        int j = 0;
        for (int i = 0; i < bots.length; i++) {
            if(i != indexToRemove){
                filteredPoints[j++] = bots[i];
            }
        }
        return filteredPoints;
    }

    public GamePoints[] consumePoint(GamePoints[] points){
        for (int i = 0; i < points.length; i++) {
            if (checkCollisionsWithPoint(points[i].getAxisX(), points[i].getAxisY())) {
                increasesTheSize(5);
                return removePoint(points, i);
            }
        }
        return points;
    }

    public Bot[] consumeBots(Bot[] bots){
        for (int i = 0; i < bots.length; i++) {
            if (
                checkCollisionsWithBot(bots[i]) && 
                circleSize > bots[i].getCircleSize()
            ) {
                increasesTheSize(bots[i].getCircleSize());
                return removeBot(bots, i);
            }
        }
        return bots;
    }

    public void move(){
    }
}
