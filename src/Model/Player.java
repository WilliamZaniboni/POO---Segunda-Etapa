package Model;

import View.Menu_usuario;

public class Player {
    
    private int gold;
    private String name;
    private int save_time;
    private Menu_usuario menu_usuario;
    
    //Contrutor==================================================================
    
    public Player(Menu_usuario menu_usuario){
        
        this.gold = Constants.GOLD_INIT; 
        this.name = menu_usuario.getPlayerName();
        
        //compara a String e tranforma em segundos (adicionar try catch caso nao clique em nada
        switch (menu_usuario.getEscolha()) {
            case "1 segundo":
                this.save_time = 1;
                break;
            case "5 segundos":
                this.save_time = 5;
                break;
            case "10 segundos":
                this.save_time = 10;
                break;
            case "30 segundos":
                this.save_time = 30;
                break;
            case "1 minuto":
                this.save_time = 60;
                break;
            default:
                break;
        }
        
    }
    
    //Getters=====================================================================

    public int getGold() {
        return gold;
    }
    
    public String getName() {
        return name;
    }

    public int getSave_time() {
        return save_time;
    }
    
    //Setters======================================================================
   
    public void setNewgold(int g) {
        this.gold = this.gold - g;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSave_time(int save_time) {
        this.save_time = save_time;
    }
    
    
    
    
}
