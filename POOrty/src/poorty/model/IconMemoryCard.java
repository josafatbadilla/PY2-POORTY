
package poorty.model;

import java.io.Serializable;
import javax.swing.ImageIcon;
import poorty.controller.MainController;
import static poorty.view.MemoryWindow.LABEL_HEIGHT;
import static poorty.view.MemoryWindow.LABEL_WIDTH;


public class IconMemoryCard implements Serializable{
    private String cardValue;
    private ImageIcon icon;

    public IconMemoryCard(String cardValue, ImageIcon icon) {
        this.icon = MainController.resizeIcon(icon, LABEL_WIDTH, LABEL_HEIGHT);
        this.cardValue = cardValue;
    }

    public String getCardValue() {
        return cardValue;
    }

    public ImageIcon getIcon() {
        return icon;
    }
    
    
    
    
}
