package br.cin.ufpe.sensibility;

import br.cin.ufpe.sensibility.file.ScenarioFile;
import br.cin.ufpe.sensibility.file.SensibilityFile;
import br.cin.ufpe.sensibility.validate.SensibilityValidate;
import br.cin.ufpe.sensibility.file.StepFile;
import br.cin.ufpe.sensibility.model.Region;
import br.cin.ufpe.sensibility.model.Scenario;
import br.cin.ufpe.sensibility.model.Sensibility;
import br.cin.ufpe.sensibility.model.SensibilityOutput;
import br.cin.ufpe.wsn2cpn.Topology;
import br.cin.ufpe.wsn2cpn.execute.AccessCpnMultiExecute;
import br.cin.ufpe.wsn2rbd.Wsn2Rbd;
import java.util.List;
import javax.swing.JOptionPane;
import org.modcs.tools.rbd.blocks.RBDModel;

/**
 *
 * @author avld
 */
public class SensibilityEvaluate implements Runnable
{
    private AccessCpnMultiExecute executor;
    private Sensibility sensibility;
    private SensibilityOutput output;
    
    public SensibilityEvaluate( Sensibility sensibility , SensibilityOutput output )
    {
        this.sensibility = sensibility;
        this.output = output;
    }
    
    @Override
    public void run()
    {
        try
        {
            // ---------------- validar a sensibilidade antes mesmo de avaliar
            SensibilityValidate validate = new SensibilityValidate( sensibility );
            validate.validate();

            // ---------------- configurar o acesso ao CPN Tools
            executor = new AccessCpnMultiExecute( "./sensibility.cpn" , output );
            executor.setSimulationManyTimes( false );
            executor.setTime( 30 );

            // ---------------- criar e avaliar todos os cenarios possiveis
            ScenarioCreator creator = new ScenarioCreator( sensibility );
            List<Scenario> list = creator.createAllScenarioPossible();
            
            output.setScenarioTotal( list.size() );
            
            String xml =  "./results/" + list.get( 0 ).toGmtString() + "/sensibility.xml";
            SensibilityFile.save( xml , sensibility );
            
            for( Scenario scenario : list )
            {
                ScenarioFile.save( scenario );
                evaluateScenario( scenario );
            }
        }
        catch( Exception err )
        {
            err.printStackTrace();
            JOptionPane.showMessageDialog( null , err );
        }
    }
    
    private void evaluateScenario( Scenario scenario ) throws Exception
    {
        ScenarioTopology topologyCreator = new ScenarioTopology( scenario );
        output.setScenario( scenario.getId() );
        
        // ---------------- avalia a dependabilidade quando os nos ainda não foram avaliados
        Topology result = topologyCreator.createManyTopologies();
        evaluateRBD( topologyCreator.getScenario() , result );
        StepFile.save( topologyCreator.getScenario() , result , "init" );
        
        // ---------------- avaliar o consumo de energia e a dependabilidade
        evaluate( topologyCreator );
    }
    
    private List<Topology> evaluate( ScenarioTopology topologyCreator ) throws Exception
    {
        //--------------- cria uma topologia
        Topology topologyCreated = topologyCreator.createManyTopologies();

        //--------------- avaliar o consumo de energia da rede
        executor.analyseSequencialNodes( topologyCreated );
        executor.traduzir( topologyCreated );

        List<Topology> list = executor.executar();
        
        for( int i = 0 ; i < list.size() ; i++ )
        {
            Topology topResult = list.get( i );
            
            topResult.getConfigurationMap().putAll( topologyCreator.getScenario().getDependability() );
            topResult.getConfigurationMap().put( "application_layer" , topologyCreated.getConfigurationMap().get("application_layer") );  //ex. periodic
            topResult.getConfigurationMap().put( "network_layer"     , topologyCreated.getConfigurationMap().get("network_layer")     );  //ex. direct
            topResult.getConfigurationMap().put( "mac_layer"         , topologyCreated.getConfigurationMap().get("mac_layer")         );  //ex. b-mac

            //--------------- avaliar a dependabilidade das regioes
            evaluateRBD( topologyCreator.getScenario() , topResult );

            //--------------- salvar o resultado desse passo
            StepFile.save( topologyCreator.getScenario() , topResult , i );
        }
        
        return list;
    }
    
    /**
     * Avalia a dependabilidade de todas as regioes
     * 
     * @param scenario  cenario com as regioes
     * @param topology  topologia com o nivel de energia dos nos sensores
     */
    public void evaluateRBD( Scenario scenario , Topology topology )
    {
        for( Region region : scenario.getRegionList() )
        {
            String name = "region_" + region.getId();
            double value = evaluateRBD( topology , region.getNodeList() , 1 );
            
            System.out.println( "name: " + name + " | value: " + value );
            
            //salva a dependabilidade da regiao na topologia
            topology.getConfigurationMap().put( name , value + "" );
        }
    }
    
    /**
     * Avalia a dependabilidade de uma regiao
     * 
     * @param top       topologia com o nivel de energia dos nos sensores
     * @param senders   os nos participantes de uma regiao
     * @param receiver  quem deve receber os pacotes (BS)
     * @return          dependabilidade
     */
    public double evaluateRBD( Topology top , List<Integer> senders , int receiver )
    {
        Wsn2Rbd rbd = new Wsn2Rbd( top );
        RBDModel model = null;
        
        int counter = 0;
        do
        {
            model = rbd.convert( senders , receiver );
            counter++;
        }
        while( model.getModel() == null && counter < 10 );
        
        if( model.getModel() == null )
        {
            return 0.0;
        }
        else
        {
            return rbd.evaluate( model );
        }
    }
    
}
