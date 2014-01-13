package br.cin.ufpe.sensibility.validate;

import br.cin.ufpe.sensibility.model.ConfigurationLayer;

/**
 *
 * @author avld
 */
public class ConfigurationLayerValidate
{
    private ConfigurationLayer conf;
    
    public ConfigurationLayerValidate( ConfigurationLayer conf )
    {
        this.conf = conf;
    }
    
    public void validate() throws Exception
    {
        if( conf == null )
        {
            throw new Exception( "There is a configuration layer that is null." );
        }
        else if( conf.getName() == null ? true : conf.getName().isEmpty() )
        {
            throw new Exception( "There is a configuration layer that its name is null." );
        }
    }
    
}
