
package poorty.model;
// Clase para cronometro

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

public class Chronometer extends Thread{
    private JLabel label;
    private boolean isRunning;
    private int seconds;
    private int minutes;
    
    public Chronometer(JLabel label){
        this.label = label;
        this.isRunning = true;
        this.seconds = 0;
        this.minutes = 0;
    }
    
    public void run(){
        
        while(this.isRunning){
            try{
                
                sleep(1000);
                
                this.seconds++;
                if (this.seconds > 59){
                    this.seconds = 0;
                    this.minutes = 0;
                    if(this.minutes > 59){
                        this.minutes = 0;
                    }
                }

                label.setText(setNiceTime(this.minutes) + ":" +  setNiceTime(this.seconds));
                
            }catch(InterruptedException ex){
                Logger.getLogger(Chronometer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
    }
    
    
    private String setNiceTime(int number){
    if (number < 10)
        return "0"+number;
    return ""+number;
    }
}
