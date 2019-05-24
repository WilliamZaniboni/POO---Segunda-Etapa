package View;

import Controller.MenuController;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Informacoes extends javax.swing.JFrame {

    private JButton retornar;
    private JLabel integrante_1;
    private JLabel integrante_2;
    
    public Informacoes() {
        
        initComponents();
        this.setLocationRelativeTo(null);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/menu_sobre_gif.gif")));
        this.setVisible(false);
        
        retornar = new JButton();
        integrante_1 = new JLabel();
        integrante_2 = new JLabel();
        
        
            Font fonte;
        try {
            fonte = Font.createFont(Font.TRUETYPE_FONT, new File("img/8-bit pusab.ttf")).deriveFont(Font.PLAIN, 13);
            retornar.setFont(fonte);
        } catch (FontFormatException ex) {
            Logger.getLogger(Informacoes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Informacoes.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        //Adicionando o botão retornar ao menu anterior
        retornar.setText("Retornar");
        jLabel1.add(retornar);
        retornar.setBounds(540, 600, 200, 60);
        retornar.setToolTipText("Retornar ao menu anterior" ); //janela de texto que aparece quando passa o mouse sobre o botão
        
        //Adicionando o texto com nome dos integrantes
        Font fonte2;
        try {
            fonte2 = Font.createFont(Font.TRUETYPE_FONT, new File("img/8-bit pusab.ttf")).deriveFont(Font.PLAIN, 16);
            integrante_1.setFont(fonte2);
            integrante_2.setFont(fonte2);
        } catch (FontFormatException ex) {
            Logger.getLogger(Informacoes.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Informacoes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        integrante_1.setText("Joao Matheus Siqueira Souza (10309100)");
        integrante_1.setForeground(Color.white);
        jLabel1.add(integrante_1);
        integrante_1.setBounds(345, 250, 1000, 60);
        
        integrante_2.setText("William Zaniboni Silva (10309159)");
        integrante_2.setForeground(Color.white);
        jLabel1.add(integrante_2);
        integrante_2.setBounds(405, 310, 1000, 60);
       
      
    }
    
    public void addController(MenuController menu_controller){
        
        //adicionando o listener do botão retornar
        retornar.addActionListener(menu_controller);
        
    }

    public JButton getRetornar() {
        return retornar;
    }
    
    
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SCC0204 - Battle Space");
        setMinimumSize(new java.awt.Dimension(1280, 720));
        setResizable(false);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/menu.jpeg"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
