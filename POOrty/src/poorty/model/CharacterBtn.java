
// Clase para el boton de seleccion de personajes de la pantalla de seleccion

package poorty.model;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import poorty.model.Character;
import poorty.controller.MainController;
import poorty.view.Selection;

public class CharacterBtn extends JButton{
    
    private Character character;
    private boolean selected;

    public CharacterBtn(Character character) {
        super(MainController.resizeIcon(character.getIcon(), Selection.CHARWIDTH - 10, Selection.CHARHEIGH - 10));
        this.character = character;
        this.selected = false;
    }

    public String getCharacterName() {
        return character.getName();
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
