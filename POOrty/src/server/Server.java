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
    
    public final static int SERVERPORT = 8081;
    
    
    private ArrayList<ServerThread> players;
    private Socket player;
    private boolean startGame = false;
   
   public Server(){
       
   }
   
   public void runServer(){
       
       try {
            ServerSocket server = new ServerSocket(SERVERPORT);
           
            this.player = server.accept(); // el primero en conectarse es host
            this.players.add(new ServerThread(player, this, players.size(), players, true)); // se agrega a lista de jugadores
            this.players.get(0).start();
            
            while(!startGame){
                
                if(players.size() < 6){
                    // hay espacio para otro jugador
                    this.player = server.accept();
                    this.players.add(new ServerThread(player, this, players.size() + 1, players, false)); // se agrega a lista de jugadores
                    this.players.get(players.size() - 1).start();
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
}
