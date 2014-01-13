package br.cin.ufpe.sensibility.gui.listener;

import br.cin.ufpe.sensibility.gui.result.ResultJDialog;
import br.cin.ufpe.sensibility.model.Scenario;
import br.cin.ufpe.wsn2cpn.Topology;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author avld
 */
public class ExportActionListener implements ActionListener
{
    private ResultJDialog dialog;
    private List<String> regionList;
    
    public ExportActionListener( ResultJDialog dialog )
    {
        this.dialog = dialog;
    }
    
    @Override
    public void actionPerformed( ActionEvent e )
    {
        List<Scenario> list = dialog.getScenarioSelected();
        
        if( list.size() >= 1 )
        {
            foundRegions( list.get( 0 ) );
        }
        
        try
        {
            FileWriter writer = new FileWriter( "./confiability.csv" );
            
            for( Scenario scenario : list )
            {
                saveData( writer , scenario );
            }
            
            writer.close();
            
            JOptionPane.showMessageDialog( dialog , "File created!" );
        }
        catch( Exception err )
        {
            JOptionPane.showMessageDialog( dialog , err );
        }
    }
    
    private void saveData( FileWriter writer , Scenario scenario ) throws IOException
    {
        for( String name : regionList )
        {
            String seriesName = "Scenario " + scenario.getId() + " ("+ name +")";
            
            writer.write( seriesName + ", \n" );
            for( int i = 0; i < scenario.getResultMap().size() ; i++ )
            {
                Topology top = scenario.getResultMap().get( i );
                double value = Double.parseDouble( top.getConfigurationMap().get( name ) );
                double time  = Double.parseDouble( top.getConfigurationMap().get( "time_executed" ) );
                
                System.out.println( "time: " + time + " | value: " + value );
                writer.write( time + ", " + value + "\n" );
            }
            
            writer.write( ", \n" );
        }
    }
    
    private void foundRegions( Scenario scenario )
    {
        regionList = new ArrayList<>();
        
        Topology topology = scenario.getResultMap().get( 0 );
        for( String name : topology.getConfigurationMap().keySet() )
        {
            if( name == null ? true : name.trim().isEmpty() )
            {
                continue ;
            }
            else if( name.toLowerCase().startsWith( "region_" ) )
            {
                regionList.add( name );
            }
        }
    }
}
