/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poorty.controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import poorty.model.Game;
import poorty.model.Character;
import poorty.model.CharacterBtn;
import poorty.view.OpponentSelectionWindow;
import poorty.view.Selection;
/**
 *
 * @author josa
 */
public class OpponentSelectionController implements ActionListener{
    
    OpponentSelectionWindow opponentWindow;
    private Game game;
    private MainController mainController;
    private DataOutputStream outputStream;
    private ArrayList<CharacterBtn> characterBtns;
    private int x = 20;
    private int option;
    

    public OpponentSelectionController(OpponentSelectionWindow opponentWindow, Game game, MainController mainController, int option) {
        this.opponentWindow = opponentWindow;
        this.game = game;
        this.mainController = mainController;
        this.outputStream = game.getPlayer().getOutputStream();
        this.characterBtns = new ArrayList<>();
        this.option = option;
        
    }
    
    public void _init_(){
        opponentWindow.getBtnContinue().addActionListener(this);
        if (option == 1){
            opponentWindow.getNameLbl().setText("Fire Flower");
            opponentWindow.setTitle("Jugador " + game.getPlayer().getPlayerId() + "| Fire Flower");
        } else{
            opponentWindow.getNameLbl().setText("Ice Flower");
            opponentWindow.setTitle("Jugador " + game.getPlayer().getPlayerId() + " | Ice Flower");
        }
        
        
    }
    
    
    public void addCharacter(Character playerCharacter){
        CharacterBtn btn = new CharacterBtn(playerCharacter);
        characterBtns.add(btn);
        btn.setBounds(x, 90, Selection.CHARWIDTH, Selection.CHARHEIGH);
        
        this.x += Selection.CHARWIDTH + 10;
        opponentWindow.getSelectionPanel().add(btn);
        
        System.out.println("Se agrega el botón " + btn.getCharacterName() + " en " + x + "," + 100);
        btn.addActionListener(this);
    }
    
    public void initBackground(){
        JLabel  background = new JLabel(MainController.resizeIcon(game.getBackgrounds().get(3), opponentWindow.getWidth(),opponentWindow.getHeight())); 
        background.setBounds(0, 0,opponentWindow.getWidth(),opponentWindow.getHeight());
        opponentWindow.getSelectionPanel().add(background);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        int charBtnIndex = characterBtns.indexOf(e.getSource());
        if(charBtnIndex != -1){
            // se presiona uno de los bootnes de personajes
            updatecharacters(characterBtns.get(charBtnIndex).getCharacterName());
            
        }else if(e.getSource().equals(opponentWindow.getBtnContinue())){
            // se presiona el boton de continuar
            for(CharacterBtn btn : characterBtns){
                if(btn.isSelected()){
                    sendOpponent(btn.getCharacterName());
                    System.out.println("Se envía a " + btn.getCharacterName());}
            }
            mainController.closeMiniGame(6); // vuelve al tablero
        }
    }
    
    public void updatecharacters(String name){
        for(CharacterBtn btn : characterBtns){
            if (btn.getCharacterName().equals(name)){
                btn.setSelected(true);
            }
            else{
                btn.setSelected(false);
            }
        }
    }
    
    public void sendOpponent(String characterName){
        try {
            option += 4;
            outputStream.writeInt(5); //opción tablero
            outputStream.writeInt(option); //opción FireFlower o IceFlower
            outputStream.writeUTF(characterName); //envía el nombre del jugador
        } catch (IOException ex) {
            Logger.getLogger(OpponentSelectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
}
