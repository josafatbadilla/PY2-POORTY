
package poorty.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataOutputStream;
import java.util.Random;
import poorty.model.Coin;
import poorty.model.CollectCoins;
import poorty.model.Game;
import poorty.model.Timer;
import poorty.model.iObserver;
import poorty.view.CoinsWindow;


public class CoinsController implements ActionListener, MouseListener, iObserver{
    
    private CoinsWindow coinsView;
    private Game game;
    private MainController mainController;
    private DataOutputStream outputStream;
    private Timer gameTimer;
    private int[] TIMES_TIMER = {30, 45, 60};
    private int score;
    private boolean statusGame;
    private boolean playerWin;

    public CoinsController(CoinsWindow coinsView, Game game, MainController mainController) {
        System.out.println("Se crea el controllador de las monedas");
        this.coinsView = coinsView;
        this.game = game;
        this.mainController = mainController;
        this.gameTimer = new Timer(TIMES_TIMER[new Random().nextInt(3)], this.coinsView.getLblTime());
        gameTimer.addObserver(this); // me agrego como observador
        this.game.setCollectCoins(new CollectCoins(coinsView.getCoins())); // se crea un nuevo juego y se le asigna a la variable del game
        this.score = 0;
        this.statusGame = false;
        this.playerWin = false;
    }
    
    public void _init_(){
        
        // listener
        this.coinsView.getBtnGameControl().addActionListener(this); // boton de control
        
        
        // agregado de los listeners 
        addCoinsMouseListeners();
        
    }
    
    private void addCoinsMouseListeners(){
        for(int i = 0; i < CoinsWindow.MATRIX_SIZE; i++){
            for(int j = 0; j < CoinsWindow.MATRIX_SIZE; j++){
                coinsView.getCoins()[i][j].addMouseListener(this);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource().equals(this.coinsView.getBtnGameControl())){
            // se presiona el boton de control
            if(this.coinsView.getBtnGameControl().getText().equals("Iniciar")){
                // se inicia el juego
                startGame();
            }else{
                finishGame();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource().getClass().equals(Coin.class)){
            // se presiona un label de la sopa de letras
            Coin clickedLabel = (Coin) e.getSource();
            // solo recolecta cuando el juego esta activo
            if(this.statusGame){
                collectCoin(clickedLabel, true);
            }
            
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    // iniciar el temporizador y el juego
    private void startGame(){
        System.out.println("Se inicia el juego");
        this.statusGame = true;
        this.gameTimer.start();// inicia el temporizador
        this.coinsView.getBtnGameControl().setEnabled(false); // se desactiva
        this.coinsView.getLblResult().setText("Jugando...");
    }
    
    // terminar el temporizador y que no se puedan recolectar mas monedas
    public void stopGame(){
        this.statusGame = false; // se detiene el juego
        this.playerWin = score > 0;
        String result = playerWin ? "Has ganado" : "Has perdido";
        this.coinsView.getLblResult().setText(result);
        this.coinsView.getBtnGameControl().setText("Salir");
        this.coinsView.getBtnGameControl().setEnabled(true);
    }
    
    
    // determina si el juegador gano o no y activa el boton para cerrar el juego
    private void finishGame(){
        this.game.getPlayer().setThrowDices(playerWin); // setea si gana o pierde
        mainController.closeMiniGame(12);
    }
    
    // se recolecta la moneda y se actualiza el puntaje
    private void collectCoin(Coin coin, boolean collect){
        coin.setCollected(collect);
        updateScore(coin.getValue());
    }
    
    private void updateScore(int addScore){
        this.score += addScore;
        
        this.coinsView.getLblScore().setText(this.score + "");
    }
    
    
    // cuando termina el juego

    @Override
    public void notifyObserver() {
        stopGame();
    }
    
}
