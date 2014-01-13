package br.cin.ufpe.sensibility.file;

import br.cin.ufpe.sensibility.model.Scenario;
import br.cin.ufpe.wsn2cpn.Topology;

/**
 *
 * @author avld
 */
public class StepFile
{

    public static void save( Scenario scenario , Topology topology , int step ) throws Exception
    {
        StepSave saver = new StepSave( scenario );
        saver.save( step , topology );
    }

    public static void save( Scenario scenario , Topology topology , String name ) throws Exception
    {
        StepSave saver = new StepSave( scenario );
        saver.save( name , topology );
    }
    
}
