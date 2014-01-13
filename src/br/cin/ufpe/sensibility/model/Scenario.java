package br.cin.ufpe.sensibility.model;

import br.cin.ufpe.sensibility.model.stopCriteria.StopCriteriaStorage;
import br.cin.ufpe.wsn2cpn.Topology;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author avld
 */
public class Scenario
{
    private int id;
    private ConfigurationLayer deploy;
    private ConfigurationLayer app;
    private ConfigurationLayer net;
    private ConfigurationLayer mac;
    private Map<String,String> dependability;
    
    private List<Region> regionList;
    private StopCriteriaStorage stopCriteriaStorage;
    private int width;
    private int height;
    private int nodeNumber;
    private int bsX;
    private int bsY;
    
    private Map<Integer,Topology> resultMap;
    private Date date;
    
    public Scenario()
    {
        regionList = new ArrayList<>();
    }
    
    public Scenario( int id )
    {
        this.id = id;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public ConfigurationLayer getDeploy()
    {
        return deploy;
    }

    public void setDeploy(ConfigurationLayer deploy)
    {
        this.deploy = deploy;
    }

    public ConfigurationLayer getApp()
    {
        return app;
    }

    public void setApp(ConfigurationLayer app)
    {
        this.app = app;
    }

    public ConfigurationLayer getNet()
    {
        return net;
    }

    public void setNet(ConfigurationLayer net)
    {
        this.net = net;
    }

    public ConfigurationLayer getMac()
    {
        return mac;
    }

    public void setMac( ConfigurationLayer mac )
    {
        this.mac = mac;
    }

    public StopCriteriaStorage getStopCriteriaStorage()
    {
        return stopCriteriaStorage;
    }

    public void setStopCriteriaStorage(StopCriteriaStorage stopCriteriaStorage)
    {
        this.stopCriteriaStorage = stopCriteriaStorage;
    }

    public List<Region> getRegionList()
    {
        return regionList;
    }

    public void setRegionList( List<Region> regionList )
    {
        if( regionList == null )
        {
            this.regionList.clear();
        }
        else
        {
            this.regionList = regionList;
        }
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public int getNodeNumber()
    {
        return nodeNumber;
    }

    public void setNodeNumber(int nodeNumber)
    {
        this.nodeNumber = nodeNumber;
    }

    public int getBsX()
    {
        return bsX;
    }

    public void setBsX(int bsX)
    {
        this.bsX = bsX;
    }

    public int getBsY()
    {
        return bsY;
    }

    public void setBsY(int bsY)
    {
        this.bsY = bsY;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public Map<Integer,Topology> getResultMap()
    {
        return resultMap;
    }

    public void setResultMap( Map<Integer,Topology> resultMap )
    {
        this.resultMap = resultMap;
    }

    public Map<String, String> getDependability()
    {
        return dependability;
    }

    public void setDependability(Map<String, String> dependability)
    {
        this.dependability = dependability;
    }
    
    public String toGmtString()
    {
        SimpleDateFormat sd = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
        //sd.setTimeZone( TimeZone.getTimeZone( "GMT" ) );
        return sd.format( date );
    }
    
}
