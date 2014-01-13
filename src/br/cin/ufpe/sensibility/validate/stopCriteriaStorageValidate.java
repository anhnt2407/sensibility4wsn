package br.cin.ufpe.sensibility.validate;

import br.cin.ufpe.sensibility.model.stopCriteria.MultiStopCriteriaStorage;
import br.cin.ufpe.sensibility.model.stopCriteria.StopCriteria;
import br.cin.ufpe.sensibility.model.stopCriteria.StopCriteriaStorage;
import br.cin.ufpe.sensibility.model.stopCriteria.TimeStopCriteriaStorage;

/**
 *
 * @author avld
 */
public class stopCriteriaStorageValidate
{
    private StopCriteriaStorage storage;
    
    public stopCriteriaStorageValidate( StopCriteriaStorage storage )
    {
        this.storage = storage;
    }
    
    public void validate() throws Exception
    {
        if( storage == null )
        {
            throw new Exception( "There is no Stop Criteria." );
        }
        
        if( storage instanceof TimeStopCriteriaStorage )
        {
            validateTime();
        }
        else
        {
            validateMulti();
        }
    }
    
    private void validateTime() throws Exception
    {
        TimeStopCriteriaStorage time = (TimeStopCriteriaStorage) storage;
        
        if( time.getTime() <= 0 )
        {
            throw new Exception( "The Stop Criteria has a inregular time (<= 0)." );
        }
    }
    
    private void validateMulti() throws Exception
    {
        MultiStopCriteriaStorage multi = (MultiStopCriteriaStorage) storage;
        
        if( multi.getSize() <= 0 )
        {
            throw new Exception( "There is no Stop Criteria." );
        }
        
        for( StopCriteria stop : multi.getList() )
        {
            if( stop == null )
            {
                throw new Exception( "There is an Stop Criteria that is null." );
            }
        }
    }
   
}
