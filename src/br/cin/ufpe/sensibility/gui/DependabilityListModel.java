package br.cin.ufpe.sensibility.gui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;

/**
 *
 * @author avld
 */
public class DependabilityListModel extends AbstractListModel<Double>
{
    private List<Double> list;
    
    public DependabilityListModel()
    {
        list = new ArrayList<>();
    }
    
    @Override
    public int getSize()
    {
        return list.size();
    }

    @Override
    public Double getElementAt( int index )
    {
        return list.get( index );
    }
    
    public void add( double value )
    {
        list.add( value );
        fireContentsChanged( value , 0 , getSize() );
    }
    
    public void clear()
    {
        list.clear();
        fireContentsChanged( list , 0 , 0 );
    }
    
}
