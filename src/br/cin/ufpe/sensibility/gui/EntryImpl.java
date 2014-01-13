package br.cin.ufpe.sensibility.gui;

import java.util.Map;

/**
 *
 * @author avld
 */
public class EntryImpl implements Map.Entry<String, String>
{
    private String key;
    private String value;
    
    public EntryImpl( String key , String value )
    {
        this.key = key;
        this.value = value;
    }
    
    @Override
    public String getKey()
    {
        return key;
    }

    @Override
    public String getValue()
    {
        return value;
    }

    @Override
    public String setValue(String value)
    {
        this.value = value;
        return this.value;
    }
    
}
