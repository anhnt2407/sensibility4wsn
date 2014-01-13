/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cin.ufpe.sensibility.gui.result;

import br.cin.ufpe.sensibility.gui.listener.ResultActionListener;
import br.cin.ufpe.sensibility.gui.listener.LoadActionListener;
import br.cin.ufpe.sensibility.gui.listener.AddActionListener;
import br.cin.ufpe.sensibility.gui.listener.ExportActionListener;
import br.cin.ufpe.sensibility.gui.listener.ReevaluateActionListener;
import br.cin.ufpe.sensibility.model.Scenario;
import java.awt.Component;
import java.awt.Window;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 *
 * @author avld
 */
public class ResultJDialog extends javax.swing.JDialog
{
    private CheckBoxJList list;
    
    /**
     * Creates new form ResultJDialog
     * 
     * @param parent
     */
    public ResultJDialog( Window parent )
    {
        super( parent );
        
        initComponents();
        init();
        
        setModal( true );
        setLocationRelativeTo( parent );
    }

    private void init()
    {
        list = new CheckBoxJList();
        scenarioJScrollPane.setViewportView( list );
        
        openJButton.addActionListener  ( new LoadActionListener( this )   );
        addJButton.addActionListener   ( new AddActionListener( this ) );
        
        createJButton.addActionListener( new ResultActionListener( this ) );
        exportJButton.addActionListener( new ExportActionListener( this ) );
        reevaluateJButton.addActionListener( new ReevaluateActionListener( this ) );
        
        File file = new File( "./results/" );
        List<File> fielList = Arrays.asList( file.listFiles() );
        Collections.sort( fielList );
        
        for( File f : fielList )
        {
            if( f.isDirectory() )
            {
                resultJComboBox.addItem( f.getAbsolutePath() );
            }
        }
    }
    
    public File getDirectory()
    {
        String dir = (String) resultJComboBox.getSelectedItem();
        return new File( dir );
    }
    
    public void setResult( Component comp )
    {
        resultJScrollPane.setViewportView( comp );
    }
    
    public void setMap( Map<String, Scenario> map )
    {
        list.setMap( map );
    }
    
    public void addMap( Map<String, Scenario> map )
    {
        list.addMap( map );
    }
    
    public List<Scenario> getScenarioSelected()
    {
        return list.getScenarioSelected();
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scenarioJScrollPane = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();
        resultJScrollPane = new javax.swing.JScrollPane();
        createJButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        openJButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        resultJComboBox = new javax.swing.JComboBox();
        reevaluateJButton = new javax.swing.JButton();
        exportJButton = new javax.swing.JButton();
        addJButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Dependability Results");

        jLabel1.setText("Scenarios");

        createJButton.setText("create");

        jLabel2.setText("Dependability");

        jLabel3.setText("File:");

        openJButton.setText("open");

        reevaluateJButton.setText("reevaluate");

        exportJButton.setText("Export");

        addJButton.setText("add");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scenarioJScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 402, Short.MAX_VALUE)
                                .addComponent(exportJButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(reevaluateJButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(createJButton))
                            .addComponent(resultJScrollPane)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(resultJComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(openJButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addJButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(openJButton)
                    .addComponent(resultJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addJButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(createJButton)
                        .addComponent(reevaluateJButton)
                        .addComponent(exportJButton))
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scenarioJScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
                    .addComponent(resultJScrollPane))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addJButton;
    private javax.swing.JButton createJButton;
    private javax.swing.JButton exportJButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton openJButton;
    private javax.swing.JButton reevaluateJButton;
    private javax.swing.JComboBox resultJComboBox;
    private javax.swing.JScrollPane resultJScrollPane;
    private javax.swing.JScrollPane scenarioJScrollPane;
    // End of variables declaration//GEN-END:variables
}
