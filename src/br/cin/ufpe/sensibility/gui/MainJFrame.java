package br.cin.ufpe.sensibility.gui;

import br.cin.ufpe.sensibility.gui.result.ResultJDialog;
import java.awt.Dialog;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author avld
 */
public class MainJFrame extends javax.swing.JFrame
{

    /**
     * Creates new form MainJFrame
     */
    public MainJFrame()
    {
        initComponents();
        setLocationRelativeTo( null );
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sensibilityJButton = new javax.swing.JButton();
        resultJButton = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        sensibilityJButton.setText("Sensibility");
        sensibilityJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sensibilityJButtonActionPerformed(evt);
            }
        });

        resultJButton.setText("Analisys Results");
        resultJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resultJButtonActionPerformed(evt);
            }
        });

        jButton3.setText("Reload Topology");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sensibilityJButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resultJButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap(132, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sensibilityJButton)
                    .addComponent(resultJButton)
                    .addComponent(jButton3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sensibilityJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sensibilityJButtonActionPerformed
        JFrame frame = new SensibilityJFrame();
        frame.setVisible( true );
    }//GEN-LAST:event_sensibilityJButtonActionPerformed

    private void resultJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resultJButtonActionPerformed
        Dialog dialog = new ResultJDialog( this );
        dialog.setVisible( true );
        dialog.dispose();
    }//GEN-LAST:event_resultJButtonActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        JDialog dialog = new DependabilityJDialog( this );
        dialog.setVisible( true );
    }//GEN-LAST:event_jButton3ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JButton resultJButton;
    private javax.swing.JButton sensibilityJButton;
    // End of variables declaration//GEN-END:variables
}
