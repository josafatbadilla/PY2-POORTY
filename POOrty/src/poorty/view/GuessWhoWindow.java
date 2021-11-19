
package poorty.view;

import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import poorty.controller.MainController;


public class GuessWhoWindow extends javax.swing.JFrame  implements iWindow{
    
    private ArrayList<JButton> gameOptions; // opciones para el juego
    private ArrayList<ImageIcon> images; // las posibles imagenes para colocar en el panel
    private String answer; // donde se guarda la respuesta correcta
    private ImageIcon questionIcon;
    private JLabel whoLabel;
    private JLabel[][] coverMatrix;
    private JLabel labelResult;
    private JButton btnCloseGame;
    
    // establecimineto de valores para los componentes graficos
    public final int LABEL_SIZE = 45; // tamano para el label con el question mark
    public final int BUTTON_HEIGH = 30;
    public final int MATRIX_SIZE = 10; // matriz sera de 10 x 10
    
    public GuessWhoWindow() {
        initComponents();
        // inicializacion de la matriz
        this.coverMatrix = new JLabel[MATRIX_SIZE][MATRIX_SIZE];
        this.images = new ArrayList<>();
        this.gameOptions = new ArrayList<>();
        initGame();
    }

    
    // inicializacion de todos los componentes necesarios para el juego
        // colocacion de la imgagen aleatoria
        //Carga de botones
        // guardar el nombre de la opcion correcta
        // creacion de la matriz de labels con el signo de pregunta
        // de forma aleatoria quita algunos
    private void initGame(){
        // se cargan las imagenes del folder guess who
        File folder = new File("./src/media/guessWho");
        ImageIcon cardIcon;
        int btnPos = 0;
        for(File file: folder.listFiles()){
            if(!file.isDirectory()){
                try{
                    // que no sea el icon de cuando esta oculto
                    if(!file.getName().replaceFirst("[.][^.]+$", "").equals("Question")){
                        // se crean las posibles imagenes a poner de fondo
                        // su descripcion es el nombre del archivo
                        images.add(new ImageIcon(file.getCanonicalPath(), file.getName().replaceFirst("[.][^.]+$", ""))); 
                        JButton btnOp = createOptionButton(file.getName().replaceFirst("[.][^.]+$", ""), btnPos);
                        gameOptions.add(btnOp); // se agrea a la lista de opciones
                        pnlOptions.add(btnOp); // se agrega al panel de opciones
                        btnPos++;
                    }else{
                        this.questionIcon = new ImageIcon(file.getCanonicalPath()); // imagen del question mark
                    }
                   
                    // el replaceFirst elimina la extension del archivo para obtener simplemente su nombre base
                }catch(IOException e){
                    System.out.println("Error al cargar personaje");
                }
 
            }
        }
        
        // seteo del label de finalizacion y el boton de terminar el juego
        setFinalOptionSection(btnPos);
        
        generateCoverMatrix();
        setGuessWho();
    }
   
    
    // escoge una imgaen de forma aleatoria y se la coloca al panel
    private void setGuessWho(){
        int randIndex = new Random().nextInt(images.size());
        this.whoLabel = new JLabel(MainController.resizeIcon(images.get(randIndex), pnlWho.getWidth(), pnlWho.getHeight()));
        this.whoLabel.setBounds(0,0, pnlWho.getWidth(), pnlWho.getHeight());
        this.pnlWho.add(whoLabel);
        
        this.answer = images.get(randIndex).getDescription(); // obtiene la descripcion

    }
    
    private JButton createOptionButton(String text, int btnPos){
        JButton newButton = new JButton(text);
        newButton.setBounds(0, btnPos * BUTTON_HEIGH, pnlOptions.getWidth(), BUTTON_HEIGH);
        newButton.setFont(new Font("Verdana", Font.PLAIN, 12));
        newButton.setHorizontalAlignment(SwingConstants.CENTER);
        return newButton;
    }
    
    private void setFinalOptionSection(int lastBtnPos){
        this.labelResult = new JLabel("");
        this.labelResult.setBounds(0, lastBtnPos * BUTTON_HEIGH - 5, pnlOptions.getWidth(), BUTTON_HEIGH);
        this.labelResult.setFont(new Font("Verdana", Font.PLAIN, 12));
        this.labelResult.setHorizontalAlignment(SwingConstants.CENTER);
        pnlOptions.add(labelResult);
        this.btnCloseGame = new JButton("Salir");
        this.btnCloseGame.setEnabled(false);
        this.btnCloseGame.setBounds(0, ((lastBtnPos + 1) * BUTTON_HEIGH) - 10, pnlOptions.getWidth(), BUTTON_HEIGH);
        this.btnCloseGame.setFont(new Font("Verdana", Font.PLAIN, 12));
        this.btnCloseGame.setHorizontalAlignment(SwingConstants.CENTER);
        pnlOptions.add(btnCloseGame);
    }

    // generacion de la matriz para ocultar la imagen
    private void generateCoverMatrix(){
        int x = 5, y = 5;
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                // creacion de los labels de covertura
                JLabel newLabel = createCoverLabel(x, y);
                this.coverMatrix[i][j] = newLabel;
                this.pnlWho.add(newLabel);
                
                x += LABEL_SIZE + 5; // + 5 para el margen
            }
            x = 5;
            y += LABEL_SIZE + 5;
        }
        
        // mostrar de forma aleatoria partes de la imagen
        unCoverLabels();
    }
    
    private JLabel createCoverLabel(int x, int y){
        JLabel newLabel = new JLabel(MainController.resizeIcon(questionIcon, LABEL_SIZE, LABEL_SIZE));
        newLabel.setBounds(x, y, LABEL_SIZE, LABEL_SIZE);
        return newLabel;
    }
    
    private void unCoverLabels(){
        // genera un numero entre 4 y 8
        int uncoverLabels = new Random().nextInt(8-4+1) + 4;
        
        for(int i = 0; i < uncoverLabels; i++){
            boolean uncover = false;
            while(!uncover){
                int iMatrix = new Random().nextInt(MATRIX_SIZE);
                int jMatrix = new Random().nextInt(MATRIX_SIZE);
                
                if(coverMatrix[iMatrix][jMatrix].isVisible()){
                    coverMatrix[iMatrix][jMatrix].setVisible(false);
                    uncover = true;
                }
            }
        
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlWho = new javax.swing.JPanel();
        pnlOptions = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlWho.setBackground(new java.awt.Color(255, 255, 255));
        pnlWho.setPreferredSize(new java.awt.Dimension(500, 500));

        javax.swing.GroupLayout pnlWhoLayout = new javax.swing.GroupLayout(pnlWho);
        pnlWho.setLayout(pnlWhoLayout);
        pnlWhoLayout.setHorizontalGroup(
            pnlWhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 508, Short.MAX_VALUE)
        );
        pnlWhoLayout.setVerticalGroup(
            pnlWhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        pnlOptions.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout pnlOptionsLayout = new javax.swing.GroupLayout(pnlOptions);
        pnlOptions.setLayout(pnlOptionsLayout);
        pnlOptionsLayout.setHorizontalGroup(
            pnlOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        pnlOptionsLayout.setVerticalGroup(
            pnlOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 498, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Guess Who");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlWho, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pnlOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlOptions, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlWho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public ArrayList<JButton> getGameOptions() {
        return gameOptions;
    }

    public String getAnswer() {
        return answer;
    }

    public JLabel getLabelResult() {
        return labelResult;
    }

    public JButton getBtnCloseGame() {
        return btnCloseGame;
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel pnlOptions;
    private javax.swing.JPanel pnlWho;
    // End of variables declaration//GEN-END:variables

    @Override
    public void visibility(boolean setVisible) {
        this.setVisible(setVisible);
    }
}
