
package poorty.controller;

// CONTROLADOR PARA EL TABLERO


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import poorty.view.Selection;
import poorty.model.Game;
import poorty.model.BoxBtn;
import poorty.model.Box;
import poorty.model.PlayerCharacter;
import poorty.model.Character;
import poorty.model.CharacterBtn;
import poorty.view.BoardWindow;


public class BoardController implements ActionListener{
    private BoardWindow boardView;
    private Game game;
    private MainController mainController;
    private DataOutputStream outputStream;
    private ArrayList<PlayerCharacter> playerIcon; 
    private PlayerCharacter thisPlayer;
    private ArrayList<Box> gameBoxes;
    private ArrayList<Box> specialBoxes;
    private ArrayList<Box> initialBoxes;
    private int turnWait = 0;
    private int actualTurn = 0;
    private boolean continuar = true;
    private int totalGameBox = 18;
    private int totalSpecialBox = 8;
    
    
    public static final int BUTTON_SIZE = 100;
    public static final int BOARD_SIZE = 28;
    public static final int PLAYER_HEIGH = 46;
    public static final int PLAYER_WIDTH = 33;
    private BoxBtn[] boxArray= new BoxBtn[BOARD_SIZE];
    

    public BoardController(Game game, BoardWindow boardView ,  MainController mainController) {
        this.boardView = boardView;
        this.game = game;
        this.mainController = mainController;
        this.outputStream = game.getPlayer().getOutputStream();
    }
    
    // METODOS
    
    // se inicializan los compponentes de la pantalla
    public void _init_(){
        
        boardView.getPlayMiniGame().addActionListener(this);
        boardView.getBtnThrowDices().addActionListener(this);
        boardView.getBtnCarcel().addActionListener(this);
        boardView.getPlaySopa().addActionListener(this);
        boardView.getBtnThrowDices().setEnabled(false);
        boardView.getBtnContinueTurn().addActionListener(this);
        boardView.setTitle("Jugador " + game.getPlayer().getPlayerId() + " : " + game.getPlayer().getCharacterName());
        //mainController.showWindow(boardView);
        //movePlayerCharacter();
        // inicializacion de componentes graficos de la ventana  
        loadGameBoxes();
        loadSpecialBoxes();
        
        initplayerCharacter();
        initBoard();
        loadInitialBoxes();
        sendNameServer();
        if(game.getPlayer().isHost()){
            setBoxOption();
        }
        initBackground();
        initialTurn();
        for (int j = 0; j < playerIcon.size() ; j++){
            if(playerIcon.get(j).getCharacterName().equals(game.getPlayer().getCharacterName())){
                thisPlayer = playerIcon.get(j);
                break;
            }  
        }
        
        //boardView.getBackgroundlbl().setIcon(MainController.resizeIcon(game.getBackgrounds().get(0), boardView.getBoardPanel().getWidth(),boardView.getBoardPanel().getHeight()));
    }
    
    // funcion para el listener de los botones y demas
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource().equals(boardView.getPlayMiniGame())){
            // se presiona el btn de jugar el minijuego
            mainController.starMarioCards(-1);}
            
        if(e.getSource().equals(boardView.getBtnThrowDices())){
            // se lanzan los dados 
            boardView.getBtnThrowDices().setEnabled(false);
            sendDicesResult();
                
        }
        
        if(e.getSource().equals(boardView.getBtnContinueTurn())){
            continuarTurno();
        }
        
        if(e.getSource().equals(boardView.getPlaySopa())){
            // se presiona el btn de jugar el minijuego
            JOptionPane.showMessageDialog(boardView, "Obtuviste una estrella, puedes volver a tirar los dados!", "Jugador " + game.getPlayer().getPlayerId(), JOptionPane.INFORMATION_MESSAGE);
            actualTurn = actualTurn-1;
            if(actualTurn == -1)
                actualTurn = playerIcon.size() - 1;
        }
        
        if(e.getSource().equals(boardView.getBtnCarcel())){
            System.out.println("Estoy en la carcel");
            turnWait = 2;
            
        
    } }
    
    public void playerTurn(int turn){
        actualTurn = turn;
        if (turnWait == 0){
            if (game.getPlayer().isThrowDices()){
                boardView.getBtnThrowDices().setEnabled(true);
                }
            else{
                for (int i = 0; i < playerIcon.size(); i++) {
                    if(game.getPlayer().getCharacterName().equals(playerIcon.get(i).getCharacterName())){
                        executeBoxOption(i);
                        break;}
                }    
            } 
            
        }
        else{
            System.out.println("Espero" + turnWait);
            //boardView.getBtnThrowDices().setEnabled(false);
            continuar = true;
            //actualTurn = turn + 1; // se salta el turno
            turnWait--;
            continuarTurno();
        }
       
    }
    
    public void setTextTurn(String text){
        this.boardView.getTurnoLbl().setText(text);
    }
    
    public void continuarTurno(){
        nextTurn(actualTurn);
    }
    
    private void initBoard(){
        
        for (int i = 0; i < boxArray.length; i++) {
            boxArray[i] = new BoxBtn("Juego",i + "", i);
            boardView.setSize(920, 750);
            boardView.getBoardPanel().setSize(920, 750);
            boardView.getBoardPanel().add(boxArray[i].getBoxButton());
            boxArray[i].getBoxButton().setBackground(Color.GRAY);
            
            if (i < 8)
                boxArray[i].setBounds(i*BUTTON_SIZE, 0, BUTTON_SIZE, BUTTON_SIZE);
            else if (i >= 8 && i <= 14){
                boxArray[i].setBounds(8*BUTTON_SIZE, (i-8) * BUTTON_SIZE, BUTTON_SIZE, BUTTON_SIZE);
            }
            else if (i > 14  && i < 22){
                boxArray[i].setBounds(8*BUTTON_SIZE-(i-14)*BUTTON_SIZE,6*BUTTON_SIZE, BUTTON_SIZE, BUTTON_SIZE);
            }
            else if(i >= 22){
                boxArray[i].setBounds(0,6*BUTTON_SIZE -(i-22)*BUTTON_SIZE, BUTTON_SIZE, BUTTON_SIZE);
            }
            boxArray[i].getBoxButton().setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
            //System.out.println((i+1) + ".\t " + buttonArray[i].getBounds().x + "," + buttonArray[i].getBounds().y );
        }
    }
    
    // 
    private void loadGameBoxes(){
        this.gameBoxes = new ArrayList<>();
        ImageIcon charIcon;
        File folder = new File("./src/media/gameBoxes");
        for(File file: folder.listFiles()){
            if(!file.isDirectory()){
                try{
                    charIcon = new ImageIcon(file.getCanonicalPath());
                    charIcon = MainController.resizeIcon(charIcon, BUTTON_SIZE, BUTTON_SIZE);
                    this.gameBoxes.add(new Box(file.getName().replaceFirst("[.][^.]+$", ""), charIcon,2));
                    // el replaceFirst elimina la extension del archivo para obtener simplemente su nombre base
                }catch(IOException e){
                    System.out.println("Error al cargar personaje");
                }
 
            }
        }
        gameBoxes.get(6).setFrequency(3);
        gameBoxes.get(2).setFrequency(3);
        gameBoxes.get(0).setFrequency(3);
        gameBoxes.get(1).setFrequency(3);
    }
    
    private void loadSpecialBoxes(){
        this.specialBoxes = new ArrayList<>();
        ImageIcon charIcon;
        File folder = new File("./src/media/specialBoxes");
        for(File file: folder.listFiles()){
            if(!file.isDirectory()){
                try{
                    charIcon = new ImageIcon(file.getCanonicalPath());
                    charIcon = MainController.resizeIcon(charIcon, BUTTON_SIZE, BUTTON_SIZE);
                    this.specialBoxes.add(new Box(file.getName().replaceFirst("[.][^.]+$", ""), charIcon,1));
                    // el replaceFirst elimina la extension del archivo para obtener simplemente su nombre base
                }catch(IOException e){
                    System.out.println("Error al cargar personaje");
                }
 
            }
        }
        specialBoxes.get(specialBoxes.size() - 1).setFrequency(3); // el tubo tiene 3 de frecuencia
    }
    
    private void loadInitialBoxes(){
        this.initialBoxes = new ArrayList<>();
        ImageIcon charIcon;
        File folder = new File("./src/media/initialBox");
        for(File file: folder.listFiles()){
            if(!file.isDirectory()){
                try{
                    charIcon = new ImageIcon(file.getCanonicalPath());
                    charIcon = MainController.resizeIcon(charIcon, BUTTON_SIZE, BUTTON_SIZE);
                    this.initialBoxes.add(new Box(file.getName().replaceFirst("[.][^.]+$", ""), charIcon,1));
                    // el replaceFirst elimina la extension del archivo para obtener simplemente su nombre base
                }catch(IOException e){
                    System.out.println("Error al cargar personaje");
                }
 
            }
        }
        this.boxArray[0].setBoxIcon(initialBoxes.get(1).getIcon());
        this.boxArray[0].setBoxName(initialBoxes.get(1).getName());
        this.boxArray[boxArray.length - 1].setBoxIcon(initialBoxes.get(0).getIcon());
        this.boxArray[boxArray.length - 1].setBoxName(initialBoxes.get(0).getName());
    }
    
    
    private void setBoxOption(){
        Random rand = new Random();
        
        for(int i = 1; i < boxArray.length - 1; i++){
            int random = rand.nextInt(2);
            if (random == 0){
                setGameBox(i);
            }
            else if(random == 1){
                setSpecialBox(i);
            }
            sendBox(i);
            System.out.println(i + " " + boxArray[i].getBoxName());
        }
    }
    
    private void setSpecialBox(int i){
        Random rand = new Random();    
        while(true){
            int random1 = rand.nextInt(specialBoxes.size());
            if (specialBoxes.get(random1).getFrequency() > 0){
                boxArray[i].setBoxIcon(specialBoxes.get(random1).getIcon());
                boxArray[i].setBoxName(specialBoxes.get(random1).getName());
                specialBoxes.get(random1).decreaseFrequency();
                totalSpecialBox--;
                break;}
            else if (totalSpecialBox == 0){
                setGameBox(i);
                break;
            }
        } 
    }
    
    private void setGameBox(int i){
        Random rand = new Random();
        while(true){
            int random1 = rand.nextInt(gameBoxes.size());
            if (gameBoxes.get(random1).getFrequency() > 0){
                boxArray[i].setBoxIcon(gameBoxes.get(random1).getIcon());
                boxArray[i].setBoxName(gameBoxes.get(random1).getName());
                gameBoxes.get(random1).decreaseFrequency();
                totalGameBox--;
                break;}
            else if (totalGameBox == 0){
                setSpecialBox(i);
                break;
            }
        } 
    }
    
    public void updateBox(int i, String boxName){
        for (Box spebox : specialBoxes){
            if(spebox.getName().equals(boxName)){
                boxArray[i].setBoxIcon(spebox.getIcon());
                boxArray[i].setBoxName(boxName);
                break;
            }
        }
        
        for(Box gamebox : gameBoxes){
            if(gamebox.getName().equals(boxName)){
                boxArray[i].setBoxIcon(gamebox.getIcon());
                boxArray[i].setBoxName(boxName);
                break;
            }
        }
    }
    
    
    
    private void initplayerCharacter(){
        ArrayList<CharacterBtn> charactersbtns = Selection.characterBtns;
        ArrayList<Character> charactersIcons = game.getCharactersIcons(); // figura de los jugadores
        playerIcon = new ArrayList<>();
        for (int i = 0; i < charactersbtns.size(); i++) {
            if (charactersbtns.get(i).isSelected()){
                for (Character charactersIcon : charactersIcons) {
                    if(charactersbtns.get(i).getCharacterName().equals(charactersIcon.getName())){
                        System.out.println("Se a??ade el caracter");
                        playerIcon.add(new PlayerCharacter(PLAYER_WIDTH, PLAYER_HEIGH, charactersIcons.get(i)));
                        
                    }
                }
                
            }
        }
        
        //dibujarlos
        for (int i = 0; i < playerIcon.size(); i++) {
            if (i < 3){
                boardView.getBoardPanel().add(playerIcon.get(i));
                playerIcon.get(i).updateBounds(i *PLAYER_WIDTH, 0);
            }
            else{
                boardView.getBoardPanel().add(playerIcon.get(i));
                playerIcon.get(i).updateBounds((i%3) *PLAYER_WIDTH, PLAYER_HEIGH + 8 );
            }
        }
        //playerButton = boardView.getComponent(8);
    }
    
    public void initBackground(){
        JLabel  background = new JLabel(MainController.resizeIcon(game.getBackgrounds().get(4), boardView.getBoardPanel().getWidth(),boardView.getBoardPanel().getHeight())); 
        background.setBounds(0, 0,boardView.getBoardPanel().getWidth(),boardView.getBoardPanel().getHeight());
        boardView.getBoardPanel().add(background);
    }
    
    public void movePlayerCharacter(int value){
        
        for (int i = 0; i < playerIcon.size(); i++) {
            if(game.getPlayer().getCharacterName().equals(playerIcon.get(i).getCharacterName())){
                boxArray[playerIcon.get(i).getCasillaActual()].getBoxButton().setBorder(BorderFactory.createLineBorder(Color.RED, 4));
                int casilla = playerIcon.get(i).getCasillaActual() + value;
                        
                
                if (casilla >= BOARD_SIZE)
                    casilla = (BOARD_SIZE - 2) - (casilla - BOARD_SIZE - 1);
                
                
                
                playerIcon.get(i).setCasillaActual(casilla);
                System.out.println("Casilla: " + casilla );
                movePlayer(i,casilla);
                /*try {
                    sleep(4000);
                    //espera dos segundos y luego ejecuta el proceso de la casilla
                    
                } catch (InterruptedException ex) {
                    Logger.getLogger(BoardController.class.getName()).log(Level.SEVERE, null, ex);
                }*/
                
                executeBoxOption(i);
                break;
            }
            
        }   
    } 
    
    public void movePlayer(int i,int casilla){
        for(int j = playerIcon.get(i).getCasillaActual() ; j < casilla; j++ ){
            boxArray[j].getBoxButton().setBorder(BorderFactory.createLineBorder(Color.RED, 4));
        }
        boxArray[casilla].getBoxButton().setBorder(BorderFactory.createLineBorder(Color.BLUE, 4));

        playerIcon.get(i).setCasillaActual(casilla);

        int x = playerIcon.get(i).getX();
        int y = playerIcon.get(i).getY();
        System.out.println("Se mueve el jugador  Casilla: " + casilla );
        //System.out.println("x= " + x + " y= " + y);
        if (casilla <= 8){
            playerIcon.get(i).updateBounds((x % BUTTON_SIZE) + BUTTON_SIZE * casilla, y % BUTTON_SIZE); 
        }
        else if (casilla > 8 && casilla < 14 ){
            playerIcon.get(i).updateBounds((x % BUTTON_SIZE) + BUTTON_SIZE * 8 , (y % BUTTON_SIZE) + BUTTON_SIZE * (casilla - 8));
        }
        else if (casilla >= 14 && casilla < 22 ){
            x = (x % BUTTON_SIZE) + BUTTON_SIZE * 8; 
            playerIcon.get(i).updateBounds(x - (BUTTON_SIZE * (casilla - 14)) , (y % BUTTON_SIZE) + BUTTON_SIZE  * 6);
        }
        else if (casilla >= 22){
            y = (y % BUTTON_SIZE) + BUTTON_SIZE * 6; 
            playerIcon.get(i).updateBounds(x % BUTTON_SIZE , y - (BUTTON_SIZE*(casilla - 22)));
        }
        characterMoved(i);
        if (casilla == BOARD_SIZE -1)
            winner();
        
    }
    
    public void executeBoxOption(int i){
        int casilla = playerIcon.get(i).getCasillaActual();
        switch(boxArray[casilla].getBoxName()){
            case "Jail":
                JOptionPane.showMessageDialog(boardView, "Ca??ste en la carcel, pierdes 2 turnos", "Jugador " + game.getPlayer().getPlayerId(), JOptionPane.INFORMATION_MESSAGE);
                turnWait += 2;
                continuarTurno();
                break;
            case "Gato":
                JOptionPane.showMessageDialog(boardView, "Jugar??s el juego del gato", "Jugador " + game.getPlayer().getPlayerId(), JOptionPane.INFORMATION_MESSAGE);
                mainController.startCatMiniGame(-1);
                break;
            case "LettersSoup":
                JOptionPane.showMessageDialog(boardView, "Jugar??s la sopa de letras", "Jugador " + game.getPlayer().getPlayerId(), JOptionPane.INFORMATION_MESSAGE);
                mainController.startSoupMiniGame();
                break;
            case "Tube":
                while(true){
                    casilla = (casilla+1) % BOARD_SIZE;
                    if(boxArray[casilla].getBoxName().equals("Tube")){
                        JOptionPane.showMessageDialog(boardView, "Ca??ste en tubo avanzas hasta la casilla " + casilla , "Jugador " + game.getPlayer().getPlayerId(), JOptionPane.INFORMATION_MESSAGE);
                        movePlayer(i,casilla);
                        break;
                    }
                }
                continuarTurno();
                break;
            case "Star": // puede volver a tirar
                JOptionPane.showMessageDialog(boardView, "Obtuviste una estrella, puedes volver a tirar los dados!", "Jugador " + game.getPlayer().getPlayerId(), JOptionPane.INFORMATION_MESSAGE);
                actualTurn = actualTurn-1;
                if(actualTurn == -1)
                    actualTurn = playerIcon.size() - 1;
                System.out.println(actualTurn);
                continuarTurno();
                break;
            case "FireFlower":
                JOptionPane.showMessageDialog(boardView, "Podr??s enviar una flor de fuego a otro jugador", "Jugador " + game.getPlayer().getPlayerId(), JOptionPane.INFORMATION_MESSAGE);
                mainController.startSelectOpponent(1);
                for (int j = 0; j < playerIcon.size() ; j++){
                    if(playerIcon.get(j).getCharacterName().equals(game.getPlayer().getCharacterName())){
                    }
                    else{
                        mainController.getOpponentController().addCharacter(playerIcon.get(j).getPlayerCharacter());
                    }    
                }
                mainController.changeSelectOpponentW();
                break;
            case "IceFlower" :
                JOptionPane.showMessageDialog(boardView, "Podras enviar una flor de hielo a otro jugador", "Jugador " + game.getPlayer().getPlayerId(), JOptionPane.INFORMATION_MESSAGE);
                mainController.startSelectOpponent(2);
                for (int j = 0; j < playerIcon.size() ; j++){
                    if(playerIcon.get(j).getCharacterName().equals(game.getPlayer().getCharacterName())){
                    
                    }
                    else{
                        
                        mainController.getOpponentController().addCharacter(playerIcon.get(j).getPlayerCharacter());
                    }    
                }
                mainController.changeSelectOpponentW();
                break;
            case "Tail":
                JOptionPane.showMessageDialog(boardView, "Caiste en la cola", "Jugador " + game.getPlayer().getPlayerId(), JOptionPane.INFORMATION_MESSAGE);
                mainController.startSelectBox();
                int casillaActual = 0;
                for (int j = 0; j < playerIcon.size() ; j++){
                    if(playerIcon.get(j).getCharacterName().equals(game.getPlayer().getCharacterName())){
                        casillaActual = playerIcon.get(j).getCasillaActual();
                        break;
                    }  
                }
                int casillaInicial = casillaActual - 3;
                
                if (casillaInicial < 1)
                    casillaInicial = 1;
                
                for (int j = casillaInicial; j <= casillaActual + 3; j++) {
                    if(j != casillaActual)
                        mainController.getSelectBoxController().addBox(boxArray[j]);
                }
                
                mainController.changeSelectBoxW();
                
                break;
                
            case "MemoryMario":
                JOptionPane.showMessageDialog(boardView, "Jugar??s a Memory Mario", "Jugador " + game.getPlayer().getPlayerId(), JOptionPane.INFORMATION_MESSAGE);
                mainController.startMemoryMiniGame(-1);
                break;
                
            case "MemoryPath":
                JOptionPane.showMessageDialog(boardView, "Jugar??s a MemoryPath", "Jugador " + game.getPlayer().getPlayerId(), JOptionPane.INFORMATION_MESSAGE);
                mainController.startMemoryPath(playerIcon.get(i).getIcon());
                break;
            
            case "MarioCards":
                JOptionPane.showMessageDialog(boardView, "Jugar??s a MarioCards", "Jugador " + game.getPlayer().getPlayerId(), JOptionPane.INFORMATION_MESSAGE);
                mainController.starMarioCards(-1);
                break;
            
            case "GuesWho":
                JOptionPane.showMessageDialog(boardView, "Jugar??s a GuesWho?", "Jugador " + game.getPlayer().getPlayerId(), JOptionPane.INFORMATION_MESSAGE);
                mainController.startGuessWhoMiniGame();
                break;
                
            case "CollectTheCoins":
                JOptionPane.showMessageDialog(boardView, "Jugar??s a Collect the coins", "Jugador " + game.getPlayer().getPlayerId(), JOptionPane.INFORMATION_MESSAGE);
                mainController.startCollectCoinsMiniGame();
                break;
                
            default:
                continuarTurno();
                break;
                
        }
        //contin??a al siguiente turno
    }
    
    public void fireFlower(String characterName){
        JOptionPane.showMessageDialog(boardView, "Un jugador te envi?? una flor de fuego vuelves a la casilla 0", "Jugador " + game.getPlayer().getPlayerId(), JOptionPane.INFORMATION_MESSAGE);
        for (int i = 0; i < playerIcon.size() ; i++){
                if(playerIcon.get(i).getCharacterName().equals(characterName)){
                    playerIcon.get(i).setCasillaActual(0);
                    movePlayer(i,playerIcon.get(i).getCasillaActual());
                    break;
                }
        }
    }
    
    public void winner(){
        JOptionPane.showMessageDialog(boardView, "Has ganado el juego, Felicidades", "Jugador " + game.getPlayer().getPlayerId(), JOptionPane.INFORMATION_MESSAGE);
        sendWinner();
    }
    
    public void iceFlower(String characterName){
        turnWait += 2;
        JOptionPane.showMessageDialog(boardView, "Un jugador te ha enviado una Flor congelante, no podr??s tirar los dados por 2 turnos", "Jugador " + game.getPlayer().getPlayerId(), JOptionPane.INFORMATION_MESSAGE);
        
    }
    
    public void tail(int casilla){
        for (int i = 0; i < playerIcon.size() ; i++){
                if(playerIcon.get(i).getCharacterName().equals(game.getPlayer().getCharacterName())){
                    playerIcon.get(i).setCasillaActual(casilla);
                    movePlayer(i,playerIcon.get(i).getCasillaActual());
                    executeBoxOption(i);
                    break;
                }
        }
    }
    
    public void updateCharacters(){
        for (int i = 0; i < playerIcon.size(); i++) {
            int x = playerIcon.get(i).getx();
            int y = playerIcon.get(i).gety();
            playerIcon.get(i).setBounds( x, y , PLAYER_WIDTH, PLAYER_HEIGH);
            }
    }
    
    private void sendDicesResult(){
        int contador = 0;
        while(contador <= 10){
            boardView.getLblDice1().setText((int)(Math.random()*6+1) + "");
            boardView.getLblDice2().setText((int)(Math.random()*6+1) + "");
            contador++;
        }
        
        int Dice1 = (int) (Math.random() * 6 + 1);
        int Dice2 = (int) (Math.random() * 6 + 1);
        
        if(Dice1 == 6 && Dice2 == 6){
            boardView.getLblDice1().setText("X");
            boardView.getLblDice2().setText("X");
            JOptionPane.showMessageDialog(boardView, "Obtuviste 2 X, pierdes 2 turnos", "Jugador " + game.getPlayer().getPlayerId(), JOptionPane.INFORMATION_MESSAGE);
            turnWait += 2;
            continuarTurno();
        } else if(Dice1 == 6){
            boardView.getLblDice1().setText("X");
            boardView.getLblDice2().setText(Dice2 + "");
            JOptionPane.showMessageDialog(boardView, "Obtuviste 1 X, pierdes 1 turno", "Jugador " + game.getPlayer().getPlayerId(), JOptionPane.INFORMATION_MESSAGE);
            turnWait += 1;
            continuarTurno();
        } else if(Dice2 == 6){
            boardView.getLblDice1().setText(Dice1 + "");
            boardView.getLblDice2().setText("X");
            JOptionPane.showMessageDialog(boardView, "Obtuviste 1 X, pierdes 1 turno", "Jugador " + game.getPlayer().getPlayerId(), JOptionPane.INFORMATION_MESSAGE);
            turnWait += 1;
            continuarTurno();
        } else{
            boardView.getLblDice1().setText(Dice1 + "");
            boardView.getLblDice2().setText(Dice2 + "");
            movePlayerCharacter(Dice1 + Dice2);
        }
            
        
       
                
        
        
       
        
        /*try {
            int dices = game.throwDices(boardView.getLblDice1(), boardView.getLblDice2());
            System.out.println("Dices :" + dices);
            //sleep(4000); // espera a que salga el resultado
            movePlayerCharacter(dices);
            /*while(true){
                if (!game.getThrowDices().isRunning()){
                    
                    break; }
            }
            
        } catch (InterruptedException ex) {
            Logger.getLogger(BoardController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
                
           
    }
    
    public ArrayList<PlayerCharacter> getPlayerIcon() {
        return playerIcon;
    }
    
    // funciones para la conexion con el servidor
    
    public void sendWinner(){
        try {
            outputStream.writeInt(0); // abre el server help
            outputStream.writeInt(2); // le indica que cierre el juego
            
        } catch (IOException ex) {
            Logger.getLogger(BoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    public void characterMoved(int i){
        try {
                outputStream.writeInt(5); // opcion de tablero 
                outputStream.writeInt(1); // 1
                outputStream.writeInt(i); // env??a el indice del personaje
                outputStream.writeInt(playerIcon.get(i).getX()); // env??a la nueva coordenada x
                outputStream.writeInt(playerIcon.get(i).getY()); // env??a la nueva coordenada y 
                
            } catch (IOException ex) {
                        Logger.getLogger(LobbyController.class.getName()).log(Level.SEVERE, null, ex);
            } 
    }
    
    public void nextTurn(int turn){
        try {
                outputStream.writeInt(5); // opcion de tablero 
                outputStream.writeInt(2); // 1
                outputStream.writeInt(turn);
            } catch (IOException ex) {
                        Logger.getLogger(LobbyController.class.getName()).log(Level.SEVERE, null, ex);
            } 
    }
    
    public void initialTurn(){
        try {
                outputStream.writeInt(5); // opcion de tablero 
                outputStream.writeInt(3); // 1
            } catch (IOException ex) {
                        Logger.getLogger(LobbyController.class.getName()).log(Level.SEVERE, null, ex);
            } 
    }
    
    public void sendBox(int i){
        try {
                outputStream.writeInt(5); // opcion de tablero 
                outputStream.writeInt(4); // opcion de enviar casilla
                outputStream.writeInt(i); // env??a el indice de casilla
                outputStream.writeUTF(boxArray[i].getBoxName()); //env??a el nombre de la casilla
            } catch (IOException ex) {
                        Logger.getLogger(LobbyController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public void sendNameServer(){
        try {
                outputStream.writeInt(2); // opcion de seleccionar 
                outputStream.writeInt(3); // opcion de enviar casilla
                outputStream.writeInt(game.getPlayer().getPlayerId()); // env??a el indice de casilla
                outputStream.writeUTF(game.getPlayer().getCharacterName()); //env??a el nombre de la casilla
            } catch (IOException ex) {
                        Logger.getLogger(LobbyController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
    

    
    
    

