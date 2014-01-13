package br.cin.ufpe.sensibility.file;

import br.cin.ufpe.sensibility.model.Scenario;
import br.cin.ufpe.sensibility.model.stopCriteria.StopCriteria;
import br.cin.ufpe.wsn2cpn.Topology;
import br.cin.ufpe.wsn2cpn.WsnFile;
import java.io.File;
import java.util.Date;

/**
 *
 * @author avld
 */
public class StepSave
{
    private Scenario scenario;
    
    public StepSave( Scenario scenario )
    {
        this.scenario = scenario;
    }
    
    private String getPath()
    {
        int id = scenario.getId();
        Date date = scenario.getDate();
        
        String directory = "./results/" + scenario.toGmtString() + "/Scenario_" + id + "/";
        
        File directoryFile = new File( directory );
        
        if( !directoryFile.exists() )
        {
            directoryFile.mkdirs();
        }
        
        return directory;
    }
    
    private String getName( int step )
    {
        StopCriteria stop = scenario.getStopCriteriaStorage().getStopCriteria();
        String stopName = stop.toString();
        
        return step + "_" + stopName;
    }
    
    public void save( int step , Topology topology ) throws Exception
    {
        save( getName( step ) , topology );
    }
    
    public void save( String name , Topology topology ) throws Exception
    {
        WsnFile.save( getPath() + name + ".wsn" , topology );
    }
    
}
