
package poorty.controller;

import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private CatGameWindow catGameView;
    private SoupWindow soupGameView;
    private OpponentSelectionWindow opponentView;
    private SelectBox selectBoxView;
    private MemoryWindow memoryView;
    private MemoryPathWindow memoryPathView;
    private GuessWhoWindow guessWhoView;
    private CoinsWindow collectCoinsView;
    
    private MarioCardsWindow marioCardsView; 
    
    // modelo principal
    private Game game;
    
    // subcontroladores
    private OpponentSelectionController opponentController;
    private LobbyController lobbyController;
    private SelectionController selectionController;
    private RandomTurnController randomTurnController;
    private DicesTurnController dicesTurnController;
    private BoardController boardController;
    private CatGameController catGameController;
    private SoupController soupGameController;
    private SelectBoxController selectBoxController;
    private MemoryController memoryGameController;
    private MemoryPathController memoryPathController;
    private GuessWhoController guessWhoController;
    private CoinsController coinsController;
    
    private MarioCardsController marioCardsController;
    
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
    
    public void closeWindow(iWindow window){
        window.visibility(false);
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
    public void startBoardWindow(int fromWindowTurn){
        
        this.boardView = new BoardWindow();
        this.boardController = new BoardController( game, boardView , this);
        boardController._init_();
        
        if(fromWindowTurn == 1){
            // se pasa desde la seleccion de turno por numero aleatorio
            changeWindow(this.randomTurnView, this.boardView);
        }else{
            // se pasa desde la seleccion de turno por tirar dados
            changeWindow(this.dicesTurnView, this.boardView);
        }
    }
    
    
    // creacion del juego del gato y pasada de pantalla desde el tablero
    public void startCatMiniGame(int enemyId){
        this.catGameView = new CatGameWindow();
        this.catGameController = new CatGameController(catGameView, game, this, enemyId);
        this.catGameController._init_();
        //changeWindow(this.boardView, this.catGameView);
        changeWindow(this.boardView, this.catGameView); // muestra la pantalla del juego del gato
    }
    
    public void startSoupMiniGame(){
        this.soupGameView = new SoupWindow();
        this.soupGameController = new SoupController(soupGameView, game, this);
        this.soupGameController._init_();
        changeWindow(this.boardView, this.soupGameView); // muestra la pantalla del juego de la sopa de letras
    }
    
    public void startSelectOpponent(int option){
        this.opponentView = new OpponentSelectionWindow();
        this.opponentController = new OpponentSelectionController(opponentView, game, this, option);
        this.opponentController._init_();
    }
    
    public void startMemoryMiniGame(int enemyId){
       this.memoryView = new MemoryWindow();
       this.memoryGameController = new MemoryController(memoryView, game, this, enemyId);
       this.memoryGameController._init_();
       changeWindow(this.boardView, this.memoryView);
    }
    
    public void changeSelectOpponentW(){
        this.opponentView.visibility(true);
        this.opponentController.initBackground();
    }
    
    public void startSelectBox(){
        this.selectBoxView = new SelectBox();
        this.selectBoxController = new SelectBoxController(selectBoxView, game, this);
        this.selectBoxController._init_();
    }
    
    public void changeSelectBoxW(){
        this.selectBoxView.visibility(true);
        this.selectBoxController.initBackground();
    }
    
    public void startMemoryPath(ImageIcon image){
        this.memoryPathView = new MemoryPathWindow();
        this.memoryPathController = new MemoryPathController(memoryPathView, game, this, image);
        this.memoryPathController._init_();
        changeWindow(this.boardView, this.memoryPathView);
    }
    
    
    public void starMarioCards(int playerID){
        this.marioCardsView = new MarioCardsWindow();
        this.marioCardsController = new MarioCardsController(marioCardsView, game , this, playerID);
        this.marioCardsController._init_();
        changeWindow(this.boardView, this.marioCardsView);
    }
    public void startGuessWhoMiniGame(){
        this.guessWhoView = new GuessWhoWindow();
        this.guessWhoController = new GuessWhoController(this.guessWhoView, game, this);
        this.guessWhoController._init_();
        changeWindow(this.boardView, this.guessWhoView);
    }
    
    public void startCollectCoinsMiniGame(){
        this.collectCoinsView = new CoinsWindow();
        this.coinsController = new CoinsController(this.collectCoinsView, game, this);
        this.coinsController._init_();
        changeWindow(this.boardView, this.collectCoinsView);
    }
    
    
    // se cierra las ventanas de los minijuegos
    public void closeMiniGame(int miniGame){
        switch(miniGame){
            case 4: // se cierra el juego del gato
                changeWindow(this.catGameView, this.boardView);
                
                break;
            case 5: // se cierra la sopa de letras
                changeWindow(this.soupGameView, this.boardView);
                this.boardController.continuarTurno();
                break;
            case 6:// se cierra ice flower o fire flower
                this.opponentView.visibility(false);
                this.boardController.continuarTurno();
                break;
            case 7:
                this.selectBoxView.visibility(false);
                break;
            
            case 8: // se cierra el juego de memory
                changeWindow(this.memoryView, this.boardView);
                
                break;
                
            case 9:
                changeWindow(this.marioCardsView, this.boardView);
                
                break;
            
            case 10:
                changeWindow(this.memoryPathView, this.boardView);
                System.out.println("Se cambia al tablero");
                this.boardController.continuarTurno();
                break;
            case 11: // se cierra el juego de guess who
                changeWindow(this.guessWhoView, this.boardView);
                this.boardController.continuarTurno();
                break;
                
            case 12: // se cierra el collect the coins
                changeWindow(this.collectCoinsView, this.boardView);
                this.boardController.continuarTurno();
                break;
            case 13: // cierra el boardController de todos
                System.exit(0);
                break;
            default:
                System.out.println("Opcion inexistente para cerrar el minijuego");
                break;
        }
        
    }
    
    // metodos varios
    
    // metodo para cambiar de tamano un icono dado a los valores dados
    public static ImageIcon resizeIcon(ImageIcon icon, int width, int height){
        Image iconImage = icon.getImage();
        Image resizedIconImage = iconImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedIconImage);
    }
    
    // metodo envia al servidor una peticion de asignarle el enemigo del minijuego que se esta jugando
    public void getEnemyMiniGame(int gameThreadOpc, int gameOpc){
        try {
            game.getPlayer().getOutputStream().writeInt(0); // helper
            game.getPlayer().getOutputStream().writeInt(1); // designar enemigo
            game.getPlayer().getOutputStream().writeInt(gameThreadOpc);
            game.getPlayer().getOutputStream().writeInt(gameOpc); // subopcion del minijuego
            
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getOtherPlayers(int playerID){ //abre la opci??n de cambiar de pantalla a los dem??s jugadores
        try {
            game.getPlayer().getOutputStream().writeInt(7);
            game.getPlayer().getOutputStream().writeInt(1);
            game.getPlayer().getOutputStream().writeInt(playerID);
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
    public CatGameController getCatGameController(){
        return catGameController;
    }

    public BoardController getBoardController() {
        return boardController;
    }

    public OpponentSelectionController getOpponentController() {
        return opponentController;
    }
    public MemoryController getMemoryGameController() {
        return memoryGameController;
    }
    
    public SelectBoxController getSelectBoxController(){
        return selectBoxController;
    }

    public MarioCardsController getMarioCardsController() {
        return marioCardsController;
    }
    
    
    
    
    
}
