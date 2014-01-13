package br.cin.ufpe.sensibility.validate;

import br.cin.ufpe.sensibility.model.Region;

/**
 *
 * @author avld
 */
public class RegionValidate
{
    private Region region;
    
    public RegionValidate( Region region )
    {
        this.region = region;
    }
    
    public void validate() throws Exception
    {
        if( region == null )
        {
            throw new Exception( "There is a region null." );
        }
        
        if( region.getNodeList().isEmpty() )
        {
            throw new Exception( "There is a region without nodes." );
        }
        
        if( region.getWidth() <= 0 ||
                region.getHeight() <= 0 )
        {
            throw new Exception( "There is a region with inregular area (width and height)." );
        }
        
        if( region.getId() <= 0 )
        {
            throw new Exception( "There is a region with inrelugar number." );
        }
    }
    
}
