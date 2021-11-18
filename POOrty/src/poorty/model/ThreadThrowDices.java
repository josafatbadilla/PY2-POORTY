
package poorty.model;

// Clase para el thread que tira los dados

import javax.swing.JLabel;
import javax.swing.SwingUtilities;


public class ThreadThrowDices extends Thread{
    private boolean isRunning = true;
    private boolean isPause = false;
    
    private int dice1Num;
    private int dice2Num;
    private int timeSleep;
    private int counter;
    
    private JLabel lblDice1;
    private JLabel lblDice2;
    
    
    public ThreadThrowDices(JLabel lblDice1, int dice1Num, JLabel lblDice2, int dice2Num){
        this.lblDice1 = lblDice1;
        this.lblDice2 = lblDice2;
        this.dice1Num = dice1Num;
        this.dice2Num = dice2Num;
        timeSleep = 100;
        counter = 0;
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
                    lblDice1.setText((int)(Math.random()*6+1) + "");
                    lblDice2.setText((int)(Math.random()*6+1) + "");
                }
            });
            
            timeSleep = counter == 4 || counter == 9 ? timeSleep + 500 : timeSleep;
            
            counter++;
            if(counter >= 8){
                stopRunning();
                // cuando termina se quedan los que salieron de primero
                lblDice1.setText(dice1Num + "");
                lblDice2.setText(dice2Num + "");
            }
        }
    }
    
    
    public int getDicesResult(){
        return dice1Num + dice2Num;
    }
 
    public void stopRunning(){
        this.isRunning = false;
    }
    
    public void pauseThread(){
        this.isPause = true;
    }
    
    public void resumeThread(){
        this.isPause = false;
    }
    
    public boolean isRunning(){
        return this.isRunning;
    }
    
}
