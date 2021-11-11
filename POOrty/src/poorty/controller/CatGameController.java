

package poorty.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import javax.swing.ImageIcon;
import poorty.model.CatGameButton;
import poorty.model.CharacterBtn;
import poorty.model.Game;
import poorty.view.CatGameWindow;
import poorty.view.Selection;



public class CatGameController implements ActionListener {
    private CatGameWindow catGameView;
    private Game game;
    private MainController mainController;
    private DataOutputStream outputStream;
    
    private boolean playerTurn;
    private boolean miniGameHost;
    private int enemyId;

    public CatGameController(CatGameWindow catGameView, Game game, MainController mainController, int enemyId) {
        this.catGameView = catGameView;
        this.game = game;
        this.mainController = mainController;
        this.outputStream = game.getPlayer().getOutputStream();
        
        this.enemyId = enemyId;
        // determina si pide enemigo o es el enemigo solicitado
        // -1 indica que es el que inicio el juego
        this.miniGameHost = enemyId == -1;
        this.playerTurn = miniGameHost; // el host siempre inicia con el turno
    }
    
    
    // METODOS
    
    // se inicializan los compponentes de la pantalla
    public void _init_(){
        // agregar los listeners
        
        // se asigna un enemigo aleatorio
        if(this.miniGameHost){
            // 4 representa el juego del gato
            mainController.getEnemyMiniGame(4, 1);
        }

        
        // inicializacion de componentes graficos de la ventana
        initCatGameBoard();
    }
    
    // creacion del tablero
    private void initCatGameBoard(){
        int x = 0;
        int y = 0;
        for (int i = 0; i < CatGameWindow.PROPORTION; i++) {
            for (int j = 0; j < CatGameWindow.PROPORTION; j++) {
                catGameView.getBoard()[i][j] = createCatGameButton(i, j, CatGameButton.getImageIcon(0), x, y);
                this.catGameView.getPnlBoard().add(catGameView.getBoard()[i][j]);
                
                x += CatGameWindow.BOARD_BTN_SIZE;
            }
            x = 0;
            y += CatGameWindow.BOARD_BTN_SIZE;
        }
    }
    
    
    private CatGameButton createCatGameButton(int row, int column, ImageIcon icon, int x, int y){
        CatGameButton newButton = new CatGameButton(row, column, 0, icon);
        newButton.setBounds(x, y, CatGameWindow.BOARD_BTN_SIZE, CatGameWindow.BOARD_BTN_SIZE);
        newButton.addActionListener(this);
        return newButton;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource().getClass().equals(CatGameButton.class)){
            // se presiono un boton de la matriz
            // se realiza la actualizacion del board en ambas pantallas
            // se cambia de turno
        }
        
       
    }
    
    
    
    // ---------------------- funciones para la conexion con el servidor-------------------------

    
    
    
    // getters and setters
    public void setEnemyId(int enemyId) {
        this.enemyId = enemyId;
    }
    
    
    
}
