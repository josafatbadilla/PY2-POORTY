/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author John
 */
public class Server {
    
    public final static int SERVER_PORT = 8081;
    
    
    private ArrayList<ServerThread> players;
    private Socket player;
    private boolean startGame = false;
   
   public Server(){
       players = new ArrayList<ServerThread>();
   }
   
   public void runServer(){
       
       try {
            ServerSocket server = new ServerSocket(SERVER_PORT);
            System.out.println("Esperando host...");
            this.player = server.accept(); // el primero en conectarse es host
            new ServerThread(player, this, players.size() + 1, players, true);
            System.out.println("Esperando otros jugadores");
            while(!startGame){
                this.player = server.accept();
                if(players.size() < 6 && !startGame){
                    // hay espacio para otro jugador
                    new ServerThread(player, this, players.size() + 1, players, false); // se agrega a lista de jugadores
                }else{
                    System.out.println("No hay mas espacio");
                    break;
                }
            }
            
            System.out.println("A jugar");
            
            
            while(true){
                // mantiene vivo el server
            }
           
       } catch (IOException ex) {
           
           System.out.println("Error en el servidor");
       }
   }
   
   
   public static void main(String[] args) {
        Server server = new Server();
        server.runServer(); // inicia el servidor
    }
    
}
