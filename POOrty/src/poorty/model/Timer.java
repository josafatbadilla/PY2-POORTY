
package poorty.model;

// clase para un temporizador

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;



public class Timer extends Thread implements iObservable{
    
    private int timerSeconds;
    private int seconds;
    private int minutes;
    private JLabel lblTimer;
    private boolean running;
    private ArrayList<iObserver> observers;

    public Timer(int timerSeconds, JLabel lblTimer) {
        this.timerSeconds = timerSeconds;
        this.lblTimer = lblTimer;
        this.running = true;
        this.observers = new ArrayList<>();
        initTimer();
    }
    
    // inicializa los valores para segundos y minutos
    private void initTimer(){
        for(int i = 0; i < timerSeconds; i++){
            seconds++;
            if(seconds > 59){
                seconds = 0;
                minutes++;
                if(this.minutes > 59){
                    this.minutes = 0;
                }
            }
        }
    }
    
    // inicio del temporizador
    public void run(){
        while(this.running){
            try{
                
                sleep(1000);
                
                this.seconds--;
                if (this.seconds < 0 && this.minutes > 0){
                    this.seconds = 59;
                    this.minutes--;
                    if(this.minutes < 0){
                        this.minutes = 59;
                    }
                }
                
                if(minutes <= 0 && seconds <= 0){
                    stopRunnig();
                }

                this.lblTimer.setText(setNiceTime(this.minutes) + ":" +  setNiceTime(this.seconds));
                
            }catch(InterruptedException ex){
                Logger.getLogger(Chronometer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    // desde aqui se avisa a los observadores que ya se termino de contar
    private void stopRunnig(){
        this.running = false;
        notifyAllObservers();
    }
    
    
    private String setNiceTime(int number){
        if (number < 10)
            return "0"+number;
        return ""+number;
    }

    @Override
    public void addObserver(iObserver observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(iObserver observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyAllObservers() {
        
        for(int i = 0; i < observers.size(); i++){
            observers.get(i).notifyObserver();
        }
    }
    
    
    
}
