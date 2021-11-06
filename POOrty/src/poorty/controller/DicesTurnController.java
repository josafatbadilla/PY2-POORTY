
// Controlador para la ventana de la seleccion de turno

package poorty.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import poorty.model.Game;
import poorty.view.DicesTurnWindow;


public class DicesTurnController implements ActionListener{
    
    private DicesTurnWindow dicesTurnView;
    private Game game;
    private MainController mainController;
    private DataOutputStream outputStream;

    public DicesTurnController(DicesTurnWindow dicesTurnView, Game game, MainController mainController) {
        this.dicesTurnView = dicesTurnView;
        this.game = game;
        this.mainController = mainController;
        this.outputStream = game.getPlayer().getOutputStream();
    }
    
    // METODOS
    
    // se inicializan los compponentes de la pantalla
    public void _init_(){
        // agregar los listeners
       
        // se activan solo para el host
        
        // inicializacion de componentes graficos de la ventana
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // funcionalidad de los botones
       
    }
    
    // funciones para la conexion con el servidor

    
    
}
