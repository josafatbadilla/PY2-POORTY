
package poorty.controller;

import java.awt.Image;
import javax.swing.ImageIcon;
import poorty.model.*; // importa todos los modelos
import poorty.view.*; // importa todas las vistas


public class MainController {
    
    // pantallas
    private Lobby lobbyView;
    private Selection charSelectView;
    private RandomTurnWindow randomTurnView;
    private DicesTurnWindow dicesTurnView; // ventana para lanzar dados para el turno
    private BoardWindow boardView; // ventana del tablero
    
    // modelo principal
    private Game game;
    
    // subcontroladores
    private LobbyController lobbyController;
    private SelectionController selectionController;
    private RandomTurnController randomTurnController;
    private DicesTurnController dicesTurnController;
    private BoardController boardController;
    
    // constructor
    // realiza una construccion de todos los subcontroladores y pantallas y su respectiva asignacion
    public MainController(){
        // creacion de las pantallas
        this.lobbyView = new Lobby();
        
        // creacion del juego
        this.game = new Game(this);
        
        // creacion de los controladores de cada ventana
        this.lobbyController = new LobbyController(game, lobbyView, this);
        
        lobbyController._init_();
    }
    
    // metodos
    
    // recibe 2 iventanas colocar una en invisible y la otra en visible
    public void changeWindow(iWindow fromWindow, iWindow toWindow){
        fromWindow.visibility(false); // esconde la anterior
        toWindow.visibility(true); // muestra la siguientes
    }
    
    public void showWindow(iWindow window){
        window.visibility(true);
    }
    
    // metodos para el cambio de ventanas 
    // creacion de la ventana para la seleccion de personajes
    public void characterSelectWindow(){
        this.charSelectView = new Selection();
        this.selectionController = new SelectionController(charSelectView, game, this);
        selectionController._init_();
        changeWindow(this.lobbyView, this.charSelectView);
    }
    
    // creacion y cambio de hacia la ventan que determina el turno
    public void selectTurnWindow(int window){
        if(window == 1){
            // se pasara a la ventana del numero random
            this.randomTurnView = new RandomTurnWindow();
            this.randomTurnController = new RandomTurnController(randomTurnView, game, this);
            this.randomTurnController._init_();
            changeWindow(this.charSelectView, this.randomTurnView);
        }else{
            // se pasa a la ventana de tirar los dados
            this.dicesTurnView = new DicesTurnWindow();
            this.dicesTurnController = new DicesTurnController(dicesTurnView, game, this);
            this.dicesTurnController._init_();
            changeWindow(this.charSelectView, this.dicesTurnView);  
        }
    }
    
    
    // creacion del tablero y pasar de la ventana
    public void startBoardWindow(int fromWindowSelecction){
        
        this.boardView = new BoardWindow();
        this.boardController = new BoardController(boardView, game, this);
        boardController._init_();
        
        if(fromWindowSelecction == 1){
            // se pasa desde la seleccion de turno por numero aleatorio
            changeWindow(this.randomTurnView, this.boardView);
        }else{
            // se pasa desde la seleccion de turno por tirar dados
            changeWindow(this.dicesTurnView, this.boardView);
        }
    }
    
    
    // metodos varios
    
    // metodo para cambiar de tamano un icono dado a los valores dados
    public static ImageIcon resizeIcon(ImageIcon icon, int width, int height){
        Image iconImage = icon.getImage();
        Image resizedIconImage = iconImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedIconImage);
    }
    
    // getters and setters
    public Game getGame() {
        return game;
    }

    public LobbyController getLobbyController() {
        return lobbyController;
    }
    
    public SelectionController getSelectionController(){
        return selectionController;
    }
    
    
    public RandomTurnController getRandomTurnController(){
        return randomTurnController;
    }
    
}
