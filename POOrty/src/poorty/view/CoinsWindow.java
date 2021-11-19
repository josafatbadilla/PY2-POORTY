/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poorty.view;

import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import poorty.controller.MainController;
import poorty.model.Coin;

/**
 *
 * @author John
 */
public class CoinsWindow extends javax.swing.JFrame implements iWindow{
    
    private Coin[][] coins;
    public static final int LABEL_SIZE = 25;
    public static final int MATRIX_SIZE = 25;
    private ImageIcon coinIcon;
    
    public CoinsWindow() {
        initComponents();
        System.out.println("Se crea la ventan de las monedas");
        this.coins = new Coin[MATRIX_SIZE][MATRIX_SIZE];
        loadCoinIcon();
        initCoins();
    }
    
    // carga el icono para las monedas
    private void loadCoinIcon(){
        File folder = new File("./src/media/collectCoins");
        for(File file: folder.listFiles()){
            if(!file.isDirectory()){
                try{
                    // carga solo el archivo que se desea
                    if(file.getName().replaceFirst("[.][^.]+$", "").equals("coinStatic")){
                        this.coinIcon = new ImageIcon(file.getCanonicalPath()); 
                    }
                   
                    // el replaceFirst elimina la extension del archivo para obtener simplemente su nombre base
                }catch(IOException e){
                    System.out.println("Error al cargar la moneda");
                }
 
            }
        }
    }

    // se inicializa la matriz de monedas
    private void initCoins(){
        int x = 0, y = 0;
        for(int i = 0; i < MATRIX_SIZE; i++){
            for(int j = 0; j < MATRIX_SIZE; j++){
                coins[i][j] = createCoin(x, y, i, j);
                pnlCoins.add(coins[i][j]);
                x += LABEL_SIZE;
            }
            
            x = 0;
            y += LABEL_SIZE;
        }
    }
    
    
    private Coin createCoin(int x, int y, int i, int j){
        Coin newCoin = new Coin(i, j, MainController.resizeIcon(coinIcon, LABEL_SIZE, LABEL_SIZE));
        newCoin.setBounds(x, y, LABEL_SIZE, LABEL_SIZE);
        return newCoin;
    }
    
    
    // getters and setters

    public Coin[][] getCoins() {
        return coins;
    }

    public JButton getBtnGameControl() {
        return btnGameControl;
    }

    public JLabel getLblResult() {
        return lblResult;
    }

    public JLabel getLblTime() {
        return lblTime;
    }

    public JLabel getLblScore() {
        return lblScore;
    }
    
    
    
    
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlCoins = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblScore = new javax.swing.JLabel();
        btnGameControl = new javax.swing.JButton();
        lblResult = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        pnlCoins.setBackground(new java.awt.Color(255, 255, 255));
        pnlCoins.setPreferredSize(new java.awt.Dimension(625, 625));

        javax.swing.GroupLayout pnlCoinsLayout = new javax.swing.GroupLayout(pnlCoins);
        pnlCoins.setLayout(pnlCoinsLayout);
        pnlCoinsLayout.setHorizontalGroup(
            pnlCoinsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 625, Short.MAX_VALUE)
        );
        pnlCoinsLayout.setVerticalGroup(
            pnlCoinsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 625, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Collect the coins");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Tiempo:");

        lblTime.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTime.setText("0:00");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Puntos:");

        lblScore.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblScore.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblScore.setText("0");

        btnGameControl.setText("Iniciar");

        lblResult.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblResult.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblResult.setText("Sin iniciar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlCoins, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addComponent(btnGameControl, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblScore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblResult, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pnlCoins, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(83, 83, 83)
                .addComponent(jLabel3)
                .addGap(11, 11, 11)
                .addComponent(lblTime)
                .addGap(42, 42, 42)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(lblScore)
                .addGap(65, 65, 65)
                .addComponent(lblResult, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGameControl, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGameControl;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lblResult;
    private javax.swing.JLabel lblScore;
    private javax.swing.JLabel lblTime;
    private javax.swing.JPanel pnlCoins;
    // End of variables declaration//GEN-END:variables

    @Override
    public void visibility(boolean setVisible) {
        this.setVisible(setVisible);
    }
}
