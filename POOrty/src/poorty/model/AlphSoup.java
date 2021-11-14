
// Clase para el minijuego de sopa de letras

package poorty.model;

import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.Random;
import poorty.model.AlphSoupWord.WordPosition;

public class AlphSoup {
    
    private ArrayList<AlphSoupWord> soupWords; // palabras en la sopa de letras
    private ArrayList<String> words; // arreglo con todas las posibles palabras
    private AlphSoupLabel[][] alphSoupMatrix; // matriz de la ventana
    private final Gson gson = new Gson();
    private final String WORDS_FILEPATH = "./src/media/words.txt";
    
    private static final char[] ALPHABET = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    public AlphSoup(AlphSoupLabel[][] alphSoupMatrix) {
        this.alphSoupMatrix = alphSoupMatrix;
        
        soupWords = new ArrayList<>();
        
        // obtener la la lista completa de palabras
        getFileWords();
        
        // llenar la matriz de las palabras aleatorias
        fillMatrix();
    }
    
    // carga las palabras del archivo al arreglo
    private void getFileWords(){
        String fileStr = FileManager.readFile(WORDS_FILEPATH);
        if(!fileStr.equals("")){
                this.words = gson.fromJson(fileStr, new TypeToken<ArrayList<String>>(){}.getType()); // se carga la lista de palabras
        }
        
        // se seleccionan 4 palabras de forma aleatoria y se cargan en el arreglo de palabras
        for(int i = 0; i < 4; i++){
            int indexWord = new Random().nextInt(words.size() - 1);
            
            // Se agregan al arreglo de palabras
            if(i == 0){ // horizontal
                soupWords.add(new AlphSoupWord(this.words.get(indexWord), WordPosition.HORIZONTAL));
            }else if(i == 1){ // vertical
                soupWords.add(new AlphSoupWord(this.words.get(indexWord), WordPosition.VERTICAL));
            }else{ // 2 diagonales
                soupWords.add(new AlphSoupWord(this.words.get(indexWord), WordPosition.DIAGONAL));
            }
        }
        
        for(int i = 0; i < soupWords.size(); i++){
            System.out.println("Palabra : " + soupWords.get(i).getWord() + " - " + soupWords.get(i).getPosition().toString());
        }
    
    }
    
    
    // colocara las palabras seleccionadas en la matriz y luego rellenara 
    private void fillMatrix(){
        
        for(int i = 0; i < soupWords.size(); i++){
            if(soupWords.get(i).getPosition() == WordPosition.HORIZONTAL){
                // se inserta de forma horizontal
                posHorizontalWord(soupWords.get(i));
                
            }else if(soupWords.get(i).getPosition() == WordPosition.VERTICAL){
                // se insertta de forma vertical
                posVerticalWord(soupWords.get(i));
            }else{
                // se inserta de forma diagonal
                posDiagonalWord(soupWords.get(i));
            }
        }
        
        for(int i = 0; i < alphSoupMatrix.length; i++){
            for(int j = 0; j < alphSoupMatrix[0].length; j++){
                if(alphSoupMatrix[i][j].getLetter() == ' '){
                    // genera una letra aleatorio para llenarlo por completo
                    alphSoupMatrix[i][j].setLetter(ALPHABET[new Random().nextInt(ALPHABET.length - 1)]);
                }
            }
        }
        
    }
    
    // posiciona la palabra de forma hozontal en la matriz
    private void posHorizontalWord(AlphSoupWord word){
        
        // mientras no este posicionada se intenta de nuevo
        // puede llegar a enciclarse
        int iMatrix, jMatrix;
        while(!word.isPlaced()){
            iMatrix = new Random().nextInt(alphSoupMatrix.length - 1);
            
            // en horizontal se el resta el largo de la palabra para que no se salga
            jMatrix = new Random().nextInt((alphSoupMatrix.length - 1) - (word.getWord().length() - 1));
            
            if(validPosHorizontalWord(iMatrix, jMatrix, word)){
                // recorrido de la palabra
                char[] wordChars = word.getWord().toCharArray();
                for(int i = 0; i < word.getWord().length(); i++){
                    if(alphSoupMatrix[iMatrix][jMatrix].getLetter() == ' ' || alphSoupMatrix[iMatrix][jMatrix].getLetter() == wordChars[i]){

                        // si esta vacio o tiene una letra igual a la que sigue
                        alphSoupMatrix[iMatrix][jMatrix].setLetter(wordChars[i]); // se setea la letra
                    }

                    if(i + 1 >= word.getWord().length()){
                        word.setPlaced(true); // ya se coloco correctamente
                    }

                    jMatrix++;
            
                } // for
            } // if can be set
        
        } // while
        
    }
    
    // metodo que valida que se pueda colocar la palabra en esa posicion
    private boolean validPosHorizontalWord(int iMatrix, int jMatrix, AlphSoupWord word){
        
        char[] wordChars = word.getWord().toCharArray();
        
        for(int i = 0; i < word.getWord().length(); i++){
            if(alphSoupMatrix[iMatrix][jMatrix].getLetter() != ' ' && alphSoupMatrix[iMatrix][jMatrix].getLetter() != wordChars[i]){
                return false;
            }
            jMatrix++;
        }
        
        return true;
    }
    
    // posiciona la palabra de forma vertical en la matriz
    private void posVerticalWord(AlphSoupWord word){
        // mientras no este posicionada se intenta de nuevo
        // puede llegar a enciclarse
        int iMatrix, jMatrix;
        while(!word.isPlaced()){
            iMatrix = new Random().nextInt((alphSoupMatrix.length - 1) - (word.getWord().length() - 1));
            
            // en horizontal se el resta el largo de la palabra para que no se salga
            jMatrix = new Random().nextInt(alphSoupMatrix.length - 1);
            
            if(validPosVerticalWord(iMatrix, jMatrix, word)){
                char[] wordChars = word.getWord().toCharArray();
                // recorrido de la palabra
                for(int i = 0; i < word.getWord().length(); i++){
                    
                    if(alphSoupMatrix[iMatrix][jMatrix].getLetter() == ' ' || alphSoupMatrix[iMatrix][jMatrix].getLetter() == wordChars[i]){

                        // si esta vacio o tiene una letra igual a la que sigue
                        alphSoupMatrix[iMatrix][jMatrix].setLetter(wordChars[i]); // se setea la letra
                    }

                    if(i + 1 >= word.getWord().length()){
                        word.setPlaced(true); // ya se coloco correctamente
                    }

                    iMatrix++;

                } // for
            } // if can be set
        } // while
    }
    
    // metodo que valida que se pueda colocar la palabra en esa posicion
    private boolean validPosVerticalWord(int iMatrix, int jMatrix, AlphSoupWord word){
        
        char[] wordChars = word.getWord().toCharArray();
        
        for(int i = 0; i < word.getWord().length(); i++){
            if(alphSoupMatrix[iMatrix][jMatrix].getLetter() != ' ' && alphSoupMatrix[iMatrix][jMatrix].getLetter() != wordChars[i]){
                return false;
            }
            iMatrix++;
        }
        
        return true;
    }
    
    // posiciona la palabra de forma diagonal en la matriz
    private void posDiagonalWord(AlphSoupWord word){
        // mientras no este posicionada se intenta de nuevo
        // puede llegar a enciclarse
        int iMatrix, jMatrix;
        while(!word.isPlaced()){
            iMatrix = new Random().nextInt((alphSoupMatrix.length - 1) - (word.getWord().length() - 1));
            
            // en horizontal se el resta el largo de la palabra para que no se salga
            jMatrix = new Random().nextInt((alphSoupMatrix.length - 1) - (word.getWord().length() - 1));
            
            // valida que se pueda colocar
            if(validPosDiagonalWord(iMatrix, jMatrix, word)){
                char[] wordChars = word.getWord().toCharArray();
                // recorrido de la palabra
                for(int i = 0; i < word.getWord().length(); i++){

                    if(alphSoupMatrix[iMatrix][jMatrix].getLetter() == ' ' || alphSoupMatrix[iMatrix][jMatrix].getLetter() == wordChars[i]){

                        // si esta vacio o tiene una letra igual a la que sigue
                        alphSoupMatrix[iMatrix][jMatrix].setLetter(wordChars[i]); // se setea la letra
                    }

                    if(i + 1 >= word.getWord().length()){
                        word.setPlaced(true); // ya se coloco correctamente
                    }

                    iMatrix++;
                    jMatrix++;

                } // for
            } // if can be set
        } // while
    }
    
    // metodo que valida que se pueda colocar la palabra en esa posicion
    private boolean validPosDiagonalWord(int iMatrix, int jMatrix, AlphSoupWord word){
        
        char[] wordChars = word.getWord().toCharArray();
        
        for(int i = 0; i < word.getWord().length(); i++){
            if(alphSoupMatrix[iMatrix][jMatrix].getLetter() != ' ' && alphSoupMatrix[iMatrix][jMatrix].getLetter() != wordChars[i]){
                return false;
            }
            iMatrix++;
            jMatrix++;
        }
        
        return true;
    }
    
    
    // GETTERS AND SETTER

    public ArrayList<AlphSoupWord> getSoupWords() {
        return soupWords;
    }
    
    
    
}
