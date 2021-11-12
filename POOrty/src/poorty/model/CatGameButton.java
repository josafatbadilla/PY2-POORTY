
package poorty.model;

// boton para el tablero del juego del gato

import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import poorty.controller.MainController;
import poorty.view.CatGameWindow;


public class CatGameButton extends JButton {
    
    private int row;
    private int column;
    private int value;

    public CatGameButton(int row, int column, int value, ImageIcon icon) {
        
        super(MainController.resizeIcon(icon, CatGameWindow.BOARD_BTN_SIZE - 5, CatGameWindow.BOARD_BTN_SIZE - 5));
        this.row = row;
        this.column = column;
        this.value = value;
    }
    
    
    public static ImageIcon getImageIcon(int value){
        ImageIcon icon;
        if(value == 0){
            // vacio
            return new ImageIcon("./src/media/catgame/Empty.png");
        }else if(value == 1){
            // equis
            return new ImageIcon("./src/media/catgame/Cross.png");
        }else{
            // circulo
            return new ImageIcon("./src/media/catgame/Circule.png");
        }
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    
    
    
    
}
