
package poorty.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
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
        
        // se activan solo para el host
        randomTurnView.getBtnStartGame().setEnabled(game.getPlayer().isHost());
        
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
        
    }
    
    
    // metodos para la conexion con el servidor
     // se cambia para la pantalla para determinar los turnos
    private void sendPlayerNumber(int playerNum){
        try {
            outputStream.writeInt(3); // opcion del turn selection
            outputStream.writeInt(1); // para la pasar a la seleccion del turno
            outputStream.writeInt(playerNum); // determina a la pantalla a la que se envia
            
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(LobbyController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
    }
    
}
