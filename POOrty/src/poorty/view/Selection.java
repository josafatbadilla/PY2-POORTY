
package poorty.view;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import poorty.model.CharacterBtn;


public class Selection extends javax.swing.JFrame implements iWindow {
    
    public static ArrayList<CharacterBtn> characterBtns;
    public static final int CHARHEIGH = 110; // ALTURA DE PERSONAJE
    public static final int CHARWIDTH = 90; // ANCHO DE PERSONAJE
    public static final Color[] BTN_COLORS = {new Color(255,255,255), new Color(51,153,255), new Color(153,153,153)};
    
    public Selection() {
        initComponents();
        characterBtns = new ArrayList<>();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PnlSelection = new javax.swing.JPanel();
        pnlCharacters = new javax.swing.JPanel();
        btnRandomTurn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnDicesTurn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlCharacters.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout pnlCharactersLayout = new javax.swing.GroupLayout(pnlCharacters);
        pnlCharacters.setLayout(pnlCharactersLayout);
        pnlCharactersLayout.setHorizontalGroup(
            pnlCharactersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlCharactersLayout.setVerticalGroup(
            pnlCharactersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 378, Short.MAX_VALUE)
        );

        btnRandomTurn.setText("Numero aleatorio");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Selecciona tu personaje");

        btnDicesTurn.setText("Tirar Dados");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Selecci??n de turno");

        javax.swing.GroupLayout PnlSelectionLayout = new javax.swing.GroupLayout(PnlSelection);
        PnlSelection.setLayout(PnlSelectionLayout);
        PnlSelectionLayout.setHorizontalGroup(
            PnlSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlSelectionLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(PnlSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlCharacters, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(PnlSelectionLayout.createSequentialGroup()
                        .addComponent(btnRandomTurn, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDicesTurn, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 55, Short.MAX_VALUE))
        );
        PnlSelectionLayout.setVerticalGroup(
            PnlSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlSelectionLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(pnlCharacters, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(PnlSelectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnDicesTurn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnRandomTurn, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PnlSelection, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PnlSelection, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PnlSelection;
    private javax.swing.JButton btnDicesTurn;
    private javax.swing.JButton btnRandomTurn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel pnlCharacters;
    // End of variables declaration//GEN-END:variables

    @Override
    public void visibility(boolean setVisible) {
        this.setVisible(setVisible);
    }

    public ArrayList<CharacterBtn> getCharacterBtns() {
        return characterBtns;
    }

    public JPanel getPnlCharacters() {
        return pnlCharacters;
    }
    
    public JButton getBtnRandomTurn(){
        return btnRandomTurn;
    }

    public JPanel getPnlSelection() {
        return PnlSelection;
    }
    

    public JButton getBtnDicesTurn() {
        return btnDicesTurn;
    }
    
    
    
}
