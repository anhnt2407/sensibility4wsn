package br.cin.ufpe.sensibility.file;

import br.cin.ufpe.sensibility.model.ConfigurationLayer;
import br.cin.ufpe.sensibility.model.Scenario;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
public class ScenarioOpen
{
    private String filename;
    private Scenario scenario;
    
    public ScenarioOpen( String filename )
    {
        this.filename = filename;
    }
    
    public Scenario open( String filename ) throws Exception
    {
        File fXmlFile = new File( filename );
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse( fXmlFile );
        doc.getDocumentElement().normalize();

        NodeList rootXml = doc.getDocumentElement().getChildNodes();

        scenario = new Scenario();
        
        for( int i = 0; i < rootXml.getLength(); i++ )
        {
            Node nodeXml = rootXml.item( i );

            if( "id".equalsIgnoreCase( nodeXml.getNodeName() ) )
            {
                int id = Integer.parseInt( getTag( nodeXml ) );
                scenario.setId( id );
            }
            else if( "nodeNumber".equalsIgnoreCase( nodeXml.getNodeName() ) )
            {
                int number = Integer.parseInt( getTag( nodeXml ) );
                scenario.setNodeNumber( number );
            }
            else if( "date".equalsIgnoreCase( nodeXml.getNodeName() ) )
            {
                scenario.setDate( getDate( nodeXml ) );
            }
            else if( "bsX".equalsIgnoreCase( nodeXml.getNodeName() ) )
            {
                int bsX = Integer.parseInt( getTag( nodeXml ) );
                scenario.setBsX( bsX );
            }
            else if( "bsY".equalsIgnoreCase( nodeXml.getNodeName() ) )
            {
                int bsY = Integer.parseInt( getTag( nodeXml ) );
                scenario.setBsY( bsY );
            }
            else if( "width".equalsIgnoreCase( nodeXml.getNodeName() ) )
            {
                int width = Integer.parseInt( getTag( nodeXml ) );
                scenario.setWidth( width );
            }
            else if( "height".equalsIgnoreCase( nodeXml.getNodeName() ) )
            {
                int height = Integer.parseInt( getTag( nodeXml ) );
                scenario.setHeight( height );
            }
            else if( "deploy".equalsIgnoreCase( nodeXml.getNodeName() ) )
            {
                scenario.setDeploy( getLayer( nodeXml ) );
            }
            else if( "app".equalsIgnoreCase( nodeXml.getNodeName() ) )
            {
                scenario.setApp( getLayer( nodeXml ) );
            }
            else if( "net".equalsIgnoreCase( nodeXml.getNodeName() ) )
            {
                scenario.setNet( getLayer( nodeXml ) );
            }
            else if( "mac".equalsIgnoreCase( nodeXml.getNodeName() ) )
            {
                scenario.setMac( getLayer( nodeXml ) );
            }
        }
        
        return scenario;
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
                long id = Long.parseLong( getTag( parameterNodeXml ) );
                layer.setId( id );
            }
            else if( "name".equalsIgnoreCase( parameterNodeXml.getNodeName() ) )
            {
                layer.setName( getTag( parameterNodeXml ) );
            }
            else if( "conf".equalsIgnoreCase( parameterNodeXml.getNodeName() ) )
            {
                layer.setConfMap( getMap( parameterNodeXml ) );
            }
            else if( "var".equalsIgnoreCase( parameterNodeXml.getNodeName() ) )
            {
                layer.setConfMap( getMap( parameterNodeXml ) );
            }
        }
        
        return layer;
    }
    
    private String getTag( Node nodeXml )
    {
        return nodeXml.getChildNodes().item( 0 ).getNodeValue();
    }
    
    private Date getDate( Node nodeXml )
    {
        String data  = getTag( nodeXml );
        String valeu = data.substring( data.indexOf( ' ' ) + 1 );
        
        SimpleDateFormat formatador = new SimpleDateFormat( "MMM dd kk:hh:ss 'BRT' yyyy" );
        
        try  
        {
            return formatador.parse( valeu );
        }  
        catch( ParseException ex )
        {  
            ex.printStackTrace();
            return null;
        }
    }
    
    private Map<String,String> getMap( Node nodeXml )
    {
        String valuesCru = getTag( nodeXml );
        
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
        else if( "{}".equalsIgnoreCase( valuesCru ) )
        {
            return new HashMap<>();
        }
        
        String values = valuesCru.substring( 1 , valuesCru.length() - 1 );
        String[] entries = values.split( "," );
        
        Map<String,String> map = new HashMap<>();
        for( String entry : entries )
        {
            String[] part = entry.split( "=" );
            map.put( part[0].trim() , part[1] );
        }
        
        return map;
    }
}
