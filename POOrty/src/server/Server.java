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
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Server {
    
    public final static int SERVER_PORT = 8081;
    
    
    private ArrayList<ServerThread> players;
    private Socket player;
    private boolean startGame = false;
    private ArrayList<Turn> playersTurn;
    public static final int RANDOM_TURN_NUM = (int)(Math.random()*1000+1); // numero aleatorio del 1 al 1000;
   
   public Server(){
       players = new ArrayList<ServerThread>();
       playersTurn = new ArrayList<>();
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
   
   public void insertPlayerTurn(Turn playerTurn, int turnSelection){
       playersTurn.add(playerTurn);
       if(turnSelection == 1){
            // numero aleatorio se ordena de menor a mayor
           playersTurn.sort(Comparator.comparing(Turn::getTurnResult));
       }else{
           // dados se ordena de mayor a menor
           playersTurn.sort(Comparator.comparing(Turn::getTurnResult).reversed());
       }
   }
   
   // getters and setters

    public ArrayList<Turn> getPlayersTurn() {
        return playersTurn;
    }
   
   
   
   public static void main(String[] args) {
        Server server = new Server();
        server.runServer(); // inicia el servidor
   }
    
}
