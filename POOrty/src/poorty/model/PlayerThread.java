
package poorty.model;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import poorty.controller.MainController;




public class PlayerThread extends Thread{
    
    DataInputStream inputStream;
    Player player;
    MainController mainController;
    
    public PlayerThread(DataInputStream inputStream, Player player, MainController mainController){
        this.inputStream = inputStream;
        this.player = player;
        this.mainController = mainController;
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
                        case 1: // lobby
                            lobby(inputStream.readInt());
                            break;
                        case 2: // seleccion de personaje
                            characterSelection(inputStream.readInt());
                            break;
                        case 3: // seleccion del turno
                            turnSelection(inputStream.readInt());
                            break;
                        case 4: // minijuego del gato
                            miniGameCat(inputStream.readInt());
                            break;
                        case 5: // movimiento tablero
                            boardMoves(inputStream.readInt());
                            break;
                    }



            }
        
        }catch(IOException e){
            System.out.println("Error en la comunicaci�n "+"Informaci�n para el usuario");
        }
        
        // se desconecta del servidor
        System.out.println("se desconecto el servidor");
        
    }
    
    // acciones para el lobby
    public void lobby(int option){
        try {
            switch(option){
                case 0: // se pasa a la seleccion de personajes
                    mainController.characterSelectWindow();
                    break;
                case 1: // imprimir la agregacion del nuevo jugador
                    // se imprime el nombre
                    int playerId = inputStream.readInt();
                    boolean host = inputStream.readBoolean();
                    mainController.getLobbyController().addLobbyPlayer(playerId, host);
                    break;

            }
        } catch (IOException ex) {
            Logger.getLogger(PlayerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    // acciones para la seleccion de personajes
    public void characterSelection(int option){
        try {
            switch(option){
                case 1: // se selecciona un personaje
                    String unselectedCharacter = inputStream.readUTF();
                    String selectedCharacter = inputStream.readUTF();
                    
                    mainController.getSelectionController().updateCharacterButtons(unselectedCharacter, selectedCharacter);
                    break;
                case 2:
                    // cambio de pantalla para la seleccion de los turnos 
                    mainController.selectTurnWindow(inputStream.readInt());

                    break;

            }
        } catch (IOException ex) {
            Logger.getLogger(PlayerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    // acciones para la selecicon del turno
    public void turnSelection(int option){
        try {
            switch(option){
                case 1: // se recibe el numero random que salio
                    mainController.getRandomTurnController().printTheRandomNumber(inputStream.readInt());
                    break;
                case 2: // el jugador tira los dados
                       // no se hace nada en consecuencia

                    break;
                case 3: // se pasa al tablero desde la seleccion de turno por numero random
                    mainController.startBoardWindow(1);
                    break;
                case 4:
                    // se pasa al tablero desde la seleccion de turno por tira de dados
                    mainController.startBoardWindow(2);
                    break;
            }
        } catch (IOException ex) {
            Logger.getLogger(PlayerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    // acciones para el minijuego del gato
    public void miniGameCat(int option) throws IOException{
        switch(option){
            case 1: // se le asigna el enemigo a este jugador en el minijuego del gato
                mainController.getCatGameController().setEnemyId(inputStream.readInt());

                break;
            case 2: // me avisan que soy su enemigo para el juego del gato
                mainController.startCatMiniGame(inputStream.readInt());

                break;
            case 3: // se recibe una jugada de mi enemigo 
                int playedRow = inputStream.readInt();
                int playedColumn = inputStream.readInt();
                mainController.getCatGameController().recievePlay(playedRow, playedColumn); // se actualiza la jugada
               
                break;
            case 4:
                // cerrar el juego y volver al tablero
                mainController.closeMiniGame(4); // cerrar el juego del gato
                break;
        }
    }
    
    public void boardMoves (int option) {
        try{    
            switch(option){
                case 1: // se envía el número del caracter (indice)
                    System.out.println("Si llega");
                    int i = inputStream.readInt();
                    int x = inputStream.readInt();
                    int y = inputStream.readInt();
                    System.out.println("i : " + i + " x: " + x + " y: " + y);
                    mainController.getBoardController().getPlayerIcon().get(i).updateBounds(x, y);
                    
                break;
            }
        } catch (IOException ex) {
            Logger.getLogger(PlayerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
