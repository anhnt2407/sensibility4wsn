/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cin.ufpe.sensibility.file;

import br.cin.ufpe.sensibility.model.Scenario;

/**
 *
 * @author avld
 */
public class ScenarioFile
{
    
    public static void save( Scenario scenario ) throws Exception
    {
        ScenarioSave saver = new ScenarioSave( scenario );
        saver.save();
    }
    
    public static Scenario open( String filename ) throws Exception
    {
        ScenarioOpen opener = new ScenarioOpen( filename );
        return opener.open( filename );
    }
    
}
