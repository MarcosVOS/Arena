package characters;

import points.GamePoints;

public abstract class MasterBall {

    private int axisX;
    private int axisY;
    private int circleSize = 30;
    private int speed = 6;

    public MasterBall(int eixoX, int eixoY){
        this.axisX = eixoX;
        this.axisY = eixoY;
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
        if(eixoX < this.axisX && this.axisX > 4){
            this.axisX = eixoX;
        }else if(eixoX < this.axisX && eixoX <= 4 ){
            this.axisX = 0;
        }else if(eixoX > this.axisX && this.axisX < 1246){
            this.axisX = eixoX;
        }else if(eixoX > this.axisX && this.axisX >= 1246){
            this.axisX = 1249;
        }
        return;
    }
    public void setAxisY(int eixoY){

        if(eixoY < this.axisY && this.axisY > 4){
            this.axisY = eixoY;
        }else if(eixoY < this.axisY && eixoY <= 4 ){
            this.axisY = 0;
        }else if(eixoY > this.axisY && eixoY < 652){
            this.axisY = eixoY;
        }
        return;
    }

    public void setEixoXeEixoY(int eixoX, int eixoY){
        this.axisX = eixoX;
        this.axisY = eixoY;
        return;
    }

    public void setCircleSize(int circleSize){
        this.circleSize = circleSize;
    }    

    public void increasesTheSize(int increment){
        this.circleSize += increment;
    }

    private boolean checkCollisionsWithPoint(int pointInAxisX, int pointInAxisY){
        double distance = Math.sqrt(Math.pow(this.axisX - pointInAxisX, 2) + Math.pow(this.axisY - pointInAxisY, 2));
        double radiusSum = (this.circleSize / 2.0) + (10 / 2.0);
        return distance <= radiusSum;
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
                checkCollisionsWithPoint(bots[i].getAxisX(), bots[i].getAxisY()) && 
                circleSize > bots[i].getCircleSize()
            ) {
                increasesTheSize(bots[i].getCircleSize());
                return removeBot(bots, i);
            }
        }
        return bots;
    }
}
