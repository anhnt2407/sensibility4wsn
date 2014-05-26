/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cin.ufpe.sensibility.file;

import br.cin.ufpe.sensibility.model.Scenario;
import java.io.File;

/**
 *
 * @author avld
 */
public class ScenarioFile
{
    
    public static void save( String name , Scenario scenario ) throws Exception
    {
        ScenarioSave saver = new ScenarioSave( scenario );
        saver.save( name );
    }
    
    public static Scenario open( String filename ) throws Exception
    {
        ScenarioOpen opener = new ScenarioOpen( filename );
        return opener.open( filename );
    }
    
}
