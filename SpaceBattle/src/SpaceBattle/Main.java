package SpaceBattle;

import Controller.MenuController;
import View.Informacoes;
import View.Menu;
import View.Menu_usuario;


public class Main {

  public static void main(String[] args) {
      java.awt.EventQueue.invokeLater(new Runnable(){
      public void run() {
          
          Menu menu = new Menu();
          Menu_usuario menu_usuario = new Menu_usuario();
          Informacoes informacoes = new Informacoes();
          MenuController menu_controller = new MenuController();
          
          
          
          menu_controller.addMenu(menu);
          menu.addController(menu_controller);
          
          menu_controller.addMenu_usuario(menu_usuario);
          menu_usuario.addController(menu_controller);
          
          menu_controller.addInformacoes(informacoes);
          informacoes.addController(menu_controller);
          
      }
    });
  }
}

