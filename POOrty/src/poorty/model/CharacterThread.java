/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poorty.model;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;

/**
 *
 * @author josa
 */


public class CharacterThread extends Thread{
    private boolean isRunning = true;
    private boolean isPause = false;
    private int timeSleep;
    private int xIncial;
    private int yInicial;
    private int xFinal;
    private int yFinal;
    private int Board_size;
    
    private PlayerCharacter character;

    public CharacterThread(int xIncial, int yInicial, int xFinal, int yFinal,int Board_size, PlayerCharacter character) {
        this.xIncial = xIncial;
        this.yInicial = yInicial;
        this.xFinal = xFinal;
        this.yFinal = yFinal;
        this.Board_size = Board_size;
        this.character = character;
        timeSleep = 1000;
        this.start();
    }
    
    public void run(){
        
        while(isRunning) {
            try {
                Thread.sleep(timeSleep);
            } catch (InterruptedException e) {
            }

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    character.updateBounds(xIncial, yFinal);
                }
            });
            
            
            
            
           
            }
        }
    }
    
    

