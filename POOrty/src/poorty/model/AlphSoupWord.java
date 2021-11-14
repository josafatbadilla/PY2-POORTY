
// Va a representar una palabra que esta en la matriz de la sopa de letras
package poorty.model;


public class AlphSoupWord {
    private String word;
    private int i; // fila de la matriz
    private int j; // columna de la matriz
    private WordPosition position;
    
    public enum WordPosition{
        VERTICAL, HORIZONTAL, DIAGONAL
    }
    
    private boolean marked; // si ya se marco correctamente
    private boolean placed; // si la palabra se acomodo correctamente en la matriz

    public AlphSoupWord(String word,WordPosition position) {
        this.word = word;
        this.position = position;
        this.placed = false;
        this.marked = false;
        this.i = 0;
        this.j = 0;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public WordPosition getPosition() {
        return position;
    }

    public void setPosition(WordPosition position) {
        this.position = position;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    public String getWord() {
        return word;
    }

    public boolean isPlaced() {
        return placed;
    }

    public void setPlaced(boolean placed) {
        this.placed = placed;
    }
    
    
    
    
}
