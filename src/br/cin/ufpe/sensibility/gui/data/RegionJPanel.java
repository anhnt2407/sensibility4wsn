package br.cin.ufpe.sensibility.gui.data;

import br.cin.ufpe.sensibility.model.Region;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author avld
 */
public class RegionJPanel extends DataJPanel<Region>
{
    /**
     * Creates new form RegionJPanel
     */
    public RegionJPanel( int id )
    {
        initComponents();
        setId( id );
    }
    
    public void setId( int id )
    {
        numberJLabel.setText( id + "" );
    }
    
    @Override
    public Region getData()
    {
        Region region = new Region();
        region.setId( Integer.parseInt( numberJLabel.getText() ) );
        
        region.setX( Integer.parseInt( xJTextField.getText() ) );
        region.setY( Integer.parseInt( yJTextField.getText() ) );
        
        region.setWidth( Integer.parseInt( widthJTextField.getText() ) );
        region.setHeight( Integer.parseInt( heightJTextField.getText() ) );
        
        try
        {
            region.setNodeList( sensorsJTextField.getText() );
        }
        catch( Exception err )
        {
            JOptionPane.showMessageDialog( this , err );
        }
        
        return region;
    }
    
    @Override
    public void addRemoveActionListener( ActionListener action )
    {
        removeJButton.addActionListener( action );
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        numberJLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        widthJTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        heightJTextField = new javax.swing.JTextField();
        yJTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        xJTextField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        sensorsJTextField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        removeJButton = new javax.swing.JButton();

        numberJLabel.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        numberJLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        numberJLabel.setText("00");
        numberJLabel.setToolTipText("");

        jLabel2.setText("Area:");

        widthJTextField.setText("0");

        jLabel3.setText("x");

        heightJTextField.setText("0");

        yJTextField.setText("0");

        jLabel5.setText("x");

        xJTextField.setText("0");

        jLabel6.setText("Point:");

        jLabel4.setText("Sensors:");

        jLabel7.setForeground(new java.awt.Color(153, 153, 153));
        jLabel7.setText("Ex.: 2; 3; 4; or 2-4;");

        removeJButton.setText("remove");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(numberJLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(xJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(yJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sensorsJTextField))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(widthJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(heightJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                        .addComponent(removeJButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(numberJLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(xJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(yJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(sensorsJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(widthJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(heightJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel7)
                            .addComponent(removeJButton))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField heightJTextField;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel numberJLabel;
    private javax.swing.JButton removeJButton;
    private javax.swing.JTextField sensorsJTextField;
    private javax.swing.JTextField widthJTextField;
    private javax.swing.JTextField xJTextField;
    private javax.swing.JTextField yJTextField;
    // End of variables declaration//GEN-END:variables
}
