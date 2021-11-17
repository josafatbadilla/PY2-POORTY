
package poorty.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import poorty.model.Game;
import poorty.model.IconMemoryCard;
import poorty.model.Memory;
import poorty.model.MemoryCard;
import poorty.view.MemoryWindow;
import static poorty.view.MemoryWindow.MATRIX_COL;
import static poorty.view.MemoryWindow.MATRIX_ROW;


public class MemoryController implements MouseListener, ActionListener{
    private MemoryWindow memoryView;
    private Game game;
    private MainController mainController;
    private DataOutputStream outputStream;
    private ObjectOutputStream objOutputStream;
    
    // atributos para la administracion del juego
    private boolean playerTurn;
    private ArrayList<MemoryCard> clickedCards;
    private boolean miniGameHost;
    private int enemyId;
    private int score; // por cada pareja acertada se suma un punto

    public MemoryController(MemoryWindow memoryView, Game game, MainController mainController, int enemyId) {
        this.memoryView = memoryView;
        this.game = game;
        this.mainController = mainController;
        this.enemyId = enemyId;
        this.outputStream = game.getPlayer().getOutputStream();
        this.objOutputStream = game.getPlayer().getObjOutputStream();
        
        this.miniGameHost = enemyId == -1; // si es el host del juego o no
        
        // se genera el juego de memory y se establecen las cartas
        this.game.setMemoryGame(new Memory(memoryView.getMemoryCardMatrix(), this.miniGameHost));
        
        // determina si pide enemigo o es el enemigo solicitado
        // -1 indica que es el que inicio el juego
        
        updatePlayerTurn(miniGameHost); // se establece el turno inicial como el minigamehost del juego
        this.clickedCards = new ArrayList<>();
        
        this.score = 0;
    }
    
    public void _init_(){

        // se asigna un enemigo aleatorio
        if(this.miniGameHost){
            // 4 representa el juego del gato
            mainController.getEnemyMiniGame(6, 1);
            
        }
        
        this.memoryView.getBtnCheckMoves().addActionListener(this);
        this.memoryView.getBtnCheckMoves().setEnabled(false);
        
        // agregar los listener de los labels
        addMemoryCardListeners();
    }
    
    // agrega los mouse listeners a cada una de las cartas
    private void addMemoryCardListeners(){
        for (int i = 0; i < MATRIX_ROW; i++) {
            for (int j = 0; j < MATRIX_COL; j++) {
                memoryView.getMemoryCardMatrix()[i][j].addMouseListener(this);
            }
        }
    }
    
    

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource().getClass().equals(MemoryCard.class)){
            // se presiona un label de la sopa de letras
            MemoryCard clickedLabel = (MemoryCard) e.getSource();
            if(this.playerTurn && this.clickedCards.size() < 2 && !clickedLabel.isFaceUp()){
                // si es su turno y tiene moviminetos y la carta esta boca a abajo 
                makeMove(clickedLabel, true);
                this.clickedCards.add(clickedLabel); // agrega a la lista de labels jugados
                
                if(clickedCards.size() == 2){
                    this.memoryView.getBtnCheckMoves().setEnabled(true);
                }
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
    
    // ACTIONLISTENER
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(this.memoryView.getBtnCheckMoves())){
            checkMoves();
        }
    }
    
    
    // ------------------------------------------------ METODOS ---------------------------------------------------------------
    // envia al contrincante las cartas
    private void sendEnemyCards(){
         try {
                outputStream.writeInt(6); // opc del juego
                outputStream.writeInt(2); // subopcion para enviar cartas generadas
                outputStream.writeInt(this.enemyId);
                
                // procede a enviar en orden cada uno los iconos de las cartas
                for (int i = 0; i < MATRIX_ROW; i++) {
                    for (int j = 0; j < MATRIX_COL; j++) {
                       objOutputStream.writeObject(memoryView.getMemoryCardMatrix()[i][j].getCardIcon());
                    }
                }
                
            } catch (IOException ex) {
                Logger.getLogger(CatGameController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    // recibir los iconos correspondientes a cada carta del host
    public void setIconMemoryCard(int i, int j, IconMemoryCard iconMemoryCard){
        memoryView.getMemoryCardMatrix()[i][j].setCardIcon(iconMemoryCard);
    }

    // actualizacion del turno del jugador y en la pantalla
    public void updatePlayerTurn(boolean newTurn){
        if(newTurn){ // es mi turno
            memoryView.getLblTurn().setText("Tu turno");
        }else{ // no es mi turno
            memoryView.getLblTurn().setText("Turno de tu contrincante");
        }
        
        this.playerTurn = newTurn; // cambia de turno
    }
    
    // realiza una jugada en mi tablero y envia el mismo movimiento al enemigo
    private void makeMove(MemoryCard labelMove, boolean faceUp){
        labelMove.setFaceUp(faceUp);
        // envia al enemigo el movimiento hecho
        try {
            outputStream.writeInt(6); // opc del juego
            outputStream.writeInt(1); // subopcion para enviar una jugada

            outputStream.writeInt(this.enemyId); // a mi enemigo

            outputStream.writeInt(labelMove.getI()); // se envia la fila
            outputStream.writeInt(labelMove.getJ()); // se envia la columna
            outputStream.writeBoolean(faceUp); // se envia si se cambia a boca arriba o boca abajo
        } catch (IOException ex) {
            Logger.getLogger(CatGameController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error");
        }
            
    }
    
    // valida si las cartas descubiertas son iguales (su valor)
    private void checkMoves(){              
        if(this.clickedCards.get(0).getCardIcon().getCardValue().equals(this.clickedCards.get(1).getCardIcon().getCardValue())){
            // suma al puntaje
            addScore();
            if(score == 5){
                // el primero en llegar a 5 parejas gana
                finishGame();
            }
        }else{
            // oculta los dos
            for(int i = 0; i < clickedCards.size(); i++){
                makeMove(clickedCards.get(i), false); // oculta las que descubrio
            }
        }
        // para quedarse asi y contar el puntaje
        // si no se colocan otra vez boca abajo
        this.clickedCards.removeAll(clickedCards);
        // pasa el turno
        changeGameTurn();
        this.memoryView.getBtnCheckMoves().setEnabled(false);
    }
    
    // recibe un movimiento del contrincante
    public void recieveMove(int i, int j, boolean faceUp){
        // se setea en la carta que jugo el valor correspondiente
        memoryView.getMemoryCardMatrix()[i][j].setFaceUp(faceUp);
    }
    
    
    public void changeGameTurn(){
        updatePlayerTurn(false);
        
        try {
            outputStream.writeInt(6); // opc del juego
            outputStream.writeInt(3); // subopcion para pasar el turno
            outputStream.writeInt(this.enemyId);

        } catch (IOException ex) {
            Logger.getLogger(CatGameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    // Este jugador gana el juego e indica a todas las ventanas y las cierra
    public void finishGame(){
        // enviar para que se termine el juego al enemigo
        try {
            outputStream.writeInt(6); // opc del juego
            outputStream.writeInt(4); // subopcion para pasar el turno
            outputStream.writeInt(this.enemyId);

        } catch (IOException ex) {
            Logger.getLogger(CatGameController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        closeGame();
    }
    
    // se cierra el juego
    public void closeGame(){
        if(this.miniGameHost){
            // si ejecuta esto quiere decir que gano
            this.game.getPlayer().setThrowDices(this.score == 5);
        }
        
        mainController.closeMiniGame(6);
        
    }
    
    // ------------------------------------------- GETTERS AND SETTERS --------------------------------------------------------------
    public void setEnemyId(int enemyId) {
        this.enemyId = enemyId;
        if(this.miniGameHost){           
            // enviar los iconos de las cartas generadas
            sendEnemyCards();
        }
    }
    
    
    private void addScore(){
        this.score++;
        this.memoryView.getLblScore().setText("Puntaje: " + score);
    }
    
    
}
