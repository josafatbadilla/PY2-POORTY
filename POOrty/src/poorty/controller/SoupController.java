
package poorty.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataOutputStream;
import poorty.model.*;
import poorty.view.SoupWindow;


public class SoupController implements MouseListener, ActionListener {
    private SoupWindow soupView;
    private Game game;
    private MainController mainController;
    private DataOutputStream outputStream;

    public SoupController(SoupWindow soupView, Game game, MainController mainController) {
        this.soupView = soupView;
        this.game = game;
        this.game.setAlphSoup(new AlphSoup(soupView.getAlphaSoupMatrix())); // creamos el minijuego de la sopa de letras
        this.mainController = mainController;
        this.outputStream = game.getPlayer().getOutputStream();
    }
    
    // inicializacion de la pantalla
    public void _init_(){
      
        
        // generar sopa de letras
        
        // iniciar el contador de 2 min
        
        // listeners para labels
        addSoupLabelMouseListener();
    }
    
    // se agrega el action listener para los labelss
    private void addSoupLabelMouseListener(){
        for(int i = 0; i < soupView.getAlphaSoupMatrix().length; i++){
            for(int j = 0; j < soupView.getAlphaSoupMatrix()[0].length; j++){
                soupView.getAlphaSoupMatrix()[i][j].addMouseListener(this);
            }
        }
    }
    
    // MOUSE LISTENER
    @Override
    public void mouseClicked(MouseEvent e) {
//        System.out.println("clicked");
    }

    @Override
    public void mousePressed(MouseEvent e) {
//        System.out.println("pressed");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
//        System.out.println("released");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
//        System.out.println("entered");
    }

    @Override
    public void mouseExited(MouseEvent e) {
//        System.out.println("exited");
    }
    
    // ACTION LISTENER
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}
