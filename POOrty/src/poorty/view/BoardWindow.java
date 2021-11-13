
package poorty.view;

import java.awt.TextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class BoardWindow extends javax.swing.JFrame implements iWindow{

   
    public BoardWindow() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BoardPanel = new javax.swing.JPanel();
        playMiniGame = new javax.swing.JButton();
        pnlThrowDices = new javax.swing.JPanel();
        lblDice1 = new javax.swing.JLabel();
        lblDice2 = new javax.swing.JLabel();
        btnThrowDices = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        playMiniGame.setText("Jugar Gato");

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

        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout BoardPanelLayout = new javax.swing.GroupLayout(BoardPanel);
        BoardPanel.setLayout(BoardPanelLayout);
        BoardPanelLayout.setHorizontalGroup(
            BoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BoardPanelLayout.createSequentialGroup()
                .addGroup(BoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BoardPanelLayout.createSequentialGroup()
                        .addGap(466, 466, 466)
                        .addComponent(playMiniGame))
                    .addGroup(BoardPanelLayout.createSequentialGroup()
                        .addGap(265, 265, 265)
                        .addComponent(pnlThrowDices, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(BoardPanelLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel1)))
                .addContainerGap(904, Short.MAX_VALUE))
        );
        BoardPanelLayout.setVerticalGroup(
            BoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BoardPanelLayout.createSequentialGroup()
                .addGap(140, 140, 140)
                .addComponent(jLabel1)
                .addGap(9, 9, 9)
                .addComponent(playMiniGame)
                .addGap(18, 18, 18)
                .addComponent(pnlThrowDices, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BoardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BoardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BoardPanel;
    private javax.swing.JButton btnThrowDices;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblDice1;
    private javax.swing.JLabel lblDice2;
    private javax.swing.JButton playMiniGame;
    private javax.swing.JPanel pnlThrowDices;
    // End of variables declaration//GEN-END:variables

    @Override
    public void visibility(boolean setVisible) {
        this.setVisible(setVisible);
    }

    public JPanel getBoardPanel() {
        return BoardPanel;
    }

    public JButton getBtnThrowDices() {
        return btnThrowDices;
    }

    public JLabel getLblDice1() {
        return lblDice1;
    }

    public JLabel getLblDice2() {
        return lblDice2;
    }

    public JPanel getPnlThrowDices() {
        return pnlThrowDices;
    }

    public JLabel getjLabel1() {
        return jLabel1;
    }

    
    
    
    
    public JButton getPlayMiniGame() {
        return playMiniGame;
    }

    
    
}
