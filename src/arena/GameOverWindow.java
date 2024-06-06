package arena;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class GameOverWindow extends JPanel{
    
    public GameOverWindow(JFrame window, int screenWidth, int screenHeight){
        
         JLabel gameOverMessage = new JLabel("Você perdeu");
         gameOverMessage.setForeground(Color.red);
         gameOverMessage.setFont(new Font("Arial", Font.BOLD, 40)); 
 
         Dimension gameOverSize = gameOverMessage.getPreferredSize();
 
         int xGameOver = (screenWidth - gameOverSize.width) / 2;
         int yGameOver = (screenHeight - gameOverSize.height) / 2;
 
         gameOverMessage.setBounds(xGameOver, yGameOver, gameOverSize.width, gameOverSize.height);
 
         JLabel sorryMessage = new JLabel("Sorry");
         sorryMessage.setForeground(Color.red);
         sorryMessage.setFont(new Font("Arial", Font.BOLD, 20)); 
 
         Dimension sorrySize = sorryMessage.getPreferredSize();
 
         int xSorry = (screenWidth - sorrySize.width) / 2;
         int ySorry = yGameOver + gameOverSize.height + 10; 
 
         sorryMessage.setBounds(xSorry, ySorry, sorrySize.width, sorrySize.height);
 
         setLayout(null);
         add(gameOverMessage);
         add(sorryMessage);
 
         window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         window.setSize(screenWidth, screenHeight);
         window.setContentPane(this);
         window.setVisible(true);
    }
}
