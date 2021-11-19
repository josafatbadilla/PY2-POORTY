
package poorty.view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class RandomTurnWindow extends javax.swing.JFrame implements iWindow {

    
    public RandomTurnWindow() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        pnlTurn = new javax.swing.JPanel();
        txfPlayerNum = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnSendNum = new javax.swing.JButton();
        lblRandomNum = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnStartGame = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Selección del turno");

        pnlTurn.setBackground(new java.awt.Color(255, 255, 255));

        txfPlayerNum.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txfPlayerNum.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel2.setText("Ingrese un numero entre 1-1000");

        btnSendNum.setText("Enviar");

        lblRandomNum.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblRandomNum.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel4.setText("Numero Aleatorio");

        javax.swing.GroupLayout pnlTurnLayout = new javax.swing.GroupLayout(pnlTurn);
        pnlTurn.setLayout(pnlTurnLayout);
        pnlTurnLayout.setHorizontalGroup(
            pnlTurnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTurnLayout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addGroup(pnlTurnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblRandomNum, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txfPlayerNum)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlTurnLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(btnSendNum, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))
                .addContainerGap(114, Short.MAX_VALUE))
        );
        pnlTurnLayout.setVerticalGroup(
            pnlTurnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTurnLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txfPlayerNum, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSendNum, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblRandomNum, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        btnStartGame.setText("Jugar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(54, Short.MAX_VALUE)
                .addComponent(pnlTurn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(159, 159, 159)
                        .addComponent(btnStartGame, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlTurn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnStartGame, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSendNum;
    private javax.swing.JButton btnStartGame;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lblRandomNum;
    private javax.swing.JPanel pnlTurn;
    private javax.swing.JTextField txfPlayerNum;
    // End of variables declaration//GEN-END:variables

    @Override
    public void visibility(boolean setVisible) {
        this.setVisible(setVisible);
    }
    
    
    // getters and setters

    public JButton getBtnRandomNum() {
        return btnSendNum;
    }

    public JPanel getPnlTurn() {
        return pnlTurn;
    }

    public JButton getBtnSendNum() {
        return btnSendNum;
    }

    public JButton getBtnStartGame() {
        return btnStartGame;
    }

    public JLabel getLblRandomNum() {
        return lblRandomNum;
    }

    public JTextField getTxfPlayerNum() {
        return txfPlayerNum;
    }
    
    
    



}
