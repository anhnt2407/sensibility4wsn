package br.cin.ufpe.sensibility.model;

/**
 *
 * @author avld
 */
public class Application
{
    private long id;
    private String path;
    private String function;
    
    public Application()
    {
        id = System.currentTimeMillis();
    }

    public long getId() {
        return id;
    }

    public void setId( long id )
    {
        this.id = id;
    }

    public String getPath()
    {
        return path;
    }

    public void setPath( String path )
    {
        this.path = path;
    }

    public String getFunction()
    {
        return function;
    }

    public void setFunction( String function )
    {
        this.function = function;
    }
    
}
