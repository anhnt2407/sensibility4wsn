package br.cin.ufpe.sensibility;

import br.cin.ufpe.sensibility.model.Region;
import br.cin.ufpe.sensibility.model.Scenario;
import br.cin.ufpe.sensibility.model.stopCriteria.StopCriteria;
import br.cin.ufpe.sensibility.model.stopCriteria.TimeStopCriteriaStorage;
import br.cin.ufpe.wsn2cpn.Node;
import br.cin.ufpe.wsn2cpn.Topology;
import br.cin.ufpe.wsn2cpn.deploy.NodeDeploy;
import br.cin.ufpe.wsn2cpn.deploy.NodeDeployFactory;
import br.cin.ufpe.wsn2cpn.execute.SimulationControl;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author avld
 */
public class ScenarioTopology
{
    private Scenario scenario;
    private int topologyQuant;
    
    public ScenarioTopology( Scenario scenario )
    {
        this.scenario = scenario;
        this.topologyQuant = 30;
    }

    public Scenario getScenario()
    {
        return scenario;
    }
    
    public void setTopologyQuant( int topologyQuant )
    {
        this.topologyQuant = topologyQuant;
    }
    
    // ------------------------------------
    // ------------------------------------
    // ------------------------------------
    
    public Topology createManyTopologies() throws Exception
    {
        SimulationControl control = new SimulationControl();
        control.add( 0.0 , createTopology() );
        
        for( int i = 1; i < topologyQuant ; i++ )
        {
            Topology top = createTopology();
            control.add( 0.0 , top );
        }
        
        for( Topology top : control.getTopologyList() )
        {
            for( Node node : top.getNodeMap().values() )
            {
                node.getProperties().put( "energy"  , "0.0" );
                node.getProperties().put( "battery" , "1.0" );
                node.getProperties().put( "battery_level" , "100.0" );
            }
        }
        
        return control.process();
    }
    
    public Topology createTopology() throws Exception
    {
        Topology top = new Topology();
        
        top.setVariableMap( getVariableMap() );
        top.getConfigurationMap().putAll( scenario.getDependability() );                     //configura a dependabilidade
        top.getConfigurationMap().put( "application_layer" , scenario.getApp().getName() );  //ex. periodic
        top.getConfigurationMap().put( "network_layer"     , scenario.getNet().getName() );  //ex. direct
        top.getConfigurationMap().put( "mac_layer"         , scenario.getMac().getName() );  //ex. b-mac
        
        StopCriteria stop = scenario.getStopCriteriaStorage().getStopCriteria();
        top.getConfigurationMap().put( "criteria_stop"       , stop.getName()  );            //ex. time
        top.getConfigurationMap().put( "criteria_stop_value" , stop.getValue() );            //ex. 10
        
        // --------------------------------
        
        boolean isTimeStorage = scenario.getStopCriteriaStorage() instanceof TimeStopCriteriaStorage;
        
        if( isTimeStorage )
        {
            TimeStopCriteriaStorage tscs = (TimeStopCriteriaStorage) scenario.getStopCriteriaStorage();
            
            top.getVariableMap().put( "timeNext"     , tscs.getTime() + "" );
            top.getVariableMap().put( "timeInterval" , tscs.getTime() + "" );
        }
        
        // --------------------------------
        
        NodeDeploy nodeDeploy = NodeDeployFactory.getNodeDeploy( scenario.getDeploy().getName() );
        nodeDeploy.setLocation( 0 , 0 );
        nodeDeploy.setSize( scenario.getWidth() , scenario.getHeight() );
        nodeDeploy.setNodeSize( scenario.getNodeNumber() );
        nodeDeploy.setNodeDefault( getNodeDefault() );
        top = nodeDeploy.create( top , scenario.getDeploy().getConfMap() );
        
        for( Region region : scenario.getRegionList() )
        {
            Map<Integer,Node> map = getNodesFromRegion( region );
            top.getNodeMap().putAll( map );
        }
        
        Node bs = getBaseStation();
        top.getNodeMap().put( bs.getId() , bs );
        
        return top;
    }
    
    private Map<String,String> getVariableMap()
    {
        Map<String,String> map = new HashMap<>();
        map.putAll( scenario.getApp().getVarMap() );
        map.putAll( scenario.getNet().getVarMap() );
        map.putAll( scenario.getMac().getVarMap() );
        
        return map;
    }
    
    private Node getNodeDefault()
    {
        Node node = new Node( 0 );
        node.setFixed( false );
        node.getProperties().putAll( scenario.getApp().getConfMap() );
        node.getProperties().putAll( scenario.getNet().getConfMap() );
        node.getProperties().putAll( scenario.getMac().getConfMap() );
        
        return node;
    }
    
    private Map<Integer,Node> getNodesFromRegion( Region region ) throws Exception
    {
        NodeDeploy nodeDeploy = NodeDeployFactory.getNodeDeploy( "random" );
        nodeDeploy.setLocation( region.getX() ,  region.getY() );
        nodeDeploy.setSize( region.getWidth() , region.getHeight() );
        nodeDeploy.setNodeSize( region.getNodeList().size() );
        nodeDeploy.setNodeDefault( getNodeDefault() );
        
        Map<Integer,Node> nodeMap = new HashMap<>();
        for( Node node : nodeDeploy.create( new Topology() ).getNodeMap().values() )
        {
            int id = region.getNodeList().get( node.getId() - 1 );
            node.setId( id );
            
            nodeMap.put( node.getId() , node );
        }
        
        return nodeMap;
    }
    
    private Node getBaseStation()
    {
        Node bs = getNodeDefault();
        bs.setId( 1 );
        bs.setFixed( true );
        bs.getProperties().put( "nodeType" , "BS" );
        bs.getProperties().put( "X" , scenario.getBsX() + "" );  // pos.x
        bs.getProperties().put( "Y" , scenario.getBsY() + "" );  // pos.y
        
        return bs;
    }
    
}
