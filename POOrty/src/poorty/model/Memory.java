
package poorty.model;

// Clase para el juego de memoria

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import static poorty.view.MemoryWindow.LABEL_HEIGHT;
import static poorty.view.MemoryWindow.LABEL_WIDTH;
import static poorty.view.MemoryWindow.MATRIX_COL;
import static poorty.view.MemoryWindow.MATRIX_ROW;


public class Memory {
    
    private MemoryCard[][] memoryCardsMatrix;
    private ArrayList<IconMemoryCard> cardIcons;

    // solo se setean las cartas para el game host
    public Memory(MemoryCard[][] memoryCardsMatrix, boolean gameHost) {
        this.memoryCardsMatrix = memoryCardsMatrix;
        
        this.cardIcons = new ArrayList<>();
        
        if(gameHost){
            loadCardImages();
        }
        
    }
    
    public void loadCardImages(){
        File folder = new File("./src/media/memoryCards");
        ImageIcon cardIcon;
        for(File file: folder.listFiles()){
            if(!file.isDirectory()){
                try{
                    // que no sea el icon de cuando esta oculto
                    if(!file.getName().replaceFirst("[.][^.]+$", "").equals("Hide")){
                        // se carga la pareja para cada icono de carta
                        ImageIcon icon = new ImageIcon(file.getCanonicalPath());
                        this.cardIcons.add(new IconMemoryCard(file.getName().replaceFirst("[.][^.]+$", ""),icon ));
                        this.cardIcons.add(new IconMemoryCard(file.getName().replaceFirst("[.][^.]+$", ""), icon));
                    }
                   
                    // el replaceFirst elimina la extension del archivo para obtener simplemente su nombre base
                }catch(IOException e){
                    System.out.println("Error al cargar personaje");
                }
 
            }
        }
        setCardImages();
    }
    
    // seteamos de forma aleatoria las cartas en la matriz
    public void setCardImages(){
        int iconIndex;
        for (int i = 0; i < MATRIX_ROW; i++) {
            for (int j = 0; j < MATRIX_COL; j++) {
                // se generara un random para setearle el icon a cada carta
                iconIndex = new Random().nextInt(cardIcons.size());
                memoryCardsMatrix[i][j].setCardIcon(cardIcons.get(iconIndex));
                cardIcons.remove(iconIndex); // lo sacamos de la lista
            }
        }
        
    }
    
    
    
}
