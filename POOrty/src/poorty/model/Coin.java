
package poorty.model;

import javax.swing.Icon;
import javax.swing.JLabel;


public class Coin extends JLabel{
    
    private int value;
    private int iMatrix;
    private int jMatrix;
    private boolean collected; // cuando ya fue recolectada 

    public Coin( int iMatrix, int jMatrix, Icon image) {
        super(image);
        this.value = 0;
        this.iMatrix = iMatrix;
        this.jMatrix = jMatrix;
        this.collected = false; // inicia como no recolectada
    }
    
    
    public void setCollected(boolean collected){
        this.collected = collected;
        this.setVisible(!collected); // se oculta si esta collected se muestra si no
    }
    
    public int getValue(){
        return this.value;
    }
    
    public void setValue(int value){
        this.value = value;
    }
}
