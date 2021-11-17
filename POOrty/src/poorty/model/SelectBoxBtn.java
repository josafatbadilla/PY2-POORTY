/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poorty.model;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import poorty.controller.MainController;
import poorty.view.Selection;

/**
 *
 * @author josa
 */
public class SelectBoxBtn extends JButton{
    
    BoxBtn box;
    private boolean selected;
    private int boxValue;

    public SelectBoxBtn(BoxBtn box) {
        super(MainController.resizeIcon(box.getBoxIcon(), Selection.CHARHEIGH - 5, Selection.CHARHEIGH - 5));
        this.box = box;
        this.selected = false;
    }
    
    public SelectBoxBtn(ImageIcon image, int boxValue){
        super(image);
        this.boxValue = boxValue;
        this.selected = false;
    }

    public int getBoxValue() {
        return boxValue;
    }

    public void setBoxValue(int boxValue) {
        this.boxValue = boxValue;
    }
    
    public String getBoxName() {
        return box.getBoxName();
    }
    
    public int getBoxNumber(){
        return box.getBoxNumber();
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        if(this.selected){
            this.setEnabled(false);
        }else{
            this.setEnabled(true);
        }
    }
}
