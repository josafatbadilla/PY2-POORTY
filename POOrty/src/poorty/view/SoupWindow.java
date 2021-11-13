
package poorty.view;

import java.awt.Color;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import poorty.model.AlphSoupLabel;


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
    
    private AlphSoupLabel createSoupLabel(int x, int y){
        AlphSoupLabel newLabel = new AlphSoupLabel("");
        newLabel.setBounds(x, y, LABEL_SIZE, LABEL_SIZE);
        newLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        newLabel.setBackground(Color.WHITE);
        newLabel.setFont(newLabel.getFont ().deriveFont (12.0f));
        newLabel.setHorizontalAlignment(SwingConstants.CENTER);
        return newLabel;
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        pnlSoup = new javax.swing.JPanel();

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(pnlSoup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlSoup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
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
    
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new SoupWindow().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel pnlSoup;
    // End of variables declaration//GEN-END:variables
}
