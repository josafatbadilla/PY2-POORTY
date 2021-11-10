

package poorty.controller;

import java.io.DataOutputStream;
import poorty.model.CatGameButton;
import poorty.model.Game;
import poorty.view.CatGameWindow;



public class CatGameController {
    private CatGameWindow catGameView;
    private Game game;
    private MainController mainController;
    private DataOutputStream outputStream;
    
    public static int PROPORTION = 3;  // tamano para la matriz
    
    private CatGameButton[][] board;

    public CatGameController(CatGameWindow catGameView, Game game, MainController mainController) {
        this.catGameView = catGameView;
        this.game = game;
        this.mainController = mainController;
        this.outputStream = game.getPlayer().getOutputStream();
        this.board = new CatGameButton[PROPORTION][PROPORTION];
    }
    
    
    // METODOS
    
    // se inicializan los compponentes de la pantalla
    public void _init_(){
        // agregar los listeners
        
        // inicializacion de componentes graficos de la ventana
        initCatGameBoard();
    }
    
    // creacion del tablero
    private void initCatGameBoard(){
        int x = 0;
        int y = 0;
        for (int i = 0; i < PROPORTION; i++) {
            for (int j = 0; j < PROPORTION; j++) {
                this.board[i][j] = new CatGameButton(i, j, 0, CatGameButton.getImageIcon(0));
                this.board[i][j].setLocation(x, y);
                this.catGameView.getPnlBoard().add(this.board[i][j]);
                x += CatGameWindow.BOARD_BTN_SIZE;
            }
            x = 0;
            y += CatGameWindow.BOARD_BTN_SIZE;
        }
        
    }
    
    
    
    // funciones para la conexion con el servidor
    
    
}
