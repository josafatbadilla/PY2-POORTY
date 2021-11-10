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
public class PlayerCharacter {
    private String nombre;
    private Character playerCharacter;
    private JLabel playerButton;
    private ImageIcon playerIcon;

    public PlayerCharacter(int width, int height, Character playerCharacter) {
        this.playerCharacter = playerCharacter;
        this.playerIcon = MainController.resizeIcon(playerCharacter.getIcon(), width, height);
        this.nombre = playerCharacter.getName();
        this.playerButton = new JLabel(playerIcon);
    }

    public JLabel getPlayerButton() {
        return playerButton;
    }

    public void setPlayerButton(JLabel playerButton) {
        this.playerButton = playerButton;
    }
    
    public void setPlayerButtonBounds(int x, int y, int width, int heigth) {
        this.playerButton.setBounds(x, y, width, heigth);
    }

    public ImageIcon getPlayerIcon() {
        return playerIcon;
    }
    
    
    
    
    
    
    
}
