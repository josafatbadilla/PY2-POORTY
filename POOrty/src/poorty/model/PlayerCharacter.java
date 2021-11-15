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
public class PlayerCharacter extends JLabel {
    
    private String nombre;
    private Character playerCharacter;
    private ImageIcon icon;
    private int x;
    private int y;
    private int width;
    private int height;
    private int casillaActual = 0;
    

    public PlayerCharacter(int width, int height, Character playerCharacter) {
        super(MainController.resizeIcon(playerCharacter.getIcon(), width, height));
        this.icon = MainController.resizeIcon(playerCharacter.getIcon(), width, height);
        this.playerCharacter = playerCharacter;
        this.width = width;
        this.height = height;
        this.nombre = playerCharacter.getName();
    }
    
    public void updateBounds(int x, int y){
        this.x = x;
        this.y = y;
        setBounds( x, y , width, height);
    }
    
    public int getx(){
        return x;
    }
    
    public int gety(){
        return y;
    }
    

    public String getCharacterName() {
        return playerCharacter.getName();
    }

    public void setCasillaActual(int casillaActual) {
        this.casillaActual = casillaActual;
    }
    
    public int getCasillaActual() {
        return casillaActual;
    }

    public ImageIcon getIcon() {
        return icon;
    }
    
    
    
    
    
    

    
    
    
    
    
    
    
}
