package arena;

import javax.swing.JPanel;

import utility.ResourceLoader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GameOverWindow extends JPanel{

    private String[] motivationalPhrases = {
        "O sucesso é como um raio, raramente atinge o mesmo lugar duas vezes. Então, relaxe!",
        "Lembre-se: sempre há alguém pior que você. Pense nisso e sorria.",
        "Se o plano A não funcionar, o alfabeto ainda tem mais 25 letras.",
        "Você é único... assim como todo mundo.",
        "Não desista dos seus sonhos. Continue dormindo.",
        "Lute como nunca... e perca como sempre.",
        "Acredite no seu potencial. Mas não espere muito.",
        "Sempre parece impossível... até você tentar e confirmar que realmente é.",
        "Para cada vencedor, há dezenas de perdedores. Não se sinta sozinho.",
        "O importante não é vencer, mas sim saber que não há chance alguma de ganhar.",
        "Se você está com dúvidas entre desistir ou continuar, lembre-se: ninguém desiste de um fracasso certo.",
        "Você está quase lá! Quase desistindo, mas quase lá.",
        "Se você cair, ria! Pelo menos você não está chorando... ainda.",
        "Sorria! Amanhã vai ser pior... mas com um sorriso no rosto.",
        "Não deixe para amanhã o que você pode fazer depois de amanhã.",
        "Trabalhe duro em silêncio. E deixe o fracasso fazer todo o barulho.",
        "Se você está cansado, descanse. Amanhã você estará ainda mais cansado.",
        "Se no início você não conseguir, o paraquedismo definitivamente não é para você.",
        "A única coisa que impede seus sonhos de se tornarem realidade é a falta de sono.",
        "Lembre-se: os procrastinadores governarão o mundo... amanhã.",
        "Errar é humano. Culpar outra pessoa é estratégia.",
        "A única coisa que garante o seu sucesso é a desistência de todos os outros.",
        "Se você acha que a grama do vizinho é mais verde, provavelmente é porque você não regou a sua."
    };
    
    public GameOverWindow(JFrame window, int screenWidth, int screenHeight, Stadium game){
         ResourceLoader resourceLoader = new ResourceLoader();
        
         JLabel gameOverMessage = new JLabel("Você perdeu");
         gameOverMessage.setForeground(Color.red);
         gameOverMessage.setFont(new Font("Arial", Font.BOLD, 40)); 
 
         Dimension gameOverSize = gameOverMessage.getPreferredSize();
 
         int xGameOver = (screenWidth - gameOverSize.width) / 2;
         int yGameOver = (screenHeight - gameOverSize.height) / 2;
 
         gameOverMessage.setBounds(xGameOver, yGameOver, gameOverSize.width, gameOverSize.height);
 
         Random random = new Random();
         JLabel sorryMessage = new JLabel(motivationalPhrases[random.nextInt(motivationalPhrases.length)]);
         sorryMessage.setForeground(Color.red);
         sorryMessage.setFont(new Font("Arial", Font.BOLD, 20)); 
 
         Dimension sorrySize = sorryMessage.getPreferredSize();
 
         int xSorry = (screenWidth - sorrySize.width) / 2;
         int ySorry = yGameOver + gameOverSize.height + 10; 
 
        sorryMessage.setBounds(xSorry, ySorry, sorrySize.width, sorrySize.height);

        JLabel loserImage = new JLabel(resourceLoader.getLoser());
        Dimension loserSize = new Dimension(resourceLoader.getLoser().getIconWidth(), resourceLoader.getLoser().getIconHeight());
        int xLoser = (screenWidth - loserSize.width) / 2;
        int yLoser = 50;
        loserImage.setBounds(xLoser, yLoser, loserSize.width, loserSize.height);
 

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
        add(sorryMessage);
        add(loserImage); 
        add(retryButton);
        add(exitButton);
 
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(screenWidth, screenHeight);
        window.setContentPane(this);
        window.setVisible(true);
    }
}
