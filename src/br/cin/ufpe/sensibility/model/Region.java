package br.cin.ufpe.sensibility.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author avld
 */
public class Region
{
    private int id;
    private int x;
    private int y;
    private int width;
    private int height;
    private List<Integer> nodeList;
    
    public Region()
    {
        nodeList = new ArrayList<>();
    }
    
    public Region( int i , int x , int y , int w , int h , String nodes ) throws Exception
    {
        this.id = i;
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
        
        nodeList = new ArrayList<>();
        setNodeList( nodes );
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<Integer> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<Integer> nodeList)
    {
        if( nodeList == null )
        {
            this.nodeList.clear();
        }
        else
        {
            this.nodeList = nodeList;
        }
    }
    
    public void setNodeList( String nodeStr ) throws Exception
    {
        if( nodeStr.indexOf( "," ) >= 0 )
        {
            throw new Exception( "Replace ',' to ';'" );
        }
        
        nodeList.clear();
        String[] nodes = nodeStr.split( ";" );
        
        for( String n : nodes )
        {
            if( n == null 
                    ? true 
                    : n.trim().isEmpty() )
            {
                continue ;
            }
            
            if( n.indexOf( '-' ) != -1 )
            {
                String[] part = n.split( "-" );
                
                if( part.length < 2 )
                {
                    throw new Exception( "Invalid node, because someone putted '" + n
                                       + "'; when we expected '" + n + "NUMBER';" );
                }
                
                setNodeRange( part[0] , part[1] );
            }
            else
            {
                try
                {
                    nodeList.add( Integer.parseInt( n.trim() ) );
                }
                catch( Exception err )
                {
                    throw new Exception( "We expected a number when someone "
                                       + "putted this " + n + "." );
                }
            }
        }
    }
    
    private void setNodeRange( String startStr , String endStr ) throws Exception
    {
        if( startStr == null 
                    ? true 
                    : startStr.trim().isEmpty() )
        {
            throw new Exception( "First number is empty "
                               + "when someone putted "+ startStr +"-"+ endStr +"." );
        }
        else if( endStr == null 
                    ? true 
                    : endStr.trim().isEmpty() )
        {
            throw new Exception( "Second number is empty "
                               + "when someone putted "+ startStr +"-"+ endStr +"." );
        }
        
        int start = Integer.parseInt( startStr.trim() );
        int end   = Integer.parseInt( endStr.trim()   );
        
        for( int i = start ; i <= end ; i++ )
        {
            nodeList.add( i );
        }
    }
    
    public String getNodes()
    {
        StringBuilder builder = new StringBuilder();
        
        for( Integer node : nodeList )
        {
            builder.append( node ).append( "; " );
        }
        
        return builder.toString();
    }
    
}
