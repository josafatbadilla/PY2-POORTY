/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poorty.model;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author josa
 */
public class BoxBtn {
    private String tipo_casilla;
    private ImageIcon boxIcon;
    private int[] bounds;
    private JLabel boxButton;
    private String boxName;
    private int boxNumber;

    public BoxBtn(String tipo_casilla, String boxName, int boxNumber) {
        this.tipo_casilla = tipo_casilla;
        this.bounds = new int[4];
        this.boxNumber = boxNumber;
        this.boxButton = new JLabel(boxName);
    }

    public String getTipo_casilla() {
        return tipo_casilla;
    }

    public void setTipo_casilla(String tipo_casilla) {
        this.tipo_casilla = tipo_casilla;
    }

    public ImageIcon getBoxIcon() {
        return boxIcon;
    }

    public void setBoxIcon(ImageIcon boxIcon) {
        this.boxIcon = boxIcon;
        this.boxButton.setIcon(boxIcon);
    }

    public int[] getBounds() {
        return bounds;
    }

    public void setBoxName(String boxName) {
        this.boxName = boxName;
    }

    public String getBoxName() {
        return boxName;
    }

    public int getBoxNumber() {
        return boxNumber;
    }
    
    
    
    

    public void setBounds(int x, int y, int width, int height) {
        this.bounds[0] = x;
        this.bounds[1] = y;
        this.bounds[2] = width;
        this.bounds[3] = height;
        this.boxButton.setBounds(bounds[0], bounds[1], bounds[2], bounds[3]);
    }

    public JLabel getBoxButton() {
        return boxButton;
    }
    
    
    
    
    
    
    
    
    
    

    
    
    
    
    
    
    
}
