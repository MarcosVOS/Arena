package arena;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class WinGameWindow extends JPanel{
    
    public WinGameWindow(JFrame window, int screenWidth, int screenHeight, Stadium game){
        
         JLabel gameOverMessage = new JLabel("Você ganhou");
         gameOverMessage.setForeground(Color.GREEN);
         gameOverMessage.setFont(new Font("Arial", Font.BOLD, 40)); 
 
         Dimension gameOverSize = gameOverMessage.getPreferredSize();
 
         int xGameOver = (screenWidth - gameOverSize.width) / 2;
         int yGameOver = (screenHeight - gameOverSize.height) / 2;
 
         gameOverMessage.setBounds(xGameOver, yGameOver, gameOverSize.width, gameOverSize.height);
 
        //  Random random = new Random();
        //  JLabel sorryMessage = new JLabel(motivationalPhrases[random.nextInt(motivationalPhrases.length)]);
        //  sorryMessage.setForeground(Color.GREEN);
        //  sorryMessage.setFont(new Font("Arial", Font.BOLD, 20)); 
 
        //  Dimension sorrySize = sorryMessage.getPreferredSize();
        // int xSorry = (screenWidth - sorrySize.width) / 2;
        // int ySorry = yGameOver + gameOverSize.height + 10; 
        // sorryMessage.setBounds(xSorry, ySorry, sorrySize.width, sorrySize.height);
 
 
 

        JButton retryButton = new JButton("Recomeçar");
        retryButton.setFont(new Font("Arial", Font.BOLD, 20));
        Dimension retrySize = retryButton.getPreferredSize();
        int xRetry = (screenWidth / 2) - retrySize.width - 10;
        int yRetry = screenHeight - retrySize.height - 60;
        retryButton.setBounds(xRetry, yRetry, retrySize.width, retrySize.height);
        
        retryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               game.reStartGame();
            }
        });


        JButton exitButton = new JButton("Sair");
        exitButton.setFont(new Font("Arial", Font.BOLD, 20));
        Dimension exitSize = exitButton.getPreferredSize();
        int xExit = (screenWidth / 2) + 10;
        int yExit = screenHeight - exitSize.height - 60;
        exitButton.setBounds(xExit, yExit, exitSize.width, exitSize.height);

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setLayout(null);
        add(gameOverMessage);
        // add(sorryMessage);
        add(retryButton);
        add(exitButton);
 
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(screenWidth, screenHeight);
        window.setContentPane(this);
        window.setVisible(true);
    }
}
