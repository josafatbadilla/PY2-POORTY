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
                case 0: // opciones generales o varias (no pertenecen a ningun juego o pantalla)
                    serverHelper(inputStream.readInt());
                    break;
                case 1: // opciones del lobby
                    lobby(inputStream.readInt());
                    break;
                case 2: // opciones de la seleccion de personajes
                    characterSelection(inputStream.readInt());
                    break;
                case 3: // opciones para la seleccion del turno
                    turnSelection(inputStream.readInt());
                    break;
                case 4: // opciones para el minijuego del gato
                    miniGameCat(inputStream.readInt());
                    break;
                case 5: // movimiento tablero
                    boardMoves(inputStream.readInt());
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
    
    // opciones del servidor y varias
    private void serverHelper(int option) throws IOException{
        switch(option){
            case 1: // designar un enemigo al jugador e iniciar el juego para el enemigo
                int opc1 = inputStream.readInt(); // opcion del juego
                int opc2 = inputStream.readInt(); // opcion dentro de las opciones del juego
                ArrayList<Integer> enemiesId = new ArrayList();
                for(int i = 0; i < players.size(); i++){
                    if(players.get(i).playerId != this.playerId){
                        enemiesId.add(players.get(i).playerId);
                    }
                }
                
                if(enemiesId.size() > 0){
                    int randomEnemy = (int)(Math.random()*enemiesId.size());
                    // enviamos el enemigo a este jugador que lo pidio
                    outputStream.writeInt(opc1); // opc del juego
                    outputStream.writeInt(opc2); // subopcion del juego
                    outputStream.writeInt(enemiesId.get(randomEnemy)); // id del enemigo
                    
                    // se le avisa al enemigo
                    for(int i = 0; i < players.size(); i++){
                        if(players.get(i).playerId == enemiesId.get(randomEnemy)){
                            players.get(i).outputStream.writeInt(opc1); // opcion del juego
                            players.get(i).outputStream.writeInt(2); // inicializacion del juego
                            players.get(i).outputStream.writeInt(this.playerId); // aviso que soy su enemigo
                        }
                    }

                }else{
                    System.out.println("No hay enemigos disposibles");
                }

                break;
            case 2:

                break;
            case 3:

                break;
        }
    
    }
    
    
    private void lobby(int option) throws IOException{
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
                // se pasa a todos los jugadores de pantalla a la seleccion de turno
                int turnWindow  = inputStream.readInt();
                for(int i = 0; i < players.size(); i++){
                    players.get(i).outputStream.writeInt(2);
                    players.get(i).outputStream.writeInt(2);
                    players.get(i).outputStream.writeInt(turnWindow);
                }
                break;
            default:
                System.out.println("Opcion inexistente");
        }
    }
    
    
    private void turnSelection(int option) throws IOException{
        switch(option){
            case 1: // se recibe un numero aleatorio del jugador
                int playerNum = inputStream.readInt();
                // ingresa el turno de este jugador en el arreglo de turnos con la diferencia
                server.insertPlayerTurn(new Turn(this.playerId, Math.abs(server.RANDOM_TURN_NUM - playerNum)), 1);
                
                // SE ENVIA CUAL ERA EL NUMERO
                outputStream.writeInt(3); // seleccion de turno
                outputStream.writeInt(1); // muestra el numero que salio
                outputStream.writeInt(server.RANDOM_TURN_NUM);
                break;
            case 2: // el jugador tiro los dados
                int dicesResult = inputStream.readInt(); // lee el resultado de los dados
                server.insertPlayerTurn(new Turn(this.playerId, Math.abs(dicesResult)), 2);
                break;
            case 3: //el host pasa al tablero desde el seleccion mediante numero random
                // se pasa a todos los jugadores de pantalla a la seleccion de turno
                for(int i = 0; i < players.size(); i++){
                    players.get(i).outputStream.writeInt(3);
                    players.get(i).outputStream.writeInt(3);
                }
                break;
            case 4:
                // se pasa al tablero desde la seleccion de turno de lanzar dados
                // se pasa a todos los jugadores de pantalla a la seleccion de turno
                for(int i = 0; i < players.size(); i++){
                    players.get(i).outputStream.writeInt(3);
                    players.get(i).outputStream.writeInt(4);
                }
                break;
            default:
                System.out.println("Opcion inexistente");
        }
    }

// Metodos para correr los juegos
    private void miniGameCat(int option) throws IOException{
        
        switch(option){
        case 0: //

            break;
        case 1: // enviar una jugada a mi enemigo
            int enemyId = inputStream.readInt(); // el id del contrincante a enviar la jugada
            for(int i = 0; i < players.size(); i++){
                if(players.get(i).playerId == enemyId){
                    players.get(i).outputStream.writeInt(4); // opc del gato
                    players.get(i).outputStream.writeInt(3); // opc de recibir jugada
                    players.get(i).outputStream.writeInt(inputStream.readInt()); // se envia la fila
                    players.get(i).outputStream.writeInt(inputStream.readInt()); // se envia la columna
                }

            } 
                
            break;
        case 2: // cerrar el juego de todos
            int vsPlayerId = inputStream.readInt(); // el id del contrincante para cerrar el juego 
            // se cierra el juego de este jugador (minigamehost)
            outputStream.writeInt(4);
            outputStream.writeInt(4); // opc de cerrar juego
            
            for(int i = 0; i < players.size(); i++){
                if(players.get(i).playerId == vsPlayerId){
                    players.get(i).outputStream.writeInt(4); // opc del gato
                    players.get(i).outputStream.writeInt(4); // opc de cerrar juego
                }

            } 

            break;
        }
        
    }
    
    public void boardMoves (int option) throws IOException {
           
        switch(option){
            case 1: // se envía el número del caracter (indice)
                for(int i = 0; i < players.size(); i++){
                    players.get(i).outputStream.writeInt(5);
                    players.get(i).outputStream.writeInt(1);
                    players.get(i).outputStream.writeInt(inputStream.readInt());
                    players.get(i).outputStream.writeInt(inputStream.readInt());
                    players.get(i).outputStream.writeInt(inputStream.readInt());
                }
                break;
                
        }
         
    }
    
    
}
