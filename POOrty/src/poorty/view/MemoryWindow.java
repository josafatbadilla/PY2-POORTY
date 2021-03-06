
package poorty.view;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import poorty.controller.MainController;
import poorty.model.MemoryCard;


public class MemoryWindow extends javax.swing.JFrame implements iWindow{
    
    private MemoryCard[][] memoryCardMatrix;
    public static final int MATRIX_ROW = 3;
    public static final int MATRIX_COL = 6;
    
    public static final int LABEL_WIDTH = 100;
    public static final int LABEL_HEIGHT = 120;
    
    public MemoryWindow() {
        initComponents();
        this.memoryCardMatrix = new MemoryCard[MATRIX_ROW][MATRIX_COL];
        initMemoryCards();
    }
    
    private void initMemoryCards(){
        int x = 5, y = 5;
        for (int i = 0; i < MATRIX_ROW; i++) {
            for (int j = 0; j < MATRIX_COL; j++) {
                memoryCardMatrix[i][j] = createMemoryCard(x, y, i, j);
                pnlCards.add(memoryCardMatrix[i][j]);
                
                x += LABEL_WIDTH + 5;
            }
            
            x = 5;
            y += LABEL_HEIGHT + 5;
        }
    }
    
    private MemoryCard createMemoryCard(int x, int y, int i, int j){
        ImageIcon hideIcon = new ImageIcon("./src/media/memoryCards/Hide.png");
        MemoryCard newLabel = new MemoryCard(MainController.resizeIcon(hideIcon, LABEL_WIDTH, LABEL_HEIGHT), i, j);
        newLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        newLabel.setBounds(x, y, LABEL_WIDTH, LABEL_HEIGHT);
        return newLabel;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        pnlCards = new javax.swing.JPanel();
        lblTurn = new javax.swing.JLabel();
        btnCheckMoves = new javax.swing.JButton();
        lblScore = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Super Bro's Memory");

        pnlCards.setBackground(new java.awt.Color(255, 255, 255));
        pnlCards.setPreferredSize(new java.awt.Dimension(515, 320));

        javax.swing.GroupLayout pnlCardsLayout = new javax.swing.GroupLayout(pnlCards);
        pnlCards.setLayout(pnlCardsLayout);
        pnlCardsLayout.setHorizontalGroup(
            pnlCardsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 635, Short.MAX_VALUE)
        );
        pnlCardsLayout.setVerticalGroup(
            pnlCardsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 380, Short.MAX_VALUE)
        );

        lblTurn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblTurn.setText("Tu turno");

        btnCheckMoves.setText("Jugar");

        lblScore.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblScore.setText("Puntaje: 0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(292, 292, 292))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pnlCards, javax.swing.GroupLayout.PREFERRED_SIZE, 635, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblTurn, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblScore, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(303, 303, 303)
                        .addComponent(btnCheckMoves, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(71, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTurn)
                    .addComponent(lblScore))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlCards, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCheckMoves, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public JLabel getLblTurn() {
        return lblTurn;
    }

    public JPanel getPnlCards() {
        return pnlCards;
    }

    public MemoryCard[][] getMemoryCardMatrix() {
        return memoryCardMatrix;
    }

    public JButton getBtnCheckMoves() {
        return btnCheckMoves;
    }

    public JLabel getLblScore() {
        return lblScore;
    }
    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCheckMoves;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblScore;
    private javax.swing.JLabel lblTurn;
    private javax.swing.JPanel pnlCards;
    // End of variables declaration//GEN-END:variables

    @Override
    public void visibility(boolean setVisible) {
        this.setVisible(setVisible);
    }
}
