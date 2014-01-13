package br.cin.ufpe.sensibility.gui.result;

import br.cin.ufpe.sensibility.model.Scenario;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.AbstractListModel;
import javax.swing.JCheckBox;

/**
 *
 * @author avld
 */
public class ScenarioListModel extends AbstractListModel<JCheckBox>
{
    private Map<String, Scenario> scenarioMap;
    private Map<String, JCheckBox> checkboxMap;
    private Map<Integer, String> keyMap;
    
    public ScenarioListModel()
    {
        scenarioMap = new HashMap<>();
        checkboxMap = new HashMap<>();
        keyMap      = new HashMap<>();
    }
    
    @Override
    public int getSize()
    {
        return scenarioMap.size();
    }

    @Override
    public JCheckBox getElementAt( int index )
    {
        String key = keyMap.get( index );
        return checkboxMap.get( key );
    }
    
    public void addMap( Map<String,Scenario> map )
    {
        if( map.isEmpty() )
        {
            return ;
        }
        
        int size = checkboxMap.size();
        scenarioMap.putAll( map );
            
        for( String key : map.keySet() )
        {
            JCheckBox checkbox = new JCheckBox( key );
            checkboxMap.put( key , checkbox );
            
            keyMap.put( keyMap.size() , key );
        }
        
        fireIntervalAdded( checkboxMap , size , size + map.size() );
    }
    
    public void setMap( Map<String,Scenario> map )
    {
        int size = scenarioMap.size();
        scenarioMap.clear();
        checkboxMap.clear();
        keyMap.clear();
        
        fireIntervalRemoved( checkboxMap , 0 , size );
        
        if( !map.isEmpty() )
        {
            scenarioMap = map;
            
            for( String key : map.keySet() )
            {
                JCheckBox checkbox = new JCheckBox( key ); //"Scenario " + key
                checkboxMap.put( key , checkbox );
                
                keyMap.put( keyMap.size() , key );
            }
            
            fireIntervalAdded( checkboxMap , 0 , map.size() );
        }
    }
    
    public List<Scenario> getScenarioSelected()
    {
        List<Scenario> list = new ArrayList<>();
        
        for( Entry<String,JCheckBox> entry : checkboxMap.entrySet() )
        {
            if( entry.getValue().isSelected() )
            {
                list.add( scenarioMap.get( entry.getKey() ) );
            }
        }
        
        return list;
    }
}
