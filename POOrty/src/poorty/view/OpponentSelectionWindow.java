/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poorty.view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author josa
 */
public class OpponentSelectionWindow extends javax.swing.JFrame implements iWindow{

    /**
     * Creates new form OpponentSelectionWindow
     */
    public OpponentSelectionWindow() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SelectionPanel = new javax.swing.JPanel();
        btnContinue = new javax.swing.JButton();
        nameLbl = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnContinue.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnContinue.setText("Continuar");

        nameLbl.setBackground(new java.awt.Color(255, 255, 255));
        nameLbl.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        nameLbl.setText("Nombre ventana");
        nameLbl.setOpaque(true);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Selecciona al jugador al que quieres enviar el castigo");
        jLabel1.setOpaque(true);

        javax.swing.GroupLayout SelectionPanelLayout = new javax.swing.GroupLayout(SelectionPanel);
        SelectionPanel.setLayout(SelectionPanelLayout);
        SelectionPanelLayout.setHorizontalGroup(
            SelectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SelectionPanelLayout.createSequentialGroup()
                .addGroup(SelectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SelectionPanelLayout.createSequentialGroup()
                        .addGap(210, 210, 210)
                        .addComponent(nameLbl))
                    .addGroup(SelectionPanelLayout.createSequentialGroup()
                        .addGap(224, 224, 224)
                        .addComponent(btnContinue))
                    .addGroup(SelectionPanelLayout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addComponent(jLabel1)))
                .addContainerGap(159, Short.MAX_VALUE))
        );
        SelectionPanelLayout.setVerticalGroup(
            SelectionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SelectionPanelLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(nameLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 224, Short.MAX_VALUE)
                .addComponent(btnContinue)
                .addGap(44, 44, 44))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(SelectionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SelectionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void visibility(boolean setVisible) {
        this.setVisible(setVisible);
    }

    public JButton getBtnContinue() {
        return btnContinue;
    }

    public JPanel getSelectionPanel() {
        return SelectionPanel;
    }

    public JLabel getNameLbl() {
        return nameLbl;
    }
    
    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel SelectionPanel;
    private javax.swing.JButton btnContinue;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel nameLbl;
    // End of variables declaration//GEN-END:variables
}
