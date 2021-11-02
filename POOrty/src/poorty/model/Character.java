
package poorty.model;

import javax.swing.ImageIcon;


// Objeto para los personajes del juego

public class Character {
    
    ImageIcon icon;
    String name;
    
    public Character(String name, ImageIcon icon){
       this.name = name;
       this.icon = icon;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }
    
    
    
    
}
