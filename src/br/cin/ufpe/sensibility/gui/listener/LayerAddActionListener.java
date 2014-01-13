package br.cin.ufpe.sensibility.gui.listener;

import br.cin.ufpe.sensibility.gui.ContainerJPanel;
import br.cin.ufpe.sensibility.gui.data.DataJPanel;
import br.cin.ufpe.sensibility.gui.data.LayerJPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author avld
 */
public class LayerAddActionListener implements ActionListener
{
    private ContainerJPanel container;
    private String layerName;
    
    public LayerAddActionListener( ContainerJPanel container , String layerName )
    {
        this.container = container;
        this.layerName = layerName;
    }
    
    @Override
    public void actionPerformed( ActionEvent e )
    {
        DataJPanel obj = new LayerJPanel( layerName );
        container.addPanel( obj );
    }
    
}
