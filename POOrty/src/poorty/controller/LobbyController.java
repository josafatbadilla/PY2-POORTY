
// CONTROLADOR PARA LA PANTALLA DEL LOBBY

package poorty.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import poorty.model.Game;
import poorty.view.Lobby;


public class LobbyController implements ActionListener {
    private Lobby lobbyWindow;
    private Game game;
    private MainController mainController;
    private DataOutputStream outputStream;
    
    public LobbyController(Game game, Lobby lobbyWindow, MainController mainController){
        this.lobbyWindow = lobbyWindow;
        this.game = game;
        this.mainController = mainController;
        this.outputStream = game.getPlayer().getOutputStream();
    }

    // se realiza una inicializacion de la pantalla
    public void _init_(){
        lobbyWindow.getBtnPlay().addActionListener(this);
        
        serverConnection(1); // avisa que un nuevo jugador se conecto
        
        mainController.showWindow(lobbyWindow); // muestra la pantalla de window
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(lobbyWindow.getBtnPlay())){
            System.out.println("Se presiona el boton de jugar");
        }
    }
    
    
    // agrega en la ventana el nuevo jugador que se unio
    public void addLobbyPlayer(int playerId, boolean playerHost){
        String str;
        
        if(playerId == game.getPlayer().getPlayerId()){
            str = "Te has unido correctamente."; 
        }else{
            str = "Jugador " + playerId + " se ha unido";
        }

        str += playerHost ? " (host)\n " : " \n";
        this.lobbyWindow.getTxaPlayers().setText(this.lobbyWindow.getTxaPlayers().getText() + str);
    }
    
    
   
    // realiza toda la comunicacion necesaria con el servidor
    private void serverConnection(int option){
        
        try {
            switch(option){
                case 1:
                    // se comunica que se conecto con exito el jugador
                    outputStream.writeInt(1); // opcion del lobby
                    outputStream.writeInt(0); // opcion de avisar conexion 
                    
                    break;

                case 2:
                    break;

            }
        } catch (IOException ex) {
                    Logger.getLogger(LobbyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
