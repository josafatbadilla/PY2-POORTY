
package poorty;

import java.io.DataInputStream;
import java.io.IOException;




public class PlayerThread extends Thread{
    
    DataInputStream inputStream;
    Player player;
    
    public PlayerThread(DataInputStream inputStream, Player player){
        this.inputStream = inputStream;
        this.player = player;
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
                        case 1:

                            break;
                        case 2:
                            //llamar a funciones de la ventana para actualizar el jframe
                            break;

                    }



            }
        
        }catch(IOException e){
            System.out.println("Error en la comunicaci�n "+"Informaci�n para el usuario");
        }
        
        // se desconecta del servidor
        System.out.println("se desconecto el servidor");
        
    }
    
}
