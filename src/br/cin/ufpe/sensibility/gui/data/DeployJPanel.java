package br.cin.ufpe.sensibility.gui.data;

import br.cin.ufpe.sensibility.gui.LayerJDialog;
import br.cin.ufpe.sensibility.model.ConfigurationLayer;
import br.cin.ufpe.wsn2cpn.deploy.NodeDeploy;
import br.cin.ufpe.wsn2cpn.deploy.NodeDeployFactory;
import br.cin.ufpe.wsn2cpn.layer.LayerConfiguration;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author avld
 */
public class DeployJPanel extends DataJPanel<ConfigurationLayer>
{
    private LayerJDialog dialog;
    private Map<String, NodeDeploy> map;
    
    /**
     * Creates new form DeployJPanel
     */
    public DeployJPanel() throws Exception
    {
        initComponents();
        init();
    }

    private void init() throws Exception
    {
        map = new HashMap<>();
        jComboBox.removeAllItems();
        
        for( String name : NodeDeployFactory.getDeployAvaliable() )
        {
            map.put( name , NodeDeployFactory.getNodeDeploy( name ) );
            jComboBox.addItem( name );
        }
    }
    
    @Override
    public ConfigurationLayer getData()
    {
        String name = (String) jComboBox.getSelectedItem();
        
        if( dialog == null )
        {
            dialog = new LayerJDialog( null );
            dialog.setVariableMap( map.get( name ).getParameterMap() );
        }
        
        // ------------
        
        ConfigurationLayer layer = new ConfigurationLayer();
        layer.setName( name );
        layer.setConfMap( dialog.getNodeMap() );
        
        return layer;
    }

    @Override
    public void addRemoveActionListener( ActionListener action )
    {
        removeJButton.addActionListener( action );
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jComboBox = new javax.swing.JComboBox();
        removeJButton = new javax.swing.JButton();
        propertiesJButton = new javax.swing.JButton();

        jLabel1.setText("Deploy:");

        jComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxItemStateChanged(evt);
            }
        });

        removeJButton.setText("remove");

        propertiesJButton.setText("Properties");
        propertiesJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                propertiesJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox, 0, 220, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(propertiesJButton)
                .addGap(18, 18, 18)
                .addComponent(removeJButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removeJButton)
                    .addComponent(propertiesJButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxItemStateChanged
        if( dialog != null )
        {
            dialog = null;
        }
    }//GEN-LAST:event_jComboBoxItemStateChanged

    private void propertiesJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_propertiesJButtonActionPerformed
        if( dialog == null )
        {
            dialog = new LayerJDialog( null );
            
            String name = (String) jComboBox.getSelectedItem();
            dialog.setVariableMap( map.get( name ).getParameterMap() );
            dialog.setNodeJPanelVisible( false );
        }
        
        dialog.setVisible( true );
        dialog.dispose();
    }//GEN-LAST:event_propertiesJButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton propertiesJButton;
    private javax.swing.JButton removeJButton;
    // End of variables declaration//GEN-END:variables
}
