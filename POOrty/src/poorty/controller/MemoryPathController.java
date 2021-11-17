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
    private DataOutputStream outputStream;
    private SelectBoxBtn[][] arrayBtns;
    private int intentos = 3;

    public MemoryPathController(MemoryPathWindow memoryPathWindow, Game game, MainController mainController) {
        this.memoryPathWindow = memoryPathWindow;
        this.game = game;
        this.mainController = mainController;
        this.arrayBtns = new SelectBoxBtn[6][3];
    }
    
    public void _init_(){
        initBoxes();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void initBoxes(){
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                SelectBoxBtn box = new SelectBoxBtn();
            }
        }
        
        
    }
    
}
