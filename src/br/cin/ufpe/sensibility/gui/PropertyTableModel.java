package br.cin.ufpe.sensibility.gui;

import br.cin.ufpe.wsn2cpn.layer.LayerProperty;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author avld
 */
public class PropertyTableModel extends AbstractTableModel
{
    private String[] columns;
    
    private List<LayerProperty> propertyList;
    private List<String> keyList;
    private List<String> valueList;
    
    public PropertyTableModel()
    {
        columns = new String[]{ "key" , "value" };
        
        propertyList = new LinkedList<>();
        valueList = new LinkedList<>();
        keyList = new LinkedList<>();
    }
    
    @Override
    public int getRowCount()
    {
        return valueList.size();
    }

    @Override
    public int getColumnCount()
    {
        return columns.length;
    }
    
    @Override
    public String getColumnName( int col )
    {
        return columns[ col ];
    }

    @Override
    public Object getValueAt( int row , int col )
    {
        switch( col )
        {
            case 0: return keyList.get( row );
            case 1: return valueList.get( row );
        }
        
        return null;
    }
    
    @Override
    public void setValueAt( Object value , int row , int col )
    {
        switch( col )
        {
            case 0: keyList.set( row , (String) value ); break ;
            case 1: valueList.set( row , (String) value ); break ;
        }
    }
    
    // ---------------------- //
    // ---------------------- //
    // ---------------------- //
    
    public void add( String key , String value )
    {
        keyList.add( key );
        valueList.add( value );
        
        fireTableDataChanged();
    }
    
    public void set( int row , String key , String value )
    {
        keyList.set( row , key );
        valueList.set( row , value );
        
        fireTableRowsUpdated( row , row );
    }
    
    public void delete( int row )
    {
        keyList.remove( row );
        valueList.remove( row );
        
        fireTableRowsDeleted(row , row );
    }
    
    // ---------------------- //
    // ---------------------- //
    // ---------------------- //
    
    public Map<String,String> getMap()
    {
        Map<String,String> map = new HashMap<>();
        
        for( int i = 0 ; i < keyList.size() ; i++ )
        {
            map.put( keyList.get( i ) , valueList.get( i ) );
        }
        
        return map;
    }
    
    public void setMap( Map<String,String> map )
    {
        keyList.clear();
        valueList.clear();
        
        if( map != null )
        {
            for( Entry<String,String> entry : map.entrySet() )
            {
                keyList.add( entry.getKey() );
                valueList.add( entry.getValue() );
            }
        }
        
        fireTableDataChanged();
    }
    
    // ---------------------- //
    // ---------------------- //
    // ---------------------- //
    
    public void setPropertyList( List<LayerProperty> propList )
    {
        propertyList.clear();
        
        if( propList != null )
        {
            propertyList = propList;
        }
        
        Map<String,String> map = new HashMap<>();
        for( LayerProperty prop : propList )
        {
            if( !prop.isHidden() )
            {
                map.put( prop.getName() , prop.getDefaultValue() );
            }
        }
        
        setMap( map );
    }
    
}