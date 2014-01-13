package br.cin.ufpe.sensibility.gui;

import br.cin.ufpe.sensibility.gui.listener.DependabilityConfActionListener;
import br.cin.ufpe.sensibility.gui.listener.DeployAddActionListener;
import br.cin.ufpe.sensibility.gui.listener.EvaluateActionListener;
import br.cin.ufpe.sensibility.gui.listener.LayerAddActionListener;
import br.cin.ufpe.sensibility.gui.listener.RegionAddActionListener;
import br.cin.ufpe.sensibility.gui.listener.ResultGraphicActionListener;
import br.cin.ufpe.sensibility.gui.listener.ScenarioCalculateActionListener;
import br.cin.ufpe.sensibility.model.ConfigurationLayer;
import br.cin.ufpe.sensibility.model.Region;
import br.cin.ufpe.sensibility.model.Sensibility;
import br.cin.ufpe.sensibility.model.stopCriteria.StopCriteriaStorage;
import br.cin.ufpe.sensibility.model.stopCriteria.TimeStopCriteriaStorage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author avld
 */
public class SensibilityJFrame extends javax.swing.JFrame
{
    private ContainerJPanel<Region> regionJPanel;
    private ContainerJPanel<ConfigurationLayer> deployJPanel;
    private ContainerJPanel<ConfigurationLayer> appContainerJPanel;
    private ContainerJPanel<ConfigurationLayer> netContainerJPanel;
    private ContainerJPanel<ConfigurationLayer> macContainerJPanel;
    private DependabilityConfActionListener dependabilityAction;
    
    /**
     * Creates new form RbdJFrame
     */
    public SensibilityJFrame()
    {
        initComponents();
        init();
        
        setLocationRelativeTo( null );
    }

    private void init()
    {
        ScenarioCalculateActionListener calculateAction = new ScenarioCalculateActionListener( this );
        
        resultJButton.addActionListener( new ResultGraphicActionListener() );
        
        dependabilityAction = new DependabilityConfActionListener();
        dependabilityPropertiesJButton.addActionListener( dependabilityAction );
        
        deployJPanel = new ContainerJPanel<>();
        deployJPanel.addButtonActionListener( new DeployAddActionListener( deployJPanel ) );
        deployJPanel.addButtonActionListener( calculateAction );
        jTabbedPane.addTab( "Deploy" , deployJPanel );
        
        regionJPanel = new ContainerJPanel<>();
        regionJPanel.addButtonActionListener( new RegionAddActionListener( regionJPanel ) );
        regionJPanel.addButtonActionListener( calculateAction );
        jTabbedPane.addTab( "Region" , regionJPanel );
        
        appContainerJPanel = new ContainerJPanel<>();
        appContainerJPanel.addButtonActionListener( new LayerAddActionListener( appContainerJPanel , "application" ) );
        appContainerJPanel.addButtonActionListener( calculateAction );
        jTabbedPane.addTab( "Application Layer" , appContainerJPanel );
        
        netContainerJPanel = new ContainerJPanel<>();
        netContainerJPanel.addButtonActionListener( new LayerAddActionListener( netContainerJPanel , "network" ) );
        netContainerJPanel.addButtonActionListener( calculateAction );
        jTabbedPane.addTab( "Network Layer" , netContainerJPanel );
        
        macContainerJPanel = new ContainerJPanel<>();
        macContainerJPanel.addButtonActionListener( new LayerAddActionListener( macContainerJPanel , "mac" ) );
        macContainerJPanel.addButtonActionListener( calculateAction );
        jTabbedPane.addTab( "Link Layer" , macContainerJPanel );
        
        evaluateJButton.addActionListener( new EvaluateActionListener( this ) );
        cancelJButton.addActionListener( new ActionListener() {

                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                System.exit( 1 );
                                            }
                                        });
    }
    
    public void setScenarioSize( int size )
    {
        scenarioJLabel.setText( size + "" );
        stepJLabel.setText( getStopCriteriaStorage().getSize() + "" );
    }
    
    public Sensibility getSensibility()
    {
        Sensibility sens = new Sensibility();
        sens.setRegionList( regionJPanel.getList() );
        
        sens.setApplicationLayer( appContainerJPanel.getList() );
        sens.setNetworkLayer( netContainerJPanel.getList() );
        sens.setMacLayer( macContainerJPanel.getList() );
        
        sens.setDeploy( deployJPanel.getList() );
        
        //TODO: deixar que a pessoa escolha entre TIME e MULTI
        int time = Integer.parseInt( timeJTextField.getText() );
        int max  = Integer.parseInt( timeMaxJTextField.getText() );
        sens.setStopCriteriaStorage( new TimeStopCriteriaStorage( time , max ) );
        
        sens.setNodeNumber( Integer.parseInt( sensorJTextField.getText() ) );
        sens.setArea( Integer.parseInt( areaJTextField.getText() ) );
        sens.setBsX( Integer.parseInt( xJTextField.getText() ) );
        sens.setBsY( Integer.parseInt( yJTextField.getText() ) );
        
        sens.setDependabilityProperties( dependabilityAction.getMap() );
        
        return sens;
    }
    
    private StopCriteriaStorage getStopCriteriaStorage()
    {
        int time = Integer.parseInt( timeJTextField.getText() );
        int max  = Integer.parseInt( timeMaxJTextField.getText() );
        return new TimeStopCriteriaStorage( time , max );
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cancelJButton = new javax.swing.JButton();
        evaluateJButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        areaJTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        sensorJTextField = new javax.swing.JTextField();
        dependabilityPropertiesJButton = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        xJTextField = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        yJTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        timeJTextField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        timeMaxJTextField = new javax.swing.JTextField();
        jTabbedPane = new javax.swing.JTabbedPane();
        jLabel4 = new javax.swing.JLabel();
        scenarioJLabel = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        stepJLabel = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        resultJButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("WSN Analysis");

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Wireless Sensor Network Analysis");

        cancelJButton.setText("Cancel");

        evaluateJButton.setText("Evaluate");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("General"));

        jLabel2.setText("Area (m²):");

        areaJTextField.setText("200");

        jLabel3.setText("No. of Sensor:");

        sensorJTextField.setText("20");

        dependabilityPropertiesJButton.setText("Properties");

        jLabel10.setText("BS Pos. X:");

        xJTextField.setText("100");

        jLabel11.setText("BS Pos. Y:");

        yJTextField.setText("100");

        jLabel5.setText("Time:");

        timeJTextField.setText("10");

        jLabel7.setText("Time Max:");

        timeMaxJTextField.setText("100");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(yJTextField)
                    .addComponent(xJTextField)
                    .addComponent(areaJTextField)
                    .addComponent(dependabilityPropertiesJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sensorJTextField)
                    .addComponent(timeJTextField)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(timeMaxJTextField))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(areaJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sensorJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(xJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(yJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(timeJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(timeMaxJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(dependabilityPropertiesJButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel4.setText("This configuration will create");

        scenarioJLabel.setText("0");

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel6.setText("scenario(s) and");

        stepJLabel.setText("0");

        jLabel8.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel8.setText("step(s).");

        resultJButton.setText("Results");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scenarioJLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stepJLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 259, Short.MAX_VALUE)
                        .addComponent(evaluateJButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelJButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(resultJButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTabbedPane)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(resultJButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelJButton)
                    .addComponent(evaluateJButton)
                    .addComponent(jLabel4)
                    .addComponent(scenarioJLabel)
                    .addComponent(jLabel6)
                    .addComponent(stepJLabel)
                    .addComponent(jLabel8))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField areaJTextField;
    private javax.swing.JButton cancelJButton;
    private javax.swing.JButton dependabilityPropertiesJButton;
    private javax.swing.JButton evaluateJButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTabbedPane jTabbedPane;
    private javax.swing.JButton resultJButton;
    private javax.swing.JLabel scenarioJLabel;
    private javax.swing.JTextField sensorJTextField;
    private javax.swing.JLabel stepJLabel;
    private javax.swing.JTextField timeJTextField;
    private javax.swing.JTextField timeMaxJTextField;
    private javax.swing.JTextField xJTextField;
    private javax.swing.JTextField yJTextField;
    // End of variables declaration//GEN-END:variables
}
