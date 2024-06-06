package characters;

import points.GamePoints;

public class Player extends MasterBall {
    
   public Player(int eixoX, int eixoY){
        super(eixoX,eixoY);
   } 

   public boolean winGame(GamePoints[] points, Bot[] bots){
      return points.length == 0 && bots.length == 0;
   }

}
