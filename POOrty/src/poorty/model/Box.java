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
public class Box {
    ImageIcon icon;
    String name;
    int frequency;
    
    public Box(String name, ImageIcon icon, int frequency){
       this.name = name;
       this.icon = icon;
       this.frequency = frequency;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    } 

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getFrequency() {
        return frequency;
    }
    
    public void decreaseFrequency(){
        this.frequency--;
    }
    
    
}
