/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poorty.model;

import javax.swing.ImageIcon;

/**
 *
 * @author josa
 */
public class MarioCard {
    private int value;
    private ImageIcon cardIcon;
    private boolean isSelected;

    public MarioCard(int value, ImageIcon cardIcon) {
        this.value = value;
        this.cardIcon = cardIcon;
    }

    public int getValue() {
        return value;
    }

    public ImageIcon getCardIcon() {
        return cardIcon;
    }
    
    public boolean isSelected(){
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
    
    
    
}
