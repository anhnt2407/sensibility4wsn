package br.cin.ufpe.sensibility.file;

import br.cin.ufpe.sensibility.model.Sensibility;
import java.io.IOException;

/**
 *
 * @author avld
 */
public class SensibilityFile
{
    
    public static void save( String filename , Sensibility sensibility ) throws IOException
    {
        SensibilitySave saver = new SensibilitySave( sensibility );
        saver.save( filename );
    }
    
    public static Sensibility open( String filename ) throws Exception
    {
        SensibilityOpen opener = new SensibilityOpen( filename );
        return opener.open( filename );
    }
    
}
