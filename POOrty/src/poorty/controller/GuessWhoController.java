
package poorty.controller;

// Controlador para el juego de guess who

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import poorty.model.Game;
import poorty.view.GuessWhoWindow;


public class GuessWhoController implements ActionListener{
    
    private GuessWhoWindow guessWhoView;
    private Game game;
    private MainController mainController;
    private DataOutputStream outputStream;
    
    // atributos para el juego
    private boolean playerWin;

    public GuessWhoController(GuessWhoWindow guessWhoView, Game game, MainController mainController) {
        this.guessWhoView = guessWhoView;
        this.game = game;
        this.mainController = mainController;
        this.outputStream = game.getPlayer().getOutputStream();
        
        this.playerWin = false;
    }
    
    public void _init_(){
        // se agregan el listener a los botones
        this.guessWhoView.getBtnCloseGame().addActionListener(this);
        this.guessWhoView.getBtnCloseGame().setEnabled(false);
        
        addBtnsOptionListener();
    }
    
    private void addBtnsOptionListener(){
        for(int i = 0; i < this.guessWhoView.getGameOptions().size(); i++){
            this.guessWhoView.getGameOptions().get(i).addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource().equals(this.guessWhoView.getBtnCloseGame())){
            // se preciona el boton de salir
            finishGame();
        }
        
        // botones de opciones
        int opBtnIndex = this.guessWhoView.getGameOptions().indexOf(e.getSource());
        if(opBtnIndex != -1){
            // se presiona uno de los bootnes de opciones
            play(this.guessWhoView.getGameOptions().get(opBtnIndex).getText());
            
            
        }
    }
    
    private void play(String option){
        if(option.equals(this.guessWhoView.getAnswer())){
            // acierta en la respuesta
            this.playerWin = true;
            this.guessWhoView.getLabelResult().setText("Has acertado");
        }else{
            this.guessWhoView.getLabelResult().setText("Has fallado");
        }
        
        this.guessWhoView.getBtnCloseGame().setEnabled(true); // se activa el boton de salir
    }
    
    private void finishGame(){
        // coloca si gano o no si puede jugar los dados otra vez
        this.game.getPlayer().setThrowDices(playerWin);
        mainController.closeMiniGame(11); // cerrar el minijuego
    }
    
    
}
