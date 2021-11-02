
// Clase que contiene toda la logica de los juegos y tablero y las pantallas

package poorty.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import poorty.controller.MainController;

public class Game {
    private Player player;
    private MainController mainController;
    private ArrayList<Character> characters; // lista de los personajes del juego
    
    // tablero
    
    // minijuegos
    
    
    public Game(MainController mainController){
        this.mainController = mainController;
        this.player = new Player(this.mainController); // se le pasa el controlador principal

        // carga de los personajes
        loadCharacters();
    }
    
    // carga los personajes que esten en la carpeta de media/characters
    private void loadCharacters(){
        this.characters = new ArrayList<>();
        ImageIcon charIcon;
        File folder = new File("./src/media/characters");
        for(File file: folder.listFiles()){
            if(!file.isDirectory()){
                try{
                    charIcon = new ImageIcon(file.getCanonicalPath());
                    this.characters.add(new Character(file.getName().replaceFirst("[.][^.]+$", ""), charIcon));
                    // el replaceFirst elimina la extension del archivo para obtener simplemente su nombre base
                }catch(IOException e){
                    System.out.println("Error al cargar personaje");
                }
 
            }
        }
    }
    
    // getters and setters

    public Player getPlayer() {
        return player;
    }
    
    public ArrayList<Character> getCharacters(){
        return characters;
    }
    
}
