
// Clase para el minijuego de sopa de letras

package poorty.model;

import java.util.ArrayList;

public class AlphSoup {
    
    private ArrayList<AlphSoupWord> soupWords; // palabras en la sopa de letras
    private ArrayList<String> words; // arreglo con todas las posibles palabras
    private AlphSoupLabel[][] alphSoupMatrix; // matriz de la ventana

    public AlphSoup(AlphSoupLabel[][] alphSoupMatrix) {
        this.alphSoupMatrix = alphSoupMatrix;
        
        // obtener la la lista completa de palabras
        // llenar la matriz de las palabras aleatorias
    }
    
    
}
