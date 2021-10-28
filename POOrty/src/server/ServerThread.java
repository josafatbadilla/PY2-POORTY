/*
    Clase que sera el hijo que atiende a cada uno de los jugadores 

*/



package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServerThread  extends Thread{
     Socket socketPlayer = null;//referencia a socket de comunicacion de cliente
     DataInputStream inputStream = null;//Para leer comunicacion
     DataOutputStream outputStream = null;//Para enviar comunicacion	
     Server server;// referencia al servidor

     ArrayList<ServerThread> players; // saber cuales son los jugadores
     // identificar el numero de jugador
     int playerId;
     boolean host;
     
    public ServerThread(Socket socketPlayer, Server server, int playerId, ArrayList<ServerThread> players, boolean host){
        this.socketPlayer = socketPlayer;
        this.server = server;
        this.playerId = playerId;
        this.players = players;
        this.host = host;
    }
    
    
    @Override
    public void run(){
        try {
            inputStream = new DataInputStream(socketPlayer.getInputStream());
            outputStream = new DataOutputStream(socketPlayer.getOutputStream());
        
            //configuracion inicial del jugador
            outputStream.writeInt(playerId);
            outputStream.writeBoolean(host);

            //variables necesarias
            int option = 0;

            while(true){
                option = inputStream.readInt();
                switch(option){
                case 0: // el host comienza el juego

                    break;
                case 1: // todas las opciones del tablero
                    
                    break;
                case 2: // todas las acciones del gato
                    runCatGame();
                    break;
                }
                
            }
            
        } catch (IOException e) {
            System.out.println("Error en el switch principal");
             e.printStackTrace();
        }
        


    }
    
    
    // Metodos para correr los juegos
    private void runCatGame(){
        
        try {
            //accion para el juego del gato
            int option = 0;

            while(true){
                option = inputStream.readInt();
                switch(option){
                case 0: // comenzar el gato

                    break;
                case 1: 
                    
                    break;
                case 2:
                    
                    break;
                }
                
            }
            
        } catch (IOException e) {
            System.out.println("Error en el switch del gato");
             e.printStackTrace();
        }
        
    }
    
    
}
