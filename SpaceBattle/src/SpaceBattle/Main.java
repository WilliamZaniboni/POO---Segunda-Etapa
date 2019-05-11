package SpaceBattle;

import Controller.EnvironmentController;
import Controller.MenuController;
import Model.Player;
import Model.UpdateClass;
import View.Environment;
import View.Menu;
import View.Menu_usuario;


public class Main {

  public static void main(String[] args) {
      java.awt.EventQueue.invokeLater(new Runnable(){
      public void run() {
          
          Menu menu = new Menu();
          Menu_usuario menu_usuario = new Menu_usuario();
          
          MenuController menu_controller = new MenuController();
          
          menu_controller.addMenu(menu);
          menu.addController(menu_controller);
          
          menu_controller.addMenu_usuario(menu_usuario);
          menu_usuario.addController(menu_controller);
          
      }
    });
  }
}

