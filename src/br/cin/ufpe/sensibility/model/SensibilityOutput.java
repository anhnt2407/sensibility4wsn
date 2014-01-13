package br.cin.ufpe.sensibility.model;

import br.cin.ufpe.wsn2cpn.debug.Debug;

/**
 *
 * @author avld
 */
public interface SensibilityOutput extends Debug
{
    public void setScenarioTotal( int total );
    public void setScenario( int scenario );
    
    public void setStepTotal( int total );
    public void setStep( int step );
}
