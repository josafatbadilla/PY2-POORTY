/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poorty.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import poorty.model.Box;
import poorty.model.BoxBtn;
import poorty.model.Game;
import poorty.model.SelectBoxBtn;
import poorty.view.MemoryPathWindow;


/**
 *
 * @author josa
 */
public class MemoryPathController implements ActionListener {
    
    MemoryPathWindow memoryPathWindow;
    private Game game;
    private MainController mainController;
    private SelectBoxBtn[][] arrayBtns;
    private int intentos = 3;
    private ArrayList<ImageIcon> images;
    private ImageIcon playerIcon;
    private int boxSize = 65;
    private int width = 300;
    private int height = 650;

    public MemoryPathController(MemoryPathWindow memoryPathWindow, Game game, MainController mainController, ImageIcon playerIcon) {
        this.memoryPathWindow = memoryPathWindow;
        this.game = game;
        this.playerIcon = playerIcon;
        this.mainController = mainController;
        this.arrayBtns = new SelectBoxBtn[6][3];
    }
    
    public void _init_(){
        loadBoxesImages();
        initBoxes();
        choiceCorrectBox();
        initPlayer();
        memoryPathWindow.setSize(350, 650);
        memoryPathWindow.visibility(true);
        memoryPathWindow.getBtnBack().addActionListener(this);
        memoryPathWindow.getBtnBack().setEnabled(false);
        memoryPathWindow.getStarLbl().setIcon(images.get(3));
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        updateBtnSelected(e);
        
        if(e.getSource().equals(memoryPathWindow.getBtnBack())){
            mainController.closeMiniGame(10);
        }
    }
    
    public void initBoxes(){
        int y = height - 120;
        int x;
        for (int i = 0; i < 6; i++) {
            y -= boxSize + 10;
            x = boxSize + 10;
            for (int j = 0; j < 3; j++) {
                arrayBtns[i][j] = new SelectBoxBtn(images.get(2),0); // genera
                arrayBtns[i][j].setBounds(x,y,boxSize,boxSize);
                arrayBtns[i][j].addActionListener(this);
                memoryPathWindow.add(arrayBtns[i][j]);
                x += boxSize + 10;
            }
        }  
    }
    
    public void initPlayer(){
        memoryPathWindow.getPlayerLbl().setIcon(playerIcon);
        memoryPathWindow.getPlayerLbl().setBounds(10, height - 150,memoryPathWindow.getPlayerLbl().getWidth(), memoryPathWindow.getPlayerLbl().getHeight());
    }
    
    public void choiceCorrectBox(){
        Random rand = new Random();
        for (int i = 0; i < 6; i++) {
            int j = rand.nextInt(3);
            arrayBtns[i][j].setBoxValue(1);
            System.out.println(j);// selecciona la columna correcta de manera random
        }
    }
        
    private void loadBoxesImages(){
        this.images = new ArrayList<>();
        ImageIcon charIcon;
        File folder = new File("./src/media/memoryPath");
        for(File file: folder.listFiles()){
            if(!file.isDirectory()){
                try{
                    charIcon = new ImageIcon(file.getCanonicalPath());
                    charIcon = MainController.resizeIcon(charIcon, boxSize, boxSize);
                    this.images.add(charIcon);
                    
                }catch(IOException e){
                    System.out.println("Error al cargar personaje");
                }
 
            }
        }
    }

    private void updateBtnSelected(ActionEvent e){
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                if (arrayBtns[i][j] == e.getSource()){
                    memoryPathWindow.getPlayerLbl().setBounds(arrayBtns[i][j].getX() + 33, arrayBtns[i][j].getY() - 33,memoryPathWindow.getPlayerLbl().getWidth(), memoryPathWindow.getPlayerLbl().getHeight());
                    if(arrayBtns[i][j].getBoxValue() == 1){
                        arrayBtns[i][j].setIcon(images.get(0));
                        arrayBtns[i][j].setBoxValue(3); // indica que está seleccionada  
                    }
                    else if (arrayBtns[i][j].getBoxValue() == 0){
                        arrayBtns[i][j].setIcon(images.get(1));
                        JOptionPane.showMessageDialog(memoryPathWindow, "Casilla incorrecta vuelves al principio" , "Jugador ", JOptionPane.INFORMATION_MESSAGE);
                        intentos--;
                        memoryPathWindow.getLblIntentos().setText("Intentos: " + intentos );
                        restartBoard();
                    }
                    break;
                }    
            }
        }
        isFinished();
        
    }
    
    private void restartBoard(){
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                arrayBtns[i][j].setIcon(images.get(2));
            }
        }
    }
    
    private void isFinished(){
        if (intentos == 0){
            finishGame(false);
        }
        boolean complete = false;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                if(arrayBtns[i][j].getBoxValue() == 3){
                    complete = true;
                    arrayBtns[i][j].setBoxValue(1);
                    break;
                }
                else{
                    complete = false;
                }
            }
        }
        if(complete)
            finishGame(true);
    }
    
    
    
    
    private void finishGame(boolean playerWin){
        
        if(playerWin){
            // gano la partida
            memoryPathWindow.getLblGameSatus().setText("Has ganado");
             
        }else{
            // pierdo la partida
            memoryPathWindow.getLblGameSatus().setText("Has perdido");
        }
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                arrayBtns[i][j].setEnabled(false);
            }
        }
        memoryPathWindow.getBtnBack().setEnabled(true); // se activa el boton para salir
        this.game.getPlayer().setThrowDices(playerWin);
        
    }
    
    
}
