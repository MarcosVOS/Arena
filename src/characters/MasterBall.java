package characters;

public abstract class MasterBall {

    private int eixoX;
    private int eixoY;
    private int circleSize = 10;
    private int speed = 3;

    public MasterBall(int eixoX, int eixoY){
        this.eixoX = eixoX;
        this.eixoY = eixoY;
    }

    public int getEixoX(){
        return this.eixoX;
    }

    public int getEixoY(){
        return this.eixoY;
    }

    public int getCircleSize(){
        return this.circleSize;
    }

    public int getSpeed(){
        return this.speed;
    }

    public void setEixoX(int eixoX){
        this.eixoX = eixoX;
        return;
    }
    public void setEixoY(int eixoY){
        this.eixoY = eixoY;
        return;
    }

    public void setEixoXeEixoY(int eixoX, int eixoY){
        this.eixoX = eixoX;
        this.eixoY = eixoY;
        return;
    }

    public void setCircleSize(int circleSize){
        this.circleSize = circleSize;
    }

    
    
}
