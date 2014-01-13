package br.cin.ufpe.sensibility.model.stopCriteria;

/**
 *
 * @author avld
 */
public class StopCriteria
{
    private String name;
    private String value;
    
    public StopCriteria()
    {
        // do nothing
    }

    public StopCriteria( String name , String value )
    {
        this.name = name;
        this.value = value;
    }
    
    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue( String value )
    {
        this.value = value;
    }
    
    @Override
    public String toString()
    {
        String text = name;
        
        if( value == null 
                ? false 
                : value.isEmpty() )
        {
            text += "_" + value;
        }
        
        return text;
    }
    
}
