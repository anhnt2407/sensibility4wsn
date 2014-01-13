package br.cin.ufpe.sensibility.gui.listener;

import br.cin.ufpe.sensibility.file.ScenarioFile;
import br.cin.ufpe.sensibility.file.SensibilityFile;
import br.cin.ufpe.sensibility.gui.result.ResultFilter;
import br.cin.ufpe.sensibility.gui.result.ResultJDialog;
import br.cin.ufpe.sensibility.gui.result.ScenarioFilter;
import br.cin.ufpe.sensibility.model.Scenario;
import br.cin.ufpe.sensibility.model.Sensibility;
import br.cin.ufpe.wsn2cpn.Topology;
import br.cin.ufpe.wsn2cpn.WsnFile;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author avld
 */
public class LoadActionListener implements ActionListener
{
    protected ResultJDialog dialog;
    
    public LoadActionListener( ResultJDialog dialog )
    {
        this.dialog = dialog;
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
                String key = scenario.toGmtString() + " - Scenario " + scenario.getId();
                
                scenarioMap.put( key , scenario );
            }
            catch( Exception err )
            {
                err.printStackTrace();
            }
        }
        
        dialog.setMap( scenarioMap );
    }
    
    protected Scenario getScenario( File directory ) throws Exception
    {
        System.out.println( "name: " + directory.getName() );
        
        String filename = directory.getParentFile().getPath() + "/sensibility.xml";
        Sensibility sensibility = SensibilityFile.open( filename );
        
        Scenario scenario = ScenarioFile.open( directory.getPath() + "/scenario.xml" );
        scenario.setResultMap( new HashMap<Integer, Topology>() );
        scenario.setRegionList( sensibility.getRegionList() );
        
        for( File f : directory.listFiles( new ResultFilter() ) )
        {
            try
            {
                Topology result = WsnFile.open( f.getAbsolutePath() );
                scenario.getResultMap().put( getResultNumber( f ) , result );
            }
            catch( Exception err )
            {
                err.printStackTrace();
            }
        }
        
        return scenario;
    }
    
    protected int getResultNumber( File f )
    {
        String name = f.getName();
        
        if( name.equalsIgnoreCase( "init.wsn" ) )
        {
            return 0;
        }
        else
        {
            String idStr = name.split( "_" )[0];
            return Integer.parseInt( idStr ) + 1;
        }
    }
    
}
