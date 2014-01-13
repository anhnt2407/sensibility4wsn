package br.cin.ufpe.sensibility.file;

import br.cin.ufpe.sensibility.model.ConfigurationLayer;
import br.cin.ufpe.sensibility.model.Region;
import br.cin.ufpe.sensibility.model.Sensibility;
import br.cin.ufpe.sensibility.model.stopCriteria.StopCriteriaStorage;
import br.cin.ufpe.sensibility.model.stopCriteria.TimeStopCriteriaStorage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author avld
 */
public class SensibilityOpen
{
    private String filename;
    
    public SensibilityOpen( String filename )
    {
        this.filename = filename;
    }
    
    public Sensibility open( String filename ) throws Exception
    {
        File fXmlFile = new File( filename );
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse( fXmlFile );
        doc.getDocumentElement().normalize();

        NodeList rootXml = doc.getDocumentElement().getChildNodes();

        Sensibility sensibility = new Sensibility();
        
        for( int i = 0; i < rootXml.getLength(); i++ )
        {
            Node nodeXml = rootXml.item( i );

            if( "nodeNumber".equalsIgnoreCase( nodeXml.getNodeName() ) )
            {
                int number = Integer.parseInt( getString( nodeXml ) );
                sensibility.setNodeNumber( number );
            }
            else if( "bsX".equalsIgnoreCase( nodeXml.getNodeName() ) )
            {
                int bsX = Integer.parseInt( getString( nodeXml ) );
                sensibility.setBsX( bsX );
            }
            else if( "bsY".equalsIgnoreCase( nodeXml.getNodeName() ) )
            {
                int bsY = Integer.parseInt( getString( nodeXml ) );
                sensibility.setBsY( bsY );
            }
            else if( "area".equalsIgnoreCase( nodeXml.getNodeName() ) )
            {
                int area = Integer.parseInt( getString( nodeXml ) );
                sensibility.setArea( area );
            }
            else if( "regions".equalsIgnoreCase( nodeXml.getNodeName() ) )
            {
                sensibility.setRegionList( getRegionList( nodeXml.getChildNodes() ) );
            }
            else if( "deploy".equalsIgnoreCase( nodeXml.getNodeName() ) )
            {
                sensibility.setDeploy( getLayerList( nodeXml.getChildNodes() ) );
            }
            else if( "application_layer".equalsIgnoreCase( nodeXml.getNodeName() ) )
            {
                sensibility.setApplicationLayer( getLayerList( nodeXml.getChildNodes() ) );
            }
            else if( "network_layer".equalsIgnoreCase( nodeXml.getNodeName() ) )
            {
                sensibility.setNetworkLayer( getLayerList( nodeXml.getChildNodes() ) );
            }
            else if( "mac_layer".equalsIgnoreCase( nodeXml.getNodeName() ) )
            {
                sensibility.setMacLayer( getLayerList( nodeXml.getChildNodes() ) );
            }
            else if( "stopCriteriaStorage".equalsIgnoreCase( nodeXml.getNodeName() ) )
            {
                sensibility.setStopCriteriaStorage( getStopCriteriaStorage( nodeXml ) );
            }
        }
        
        return sensibility;
    }
    
    private StopCriteriaStorage getStopCriteriaStorage( Node nodeXml )
    {
        TimeStopCriteriaStorage timeSCS = new TimeStopCriteriaStorage( 0 , 0 );
        
        NodeList parameterXml = nodeXml.getChildNodes();
        for( int i = 0; i < parameterXml.getLength(); i++ )
        {
            Node parameterNodeXml = parameterXml.item( i );

            if( "time".equalsIgnoreCase( parameterNodeXml.getNodeName() ) )
            {
                int time = Integer.parseInt( getString( parameterNodeXml ) );
                timeSCS.setTime( time );
            }
            else if( "max".equalsIgnoreCase( parameterNodeXml.getNodeName() ) )
            {
                int max = Integer.parseInt( getString( parameterNodeXml ) );
                timeSCS.setMax( max );
            }
        }
        
        return timeSCS;
    }
    
    // -----------------------------------------
    // -----------------------------------------
    // -----------------------------------------
    
    private List<ConfigurationLayer> getLayerList( NodeList nodeXml ) throws IOException
    {
        List<ConfigurationLayer> list = new ArrayList<>();
        
        for( int i = 0; i < nodeXml.getLength(); i++ )
        {
            Node parameterNodeXml = nodeXml.item( i );
            
            if( "configuration".equalsIgnoreCase( parameterNodeXml.getNodeName() ) )
            {
                ConfigurationLayer layer = getLayer( parameterNodeXml );
                list.add( layer );
            }
        }
        
        return list;
    }
    
    private ConfigurationLayer getLayer( Node nodeXml ) throws IOException
    {
        ConfigurationLayer layer = new ConfigurationLayer();
        
        NodeList parameterXml = nodeXml.getChildNodes();
        for( int i = 0; i < parameterXml.getLength(); i++ )
        {
            Node parameterNodeXml = parameterXml.item( i );

            if( "id".equalsIgnoreCase( parameterNodeXml.getNodeName() ) )
            {
                long id = Long.parseLong( getString( parameterNodeXml ) );
                layer.setId( id );
            }
            else if( "name".equalsIgnoreCase( parameterNodeXml.getNodeName() ) )
            {
                layer.setName( getString( parameterNodeXml ) );
            }
            else if( "conf".equalsIgnoreCase( parameterNodeXml.getNodeName() ) )
            {
                layer.setConfMap( getMap( parameterNodeXml ) );
            }
            else if( "var".equalsIgnoreCase( parameterNodeXml.getNodeName() ) )
            {
                layer.setVarMap( getMap( parameterNodeXml ) );
            }
        }
        
        return layer;
    }
    
    // -----------------------------------------
    // -----------------------------------------
    // -----------------------------------------
    
    private List<Region> getRegionList( NodeList nodeXml ) throws Exception
    {
        List<Region> list = new ArrayList<>();
        
        for( int i = 0; i < nodeXml.getLength(); i++ )
        {
            Node parameterNodeXml = nodeXml.item( i );
            
            if( "region".equalsIgnoreCase( parameterNodeXml.getNodeName() ) )
            {
                Region region = getRegion( parameterNodeXml );
                list.add( region );
            }
        }
        
        return list;
    }
    
    private Region getRegion( Node nodeXml ) throws Exception
    {
        Region region = new Region();
        
        NodeList parameterXml = nodeXml.getChildNodes();
        for( int i = 0; i < parameterXml.getLength(); i++ )
        {
            Node parameterNodeXml = parameterXml.item( i );

            if( "id".equalsIgnoreCase( parameterNodeXml.getNodeName() ) )
            {
                int id = Integer.parseInt( getString( parameterNodeXml ) );
                region.setId( id );
            }
            else if( "x".equalsIgnoreCase( parameterNodeXml.getNodeName() ) )
            {
                int id = Integer.parseInt( getString( parameterNodeXml ) );
                region.setX( id );
            }
            else if( "y".equalsIgnoreCase( parameterNodeXml.getNodeName() ) )
            {
                int id = Integer.parseInt( getString( parameterNodeXml ) );
                region.setY( id );
            }
            else if( "width".equalsIgnoreCase( parameterNodeXml.getNodeName() ) )
            {
                int id = Integer.parseInt( getString( parameterNodeXml ) );
                region.setWidth( id );
            }
            else if( "height".equalsIgnoreCase( parameterNodeXml.getNodeName() ) )
            {
                int id = Integer.parseInt( getString( parameterNodeXml ) );
                region.setHeight( id );
            }
            else if( "nodes".equalsIgnoreCase( parameterNodeXml.getNodeName() ) )
            {
                region.setNodeList( getString( parameterNodeXml ) );
            }
        }
        
        return region;
    }
    
    private String getString( Node nodeXml )
    {
        return nodeXml.getChildNodes().item( 0 ).getNodeValue();
    }
    
    private Map<String,String> getMap( Node nodeXml )
    {
        String valuesCru = getString( nodeXml );
        
        if( valuesCru == null 
                ? true 
                : valuesCru.trim().isEmpty() )
        {
            return null;
        }
        else if( !valuesCru.startsWith( "{" ) )
        {
            return null;
        }
        
        String values = valuesCru.substring( 1 , valuesCru.length() - 1 );
        String[] entries = values.split( "," );
        
        Map<String,String> map = new HashMap<>();
        for( String entry : entries )
        {
            if( entry == null 
                    ? true 
                    : entry.trim().isEmpty() )
            {
                continue ;
            }
            
            String[] part = entry.split( "=" );
            map.put( part[0].trim() , part[1] );
        }
        
        return map;
    }
    
}
