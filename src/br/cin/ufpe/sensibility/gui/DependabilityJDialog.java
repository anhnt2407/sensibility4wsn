package br.cin.ufpe.sensibility.gui;

import br.cin.ufpe.sensibility.gui.listener.DependabilityEvaluateActionListener;
import br.cin.ufpe.wsn2cpn.Node;
import br.cin.ufpe.wsn2cpn.Topology;
import br.cin.ufpe.wsn2cpn.WsnFile;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author avld
 */
public class DependabilityJDialog extends javax.swing.JDialog
{
    private Topology topology;
    private DependabilityListModel model;
    
    /**
     * Creates new form DependabilityJDialog
     */
    public DependabilityJDialog( java.awt.Window parent )
    {
        super( parent );
        setModal( true );
        
        initComponents();
        
        setLocationRelativeTo( null );
        
        // -------------------
        
        model = new DependabilityListModel();
        jList.setModel( model );
        
        evaluateJButton.addActionListener( new DependabilityEvaluateActionListener( this ) );
    }

    public void setTopology( Topology topology )
    {
        senderJComboBox.removeAllItems();
        receiverJComboBox.removeAllItems();
        
        for( Node node : topology.getNodeMap().values() )
        {
            String t1 = " Node " + node.getId() + " | Fixed: " + node.isFixed();
            senderJComboBox.addItem( t1 );
            
            String type = node.getProperties().get( "nodeType" );
            String t2 = " Node " + node.getId() + " | Type: " + type;
            receiverJComboBox.addItem( t2 );
        }
        
        this.topology = topology;
    }

    public Topology getTopology()
    {
        return topology;
    }
    
    public int getSenderId()
    {
        return senderJComboBox.getSelectedIndex() + 1;
    }
    
    public int getReceiverId()
    {
        return receiverJComboBox.getSelectedIndex() + 1;
    }
    
    public int getTime()
    {
        return Integer.parseInt( timeJTextField.getText() );
    }
    
    public DependabilityListModel getListModel()
    {
        return model;
    }
    
    public void calculateMean()
    {
        double max = 0.0;
        double min = 1.0;
        double mean = 0.0;
        
        for( int i = 0 ; i < jList.getModel().getSize() ; i++ )
        {
            double value = (Double) jList.getModel().getElementAt( i );
            
            if( value > max )
            {
                max = value;
            }
            
            if( value < min )
            {
                min = value;
            }
            
            mean += value;
        }
        
        mean /= jList.getModel().getSize();
        
        maxJTextField.setText ( max + ""  );
        minJTextField.setText ( min + ""  );
        meanJTextField.setText( mean + "" );
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        evaluateJButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList = new javax.swing.JList();
        jLabel2 = new javax.swing.JLabel();
        maxJTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        minJTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        meanJTextField = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        receiverJComboBox = new javax.swing.JComboBox();
        senderJComboBox = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        timeJTextField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        fileJTextField = new javax.swing.JTextField();
        fileJButton = new javax.swing.JButton();
        openJButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        evaluateJButton.setText("evaluate");

        jList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(jList);

        jLabel2.setText("max:");

        jLabel3.setText("min:");

        jLabel4.setText("mean:");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Choose a node", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel1.setText("sender:");

        jLabel5.setText("receiver:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 1, 1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(receiverJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(senderJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(senderJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(receiverJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jLabel6.setText("Times:");

        timeJTextField.setText("30");

        jLabel7.setText("Choose:");

        fileJButton.setText("File");
        fileJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileJButtonActionPerformed(evt);
            }
        });

        openJButton.setText("Open");
        openJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(timeJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(evaluateJButton))
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(minJTextField)
                            .addComponent(maxJTextField)
                            .addComponent(meanJTextField)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fileJTextField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fileJButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(openJButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(fileJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fileJButton)
                    .addComponent(openJButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(evaluateJButton)
                    .addComponent(jLabel6)
                    .addComponent(timeJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maxJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(minJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(meanJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fileJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileJButtonActionPerformed
        JFileChooser fc = new JFileChooser( new File( "./results/" ) );
        int returnVal = fc.showOpenDialog( this );

        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
            File file = fc.getSelectedFile();
            fileJTextField.setText( file.getAbsolutePath() );
        }
    }//GEN-LAST:event_fileJButtonActionPerformed

    private void openJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openJButtonActionPerformed
        try
        {
            String filename = fileJTextField.getText();
            Topology top = WsnFile.open( filename );
            setTopology( top );
        }
        catch( Exception err )
        {
            err.printStackTrace();
            JOptionPane.showMessageDialog( this , err );
        }
    }//GEN-LAST:event_openJButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton evaluateJButton;
    private javax.swing.JButton fileJButton;
    private javax.swing.JTextField fileJTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JList jList;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField maxJTextField;
    private javax.swing.JTextField meanJTextField;
    private javax.swing.JTextField minJTextField;
    private javax.swing.JButton openJButton;
    private javax.swing.JComboBox receiverJComboBox;
    private javax.swing.JComboBox senderJComboBox;
    private javax.swing.JTextField timeJTextField;
    // End of variables declaration//GEN-END:variables
}
