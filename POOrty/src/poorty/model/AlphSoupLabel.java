

// Clase que representa el boton de la sopa de letra
package poorty.model;

import java.awt.Color;
import javax.swing.JLabel;

public class AlphSoupLabel extends JLabel{
    
    boolean selected;
    
    public AlphSoupLabel(String text) {
        super(text);
        setSelected(false);
    }
    
    
    // getters and setters
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected){
        this.selected = selected;
        if(this.selected){
            this.setBackground(Color.GRAY);
        }else{
            this.setBackground(Color.WHITE);
        }
    }
    
    public void setLetter(String letter){
        this.setText(letter);
    }
    
    public String getLetter(){
        return this.getText();
    }
}
