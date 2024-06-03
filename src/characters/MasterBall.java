package characters;

public abstract class MasterBall {

    private int axisX;
    private int axisY;
    private int circleSize = 10;
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
}
