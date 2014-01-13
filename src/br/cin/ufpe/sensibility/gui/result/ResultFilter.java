package br.cin.ufpe.sensibility.gui.result;

import java.io.File;
import java.io.FilenameFilter;

/**
 *
 * @author avld
 */
public class ResultFilter implements FilenameFilter
{

    public ResultFilter()
    {
        // do nothing
    }
    
    @Override
    public boolean accept( File dir , String name )
    {
        if( name.toLowerCase().startsWith( "scenario" ) )
        {
            return false;
        }
        else
        {
            return name.toLowerCase().endsWith( ".wsn" );
        }
    }
    
}
