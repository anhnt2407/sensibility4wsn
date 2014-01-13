package br.cin.ufpe.sensibility.file;

import br.cin.ufpe.sensibility.model.ConfigurationLayer;
import br.cin.ufpe.sensibility.model.Scenario;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author avld
 */
public class ScenarioSave
{
    private Scenario scenario;
    private FileWriter writer;
    
    public ScenarioSave( Scenario scenario )
    {
        this.scenario = scenario;
    }
    
    public void save() throws IOException
    {
        writer = new FileWriter( getName() );
        writer.write( "<scenario>\n" );
        writeTag( "id" , scenario.getId() );
        writeTag( "nodeNumber" , scenario.getNodeNumber() );
        writeTag( "date" , scenario.getDate() );
        
        writeTag( "bsX" , scenario.getBsX() );
        writeTag( "bsY" , scenario.getBsY() );
        
        writeTag( "width"  , scenario.getWidth() );
        writeTag( "height" , scenario.getHeight() );
        
        writeLayer( "deploy" , scenario.getDeploy() );
        writeLayer( "app" , scenario.getApp() );
        writeLayer( "net" , scenario.getNet() );
        writeLayer( "mac" , scenario.getMac() );
        
        writer.write( "</scenario>" );
        writer.close();
    }
    
    private void writeTag( String tag , Object value ) throws IOException
    {
        writer.write( " <"+tag+">"+ value +"</"+tag+">\n" );
    }
    
    private void writeLayer( String tag , ConfigurationLayer layer ) throws IOException
    {
        writer.write( " <" + tag + ">\n" );
        writer.write( "   <id>" + layer.getId() + "</id>\n" );
        writer.write( "   <name>"+ layer.getName() +"</name>\n" );
        writer.write( "   <conf>"+ layer.getConfMap() +"</conf>\n" );
        writer.write( "   <var>"+ layer.getVarMap() +"</var>\n" );
        writer.write( " </" + tag + ">\n" );
    }
    
    // ------------------------
    // ------------------------
    // ------------------------
    
    private String getPath()
    {
        int id = scenario.getId();
        
        String directory = "./results/" + scenario.toGmtString() + "/Scenario_" + id + "/";
        
        File directoryFile = new File( directory );
        
        if( !directoryFile.exists() )
        {
            directoryFile.mkdirs();
        }
        
        return directory;
    }
    
    private String getName()
    {
        return getPath() + "scenario.xml";
    }
}
