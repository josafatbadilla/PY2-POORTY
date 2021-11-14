
package poorty.controller;

// CONTROLADOR PARA EL TABLERO


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JButton;
import poorty.view.Selection;
import poorty.model.Game;
import poorty.model.Box;
import poorty.model.PlayerCharacter;
import poorty.model.Character;
import poorty.model.CharacterBtn;
import poorty.view.BoardWindow;


public class BoardController implements ActionListener{
    private BoardWindow boardView;
    private Game game;
    private MainController mainController;
    private DataOutputStream outputStream;
    private ArrayList<PlayerCharacter> playerIcon; 
    
    
    public static final int BUTTON_SIZE = 100;
    public static final int BOARD_SIZE = 27;
    public static final int PLAYER_HEIGH = 46;
    public static final int PLAYER_WIDTH = 33;
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
        
        boardView.getNameLbl().setText(game.getPlayer().getCharacterName());
        boardView.getPlayMiniGame().addActionListener(this);
        boardView.getBtnThrowDices().addActionListener(this);
        //mainController.showWindow(boardView);
        //movePlayerCharacter();
        // inicializacion de componentes graficos de la ventana  
        initplayerCharacter();
        initBoard();
        initBackground();
        
        //boardView.getBackgroundlbl().setIcon(MainController.resizeIcon(game.getBackgrounds().get(0), boardView.getBoardPanel().getWidth(),boardView.getBoardPanel().getHeight()));
    }
    
    // funcion para el listener de los botones y demas
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource().equals(boardView.getPlayMiniGame())){
            // se presiona el btn de jugar el minijuego
            mainController.startCatMiniGame(-1); }
            
        if(e.getSource().equals(boardView.getBtnThrowDices())){
            // se lanzan los dados
            //boardView.getBtnThrowDices().setEnabled(false);
            sendDicesResult();
            
        }
    }
    
    private void initBoard(){
        
        for (int i = 0; i < boxArray.length; i++) {
            boxArray[i] = new Box("Juego",i + "");
            boardView.setSize(920, 750);
            boardView.getBoardPanel().setSize(920, 750);
            boardView.getBoardPanel().add(boxArray[i].getBoxButton());
            boxArray[i].getBoxButton().setBackground(Color.GRAY);
            boxArray[i].getBoxButton().setEnabled(false);
            if (i < 8)
                boxArray[i].setBounds(i*BUTTON_SIZE, 0, BUTTON_SIZE, BUTTON_SIZE);
            else if (i >= 8 && i <= 14){
                boxArray[i].setBounds(8*BUTTON_SIZE, (i-8) * BUTTON_SIZE, BUTTON_SIZE, BUTTON_SIZE);
            }
            else if (i > 14  && i < 22){
                boxArray[i].setBounds(8*BUTTON_SIZE-(i-14)*BUTTON_SIZE,6*BUTTON_SIZE, BUTTON_SIZE, BUTTON_SIZE);
            }
            else if(i >= 22){
                boxArray[i].setBounds(0,6*BUTTON_SIZE -(i-22)*BUTTON_SIZE, BUTTON_SIZE, BUTTON_SIZE);
            }
            //System.out.println((i+1) + ".\t " + buttonArray[i].getBounds().x + "," + buttonArray[i].getBounds().y );
        }
    }
    
    private void initplayerCharacter(){
        ArrayList<CharacterBtn> charactersbtns = Selection.characterBtns;
        ArrayList<Character> charactersIcons = game.getCharactersIcons(); // figura de los jugadores
        playerIcon = new ArrayList<>();
        for (int i = 0; i < charactersbtns.size(); i++) {
            if (charactersbtns.get(i).isSelected()){
                for (Character charactersIcon : charactersIcons) {
                    if(charactersbtns.get(i).getCharacterName().equals(charactersIcon.getName())){
                        System.out.println("Se añade el caracter");
                        playerIcon.add(new PlayerCharacter(PLAYER_WIDTH, PLAYER_HEIGH, charactersIcons.get(i)));
                        
                    }
                }
                
            }
        }
        
        //dibujarlos
        for (int i = 0; i < playerIcon.size(); i++) {
            if (i < 3){
                boardView.getBoardPanel().add(playerIcon.get(i));
                playerIcon.get(i).updateBounds(i *PLAYER_WIDTH, 0);
            }
            else{
                boardView.getBoardPanel().add(playerIcon.get(i));
                playerIcon.get(i).updateBounds((i%3) *PLAYER_WIDTH, PLAYER_HEIGH + 8 );
            }
        }
        //playerButton = boardView.getComponent(8);
    }
    
    public void initBackground(){
        JLabel  background = new JLabel(MainController.resizeIcon(game.getBackgrounds().get(4), boardView.getBoardPanel().getWidth(),boardView.getBoardPanel().getHeight())); 
        background.setBounds(0, 0,boardView.getBoardPanel().getWidth(),boardView.getBoardPanel().getHeight());
        boardView.getBoardPanel().add(background);
    }
    
    public void movePlayerCharacter(int value){
        for (int i = 0; i < playerIcon.size(); i++) {
            if(game.getPlayer().getCharacterName().equals(playerIcon.get(i).getCharacterName())){
                
                int casilla = playerIcon.get(i).getCasillaActual() + value;
                
                if (casilla == BOARD_SIZE)
                    continue;
                else if (casilla > BOARD_SIZE)
                    casilla = (BOARD_SIZE -1) - (casilla%(BOARD_SIZE -1));
                
                playerIcon.get(i).setCasillaActual(casilla);
                
                int x = playerIcon.get(i).getX();
                int y = playerIcon.get(i).getY();
                System.out.println("Se mueve el jugador  Casilla: " + casilla + " Value:" + value);
                System.out.println("x= " + x + " y= " + y);
                if (casilla <= 8){
                    int x1 = (x % BUTTON_SIZE);
                    while(x1 != (x % BUTTON_SIZE) + BUTTON_SIZE * casilla){
                        playerIcon.get(i).updateBounds(x1, y % BUTTON_SIZE);
                        playerIcon.get(i).setBounds( x1, y % BUTTON_SIZE , PLAYER_WIDTH, PLAYER_HEIGH);
                        x1 = x1 + BUTTON_SIZE;
                        try {
                            characterMoved(i);
                            System.out.println(x1+ "");
                            sleep(1000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(BoardController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                else if (casilla > 8 && casilla < 14 ){
                    playerIcon.get(i).updateBounds((x % BUTTON_SIZE) + BUTTON_SIZE * 8 , (y % BUTTON_SIZE) + BUTTON_SIZE * (casilla - 8));
                }
                else if (casilla >= 14 && casilla < 22 ){
                    x = (x % BUTTON_SIZE) + BUTTON_SIZE * 8; 
                    playerIcon.get(i).updateBounds(x - (BUTTON_SIZE * (casilla - 14)) , (y % BUTTON_SIZE) + BUTTON_SIZE  * 6);
                }
                else if (casilla >= 22){
                    y = (y % BUTTON_SIZE) + BUTTON_SIZE * 6; 
                    playerIcon.get(i).updateBounds(x % BUTTON_SIZE , y - (BUTTON_SIZE*(casilla - 22)));
                }
                
                break;
            }
        }
        
    } 
    
    public void updateCharacters(){
        for (int i = 0; i < playerIcon.size(); i++) {
            int x = playerIcon.get(i).getx();
            int y = playerIcon.get(i).gety();
            playerIcon.get(i).setBounds( x, y , PLAYER_WIDTH, PLAYER_HEIGH);
            }
    }
    
    private void sendDicesResult(){
        
        try {
            int dices = game.throwDices(boardView.getLblDice1(), boardView.getLblDice2());
            System.out.println("Dices :" + dices);
            movePlayerCharacter(dices);
            
        } catch (InterruptedException ex) {
            Logger.getLogger(BoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
                
           
    }
    
    public ArrayList<PlayerCharacter> getPlayerIcon() {
        return playerIcon;
    }
    
    // funciones para la conexion con el servidor
    
    
    public void characterMoved(int i){
        try {
                outputStream.writeInt(5); // opcion de tablero 
                outputStream.writeInt(1); // 1
                outputStream.writeInt(i); // envía el indice del personaje
                outputStream.writeInt(playerIcon.get(i).getX()); // envía la nueva coordenada x
                outputStream.writeInt(playerIcon.get(i).getY()); // envía la nueva coordenada y 
            } catch (IOException ex) {
                        Logger.getLogger(LobbyController.class.getName()).log(Level.SEVERE, null, ex);
            } 
    }

    
    
    
}
