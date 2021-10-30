
package poorty.controller;

import poorty.model.*; // importa todos los modelos
import poorty.view.*; // importa todas las vistas


public class MainController {
    
    // pantallas
    private Lobby lobbyView;
    
    // modelo
    private Game game;
    
    // subcontroladores
    private LobbyController lobbyController;
    
    // constructor
    // realiza una construccion de todos los subcontroladores y pantallas y su respectiva asignacion
    public MainController(){
        // creacion de las pantallas
        this.lobbyView = new Lobby();
        
        // creacion del juego
        this.game = new Game(this);
        
        // creacion de los controladores de cada ventana
        this.lobbyController = new LobbyController(game, lobbyView, this);
        
        _init_();
    }
    
    // metodos
    public void _init_(){
        lobbyController._init_();
    }
    
    // recibe 2 iventanas colocar una en invisible y la otra en visible
    public void changeWindow(iWindow fromWindow, iWindow toWindow){
        fromWindow.visibility(false); // esconde la anterior
        toWindow.visibility(true); // muestra la siguientes
    }
    
    public void showWindow(iWindow window){
        window.visibility(true);
    }
    
    // getters and setters

    public Game getGame() {
        return game;
    }

    public LobbyController getLobbyController() {
        return lobbyController;
    }
    
    
}
