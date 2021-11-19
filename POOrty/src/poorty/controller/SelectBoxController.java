/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poorty.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.util.ArrayList;
import javax.swing.JLabel;
import poorty.model.Box;
import poorty.model.BoxBtn;
import poorty.model.Game;
import poorty.model.SelectBoxBtn;

import poorty.view.SelectBox;
import poorty.view.Selection;

/**
 *
 * @author josa
 */
public class SelectBoxController implements ActionListener{
    
    SelectBox boxWindow;
    ArrayList<SelectBoxBtn> boxBtns;
    private Game game;
    private MainController mainController;
    private DataOutputStream outputStream;
    private int x = 20;

    public SelectBoxController(SelectBox boxWindow, Game game, MainController mainController) {
        this.boxWindow = boxWindow;
        this.game = game;
        this.mainController = mainController;
        this.boxBtns = new ArrayList<>();
    }
    
    public void _init_(){
        boxWindow.getBtnContinue().addActionListener(this);
        boxWindow.setTitle("Jugador " + game.getPlayer().getPlayerId() + " /Tail");
    }
    
    public void initBackground(){
        JLabel  background = new JLabel(MainController.resizeIcon(game.getBackgrounds().get(3), boxWindow.getWidth(),boxWindow.getHeight())); 
        background.setBounds(0, 0,boxWindow.getWidth(),boxWindow.getHeight());
        boxWindow.add(background);
    }
    
    public void addBox(BoxBtn box){
        //Agrega el botón de la casilla
        SelectBoxBtn btn = new SelectBoxBtn(box);
        boxBtns.add(btn);
        btn.setBounds(x, 110, Selection.CHARHEIGH, Selection.CHARHEIGH);
        this.x += Selection.CHARHEIGH + 10;
        boxWindow.add(btn);
        btn.setVisible(true);
        System.out.println("Se agrega el botón " + btn.getBoxName() + " en " + x + "," + 100);
        btn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int charBtnIndex = boxBtns.indexOf(e.getSource());
        if(charBtnIndex != -1){
            // se presiona uno de los bootnes de personajes
            updatecharacters(boxBtns.get(charBtnIndex).getBoxName());}
        else if(e.getSource().equals(boxWindow.getBtnContinue())){
            // se presiona el boton de continuar
            for( SelectBoxBtn btn : boxBtns){
                if(btn.isSelected()){
                    mainController.getBoardController().tail(btn.getBoxNumber());
                    System.out.println("Se envía " + btn.getBoxName());
                    break;}
            }
            mainController.closeMiniGame(7); // vuelve al tablero
        }
    }
        
    public void updatecharacters(String name){
        for(SelectBoxBtn btn : boxBtns){
            if (btn.getBoxName().equals(name)){
                btn.setSelected(true);
                break;
            }
            else{
                btn.setSelected(false);
            }
        }
    }
}
