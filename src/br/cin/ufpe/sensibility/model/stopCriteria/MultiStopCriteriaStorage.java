package br.cin.ufpe.sensibility.model.stopCriteria;

import java.util.LinkedList;
import java.util.List;

/**
 * Precisa melhorar essa classe aqui!
 * 
 * @author avld
 */
@Deprecated
public class MultiStopCriteriaStorage implements StopCriteriaStorage
{
    private List<StopCriteria> list;
    
    public MultiStopCriteriaStorage()
    {
        list = new LinkedList<>();
    }

    public List<StopCriteria> getList()
    {
        return list;
    }

    @Override
    public StopCriteria getStopCriteria()
    {
        return list.get( 0 );
    }

    @Override
    public int getSize()
    {
        return list.size();
    }

    
    
}
