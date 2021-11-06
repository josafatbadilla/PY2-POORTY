
package poorty.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import poorty.model.CharacterBtn;
import poorty.model.Character;
import poorty.model.Game;
import poorty.view.Selection;


public class SelectionController implements ActionListener{
    
    private Selection charSelectWindow;
    private Game game;
    private MainController mainController;
    private DataOutputStream outputStream;

    public SelectionController(Selection charSelectWindow, Game game, MainController mainController) {
        this.charSelectWindow = charSelectWindow;
        this.game = game;
        this.mainController = mainController;
        this.outputStream = game.getPlayer().getOutputStream();
    }
    
    // se inicializan los compponentes de la pantalla
    public void _init_(){
        // agregar los listeners
        charSelectWindow.getBtnRandomTurn().addActionListener(this);
        charSelectWindow.getBtnRandomTurn().setEnabled(game.getPlayer().isHost());
        
        charSelectWindow.getBtnDicesTurn().addActionListener(this);
        charSelectWindow.getBtnDicesTurn().setEnabled(game.getPlayer().isHost());
        
        // inicializacion de componentes graficos de la ventana
        initCharacterBtns();
       
    }
    
    private void initCharacterBtns(){
        int x = 10, y = 10;
        for(int i = 0; i < game.getCharacters().size(); i++){
   
            CharacterBtn characterBtn = createCharacterButton(game.getCharacters().get(i), x, y);
            charSelectWindow.getCharacterBtns().add(characterBtn); // se agrea a la lista
            charSelectWindow.getPnlCharacters().add(characterBtn);
            
            x += Selection.CHARWIDTH + 10;
            
            if(i == 3 || i == 7){
                x = 10;
                y += Selection.CHARHEIGH + 10;
            }
        }
        
        printCharacterButtons();
    
    }
    
    private CharacterBtn createCharacterButton(Character character, int x, int y){
        CharacterBtn newButton = new CharacterBtn(character);
        newButton.setBounds(x, y, Selection.CHARWIDTH, Selection.CHARHEIGH);
        newButton.addActionListener(this);
        return newButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        int charBtnIndex = charSelectWindow.getCharacterBtns().indexOf(e.getSource());
        if(charBtnIndex != -1){
            // se presiona uno de los bootnes de personajes
            selectCharacter(charSelectWindow.getCharacterBtns().get(charBtnIndex).getCharacterName());
            
        }else if(e.getSource().equals(charSelectWindow.getBtnRandomTurn())){
            // se presiona el boton para los turnos aleatorios por numero
            turnSelection(1);
        }else if(e.getSource().equals(charSelectWindow.getBtnDicesTurn())){
             turnSelection(2);
        }
    }
    
    
    // funcion para actualizar el estado de seleccion de los botones
    public void updateCharacterButtons(String unselectedCharacter, String selectedCharacter){
        for(CharacterBtn characterBtn: charSelectWindow.getCharacterBtns()){
            if(characterBtn.getCharacterName().equals(unselectedCharacter)){
                characterBtn.setSelected(false);
            }else if(characterBtn.getCharacterName().equals(selectedCharacter)){
                characterBtn.setSelected(true);
            }
        }
        printCharacterButtons();
    }
    
    private void printCharacterButtons(){
        for(CharacterBtn characterBtn: charSelectWindow.getCharacterBtns()){
            if(characterBtn.isSelected()){
                if(characterBtn.getCharacterName().equals(game.getPlayer().getCharacterName())){
                    characterBtn.setBackground(Selection.BTN_COLORS[1]); // fondo gris
                    characterBtn.setBorder(BorderFactory.createLineBorder(Selection.BTN_COLORS[1], 1));
                }else{
                    characterBtn.setBackground(Selection.BTN_COLORS[2]); // fondo gris
                    characterBtn.setBorder(BorderFactory.createLineBorder(Selection.BTN_COLORS[2], 1));
                }
            }else{
                characterBtn.setBackground(Selection.BTN_COLORS[0]); // fondo blanco
                characterBtn.setBorder(BorderFactory.createLineBorder(Selection.BTN_COLORS[0], 1));
            }
        }
    }
    
    
    // funciones para la conexion con el servidor
    
    // comunica al servidor que se selecciono al personaje
    private void selectCharacter(String characterName){
       try {
            outputStream.writeInt(2); // opcion de la seleccion de personajes
            outputStream.writeInt(1); // para la seleccion de personaje
            
            // se comunica el personaje que se deselecciono
            outputStream.writeUTF(game.getPlayer().getCharacterName());
            // envia el personaje seleccionado
            outputStream.writeUTF(characterName);
            // se le actualiza al jugador
            game.getPlayer().setCharacterName(characterName);
            
        } catch (IOException ex) {
                    Logger.getLogger(LobbyController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    
    // se cambia para la pantalla para determinar los turnos
    private void turnSelection(int option){
        try {
            outputStream.writeInt(2); // opcion de la seleccion de personajes
            outputStream.writeInt(2); // para la pasar a la seleccion del turno
            outputStream.writeInt(option); // determina a la pantalla a la que se envia
            
        } catch (IOException ex) {
            Logger.getLogger(LobbyController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
}
