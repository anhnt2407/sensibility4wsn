package br.cin.ufpe.sensibility.gui.data;

import br.cin.ufpe.sensibility.gui.LayerJDialog;
import br.cin.ufpe.sensibility.model.ConfigurationLayer;
import br.cin.ufpe.wsn2cpn.Node;
import br.cin.ufpe.wsn2cpn.layer.LayerConfiguration;
import br.cin.ufpe.wsn2cpn.layer.LayerConfigurationFile;
import br.cin.ufpe.wsn2cpn.layer.LayerContainer;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author avld
 */
public class LayerJPanel extends DataJPanel<ConfigurationLayer>
{
    private List<LayerConfiguration> list;
    private LayerJDialog dialog;
    private String layerName;
    
    public LayerJPanel( String layerName )
    {
        initComponents();
        init( layerName );
    }

    private void init( String layerName )
    {
        try
        {
            createList( layerName );
            
            for( LayerConfiguration conf : list )
            {
                jComboBox.addItem( conf );
            }
        }
        catch( Exception err )
        {
            JOptionPane.showMessageDialog( null , err );
        }
    }
    
    private void createList( String layerName ) throws Exception
    {
        list = new ArrayList<>();
        List<String> nameList = LayerContainer.getInstance().getLayerList( layerName );
        
        for( String modelName : nameList )
        {
            list.add( getConfiguration( layerName , modelName ) );
        }
        
        this.layerName = layerName;
    }
    
    private LayerConfiguration getConfiguration( String layerName , String modelName ) throws Exception
    {
        String filename = "./layers/" + layerName + "/" + modelName+ ".xml";
        return LayerConfigurationFile.open( filename );
    }
    
    // --------------
    
    
    @Override
    public ConfigurationLayer getData()
    {
        LayerConfiguration conf = (LayerConfiguration) jComboBox.getSelectedItem();
        
        if( dialog == null )
        {
            dialog = new LayerJDialog( null );
            dialog.setLayerConfiguration( conf );
        }
        
        // ----------
        
        ConfigurationLayer layer = new ConfigurationLayer();
        layer.setName( conf.getName() );
        layer.setConfMap( dialog.getNodeMap() );
        layer.setVarMap( dialog.getVariableMap() );
        
        return layer;
    }
    
    @Override
    public void addRemoveActionListener( ActionListener action )
    {
        removeJButton.addActionListener( action );
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox = new javax.swing.JComboBox();
        propertiesJButton = new javax.swing.JButton();
        removeJButton = new javax.swing.JButton();

        jComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxItemStateChanged(evt);
            }
        });

        propertiesJButton.setText("Properties");
        propertiesJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                propertiesJButtonActionPerformed(evt);
            }
        });

        removeJButton.setText("remove");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox, 0, 239, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(propertiesJButton)
                .addGap(18, 18, 18)
                .addComponent(removeJButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(propertiesJButton)
                        .addComponent(removeJButton)))
                .addContainerGap())
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
            
            LayerConfiguration conf = (LayerConfiguration) jComboBox.getSelectedItem();
            dialog.setLayerConfiguration( conf );
            
            if( "mac".equalsIgnoreCase( layerName ) )
            {
                Node node = new Node();
                dialog.setNodeMap( node.getProperties() );
            }
        }
        
        dialog.setVisible( true );
        dialog.dispose();
    }//GEN-LAST:event_propertiesJButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jComboBox;
    private javax.swing.JButton propertiesJButton;
    private javax.swing.JButton removeJButton;
    // End of variables declaration//GEN-END:variables
}
