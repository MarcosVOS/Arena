package characters;

import java.awt.event.KeyEvent;

import points.GamePoints;

public class Player extends MasterBall {

   private Boolean theGameStarted;  
   private KeyEvent keyPressed;
    
   public Player(){
        super(100, 100);
        theGameStarted = false; 
   }
   
   public void setTheGameStarted(Boolean theGameStarted) {
      this.theGameStarted = theGameStarted;
      return;
   }

   public Boolean getTheGameStarted() {
      return this.theGameStarted;
   }

   public boolean winGame(GamePoints[] points, Bot[] bots){
      return points.length == 0 && bots.length == 0;
   }

   public void setLastKeyPressed(KeyEvent k){
        this.keyPressed = k; 
        return;
   }

   @Override
   public void move(){
      int pressedKey = keyPressed.getKeyCode();
      switch (pressedKey) {
          case KeyEvent.VK_UP:
              super.setAxisY(super.getAxisY() - super.getSpeed());
              this.theGameStarted = true;
              break;
          case KeyEvent.VK_DOWN:
              super.setAxisY(super.getAxisY() + super.getSpeed());
              this.theGameStarted = true;
              break;
          case KeyEvent.VK_LEFT:
              super.setAxisX(super.getAxisX() - super.getSpeed() );
              this.theGameStarted = true;
              break;
          case KeyEvent.VK_RIGHT:
              super.setAxisX(super.getAxisX() + super.getSpeed() );
              this.theGameStarted = true;
              break;
          default:
              break;
      }
   }

}
