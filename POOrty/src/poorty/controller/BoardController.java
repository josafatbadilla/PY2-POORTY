
package poorty.controller;

// CONTROLADOR PARA EL TABLERO


import java.awt.Color;
import java.io.DataOutputStream;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JButton;
import poorty.model.Game;
import poorty.model.Box;
import poorty.model.PlayerCharacter;
import poorty.model.Character;
import poorty.view.BoardWindow;


public class BoardController {
    private BoardWindow boardView;
    private Game game;
    private MainController mainController;
    private DataOutputStream outputStream;
    private PlayerCharacter playerIcon; 
    
    
    public static final int BUTTON_SIZE = 100;
    public static final int BOARD_SIZE = 26;
    public static final int PLAYER_HEIGH = 25;
    public static final int PLAYER_WIDTH = 18;
    private Box[] boxArray= new Box[BOARD_SIZE];

    public BoardController(Game game, BoardWindow boardView ,  MainController mainController) {
        this.boardView = boardView;
        this.game = game;
        this.mainController = mainController;
        this.outputStream = game.getPlayer().getOutputStream();
    }
    
    // METODOS
    
    // se inicializan los compponentes de la pantalla
    public void _init_(){
        initplayerCharacter();
        initBoard();
        
        //mainController.showWindow(boardView);
        //movePlayerCharacter();
        
        
        
        // inicializacion de componentes graficos de la ventana  
    }
    
    private void initBoard(){
        
        for (int i = 0; i < boxArray.length; i++) {
            boxArray[i] = new Box("Juego",(i+1) + "");
            boardView.setSize(820, 750);
            boardView.getBoardPanel().add(boxArray[i].getBoxButton());
            boxArray[i].getBoxButton().setBackground(Color.green);
            
            if (i < 8)
                boxArray[i].setBounds(i*BUTTON_SIZE, 0, BUTTON_SIZE, BUTTON_SIZE);
            else if (i >= 8 && i <= 13){
                boxArray[i].setBounds(7*BUTTON_SIZE, (i-7) * BUTTON_SIZE, BUTTON_SIZE, BUTTON_SIZE);
            }
            else if (i > 13  && i < 21){
                boxArray[i].setBounds(7*BUTTON_SIZE-(i-13)*BUTTON_SIZE,6*BUTTON_SIZE, BUTTON_SIZE, BUTTON_SIZE);
            }
            else if(i >= 21){
                boxArray[i].setBounds(0,5*BUTTON_SIZE -(i-21)*BUTTON_SIZE, BUTTON_SIZE, BUTTON_SIZE);
            }
            //System.out.println((i+1) + ".\t " + buttonArray[i].getBounds().x + "," + buttonArray[i].getBounds().y );
        }
    }
    
    private void initplayerCharacter(){
        ArrayList<Character> charactersIcons = game.getCharactersIcons();
        Character pCharacter = charactersIcons.get(0);
        for (int i = 0; i < charactersIcons.size(); i++) {
            if (game.getPlayer().getCharacterName().equals(charactersIcons.get(i).getName())){
                pCharacter = charactersIcons.get(i);
                break;
            }
            
        }
        playerIcon = new PlayerCharacter(PLAYER_WIDTH, PLAYER_HEIGH, pCharacter);
        boardView.getBoardPanel().add(playerIcon.getPlayerButton()).setVisible(true);
        // game.getPlayer().getPlayerId() - 1*PLAYER_HEIGH + 55
        
        playerIcon.getPlayerButton().setBounds( 0, game.getPlayer().getPlayerId() - 1*PLAYER_HEIGH + 55 , playerIcon.getPlayerIcon().getIconWidth(),  playerIcon.getPlayerIcon().getIconHeight());
        
        //playerButton = boardView.getComponent(8);
    }
    
    
    public void movePlayerCharacter(){
        playerIcon.getPlayerButton().setBounds(15, game.getPlayer().getPlayerId() - 1*PLAYER_HEIGH + 55, playerIcon.getPlayerIcon().getIconWidth(),  playerIcon.getPlayerIcon().getIconHeight());
    }
    // funciones para la conexion con el servidor
    
    
}
