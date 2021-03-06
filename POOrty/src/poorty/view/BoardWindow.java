
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
        lblDice2 = new javax.swing.JLabel();
        btnThrowDices = new javax.swing.JButton();
        lblDice1 = new javax.swing.JLabel();
        turnoLbl = new javax.swing.JLabel();
        btnCarcel = new javax.swing.JButton();
        playSopa = new javax.swing.JButton();
        btnContinueTurn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        playMiniGame.setText("Probar Tail");

        pnlThrowDices.setBackground(new java.awt.Color(255, 255, 255));

        lblDice2.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lblDice2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDice2.setText("0");
        lblDice2.setToolTipText("");
        lblDice2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)));

        btnThrowDices.setText("Lanzar");

        lblDice1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        lblDice1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDice1.setText("0");
        lblDice1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 255), 1, true));

        javax.swing.GroupLayout pnlThrowDicesLayout = new javax.swing.GroupLayout(pnlThrowDices);
        pnlThrowDices.setLayout(pnlThrowDicesLayout);
        pnlThrowDicesLayout.setHorizontalGroup(
            pnlThrowDicesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThrowDicesLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(lblDice1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(lblDice2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlThrowDicesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThrowDices, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlThrowDicesLayout.setVerticalGroup(
            pnlThrowDicesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThrowDicesLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(pnlThrowDicesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDice1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDice2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnThrowDices)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        turnoLbl.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        turnoLbl.setText("jLabel1");

        btnCarcel.setText("Carcel");

        playSopa.setText("Jugar Sopa");

        btnContinueTurn.setText("Continuar Turno");

        javax.swing.GroupLayout BoardPanelLayout = new javax.swing.GroupLayout(BoardPanel);
        BoardPanel.setLayout(BoardPanelLayout);
        BoardPanelLayout.setHorizontalGroup(
            BoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BoardPanelLayout.createSequentialGroup()
                .addGroup(BoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BoardPanelLayout.createSequentialGroup()
                        .addGap(224, 224, 224)
                        .addGroup(BoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pnlThrowDices, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(BoardPanelLayout.createSequentialGroup()
                                .addComponent(btnContinueTurn)
                                .addGap(169, 169, 169)
                                .addGroup(BoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(playSopa)
                                    .addComponent(playMiniGame))))
                        .addGap(145, 145, 145)
                        .addComponent(btnCarcel))
                    .addGroup(BoardPanelLayout.createSequentialGroup()
                        .addGap(303, 303, 303)
                        .addComponent(turnoLbl)))
                .addContainerGap(887, Short.MAX_VALUE))
        );
        BoardPanelLayout.setVerticalGroup(
            BoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BoardPanelLayout.createSequentialGroup()
                .addGroup(BoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BoardPanelLayout.createSequentialGroup()
                        .addGap(163, 163, 163)
                        .addComponent(playMiniGame)
                        .addGap(70, 70, 70)
                        .addComponent(btnCarcel))
                    .addGroup(BoardPanelLayout.createSequentialGroup()
                        .addGap(171, 171, 171)
                        .addComponent(btnContinueTurn)
                        .addGap(10, 10, 10)
                        .addComponent(playSopa)
                        .addGap(17, 17, 17)
                        .addComponent(pnlThrowDices, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(33, 33, 33)
                .addComponent(turnoLbl)
                .addContainerGap(55, Short.MAX_VALUE))
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
    private javax.swing.JButton btnCarcel;
    private javax.swing.JButton btnContinueTurn;
    private javax.swing.JButton btnThrowDices;
    private javax.swing.JLabel lblDice1;
    private javax.swing.JLabel lblDice2;
    private javax.swing.JButton playMiniGame;
    private javax.swing.JButton playSopa;
    private javax.swing.JPanel pnlThrowDices;
    private javax.swing.JLabel turnoLbl;
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

    public JButton getBtnContinueTurn() {
        return btnContinueTurn;
    }

    

    public JLabel getTurnoLbl() {
        return turnoLbl;
    }

    public JButton getBtnCarcel() {
        return btnCarcel;
    }

    public JButton getPlayMiniGame() {
        return playMiniGame;
    }

    public JButton getPlaySopa() {
        return playSopa;
    }
    
    

    
    
}
