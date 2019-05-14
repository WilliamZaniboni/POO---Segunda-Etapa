/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.EnvironmentController;
import Controller.MenuController;
import Model.UpdateClass;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import static java.awt.font.TextAttribute.FONT;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author willi
 */
public class Menu extends javax.swing.JFrame {
    
    private UpdateClass updater;
    private JButton play;
    private JButton continue_thegame;
    
    public Menu(){
        
        initComponents();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/menu_gif.gif")));
        
        play = new JButton();
        continue_thegame = new JButton();
        
        try {
            Font fonte;
            try {
                fonte = Font.createFont(Font.TRUETYPE_FONT, new File("img/8-bit pusab.ttf")).deriveFont(Font.PLAIN, 15);
                play.setFont(fonte);
                continue_thegame.setFont(fonte);
            } catch (FontFormatException ex) {
                Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
            }
           
            
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
         
        //Adicionando o botão para iniciar o jogo
        play.setText("Novo Jogo");
        jLabel1.add(play);
        play.setBounds(540, 300, 200, 60);
        play.setToolTipText("Iniciar" ); //janela de texto que aparece quando passa o mouse sobre o botão
        
        //Adicionando o botão para carregar o jogo a partir de um arquivo
        continue_thegame.setText("Continuar");
        jLabel1.add(continue_thegame);
        continue_thegame.setBounds(540, 380, 200, 60);
        continue_thegame.setToolTipText("Utilizar um arquivo de save" ); //janela de texto que aparece quando passa o mouse sobre o botão

        
    }

    
    public void addController(MenuController menu_controller){
  
        //adicionando o listener dos botões
        play.addActionListener(menu_controller);
        continue_thegame.addActionListener(menu_controller);      
        
    }

    public JButton getPlay() {
        return play;
    }

    public JButton getContinue_thegame() {
        return continue_thegame;
    }
    
    
    
    
    
    
    
    
    
      
       
 
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SCC0204 - Battle Space");
        setMinimumSize(new java.awt.Dimension(1280, 720));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/menu.jpeg"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1280, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
