
package poorty.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import javax.swing.JLabel;
import poorty.model.Game;
import poorty.view.RandomTurnWindow;


public class RandomTurnController implements ActionListener{
   
    private RandomTurnWindow randomTurnView;
    private Game game;
    private MainController mainController;
    private DataOutputStream outputStream;

    public RandomTurnController(RandomTurnWindow randomTurnView, Game game, MainController mainController) {
        this.randomTurnView = randomTurnView;
        this.game = game;
        this.mainController = mainController;
        this.outputStream = game.getPlayer().getOutputStream();
    }
    
    // METODOS
    
    // se inicializan los compponentes de la pantalla
    public void _init_(){
        // agregar los listeners
        randomTurnView.getBtnSendNum().addActionListener(this);
        randomTurnView.getBtnStartGame().addActionListener(this);
        randomTurnView.setTitle("Jugador " + game.getPlayer().getPlayerId() + " : " + game.getPlayer().getCharacterName());
        // se activan solo para el host
        randomTurnView.getBtnStartGame().setEnabled(game.getPlayer().isHost());
        initBackground();
        // inicializacion de componentes graficos de la ventana
    }
    
    
    public void printTheRandomNumber(int randomServerNum){
        randomTurnView.getLblRandomNum().setText(randomServerNum  + "");
    }
  

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource().equals(randomTurnView.getBtnSendNum())){
            if(!randomTurnView.getTxfPlayerNum().getText().equals("")){
                randomTurnView.getBtnSendNum().setEnabled(false);
                sendPlayerNumber(parseInt(randomTurnView.getTxfPlayerNum().getText()));
                
            }
            
        }
        
        if(e.getSource().equals(randomTurnView.getBtnStartGame())){
            // se pasa al tablero
            startGame();
        }
        
    }
    
    public void initBackground(){
        JLabel  background = new JLabel(MainController.resizeIcon(game.getBackgrounds().get(1), randomTurnView.getWidth(),randomTurnView.getHeight())); 
        background.setBounds(0, 0,randomTurnView.getWidth(),randomTurnView.getHeight());
        randomTurnView.add(background);
    }
    
    
    // metodos para la conexion con el servidor
     // se envia el numero escogido por el jugador
    private void sendPlayerNumber(int playerNum){
        try {
            outputStream.writeInt(3); // opcion del turn selection
            outputStream.writeInt(1); // para la pasar a la seleccion del turno
            outputStream.writeInt(playerNum); // determina a la pantalla a la que se envia
            
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(LobbyController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
    }
    
    // se pasa para el tablero principal para que empiece el juego
    public void startGame(){
        try {
            outputStream.writeInt(3); // opcion de la seleccion de turno
            outputStream.writeInt(3); // para la pasar al tablero
            
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(LobbyController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
    }
    
}
