
package poorty.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
        
        // inicializacion de componentes graficos de la ventana
        initCharacterBtns();
        
    }
    
    private void initCharacterBtns(){
        int x = 10, y = 10;
        for(int i = 0; i < game.getCharacters().size(); i++){
            JButton characterBtn = createCharacterButton(game.getCharacters().get(i).getIcon(), x, y);
            charSelectWindow.getCharactersBtn().add(characterBtn); // se agrea a la lista
            charSelectWindow.getPnlCharacters().add(characterBtn);
            
            x += charSelectWindow.CHARWIDTH + 10;
            
            if(i == 3 || i == 7){
                x = 10;
                y += charSelectWindow.CHARHEIGH + 10;
            }
        }
    
    }
    
    private JButton createCharacterButton(ImageIcon icon, int x, int y){
        JButton newButton = new JButton(mainController.resizeIcon(icon, charSelectWindow.CHARWIDTH - 5, charSelectWindow.CHARHEIGH - 5));
        newButton.setBounds(x, y, charSelectWindow.CHARWIDTH, charSelectWindow.CHARHEIGH);
        return newButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
    
    
}
