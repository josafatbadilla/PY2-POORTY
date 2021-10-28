
package poorty;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Player {
    
    public static final String IP_SERVER = "localhost";
    public static final int SERVER_PORT = 8081;
    
    DataInputStream inputStream = null;//leer comunicacion
    DataOutputStream outputStream = null;//escribir comunicacion
    Socket player = null;//para la comunicacion
    
    private int playerId;
    private boolean host;
    // referencia a la ventana del cliente para realizar y llamar las funciones necesarias
    
    
   public Player(/*recibir la ventana*/){
       
      // conexion con el servidor
      try {
            player = new Socket(IP_SERVER, SERVER_PORT);
            
            // inicializa las entradas-lectura y salidas-escritura
            inputStream = new DataInputStream(player.getInputStream());
            outputStream = new DataOutputStream(player.getOutputStream());
            
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        new PlayerThread(inputStream, this).start();
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
   

    
}
