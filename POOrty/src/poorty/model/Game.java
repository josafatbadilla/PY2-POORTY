
// Clase que contiene toda la logica de los juegos y tablero y las pantallas

package poorty.model;

import poorty.controller.MainController;

public class Game {
    private Player player;
    private MainController mainController;
    
    // tablero
    
    // minijuegos
    
    
    public Game(MainController mainController){
        this.mainController = mainController;
        this.player = new Player(this.mainController); // se le pasa el controlador principal
    }
    
    
    
    // getters and setters

    public Player getPlayer() {
        return player;
    }
    
    
}
