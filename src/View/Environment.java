package View;

import Controller.EnvironmentController;
import Controller.FightController;
import Model.Constants;
import Model.UpdateClass;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;


public class Environment extends javax.swing.JFrame implements Observer {

    
    private Canvas canvas;
    private Point mouseCoord;
    private UpdateClass updater;
    private FightController fightController;
  
    //Botões para adição de SpaceIcons Rebeldes
    private JButton button_SpaceTower; //SpaceTower button
    private JButton button_Starship;   //Starship button
    private JButton button_Starbomb;   //Starbomb button
    private JButton button_next_cycle; //Next cycle button

    //Contructor =======================================================================================================
    public Environment(UpdateClass updater_parameter, FightController fightController) {
      
        initComponents();
        canvas = new Canvas();
        mouseCoord = new Point();
        canvas.registerObserver(this);
        this.updater = updater_parameter;
        canvas.registerObserver(updater_parameter);
        this.fightController = fightController;
    
        Dimension area = new Dimension(jPCanvas.getWidth(), jPCanvas.getHeight());
        canvas.setPreferredSize(area);//set dimensao do painel de desenho
        jPCanvas.setLayout(new FlowLayout());
        jPCanvas.add(canvas);
    
        Color minhaCor = new Color(40, 40, 40); //pintando o fundo da cor preta (mesma cor da imagem de fundo)
    
        this.getContentPane().setBackground(minhaCor);

        //Adição dos botões de inserção de rebelde =============================================================================================================
    
        //Botão de adição do SpaceIcon Rebelde "SpaceTower" (Botão 1) -----------------------------------------------------------------------------------------
    
            ImageIcon imgButton = new ImageIcon("img/nave.png");
            button_SpaceTower = new JButton(imgButton);
            button_SpaceTower.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(79, 206, 234), 3)); //cor ao redor do botão
            jLabel1.add(button_SpaceTower);
            button_SpaceTower.setBounds(560, 0, 106, 94);  //posição
            button_SpaceTower.setToolTipText("SpaceTower"); //janela de texto que aparece quando passa o mouse sobre o botão
    
        //Botão de adição do SpaceIcon Rebelde "Starship" (Botão 2) ------------------------------------------------------------------------------------------
  
            ImageIcon imgButton2 = new ImageIcon("img/nave2.png");
            button_Starship = new JButton(imgButton2);
            button_Starship.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(79, 206, 234), 3)); //cor amarela ao redor do botão
            jLabel1.add(button_Starship);
            button_Starship.setBounds(700, 0, 106, 94);
            button_Starship.setToolTipText("Starship"); //janela de texto que aparece quando passa o mouse sobre o botão
    
        //Botão de adição do SpaceIcon Rebelde "Starbomb" (Botão 3) ------------------------------------------------------------------------------------------
     
            ImageIcon imgButton3 = new ImageIcon("img/nave3.png");
            button_Starbomb = new JButton(imgButton3);
            button_Starbomb.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(79, 206, 234), 3)); //cor amarela ao redor do botão
            jLabel1.add(button_Starbomb);
            button_Starbomb.setBounds(840, 0, 106, 94);
            button_Starbomb.setToolTipText("StarBomb" ); //janela de texto que aparece quando passa o mouse sobre o botão

        //Botão para avanço do ciclo
        
            ImageIcon imgButton_next = new ImageIcon("img/avancar.png");
            button_next_cycle = new JButton(imgButton_next );
            button_next_cycle.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(79, 206, 234), 3)); //cor amarela ao redor do botão
            jLabel1.add(button_next_cycle);
            button_next_cycle.setBounds(120, 0, 94, 94);
            button_next_cycle.setToolTipText("Avançar ciclo" ); //janela de texto que aparece quando passa o mouse sobre o botão


        //Adicão das labels que apresentam as coordenadas do mouse sobre o panel ===========================================
    
            jLabel1.add(jLabel2);
            jLabel2.setBounds(4, 130, 74, 16);
    
            jLabel1.add(coordenadaLabel);
            coordenadaLabel.setBounds(78, 130, 540, 16);
    
            jLabel1.add(jLabel3);
            jLabel3.setBounds(4, 146, 91, 16);
    
            jLabel1.add(clickLabel);
            clickLabel.setBounds(96, 146, 540, 16);
   
        //Adição das Labels de escolha de Rebelde (Informações adicionais sobre o SpaceIcon Rebelde) =======================
    
            jLabel1.add(jLabel4); //atributos da nave 3
            jLabel4.setBounds(300, 0, 212, 110);
            jLabel4.setVisible(false);
    
            jLabel1.add(jLabel5); //atributos da nave 1
            jLabel5.setBounds(300, 0, 212, 110);
            jLabel5.setVisible(false);
    
            jLabel1.add(jLabel6); //atributos da nave 2
            jLabel6.setBounds(300, 0, 212, 110);
            jLabel6.setVisible(false);
            
         //Adição das Labels com a quantidade de moeda
          
            jLabel1.add(jLabel7); //desenho da moeda
            jLabel7.setBounds(710, 113, 40, 40);
            
            jLabel1.add(jLabel8); //quantidade de moeda
            jLabel8.setBounds(760, 113, 100, 40);
            jLabel8.setText(""+Constants.GOLD_INIT);
            
         //Label para informar o ciclo
         
            jLabel1.add(jLabel9); //label para informar o ciclo
            jLabel9.setBounds(35, 5, 80, 40);
            jLabel9.setText("0º");
            
            jLabel1.add(jLabel10); //label para informar o ciclo
            jLabel10.setBounds(30, 40, 100, 60);
            jLabel10.setText("CICLO");
            
          //Label para informar o tempo restante
          
            jLabel1.add(jLabel11); //label para informar o ciclo
            jLabel11.setBounds(1133, 44, 140, 40);
           
            
    }


    //Conectors

    public void addController(EnvironmentController environment_controller){

        //Listeners
        jPCanvas.addMouseListener(environment_controller);
        jPCanvas.addMouseMotionListener(environment_controller);
        
        //adicionando o listener dos botões
        button_SpaceTower.addActionListener(environment_controller);
        button_Starship.addActionListener(environment_controller);
        button_Starbomb.addActionListener(environment_controller);
        button_next_cycle.addActionListener(environment_controller);
  }
 

    //Getters dos botões ===============================================================================================

    public JButton getbutton_spacetower(){
        return button_SpaceTower;
    }
   
    public JButton getbutton_starship(){
        return button_Starship;
    }

    public JButton getbutton_starbomb(){
        return button_Starbomb;
    }

    public JButton getbutton_next_cycle() {
        //System.out.println("Entrou na verific");
        return button_next_cycle;
    }
    
  
    //Getters e Setters das labels de atributos dos SpaceIcons Rebeldes ================================================

    public JLabel getjLabel4() {
        return jLabel4;
    }
    
    public void setjLabel4(JLabel jLabel4) {
        this.jLabel4 = jLabel4;
    }
    
    public JLabel getjLabel5() {
        return jLabel5;
    }

    public void setjLabel5(JLabel jLabel5) {
        this.jLabel5 = jLabel5;
    }
    
    public JLabel getjLabel6() {
        return jLabel6;
    }

    public void setjLabel6(JLabel jLabel6) {
        this.jLabel6 = jLabel6;
    }
    
    public JLabel getjLabel8() {
        return jLabel8;
    }

    public void setjLabel8(JLabel jLabel8) {
        this.jLabel8 = jLabel8;
    }

    public JLabel getjLabel9() {
        return jLabel9;
    }
    
    public JLabel getjLabel11() {
        return jLabel11;
    }
    
    //Getters e Setters de ações de Mouse ==============================================================================
    
    public Point getMouseCoord() {
        return mouseCoord;
    }

    public void setMouseCoord(Point mouseCoord) {
        this.mouseCoord = mouseCoord;
    }
  
    public JLabel getClickLabel() {
        return clickLabel;
    }

    public JLabel getCoordenadaLabel() {
        return coordenadaLabel;
    }

    public void setClickLabel(JLabel clickLabel) {
        this.clickLabel = clickLabel;
    }

    public void setCoordenadaLabel(JLabel coordenadaLabel) {
        this.coordenadaLabel = coordenadaLabel;
    }
    
    //Verificação se o botão foi pressionado e ações subsequentes ======================================================

    boolean k = false;

    boolean l = false;

    boolean j = false;

    public void setClickButton1(boolean i){
      k=i;   
    }

    public void setClickButton2(boolean i){
        l=i;
    }

    public void setClickButton3(boolean i){
        j=i;
    }
    
    public boolean getClickButton1(){
        return k;
    }

    public boolean getClickButton2(){
        return l;
    }

    public boolean getClickButton3(){
        return j;
    }

    public void drawSpaceIcon(Graphics2D g, FightController fightController){

    }

    public void drawMouseQuadrante(Graphics2D g, FightController fightController) { //desenha um quadrado ao redor dos quadrantesc

        int width = canvas.getWidth()/8;
        int height = canvas.getHeight()/8;
        
        int pmousex = mouseCoord.x - 327;
        int pmousey = mouseCoord.y - 94; 
        
        int qx = pmousex/54;
        int qy = pmousey/54;
        
       
        int squareWidth = 54;
        int squareHeight = 54;
       
        if((mouseCoord.x>=327) && (mouseCoord.x<=1135) && (mouseCoord.y<=517) && (mouseCoord.y>=88)) {

            switch (fightController.getMatrixpreenchida(qx, qy)) {
                case 0: //Quadrante vazio 
 
                    g.setColor(Color.yellow);
                    g.setStroke(new BasicStroke(2));
                    g.drawRect(qx * squareWidth + 327, qy * squareHeight + 87, squareWidth, squareHeight);
                    g.setColor(Color.BLACK);
                    break;
            
                case 1: //SpaceTower
                    
                    for(int i=0; i<8; i++){
                        
                        if((qy * squareHeight + 87-54*i)>=87){
                            g.setColor(Color.red);
                            g.setStroke(new BasicStroke(2));
                            g.drawRect(qx * squareWidth+327, qy * squareHeight+87-54*i, squareWidth, squareHeight);
                            g.setColor(Color.BLACK);
                        }
                         
                    }      
                    
                    for(int i=0; i<8; i++){
                        
                        if((qy * squareHeight + 87+54*i)<=517){
                            g.setColor(Color.red);
                            g.setStroke(new BasicStroke(2));
                            g.drawRect(qx * squareWidth+327, qy * squareHeight+87+54*i, squareWidth, squareHeight);
                            g.setColor(Color.BLACK);
                        }
                         
                    }    
                    
                    break;
                    
                case 2: //StarShip
                    
                    g.setColor(Color.red);
                    g.setStroke(new BasicStroke(2));
                    g.drawRect(qx * squareWidth+327, qy * squareHeight+87, squareWidth, squareHeight); 
                    
                    int c = 2;
                    
                    for(int i=2;i>-1;i--){
                        
                         if(qx * squareWidth+327+i*54<=1135){
                       
                            g.setColor(Color.red);
                            g.setStroke(new BasicStroke(2));
                            
                            for(int k=0; k<c;k++){
                              if(qy * squareHeight+87+54*k<=517){  
                                g.drawRect(qx * squareWidth+327+i*54, qy * squareHeight+87+54*k, squareWidth, squareHeight);
                              } 
                           }
                            for(int k=0; k<c;k++){
                              if((qy * squareHeight+87-54*k)>=87){  
                                g.drawRect(qx * squareWidth+327+i*54, qy * squareHeight+87-54*k, squareWidth, squareHeight);
                              }  
                           }
                           
                         }
                          c--;
                            
                    }      
                    
                    break;
                    
                case 3: //StarBomb

                    g.setColor(Color.red);
                    g.setStroke(new BasicStroke(2));
                    g.drawRect(qx * squareWidth+327, qy * squareHeight+87, squareWidth, squareHeight); 
                    
                    int c2 = 3;
                    
                    for(int i=3;i>-1;i--){
                        
                         if(qx * squareWidth+327+i*54<=1135){
                       
                            g.setColor(Color.red);
                            g.setStroke(new BasicStroke(2));
                            
                            for(int k=0; k<c2;k++){
                              if(qy * squareHeight+87+54*k<=517){  
                                g.drawRect(qx * squareWidth+327+i*54, qy * squareHeight+87+54*k, squareWidth, squareHeight);
                              } 
                           }
                            for(int k=0; k<c2;k++){
                              if((qy * squareHeight+87-54*k)>=87){  
                                g.drawRect(qx * squareWidth+327+i*54, qy * squareHeight+87-54*k, squareWidth, squareHeight);
                              }  
                           }
                           
                         }
                          c2--;
                            
                    }      
                    
           
                    break;
                    
                case 4: //Caso seja um inimigo
                        for(int i=0; i<15; i++){
                             if((qx * squareWidth+327-54*i)>=327){
                                g.setColor(Color.red);
                                g.setStroke(new BasicStroke(2));
                                g.drawRect(qx * squareWidth+327-54*i, qy * squareHeight+87, squareWidth, squareHeight);
                                g.setColor(Color.BLACK);
                             }
                         }
             
                default:
                    break;
            }
        
            //adicionando o png ao mouse apos clicar no botão

           if (this.getClickButton1() == true) {

                ImageIcon referencia1 = new ImageIcon("img/nave1icone.png");
                Image nave1 = referencia1.getImage();

                g.drawImage(nave1, mouseCoord.x, mouseCoord.y, this);

            }

            if (this.getClickButton2() == true) {

                ImageIcon referencia2 = new ImageIcon("img/nave2icone.png");
                Image nave2 = referencia2.getImage();

                g.drawImage(nave2, mouseCoord.x, mouseCoord.y, this);

            }

            if (this.getClickButton3() == true) {

                ImageIcon referencia3 = new ImageIcon("img/nave3icone.png");
                Image nave3 = referencia3.getImage();

                g.drawImage(nave3, mouseCoord.x, mouseCoord.y, this);

            }
        }
        
        
        // desenha os SpaceIcons no battlefield aliadas
            for(int i = 0; i < fightController.getRebels_number(); i++){
                
                ImageIcon referencia1 = new ImageIcon("img/nave1icone.png"); //SpaceTower
                Image nave1 = referencia1.getImage();
                
                ImageIcon referencia2 = new ImageIcon("img/nave2icone.png"); //StarShip
                Image nave2 = referencia2.getImage();
                
                ImageIcon referencia3 = new ImageIcon("img/nave3icone.png"); //StarBomb
                Image nave3 = referencia3.getImage();
                
                
                if(fightController.getMatrixpreenchida(fightController.getRebels().get(i).getX(),fightController.getRebels().get(i).getY())==1){
                    g.drawImage(nave1, squareWidth * fightController.getRebels().get(i).getX() + 327,
                        squareHeight * fightController.getRebels().get(i).getY() + 87, null);
                }
                
                else if(fightController.getMatrixpreenchida(fightController.getRebels().get(i).getX(),fightController.getRebels().get(i).getY())==2){
                    g.drawImage(nave2, squareWidth * fightController.getRebels().get(i).getX() + 327,
                        squareHeight * fightController.getRebels().get(i).getY() + 87, null);
                }
                
                else if(fightController.getMatrixpreenchida(fightController.getRebels().get(i).getX(),fightController.getRebels().get(i).getY())==3){
                    g.drawImage(nave3, squareWidth * fightController.getRebels().get(i).getX() + 327,
                        squareHeight * fightController.getRebels().get(i).getY() + 87, null);
                }
                
            }
           
            // desenha os SpaceIcons no battlefield inimigas
            for(int i = 0; i < fightController.getEmpire_number(); i++){
                
                ImageIcon referencia4 = new ImageIcon("img/eticone.png"); //Inimigo
                Image nave4 = referencia4.getImage();
                
                 if(fightController.getMatrixpreenchida(fightController.getEmpire().get(i).getX(),fightController.getEmpire().get(i).getY())==4){
                    g.drawImage(nave4, squareWidth * fightController.getEmpire().get(i).getX() + 327,
                        squareHeight * fightController.getEmpire().get(i).getY() + 87, null);
                }
                 
            }

        
        
    }


    @Override
    public void update(Observable o, Object arg) {
        drawMouseQuadrante((Graphics2D) arg, this.fightController);
    }

    //initComponents() - GERADO PELA IDE ===============================================================================
  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPCanvas = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        coordenadaLabel = new javax.swing.JLabel();
        clickLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SCC0204 - Battle Space");
        setBackground(new java.awt.Color(51, 255, 51));
        setPreferredSize(new java.awt.Dimension(1292, 776));
        setResizable(false);

        jPCanvas.setMaximumSize(new java.awt.Dimension(1280, 720));
        jPCanvas.setMinimumSize(new java.awt.Dimension(400, 400));
        jPCanvas.setPreferredSize(new java.awt.Dimension(1280, 720));

        javax.swing.GroupLayout jPCanvasLayout = new javax.swing.GroupLayout(jPCanvas);
        jPCanvas.setLayout(jPCanvasLayout);
        jPCanvasLayout.setHorizontalGroup(
            jPCanvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPCanvasLayout.setVerticalGroup(
            jPCanvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 558, Short.MAX_VALUE)
        );

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Click do mouse:");

        coordenadaLabel.setBackground(new java.awt.Color(0, 0, 0));
        coordenadaLabel.setForeground(new java.awt.Color(255, 255, 255));
        coordenadaLabel.setText(" ");

        clickLabel.setBackground(new java.awt.Color(0, 0, 0));
        clickLabel.setForeground(new java.awt.Color(255, 255, 255));
        clickLabel.setText(" ");

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Coordenada:");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/atributos_nave3.png"))); // NOI18N

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/baixo.png"))); // NOI18N

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/atributos_nave1.png"))); // NOI18N

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/atributos_nave2.png"))); // NOI18N

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/money.png"))); // NOI18N
        jLabel7.setText("jLabel7");

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));

        jLabel9.setBackground(new java.awt.Color(0, 0, 0));
        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 35)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));

        jLabel10.setBackground(new java.awt.Color(0, 0, 0));
        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPCanvas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1292, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(coordenadaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 546, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clickLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(50, 50, 50)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(103, 103, 103))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(190, 190, 190)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(890, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(437, 437, 437)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(643, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPCanvas, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(coordenadaLabel)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(clickLabel))
                    .addComponent(jLabel2))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(31, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(785, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(64, 64, 64)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(796, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(53, 53, 53)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel clickLabel;
    private javax.swing.JLabel coordenadaLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPCanvas;
    // End of variables declaration//GEN-END:variables
}

