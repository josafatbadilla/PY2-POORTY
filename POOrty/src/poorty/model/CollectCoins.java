
package poorty.model;

// modelo para el juego de recolectar las monedas

import java.util.Random;
import poorty.view.CoinsWindow;


public class CollectCoins {
    
    private Coin[][] coins;
    // entre ambas suman las 625 monedas que deben haber
    private int posCoinCounter = 313;
    private int negCoinCounter = 312;

    public CollectCoins(Coin[][] coins) {
        this.coins = coins;
        setCoinsValues();
    }
    
    private void setCoinsValues(){
        
        for(int i = 0; i < CoinsWindow.MATRIX_SIZE; i++){
            for(int j = 0; j < CoinsWindow.MATRIX_SIZE; j++){
                // genera un random de 1 a 10
                coins[i][j].setValue((new Random().nextInt(10) + 1) * getCoinSign());
            }
        }
    }
    
    
    // retorna 1 o -1 para darle el signo a la moneda
    private int getCoinSign(){
        if(posCoinCounter > 0 && negCoinCounter > 0){
            // escoge de forma aleatoria un signo
            int sign = new Random().nextInt(1);
            if(sign == 1) this.posCoinCounter--;
            else this.negCoinCounter--;
            return sign == 1 ? 1 : -1;
        }else if(posCoinCounter >0){
            // no quedan negativas
            this.posCoinCounter--;
            return 1;
        }else{
            // no quedan postivas
            this.negCoinCounter--;
            return -1;
        }
    
    }
}
    
