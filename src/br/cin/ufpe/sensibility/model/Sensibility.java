package br.cin.ufpe.sensibility.model;

import br.cin.ufpe.sensibility.model.stopCriteria.StopCriteriaStorage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author avld
 */
public class Sensibility
{
    private int area;
    private int nodeNumber;
    private int bsX;
    private int bsY;
    private List<Region> regionList;
    private List<ConfigurationLayer> applicationLayer;
    private List<ConfigurationLayer> networkLayer;
    private List<ConfigurationLayer> macLayer;
    private List<ConfigurationLayer> deploy;
    private StopCriteriaStorage stopCriteriaStorage;
    private Map<String,String> dependabilityProperties;
    
    public Sensibility()
    {
        regionList = new ArrayList<>();
        
        applicationLayer = new ArrayList<>();
        networkLayer = new ArrayList<>();
        macLayer = new ArrayList<>();
        dependabilityProperties = new HashMap<>();
    }

    public int getArea()
    {
        return area;
    }

    public void setArea(int area)
    {
        this.area = area;
    }

    public int getNodeNumber()
    {
        return nodeNumber;
    }

    public void setNodeNumber(int nodeNumber)
    {
        this.nodeNumber = nodeNumber;
    }

    public List<Region> getRegionList()
    {
        return regionList;
    }

    public void setRegionList(List<Region> regionList)
    {
        this.regionList = regionList;
    }

    public List<ConfigurationLayer> getApplicationLayer()
    {
        return applicationLayer;
    }

    public void setApplicationLayer(List<ConfigurationLayer> applicationLayer)
    {
        this.applicationLayer = applicationLayer;
    }

    public List<ConfigurationLayer> getNetworkLayer()
    {
        return networkLayer;
    }

    public void setNetworkLayer(List<ConfigurationLayer> networkLayer)
    {
        this.networkLayer = networkLayer;
    }

    public List<ConfigurationLayer> getMacLayer()
    {
        return macLayer;
    }

    public void setMacLayer(List<ConfigurationLayer> macLayer)
    {
        this.macLayer = macLayer;
    }

    public List<ConfigurationLayer> getDeploy()
    {
        return deploy;
    }

    public void setDeploy(List<ConfigurationLayer> deploy)
    {
        this.deploy = deploy;
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

    public StopCriteriaStorage getStopCriteriaStorage()
    {
        return stopCriteriaStorage;
    }

    public void setStopCriteriaStorage(StopCriteriaStorage stopCriteriaStorage)
    {
        this.stopCriteriaStorage = stopCriteriaStorage;
    }

    public Map<String, String> getDependabilityProperties()
    {
        return dependabilityProperties;
    }

    public void setDependabilityProperties(Map<String, String> dependabilityProperties)
    {
        this.dependabilityProperties = dependabilityProperties;
    }

}
