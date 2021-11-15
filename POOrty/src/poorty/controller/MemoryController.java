
package poorty.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataOutputStream;
import java.util.ArrayList;
import poorty.model.Game;
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
    
    // atributos para la administracion del juego
    private boolean playerTurn;
    private ArrayList<MemoryCard> clickedCards;
    private boolean miniGameHost;
    private int enemyId;

    public MemoryController(MemoryWindow memoryView, Game game, MainController mainController, int enemyId) {
        this.memoryView = memoryView;
        this.game = game;
        this.mainController = mainController;
        this.enemyId = enemyId;
        
        // se genera el juego de memory y se establecen las cartas
        this.game.setMemoryGame(new Memory(memoryView.getMemoryCardMatrix()));
        
        // determina si pide enemigo o es el enemigo solicitado
        // -1 indica que es el que inicio el juego
        this.miniGameHost = enemyId == -1;
        this.playerTurn = miniGameHost; // el host siempre inicia con el turno
        this.clickedCards = new ArrayList<>();
    }
    
    public void _init_(){

        // se asigna un enemigo aleatorio
        if(this.miniGameHost){
            // 4 representa el juego del gato
//            mainController.getEnemyMiniGame(4, 1);
        }
        
        updatePlayerTurn(miniGameHost); // se establece el turno inicial como el minigamehost del juego
        
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
        System.out.println("Clicked");
       if(e.getSource().getClass().equals(MemoryCard.class)){
            // se presiona un label de la sopa de letras
            System.out.println("Click label");
            MemoryCard clickedLabel = (MemoryCard) e.getSource();
            if(this.playerTurn && this.clickedCards.size() < 2 && !clickedLabel.isFaceUp()){
                // si es su turno y tiene moviminetos y la carta esta boca a abajo 
                
                clickedLabel.setFaceUp(true);
                this.clickedCards.add(clickedLabel);
                
                if(clickedCards.size() == 2){
                    // hace check si las que coloco boca arriba son iguales
                    // para quedarse asi y contar el puntaje
                    // si no se colocan otra vez boca abajo
                    // se remueven todas las cartas seleccionadas
                    System.out.println("Check");
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    // ------------------------------------------------ METODOS ---------------------------------------------------------------
    // actualizacion del turno del jugador y en la pantalla
    public void updatePlayerTurn(boolean newTurn){
        if(newTurn){ // es mi turno
            memoryView.getLblTurn().setText("Tu turno");
        }else{ // no es mi turno
            memoryView.getLblTurn().setText("Turno de tu contrincante");
        }
        
        this.playerTurn = newTurn; // cambia de turno
    }
    
    
    // ------------------------------------------- GETTERS AND SETTERS --------------------------------------------------------------
}
