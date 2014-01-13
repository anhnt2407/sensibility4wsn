package br.cin.ufpe.sensibility.model.stopCriteria;

/**
 *
 * @author avld
 */
public class TimeStopCriteriaStorage implements StopCriteriaStorage
{
    private int time;
    private int max;
    
    public TimeStopCriteriaStorage( int time , int max )
    {
        this.time = time;
        this.max = max;
    }

    public int getTime()
    {
        return time;
    }

    public void setTime( int time )
    {
        this.time = time;
    }

    public int getMax()
    {
        return max;
    }

    public void setMax( int max )
    {
        this.max = max;
    }

    @Override
    public StopCriteria getStopCriteria()
    {
        return new StopCriteria( "time" , max + "" );
    }

    @Override
    public int getSize()
    {
        return 1;
    }
}
