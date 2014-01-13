package br.cin.ufpe.sensibility.gui.listener;

import br.cin.ufpe.sensibility.ScenarioCreator;
import br.cin.ufpe.sensibility.gui.SensibilityJFrame;
import br.cin.ufpe.sensibility.model.Sensibility;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author avld
 */
public class ScenarioCalculateActionListener implements ActionListener
{
    private SensibilityJFrame frame;
    
    public ScenarioCalculateActionListener( SensibilityJFrame frame )
    {
        this.frame = frame;
    }
    
    @Override
    public void actionPerformed( ActionEvent e )
    {
        Sensibility sensibility = frame.getSensibility();
        ScenarioCreator creator = new ScenarioCreator( sensibility );
        
        frame.setScenarioSize( creator.createAllScenarioPossible().size() );
    }
    
}
