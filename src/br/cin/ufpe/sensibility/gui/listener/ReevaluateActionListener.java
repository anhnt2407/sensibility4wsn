package br.cin.ufpe.sensibility.gui.listener;

import br.cin.ufpe.sensibility.SensibilityEvaluate;
import br.cin.ufpe.sensibility.file.SensibilityFile;
import br.cin.ufpe.sensibility.gui.result.ReevaluateJDialog;
import br.cin.ufpe.sensibility.gui.result.ResultJDialog;
import br.cin.ufpe.sensibility.model.Scenario;
import br.cin.ufpe.sensibility.model.Sensibility;
import br.cin.ufpe.wsn2cpn.Topology;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author avld
 */
public class ReevaluateActionListener implements ActionListener
{
    private ResultJDialog dialog;
    
    public ReevaluateActionListener( ResultJDialog dialog )
    {
        this.dialog = dialog;
    }
    
    @Override
    public void actionPerformed( ActionEvent e )
    {
        ReevaluateJDialog d = new ReevaluateJDialog( dialog );
        d.setVisible( true );
        
        if( !d.isExecute() )
        {
            return ;
        }
        
        try
        {
            double max = d.getMax();
            double min = d.getMin();

            List<Scenario> list = dialog.getScenarioSelected();
            SensibilityEvaluate evaluator = new SensibilityEvaluate( null , null );

            for( Scenario scenario : list )
            {
                for( Topology top : scenario.getResultMap().values() )
                {
                    top.getConfigurationMap().put( "dependability.batteryMax" , max + "" );
                    top.getConfigurationMap().put( "dependability.batteryMin" , min + "" );

                    evaluator.evaluateRBD( scenario , top );
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog( dialog , ex );
        }
        
        d.dispose();
        d = null;
    }
    
}
