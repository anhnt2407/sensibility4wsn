package br.cin.ufpe.sensibility.model;

import java.util.Map;

/**
 *
 * @author avld
 */
public class ConfigurationLayer
{
    private long id;
    private String name;
    private Map<String,String> confMap;
    private Map<String,String> varMap;
    
    public ConfigurationLayer()
    {
        this.id = System.currentTimeMillis();
    }

    public ConfigurationLayer( String name , Map<String, String> confMap )
    {
        this.id = System.currentTimeMillis();
        this.name = name;
        this.confMap = confMap;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Map<String, String> getConfMap()
    {
        return confMap;
    }

    public void setConfMap(Map<String, String> confMap)
    {
        this.confMap = confMap;
    }

    public Map<String, String> getVarMap()
    {
        return varMap;
    }

    public void setVarMap(Map<String, String> varMap)
    {
        this.varMap = varMap;
    }
    
}
