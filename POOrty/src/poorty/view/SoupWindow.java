
package poorty.view;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import poorty.model.AlphSoupLabel;
import poorty.model.AlphSoupWord;


public class SoupWindow extends javax.swing.JFrame implements iWindow{
    
    private AlphSoupLabel[][] alphaSoupMatrix; // matriz de la sopa de letras
    private final int[] matrixSize = {10, 15, 20};
    public static final int LABEL_SIZE = 25; // size para el espacio de cada letra
    
    public SoupWindow() {
        initComponents(); 
        // genera un numero aleatorio para indicar el size de la matriz de la sopa de letras
        generateAlphMatrix(matrixSize[new Random().nextInt(matrixSize.length - 1)]);
    }
    
    
    // generacion de la matriz de botones
    private void generateAlphMatrix(int size){
        alphaSoupMatrix = new AlphSoupLabel[size][size];
        int x = 0, y = 0;
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                alphaSoupMatrix[i][j] = createSoupLabel(x, y);
                pnlSoup.add(alphaSoupMatrix[i][j]);
                x += LABEL_SIZE;
            }
            
            x = 0;
            y += LABEL_SIZE;
        }
        
    }
    
    // las palabras generadas se imprimen
    public void generateWordList(ArrayList<AlphSoupWord> soupWords){
        int x = 0, y = 50;
        for(int i = 0; i < soupWords.size(); i++){
            JLabel label = createWordLabel(x, y, soupWords.get(i).getWord());
            pnlWordList.add(label);
            y += 50;
        }
    }
    
    private AlphSoupLabel createSoupLabel(int x, int y){
        AlphSoupLabel newLabel = new AlphSoupLabel(' ');
        newLabel.setBounds(x, y, LABEL_SIZE, LABEL_SIZE);
        newLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        newLabel.setBackground(Color.WHITE);
        newLabel.setFont(newLabel.getFont ().deriveFont (12.0f));
        newLabel.setHorizontalAlignment(SwingConstants.CENTER);
        return newLabel;
    }
    
    private JLabel createWordLabel(int x, int y, String word){
        JLabel newLabel = new JLabel(word);
        newLabel.setBounds(x, y, pnlWordList.getWidth(), 50);
        newLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
        newLabel.setHorizontalAlignment(SwingConstants.CENTER);
        return newLabel;
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        pnlSoup = new javax.swing.JPanel();
        pnlWordList = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblTimer = new javax.swing.JLabel();
        btnCheckSoup = new javax.swing.JButton();
        lblMessage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Sopa de letras");

        pnlSoup.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlSoupLayout = new javax.swing.GroupLayout(pnlSoup);
        pnlSoup.setLayout(pnlSoupLayout);
        pnlSoupLayout.setHorizontalGroup(
            pnlSoupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
        pnlSoupLayout.setVerticalGroup(
            pnlSoupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        pnlWordList.setBackground(new java.awt.Color(255, 255, 255));
        pnlWordList.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Palabras");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Tiempo");

        lblTimer.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblTimer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTimer.setText("00:00");

        btnCheckSoup.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCheckSoup.setText("Verificar");

        lblMessage.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout pnlWordListLayout = new javax.swing.GroupLayout(pnlWordList);
        pnlWordList.setLayout(pnlWordListLayout);
        pnlWordListLayout.setHorizontalGroup(
            pnlWordListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlWordListLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(btnCheckSoup, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlWordListLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlWordListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTimer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                    .addComponent(lblMessage, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlWordListLayout.setVerticalGroup(
            pnlWordListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlWordListLayout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 342, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTimer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCheckSoup)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlSoup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pnlWordList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlSoup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlWordList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void visibility(boolean setVisible) {
        this.setVisible(setVisible);
    }
    
    // getters and setterss

    public AlphSoupLabel[][] getAlphaSoupMatrix() {
        return alphaSoupMatrix;
    }
    
    
    public JPanel getPnlSoup() {
        return pnlSoup;
    }

    public JButton getBtnCheckSoup() {
        return btnCheckSoup;
    }

    public JLabel getLblTimer() {
        return lblTimer;
    }

    public JLabel getLblMessage() {
        return lblMessage;
    }
    
    
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCheckSoup;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblMessage;
    private javax.swing.JLabel lblTimer;
    private javax.swing.JPanel pnlSoup;
    private javax.swing.JPanel pnlWordList;
    // End of variables declaration//GEN-END:variables
}
