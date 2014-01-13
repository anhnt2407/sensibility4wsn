package br.cin.ufpe.sensibility.validate;

import br.cin.ufpe.sensibility.model.ConfigurationLayer;
import br.cin.ufpe.sensibility.model.Region;
import br.cin.ufpe.sensibility.model.Sensibility;
import br.cin.ufpe.sensibility.model.stopCriteria.StopCriteriaStorage;
import java.util.List;

/**
 *
 * @author avld
 */
public class SensibilityValidate
{
    private Sensibility sensibility;
    
    public SensibilityValidate( Sensibility sensibility )
    {
        this.sensibility = sensibility;
    }
    
    public void validate() throws Exception
    {
        if( sensibility == null )
        {
            throw new Exception( "Sensibility is null." );
        }
        else if( sensibility.getNodeNumber() <= 0 )
        {
            throw new Exception( "The node number is invalid (<= 0)." );
        }
        else if( sensibility.getArea() <= 0 )
        {
            throw new Exception( "The area is invalid (<= 0)." );
        }
        else if( sensibility.getBsX() <= 0 
                || sensibility.getBsX() <= 0 )
        {
            throw new Exception( "The base station position is invalid (X or Y is <= 0)." );
        }
        
        configurationLayer( "application" , sensibility.getApplicationLayer() );
        configurationLayer( "network"     , sensibility.getNetworkLayer() );
        configurationLayer( "link"        , sensibility.getMacLayer() );
        
        configurationLayer( "deploy"      , sensibility.getDeploy() );
        
        validateRegion();
        stopCriteriaStorage();
    }
    
    private void configurationLayer( String name , List<ConfigurationLayer> list ) throws Exception
    {
        if( list == null 
                ? true
                : list.isEmpty() )
        {
            throw new Exception( "There is no a "+ name +" registred." );
        }
        
        for( ConfigurationLayer layer : list )
        {
            ConfigurationLayerValidate validate = new ConfigurationLayerValidate( layer );
            validate.validate();
        }
    }
    
    private void stopCriteriaStorage() throws Exception
    {
        StopCriteriaStorage stop = sensibility.getStopCriteriaStorage();
        
        stopCriteriaStorageValidate validate = new stopCriteriaStorageValidate( stop );
        validate.validate();
    }
    
    private void validateRegion() throws Exception
    {
        if( sensibility.getRegionList() == null 
                ? true
                : sensibility.getRegionList().isEmpty() )
        {
            throw new Exception( "There is not a region registred." );
        }
        
        for( Region region : sensibility.getRegionList() )
        {
            RegionValidate validate = new RegionValidate( region );
            validate.validate();
        }
    }
}
