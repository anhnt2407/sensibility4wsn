package br.cin.ufpe.sensibility.gui.listener;

import br.cin.ufpe.sensibility.gui.ContainerJPanel;
import br.cin.ufpe.sensibility.gui.data.RegionJPanel;
import br.cin.ufpe.sensibility.model.Region;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author avld
 */
public class RegionAddActionListener implements ActionListener
{
    private ContainerJPanel<Region> container;
    private int counter;
    
    public RegionAddActionListener( ContainerJPanel<Region> panel )
    {
        this.container = panel;
        this.counter = 1;
    }

    @Override
    public void actionPerformed( ActionEvent e ) 
   {
        RegionJPanel panel = new RegionJPanel( counter++ );
        container.addPanel( panel );
    }
    
}
