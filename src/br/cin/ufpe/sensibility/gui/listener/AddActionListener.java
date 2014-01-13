package br.cin.ufpe.sensibility.gui.listener;

import br.cin.ufpe.sensibility.gui.result.ResultJDialog;
import br.cin.ufpe.sensibility.gui.result.ScenarioFilter;
import br.cin.ufpe.sensibility.model.Scenario;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author avld
 */
public class AddActionListener extends LoadActionListener
{
    
    public AddActionListener( ResultJDialog dialog )
    {
        super( dialog );
    }
    
    @Override
    public void actionPerformed( ActionEvent e )
    {
        File dir = dialog.getDirectory();
        
        Map<String, Scenario> scenarioMap = new HashMap<>();
        for( File f : dir.listFiles( new ScenarioFilter() ) )
        {
            try
            {
                Scenario scenario = getScenario( f );
                scenarioMap.put( scenario.toGmtString() + " - Scenario " + scenario.getId() , scenario );
            }
            catch( Exception err )
            {
                err.printStackTrace();
            }
        }
        
        dialog.addMap( scenarioMap );
    }
    
}
