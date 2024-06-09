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
public class WinGameView extends JPanel{
    
    public WinGameView(Stadium game){
        setupWinGameWindow(game);
    }

    private void setupWinGameWindow(Stadium game){
       JLabel congratulationsMessageLabel = createLabel(game);
       JButton retryButton = createButtonRetry(game);
       JButton exitButton = createExitButton(game);

       setLayout(null);
       add(congratulationsMessageLabel);
       add(retryButton);
       add(exitButton);

       game.getWindow().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       game.getWindow().setContentPane(this);
       game.getWindow().setVisible(true);
    }

    private JLabel createLabel(Stadium game){
        JLabel winGameMessage = new JLabel("Você ganhou");
        winGameMessage.setForeground(Color.GREEN);
        winGameMessage.setFont(new Font("Arial", Font.BOLD, 40)); 

        Dimension gameOverSize = winGameMessage.getPreferredSize();

        int xGameOver = (game.getWidth() - gameOverSize.width) / 2;
        int yGameOver = (game.getHeight() - gameOverSize.height) / 2;

        winGameMessage.setBounds(xGameOver, yGameOver, gameOverSize.width, gameOverSize.height); 

        return winGameMessage;
    }

    private JButton createButtonRetry(Stadium game){
        JButton retryButton = new JButton("Recomeçar");
        retryButton.setFont(new Font("Arial", Font.BOLD, 20));
        Dimension retrySize = retryButton.getPreferredSize();
        int xRetry = (game.getWidth() / 2) - retrySize.width - 10;
        int yRetry = game.getHeight() - retrySize.height - 60;
        retryButton.setBounds(xRetry, yRetry, retrySize.width, retrySize.height);
        
        retryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               game.reStartGame();
            }
        });

        return retryButton;
    }

    private JButton createExitButton(Stadium game){
        JButton exitButton = new JButton("Sair");
        exitButton.setFont(new Font("Arial", Font.BOLD, 20));
        Dimension exitSize = exitButton.getPreferredSize();
        int xExit = (game.getWidth() / 2) + 10;
        int yExit = game.getHeight() - exitSize.height - 60;
        exitButton.setBounds(xExit, yExit, exitSize.width, exitSize.height);
 
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        return exitButton;
    }
}
