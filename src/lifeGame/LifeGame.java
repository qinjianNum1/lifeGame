package lifeGame;

import javax.swing.JFrame;  
public class LifeGame extends JFrame
{  
    LifeGame(){  
        this.setSize(600,600);  
        this.setTitle("LifeGame");  
        this.add(new WorldMap());  
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        this.setLocationRelativeTo(null);  
        this.setResizable(false);  
    }  
      
    public static void main(String[] args)
    {  
        LifeGame game = new LifeGame();  
        game.setVisible(true);  
    }  
  
}
