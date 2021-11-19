
package poorty.view;

import javax.swing.JButton;
import javax.swing.JLabel;

public class DicesTurnWindow extends javax.swing.JFrame implements iWindow {

    
    public DicesTurnWindow() {
        initComponents();
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlThrowDices = new javax.swing.JPanel();
        lblDice1 = new javax.swing.JLabel();
        lblDice2 = new javax.swing.JLabel();
        btnThrowDices = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnStartGame = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlThrowDices.setBackground(new java.awt.Color(255, 255, 255));

        lblDice1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lblDice1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDice1.setText("0");
        lblDice1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 255), 1, true));

        lblDice2.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lblDice2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDice2.setText("0");
        lblDice2.setToolTipText("");
        lblDice2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)));

        btnThrowDices.setText("Lanzar");

        javax.swing.GroupLayout pnlThrowDicesLayout = new javax.swing.GroupLayout(pnlThrowDices);
        pnlThrowDices.setLayout(pnlThrowDicesLayout);
        pnlThrowDicesLayout.setHorizontalGroup(
            pnlThrowDicesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThrowDicesLayout.createSequentialGroup()
                .addGap(98, 98, 98)
                .addGroup(pnlThrowDicesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThrowDices, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlThrowDicesLayout.createSequentialGroup()
                        .addComponent(lblDice1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblDice2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(92, Short.MAX_VALUE))
        );
        pnlThrowDicesLayout.setVerticalGroup(
            pnlThrowDicesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThrowDicesLayout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addGroup(pnlThrowDicesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDice1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDice2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addComponent(btnThrowDices)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Selección de turno");

        btnStartGame.setText("Jugar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(159, 159, 159)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(pnlThrowDices, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(81, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnStartGame, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(207, 207, 207))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(pnlThrowDices, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnStartGame, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void visibility(boolean setVisible) {
        this.setVisible(setVisible);
    }
    
    // getters and setters
    
    public JButton getBtnThrowDices() {
        return btnThrowDices;
    }

    public JLabel getLblDice1() {
        return lblDice1;
    }

    public JLabel getLblDice2() {
        return lblDice2;
    }

    public JButton getBtnStartGame() {
        return btnStartGame;
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnStartGame;
    private javax.swing.JButton btnThrowDices;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblDice1;
    private javax.swing.JLabel lblDice2;
    private javax.swing.JPanel pnlThrowDices;
    // End of variables declaration//GEN-END:variables
}
