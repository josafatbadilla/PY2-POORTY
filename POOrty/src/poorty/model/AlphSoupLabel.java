

// Clase que representa el boton de la sopa de letra
package poorty.model;

import java.awt.Color;
import javax.swing.JLabel;

public class AlphSoupLabel extends JLabel{
    
    boolean selected;
    
    public AlphSoupLabel(char text) {
        super(text + "");
        this.setOpaque(true); // para que se vea el fondo
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
    
    public void setLetter(char letter){
        this.setText(letter + "");
    }
    
    public char getLetter(){
        char[] chars = this.getText().toCharArray();
        return chars[0];
    }
}
