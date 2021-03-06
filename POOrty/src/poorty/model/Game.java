
// Clase que contiene toda la logica de los juegos y tablero y las pantallas

package poorty.model;

import java.io.File;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import poorty.controller.MainController;

public class Game {
    private Player player;
    private MainController mainController;
    private ArrayList<Character> characters; // lista de los personajes del juego
    private ArrayList<Character> charactersIcons;
    private ArrayList<ImageIcon> backgrounds;
    private ThreadThrowDices throwDices;
    
    // tablero
    
    // minijuegos
    private AlphSoup alphSoup; // sopa de letras
    private Memory memoryGame; // super bros memorys
    private CollectCoins collectCoins;
    
    
    public Game(MainController mainController){
        this.mainController = mainController;
        this.player = new Player(this.mainController); // se le pasa el controlador principal

        // carga de los personajes
        loadCharacters();
        loadCharactersIcons();
        loadBackgroudns();
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
    
    // carga los personajes que esten en la carpeta de media/Iconscharacters
    private void loadCharactersIcons(){
        this.charactersIcons = new ArrayList<>();
        ImageIcon charIcon;
        File folder = new File("./src/media/iconsCharacters");
        for(File file: folder.listFiles()){
            if(!file.isDirectory()){
                try{
                    charIcon = new ImageIcon(file.getCanonicalPath());
                    this.charactersIcons.add(new Character(file.getName().replaceFirst("[.][^.]+$", ""), charIcon));
                    // el replaceFirst elimina la extension del archivo para obtener simplemente su nombre base
                }catch(IOException e){
                    System.out.println("Error al cargar personaje");
                }
 
            }
        }
    }
    
    private void loadBackgroudns(){
        this.backgrounds = new ArrayList<>();
        ImageIcon charIcon;
        File folder = new File("./src/media/backgrounds");
        for(File file: folder.listFiles()){
            if(!file.isDirectory()){
                try{
                    charIcon = new ImageIcon(file.getCanonicalPath());
                    this.backgrounds.add(charIcon);
                }catch(IOException e){
                    System.out.println("Error al cargar personaje");
                }
 
            }
        }
    }
    
    
    // funcion para tirar los dados
    public int throwDices(JLabel lblDice1, JLabel lblDice2) throws InterruptedException{
        int dice1Num = (int)(Math.random()*6+1);
        int dice2Num = (int)(Math.random()*6+1);
        
        // animacion para lanzar los dados
        throwDices = new ThreadThrowDices(lblDice1, dice1Num, lblDice2, dice2Num);
        System.out.println("ThrowDices = " +throwDices.getDicesResult());
        // retorna el resultado de los dados
        return dice1Num + dice2Num;
    }
    
    
    
    // getters and setters

    public Player getPlayer() {
        return player;
    }
    
    public ArrayList<Character> getCharacters(){
        return characters;
    }

    public ThreadThrowDices getThrowDices() {
        return throwDices;
    }

    public ArrayList<Character> getCharactersIcons() {
        return charactersIcons;
    }

    public ArrayList<ImageIcon> getBackgrounds() {
        return backgrounds;
    }

    public void setAlphSoup(AlphSoup alphSoup) {
        this.alphSoup = alphSoup;
    }
    
    public AlphSoup getAlphSoup() {
        return alphSoup;
    }

    public Memory getMemoryGame() {
        return memoryGame;
    }

    public void setMemoryGame(Memory memoryGame) {
        this.memoryGame = memoryGame;
    }

    public CollectCoins getCollectCoins() {
        return collectCoins;
    }

    public void setCollectCoins(CollectCoins collectCoins) {
        this.collectCoins = collectCoins;
    }
    
    
    
    
    
    
    
}
