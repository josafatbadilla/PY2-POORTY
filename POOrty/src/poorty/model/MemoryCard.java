
package poorty.model;

// label para las cartas del super bros memory

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class MemoryCard extends JLabel{
    private boolean faceUp;
    private ImageIcon faceDownIcon;
    private IconMemoryCard cardIcon;
    private int i;
    private int j;

    public MemoryCard(ImageIcon faceDownIcon, int i, int j) {
        super(faceDownIcon);
        this.faceUp = false;
        this.faceDownIcon = faceDownIcon;
        this.cardIcon = null;
        // coordenadas en la matriz
        this.i = i;
        this.j = j;
    }
    
    // ----------------------------- GETTERS AND SETTERS -----------------------------------------
    public boolean isFaceUp() {
        return faceUp;
    }

    public void setFaceUp(boolean faceUp) {
        this.faceUp = faceUp;
        if(faceUp){
            // se setea la imagen de boca arriba
            this.setIcon(cardIcon.getIcon());
        }else{
            // se setea la imagen de boca abajo
             this.setIcon(faceDownIcon);
        }
    }

    public IconMemoryCard getCardIcon() {
        return cardIcon;
    }

    public void setCardIcon(IconMemoryCard cardIcon) {
        this.cardIcon = cardIcon;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }
    
    
    
}
