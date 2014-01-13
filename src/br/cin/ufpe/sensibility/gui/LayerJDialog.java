package br.cin.ufpe.sensibility.gui;

import br.cin.ufpe.sensibility.gui.listener.LayerEditActionListener;
import br.cin.ufpe.wsn2cpn.layer.LayerConfiguration;
import java.awt.Dimension;
import java.util.Map;

/**
 *
 * @author avld
 */
public class LayerJDialog extends javax.swing.JDialog
{
    private PropertyTableModel nodeTableModel;
    private PropertyTableModel variableTableModel;
    
    public LayerJDialog( java.awt.Window parent )
    {
        super( parent );
        setModal( true );
        
        initComponents();
        init();
        
        setLocationRelativeTo( parent );
    }
    
    private void init()
    {
        nodeEditJButton.addActionListener( new LayerEditActionListener( this , true ) );
        nodeJTable.addMouseListener( new LayerEditActionListener( this , true ) );
        
        variableEditJButton.addActionListener( new LayerEditActionListener( this , false ) );
        variableJTable.addMouseListener( new LayerEditActionListener( this , false ) );
        
        // ---------------------------------- //
        
        nodeTableModel = new PropertyTableModel();
        nodeJTable.setModel( nodeTableModel );
        
        variableTableModel = new PropertyTableModel();
        variableJTable.setModel( variableTableModel );
    }
    
    public void setLayerConfiguration( LayerConfiguration layer  )
    {
        nodeTableModel.setPropertyList( layer.getNodeProperties() );
        variableTableModel.setPropertyList( layer.getVariableList() );
    }
    
    public void setNodeJPanelVisible( boolean visible )
    {
        nodeJPanel.setVisible( visible );
        
        Dimension dim = getPreferredSize();
        setSize( (int) dim.getWidth() / (int) 1.5 , (int) dim.getHeight() / (int) 1.5 );
        setLocationRelativeTo( null );
    }
    
    public void setNodeMap( Map<String,String> nodeMap )
    {
        nodeTableModel.setMap( nodeMap );
    }
    
    public void setVariableMap( Map<String,String> variableMap )
    {
        variableTableModel.setMap( variableMap );
    }
    
    public Map<String,String> getNodeMap()
    {
        return nodeTableModel.getMap();
    }
    
    public Map<String,String> getVariableMap()
    {
        return variableTableModel.getMap();
    }
    
    public void add( String key , String value )
    {
        nodeTableModel.add( key , value );
    }
    
    // -----------------------
    
    public Map.Entry<String, String> getEntrySelected( boolean isNodeProperty ) throws Exception
    {
        int row = isNodeProperty ? nodeJTable.getSelectedRow() 
                                 : variableJTable.getSelectedRow() ;
        
        if( row < 0 )
        {
            throw new Exception( "There is not a row selected." );
        }
        
        String key = "";
        String value = "";
        
        if( isNodeProperty )
        {
            key = (String) nodeTableModel.getValueAt( row , 0 );
            value = (String) nodeTableModel.getValueAt( row , 1 );
        }
        else
        {
            key = (String) variableTableModel.getValueAt( row , 0 );
            value = (String) variableTableModel.getValueAt( row , 1 );
        }
        
        return new EntryImpl( key , value );
    }

    public void setEntrySelected( boolean isNodeProperty , Map.Entry<String, String> entry ) throws Exception
    {
        int row = isNodeProperty ? nodeJTable.getSelectedRow() 
                                 : variableJTable.getSelectedRow() ;
        
        if( row < 0 )
        {
            throw new Exception( "There is not a row selected." );
        }
        
        if( isNodeProperty )
        {
            nodeTableModel.set( row ,  entry.getKey() , entry.getValue() );
        }
        else
        {
            variableTableModel.set( row ,  entry.getKey() , entry.getValue() );
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        variableJPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        variableJTable = new javax.swing.JTable();
        variableEditJButton = new javax.swing.JButton();
        nodeJPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        nodeJTable = new javax.swing.JTable();
        nodeEditJButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Node Properties");

        variableJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Variable", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        variableJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(variableJTable);

        variableEditJButton.setText("Edit");

        javax.swing.GroupLayout variableJPanelLayout = new javax.swing.GroupLayout(variableJPanel);
        variableJPanel.setLayout(variableJPanelLayout);
        variableJPanelLayout.setHorizontalGroup(
            variableJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(variableJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(variableJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, variableJPanelLayout.createSequentialGroup()
                        .addGap(0, 225, Short.MAX_VALUE)
                        .addComponent(variableEditJButton))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        variableJPanelLayout.setVerticalGroup(
            variableJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, variableJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(variableEditJButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                .addContainerGap())
        );

        nodeJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sensor Node Property", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        nodeJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(nodeJTable);

        nodeEditJButton.setText("Edit");

        javax.swing.GroupLayout nodeJPanelLayout = new javax.swing.GroupLayout(nodeJPanel);
        nodeJPanel.setLayout(nodeJPanelLayout);
        nodeJPanelLayout.setHorizontalGroup(
            nodeJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nodeJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(nodeJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, nodeJPanelLayout.createSequentialGroup()
                        .addGap(0, 230, Short.MAX_VALUE)
                        .addComponent(nodeEditJButton))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        nodeJPanelLayout.setVerticalGroup(
            nodeJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, nodeJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nodeEditJButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(variableJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nodeJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(variableJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(nodeJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton nodeEditJButton;
    private javax.swing.JPanel nodeJPanel;
    private javax.swing.JTable nodeJTable;
    private javax.swing.JButton variableEditJButton;
    private javax.swing.JPanel variableJPanel;
    private javax.swing.JTable variableJTable;
    // End of variables declaration//GEN-END:variables
}
