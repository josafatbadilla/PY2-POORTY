/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poorty.model;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        /*while(x <= this.x){
            try {
                this.setBounds( x, y , width, height);
                x+= 100;
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(PlayerCharacter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        while(y <= this.y){
            try {
                this.setBounds( x, y , width, height);
                y+= 100;
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(PlayerCharacter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }*/
        
        this.x = x ;
        this.y = y;
        System.out.println("ChatacterBounds  x: " + x + " y: " + y);
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

    public Character getPlayerCharacter() {
        return playerCharacter;
    }
    
    
    
    
    
    
    
    

    
    
    
    
    
    
    
}
