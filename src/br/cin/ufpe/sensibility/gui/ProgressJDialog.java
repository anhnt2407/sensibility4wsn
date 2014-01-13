package br.cin.ufpe.sensibility.gui;

import br.cin.ufpe.sensibility.model.SensibilityOutput;
import java.awt.Window;

/**
 *
 * @author avld
 */
public class ProgressJDialog extends javax.swing.JDialog implements SensibilityOutput
{
    private int scenarioTotal;
    private int stepTotal;
    
    /**
     * Creates new form ProgressJDialog
     * 
     * @param parent
     */
    public ProgressJDialog( Window parent )
    {
        super( parent );
        initComponents();
        
        setLocationRelativeTo( parent );
    }

    @Override
    public void setScenarioTotal( int scenario )
    {
        this.scenarioTotal = scenario;
        scenarioJProgressBar.setValue( 0 );
    }

    @Override
    public void setScenario( int scenario )
    {
        scenarioJLabel.setText( "Scenario ( "+ scenario +" / "+ scenarioTotal +" )" );
        
        int value = (scenario * 100) / scenarioTotal;
        scenarioJProgressBar.setValue( value );
    }

    @Override
    public void setStepTotal( int total )
    {
        this.stepTotal = total;
        stepJProgressBar.setValue( 0 );
    }

    @Override
    public void setStep( int step )
    {
        stepJLabel.setText( "Step ( "+ step +" / "+ stepTotal +" )" );
        
        int value = ( step * 100 ) / stepTotal;
        stepJProgressBar.setValue( value );
    }

    @Override
    public void print( String msg )
    {
        jTextArea.setText( jTextArea.getText() + msg );
    }

    @Override
    public void println( String msg )
    {
        print( msg + "\n" );
    }

    @Override
    public void clear()
    {
        jTextArea.setText( "" );
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scenarioJLabel = new javax.swing.JLabel();
        scenarioJProgressBar = new javax.swing.JProgressBar();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        stepJLabel = new javax.swing.JLabel();
        stepJProgressBar = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Evaluator Progress");

        scenarioJLabel.setText("Scenario ( 000 / 000 )");

        jTextArea.setColumns(20);
        jTextArea.setRows(5);
        jScrollPane1.setViewportView(jTextArea);

        jLabel3.setText("Output");

        stepJLabel.setText("Step ( 000 / 000 )");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scenarioJProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 643, Short.MAX_VALUE)
                    .addComponent(stepJProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scenarioJLabel)
                            .addComponent(jLabel3)
                            .addComponent(stepJLabel))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scenarioJLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scenarioJProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(stepJLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(stepJProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea;
    private javax.swing.JLabel scenarioJLabel;
    private javax.swing.JProgressBar scenarioJProgressBar;
    private javax.swing.JLabel stepJLabel;
    private javax.swing.JProgressBar stepJProgressBar;
    // End of variables declaration//GEN-END:variables
}
