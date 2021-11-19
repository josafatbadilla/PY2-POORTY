
package poorty.view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class Lobby extends javax.swing.JFrame implements iWindow {

 
    public Lobby() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LobbyPnl = new javax.swing.JPanel();
        btnPlay = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaPlayers = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnPlay.setText("Continuar");

        txaPlayers.setEditable(false);
        txaPlayers.setColumns(20);
        txaPlayers.setRows(5);
        jScrollPane1.setViewportView(txaPlayers);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Lobby");
        jLabel1.setToolTipText("");

        javax.swing.GroupLayout LobbyPnlLayout = new javax.swing.GroupLayout(LobbyPnl);
        LobbyPnl.setLayout(LobbyPnlLayout);
        LobbyPnlLayout.setHorizontalGroup(
            LobbyPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LobbyPnlLayout.createSequentialGroup()
                .addGroup(LobbyPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LobbyPnlLayout.createSequentialGroup()
                        .addGap(215, 215, 215)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(LobbyPnlLayout.createSequentialGroup()
                        .addGap(215, 215, 215)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(LobbyPnlLayout.createSequentialGroup()
                        .addGap(290, 290, 290)
                        .addComponent(btnPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(279, Short.MAX_VALUE))
        );
        LobbyPnlLayout.setVerticalGroup(
            LobbyPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LobbyPnlLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel1)
                .addGap(43, 43, 43)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(btnPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LobbyPnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LobbyPnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel LobbyPnl;
    private javax.swing.JButton btnPlay;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txaPlayers;
    // End of variables declaration//GEN-END:variables

    // getters ans setters
    public JButton getBtnPlay() {
        return btnPlay;
    }

    public JTextArea getTxaPlayers() {
        return txaPlayers;
    }

    public JPanel getLobbyPnl() {
        return LobbyPnl;
    }

    

    @Override
    public void visibility(boolean setVisible) {
        this.setVisible(setVisible);
    }

    


}
