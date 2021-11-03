
package poorty.controller;

// CONTROLADOR PARA EL TABLERO

import java.io.DataOutputStream;
import poorty.model.Game;
import poorty.view.BoardWindow;


public class BoardController {
    private BoardWindow boardView;
    private Game game;
    private MainController mainController;
    private DataOutputStream outputStream;

    public BoardController(BoardWindow boardView, Game game, MainController mainController) {
        this.boardView = boardView;
        this.game = game;
        this.mainController = mainController;
        this.outputStream = game.getPlayer().getOutputStream();
    }
    
    // METODOS
    
    // se inicializan los compponentes de la pantalla
    public void _init_(){
        // agregar los listeners
        
        // inicializacion de componentes graficos de la ventana
        
    }
    
    
    // funciones para la conexion con el servidor
    
    
}
