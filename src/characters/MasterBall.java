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
        this.axisX = eixoX;
        return;
    }
    public void setAxisY(int eixoY){
        this.axisY = eixoY;
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
        double distance = Math.sqrt(Math.pow(this.getAxisX() - pointInAxisX, 2) + Math.pow(this.getAxisY() - pointInAxisY, 2));
        return distance < this.getCircleSize();
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

    public GamePoints[] consumePoint(GamePoints[] points){
        for (int i = 0; i < points.length; i++) {
            if (checkCollisionsWithPoint(points[i].getAxisX(), points[i].getAxisY())) {
                increasesTheSize(5);
                return removePoint(points, i);
            }
        }
        return points;
    }

    public boolean checkCollisionsWithPlayer(){
        return true;
    }
}
