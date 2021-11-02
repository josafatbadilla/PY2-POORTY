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
            boolean playerHost;
            
            while(!startGame){
                System.out.println("Esperando jugador...");
                this.player = server.accept();
                if(!startGame && players.size() + 1 <= 6){
                    playerHost = players.isEmpty(); // el primero en llegar es el host
                    new ServerThread(player, this, players.size() + 1, players, playerHost); // se agrega a lista de jugadores
                }else{
                    hostStartGame();
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
   
   public void hostStartGame(){
       this.startGame = true; // se inicia con el juego
   }
   
   
   public static void main(String[] args) {
        Server server = new Server();
        server.runServer(); // inicia el servidor
    }
    
}
