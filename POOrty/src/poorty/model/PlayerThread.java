
package poorty.model;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import poorty.controller.MainController;




public class PlayerThread extends Thread{
    
    DataInputStream inputStream;
    Player player;
    MainController mainController;
    
    public PlayerThread(DataInputStream inputStream, Player player, MainController mainController){
        this.inputStream = inputStream;
        this.player = player;
        this.mainController = mainController;
    }
    
    @Override
    public void run(){
        try{
            // espera la configuracion inicial del jugador por parte del servidor
            player.setPlayerId(inputStream.readInt());
            player.setHost(inputStream.readBoolean());
            int option = 0;
        
            // lee lo que el servidor ServerThread
            while(true){
                    // espera ordenes del servidor
                    option = inputStream.readInt();
                    switch(option){
                        case 1: // lobby
                            lobby(inputStream.readInt());
                            break;
                        case 2: // seleccion de personaje
                            characterSelection(inputStream.readInt());
                            break;

                    }



            }
        
        }catch(IOException e){
            System.out.println("Error en la comunicaci�n "+"Informaci�n para el usuario");
        }
        
        // se desconecta del servidor
        System.out.println("se desconecto el servidor");
        
    }
    
    // acciones para el lobby
    public void lobby(int option){
        try {
            switch(option){
                case 0: // se pasa a la seleccion de personajes
                    mainController.characterSelectWindow();
                    break;
                case 1: // imprimir la agregacion del nuevo jugador
                    // se imprime el nombre
                    int playerId = inputStream.readInt();
                    boolean host = inputStream.readBoolean();
                    mainController.getLobbyController().addLobbyPlayer(playerId, host);
                    break;

            }
        } catch (IOException ex) {
            Logger.getLogger(PlayerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    // acciones para la seleccion de personajes
    public void characterSelection(int option){
        try {
            switch(option){
                case 1: // se selecciona un personaje
                    String unselectedCharacter = inputStream.readUTF();
                    String selectedCharacter = inputStream.readUTF();
                    
                    mainController.getSelectionController().updateCharacterButtons(unselectedCharacter, selectedCharacter);
                    break;
                case 2:
                    mainController.startGame();
                    break;

            }
        } catch (IOException ex) {
            Logger.getLogger(PlayerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
