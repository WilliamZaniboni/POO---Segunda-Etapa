package View;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class Canvas extends JPanel{
    
    private ArrayList<Observer> observers;
    protected final static String imgPath = "img/cima.jpg"; //parte superior da tela de fundo do jogo
    protected static BufferedImage Img = null;    

    public Canvas() {
        super();
        observers = new ArrayList<Observer>();
    }
    
    public void registerObserver(Observer ob){
        this.observers.add(ob);
    }
    
    
    private void drawBoard(Graphics2D g){
        if(Img == null){
            try {
                Img = ImageIO.read(new File(imgPath));
            } catch (IOException ex) {
                //tratamento de exceção
            }
        }
        g.drawImage(Img, 0, 0, 1292, 553, null);
    }
    
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //desenhaTabuleiro(g);
        Graphics2D g2 = (Graphics2D)g;
        this.drawBoard(g2);
        // Depois de desenhar o tabuleiro, desenha o resto.
        // temos 2 observers registrados: o Environment
        for(Observer ob : observers){
            ob.update(null, g2);
        }
    }
    
}
