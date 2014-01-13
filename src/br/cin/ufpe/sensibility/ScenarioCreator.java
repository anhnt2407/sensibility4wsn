package br.cin.ufpe.sensibility;

import br.cin.ufpe.sensibility.model.ConfigurationLayer;
import br.cin.ufpe.sensibility.model.Scenario;
import br.cin.ufpe.sensibility.model.Sensibility;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Essa classe cria todos os cenarios possiveis de acordo com
 * as informações dentro do Sensibility.
 * 
 * @author avld
 */
public class ScenarioCreator
{
    private Sensibility sensibility;
    
    public ScenarioCreator( Sensibility sensibility )
    {
        this.sensibility = sensibility;
    }

    /**
     * Cria todos os cenarios possiveis
     * 
     * @return os cenarios criados
     */
    public List<Scenario> createAllScenarioPossible()
    {
        List<Scenario> list = new LinkedList<>();
        Date date = new Date();
        
        for( ConfigurationLayer deploy : sensibility.getDeploy() )            //seleciona como colocar os nos
        {
            for( ConfigurationLayer app : sensibility.getApplicationLayer() ) //seleciona uma aplicacao
            {
                for( ConfigurationLayer net : sensibility.getNetworkLayer() ) //seleciona um protocolo de roteamento
                {
                    for( ConfigurationLayer mac : sensibility.getMacLayer() ) //seleciona um protocolo de enlace
                    {
                        Scenario scenario = new Scenario( list.size() + 1 );  //cria um cenario
                        scenario.setApp( app );
                        scenario.setNet( net );
                        scenario.setMac( mac );
                        scenario.setDeploy( deploy );
                        scenario.setDate( date );

                        complete( scenario );                                //completa as informacoes

                        list.add( scenario );                                //salva na lista
                    }
                }
            }
        }
        
        return list;
    }
    
    /**
     * Essa funcao completa o cenario com as informações estaticas do
     * Sensibility (ex. o numero de nos sensores).
     * 
     * @param scenario cenario para ser completado
     */
    private void complete( Scenario scenario )
    {
        scenario.setStopCriteriaStorage( sensibility.getStopCriteriaStorage() );
        
        scenario.setNodeNumber( sensibility.getNodeNumber() );
        
        scenario.setWidth( sensibility.getArea() );
        scenario.setHeight( sensibility.getArea() );
        
        scenario.setRegionList( sensibility.getRegionList() );
        
        scenario.setBsX( sensibility.getBsX() );
        scenario.setBsY( sensibility.getBsY() );
        
        scenario.setDependability( sensibility.getDependabilityProperties() );
    }
    
}
