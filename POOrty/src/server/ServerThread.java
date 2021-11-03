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
        this.players.add(this); // se agrega a si mismo al array de jugadores
        this.start();
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
                case 1: // opciones del lobby
                    lobby(inputStream.readInt());
                    break;
                case 2: // opciones de la seleccion de personajes
                    characterSelection(inputStream.readInt());
                    break;
                }
                
            }
            
        } catch (IOException e) {
            System.out.println("Se termino la conexion");
            
        }
        
        System.out.println("Se removio al cliente");
        
        try{
          socketPlayer.close();
    	}catch(Exception et){
            System.out.println("No se puedo cerrar conexion");
        }   
        


    }
    
    private void lobby(int option) throws IOException{
        //accion para el juego del gato
        switch(option){
            case 0: // el host inicia el juego desde el lobby
                server.hostStartGame();
                // pasa a todos los jugadores de ventana
                for(int i = 0; i < players.size(); i++){
                    players.get(i).outputStream.writeInt(1);
                    players.get(i).outputStream.writeInt(0);
                }
                break;
            case 1: // se avisa la conexion de este jugador a todos los demas
                for(int i = 0; i < players.size(); i++){
                    players.get(i).outputStream.writeInt(1);
                    players.get(i).outputStream.writeInt(1);
                    players.get(i).outputStream.writeInt(this.playerId);
                    players.get(i).outputStream.writeBoolean(this.host);
                }

                break;
            case 2:

                break;
        }
    }
    
    
    private void characterSelection(int option) throws IOException{
        
        switch(option){
            case 1: // se selecciona un personaje
                String unselectedCharacter = inputStream.readUTF();
                String selectedCharacter = inputStream.readUTF();

                for(int i = 0; i < players.size(); i++){
                    players.get(i).outputStream.writeInt(2); // opcion para la seleccion de personaje
                    players.get(i).outputStream.writeInt(1); // seleccion de personaje
                    players.get(i).outputStream.writeUTF(unselectedCharacter);
                    players.get(i).outputStream.writeUTF(selectedCharacter);
                }
                break;
            case 2:
                // se pasa a todos los jugadores de pantalla
                for(int i = 0; i < players.size(); i++){
                    players.get(i).outputStream.writeInt(2);
                    players.get(i).outputStream.writeInt(2);
                }
                break;
            default:
                System.out.println("Opcion inexistente");
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
