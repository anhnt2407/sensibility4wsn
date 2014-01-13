package br.cin.ufpe.sensibility.gui.result;

import java.io.File;
import java.io.FilenameFilter;

/**
 *
 * @author avld
 */
public class ScenarioFilter implements FilenameFilter
{

    public ScenarioFilter()
    {
        // do nothing
    }
    
    @Override
    public boolean accept( File dir , String name )
    {
        if( dir.isDirectory() )
        {
            return name.toLowerCase().startsWith( "scenario_" );
        }
        else
        {
            return false;
        }
    }
    
}
