

package poorty.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import poorty.model.CatGameButton;
import poorty.model.CharacterBtn;
import poorty.model.Game;
import poorty.view.CatGameWindow;
import poorty.view.Selection;



public class CatGameController implements ActionListener {
    private CatGameWindow catGameView;
    private Game game;
    private MainController mainController;
    private DataOutputStream outputStream;
    
    private boolean playerTurn;
    private boolean miniGameHost;
    private int enemyId;

    public CatGameController(CatGameWindow catGameView, Game game, MainController mainController, int enemyId) {
        this.catGameView = catGameView;
        this.game = game;
        this.mainController = mainController;
        this.outputStream = game.getPlayer().getOutputStream();
        
        this.enemyId = enemyId;
        // determina si pide enemigo o es el enemigo solicitado
        // -1 indica que es el que inicio el juego
        this.miniGameHost = enemyId == -1;
        this.playerTurn = miniGameHost; // el host siempre inicia con el turno
    }
    
    
    // METODOS
    
    // se inicializan los compponentes de la pantalla
    public void _init_(){
        // agregar los listeners
        catGameView.getBtnBack().setEnabled(false);
        catGameView.getBtnBack().addActionListener(this);
        
        // se asigna un enemigo aleatorio
        if(this.miniGameHost){
            // 4 representa el juego del gato
            mainController.getEnemyMiniGame(4, 1);
        }

        
        // inicializacion de componentes graficos de la ventana
        updatePlayerTurn(miniGameHost); // se establece el turno inicial como el minigamehost del juego
        initCatGameBoard();
    }
    
    // creacion del tablero
    private void initCatGameBoard(){
        int x = 0;
        int y = 0;
        for (int i = 0; i < CatGameWindow.PROPORTION; i++) {
            for (int j = 0; j < CatGameWindow.PROPORTION; j++) {
                catGameView.getBoard()[i][j] = createCatGameButton(i, j, CatGameButton.getImageIcon(0), x, y);
                this.catGameView.getPnlBoard().add(catGameView.getBoard()[i][j]);
                
                x += CatGameWindow.BOARD_BTN_SIZE;
            }
            x = 0;
            y += CatGameWindow.BOARD_BTN_SIZE;
        }
    }
    
    
    private CatGameButton createCatGameButton(int row, int column, ImageIcon icon, int x, int y){
        CatGameButton newButton = new CatGameButton(row, column, 0, icon);
        newButton.setBounds(x, y, CatGameWindow.BOARD_BTN_SIZE, CatGameWindow.BOARD_BTN_SIZE);
        newButton.addActionListener(this);
        return newButton;
    }
    
    // funcion para actualizar los iconos del tablero en pantalla
    public void repaintBoard(){
        ImageIcon icon;
        for (int i = 0; i < CatGameWindow.PROPORTION; i++) {
            for (int j = 0; j < CatGameWindow.PROPORTION; j++) {
                icon = MainController.resizeIcon(CatGameButton.getImageIcon(catGameView.getBoard()[i][j].getValue()), CatGameWindow.BOARD_BTN_SIZE - 5, CatGameWindow.BOARD_BTN_SIZE - 5);
                catGameView.getBoard()[i][j].setIcon(icon);
            }
        }
        
        catGameView.getPnlBoard().repaint(); // se repinta el tablero
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource().getClass().equals(CatGameButton.class)){
            // se presiono un boton de la matriz
            // se realiza la actualizacion del board en ambas pantallas
            // se cambia de turno
            play((CatGameButton)e.getSource());
        }
        
        if(e.getSource().equals(catGameView.getBtnBack())){
            // se regresa todo mundo al tablero principal
            System.out.println("Se presiona el boton de cerrar");
            closeMiniGame();
        }
       
    }
    
    // actualizacion del turno del jugador y en la pantalla
    public void updatePlayerTurn(boolean newTurn){
        if(newTurn){ // es mi turno
            catGameView.getLblTurn().setText("Tu turno");
        }else{ // no es mi turno
            catGameView.getLblTurn().setText("Turno de tu contrincante");
        }
        
        this.playerTurn = newTurn; // cambia de turno
    }
   
    
    
    
    
    // funcion para jugar y enviar al servidor
    private void play(CatGameButton clickedButton){
        // solo si es el turno de mi se realiza la accion
        if(this.playerTurn && clickedButton.getValue() == 0){
            
            clickedButton.setValue(1); // marcar con x donde yo juegue
            
            // se envia la jugada al adversario
            try {
                outputStream.writeInt(4); // opc del juego
                outputStream.writeInt(1); // subopcion para enviar una jugada
                outputStream.writeInt(this.enemyId); // a mi enemigo
                outputStream.writeInt(clickedButton.getRow()); // se envia la fila
                outputStream.writeInt(clickedButton.getColumn()); // se envia la columna
            } catch (IOException ex) {
                Logger.getLogger(CatGameController.class.getName()).log(Level.SEVERE, null, ex);
            }

            updatePlayerTurn(false); // despues de jugar se pasa mi turno
            repaintBoard();
            
            
            if(isWinner(1)){
                // verifica si la juegada que realizo es la ganadora
                finishGame(true);
            }
        }
    }
    
    // cuando mi enemigo juega yo recibo la jugada
    public void recievePlay(int row, int column){
        
        catGameView.getBoard()[row][column].setValue(2); // marca con un ciculo donde jugo mi contrincante
        updatePlayerTurn(true); // despues de recibir una jugada es mi turno
        repaintBoard();
        
        // gana el contrincante
        if(isWinner(2)){
                // pierdo yo
                finishGame(false);
        }
    }
    
    private boolean isWinner(int value){
        
        //Ganó en las filas
        for(int i=0;i<3;i++){
            if ((catGameView.getBoard()[i][0].getValue() == catGameView.getBoard()[i][1].getValue()) 
                    && (catGameView.getBoard()[i][1].getValue() == catGameView.getBoard()[i][2].getValue())
                    && (catGameView.getBoard()[i][0].getValue() ==  value)){
                return true;
            }
        }
        
        //Gano en las columnas
        for(int i=0;i<3;i++){
            if ((catGameView.getBoard()[0][i].getValue() ==catGameView.getBoard()[1][i].getValue())
                    &&(catGameView.getBoard()[1][i].getValue() ==catGameView.getBoard()[2][i].getValue())
                    && (catGameView.getBoard()[0][i].getValue() ==  value)){
                return true;
            }
        }
        
        //Verificar diagonal 1
        if ((catGameView.getBoard()[0][0].getValue()  == catGameView.getBoard()[1][1].getValue() )
                &&(catGameView.getBoard()[1][1].getValue()  ==catGameView.getBoard()[2][2].getValue())
                && (catGameView.getBoard()[0][0].getValue() == value))
            return true;
        
        //Verificar diagonal 2
        if ((catGameView.getBoard()[2][0].getValue() == catGameView.getBoard()[1][1].getValue() )
                &&(catGameView.getBoard()[1][1].getValue() ==catGameView.getBoard()[0][2].getValue() )
                && (catGameView.getBoard()[2][0].getValue() == value))
            return true;
        
        return false;
    }
    
    // cuando haya un ganador esta funcion cambia el estado del lbl del juego
    // y activa para el host el boton de salir con los resultados correspondientes 
    private void finishGame(boolean playerWin){
        
        if(playerWin){
            // yo gano la partida
            catGameView.getLblGameSatus().setText("Has ganado");
            
        }else{
            // yo pierdo la partida
            catGameView.getLblGameSatus().setText("Has perdido");
        }
        
        if(this.miniGameHost){
            catGameView.getBtnBack().setEnabled(true); // se activa el boton para salir
            this.game.getPlayer().setThrowDices(playerWin); // puede tirar los dados en su proximo turno o no siendo este el retador del juego
        }

    }
    
    // ---------------------- funciones para la conexion con el servidor-------------------------
    private void closeMiniGame(){
        try {
            outputStream.writeInt(4); // opc del juego
            outputStream.writeInt(2); // subopcion para enviar una jugada
            outputStream.writeInt(this.enemyId); // a mi enemigo
        } catch (IOException ex) {
            Logger.getLogger(CatGameController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
 
    
    // getters and setters
    public void setEnemyId(int enemyId) {
        this.enemyId = enemyId;
    }
    
    public void setPlayerTurn(boolean turn){
        this.playerTurn = turn;
    }
    
}
