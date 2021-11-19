/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poorty.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

import poorty.model.Box;
import poorty.model.Game;
import poorty.model.MarioCard;
import poorty.model.MemoryCard;
import poorty.view.MarioCardsWindow;
import poorty.view.MemoryWindow;

/**
 *
 * @author josa
 */
public class MarioCardsController implements  ActionListener{
    private MarioCardsWindow marioCardview;
    private Game game;
    private MainController mainController;
    private DataOutputStream outputStream;
    private ObjectOutputStream objOutputStream;
    private ArrayList<Integer> selectedCardsValues;
    private ArrayList<Integer> playersID;
    private ArrayList<MarioCard> cards;
    private MarioCard carta;
    private boolean playerPlaying;
    private int playerIdwinner;
    
    public MarioCardsController(MarioCardsWindow marioCardview, Game game, MainController mainController, int ID) {
        this.marioCardview = marioCardview;
        this.game = game;
        this.mainController = mainController;
        this.cards = new ArrayList<>();
        this.selectedCardsValues = new ArrayList<>();
        this.playerPlaying = ID == -1;
        this.playersID = new ArrayList<>();
        this.outputStream = game.getPlayer().getOutputStream();
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(marioCardview.getBtnCard())){
            mostrarCarta();
            choiceMax();
            finishGame();
               
        }
        
        else if(e.getSource().equals(marioCardview.getBtnContinue())){
            closeMiniGame();
        }
    }
    
    public void _init_(){
        marioCardview.visibility(true);
        marioCardview.getBtnCard().addActionListener(this);
        marioCardview.getBtnContinue().addActionListener(this);
        
        marioCardview.setTitle("Jugador " + game.getPlayer().getPlayerId());
        
        marioCardview.getBtnContinue().setEnabled(false);
        
        
        if(this.playerPlaying){
            mainController.getOtherPlayers(game.getPlayer().getPlayerId());
            
        }
        
        loadCardsIcons();
        initCarta();
        selectCard();
        
        
        
    }
    
    private void loadCardsIcons(){
        this.cards = new ArrayList<>();
        //carga las cartas
        ImageIcon charIcon;
        File folder = new File("./src/media/cartas");
        for(File file: folder.listFiles()){
            if(!file.isDirectory()){
                try{
                    charIcon = new ImageIcon(file.getCanonicalPath());
                    charIcon = MainController.resizeIcon(charIcon, 300, 400);
                    this.cards.add(new MarioCard(Integer.parseInt(file.getName().replaceFirst("[.][^.]+$", "")), charIcon));
                    // el replaceFirst elimina la extension del archivo para obtener simplemente su nombre base
                }catch(IOException e){
                    System.out.println("Error al cargar personaje");
                }
 
            }
        } 
    }
    
    private void initCarta(){
        carta = cards.get(0);
        cards.get(0).setIsSelected(true);
        marioCardview.getBtnCard().setBounds(50, 50, 300, 400);
        marioCardview.getBtnCard().setIcon(cards.get(0).getCardIcon());
    }
    
    private void selectCard(){
        Random ran = new Random();
        int j = 0;
        while(carta.isSelected()){
            j = ran.nextInt((cards.size()-1)) + 1;// seleciona cualquiera menos la cero
            carta = cards.get(j);} 
        System.out.println(carta.getValue() + " ");
        sendCard(j, cards.get(j).getValue());//enviar que la carta ya está seleccionada
    }
    
    public void mostrarCarta(){
        marioCardview.getBtnCard().setIcon(carta.getCardIcon());  
    }

    public void setCards(ArrayList<MarioCard> cards) {
        this.cards = cards;
    }
    
    public void choiceMax(){
        System.out.print("selectCardsValues: ");
        for (int i : selectedCardsValues){
            System.out.print(i + " ");
        }
        System.out.print("/n PlayersID: ");
        for (int i : playersID){
            System.out.print(i + " ");
        }
        System.out.println("");
        
        int max = Collections.max(selectedCardsValues); // obtiene el valor mayor
        System.out.println("Valor mayor = " + max);
        int i = selectedCardsValues.indexOf(max);
        playerIdwinner = playersID.get(i);
        
        if(this.playerPlaying){
            game.getPlayer().setThrowDices(playerIdwinner == game.getPlayer().getPlayerId());
            marioCardview.getBtnContinue().setEnabled(true);
        }
        
        
        
    }
    
    public void finishGame(){
        if (playerIdwinner == game.getPlayer().getPlayerId())
            marioCardview.getLblWinner().setText("Has ganado...");
        else{
            marioCardview.getLblWinner().setText("Has Perdido...");
            }
    }
    
    
    
    // conexión con el servidor
    
    public void sendCard(int i, int value){
        try {
            outputStream.writeInt(7); // opcion de MarioCards
            outputStream.writeInt(2); // opcion de recibir carta
            outputStream.writeInt(i); // envía el número de casilla
            outputStream.writeInt(value); // envía el valor de la carta
            outputStream.writeInt(game.getPlayer().getPlayerId()); // envía el id del jugador
            
            
        }
        catch (IOException ex) {
            Logger.getLogger(MarioCardsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    private void closeMiniGame(){
        if (playerPlaying){
            mainController.getBoardController().continuarTurno();
        }
        
        
        try {
            outputStream.writeInt(7); // opcion de MarioCards
            outputStream.writeInt(3); // opcion de cerrar el juego
        } catch (IOException ex) {
            Logger.getLogger(MarioCardsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public ArrayList<Integer> getSelectedCardsValues() {
        return selectedCardsValues;
    }

    public ArrayList<Integer> getPlayersID() {
        return playersID;
    }

    public ArrayList<MarioCard> getCards() {
        return cards;
    }
    
    
    
    
    
}


