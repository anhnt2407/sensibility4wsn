package br.cin.ufpe.sensibility.gui.listener;

import br.cin.ufpe.sensibility.gui.result.ResultJDialog;
import br.cin.ufpe.sensibility.model.Scenario;
import br.cin.ufpe.wsn2cpn.Topology;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author avld
 */
public class ResultActionListener implements ActionListener
{
    private ResultJDialog dialog;
    private List<String> regionList;
    
    public ResultActionListener( ResultJDialog dialog )
    {
        this.dialog = dialog;
    }
    
    @Override
    public void actionPerformed( ActionEvent e )
    {
        List<Scenario> list = dialog.getScenarioSelected();
        Component result = getResult( list );
        dialog.setResult( result );
    }
    
    private Component getResult( List<Scenario> list )
    {
        JFreeChart chart = ChartFactory.createXYLineChart(
                                "Log Axis Demo",             // chart title
                                "Category",                  // domain axis label
                                "Value",                     // range axis label
                                getSeriesCollection( list ), // data
                                PlotOrientation.VERTICAL,
                                true,                        // include legend
                                true,
                                false
                            );
        
        XYPlot plot = chart.getXYPlot();
        //plot.setRenderer( new XYSplineRenderer() );
        
        plot.setDomainAxis( new NumberAxis( "Time" ) );
        plot.setRangeAxis( new NumberAxis( "Dependability" ) );
        chart.setBackgroundPaint( Color.white );
        plot.setOutlinePaint( Color.black );
        return new ChartPanel( chart );
    }
    
    private XYSeriesCollection getSeriesCollection( List<Scenario> list )
    {
        XYSeriesCollection dataset = new XYSeriesCollection();
        
        if( list.size() >= 1 )
        {
            foundRegions( list.get( 0 ) );
        }
        
        for( Scenario scenario : list )
        {
            for( XYSeries s : getSeries( scenario ) )
            {
                dataset.addSeries( s );
            }
        }
        
        return dataset;
    }
    
    private List<XYSeries> getSeries( Scenario scenario )
    {
        List<XYSeries> list = new ArrayList<>();
        
        for( String name : regionList )
        {
            String seriesName = "Scenario " + scenario.getId() + " ("+ name +")";
            
            XYSeries series = new XYSeries( seriesName );
            for( int i = 0; i < scenario.getResultMap().size() ; i++ )
            {
                Topology top = scenario.getResultMap().get( i );
                double value = Double.parseDouble( top.getConfigurationMap().get( name ) );
                double time  = Double.parseDouble( top.getConfigurationMap().get( "time_executed" ) );
                
                System.out.println( "time: " + time + " | value: " + value );
                series.add( time , value );
            }
            
            list.add( series );
        }
        
        return list;
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
