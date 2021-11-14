
// Controlador para la ventana de la seleccion de turno

package poorty.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import poorty.model.Game;
import poorty.view.DicesTurnWindow;


public class DicesTurnController implements ActionListener{
    
    private DicesTurnWindow dicesTurnView;
    private Game game;
    private MainController mainController;
    private DataOutputStream outputStream;

    public DicesTurnController(DicesTurnWindow dicesTurnView, Game game, MainController mainController) {
        this.dicesTurnView = dicesTurnView;
        this.game = game;
        this.mainController = mainController;
        this.outputStream = game.getPlayer().getOutputStream();
    }
    
    // METODOS
    
    // se inicializan los compponentes de la pantalla
    public void _init_(){
        // agregar los listeners
       dicesTurnView.getBtnThrowDices().addActionListener(this);
       dicesTurnView.getBtnStartGame().addActionListener(this);
       dicesTurnView.setTitle("Jugador " + game.getPlayer().getPlayerId() + " : " + game.getPlayer().getCharacterName());
        // se activan solo para el host
        dicesTurnView.getBtnStartGame().setEnabled(game.getPlayer().isHost());
        initBackground();
        // inicializacion de componentes graficos de la ventana
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // funcionalidad de los botones
        if(e.getSource().equals(dicesTurnView.getBtnThrowDices())){
            // se lanzan los dados
            dicesTurnView.getBtnThrowDices().setEnabled(false);
            sendDicesResult();
        }
        
        if(e.getSource().equals(dicesTurnView.getBtnStartGame())){
            // se inicia el juego
            startGame();
        }
       
    }
    
    public void initBackground(){
        JLabel  background = new JLabel(MainController.resizeIcon(game.getBackgrounds().get(2), dicesTurnView.getWidth(),dicesTurnView.getHeight())); 
        background.setBounds(0, 0,dicesTurnView.getWidth(),dicesTurnView.getHeight());
        dicesTurnView.add(background);
    }
    
    // funciones para la conexion con el servidor
    // lanza los dados y envia el resultado que salio
    public void sendDicesResult(){
        try {
            int dicesResult = game.throwDices(dicesTurnView.getLblDice1(), dicesTurnView.getLblDice2());
            
            outputStream.writeInt(3); // opcion del turn selection
            outputStream.writeInt(2); // que tiraron los dados para jugar
            outputStream.writeInt(dicesResult); // resultado de dados
            
        } catch (InterruptedException ex) {
            Logger.getLogger(DicesTurnController.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(LobbyController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
    }
    
     // se pasa para el tablero principal para que empiece el juego
    public void startGame(){
        try {
            outputStream.writeInt(3); // opcion de la seleccion de turno
            outputStream.writeInt(4); // para la pasar al tablero desde la ventana de tira de dadoss
            
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(LobbyController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } 
    }
    
    
}
