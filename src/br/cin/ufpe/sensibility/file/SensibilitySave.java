package br.cin.ufpe.sensibility.file;

import br.cin.ufpe.sensibility.model.ConfigurationLayer;
import br.cin.ufpe.sensibility.model.Region;
import br.cin.ufpe.sensibility.model.Sensibility;
import br.cin.ufpe.sensibility.model.stopCriteria.StopCriteria;
import br.cin.ufpe.sensibility.model.stopCriteria.StopCriteriaStorage;
import br.cin.ufpe.sensibility.model.stopCriteria.TimeStopCriteriaStorage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author avld
 */
public class SensibilitySave
{
    private Sensibility sensibility;
    private FileWriter writer;
    
    public SensibilitySave( Sensibility sensibility )
    {
        this.sensibility = sensibility;
    }
    
    public void save( String filename ) throws IOException
    {
        File file = new File( filename );
        file.getParentFile().mkdirs();
        
        // --------------------
        // --------------------
        // --------------------
        
        writer = new FileWriter( filename );
        writer.write( "<sensibility>\n" );
        
        writeTag( "nodeNumber" , sensibility.getNodeNumber() );
        writeTag( "area" , sensibility.getArea() );
        
        writeTag( "bsX" , sensibility.getBsX() );
        writeTag( "bsy" , sensibility.getBsY() );
        
        writeLayer( "application_layer" , sensibility.getApplicationLayer() );
        writeLayer( "network_layer" , sensibility.getNetworkLayer() );
        writeLayer( "mac_layer" , sensibility.getMacLayer() );
        writeLayer( "deploy" , sensibility.getDeploy() );
        writeRegions();
        writeStopCriteriaStorage();
        
        writer.write( "</sensibility>" );
        writer.close();
    }
    
    private void writeTag( String tag , Object value ) throws IOException
    {
        writer.write( " <"+tag+">"+ value +"</"+tag+">\n" );
    }
    
    private void writeLayer( String tag , List<ConfigurationLayer> list ) throws IOException
    {
        writer.write( " <"+ tag +">\n\n" );
        
        for( ConfigurationLayer layer : list )
        {
            writer.write( "  <configuration>\n" );
            writer.write("     <id>"   + layer.getId()      + "</id>\n");
            writer.write("     <name>" + layer.getName()    + "</name>\n");
            writer.write("     <conf>" + layer.getConfMap() + "</conf>\n");
            writer.write("     <var>"  + layer.getVarMap()  + "</var>\n");
            writer.write( "  </configuration>\n\n" );
        }
        
        writer.write( " </"+ tag +">\n" );
    }
    
    private void writeRegions() throws IOException
    {
        writer.write( " <regions>\n\n" );
        
        for( Region region : sensibility.getRegionList() )
        {
            writer.write( "  <region>\n" );
            
            writer.write( "    <id>"+ region.getId() +"</id>\n" );
            writer.write( "    <x>"+ region.getX() +"</x>\n" );
            writer.write( "    <y>"+ region.getY() +"</y>\n" );
            writer.write( "    <width>"+ region.getWidth() +"</width>\n" );
            writer.write( "    <height>"+ region.getHeight() +"</height>\n" );
            writer.write( "    <nodes>"+ region.getNodes() +"</nodes>\n" );
            
            writer.write( "  </region>\n\n" );
        }
        
        writer.write( " </regions>\n" );
    }
    
    private void writeStopCriteriaStorage() throws IOException
    {
        boolean isTime = sensibility.getStopCriteriaStorage() instanceof TimeStopCriteriaStorage;
        
        if( isTime )
        {
            TimeStopCriteriaStorage tscs = (TimeStopCriteriaStorage) sensibility.getStopCriteriaStorage();
            
            writer.write( " <stopCriteriaStorage type=\"time\">\n" );
            writer.write( "   <time>" + tscs.getTime() + "</time>\n" );
            writer.write( "   <max>"  + tscs.getMax()  + "</max>\n" );
            writer.write( " </stopCriteriaStorage>\n" );
        }
        else
        {
            StopCriteriaStorage storage = sensibility.getStopCriteriaStorage();
            
            writer.write( " <stopCriteriaStorage type=\"time\">\n" );
            
            StopCriteria sc = storage.getStopCriteria();
            writer.write( "  <stopCriteria name=\""+ sc.getName() +"\" "
                                        + "value =\""+ sc.getValue()+"\">\n" );
            
            writer.write( " </stopCriteriaStorage>\n" );
        }
    }
}
