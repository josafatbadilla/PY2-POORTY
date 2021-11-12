
package poorty.model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import poorty.controller.MainController;



public class Player {
    
    public static final String IP_SERVER = "localhost";
    public static final int SERVER_PORT = 8081;
    
    DataInputStream inputStream = null;//leer comunicacion
    DataOutputStream outputStream = null;//escribir comunicacion
    Socket player = null;//para la comunicacion
    
    private int playerId;
    private boolean host;
    private String characterName;
    private boolean throwDices; // indica si puede tirar los dados en su turno del tablero principal
    // referencia a la ventana del cliente para realizar y llamar las funciones necesarias
    
    
   public Player(MainController mainController){
       
      // conexion con el servidor
      try {
            player = new Socket(IP_SERVER, SERVER_PORT);
            
            // inicializa las entradas-lectura y salidas-escritura
            inputStream = new DataInputStream(player.getInputStream());
            outputStream = new DataOutputStream(player.getOutputStream());
            
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // inicializacion de variables
        this.characterName = "";
        this.throwDices = true;
        
        new PlayerThread(inputStream, this, mainController).start();
   }
   
   
   
   
   // GETTERS AND SETTERS
    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public boolean isHost() {
        return host;
    }

    public void setHost(boolean host) {
        this.host = host;
    }

    public DataOutputStream getOutputStream() {
        return outputStream;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public boolean isThrowDices() {
        return throwDices;
    }

    public void setThrowDices(boolean throwDices) {
        this.throwDices = throwDices;
    }

    
    
    
    
    
   

    
}
