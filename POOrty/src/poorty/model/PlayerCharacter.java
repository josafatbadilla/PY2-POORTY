/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poorty.model;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import poorty.controller.MainController;

/**
 *
 * @author josa
 */
public class PlayerCharacter extends JButton {
    
    private String nombre;
    private Character playerCharacter;
    private int x;
    private int y;
    private int[] bounds = new int[4];

    public PlayerCharacter(int width, int height, Character playerCharacter) {
        super(MainController.resizeIcon(playerCharacter.getIcon(), width, height));
        this.playerCharacter = playerCharacter;
        this.nombre = playerCharacter.getName();
    }
    
    public void updateBounds(int x, int y, int widht, int height){
        bounds[0] = x;
        bounds[1] = y;
        bounds[2] = widht;
        bounds[3] = height;
        this.x = x;
        this.y = y;
    }
    
    public int[] getButtonBounds(){
        return bounds;
    }

    public String getCharacterName() {
        return playerCharacter.getName();
    }
    
    
    

    
    
    
    
    
    
    
}
